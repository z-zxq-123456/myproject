package com.dcits.galaxy.dtp.db.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dtp.trunk.TrunkStatus;
import com.dcits.galaxy.dtp.trunk.TrunkTransaction;

/**
 * 主事务数据库访问类
 * @author Yin.Weicai
 */
@SuppressWarnings("unchecked")
public class TrunkDao {
	@Resource
	private ShardSqlSessionTemplate shardSqlSessionTemplate;

	private final String nameSpace = "com.dcits.galaxy.dtp.trunk.TrunkTransaction";

	public void save(TrunkTransaction trunk) {
		trunk.setStartTime(System.currentTimeMillis());
		shardSqlSessionTemplate.insert(nameSpace, "save", trunk);

	}

	public void updateStatus(String txid, TrunkStatus status) {
		TrunkTransaction t = new TrunkTransaction();
		t.setTxid(txid);
		t.setStatus(status);
		shardSqlSessionTemplate.update(nameSpace, "updateStatus", t);
	}

	public TrunkTransaction getTrunk(String txid) {
		TrunkTransaction t = new TrunkTransaction();
		t.setTxid(txid);
		return (TrunkTransaction) shardSqlSessionTemplate.selectOne(nameSpace, "getTrunk", t);
	}

	public List<TrunkTransaction> getAll(String appGroup, TrunkStatus status) {
		TrunkTransaction t = new TrunkTransaction();
		t.setAppGroup(appGroup);
		t.setStatus(status);
		return shardSqlSessionTemplate.selectList(nameSpace, "getAll", t);
	}

	public List<TrunkTransaction> getAllByAppGroup(String appGroup) {
		TrunkTransaction t = new TrunkTransaction();
		t.setAppGroup(appGroup);
		return shardSqlSessionTemplate.selectList(nameSpace, "getAllByAppGroup", t);
	}

	public List<TrunkTransaction> getUnCompletedTrunks(String appGroup, long timeout) {
		long time = System.currentTimeMillis() - timeout * 1000;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appGroup", appGroup);
		map.put("time", time);
		return shardSqlSessionTemplate.selectList(nameSpace, "getUnCompletedTrunks", map);
	}

	public boolean hasUnCompletedBranches(String txid) {
		boolean result = false;
		TrunkTransaction t = new TrunkTransaction();
		t.setTxid(txid);
		int c = (int) shardSqlSessionTemplate.selectOne(nameSpace, "hasUnCompletedBranches", t);
		if (c > 0) {
			result = true;
		}
		return result;
	}

	public boolean setConfirm(String txid) throws Exception {
		boolean result = false;
		TrunkTransaction t = new TrunkTransaction();
		t.setTxid(txid);
		int r = shardSqlSessionTemplate.update(nameSpace, "setConfirm", t);
		if (r > 0) {
			result = true;
		}
		return result;
	}

	public boolean setConfirmCompleted(String txid) {
		boolean result = false;
		TrunkTransaction t = new TrunkTransaction();
		t.setTxid(txid);
		int r = shardSqlSessionTemplate.update(nameSpace, "setConfirmCompleted", t);
		if (r > 0) {
			result = true;
		}
		return result;
	}

	public boolean setCancel(String txid) {
		boolean result = false;
		TrunkTransaction t = new TrunkTransaction();
		t.setTxid(txid);
		int r = shardSqlSessionTemplate.update(nameSpace, "setCancel", t);
		if (r > 0) {
			result = true;
		}
		return result;
	}

	public boolean setCancelCompleted(String txid) throws Exception {
		boolean result = false;
		TrunkTransaction t = new TrunkTransaction();
		t.setTxid(txid);
		int r = shardSqlSessionTemplate.update(nameSpace, "setCancelCompleted", t);
		if (r > 0) {
			result = true;
		}
		return result;
	}

	public void clean(String appGroup, long time) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appGroup", appGroup);
		map.put("time", time);
		shardSqlSessionTemplate.delete(nameSpace, "clean", map);
	}

}
