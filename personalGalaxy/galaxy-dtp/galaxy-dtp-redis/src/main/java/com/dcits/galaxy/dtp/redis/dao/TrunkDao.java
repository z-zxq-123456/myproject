package com.dcits.galaxy.dtp.redis.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.cache.base.ByteHashCache;
import com.dcits.galaxy.cache.base.CKey;
import com.dcits.galaxy.cache.base.SetCache;
import com.dcits.galaxy.dtp.branch.BranchStatus;
import com.dcits.galaxy.dtp.branch.BranchTransaction;
import com.dcits.galaxy.dtp.exception.DTPException;
import com.dcits.galaxy.dtp.redis.util.CKeyUtil;
import com.dcits.galaxy.dtp.redis.util.TransactionUtil;
import com.dcits.galaxy.dtp.trunk.TrunkStatus;
import com.dcits.galaxy.dtp.trunk.TrunkTransaction;

/**
 * 主事务访问类
 * @author fan.kaijie
 */
public class TrunkDao {
	
	private long partition = 30 * 60 * 1000;
	
	private static TrunkDao trunkDao = null;
	
	private static Logger logger = LoggerFactory.getLogger(TrunkDao.class);
	
	public long getPartition() {
		return partition;
	}

	public void setPartition(long partition) {
		this.partition = partition;
	}

	public void save(TrunkTransaction trunk) throws Exception{

		String txid = trunk.getTxid();
		String appGroup = trunk.getAppGroup();
		long startTime = System.currentTimeMillis();
		long time = startTime / partition;
		
		trunk.setStartTime(startTime);
		
		CKey txidKey = CKeyUtil.createCKey("trunk", "txid", txid);
		CKey appGroupKey = CKeyUtil.createTrunkIndexCKey(appGroup, time);
		
		if(ByteHashCache.isExistCKey(txidKey)) {
			throw new DTPException("txid已存在，请不要重复插入");
		}
		
		if(!SetCache.isExistCKey(appGroupKey)) {
			CKey cKey = CKeyUtil.createIndexCKey("trunk", "appGroups", appGroup);
			SetCache.create(cKey, Long.toString(time));
		}
		
		SetCache.create(appGroupKey, txid);
		ByteHashCache.addMap(txidKey, TransactionUtil.trunkToMap(trunk), ByteHashCache.FOREVER_EXPIRE);
	}
	
	public void updateStatus(String txid, TrunkStatus status) throws Exception {
		CKey txidKey = CKeyUtil.createCKey("trunk", "txid", txid);
		
		if (ByteHashCache.isExistCKey(txidKey)) {
			ByteHashCache.addKV(txidKey, "status", status);
		}
	}
	
	public TrunkTransaction getTrunk( String txid ) throws Exception {
		CKey cKey = CKeyUtil.createCKey("trunk", "txid", txid);
		return getTrunk(cKey);
	}
	
	private TrunkTransaction getTrunk( CKey txidKey) {
		TrunkTransaction trunk = null;
		Map<String, Object> map = ByteHashCache.getMap(txidKey);
		if(!map.isEmpty()){
			trunk = TransactionUtil.mapToTrunk(map);
		}
		return trunk;
	}
	
	public List<TrunkTransaction> getUnCompletedTrunks(String appGroup, long timeout)
			throws Exception {
		long time = System.currentTimeMillis() - timeout * 1000;

		List<TrunkTransaction> trunks = new ArrayList<>();
		long order = time / partition;
		
		CKey appsKey = CKeyUtil.createIndexCKey("trunk", "appGroups", appGroup);
		CKey appGroupKey = CKeyUtil.createTrunkIndexCKey(appGroup, 0);

		Set<String> groups = SetCache.getValue(appsKey);
		for (String group : groups) {
			
			try {
				if(Long.parseLong(group) > order)
					continue;
			} catch (Exception e) {
				logger.warn(appGroupKey.createCkey() + " is illegal format");
				continue;
			}
			
			appGroupKey.setKey("suffix", group);
			Set<String> txidSet = SetCache.getValue(appGroupKey);
			
			if(txidSet == null || txidSet.isEmpty()){
				continue;
			}
			
			for (String txid : txidSet) {
				TrunkTransaction trunk = getTrunk(txid);
				if (trunk != null && isUnCompleted(trunk.getStatus()) && trunk.getStartTime() < time)
					trunks.add(trunk);
			}
		}
		
		return trunks;
	}
	
	private boolean isUnCompleted(TrunkStatus status){
		boolean result = false;
		if (TransactionUtil.isUnCompletedTrunk(status) || TrunkStatus.prepare == status) {
			result = true;
		}
		return result;
	}
	
