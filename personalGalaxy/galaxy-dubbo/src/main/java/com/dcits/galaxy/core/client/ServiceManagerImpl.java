package com.dcits.galaxy.core.client;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.rpc.service.EchoService;
import com.dcits.galaxy.core.client.builder.Attributes;
import com.dcits.galaxy.core.client.builder.AttributesConstants;
import com.dcits.galaxy.core.client.utils.ClasslLoaderUtils;

final class ServiceManagerImpl implements ServiceManager, AttributesConstants {

	private static final Map<String, Method> METHOD_MAPPINGS = new HashMap<String, Method>();

	static {
		BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(ReferenceConfig.class, Object.class);
		} catch (IntrospectionException e) {
			throw new IllegalStateException("Can't introspect bean of type [" + ReferenceConfig.class.getName() + "].",
					e);
		}

		Set<String> ignoredProperties = new HashSet<String>(Arrays.asList("application", "module", "consumer",
				"registries", "registry", "protocol", "owner"));

		for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
			Method writeMethod = descriptor.getWriteMethod();
			if (writeMethod != null) {
				String property = descriptor.getName();
				if (!ignoredProperties.contains(property)) {
					METHOD_MAPPINGS.put(property, writeMethod);
				}
			}
		}
	}

	/**
	 * 服务缓存
	 */
	private final Map<String, Object> serviceCache = new HashMap<String, Object>();

	/**
	 * 应用配置
	 */
	private final ApplicationConfig applicationConfig;

	/**
	 * ServiceManager是否被销毁
	 */
	private final AtomicBoolean destroyed = new AtomicBoolean(false);

	ServiceManagerImpl(ApplicationConfig applicationConfig) {
		this.applicationConfig = applicationConfig;
	}

	@Override
	public <T> T getService(Class<T> serviceInterface) {
		return getService(serviceInterface, null);
	}

	@Override
	public <T> T getService(Class<T> serviceInterface, Attributes attributes) {
		synchronized (serviceCache) {
			if (destroyed.get()) {
				throw new IllegalStateException("This ServiceManager has already been destroyed.");
			}

			if (serviceInterface == null) {
				throw new IllegalArgumentException("Service interface is null.");
			} else if (!serviceInterface.isInterface()) {
				throw new IllegalArgumentException("[" + serviceInterface.getName()
						+ "] is not an interface which can't be used for obtaining a service instance.");
			}

			// 生成缓存用到的key
			String key;
			if (attributes != null && !attributes.getAttributeNames().isEmpty()) {
				key = serviceInterface.getName() + '_' + attributes.toString();
			} else {
				key = serviceInterface.getName();
			}

			Object service = serviceCache.get(key);
			if (service == null) {
				// 类加载器，兼容目标接口和Destroyable, EchoService接口
				ClassLoader classLoader = ClasslLoaderUtils.createClassLoader(serviceInterface, Destroyable.class,
						EchoService.class);

				// 创建service代理对象
				service = Proxy.newProxyInstance(classLoader, new Class<?>[] { serviceInterface, Destroyable.class,
						EchoService.class }, new ServiceHandler<T>(createReferenceConfig(serviceInterface, attributes),
						serviceCache, destroyed));
				serviceCache.put(key, service);
			}

			// 测试服务提供者是否可用
			boolean check = applicationConfig.getRegistry().isCheck();
			if (attributes != null && attributes.hasAttribute(CHECK)) {
				check = (Boolean) attributes.getAttribute(CHECK);
			}
			if (check) {
				((EchoService) service).$echo(true);
			}

			return serviceInterface.cast(service);
		}
	}

	@Override
	public int getServiceCount() {
		synchronized (serviceCache) {
			return serviceCache.size();
		}
	}

	@Override
	public void destroy() {
		List<Object> services = null;

		synchronized (serviceCache) {
			if (destroyed.get()) {
				return;
			} else {
				destroyed.set(true);

				services = new ArrayList<Object>(serviceCache.values());
				serviceCache.clear();
			}
		}

		// Destroyable对象的销毁操作会在ServiceHandler内具体执行
		// 此时将会进入另外一个以serviceCache为锁的同步块，为了避免引起死锁
		// 必须先收集Destroyable对象集合，然后在同步块外逐个销毁
		if (services != null) {
			for (Object object : services) {
				if (object instanceof Destroyable) {
					try {
						((Destroyable) object).destroy();
					} catch (Exception ignore) {
					}
				}
			}
		}
	}

	@Override
	public boolean isDestroyed() {
		synchronized (serviceCache) {
			return destroyed.get();
		}
	}

	private <T> ReferenceConfig<T> createReferenceConfig(Class<T> serviceInterface, Attributes attributes) {
		ReferenceConfig<T> config = new ReferenceConfig<T>();
		config.setApplication(applicationConfig);
		config.setInterface(serviceInterface);

		// 覆盖全局属性
		if (attributes != null) {
			for (String attributeName : attributes.getAttributeNames()) {
				Method writeMethod = METHOD_MAPPINGS.get(attributeName);
				if (writeMethod != null) {
					if (!attributes.hasAttribute(attributeName)) {
						continue;
					}

					try {
						writeMethod.invoke(config, attributes.getAttribute(attributeName));
					} catch (Exception e) {
						throw new IllegalStateException("Error set attribute [" + attributeName
								+ "] to ReferenceConfig object.", e);
					}
				}
			}

			// Use remote scope by default
			if (!attributes.hasAttribute(SCOPE)) {
				config.setScope("remote");
			}
		}

		return config;
	}

	private static class ServiceHandler<T> implements InvocationHandler {

		private volatile boolean destroyed = false;
		private final ReferenceConfig<T> referenceConfig;
		private final Map<String, Object> serviceCache;
		private final AtomicBoolean allDestroyed;

		ServiceHandler(ReferenceConfig<T> referenceConfig, Map<String, Object> serviceCache, AtomicBoolean allDestroyed) {
			this.referenceConfig = referenceConfig;
			this.serviceCache = serviceCache;
			this.allDestroyed = allDestroyed;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if (method.getDeclaringClass() == Destroyable.class) {
				synchronized (serviceCache) {
					if ("isDestroyed".equals(method.getName())) {
						return destroyed || allDestroyed.get();
					} else if ("destroy".equals(method.getName())) {
						if (!destroyed) {
							destroyed = true;

							String key = null;
							for (Entry<String, Object> entry : serviceCache.entrySet()) {
								if (entry.getValue() == proxy) { // "=="直接采用对象地址判断对象相等
									key = entry.getKey();
									break;
								}
							}

							if (key != null) {
								serviceCache.remove(key);
							}
							referenceConfig.destroy();
						}
						return null;
					}
				}
			}

			if (allDestroyed.get()) {
				throw new IllegalStateException("ServiceManager which contains service instance of type ["
						+ method.getDeclaringClass().getName() + "] has already been destroyed.");
			} else if (destroyed) {
				throw new IllegalStateException(
						"Service instance of type ["
								+ method.getDeclaringClass().getName()
								+ "] has already been destroyed, you may call ServiceManager.getService() again to obtain a new service.");
			}

			return method.invoke(referenceConfig.get(), args);
		}
	}
}
