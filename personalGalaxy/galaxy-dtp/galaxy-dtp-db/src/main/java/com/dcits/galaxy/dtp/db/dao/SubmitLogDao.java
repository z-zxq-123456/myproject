package com.dcits.galaxy.dtp.db.dao;

import java.util.List;

import javax.annotation.Resource;

import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dtp.defaults.log.DefaultSubmitLog;
import com.dcits.galaxy.dtp.log.LogStatus;
import com.dcits.galaxy.dtp.log.SubmitLog;

/**
 * 提交事务日志数据库访问类
 * @author Yin.Weicai
 */
@SuppressWarnings("unchecked")
public class SubmitLogDao {
	@Resource
	private ShardSqlSessionTemplate shardSqlSessionTemplate;

	private final String nameSpace = "com.dcits.galaxy.dtp.log.SubmitLog";

	/**
	 * 持久化提交事务日志
	 * @param submitLog
	 */
	public void save(SubmitLog submitLog) {
		shardSqlSessionTemplate.insert(nameSpace, "save", submitLog);
	}

	/**
	 * 更新指定提交事务日志的状态
	 * @param logId
	 * @param logStatus
	 */
	public void updateStatus(String logId, LogStatus logStatus) {
		SubmitLog submitLog = new DefaultSubmitLog();
		submitLog.setLogId(logId);
		submitLog.setLogStatus(logStatus);
		shardSqlSessionTemplate.update(nameSpace, "updateStatus", submitLog);
	}

	/**
	 * 获取指定的提交事务日志
	 * @param logId
	 * @return
	 */
	public SubmitLog getSubmitInfo(String logId) {
		SubmitLog submitLog = new DefaultSubmitLog();
		submitLog.setLogId(logId);
		return (SubmitLog) shardSqlSessionTemplate.selectOne(nameSpace, "getSubmitInfo", submitLog);
	}

	/**
	 * 获取指定分支事务的所有提交事务日志
	 * @param bxid
	 * @return
	 */
	public List<SubmitLog> getLogsByBxid(String bxid) {
		SubmitLog submitLog = new DefaultSubmitLog();
		submitLog.setBxid(bxid);
		return shardSqlSessionTemplate.selectList(nameSpace, "getLogsByBxid", submitLog);
	}

	/**
	 * 获取指定的分支事务的指定状态的所有提交事务日志
	 * @param bxid
	 * @param logStatus
	 * @return
	 */
	public List<SubmitLog> getLogsByBxid(String bxid, LogStatus logStatus) {
		SubmitLog submitLog = new DefaultSubmitLog();
		submitLog.setBxid(bxid);
		submitLog.setLogStatus(logStatus);
		return shardSqlSessionTemplate.selectList(nameSpace, "getLogsByBxidStatus", submitLog);
	}
}
