package com.dcits.galaxy.dtp.redis.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dcits.galaxy.cache.base.ByteHashCache;
import com.dcits.galaxy.cache.base.CKey;
import com.dcits.galaxy.cache.base.SetCache;
import com.dcits.galaxy.dtp.exception.DTPException;
import com.dcits.galaxy.dtp.log.LogStatus;
import com.dcits.galaxy.dtp.log.PrepareLog;
import com.dcits.galaxy.dtp.redis.util.CKeyUtil;
import com.dcits.galaxy.dtp.redis.util.TransactionLogUtil;

/**
 * 准备事务日志数据库访问类
 * 
 * @author fan.kaijie
 */
public class PrepareLogDao {

	private static PrepareLogDao prepareLogDao = null;
	
	/**
	 * 持久化准备事务日志
	 * 
	 * @param prapareLog
	 * @throws Exception
	 */
	public void save(PrepareLog prapareLog) throws Exception {

		CKey logIdKey = CKeyUtil.createCKey("prepareLog", "logId",
				prapareLog.getLogId());
		CKey txidKey = CKeyUtil.createIndexCKey("prepareLog", "txid",
				prapareLog.getTxid());

		if(ByteHashCache.isExistCKey(logIdKey)) {
			throw new DTPException("logId已存在，请不要重复插入");
		}
		
		SetCache.create(txidKey, prapareLog.getLogId());
		ByteHashCache.addMap(logIdKey, TransactionLogUtil.logToMap(prapareLog),
				ByteHashCache.FOREVER_EXPIRE);
	}

	/**
	 * 更新准备事务日志状态
	 * 
	 * @param logId
	 *            事务日志标识
	 * @param logStatus
	 *            日志床头
	 * @throws Exception
	 */
	public void updateStatus(String logId, LogStatus logStatus)
			throws Exception {

		CKey cKey = CKeyUtil.createCKey("prepareLog", "logId", logId);
		if (ByteHashCache.isExistCKey(cKey)) {
			ByteHashCache.addKV(cKey, "logStatus", logStatus);
		}
	}

	/**
	 * 获取指定的准备事务日志
	 * 
	 * @param logId
	 * @return
	 * @throws Exception
	 */
	public PrepareLog getPrepareLog(String logId) throws Exception {
		CKey cKey = CKeyUtil.createCKey("prepareLog", "logId", logId);
		return getPrepareLog(cKey);
	}

	/**
	 * 获取指定的分支事务的所有准备事务日志
	 * 
	 * @param bxid
	 * @return
	 * @throws Exception
	 */
	public List<PrepareLog> getLogsByTxid(String txid) throws Exception {
		List<PrepareLog> prepareLogs = new ArrayList<PrepareLog>();

		CKey txidKey = CKeyUtil.createIndexCKey("prepareLog", "txid", txid);
		CKey logidKey = CKeyUtil.createCKey("prepareLog", "logId", "");

		Set<String> logidSet = SetCache.getValue(txidKey);
		
		for (String logid : logidSet) {
			logidKey.setKey("value", logid);
			PrepareLog prepareLog = getPrepareLog(logidKey);
			if (prepareLog != null) {
				prepareLogs.add(prepareLog);
			}
		}

		return prepareLogs;
	}

	/**
	 * 获取指定的分支事务的所有准备事务日志
	 * 
	 * @param bxid
	 * @return
	 * @throws Exception
	 */
	public List<PrepareLog> getLogsByTxid(String txid, LogStatus logStatus)
			throws Exception {
		List<PrepareLog> prepareLogs = new ArrayList<PrepareLog>();

		CKey txidKey = CKeyUtil.createIndexCKey("prepareLog", "txid", txid);
		CKey logidKey = CKeyUtil.createCKey("prepareLog", "logId", "");

		Set<String> logidSet = SetCache.getValue(txidKey);
		
		for (String logid : logidSet) {
			logidKey.setKey("value", logid);
			PrepareLog prepareLog = getPrepareLog(logidKey);
			if (prepareLog != null && prepareLog.getLogStatus().equals(logStatus)) {
					prepareLogs.add(prepareLog);
			}
		}

		return prepareLogs;
	}

	private PrepareLog getPrepareLog(CKey logIdKey) throws Exception {
		PrepareLog prepareLog = null;
		Map<String, Object> map = ByteHashCache.getMap(logIdKey);
		if (!map.isEmpty()) {
			prepareLog = TransactionLogUtil.mapToPrepareLog(map);
		}
		return prepareLog;
	}
	
	public void cleanByTxid(String txid) throws Exception {

		CKey txidKey = CKeyUtil.createIndexCKey("prepareLog", "txid", txid);
		CKey logidKey = CKeyUtil.createCKey("prepareLog", "logId", "");
		
		Set<String> logidSet = SetCache.getValue(txidKey);
		
		for (String logid : logidSet) {
			logidKey.setKey("value", logid);
			ByteHashCache.deleteCache(logidKey);
		}
		SetCache.deleteCache(txidKey);
	}
	
	private PrepareLogDao() {
	}
	
	public static PrepareLogDao getInstance() {
		if(prepareLogDao == null){
			prepareLogDao = new PrepareLogDao();
		}
		return prepareLogDao;
	}
}
