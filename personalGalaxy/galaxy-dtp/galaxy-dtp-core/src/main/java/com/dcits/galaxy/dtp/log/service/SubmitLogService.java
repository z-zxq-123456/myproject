package com.dcits.galaxy.dtp.log.service;

import com.dcits.galaxy.dtp.log.LogStatus;
import com.dcits.galaxy.dtp.log.SubmitLog;

import java.io.Serializable;
import java.util.List;

/**
 * 提交日志服务
 * @author Yin.Weicai
 */
public interface SubmitLogService extends Serializable {
	
	/**
	 * 获取提交日志服务名。该接口的每个实现类有一个全局唯一的ServiceName
	 * @return
	 */
	public String getServiceName();

	/**
	 * 保存事务日志
	 * @return
	 */
	public boolean saveLog(SubmitLog submitLog);
	
	
	/**
	 * 更新事务日志状态
	 * @param logId
	 * @param logStatus
	 * @return
	 */
	public boolean updateLogStatus(String logId, LogStatus logStatus);
	
	
	/**
	 * 根据事务标识获取未处理的提交事务日志
	 * @param bxid 分支事务标识
	 * @return
	 */
	public List<SubmitLog> getUndoLogsByBxid(String bxid);
	
	/**
	 * 根据应用名获取未处理的提交事务日志
	 * @param appName 应用名
	 * @return
	 */
	public List<SubmitLog> getUndoLogsByAppName(String appName);
	
	/**
	 * 根据分日志标识获取一个特定的事务日志
	 * @param logId
	 * @return
	 */
	public SubmitLog getSubmitLog(String logId);
	
}
