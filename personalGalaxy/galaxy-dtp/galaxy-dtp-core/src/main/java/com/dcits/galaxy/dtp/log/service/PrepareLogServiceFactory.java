package com.dcits.galaxy.dtp.log.service;


/**
 * 准备日志执行器工厂类
 *
 * @author Yin.Weicai
 */
public class PrepareLogServiceFactory {

    private static PrepareLogService logService = null;
    
    /**
     * 获取准备日志持久化处理实现
     * @return
     */
    public static PrepareLogService getBean() {
        return logService;
    }
    
    /**
	 * 注册准备日志持久化处理实现
	 * @param logService
	 */
	public static void registerBean(PrepareLogService logService){
		PrepareLogServiceFactory.logService = logService;
	}
}
