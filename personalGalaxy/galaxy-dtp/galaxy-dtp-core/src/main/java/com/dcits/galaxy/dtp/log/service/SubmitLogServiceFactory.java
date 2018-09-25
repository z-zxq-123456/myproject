package com.dcits.galaxy.dtp.log.service;


/**
 * 准备日志执行器工厂类
 *
 * @author Yin.Weicai
 */
public class SubmitLogServiceFactory {

	private static SubmitLogService logService = null;

    public static SubmitLogService getBean() {
        return logService;
    }
    
    /**
	 * 注册提交日志持久化处理实现
	 * @param logService
	 */
	public static void registerBean(SubmitLogService logService){
		SubmitLogServiceFactory.logService = logService;
	}
}
