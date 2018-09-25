package com.dcits.galaxy.core.client.spring;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.dcits.galaxy.core.client.Destroyable;
import com.dcits.galaxy.core.client.ServiceManager;
import com.dcits.galaxy.core.client.builder.AbstractServiceAttributesBuilder;
import com.dcits.galaxy.core.client.utils.ClasslLoaderUtils;

public final class ServiceFactoryBean<T> extends AbstractServiceAttributesBuilder<ServiceFactoryBean<T>> implements
		FactoryBean<T>, InitializingBean, DisposableBean {

	private Class<T> serviceInterface;
	private ServiceManager serviceManager;
	private T service;

	public void setServiceInterface(Class<T> serviceInterface) {
		this.serviceInterface = serviceInterface;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		service = serviceManager.getService(serviceInterface, build());
	}

	@Override
	public void destroy() throws Exception {
		((Destroyable) service).destroy();
	}

	@Override
	public T getObject() throws Exception {
		return serviceInterface.cast(Proxy.newProxyInstance(
				ClasslLoaderUtils.createClassLoader(serviceInterface, Destroyable.class), new Class<?>[] {
						serviceInterface, Destroyable.class }, new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if (method.getDeclaringClass() == Destroyable.class) {
							if ("destroy".equals(method.getName())) {
								throw new UnsupportedOperationException("Service of type ["
										+ serviceInterface.getName()
										+ "] created by spring FactoryBean should not be destroyed.");
							}
						}
						return method.invoke(service, args);
					}
				}));
	}

	@Override
	public Class<?> getObjectType() {
		return serviceInterface;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
