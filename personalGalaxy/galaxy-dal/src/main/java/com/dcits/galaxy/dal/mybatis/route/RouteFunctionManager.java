package com.dcits.galaxy.dal.mybatis.route;

import java.util.HashMap;
import java.util.Map;

/**
 * 路由函数管理
 * @author chenhongk
 *
 */
public class RouteFunctionManager{
	
	private Map<String,Object> functionMap = null;
	
	public RouteFunctionManager(){
		
	}
	/**
	 * 获得路由函数集合
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getFunctionMap() {
		Map<String,Object> tempFunctionMap = new HashMap<String,Object>(functionMap);
		return tempFunctionMap;
	}
	/**
	 * 根据key 获得对应的路由函数
	 * @param key
	 * @return Object
	 */
	public Object getFunctionByKey(String key){
		return functionMap.get(key);
	}
	
	public void setFunctionMap(Map<String, Object> functionMap) {
		this.functionMap = functionMap;
	}
}
