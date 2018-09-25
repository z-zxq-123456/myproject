package com.dcits.galaxy.dtp.redis.util;

import java.util.HashMap;
import java.util.Map;

import com.dcits.galaxy.dtp.branch.BranchStatus;
import com.dcits.galaxy.dtp.branch.BranchTransaction;
import com.dcits.galaxy.dtp.trunk.TrunkStatus;
import com.dcits.galaxy.dtp.trunk.TrunkTransaction;

public class TransactionUtil {

	public static Map<String, Object> trunkToMap(TrunkTransaction trunk) {
		Map<String, Object> map = new HashMap<>();

		map.put("txid", trunk.getTxid());
		map.put("status", trunk.getStatus());
		map.put("needOrder", trunk.isNeedOrder());
		map.put("appGroup", trunk.getAppGroup());
		map.put("startTime", trunk.getStartTime());

		return map;
	}

	public static TrunkTransaction mapToTrunk(Map<String, Object> map) {
		TrunkTransaction trunk = new TrunkTransaction();
		
		if (map.containsKey("txid"))
			trunk.setTxid((String) map.get("txid"));

		if (map.containsKey("status"))
			trunk.setStatus((TrunkStatus) map.get("status"));

		if (map.containsKey("needOrder"))
			trunk.setNeedOrder( (boolean) map.get("needOrder"));

		if (map.containsKey("appGroup"))
			trunk.setAppGroup((String) map.get("appGroup"));

		if (map.containsKey("startTime"))
			trunk.setStartTime((long) map.get("startTime"));
		
		return trunk;
	}

	public static Map<String, Object> branchToMap(BranchTransaction branch) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("bxid", branch.getBxid());
		map.put("txid", branch.getTxid());
		map.put("parentBxid", branch.getParentBxid());
		map.put("indexInBranch", branch.getIndexInBranch());
		map.put("indexInTrunk", branch.getIndexInTrunk());
		map.put("status", branch.getStatus());
		map.put("appGroup", branch.getAppGroup());
		
		return map;
	}
	
	public static BranchTransaction mapToBranch(Map<String, Object> map) {
		BranchTransaction branch = new BranchTransaction();

		if(map.containsKey("bxid"))
			branch.setBxid((String) map.get("bxid"));
		
		if(map.containsKey("txid"))
			branch.setTxid((String) map.get("txid"));
		
		if(map.containsKey("parentBxid"))
			branch.setParentBxid((String) map.get("parentBxid"));
		
		if(map.containsKey("indexInBranch"))
			branch.setIndexInBranch((int) map.get("indexInBranch"));
		
		if(map.containsKey("indexInTrunk"))
			branch.setIndexInTrunk((int) map.get("indexInTrunk"));
		
		if(map.containsKey("status"))
			branch.setStatus((BranchStatus) map.get("status"));
		
		if(map.containsKey("appGroup"))
			branch.setAppGroup((String) map.get("appGroup"));
		
		return branch;
	}

	public static boolean isUnCompletedTrunk(TrunkStatus status) {
		boolean result = false;

		if (TrunkStatus.cancel == status || TrunkStatus.confirm == status )
			result = true;

		return result;
	}
	
	public static boolean isCompleted(TrunkStatus status) {
		boolean result = false;

		if (TrunkStatus.cancel_complete == status
				|| TrunkStatus.confirm_complete == status)
			result = true;

		return result;
	}

	public static boolean isCompleted(TrunkTransaction trunk) {
		return isCompleted(trunk.getStatus());
	}
	
	public static String getTimeQuantum(long time){
		long tmp = time / 1000 / 60 / 30;
		return Long.toString(tmp);
	}
	
	private TransactionUtil() {

	}
}
