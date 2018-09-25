package com.dcits.galaxy.dtp.defaults.trunk;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.dcits.galaxy.dtp.DtpContext;
import com.dcits.galaxy.dtp.branch.BranchManager;
import com.dcits.galaxy.dtp.branch.BranchService;
import com.dcits.galaxy.dtp.branch.BranchServiceFactory;
import com.dcits.galaxy.dtp.branch.BranchTransaction;
import com.dcits.galaxy.dtp.exception.CannotSetConfirmException;
import com.dcits.galaxy.dtp.exception.DTPException;
import com.dcits.galaxy.dtp.log.LogStatus;
import com.dcits.galaxy.dtp.log.PrepareLog;
import com.dcits.galaxy.dtp.log.executor.PrepareLogExecutor;
import com.dcits.galaxy.dtp.log.executor.PrepareLogExecutorFactory;
import com.dcits.galaxy.dtp.log.service.PrepareLogService;
import com.dcits.galaxy.dtp.log.service.PrepareLogServiceFactory;
import com.dcits.galaxy.dtp.trunk.TrunkManager;
import com.dcits.galaxy.dtp.trunk.TrunkManagerFactory;
import com.dcits.galaxy.dtp.trunk.TrunkService;
import com.dcits.galaxy.dtp.trunk.TrunkServiceFactory;
import com.dcits.galaxy.dtp.trunk.TrunkStatus;
import com.dcits.galaxy.dtp.trunk.TrunkTransaction;
import com.dcits.galaxy.dtp.util.RPCHelper;

/**
 * 缺省的主事务管理器
 * @author Yin.Weicai
 *
 */
public class DefaultTrunkManager implements TrunkManager,InitializingBean {
	
	private static Logger logger = LoggerFactory.getLogger( DefaultTrunkManager.class );
	
	@Override
	public TrunkTransaction begin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TrunkTransaction begin(String txid) {
		TrunkTransaction trunk = new TrunkTransaction();
		trunk.setTxid(txid);
		String appGroup = RPCHelper.getAppGroup();
		trunk.setAppGroup(appGroup);
		TrunkService trunkService = TrunkServiceFactory.getBean();
		boolean result = trunkService.saveTransaction(trunk);
		if( result ){
			DtpContext.setTxid(txid);
			DtpContext.setStatus(TrunkStatus.prepare);
			return trunk;
		} else {
			return null;
		}
	}

	@Override
	public void confirm(String txid) {
		logger.debug("Begin TrunkTransaction [txid=" + txid +"] confirm...");

		boolean result = false;
		//置主事务状态为确认
		TrunkService trunkService = TrunkServiceFactory.getBean();
		try {
			result = trunkService.setConfirm(txid);
		} catch (Exception e) {
			throw new CannotSetConfirmException("caught exception when change trunk:"+txid+" status to confirm.", e);
		}
		
		if(!result){
			throw new CannotSetConfirmException("can't change trunk:"+txid+" status to confirm, please check Trunk status.");
		}
		
		DtpContext.setStatus(TrunkStatus.confirm);

		try {
			//确认提交分支事务
			BranchService branchService = BranchServiceFactory.getBean();
			List<BranchTransaction> submitBranches = branchService.getNeedSubmitByTxid(txid);
			if( submitBranches != null && submitBranches.size() > 0){
				logger.debug("TrunkTransaction [ txid=" + txid +"] has [" + submitBranches.size() + "] BranchTransaction need be confirmSubmit!");
				for (BranchTransaction branch : submitBranches) {
					String appGroup = branch.getAppGroup();
					BranchManager bmService = RPCHelper.getBranchManager(appGroup);
					result = bmService.confirmSubmit(branch);
					if( !result ){
						break;
					}
				}
			}else{
				logger.debug("TrunkTransaction [" + txid + "] has [0] BranchTransaction need be confirmSubmit!");
				result = true;
			}
			
			if( result ){
				//释放占有的资源
				result = doRelease(txid);
			}
			
			if( result ){
				//置主事务状态为确认完成
				trunkService.setConfirmCompleted(txid);
				logger.debug("TrunkTransaction [" + txid + "] confirm has completed!");
			}
		} catch (Exception e) {
			throw new DTPException("caught exception when confirm Trunk:" + txid, e);
		} finally {
			DtpContext.clean();
		}
	}

