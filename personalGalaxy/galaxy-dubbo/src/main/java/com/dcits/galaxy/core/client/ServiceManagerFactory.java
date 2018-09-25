package com.dcits.galaxy.core.client;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;

/**
 * 服务管理工厂
 */
public final class ServiceManagerFactory {

	private String applicationName;
	private String version;
	private String owner;
	private String logger;
	private String address;
	private int timeout = 5000;
	private String protocol;
	private int port = -1;
	private boolean check = true;

	private ServiceManager serviceManager;

	/**
	 * 设置应用程序名称
	 * 
	 * @param applicationName
	 *            应用程序名称
	 */
	public synchronized void setApplicationName(String applicationName) {
		validate();
		this.applicationName = applicationName;
	}

	/**
	 * 设置版本
	 * 
	 * @param version
	 *            版本
	 */
	public synchronized void setVersion(String version) {
		validate();
		this.version = version;
	}

	/**
	 * 设置所有者
	 * 
	 * @param owner
	 *            所有者
	 */
	public synchronized void setOwner(String owner) {
		validate();
		this.owner = owner;
	}

	/**
	 * 设置日志提供者
	 * 
	 * @param logger
	 *            日志提供者
	 */
	public synchronized void setLogger(String logger) {
		validate();
		this.logger = logger;
	}

	/**
	 * 设置注册中心地址
	 * 
	 * @param address
	 *            注册中心地址
	 */
	public synchronized void setAddress(String address) {
		validate();
		this.address = address;
	}

	/**
	 * 设置超时时间
	 * 
	 * @param timeout
	 *            超时时间
	 */
	public synchronized void setTimeout(int timeout) {
		validate();
		this.timeout = timeout;
	}

	/**
	 * 设置协议
	 * 
	 * @param protocol
	 *            协议名称
	 */
	public void setProtocol(String protocol) {
		validate();
		this.protocol = protocol;
	}

	/**
	 * 设置Dubbo的端口
	 * 
	 * @param port
	 *            Dubbo的端口
	 */
	public synchronized void setPort(int port) {
		validate();
		if (port < 0 || port > 65535) {
			throw new IllegalArgumentException("Invalid port value [" + port
					+ "].");
		}
		this.port = port;
	}

	/**
	 * 设置是否检查服务提供者
	 * 
	 * @param check
	 *            是否检查服务提供者
	 */
	public synchronized void setCheck(boolean check) {
		validate();
		this.check = check;
	}

	private void validate() {
		if (serviceManager != null) {
			throw new IllegalStateException("ServiceManager already created!");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public synchronized ServiceManager create() {
		if (serviceManager == null) {
			if (applicationName == null) {
				throw new IllegalArgumentException(
						"No application name was set, please set one.");
			} else if (address == null) {
				throw new IllegalArgumentException(
						"No address was set, please set one.");
			} else if (port == -1) {
				throw new IllegalArgumentException(
						"No port was set, please set one.");
			}

			ApplicationConfig applicationConfig = new ApplicationConfig();
			applicationConfig.setName(applicationName);
			if (version != null) {
				applicationConfig.setVersion(version);
			}
			if (owner != null) {
				applicationConfig.setOwner(owner);
			}
			if (logger != null) {
				applicationConfig.setLogger(logger);
			}

			RegistryConfig registryConfig = new RegistryConfig();
			registryConfig.setAddress(address);
			registryConfig.setProtocol(protocol);
			registryConfig.setTimeout(timeout);
			registryConfig.setCheck(check);
			registryConfig.setPort(port);

			applicationConfig.setRegistry(registryConfig);

			serviceManager = new ServiceManagerImpl(applicationConfig);
		}

		return serviceManager;
	}
}
