package com.dcits.galaxy.dtp.defaults.branch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.dcits.galaxy.dtp.DtpContext;
import com.dcits.galaxy.dtp.branch.BranchManager;
import com.dcits.galaxy.dtp.branch.BranchManagerFactory;
import com.dcits.galaxy.dtp.branch.BranchService;
import com.dcits.galaxy.dtp.branch.BranchServiceFactory;
import com.dcits.galaxy.dtp.branch.BranchStatus;
import com.dcits.galaxy.dtp.branch.BranchTransaction;
import com.dcits.galaxy.dtp.log.LogStatus;
import com.dcits.galaxy.dtp.log.SubmitLog;
import com.dcits.galaxy.dtp.log.executor.SubmitLogExecutor;
import com.dcits.galaxy.dtp.log.executor.SubmitLogExecutorFactory;
import com.dcits.galaxy.dtp.log.service.SubmitLogService;
import com.dcits.galaxy.dtp.log.service.SubmitLogServiceFactory;
import com.dcits.galaxy.dtp.util.RPCHelper;

/**
 * 缺省的分支事务管理器
 * @author Yin.Weicai
 *
 */
public class DefaultBranchManager implements BranchManager,InitializingBean {
	
	private static Logger logger = LoggerFactory.getLogger( DefaultBranchManager.class );
	
	@Override
	public boolean confirmSubmit(String bxid) {
		boolean result = false;
		
		logger.debug("Begin BrancTransaction [txid=" + bxid +"] confirmSubmit...");
		//更新分支事务状态 -- 确认态
		BranchService branchService = BranchServiceFactory.getBean();
		result = branchService.updateStatus(bxid, BranchStatus.confirm);
		
		//确认该分支事务内部嵌套的分支事务
		if( result ){
			logger.debug("Begin BrancTransaction [txid=" + bxid +"] confirmSubmitNested...");
			result = confirmSubmitNested( bxid );
			logger.debug("BrancTransaction [txid=" + bxid +"] confirmSubmitNested result=" + result);
		}
		
		//嵌套的分支事务处理完毕，确认该分支事务
		if( result ){
			logger.debug("Begin BrancTransaction [txid=" + bxid +"] doSubmit...");
			result = doSubmit(bxid);
			logger.debug("BrancTransaction [txid=" + bxid +"] doSubmit result=" + result);
		}
		
		//将分支事务置为完成态
		if( result ){
			result = branchService.updateStatus(bxid, BranchStatus.submit_complete);
			logger.debug("BrancTransaction [bxid=" + bxid +"] confirmSubmit is completed...");
		}
		
		return result;
	}
	
	@Override
	public boolean confirmSubmit(BranchTransaction branch) {
		boolean result = false;
		String bxid = branch.getBxid();
		result = confirmSubmit(bxid);
		return result;
	}
	
	/**
	 * 提交分支事务内部嵌套的分支事务
	 * @param bxid 分支事务标识
	 * @return
	 */
	private boolean confirmSubmitNested(String bxid){
		boolean result = false;
		BranchService branchService = BranchServiceFactory.getBean();
		List<BranchTransaction> branches = branchService.getNeedSubmitByBxid(bxid);
		if( branches != null && branches.size() > 0){
			for (BranchTransaction branch : branches) {
				String appName = branch.getAppGroup();
				BranchManager branchManager = RPCHelper.getBranchManager(appName);
				result = branchManager.confirmSubmit( branch );
				if( !result ){
					break;
				}
			}
		}else{
			result = true;
		}
		return result;
	}
	

	@Override
	public boolean cancelSubmit(String bxid) {
		boolean result = false;
		
		//更新分支事务状态 -- 取消态
		BranchService branchService = BranchServiceFactory.getBean();
		result = branchService.updateStatus(bxid, BranchStatus.cancel);
		
		//取消分支事务内部嵌套的要提交的分支事务
		if( result ){
			result = cancelSubmitNested(bxid);
		}
		
		//更新分支事务状态 -- 确认态
		if( result ){
			result = branchService.updateStatus(bxid, BranchStatus.submit_invalid);
		}
		
		return result;
	}
	
