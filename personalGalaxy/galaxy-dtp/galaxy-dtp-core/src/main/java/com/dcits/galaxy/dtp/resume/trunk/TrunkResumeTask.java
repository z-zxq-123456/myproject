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
public class TrunkResumeTask implements Runnable {
	
	private static Logger logger = LoggerFactory.getLogger(TrunkResumeTask.class);
	
	private TrunkTransaction trunk = null;
	
	public TrunkResumeTask( TrunkTransaction trunk){
		this.trunk = trunk;
	}

	@Override
	public void run() {
		try {
			TrunkManager trunkManager = TrunkManagerFactory.getBean();
			String txid = trunk.getTxid();
			
			DtpContext.setTxid(txid);
			
			TrunkStatus status = trunk.getStatus();
			if( TrunkStatus.cancel == status ){
				logger.debug(" Try resume the cancel operation of TrunkTransaction : txid=" + txid);
				trunkManager.cancel(txid);
				logger.debug(" Try resume the cancel operation of TrunkTransaction : txid=" + txid +". successed!");
			}else if( TrunkStatus.confirm == status  ){	
				logger.debug(" Try resume the confirm operation of TrunkTransaction : txid=" + txid);
				trunkManager.confirm(txid);
				logger.debug(" Try resume the confirm operation of TrunkTransaction : txid=" + txid +". successed!");
			}else if( TrunkStatus.prepare == status  ){
				logger.debug(" Try resume the cancel operation of TrunkTransaction : txid=" + txid);
				trunkManager.cancel(txid);
				logger.debug(" Try resume the cancel operation of TrunkTransaction : txid=" + txid +". successed!");
			}
		} catch (Exception e) {
			logger.info("occur execption when resume trunk :" + trunk.getTxid(), e);
		}
		
	}

}
