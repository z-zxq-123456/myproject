package com.dcits.galaxy.dtp.log;


/**
 * 分支事务管理器工厂类
 * @author Yin.Weicai
 *
 */
public class LogManagerFactory {
	
	private static LogManager logManager = null;
	
	public static LogManager getBean(){
		return logManager;
	}
	
	/**
	 * 注册具体的分支事务管理器实现
	 * @param branchManager
	 */
	public static void registerBean(LogManager logManager){
		LogManagerFactory.logManager = logManager;
	}
}
