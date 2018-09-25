package com.dcits.galaxy.dtp.log;

import java.io.Serializable;


/**
 * 事务日志
 * @author Yin.Weicai
 *
 */
public abstract class TransactionLog {
	
	/**
	 * 日志标识
	 */
	private String logId = null;
	
	/**
	 * 分支事务标识
	 */
	private String bxid = null;
	
	/**
	 * 主事务标识
	 */
	private String txid = null;
	
	/**
	 * 一个分支事务可能有多条事务日志，这些事务日志有时需按顺序执行
	 * logIndex就是来记录事务日志的执行顺序。
	 * logIndex设置要求：0 =< logIndex =< 无穷大 ，
	 * -1： 表示无顺序要求,值越小，表示越执行顺序越靠前。
	 * 默认值: -1
	 */
	private int logIndex = -1;
	
	/**
	 * 日志状态
	 */
	private LogStatus logStatus = LogStatus.undo;
	
	/**
	 * 日志内容
	 */
	private Serializable content = null;
	
	
	
	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	/**
	 *  获取分支事务标识
	 * @return
	 */
	public String getBxid(){
		return this.bxid;
	}
	
	/**
	 * 设置分支事务标识
	 * @param logId
	 */
	public void setBxid(String bxid){
		this.bxid = bxid;
	}
	
	/**
	 * 获取日志状态
	 * @return
	 */
	public LogStatus getLogStatus(){
		return this.logStatus;
	}
	
	/**
	 * 设置日志状态
	 * @param logStatus
	 */
	public void setLogStatus(LogStatus logStatus){
		this.logStatus = logStatus;
	}
	
	/**
	 * 设置日志状态
	 * @param logStatus 日志状态枚举值的名字
	 */
	public void setLogStatus(String logStatus){
		this.logStatus = LogStatus.valueOf( logStatus );
	}
	
	/**
	 * 获取主事务标识
	 * @return
	 */
	public String getTxid(){
		return this.txid;
	}
	
	/**
	 * 设置主事务标识
	 * @param txid
	 */
	public void setTxid(String txid){
		this.txid = txid;
	}
	
	/**
	 * 一个分支事务可能有多条事务日志，这些事务日志有时需按顺序执行
	 * logIndex就是来记录事务日志的执行顺序。
	 * logIndex设置要求：0 =< logIndex =< 无穷大 ，
	 * -1： 表示无顺序要求,值越小，表示越执行顺序越靠前。
	 * 默认值: -1
	 * @param logIndex
	 */
	public void setLogIndex(int logIndex){
		this.logIndex = logIndex;
	}
	
	/**
	 * 获取该事务日志的执行顺序
	 * @return
	 */
	public int getLogIndex(){
		return this.logIndex;
	}
	
	/**
	 * 设置日志内容
	 * @param content
	 */
	public void setContent(Serializable content){
		this.content = content;
	}
	
	/**
	 * 获取日志内容
	 * @return
	 */
	public Serializable getContent(){
		return this.content;
	}
	
	/**
	 * 获取日志类型名字
	 * @return
	 */
	public String getLogTypeName(){
		return null;
	}
	
}
