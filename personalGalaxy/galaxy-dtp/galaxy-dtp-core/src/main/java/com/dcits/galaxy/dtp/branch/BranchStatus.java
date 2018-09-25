package com.dcits.galaxy.dtp.branch;


/**
 * 分支事务状态
 */
public enum BranchStatus {

	/**
	 * 准备
	 */
	prepare,
	
	/**
	 * 确认
	 */
	confirm,
	
	/**
	 * 提交完成
	 */
	submit_complete,
	
	/**
	 * 取消阶段
	 */
	cancel,
	
	/**
	 * 无序提交
	 */
	submit_invalid,
	
	/**
	 * 释放完成
	 */
	release_complete
}
