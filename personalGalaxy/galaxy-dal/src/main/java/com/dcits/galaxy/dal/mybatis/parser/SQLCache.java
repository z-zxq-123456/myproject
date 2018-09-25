package com.dcits.galaxy.dal.mybatis.parser;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.ibatis.mapping.MappedStatement;

public class SQLCache {

	private final static ConcurrentMap<String, MappedStatement> msCache = new ConcurrentHashMap<>(128);
	private static Object readLock = new Object();
	private static Object writeLock = new Object();
	
	public static void put(String sqlMap, MappedStatement ms){
		synchronized (readLock) {
			if( msCache.containsKey(sqlMap) ){
				return ;
			}
		}
		synchronized (writeLock) {
			if( !msCache.containsKey(sqlMap) ){
				msCache.put(sqlMap, ms);
			}
		}
	}
	
	public static MappedStatement get(String sqlMap){
		return msCache.get( sqlMap );
	}
}
