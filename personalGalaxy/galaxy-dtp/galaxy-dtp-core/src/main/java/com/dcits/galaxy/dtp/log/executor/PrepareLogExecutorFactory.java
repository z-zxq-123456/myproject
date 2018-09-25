package com.dcits.galaxy.dtp.log.executor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 准备日志执行器工厂类
 * @author Yin.Weicai
 */
public class PrepareLogExecutorFactory {

	private static ConcurrentMap<String, PrepareLogExecutor> logExecutorCache = 
			new ConcurrentHashMap<String, PrepareLogExecutor>();
	
	/**
	 * 根据日志类型名获取对应的日志处理器
	 * @param logTypeName 
	 * @return
	 */
	public static PrepareLogExecutor getLogExecutor(String logTypeName){
		return logExecutorCache.get(logTypeName);
	}
	
	/**
	 * 注册具体的事务准备日志处理器实现
	 * @param logExecutor
	 */
	public static void registerBean(PrepareLogExecutor logExecutor){
		String logTypeName = logExecutor.getLogTypeName();
		logExecutorCache.put( logTypeName, logExecutor );
	}
	
	/**
	 * 移除注册的具体的事务准备日志处理器实现
	 * @param logExecutor
	 */
	public static void deregisterBean(PrepareLogExecutor logExecutor){
		String logTypeName = logExecutor.getLogTypeName();
		deregisterBean(logTypeName);
	}
	
	/**
	 * 移除注册的具体的事务准备日志处理器实现
	 * @param logTypeName
	 */
	public static void deregisterBean(String logTypeName){
		logExecutorCache.remove( logTypeName );
	}
}
