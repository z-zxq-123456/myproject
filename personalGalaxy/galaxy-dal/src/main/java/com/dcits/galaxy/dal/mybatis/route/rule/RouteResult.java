package com.dcits.galaxy.dal.mybatis.route.rule;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 路由结果集
 * 
 * @author chenhongk
 *
 */
public class RouteResult {
	/**
	 * 路由结果集类型
	 * 包含 list、map两种类型
	 */
	private String type;
	/**
	 * list 类型 结果集分隔符,默认为","
	 */
	private String separator;
	/**
	 * 类型为list结果集合
	 */
	private List<String> resultList;
	/**
	 * 类型为map结果集合
	 */
	private Map<String, Set<String>> resultMap;

	public RouteResult() {
	}

	public RouteResult(String type, String separator, List<String> resultList, Map<String, Set<String>> resultMap) {
		this.type = type;
		this.separator = separator;
		this.resultList = resultList;
		this.resultMap = resultMap;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public List<String> getResultList() {
		return resultList;
	}

	public void setResultList(List<String> resultList) {
		this.resultList = resultList;
	}

	public Map<String, Set<String>> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Set<String>> resultMap) {
		this.resultMap = resultMap;
	}

}
