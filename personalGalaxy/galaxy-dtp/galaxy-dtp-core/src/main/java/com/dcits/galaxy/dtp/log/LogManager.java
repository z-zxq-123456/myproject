package com.dcits.galaxy.dtp.log;

/**
 * 事务日志管理器
 * @author Yin.Weicai
 *
 */
public interface LogManager {

	/**
	 * 写准备操作日志
	 * @param prepareLog
	 * @return
	 */
	public boolean writePrapareLog(PrepareLog prepareLog);
	
	/**
	 * 记录提交操作信息
	 * @param submitLog
	 * @return
	 */
	public boolean writeSubmitLog(SubmitLog submitLog);
	
}
