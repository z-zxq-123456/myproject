package com.dcits.galaxy.dal.mybatis.transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.HeuristicCompletionException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.SavepointManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.SmartTransactionObject;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.dcits.galaxy.dal.mybatis.exception.MultipleCauseException;
import com.dcits.galaxy.dal.mybatis.shard.Shard;
import com.dcits.galaxy.dal.mybatis.shard.ShardManager;

/**
 * 基于一阶段提交的事务管理
 *
 * @author fan.kaijie
 *
 */
public class BestEffortMultiDataSourceTransactionManager extends AbstractPlatformTransactionManager implements InitializingBean {

	private static final long serialVersionUID = 4669889156628384124L;

	protected static final Logger logger = LoggerFactory.getLogger(BestEffortMultiDataSourceTransactionManager.class);

	private ShardManager shardManager;

	protected final List<AbstractPlatformTransactionManager> transactionManagers = new ArrayList<>();

	public BestEffortMultiDataSourceTransactionManager() {
		setNestedTransactionAllowed(true);
	}

	public BestEffortMultiDataSourceTransactionManager(ShardManager shardManager) {
		this();
		this.shardManager = shardManager;
	}

	@Override
	public boolean isExistingTransaction(Object transaction)
			throws TransactionException {
		return TransactionSynchronizationManager.hasResource(transactionManagers);
	}

	@Override
	protected Object doGetTransaction() throws TransactionException {
		logger.debug("get a transaction for multiple data sources.");
		return new TransactionObject(transactionManagers);
	}

	@Override
	protected void doBegin(Object o, TransactionDefinition definition) throws TransactionException {
		logger.debug("create a new transaction for multiple data sources.");

		TransactionObject object = (TransactionObject) o;

		Map<PlatformTransactionManager,TransactionStatus> statusMap = new HashMap<>();
		for (AbstractPlatformTransactionManager transactionManager : transactionManagers) {
			TransactionStatus status = transactionManager.getTransaction(definition);
			statusMap.put(transactionManager,	status);
		}
		object.setStatusMap(statusMap);

		bindTransaction(object);
	}

	@Override
	protected void doCommit(DefaultTransactionStatus defaultTransactionStatus) throws TransactionException {
		logger.debug("prepare to commit transactions on multiple data sources.");

		TransactionObject object = (TransactionObject) defaultTransactionStatus.getTransaction();

		MultipleCauseException ex = new MultipleCauseException();

		for(Entry<PlatformTransactionManager,TransactionStatus> entry : object.getStatusMap().entrySet()) {
			PlatformTransactionManager tm = entry.getKey();
			TransactionStatus status = entry.getValue();
			try {
				tm.commit(status);
			} catch (TransactionException e) {
				ex.add(e);
			}
		}

		List<Throwable> causes = ex.getCauses();
		if (!causes.isEmpty()) {
			if(causes.size() == 1){
				throw new HeuristicCompletionException(HeuristicCompletionException.STATE_UNKNOWN, causes.get(0));
			} else {
				throw new HeuristicCompletionException(HeuristicCompletionException.STATE_UNKNOWN, ex);
			}
		}
	}

	@Override
	protected void doRollback(DefaultTransactionStatus defaultTransactionStatus) throws TransactionException {
		logger.debug("prepare to rollback transactions on multiple data sources.");

		TransactionObject object = (TransactionObject) defaultTransactionStatus.getTransaction();

		MultipleCauseException ex = new MultipleCauseException();

		for(Entry<PlatformTransactionManager,TransactionStatus> entry : object.getStatusMap().entrySet()) {
			PlatformTransactionManager tm = entry.getKey();
			TransactionStatus status = entry.getValue();
			try {
				tm.rollback(status);
			} catch (TransactionException e) {
				ex.add(e);
			}
		}

		List<Throwable> causes = ex.getCauses();
		if (!causes.isEmpty()) {
			if(causes.size() == 1){
				throw new UnexpectedRollbackException("one or more error on rolling back the transaction", causes.get(0));
			} else {
				throw new UnexpectedRollbackException("one or more error on rolling back the transaction", ex);
			}
		}
	}

	public ShardManager getShardManager() {
		return shardManager;
	}

