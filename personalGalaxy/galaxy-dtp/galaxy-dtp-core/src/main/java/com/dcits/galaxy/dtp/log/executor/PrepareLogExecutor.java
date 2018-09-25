package com.dcits.galaxy.dtp.log.executor;

import com.dcits.galaxy.dtp.log.PrepareLog;

/**
 * 事务准备日志执行器
 * @author Yin.Weicai
 */
public interface PrepareLogExecutor {
	
	/**
	 * 获取执行器对应的日志类型名
	 * @return
	 */
	public String getLogTypeName();
	
	/**
	 * 执行日志
	 * @param prepareLog
	 * @return
	 */
	public boolean execute(PrepareLog prepareLog);
}
