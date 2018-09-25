package com.dcits.galaxy.dal.mybatis.route.rule.exp;
/**
 * 路由表达式形参
 * @author chenhongk
 *
 * @param <T>
 */
public class FormalParam<T> {
	/**
	 * 参数名
	 */
	private String name;
	/**
	 * 参数类型
	 */
	private Class<?> type;
	/**
	 * 参数值
	 */
	private T value;

	public FormalParam() {
	}

	public FormalParam(String name, Class<?> type, T value) {
		this.name = name;
		this.type = type;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	

}
