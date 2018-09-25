package com.dcits.galaxy.dtp.resume.branch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.dtp.DtpContext;
import com.dcits.galaxy.dtp.branch.BranchManager;
import com.dcits.galaxy.dtp.branch.BranchManagerFactory;
import com.dcits.galaxy.dtp.branch.BranchStatus;
import com.dcits.galaxy.dtp.branch.BranchTransaction;

/**
 * 分支事务恢复任务
 * @author Yin.Weicai
 *
 */
public class BranchResumerTask implements Runnable {
	
	private static Logger logger = LoggerFactory.getLogger(BranchResumerTask.class);
	
	private BranchTransaction branch = null;
	
	public BranchResumerTask( BranchTransaction branch){
		this.branch = branch;
	}

	@Override
	public void run() {
		
		try {
			DtpContext.setTxid(branch.getTxid());
			BranchManager branchManager = BranchManagerFactory.getBean();
			
			String bxid = branch.getBxid();
			BranchStatus status = branch.getStatus();
			
			if( BranchStatus.cancel == status ){
				
				logger.debug(" Try resume the cancelSubmit operation of BranchTransaction : bxid=" + bxid);
				branchManager.cancelSubmit(bxid);
				logger.debug(" Try resume the cancelSubmit operation of BranchTransaction : bxid=" + bxid +". successed!");
				
			}else if( BranchStatus.confirm == status  ){	
				
				logger.debug(" Try resume the confirmSubmit operation of BranchTransaction : bxid=" + bxid);
				branchManager.confirmSubmit(bxid);
				logger.debug(" Try resume the confirmSubmit operation of BranchTransaction : bxid=" + bxid +". successed!");
				
			}else if( BranchStatus.prepare == status  ){
				
//				branchManager.cancel(txid);
			}
			
		} catch (Exception e) {
			logger.info("occur execption when resume branch :" + branch.getBxid(), e);
		} finally {
			DtpContext.clean();
		}
		
	}

}
