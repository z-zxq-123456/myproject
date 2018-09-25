package com.dcits.galaxy.dtp.redis.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.dcits.galaxy.dtp.defaults.log.DefaultPrepareLog;
import com.dcits.galaxy.dtp.defaults.log.DefaultSubmitLog;
import com.dcits.galaxy.dtp.log.LogStatus;
import com.dcits.galaxy.dtp.log.PrepareLog;
import com.dcits.galaxy.dtp.log.SubmitLog;
import com.dcits.galaxy.dtp.log.TransactionLog;

public class TransactionLogUtil {

	public static Map<String, Object> logToMap(TransactionLog log) {
		Map<String, Object> map = new HashMap<>();

		map.put("logId", log.getLogId());
		map.put("bxid", log.getBxid());
		map.put("txid", log.getTxid());
		map.put("logIndex", log.getLogIndex());
		map.put("content", log.getContent());
		map.put("logStatus", log.getLogStatus());

		return map;
	}

	public static SubmitLog mapToSubmitLog(Map<String, Object> map) {
		SubmitLog log = new DefaultSubmitLog();
		mapToLog(map, log);
		return log;
	}

	public static PrepareLog mapToPrepareLog(Map<String, Object> map) {
		PrepareLog log = new DefaultPrepareLog();
		mapToLog(map, log);
		return log;
	}
	
	private static void mapToLog(Map<String, Object> map, TransactionLog log){
		if (map.containsKey("logId"))
			log.setLogId((String) map.get("logId"));

		if (map.containsKey("bxid"))
			log.setBxid((String) map.get("bxid"));

		if (map.containsKey("txid"))
			log.setTxid((String) map.get("txid"));

		if (map.containsKey("logStatus"))
			log.setLogStatus((LogStatus) map.get("logStatus"));

		if (map.containsKey("content"))
			log.setContent((Serializable) map.get("content"));

		if (map.containsKey("logIndex"))
			log.setLogIndex((int) map.get("logIndex"));
	}

	private TransactionLogUtil() {

	}
}
