package com.dcits.galaxy.dtp.resume.trunk;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.dtp.DtpContext;
import com.dcits.galaxy.dtp.trunk.TrunkManager;
import com.dcits.galaxy.dtp.trunk.TrunkManagerFactory;
import com.dcits.galaxy.dtp.trunk.TrunkStatus;
import com.dcits.galaxy.dtp.trunk.TrunkTransaction;

/**
 * 主事务恢复任务
 * @author Yin.Weicai
 *
 */
public class TrunkResumeDisorderTask implements Runnable {
	
	private static Logger logger = LoggerFactory.getLogger(TrunkResumeDisorderTask.class);
	
	private TrunkTransaction trunk = null;
	
	public TrunkResumeDisorderTask( TrunkTransaction trunk){
		this.trunk = trunk;
	}

	@Override
	public void run() {
		try {
			TrunkManager trunkManager = TrunkManagerFactory.getBean();
			String txid = trunk.getTxid();
			
			TrunkStatus status = trunk.getStatus();
			
			boolean hasUnCompletedBranches = trunkManager.hasUnCompletedBranches(txid);
			if( !hasUnCompletedBranches ){
				if( TrunkStatus.cancel == status ){
					DtpContext.setStatus(status);
					
					logger.debug(" Try resume the cancel operation of TrunkTransaction : txid=" + txid);
					trunkManager.release(txid);
					boolean result = trunkManager.updateStatus(txid, TrunkStatus.cancel_complete);
					if( result ){
						logger.debug(" Try resume the cancel operation of TrunkTransaction : txid=" + txid +". successed!");
					}else{
						logger.debug(" Try resume the cancel operation of TrunkTransaction : txid=" + txid +". failed!");
					}
					
				}else if( TrunkStatus.confirm == status  ){	
					DtpContext.setStatus(status);
					
					logger.debug(" Try resume the confirm operation of TrunkTransaction : txid=" + txid);
					trunkManager.release(txid);
					boolean result = trunkManager.updateStatus(txid, TrunkStatus.confirm_complete);
					if( result ){
						logger.debug(" Try resume the confirm operation of TrunkTransaction : txid=" + txid +". successed!");
					}else{
						logger.debug(" Try resume the confirm operation of TrunkTransaction : txid=" + txid +". failed!");
					}
				}
			}
			
		} catch (Exception e) {
			logger.info("occur execption when resume trunk :" + trunk.getTxid(), e);
		}
	}

}
