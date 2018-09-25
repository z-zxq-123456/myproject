package com.dcits.galaxy.dtp.defaults.log;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.dcits.galaxy.dtp.exception.PreExecuteException;
import com.dcits.galaxy.dtp.log.LogManager;
import com.dcits.galaxy.dtp.log.PrepareLog;
import com.dcits.galaxy.dtp.log.SubmitLog;
import com.dcits.galaxy.dtp.log.executor.SubmitLogExecutor;
import com.dcits.galaxy.dtp.log.executor.SubmitLogExecutorFactory;
import com.dcits.galaxy.dtp.log.service.PrepareLogService;
import com.dcits.galaxy.dtp.log.service.PrepareLogServiceFactory;
import com.dcits.galaxy.dtp.log.service.SubmitLogService;
import com.dcits.galaxy.dtp.log.service.SubmitLogServiceFactory;

/**
 * 默认的事务日志管理器
 * @author Yin.Weicai
 *
 */
public class DefaultLogManager implements LogManager,InitializingBean {
	
	private boolean testOnWrite = false;
	
	@Resource
	private PlatformTransactionManager transactionManager;

	public boolean isTestOnWrite() {
		return testOnWrite;
	}

	public void setTestOnWrite(boolean testOnWrite) {
		this.testOnWrite = testOnWrite;
	}

	public DefaultLogManager(){
	}
	
	public DefaultLogManager(boolean testWhenWrite) {
		this.testOnWrite = testWhenWrite;
	}
	
	@Override
	public boolean writePrapareLog(PrepareLog prepareLog) {
		PrepareLogService prepareLogService = PrepareLogServiceFactory.getBean();
		return prepareLogService.saveLog( prepareLog );
	}

	@Override
	public boolean writeSubmitLog(SubmitLog submitLog) {
//		if( testOnWrite && !testSubmitLog(submitLog)) {
//			return false;
		SubmitLogService submitLogService = SubmitLogServiceFactory.getBean();
		return submitLogService.saveLog( submitLog );
	}
	
	@SuppressWarnings("unused")
	private boolean testSubmitLog(SubmitLog submitLog) {
		SubmitLogExecutor executor = SubmitLogExecutorFactory
				.getLogExecutor(submitLog.getLogTypeName());

		TransactionDefinition definition = new DefaultTransactionDefinition(
				TransactionDefinition.PROPAGATION_REQUIRES_NEW);

		TransactionStatus status = transactionManager.getTransaction(definition);
		boolean result = false;
		try{
			result = executor.execute(submitLog);
		} catch(Exception e){
			throw new PreExecuteException(e);
		} finally {
			transactionManager.rollback(status);
		}
		
		return result;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		LogManagerHelper.registLogManager(this);
	}
	
}
