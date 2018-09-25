package com.dcits.galaxy.dtp.redis.branch;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;

import com.dcits.galaxy.dtp.branch.BranchService;
import com.dcits.galaxy.dtp.branch.BranchServiceFactory;
import com.dcits.galaxy.dtp.branch.BranchStatus;
import com.dcits.galaxy.dtp.branch.BranchTransaction;
import com.dcits.galaxy.dtp.exception.DTPException;
import com.dcits.galaxy.dtp.redis.dao.BranchDao;

public class BranchServiceImplRedis implements BranchService,InitializingBean {
	
	private BranchDao branchDao = BranchDao.getInstance();
	
	@Override
	public boolean savaBranch(BranchTransaction branch){
		boolean result = false;
		
		try {
			branchDao.save(branch);
			result = true;
		} catch (Exception e) {
			throw new DTPException(e.getMessage(),e.getCause());
		}
		
		return result;
	}

	@Override
	public BranchTransaction getByBxid(String bxid) {
		BranchTransaction branch = null;
		try {
			branch = branchDao.getBranch(bxid);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(),e.getCause());
		}
		
		return branch;
	}

	@Override
	public boolean updateStatus(String bxid, BranchStatus status) {
		boolean result = false;
		try {
			branchDao.updateStatus(bxid, status);
			result = true;
		} catch (Exception e) {
			throw new DTPException(e.getMessage(),e.getCause());
		}
		return result;
	}

	@Override
	public List<BranchTransaction> getUnCompletedByAppGroup(String appGroup) {
		// nodo
		return null;
	}

	@Override
	public List<BranchTransaction> getUnCompletedDisorderByAppGroup(String appGroup) {
		List<BranchTransaction> branches = null;
		try {
			branches = branchDao.getUnCompletedDisorderByAppGroup(appGroup);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(),e.getCause());
		}
		return branches ;
	}

	@Override
	public List<BranchTransaction> getNeedSubmitByTxid(String txid) {
		List<BranchTransaction> branches = null;
		try {
			branches = branchDao.getNeedSubmitByTxid(txid);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(),e.getCause());
		}
		return branches ;
	}

	@Override
	public List<BranchTransaction> getNeedSubmitByBxid(String bxid) {
		List<BranchTransaction> branches = null;
		try {
			branches = branchDao.getNeedSubmitByBxid(bxid);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(),e.getCause());
		}
		return branches ;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		BranchServiceFactory.registerBean(this);
	}
}
