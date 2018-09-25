/**   
 * <p>Title: GXJob.java</p>
 * <p>Description: Sqoop Job定义</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.model;

/**
 * @description Galaxy Job定义
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午3:25:27
 */

public class GXJob extends AbstractJob {

	private String api;

	private String method;

	private Arguments arguments;

	/**
	 * @return api : return the property api.
	 */
	public String getApi() {
		return api;
	}

	/**
	 * @param api
	 *            : set the property api.
	 */
	public void setApi(String api) {
		this.api = api;
	}

	/**
	 * @return method : return the property method.
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method
	 *            : set the property method.
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return arguments : return the property arguments.
	 */
	public Arguments getArgs() {
		return arguments;
	}

	/**
	 * @param arguments
	 *            : set the property arguments.
	 */
	public void setArgs(Arguments arguments) {
		this.arguments = arguments;
	}

}