	public boolean hasUnCompletedBranches(String txid) throws Exception {

		CKey cKey = CKeyUtil.createIndexCKey("branch", "txid", txid);
		Set<String> bxidSet = SetCache.getValue(cKey);
		
		if(bxidSet != null && (!bxidSet.isEmpty())) {
			BranchTransaction branch = null;
			CKey bxidKey = CKeyUtil.createCKey("branch", "bxid", "");
			
			for(String bxid : bxidSet){
				bxidKey.setKey("value", bxid);
				branch = TransactionUtil.mapToBranch(ByteHashCache.getMap(bxidKey));
				
				if(branch != null) {
					BranchStatus status = branch.getStatus();
					
					if(branch.getParentBxid().equals("unknow") && (
							status != BranchStatus.submit_complete
							|| status != BranchStatus.submit_invalid
							|| status != BranchStatus.release_complete)) {
						return true;
					}
					
					branch = null;
				}
			}
		}
		return false;
	}
	
	public boolean setConfirm(String txid) throws Exception {
		boolean result = false;

		CKey txidKey = CKeyUtil.createCKey("trunk", "txid", txid);
		TrunkStatus oldStatus = ByteHashCache.getDataValue(txidKey, "status");
		if(TrunkStatus.prepare.equals(oldStatus) || TrunkStatus.confirm.equals(oldStatus)){
			updateStatus(txid, TrunkStatus.confirm);
			result = true;
		}
		return result;
	}
	
	public boolean setConfirmCompleted(String txid) throws Exception {
		boolean result = false;

		CKey txidKey = CKeyUtil.createCKey("trunk", "txid", txid);
		TrunkStatus oldStatus = ByteHashCache.getDataValue(txidKey, "status");
		if(TrunkStatus.confirm.equals(oldStatus) || TrunkStatus.confirm_complete.equals(oldStatus)){
			updateStatus(txid, TrunkStatus.confirm_complete);
			result = true;
		}
		return result;
	}
	
	public boolean setCancel(String txid) throws Exception {
		boolean result = false;

		CKey txidKey = CKeyUtil.createCKey("trunk", "txid", txid);
		TrunkStatus oldStatus = ByteHashCache.getDataValue(txidKey, "status");
		if(TrunkStatus.prepare.equals(oldStatus) || TrunkStatus.cancel.equals(oldStatus)){
			updateStatus(txid, TrunkStatus.cancel);
			result = true;
		}
		return result;
	}
	
	public boolean setCancelCompleted(String txid) throws Exception {
		boolean result = false;

		CKey txidKey = CKeyUtil.createCKey("trunk", "txid", txid);
		TrunkStatus oldStatus = ByteHashCache.getDataValue(txidKey, "status");
		if(TrunkStatus.cancel_complete.equals(oldStatus) || TrunkStatus.cancel.equals(oldStatus)){
			updateStatus(txid, TrunkStatus.cancel_complete);
			result = true;
		}
		return result;
	}
	
	public void delete(TrunkTransaction trunk) throws Exception {
		CKey txidKey = CKeyUtil.createCKey("trunk","txid", trunk.getTxid());
		ByteHashCache.deleteCache(txidKey);
		
		String appGroup = trunk.getAppGroup();
		long time = trunk.getStartTime() / partition;
		
		CKey cKey = CKeyUtil.createTrunkIndexCKey(appGroup, time);
		SetCache.deleteCacheValue(cKey, trunk.getTxid());
		if(!SetCache.isExistCKey(cKey)){
			CKey appsKey = CKeyUtil.createIndexCKey("trunk", "appGroups", appGroup);
			SetCache.deleteCacheValue(appsKey, Long.toString(time));
		}
	}
	
	public Set<TrunkTransaction> getComplete(String appGroup, long time) throws Exception {
		
		Set<TrunkTransaction> trunkSet = new HashSet<>();
		
		long newOrder = System.currentTimeMillis()/partition - 2;
		long order = time / partition;
		
		order = order < newOrder ? order : newOrder;
		
		CKey appsKey = CKeyUtil.createIndexCKey("trunk", "appGroups", appGroup);
		CKey appGroupKey = CKeyUtil.createTrunkIndexCKey(appGroup, 0);

		Set<String> groups = SetCache.getValue(appsKey);
		for (String group : groups) {
			
			try {
				if(Long.parseLong(group) > order)
					continue;
			} catch (Exception e) {
				logger.warn(appGroupKey.createCkey() + " is illegal format");
				continue;
			}
			
			appGroupKey.setKey("suffix", group);
			Set<String> txidSet = SetCache.getValue(appGroupKey);
			
			if(txidSet == null || txidSet.isEmpty()){
				SetCache.deleteCacheValue(appsKey, group);
				continue;
			}
			
			for (String txid : txidSet) {
				TrunkTransaction trunk = getTrunk(txid);
				if (trunk != null) {
					if (TransactionUtil.isCompleted(trunk)) {
						trunkSet.add(trunk);
					}
				} else {
					SetCache.deleteCacheValue(appGroupKey, txid);
					if (!SetCache.isExistCKey(appGroupKey))
						SetCache.deleteCacheValue(appsKey, group);
				}
			}
		}
		
		return trunkSet;
	}

	private TrunkDao() {
	}
	
	
	public static TrunkDao getInstance(){
		
		if(trunkDao == null){
			trunkDao = new TrunkDao();
		}
		
		return trunkDao;
	}
}
