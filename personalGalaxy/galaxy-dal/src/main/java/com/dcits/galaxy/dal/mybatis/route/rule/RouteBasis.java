package com.dcits.galaxy.dal.mybatis.route.rule;

import java.util.Map;
/**
 * 路由依据
 * @author chenhongk
 *
 */
public class RouteBasis {
	/**
	 * 依据名
	 */
	private String basis;
	/**
	 * 实参映射
	 */
	private Map<String, String> actualParamMap;
	
	public RouteBasis() {
	}

	
	public RouteBasis(String basis, Map<String, String> actualParamMap) {
		this.basis = basis;
		this.actualParamMap = actualParamMap;
	}

	public String getBasis() {
		return basis;
	}

	public void setBasis(String basis) {
		this.basis = basis;
	}

	public Map<String, String> getActualParamMap() {
		return actualParamMap;
	}

	public void setActualParamMap(Map<String, String> actualParamMap) {
		this.actualParamMap = actualParamMap;
	}
	
	
}
