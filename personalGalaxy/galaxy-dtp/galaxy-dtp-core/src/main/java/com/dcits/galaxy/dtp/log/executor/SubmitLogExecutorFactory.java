package com.dcits.galaxy.dtp.log.executor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 提交日志执行器工厂类
 * @author Yin.Weicai
 */
public class SubmitLogExecutorFactory {
	
	private static ConcurrentMap<String, SubmitLogExecutor> logExecutorCache = 
			new ConcurrentHashMap<String, SubmitLogExecutor>();
	
	/**
	 * 根据日志类型名获取对应的日志处理器
	 * @param logTypeName 
	 * @return
	 */
	public static SubmitLogExecutor getLogExecutor(String logTypeName){
		return logExecutorCache.get(logTypeName);
	}
	
	/**
	 * 注册具体的事务提交日志处理器实现
	 * @param logExecutor
	 */
	public static void registerBean(SubmitLogExecutor logExecutor){
		String logTypeName = logExecutor.getLogTypeName();
		logExecutorCache.put( logTypeName, logExecutor );
	}
	
	/**
	 * 移除注册的具体的事务提交日志处理器实现
	 * @param logExecutor
	 */
	public static void deregisterBean(SubmitLogExecutor logExecutor){
		String logTypeName = logExecutor.getLogTypeName();
		deregisterBean(logTypeName);
	}
	
	/**
	 * 移除注册的具体的事务提交日志处理器实现
	 * @param logExecutor
	 */
	public static void deregisterBean(String logTypeName){
		logExecutorCache.remove( logTypeName );
	}
}
