package com.dcits.galaxy.dtp.db.dao;

import java.util.List;

import javax.annotation.Resource;

import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dtp.defaults.log.DefaultPrepareLog;
import com.dcits.galaxy.dtp.log.LogStatus;
import com.dcits.galaxy.dtp.log.PrepareLog;

/**
 * 准备事务日志数据库访问类
 * @author Yin.Weicai
 */
@SuppressWarnings("unchecked")
public class PrepareLogDao {
	@Resource
	private ShardSqlSessionTemplate shardSqlSessionTemplate;

	private final String nameSpace = "com.dcits.galaxy.dtp.log.PrepareLog";

	/**
	 * 持久化准备事务日志
	 * @param prapareLog
	 */
	public void save(PrepareLog prepareLog) {
		shardSqlSessionTemplate.insert(nameSpace, "save", prepareLog);
	}

	/**
	 * 更新准备事务日志状态
	 * @param logId 事务日志标识
	 * @param logStatus 日志状态
	 */
	public void updateStatus(String logId, LogStatus logStatus) {
		PrepareLog pl = new DefaultPrepareLog();
		pl.setLogId(logId);
		pl.setLogStatus(logStatus);
		shardSqlSessionTemplate.update(nameSpace, "updateStatus", pl);
	}

	/**
	 * 获取指定的准备事务日志
	 * @param logId
	 * @return
	 */
	public PrepareLog getPrepareLog(String logId) {
		PrepareLog pl = new DefaultPrepareLog();
		pl.setLogId(logId);
		return (PrepareLog) shardSqlSessionTemplate.selectOne(nameSpace, "getPrepareLog", pl);
	}

	/**
	 * 获取指定的分支事务的所有准备事务日志
	 * @param bxid
	 * @return
	 */
	public List<PrepareLog> getLogsByTxid(String txid) {
		PrepareLog pl = new DefaultPrepareLog();
		pl.setTxid(txid);
		return shardSqlSessionTemplate.selectList(nameSpace, "getLogsByTxid", pl);
	}

	/**
	 * 获取指定的分支事务的所有准备事务日志
	 * @param bxid
	 * @return
	 */
	public List<PrepareLog> getLogsByTxid(String txid, LogStatus logStatus) {
		PrepareLog pl = new DefaultPrepareLog();
		pl.setTxid(txid);
		pl.setLogStatus(logStatus);
		return shardSqlSessionTemplate.selectList(nameSpace, "getLogsByTxidStatus", pl);
	}
}
