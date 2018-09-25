package com.dcits.galaxy.dtp.redis.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dcits.galaxy.cache.base.ByteHashCache;
import com.dcits.galaxy.cache.base.CKey;
import com.dcits.galaxy.cache.base.SetCache;
import com.dcits.galaxy.cache.base.ZSetCache;
import com.dcits.galaxy.dtp.branch.BranchStatus;
import com.dcits.galaxy.dtp.branch.BranchTransaction;
import com.dcits.galaxy.dtp.exception.DTPException;
import com.dcits.galaxy.dtp.redis.util.CKeyUtil;
import com.dcits.galaxy.dtp.redis.util.TransactionUtil;
import com.dcits.galaxy.dtp.trunk.TrunkStatus;

/**
 * 分支事务访问类
 * @author fan.kaijie
 */
public class BranchDao {
	
	private static BranchDao branchDao = null;
	
	/**
	 * 持久化分支事务
	 * @param branch
	 * @throws Exception
	 */
	public void save(BranchTransaction branch) throws Exception {
		String bxid = branch.getBxid();
		String txid = branch.getTxid();
		String parentBxid = branch.getParentBxid();
		
		CKey bxidKey = CKeyUtil.createCKey("branch", "bxid", bxid);
		CKey txidKey = CKeyUtil.createIndexCKey("branch", "txid", txid);
		CKey parentBxidKey = CKeyUtil.createIndexCKey("branch", "parentBxid", parentBxid);
		
		if(ByteHashCache.isExistCKey(bxidKey)) {
			throw new DTPException("bxid已存在，请不要重复插入");
		}
		
		SetCache.create(txidKey, bxid);
		SetCache.create(parentBxidKey, bxid);
		ByteHashCache.addMap(bxidKey, TransactionUtil.branchToMap(branch), ByteHashCache.FOREVER_EXPIRE);
	}
	
	/**
	 * 更新指定的分支事务的状态
	 * @param bxid
	 * @param status
	 * @throws Exception
	 */
	public void updateStatus(String bxid, BranchStatus status) throws Exception {
		CKey bxidKey = CKeyUtil.createCKey("branch", "bxid", bxid);
		if(ByteHashCache.isExistCKey(bxidKey)){
			ByteHashCache.addKV(bxidKey, "status", status);
		}
	}
	
	/**
	 * 获取指定的分支事务
	 * @param bxid
	 * @return
	 * @throws Exception
	 */
	public BranchTransaction getBranch( String bxid ) throws Exception {
		CKey bxidKey = CKeyUtil.createCKey("branch", "bxid", bxid);
		return getBranch(bxidKey);
	}
	
	private BranchTransaction getBranch( CKey cKey ) throws Exception {
		BranchTransaction branch = null;
		Map<String, Object> map = ByteHashCache.getMap(cKey);
		
		if(!map.isEmpty()){
			branch = TransactionUtil.mapToBranch(map);
		}
		
		return branch;
	}
	
	/**
	 * 获取指定的主事务的指定状态的分支事务
	 * @param txid
	 * @return
	 * @throws Exception
	 */
	public List<BranchTransaction> getAllByTxid( String txid ) throws Exception {
		List<BranchTransaction> branches = new ArrayList<BranchTransaction>();
		CKey txidKey = CKeyUtil.createIndexCKey("branch", "txid", txid);
		Set<String> bxidSet = SetCache.getValue(txidKey);
		
		if(bxidSet == null || bxidSet.isEmpty()){
			return branches;
		}
		
		CKey bxidKey = CKeyUtil.createCKey("branch", "bxid", "");
		for(String bxid : bxidSet){
			bxidKey.setKey("value", bxid);
			BranchTransaction branch = getBranch(bxidKey);
			if (branch != null) {
				branches.add(branch);
			}
		}
		return branches;
	}
	
	public Set<String> getBxids(String txid) throws Exception {
		CKey txidKey = CKeyUtil.createIndexCKey("branch", "txid", txid);
		Set<String> bxidSet = SetCache.getValue(txidKey);
		return bxidSet;
	}
	
