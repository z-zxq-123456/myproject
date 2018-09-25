package com.dcits.galaxy.dtp.branch;

import com.dcits.galaxy.dtp.branch.BranchManager;
import com.dcits.galaxy.dtp.branch.BranchManagerFactory;
import com.dcits.galaxy.dtp.branch.BranchTransaction;

public class BranchManagerHelper {
	
	public static BranchTransaction begin(String bxid,String txid){
		BranchManager branchManager = BranchManagerFactory.getBean();
		return branchManager.begin(bxid, txid);
	}
	
	public static BranchTransaction begin(String bxid,int indexInTrunk,String txid){
		BranchManager branchManager = BranchManagerFactory.getBean();
		return branchManager.begin(bxid, indexInTrunk, txid);
	}
	
	public static BranchTransaction begin(String bxid,int indexInBranch,String txid, String parentBxid){
		BranchManager branchManager = BranchManagerFactory.getBean();
		return branchManager.begin(bxid, indexInBranch, txid, parentBxid);
	}
	
	public static BranchTransaction begin(String bxid,String txid, String parentBxid){
		BranchManager branchManager = BranchManagerFactory.getBean();
		return branchManager.begin(bxid, -1, txid, parentBxid);
	}
}
