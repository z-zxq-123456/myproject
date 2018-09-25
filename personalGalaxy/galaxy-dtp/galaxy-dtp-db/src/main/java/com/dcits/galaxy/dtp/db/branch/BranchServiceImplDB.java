package com.dcits.galaxy.dtp.db.branch;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.galaxy.dtp.branch.BranchService;
import com.dcits.galaxy.dtp.branch.BranchServiceFactory;
import com.dcits.galaxy.dtp.branch.BranchStatus;
import com.dcits.galaxy.dtp.branch.BranchTransaction;
import com.dcits.galaxy.dtp.db.dao.BranchDao;
import com.dcits.galaxy.dtp.exception.DTPException;

public class BranchServiceImplDB implements BranchService, ApplicationContextAware {

	@Resource
	private BranchDao branchDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean savaBranch(BranchTransaction branch) {
		boolean result = false;
		try {
			branchDao.save(branch);
			result = true;
		} catch (Exception e) {
			throw new DTPException(e.getMessage(), e.getCause());
		}

		return result;
	}

	@Override
	public BranchTransaction getByBxid(String bxid) {
		BranchTransaction branch = null;
		try {
			branch = branchDao.getBranch(bxid);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(), e.getCause());
		}
		return branch;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean updateStatus(String bxid, BranchStatus status) {
		
		try {
			branchDao.updateStatus(bxid, status);
			return true;
		} catch (Exception e) {
			throw new DTPException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public List<BranchTransaction> getUnCompletedByAppGroup(String appGroup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BranchTransaction> getUnCompletedDisorderByAppGroup(String appGroup) {
		List<BranchTransaction> branches = null;
		try {
			branches = branchDao.getUnCompletedDisorderByAppGroup(appGroup);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(), e.getCause());
		}
		return branches;
	}

	@Override
	public List<BranchTransaction> getNeedSubmitByTxid(String txid) {
		List<BranchTransaction> branches = new ArrayList<>();
		try {
			branches = branchDao.getNeedSubmitByTxid(txid);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(), e.getCause());
		}
		return branches;
	}

//	@Override
//	public List<BranchTransaction> getNeedReleaseByTxid(String txid) {
//		List<BranchTransaction> branches = new ArrayList<>();
//		try {
//			branches = branchDao.get(txid);
//		} catch (Exception e) {
//			throw new DTPException(e.getMessage(), e.getCause());
//		}
//		return branches;
//	}

	@Override
	public List<BranchTransaction> getNeedSubmitByBxid(String bxid) {
		List<BranchTransaction> branches = null;
		try {
			branches = branchDao.getAllByBxid(bxid);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(), e.getCause());
		}
		return branches;
	}

//	@Override
//	public List<BranchTransaction> getNeedReleaseByBxid(String bxid) {
//		List<BranchTransaction> branches = null;
//		try {
//			branches = branchDao.getAllByBxid(bxid);
//		} catch (Exception e) {
//			throw new DTPException(e.getMessage(), e.getCause());
//		}
//		return branches;
//	}

//	@Override
//	public List<BranchTransaction> getUnCompletedByTxid(String txid) {
//		List<BranchTransaction> branches = null;
//		try {
//			branches = branchDao.getUnCompletedByTxid(txid);
//		} catch (Exception e) {
//			throw new DTPException(e.getMessage(), e.getCause());
//		}
//		return branches;
//	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		BranchService service = applicationContext.getBean(this.getClass());
		BranchServiceFactory.registerBean(service);
	}
}
