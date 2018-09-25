package com.dcits.galaxy.core.client.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.dcits.galaxy.core.client.ServiceManager;
import com.dcits.galaxy.core.client.ServiceManagerFactory;
import com.dcits.galaxy.core.client.builder.Attributes;

public final class ServiceManagerFactoryBean implements FactoryBean<ServiceManager>, InitializingBean, DisposableBean {

	private ServiceManager serviceManager;
	private final ServiceManagerFactory serviceManagerFactory = new ServiceManagerFactory();

	/**
	 * 设置应用程序名称
	 * 
	 * @param applicationName 应用程序名称
	 */
	public void setApplicationName(String applicationName) {
		serviceManagerFactory.setApplicationName(applicationName);
	}

	/**
	 * 设置版本
	 * 
	 * @param version 版本
	 */
	public void setVersion(String version) {
		serviceManagerFactory.setVersion(version);
	}

	/**
	 * 设置所有者
	 * 
	 * @param owner 所有者
	 */
	public void setOwner(String owner) {
		serviceManagerFactory.setOwner(owner);
	}

	/**
	 * 设置日志提供者
	 * 
	 * @param logger 日志提供者
	 */
	public void setLogger(String logger) {
		serviceManagerFactory.setLogger(logger);
	}

	/**
	 * 设置注册中心地址
	 * 
	 * @param registry 注册中心地址
	 */
	public void setAddress(String address) {
		serviceManagerFactory.setAddress(address);
	}

	/**
	 * 设置超时时间
	 * 
	 * @param timeout 超时时间
	 */
	public void setTimeout(int timeout) {
		serviceManagerFactory.setTimeout(timeout);
	}

	/**
	 * 设置Dubbo的端口
	 * 
	 * @param port Dubbo的端口
	 */
	public void setPort(int port) {
		serviceManagerFactory.setPort(port);
	}

	/**
	 * 设置是否检查服务提供者
	 * 
	 * @param check 是否检查服务提供者
	 */
	public void setCheck(boolean check) {
		serviceManagerFactory.setCheck(check);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		serviceManager = serviceManagerFactory.create();
	}

	@Override
	public void destroy() throws Exception {
		serviceManager.destroy();
	}

	@Override
	public ServiceManager getObject() throws Exception {
		return new UndestroyableServiceManager(serviceManager);
	}

	@Override
	public Class<?> getObjectType() {
		return ServiceManager.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	private static class UndestroyableServiceManager implements ServiceManager {

		private final ServiceManager serviceManager;

		UndestroyableServiceManager(ServiceManager serviceManager) {
			this.serviceManager = serviceManager;
		}

		@Override
		public <T> T getService(Class<T> serviceInterface) {
			return serviceManager.getService(serviceInterface);
		}

		@Override
		public <T> T getService(Class<T> serviceInterface, Attributes attributes) {
			return serviceManager.getService(serviceInterface, attributes);
		}

		@Override
		public int getServiceCount() {
			return serviceManager.getServiceCount();
		}

		@Override
		public void destroy() {
			throw new UnsupportedOperationException("ServiceManager managed by spring should not be destroyed.");
		}

		@Override
		public boolean isDestroyed() {
			return serviceManager.isDestroyed();
		}
	}
}
