package com.dcits.galaxy.dal.mybatis.transaction;

import java.util.HashMap;
import java.util.Map;

public class TransactionExtensionConfig {
	private static ThreadLocal<Map<String, Object>> localConfig = new ThreadLocal<>();
	
	public static final String ORDER_SUBMIT = "order";
	
	public static final String RECORD_LOG = "record";
	
	public static boolean isOrderSubmit(){
		return getConfig(ORDER_SUBMIT) != null;
	}
	
	public static boolean isrRecordLog(){
		return getConfig(RECORD_LOG) != null;
	}
	
	public static void setOrderSubmit(boolean order){
		setBoolean(ORDER_SUBMIT, order);
	}
	
	public static void setRecordLog(boolean order){
		setBoolean(RECORD_LOG, order);
	}
	
	private static Object getConfig(String name){
		Map<String, Object> config = localConfig.get();
		if(config == null){
			return null;
		}
		return config.get(name);
	}
	
	private static Object setConfig(String name, Object obj){
		Map<String, Object> config = localConfig.get();
		if(config == null){
			config = new HashMap<String, Object>();
			localConfig.set(config);
		}
		return config.put(name, obj);
	}
	
	private static void setBoolean(String name, boolean flag){
		if(flag){
			setConfig(name, Boolean.TRUE);
		} else {
			setConfig(name, null);
		}
	}
}
