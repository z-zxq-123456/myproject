package com.dcits.galaxy.core.client;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.common.utils.UrlUtils;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;
import com.dcits.galaxy.base.exception.NoProviderException;
import com.dcits.galaxy.core.client.builder.Attributes;
import com.dcits.galaxy.core.client.builder.AttributesConstants;
import com.dcits.galaxy.core.client.builder.ServiceAttributesBuilder;

/**
 * 服务代理
 * 
 * @author xuecy
 *
 */
public class ServiceProxy implements AttributesConstants{

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
	 * ref缓存
	 */
	@SuppressWarnings("rawtypes")
	private final Map<String, ReferenceConfig> refCache = new HashMap<String, ReferenceConfig>();

	/**
	 * 应用配置
	 */
	private final ApplicationConfig applicationConfig;

	/**
	 * ServiceManager是否被销毁
	 */
	private final AtomicBoolean destroyed = new AtomicBoolean(false);

	private static ServiceProxy instance = null;
	/**
	 * 注册中心实例 zookeeper
	 */
	private final Registry registry;

	public static synchronized ServiceProxy getInstance() {
		if (instance == null) {
			instance = new ServiceProxy();
		}
		return instance;
	}
	
	public static synchronized void realseInstance() {
		if(instance != null){
			instance.destroy();
		}
		instance = null;
	}
		
	ServiceProxy() {		
		
		Properties properties = loadProperties();

		ApplicationConfig applicationConfig = new ApplicationConfig();

		// 应用名
		String applicationName = properties
				.getProperty("galaxy.application.name");
		if (applicationName == null) {
			throw new IllegalArgumentException(
					"No application name was set, please set it by [galaxy.application.name] in your [galaxy.properties] file.");
		}

		// 地址
		String address = properties.getProperty("galaxy.registry.address");
		if (address == null) {
			throw new IllegalArgumentException(
					"No address was set, please set it by [galaxy.registry.address] in your [galaxy.properties] file.");
		}

		// 协议
		String protocol = properties.getProperty("galaxy.protocol.name");
		if (protocol == null) {
			throw new IllegalArgumentException(
					"No protocol was set, please set it by [galaxy.protocol.name] in your [galaxy.properties] file.");
		}

		// 端口
		String port = properties.getProperty("galaxy.protocol.port");
		if (port == null) {
			throw new IllegalArgumentException(
					"No port was set, please set it by [galaxy.protocol.dubbo.port] in your [galaxy.properties] file.");
		}

		// 所有者
		String owner = properties.getProperty("galaxy.application.owner");

		
		applicationConfig.setName(applicationName);
		if (owner != null) {
			applicationConfig.setOwner(owner);
		}

		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setAddress(address);
		// xuecy 2015-08-03
		// 这里的protocol指的是服务调用时使用的协议，并不是注册中心使用的协议，不设置采用跟address一致的即可。
		// registryConfig.setProtocol(protocol);
		registryConfig.setCheck(true);
		registryConfig.setPort(Integer.parseInt(port));
		
		applicationConfig.setRegistry(registryConfig);

		this.applicationConfig = applicationConfig;
		
		this.registry = getRegistry();
	}

	/**
	 * 获取服务的代理
	 * 
	 * @param serviceInterface
	 * @return
	 */
	public <T> T getService(Class<T> serviceInterface) {
		if (serviceInterface == null) {
			throw new IllegalArgumentException("Service interface is null.");
		} else if (!serviceInterface.isInterface()) {
			throw new IllegalArgumentException("[" + serviceInterface.getName()
					+ "] is not an interface which can't be used for obtaining a service instance.");
		}
		return getService(serviceInterface.getName(), null);
	}
	
	/**
	 * 获取服务的代理
	 * 
	 * @param serviceInterface
	 * @return
	 */
	public <T> T getService(String serviceInterface) {
		return getService(serviceInterface, null);
	}

	/**
	 * 获取服务的代理（指定服务提供者地址）
	 * 
	 * @param serviceInterface
	 * @param url
	 * @return
	 */
	public <T> T getServiceOfUrl(Class<T> serviceInterface, String url) {
		Attributes attr = new ServiceAttributesBuilder().setUrl(url).build();
		return getService(serviceInterface, attr);
	}
	public <T> T getServiceOfUrl(String serviceInterface, String url) {
		Attributes attr = new ServiceAttributesBuilder().setUrl(url).build();
		return getService(serviceInterface, attr);
	}

	/**
	 * 获取服务的所有提供者的代理集合
	 * 
	 * @param serviceInterface
	 * @param url
	 * @return
	 */
	public <T> List<T> getServiceAll(Class<T> serviceInterface) {
		List<T> proxys = new ArrayList<T>();
		List<String> urls = this.getProviderList(serviceInterface);
		for (String url : urls) {
			T t = getServiceOfUrl(serviceInterface,url);
			proxys.add(t);			
		}
		return proxys;
	}
	
