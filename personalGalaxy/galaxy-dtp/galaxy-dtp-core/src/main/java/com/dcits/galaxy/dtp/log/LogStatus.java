package com.dcits.galaxy.dtp.log;


/**
 * 事务日志状态
 * @author Yin.Weicai
 */
public enum LogStatus {

	/**
	 * 未处理
	 */
	undo,
	
	/**
	 * 已处理
	 */
	done
}