	/**
	 * 获取指定分支事务的嵌套的指定状态的所有分支事务
	 * @param bxid
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<BranchTransaction> getAllByBxid( String parentBxid ) throws Exception {

		List<BranchTransaction> branches = new ArrayList<BranchTransaction>();
		CKey parentBxidKey = CKeyUtil.createIndexCKey("branch", "parentBxid", parentBxid);
		Set<String> bxidSet = SetCache.getValue(parentBxidKey);
		
		CKey bxidKey = CKeyUtil.createCKey("branch", "bxid", "");
		for(String bxid : bxidSet){
			bxidKey.setKey("value", bxid);
			BranchTransaction branch = getBranch(bxidKey);
			if (branch != null) {
				branches.add(branch);
			}
		}
		return branches;
	}
	
	public List<BranchTransaction> getUnCompletedDisorderByAppGroup(String appGroup) throws Exception {
		List<BranchTransaction> branches = new ArrayList<BranchTransaction>();

		CKey appGroupKey = CKeyUtil.createIndexCKey("trunk", "appGroup",appGroup);
		Set<String> txidSet = ZSetCache.getValue(appGroupKey);

		CKey trunkTxidKey = CKeyUtil.createCKey("trunk", "txid", "");
		for (String txid : txidSet) {
			trunkTxidKey.setKey("value", txid);
			TrunkStatus trunkStatus = (TrunkStatus) ByteHashCache.getDataValue(trunkTxidKey, "status");
			if (TransactionUtil.isUnCompletedTrunk(trunkStatus)) {
				List<BranchTransaction> tmpBranches = getAllByTxid(txid);
				for (BranchTransaction branch : tmpBranches) {
					if (branch.getParentBxid().equals("unknow") && branch.getIndexInTrunk() < 0) {
						branches.add(branch);
					}
				}
			}
		}

		return branches;
	}
		
	public List<BranchTransaction> getNeedSubmitByTxid(String txid) throws Exception {
		List<BranchTransaction> branches = new ArrayList<BranchTransaction>();

		List<BranchTransaction> tmpBranches = getAllByTxid(txid);
		
		Map<String, BranchTransaction> branchMap = new HashMap<>();
		for(BranchTransaction branch : tmpBranches) {
			branchMap.put(branch.getBxid(), branch);
		}

		CKey bxidKey = CKeyUtil.createIndexCKey("submitLog", "bxid","");
		for(BranchTransaction branch : tmpBranches){
			String bxid = branch.getBxid();
			BranchStatus status = branch.getStatus();
			
			if("unknow".equals(bxid) && isNeedSubmit(status)) {
				bxidKey.setKey("value", branch.getBxid());
				if(SetCache.isExistCKey(bxidKey))
					branches.add(branch);
			}
		}
		
		return branches;
	}
	
	public List<BranchTransaction> getNeedSubmitByBxid(String bxid) throws Exception {
		List<BranchTransaction> branches = new ArrayList<BranchTransaction>();

		List<BranchTransaction> tmpBranches = getAllByBxid(bxid);

		CKey bxidKey = CKeyUtil.createIndexCKey("submitLog", "bxid","");
		for(BranchTransaction branch : tmpBranches){
			BranchStatus status = branch.getStatus();
			if (isNeedSubmit(status)) {
				bxidKey.setKey("value", branch.getBxid());
				if(SetCache.isExistCKey(bxidKey))
					branches.add(branch);
			}
		}
		
		return branches;
	}

	public void delete(String bxid) throws Exception {
		
		CKey bxidKey = CKeyUtil.createCKey("branch", "bxid", bxid);
		CKey bxidIndexKey = CKeyUtil.createIndexCKey("branch", "parentBxid", bxid);
		
		String parentBxid = ByteHashCache.getDataValue(bxidKey, "parentBxid");
		
		ByteHashCache.deleteCache(bxidKey);
		SetCache.deleteCache(bxidIndexKey); // 删除以此分支事务为父事务的索引，不论此分支事务有没有子事务

		if("unknow".equals(parentBxid)) {
			bxidIndexKey.setKey("value", "unknow");
			SetCache.deleteCacheValue(bxidIndexKey, bxid);
		}
	}
	
	public void deleteIndexTxid(String txid) throws Exception{
		CKey txidIndexKey = CKeyUtil.createIndexCKey("branch", "txid", txid);
		SetCache.deleteCache(txidIndexKey);
	}
	
	public List<BranchTransaction> getUnCompletedByTxid(String txid) throws Exception {
		List<BranchTransaction> branches = new ArrayList<BranchTransaction>();
		List<BranchTransaction> tmpBranches = getAllByTxid(txid);
		for(BranchTransaction branch : tmpBranches){
			BranchStatus status = branch.getStatus();
			if (isNeedSubmit(status)) {
				branches.add(branch);
			}
		}
		return branches;
	}
	
	private boolean isNeedSubmit(BranchStatus status) {
		if (BranchStatus.submit_complete != status
					&& BranchStatus.submit_invalid != status)
			return true;
		
		return false;
	}
	
	private BranchDao(){
		
	}
	
	public static BranchDao getInstance(){
		
		if(branchDao == null){
			branchDao = new BranchDao();
		}
		return branchDao;
	}
}