	@Override
	public void cancel(String txid) {
		logger.debug("Begin TrunkTransaction [txid=" + txid +"] cancel...");

		boolean result = false;
		
		//置主事务状态为取消
		TrunkService trunkService = TrunkServiceFactory.getBean();

		try {
			result = trunkService.setCancel(txid);
			
			if(!result){
				logger.warn("can't cancle trunk:{}, please check Trunk status. ", txid);
				return;
			}
			
			DtpContext.setStatus(TrunkStatus.cancel);
			BranchService branchService = BranchServiceFactory.getBean();
			
			//释放事务占有的资源
			result = doRelease(txid);
			
			if( result ){
				//取消分支事务
				List<BranchTransaction> submitBranches = branchService.getNeedSubmitByTxid(txid);
				if( submitBranches != null && submitBranches.size() > 0){
					logger.debug("TrunkTransaction [ txid=" + txid +"] has " + submitBranches.size() + "BranchTransaction need be cancelSubmit!");
					for (BranchTransaction branch : submitBranches) {
						String appGroup = branch.getAppGroup();
						BranchManager branchManager = RPCHelper.getBranchManager(appGroup);
						result = branchManager.cancelSubmit( branch );
						if( !result ){
							break;
						}
					}
				}else{
					logger.debug("TrunkTransaction [ txid=" + txid +"] has [0] BranchTransaction need be cancelSubmit!");
				}
			}
			
			if( result ){
				//置主事务状态为取消完成
				trunkService.setCancelCompleted(txid);
				logger.debug("TrunkTransaction [" + txid + "] cancel has completed!");
			}
		} catch (Exception e) {
			throw new DTPException("caught exception when cancel Trunk:" + txid, e);
		} finally {
			DtpContext.clean();
		}
	}

	@Override
	public TrunkStatus getStatus(String txid) {
		TrunkService trunkService = TrunkServiceFactory.getBean();
		TrunkTransaction trunk = trunkService.getTransaction(txid);
		return trunk.getStatus();
	}
	
	@Override
	public boolean hasUnCompletedBranches(String txid) {
		TrunkService trunkService = TrunkServiceFactory.getBean();
		return trunkService.hasUnCompletedBranches(txid);
	}

	@Override
	public void release(String txid) {
		doRelease(txid);
	}

	@Override
	public boolean updateStatus(String txid, TrunkStatus status) {
		boolean result = false;
		
		//置主事务状态为确认
		TrunkService trunkService = TrunkServiceFactory.getBean();
		
		if( TrunkStatus.cancel_complete == status){
			result = trunkService.setCancelCompleted( txid );
		} else if( TrunkStatus.confirm_complete == status ){
			result = trunkService.setConfirmCompleted( txid );
		} else if( TrunkStatus.cancel == status ){
			result = trunkService.setCancel( txid );
		} else if( TrunkStatus.confirm == status ){
			result = trunkService.setConfirm( txid );
		} else {
			// do nothing
		}
		return result;
	}
	
	
	/**
	 * 释放事务占有的资源
	 * @param txid 主事务标识
	 * @return
	 */
	private boolean doRelease(String txid){
		
		boolean result = true;

		PrepareLogService prepareLogService = PrepareLogServiceFactory.getBean();
		List<PrepareLog> undoPrepareLogs = prepareLogService.getUndoLogsByTxid(txid);
		
		if(undoPrepareLogs != null && undoPrepareLogs.size() > 0) {
			logger.debug("TrunkTransaction [ txid={} ] has [{}] BranchTransaction need be release!", txid, undoPrepareLogs.size());
			
			for(PrepareLog prepareLog : undoPrepareLogs) {
				result = doReleasePrepareLog(prepareLog);
				if(!result){
					break;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 释放事务占有的资源
	 * @param prepareLog 要释放资源的资源日志
	 * @return
	 */
	private boolean doReleasePrepareLog(PrepareLog prepareLog){
		boolean result = true;
		
		//根据事务准备日志释放资源
		String logId = prepareLog.getLogId();
		String logName = prepareLog.getLogTypeName();
		
		DtpContext.setLogid(logId);
		
		PrepareLogExecutor logExecutor = PrepareLogExecutorFactory.getLogExecutor(logName);
		logger.debug("PrepareLog [txid={} ,logId={}] execute...",prepareLog.getTxid(),logId);
		result = logExecutor.execute(prepareLog);
		logger.debug("PrepareLog [txid={},logId={}] execute result={}",new Object[]{prepareLog.getTxid(), logId, result});
		
		DtpContext.setLogid(null);
		
		if( !result ){
			return false;
		}
		
		//更新事务准备日志状态
		PrepareLogService prepareLogService = PrepareLogServiceFactory.getBean();
		result = prepareLogService.updateLogStatus( logId, LogStatus.done);
		
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		TrunkManagerFactory.registerBean(this);
	}

}
