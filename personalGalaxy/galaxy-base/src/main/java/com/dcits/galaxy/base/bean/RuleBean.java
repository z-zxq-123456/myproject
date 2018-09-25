package com.dcits.galaxy.base.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * A、O、D类检查服务bean
 * 
 * @author Tim
 *
 */
public class RuleBean extends AbstractBean {

	private static final long serialVersionUID = 790887522010703201L;

	private String desc;

	private String api;

	private boolean async = false;

	private String method;
	
	private String sourceType;

	private List<ArgumentBean> args = new ArrayList<ArgumentBean>();

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public List<ArgumentBean> getArgs() {
		return args;
	}

	public void setArgs(List<ArgumentBean> args) {
		this.args = args;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

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
	 * @return async : return the property async.
	 */
	public boolean isAsync() {
		return async;
	}

	/**
	 * @param async
	 *            : set the property async.
	 */
	public void setAsync(boolean async) {
		this.async = async;
	}

	/**
	 * @return sourceType : return the property sourceType.
	 */
	public String getSourceType() {
		return sourceType;
	}

	/**
	 * @param sourceType : set the property sourceType.
	 */
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
}