	@Override
	public boolean cancelSubmit( BranchTransaction branch ) {
		boolean result = false;
		
		String bxid = branch.getBxid();
		
		result = cancelSubmit(bxid);
		
		return result;
	}
	
	/**
	 * 根据分支事务内部嵌套的分支事务准备日志释放占有的资源
	 * @param bxid 分支事务标识
	 * @return
	 */
	private boolean cancelSubmitNested(String bxid){
		boolean result = false;
		BranchService branchService = BranchServiceFactory.getBean();
		List<BranchTransaction> branches = branchService.getNeedSubmitByBxid(bxid);
		if( branches != null && branches.size() > 0){
			for (BranchTransaction branch : branches) {
				String appName = branch.getAppGroup();
				BranchManager branchManager = RPCHelper.getBranchManager(appName);
				result = branchManager.cancelSubmit( branch );
				if( !result ){
					break;
				}
			}
		}else{
			result = true;
		}
		return result;
	}
	
	@Override
	public BranchTransaction begin(String bxid, String txid) {
		return begin(bxid, -1, txid);
	}

	@Override
	public BranchTransaction begin(String bxid, int indexInTrunk, String txid) {
		BranchTransaction branch = new BranchTransaction();
		branch.setBxid( bxid );
		branch.setIndexInTrunk(indexInTrunk);
		branch.setTxid(txid);
		String appGroup = RPCHelper.getAppGroup();
		branch.setAppGroup(appGroup);
		return begin(branch);
	}
	
	@Override
	public BranchTransaction begin(String bxid, String txid, String parentBxid) {
		return begin(bxid, -1, txid, parentBxid);
	}

	@Override
	public BranchTransaction begin(String bxid, int indexInBranch, String txid,
			String parentBxid) {
		BranchTransaction branch = new BranchTransaction();
		branch.setBxid( bxid );
		branch.setParentBxid(parentBxid);
		branch.setIndexInBranch(indexInBranch);
		branch.setTxid(txid);
		String appName = RPCHelper.getAppGroup();
		branch.setAppGroup(appName);
		return begin(branch);
	}

	@Override
	public BranchTransaction begin(BranchTransaction branch) {
		BranchService branchService = BranchServiceFactory.getBean();
		boolean result = branchService.savaBranch(branch);
		if( result ){
			DtpContext.setBxid(branch.getBxid());
			return branch;
		}else{
			return null;
		}
	}
	
	/**
	 * 执行事务的提交
	 * @param bxid 分支事务标识
	 * @return
	 */
	private boolean doSubmit(String bxid){

		SubmitLogService submitLogService = SubmitLogServiceFactory.getBean();
		List<SubmitLog> submitLogs = submitLogService.getUndoLogsByBxid(bxid);

		if(submitLogs == null || submitLogs.isEmpty()) {
			return true;
		}
		
		DtpContext.setBxid(bxid);
		boolean result = true;
		try{
			for (SubmitLog commitLog : submitLogs) {
				result = executeLog(commitLog);
				if(!result) {
					break;
				}
			}
		} finally {
			DtpContext.setBxid(null);
		}

		return result;
	}

	private boolean executeLog(SubmitLog submitLog){
		boolean result = false;

		String logName = submitLog.getLogTypeName();
		String logId = submitLog.getLogId();

		DtpContext.setLogid(logId);

		try {
			SubmitLogExecutor logExecutor = SubmitLogExecutorFactory.getLogExecutor( logName );
			logger.debug("SubmitLog [bxid={},logId={}] execute...",submitLog.getBxid(),logId);
			result = logExecutor.execute( submitLog );
			logger.debug("SubmitLog [bxid={},logId={}] execute result={}", new Object[]{submitLog.getBxid(),logId,result});
		} finally {
			DtpContext.setLogid(null);
		}

		if( result ){
			SubmitLogServiceFactory.getBean().updateLogStatus(logId, LogStatus.done);
		}

		return result;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		BranchManagerFactory.registerBean(this);
	}
}
