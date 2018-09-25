package com.dcits.galaxy.dtp.trunk;


/**
 * 事务状态
 */
public enum TrunkStatus {

	/**
	 * 准备阶段
	 */
	prepare,
	
	/**
	 * 确认阶段
	 */
	confirm,
	
	/**
	 * 确认完成
	 */
	confirm_complete,
	
	/**
	 * 取消阶段
	 */
	cancel,
	
	/**
	 * 取消完成
	 */
	cancel_complete
}
