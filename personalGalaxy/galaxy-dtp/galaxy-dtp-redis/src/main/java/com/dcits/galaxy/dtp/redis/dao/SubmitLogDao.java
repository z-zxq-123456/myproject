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
import com.dcits.galaxy.dtp.log.SubmitLog;
import com.dcits.galaxy.dtp.redis.util.CKeyUtil;
import com.dcits.galaxy.dtp.redis.util.TransactionLogUtil;

/**
 * 提交事务日志访问类
 * 
 * @author fan.kaijie
 *
 */
public class SubmitLogDao {

	private static SubmitLogDao submitLogDao = null;

	/**
	 * 持久化提交事务日志
	 * 
	 * @param submitLog
	 * @throws Exception
	 */
	public void save(SubmitLog submitLog) throws Exception {

		CKey logIdKey = CKeyUtil.createCKey("submitLog", "logId",
				submitLog.getLogId());
		CKey bxidKey = CKeyUtil.createIndexCKey("submitLog", "bxid",
				submitLog.getBxid());

		if (ByteHashCache.isExistCKey(logIdKey)) {
			throw new DTPException("logId已存在，请不要重复插入");
		}

		SetCache.create(bxidKey, submitLog.getLogId());

		ByteHashCache.addMap(logIdKey, TransactionLogUtil.logToMap(submitLog),
				ByteHashCache.FOREVER_EXPIRE);
	}

	/**
	 * 更新指定提交事务日志的状态
	 * 
	 * @param logId
	 * @param logStatus
	 * @throws Exception
	 */
	public void updateStatus(String logId, LogStatus logStatus)
			throws Exception {

		CKey cKey = CKeyUtil.createCKey("submitLog", "logId", logId);
		if (ByteHashCache.isExistCKey(cKey)) {
			ByteHashCache.addKV(cKey, "logStatus", logStatus);
		}
	}

	/**
	 * 获取指定的提交事务日志
	 * 
	 * @param logId
	 * @return
	 * @throws Exception
	 */
	public SubmitLog getSubmitInfo(String logId) throws Exception {
		CKey cKey = CKeyUtil.createCKey("submitLog", "logId", logId);
		return getSubmitInfo(cKey);
	}

	/**
	 * 获取指定分支事务的所有提交事务日志
	 * 
	 * @param bxid
	 * @return
	 * @throws Exception
	 */
	public List<SubmitLog> getLogsByBxid(String bxid) throws Exception {

		List<SubmitLog> submitInfos = new ArrayList<SubmitLog>();

		CKey bxidKey = CKeyUtil.createIndexCKey("submitLog", "bxid", bxid);
		CKey logidKey = CKeyUtil.createCKey("submitLog", "logId", "");

		Set<String> logidSet = SetCache.getValue(bxidKey);

		for (String logid : logidSet) {
			logidKey.setKey("value", logid);
			SubmitLog submitLog = getSubmitInfo(logidKey);
			if (submitLog != null) {
				submitInfos.add(submitLog);
			}
		}
		return submitInfos;
	}

	/**
	 * 获取指定的分支事务的指定状态的所有提交事务日志
	 * 
	 * @param bxid
	 * @param logStatus
	 * @return
	 * @throws Exception
	 */
	public List<SubmitLog> getLogsByBxid(String bxid, LogStatus logStatus)
			throws Exception {
		List<SubmitLog> submitInfos = new ArrayList<SubmitLog>();

		CKey bxidKey = CKeyUtil.createIndexCKey("submitLog", "bxid", bxid);

		Set<String> logidSet = SetCache.getValue(bxidKey);

		// for (String logid : logidSet) {
		// logidKey.setKey("value", logid);
		// if (ByteHashCache.isExistCKey(logidKey)) {
		// String status = (String) ByteHashCache.getDataValue(logidKey,
		// "logStatus");
		//
		// if (status.equals(logStatus.name())) {
		// SubmitLog submitLog = TransactionLogUtil
		// .map2SubmitLog(ByteHashCache.getMap(logidKey));
		// submitInfos.add(submitLog);
		// }
		// } else {
		// SetCache.deleteCacheValue(bxidKey, logid);
		// }
		// }
		CKey logidKey = CKeyUtil.createCKey("submitLog", "logId", "");

		for (String logid : logidSet) {
			logidKey.setKey("value", logid);
			SubmitLog submitLog = getSubmitInfo(logidKey);
			if (submitLog != null && submitLog.getLogStatus().equals(logStatus)) {
				submitInfos.add(submitLog);
			}
		}

		return submitInfos;
	}

	private SubmitLog getSubmitInfo(CKey logIdKey) throws Exception {
		SubmitLog submitInfo = null;
		Map<String, Object> map = ByteHashCache.getMap(logIdKey);
		if (!map.isEmpty()) {
			submitInfo = TransactionLogUtil.mapToSubmitLog(map);
		}
		return submitInfo;
	}

	public void cleanByBxid(String bxid) throws Exception {

		CKey bxidKey = CKeyUtil.createIndexCKey("submitLog", "bxid", bxid);
		CKey logidKey = CKeyUtil.createCKey("submitLog", "logId", "");

		Set<String> logidSet = SetCache.getValue(bxidKey);

		for (String logid : logidSet) {
			logidKey.setKey("value", logid);
			ByteHashCache.deleteCache(logidKey);
		}
		SetCache.deleteCache(bxidKey);
	}

	public static SubmitLogDao getInstance() {
		if (submitLogDao == null) {
			submitLogDao = new SubmitLogDao();
		}
		return submitLogDao;
	}

	private SubmitLogDao() {

	}
}
