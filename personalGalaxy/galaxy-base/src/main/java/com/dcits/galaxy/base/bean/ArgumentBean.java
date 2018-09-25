package com.dcits.galaxy.base.bean;

/**
 * 入参bean实现类
 * 
 * @author Tim
 *
 */
public class ArgumentBean extends AbstractBean{

	private static final long serialVersionUID = 3464535062585822315L;

	private String argType;

	private String argValue;

	public String getArgType() {
		return argType;
	}

	public void setArgType(String argType) {
		this.argType = argType;
	}

	public String getArgValue() {
		return argValue;
	}

	public void setArgValue(String argValue) {
		this.argValue = argValue;
	}
}