	public <T> List<T> getServiceAll(String serviceInterface) {
		List<T> proxys = new ArrayList<T>();
		List<String> urls = this.getProviderList(serviceInterface);
		for (String url : urls) {
			T t = getServiceOfUrl(serviceInterface,url);
			proxys.add(t);			
		}
		return proxys;
	}
	
	/**
	 * 获取服务的代理,可以指定各种调用参数
	 * 
	 * @param serviceInterface
	 * @param attributes
	 * @return
	 */
	public <T> T getService(Class<T> serviceInterface, Attributes attributes) {
		if (serviceInterface == null) {
			throw new IllegalArgumentException("Service interface is null.");
		} else if (!serviceInterface.isInterface()) {
			throw new IllegalArgumentException("[" + serviceInterface.getName()
					+ "] is not an interface which can't be used for obtaining a service instance.");
		}
		String serviceName = serviceInterface.getName();
		return getService(serviceName, attributes);
	}
	
	public <T> T getService(String serviceInterface, Attributes attributes) {
		
		if (serviceInterface == null) {
			throw new IllegalArgumentException("Service interface is null.");
		}
		
		synchronized (refCache) {
			if (destroyed.get()) {
				throw new IllegalStateException("This ServiceManager has already been destroyed.");
			}
			// 生成缓存用到的key
			String key;
			if (attributes != null && !attributes.getAttributeNames().isEmpty()) {
				key = serviceInterface + '_' + attributes.toString();
			} else {
				key = serviceInterface;
			}

			@SuppressWarnings("unchecked")
			ReferenceConfig<T> ref = refCache.get(key);
			if (ref == null) {
				ref = createReferenceConfig(serviceInterface, attributes);
			}

			// get
			T ret = null;
			try {
				ret = ref.get();
			} catch (java.lang.IllegalStateException e) {
				//no provider(只在强制进行提供者检查时触发，否则在调用时触发。)
				throwNoProviderException(serviceInterface, e);
			}

			if (ret == null) {
				throwNoProviderException(serviceInterface, null);
			}
			
			refCache.put(key, ref);
			return ret;
		}
	}
	
	private <T> void throwNoProviderException(String serviceInterface, Throwable t) {
		throw new NoProviderException(
				" No provider available for the service ("
						+ serviceInterface + ").", t);
	}
	
	public int getServiceCount() {
		synchronized (refCache) {
			return refCache.size();
		}
	}
	
	/**
	 * 获取指定服务名称的提供者列表，来源于ZK。
	 * @return
	 */
	public List<String> getProviderList(Class<?> serviceInterface) {
		return getProviderList(serviceInterface.getName());
	}
	
	
	public List<String> getProviderList(String serviceInterface) {
		List<String> urls =  new ArrayList<String>();
		String path = "*/"+serviceInterface;
		List<URL> list = this.registry.lookup(com.alibaba.dubbo.common.URL.valueOf(path));
		for (URL str : list) {
			urls.add(str.toString());
		}
		return urls;
	}
	/**
	 * 获得注册中心注册者实例 zookeeper
	 * @return
	 */
	private Registry getRegistry(){
		String zkPath = this.applicationConfig.getRegistry().getAddress();
		Map<String,String> map = new HashMap<String,String>();
		//设置注册者协议，此处为zookeeper
		map.put("protocol", "zookeeper");
		URL url = UrlUtils.parseURL(zkPath, map);
		RegistryFactory registryFactory = 
				ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();
		Registry registry = registryFactory.getRegistry(url);
		return registry;
	} 
	/**
	 * 获得zk注册地址
	 * @return
	 */
	public String getZkUrl(){
		return this.registry.getUrl().getBackupAddress();
	}
	
	public void destroy() {
		synchronized (refCache) {
			if (destroyed.get()) {
				return;
			} else {
				for (ReferenceConfig<?> ref : refCache.values()) {
					ref.destroy();
				}
				refCache.clear();
				destroyed.set(true);
			}
		}
	}

	public boolean isDestroyed() {
		synchronized (refCache) {
			return destroyed.get();
		}
	}
	
	private <T> ReferenceConfig<T> createReferenceConfig(String serviceInterface, Attributes attributes) {
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

			// xuecy 2015-08-03
			// 取消scope的默认设置，该处的逻辑跟使用xml定义一样，优先使用内部调用。
			// // Use remote scope by default
			// if (!attributes.hasAttribute(SCOPE)) {
			// config.setScope("remote");
			// }
		}

		return config;
	}

	private static Properties loadProperties() {
		InputStream inputStream = null;
		try {
			inputStream = ServiceProxy.class
					.getResourceAsStream("/galaxy.properties");
			if (inputStream == null) {
				throw new IllegalStateException(
						"[galaxy.properties] not found, please put this file under your classpath root.");
			}

			Properties properties = new Properties();
			properties.load(inputStream);

			return properties;
		} catch (IOException e) {
			throw new IllegalStateException(
					"Error occured while reading [/galaxy.properties].", e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ignore) {
				}
			}
		}
	}
}
