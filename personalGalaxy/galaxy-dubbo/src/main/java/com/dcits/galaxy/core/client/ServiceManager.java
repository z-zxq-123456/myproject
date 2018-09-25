package com.dcits.galaxy.core.client;

import com.dcits.galaxy.core.client.builder.Attributes;

/**
 * <pre>
 * 服务管理
 * 
 * 1、{@code ServiceManager}实例销毁后，将销毁其管理的所有服务(清除缓存以及取消订阅)，{@link #getService(Class)}和{@link #getService(Class, Attributes)}方法调用均会抛出异常
 * 2、{@code ServiceManager}实例销毁后，其管理的服务进行方法调用，亦将抛出异常
 * 3、{@code getService}获取到的服务可以安全地强制转换为{@link Destroyable}接口实例，调用该接口上的方法永远不会抛出异常
 * 4、{@link Destroyable#destroy()}将销毁单个服务，一旦服务销毁，则再次调用服务的方法时将抛出异常。销毁服务会同时从{@code ServiceManager}的缓存中清除以及取消订阅
 * 5、{@link Destroyable#isDestroyed()}可以判断服务是否被销毁，销毁{@code ServiceManager}意味着其管理的服务也被销毁
 * </pre>
 */
public interface ServiceManager {

	/**
	 * 获取服务<br/>
	 * 注意，该方法获取到的服务将采用全局属性，即{@link ServiceManagerFactory}内配置的属性
	 * 
	 * @param serviceInterface 服务接口
	 * @return 服务接口实例
	 * @throws IllegalArgumentException {@code serviceInterface}为{@code null}或者不是接口时抛出
	 * @throws IllegalStateException 该{@code ServiceManager}已经销毁时抛出
	 */
	<T> T getService(Class<T> serviceInterface);

	/**
	 * 获取服务<br/>
	 * {@code attributes}可提供重载属性，以便覆盖全局属性
	 * 
	 * @param serviceInterface 服务接口
	 * @param attributes 重载属性，用来覆盖全局属性，可以为{@code null}，表示不进行覆盖
	 * @return 服务接口实例
	 * @throws IllegalArgumentException {@code serviceInterface}为{@code null}或者不是接口时抛出
	 * @throws IllegalStateException 该{@code ServiceManager}已经销毁时抛出
	 */
	<T> T getService(Class<T> serviceInterface, Attributes attributes);

	/**
	 * 获取受该{@code ServiceManager}管理的服务数量
	 * 
	 * @return 受该{@code ServiceManager}管理的服务数量
	 */
	int getServiceCount();

	/**
	 * 销毁{@code ServiceManager}及其管理的所有服务<br/>
	 * 该操作将清空ServiceManager内部缓存并取消订阅所有服务
	 */
	void destroy();

	/**
	 * 判断该{@code ServiceManager}是否已经销毁
	 * 
	 * @return 已销毁返回{@code true}，否则返回{@code false}
	 */
	boolean isDestroyed();
}
