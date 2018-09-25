package com.dcits.galaxy.core.client;

/**
 * <pre>
 * 可销毁的对象
 * 
 * 从{@link ServiceManager#getService(Class)}或{@link ServiceManager#getService(Class, Attributes)}方法获取到的服务，
 * 均可以安全地转换为{@code Destroyable}接口实例
 * </pre>
 */
public interface Destroyable {

	/**
	 * 销毁对象
	 */
	void destroy();

	/**
	 * <pre>
	 * 判断是否已销毁
	 * 
	 * 两种销毁方式：
	 * 1、{@code ServiceManager}管理的服务可以直接转换为{@code Destroyable}接口，然后调用{@link #destroy()}进行销毁，这种方式仅销毁单个服务
	 * 2、直接销毁{@code ServiceManager}，此种方式将销毁该{@code ServiceManager}管理的所有服务
	 * 
	 * 不论通过哪种方式进行销毁，一旦服务被销毁，{@code isDestroyed()}方法均返回{@code true}
	 * </pre>
	 * 
	 * @return 已销毁返回{@code true}，否则返回{@code false}
	 */
	boolean isDestroyed();
}
