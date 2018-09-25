package com.dcits.galaxy.dtp.log.executor;

import com.dcits.galaxy.dtp.log.SubmitLog;

/**
 * 提交日志执行器
 * @author Yin.Weicai
 */
public interface SubmitLogExecutor {
	
	/**
	 * 获取执行器对应的日志类型名
	 * @return
	 */
	public String getLogTypeName();
	
	/**
	 * 执行日志
	 * @param submitLog
	 * @return
	 */
	public boolean execute(SubmitLog submitLog);
}