	public void setShardManager(ShardManager shardManager) {
		this.shardManager = shardManager;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (shardManager == null || shardManager.getShardSize() == 0) {
			throw new IllegalArgumentException("'shardManager' is required.");
		}
		for (Shard shard : shardManager.getAllShard()) {
			DataSource dataSource = shard.getDataSource();
			DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
			transactionManager.setDefaultTimeout(getDefaultTimeout());
			transactionManager.setTransactionSynchronization(SYNCHRONIZATION_NEVER);
			transactionManagers.add(transactionManager);
		}
	}

	@Override
	protected void doSetRollbackOnly(DefaultTransactionStatus status) throws TransactionException {
		logger.debug("set global transaction rollbackonly.");

		TransactionObject object = getCurrrntTransaction();
		object.setRollbackOnly();
	}

	@Override
	protected Object doSuspend(Object transaction) throws TransactionException {
		return unbindTransaction();
	}

	@Override
	protected void doResume(Object transaction, Object suspendedResources)
			throws TransactionException {
		TransactionObject oldObject = getCurrrntTransaction();
		if(oldObject != null && oldObject != transaction){
			logger.warn("it't hava exsited transaction for {}, will over it. ", oldObject);
		}
		bindTransaction(suspendedResources);
	}

	@Override
	protected void doCleanupAfterCompletion(Object transaction) {
		unbindTransaction();
	}

	private TransactionObject getCurrrntTransaction(){
		return (TransactionObject) TransactionSynchronizationManager.getResource(transactionManagers);
	}

	private TransactionObject unbindTransaction(){
		return (TransactionObject) TransactionSynchronizationManager.unbindResourceIfPossible(transactionManagers);
	}

	private void bindTransaction(Object suspendedResources){
		TransactionSynchronizationManager.bindResource(transactionManagers, suspendedResources);
	}

	private static class TransactionObject implements SmartTransactionObject,SavepointManager {

		private final Object transactionKey;
		private Map<PlatformTransactionManager, TransactionStatus> statusMap = null;

		private boolean rollbackOnly = false;

		public TransactionObject(Object transactionKey){
			this.transactionKey = transactionKey;
		}

		public Map<PlatformTransactionManager, TransactionStatus> getStatusMap() {
			return statusMap;
		}

		public void setStatusMap(Map<PlatformTransactionManager, TransactionStatus> statusMap) {
			this.statusMap = statusMap;
		}

		public void setRollbackOnly() {
			rollbackOnly = true;
		}

		@Override
		public boolean isRollbackOnly() {
			return rollbackOnly;
		}

		@Override
		public void flush() {
			// no-op
		}

		@Override
		public Object createSavepoint() throws TransactionException {

			TransactionObject activeTransaction = (TransactionObject) TransactionSynchronizationManager.getResource(transactionKey);

			if(activeTransaction != this) {
				return activeTransaction.createSavepoint();
			}

			logger.debug("create savepoint for nested transaction.");
			Map<TransactionStatus, Object> savapointMap = new HashMap<>();
			for (TransactionStatus status : statusMap.values()) {
				savapointMap.put(status, status.createSavepoint());
			}
			return savapointMap;
		}

		@Override
		public void rollbackToSavepoint(Object savepoints) throws TransactionException {
			TransactionObject activeTransaction = (TransactionObject) TransactionSynchronizationManager.getResource(transactionKey);

			if(activeTransaction != this) {
				activeTransaction.rollbackToSavepoint(savepoints);
				return;
			}

			logger.debug("rollback to savepoint for nested transaction.");
			@SuppressWarnings("unchecked")
			Map<TransactionStatus, Object> savapointMap = (Map<TransactionStatus, Object>) savepoints;
			for (Entry<TransactionStatus, Object> entry : savapointMap.entrySet()) {
				TransactionStatus status = entry.getKey();
				Object savepoint = entry.getValue();
				status.rollbackToSavepoint(savepoint);
			}

		}

		@Override
		public void releaseSavepoint(Object savepoints) throws TransactionException {
			TransactionObject activeTransaction = (TransactionObject) TransactionSynchronizationManager.getResource(transactionKey);

			if(activeTransaction != this) {
				activeTransaction.releaseSavepoint(savepoints);
				return;
			}

			logger.debug("release savepoint for nested transaction.");
			@SuppressWarnings("unchecked")
			Map<TransactionStatus, Object> savapointMap = (Map<TransactionStatus, Object>) savepoints;
			for (Entry<TransactionStatus, Object> entry : savapointMap.entrySet()) {
				TransactionStatus status = entry.getKey();
				Object savepoint = entry.getValue();
				status.releaseSavepoint(savepoint);
			}
		}
	}
}
