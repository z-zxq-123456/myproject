package com.dcits.galaxy.dal.mybatis.transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.HeuristicCompletionException;
import org.springframework.transaction.SavepointManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.SmartTransactionObject;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.dcits.galaxy.dal.mybatis.exception.DALException;
import com.dcits.galaxy.dal.mybatis.exception.MultipleCauseException;
import com.dcits.galaxy.dal.mybatis.exception.UnexpectedTransactionException;
import com.dcits.galaxy.dal.mybatis.transaction.log.SqlLogConnection;
import com.dcits.galaxy.dal.mybatis.transaction.ordered.OrderedSubmitUtils;

/**
 * 基于一阶段提交的事务管理 与BestEffortMultiDataSourceTransactionManager不同点为：
 * MultiDataSourceTransactionManager只在实际使用某个DataSource时 才开启对应的事务
 *
 * @author fan.kaijie
 */
public class DynamicBestEffortMultiDataSourceTransactionManager extends AbstractPlatformTransactionManager {

	private static final long serialVersionUID = -2929646244712639459L;
	
	public static final int ORDER_SUBMIT_NO = 0;
	public static final int ORDER_SUBMIT_ALWAYS = 1;
	public static final int ORDER_SUBMIT_DYNAMIC = 2;

	protected static final Logger logger = LoggerFactory
			.getLogger(DynamicBestEffortMultiDataSourceTransactionManager.class);

	/*
	 * 全局保持单例引用，此类的实例应该保持应用中唯一
	 */
	protected static DynamicBestEffortMultiDataSourceTransactionManager single = null;
	
	private int orderSubmit = ORDER_SUBMIT_NO;

	public static DynamicBestEffortMultiDataSourceTransactionManager getInstance() {
		return single;
	}

	public DynamicBestEffortMultiDataSourceTransactionManager() {
		setNestedTransactionAllowed(true);
		single = this;
	}

	@Override
	protected Object doGetTransaction() throws TransactionException {
		logger.debug("get a transaction for multiple data sources.");
		return new TransactionHolder();
	}

	@Override
	protected void doBegin(Object o, TransactionDefinition definition) throws TransactionException {
		logger.debug("create a new transaction for multiple data sources.");
		TransactionHolder holder = (TransactionHolder) o;
		holder.setDefinition(definition);
		holder.setOrderSubmit(orderSubmit);
		int timeout = determineTimeout(definition);
		if (timeout != TransactionDefinition.TIMEOUT_DEFAULT) {
			((DefaultTransactionDefinition) definition).setTimeout(timeout);
		}
		TransactionSynchronizationManager.bindResource(DynamicTransactionObject.DYNAMIC_KEY, holder);
	}

	@Override
	public boolean isExistingTransaction(Object transaction) throws TransactionException {
		return isExistingTransaction();
	}

	protected boolean isExistingTransaction() {
		return TransactionSynchronizationManager.hasResource(DynamicTransactionObject.DYNAMIC_KEY);
	}

	@Override
	protected void doCommit(DefaultTransactionStatus status) throws TransactionException {

		logger.debug("prepare to commit transactions on multiple data sources.");

		TransactionHolder currentHolder = getCurrrntHolder();

		if (currentHolder == null) {
			throw new UnexpectedTransactionException("don't expected Transaction!");
		}
		MultipleCauseException ex = new MultipleCauseException();
		LinkedHashSet<DataSource> dataSources = OrderedSubmitUtils.getOrderInfo();
		if(dataSources == null){
			dataSources = new LinkedHashSet<DataSource>();
		}
		
		Map<DataSource, TransactionConnectionHolder> map = currentHolder.getHolderResources();
		for(DataSource ds : map.keySet()){
			dataSources.add(ds);
		}
		
		for(DataSource ds : dataSources){
			TransactionConnectionHolder con = map.get(ds);
			try {
				con.getConnection().commit();
			} catch (SQLException e) {
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
	protected void doRollback(DefaultTransactionStatus status) throws TransactionException {
		logger.debug("prepare to rollback transactions on multiple data sources.");
		MultipleCauseException ex = new MultipleCauseException();

		TransactionHolder currentHolder = getCurrrntHolder();

		if (currentHolder == null) {
			throw new UnexpectedTransactionException("don't expected Transaction!");
		}
		for (Entry<DataSource, TransactionConnectionHolder> conHolders : currentHolder.getHolderResources()
				.entrySet()) {
			TransactionConnectionHolder conHolder = conHolders.getValue();
			try {
				conHolder.getConnection().rollback();
			} catch (SQLException e) {
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

	private TransactionHolder getCurrrntHolder() {
		return (TransactionHolder) TransactionSynchronizationManager.getResource(DynamicTransactionObject.DYNAMIC_KEY);
	}

	@Override
	protected void doSetRollbackOnly(DefaultTransactionStatus status) throws TransactionException {

		logger.debug("set global transaction rollbackonly.");

		TransactionHolder holder = getCurrrntHolder();
		if (holder == null) {
			throw new UnexpectedTransactionException("don't expected Transaction!");
		}

		holder.setRollbackOnly();
	}

	@Override
	protected Object doSuspend(Object transaction) throws TransactionException {
		TransactionHolder currentHolder = getCurrrntHolder();
		if (currentHolder == null) {
			return null;
		}
		logger.debug("suspend the transaction!");
		Map<DataSource, TransactionConnectionHolder> holdermap = currentHolder.getHolderResources();
		for (Entry<DataSource, TransactionConnectionHolder> holders : holdermap.entrySet()) {
			DataSource data = holders.getKey();
			TransactionSynchronizationManager.unbindResource(data);
		}
		TransactionSynchronizationManager.unbindResource(DynamicTransactionObject.DYNAMIC_KEY);
		return currentHolder;
	}

	@Override
	protected void doResume(Object transaction, Object suspendedResources) throws TransactionException {
		TransactionHolder transactionHolder = (TransactionHolder) suspendedResources;
		Map<DataSource, TransactionConnectionHolder> holdermap = transactionHolder.getHolderResources();
		for (Entry<DataSource, TransactionConnectionHolder> holders : holdermap.entrySet()) {
			DataSource data = holders.getKey();
			TransactionConnectionHolder holder = holders.getValue();
			TransactionSynchronizationManager.bindResource(data, holder);
		}
		TransactionSynchronizationManager.bindResource(DynamicTransactionObject.DYNAMIC_KEY, suspendedResources);
	}

	@Override
	protected void doCleanupAfterCompletion(Object transaction) {
		logger.debug("resume the transaction previousIsolationLevel!");
		TransactionHolder transactionHolder = (TransactionHolder) transaction;
		Map<DataSource, TransactionConnectionHolder> holderMap = transactionHolder.getHolderResources();

		for(Entry<DataSource,TransactionConnectionHolder> entry: holderMap.entrySet()){
			DataSource datasource = entry.getKey();
			TransactionConnectionHolder holder = entry.getValue();
			Integer previousIsolationLevel = holder.getPreviousIsolationLevel();
			Connection conn = holder.getConnection();
			try {
				TransactionSynchronizationManager.unbindResource(datasource);
				try {
					if(holder.isMustRestoreAutoCommit()){
						conn.setAutoCommit(true);
					}
				} catch (Exception e) {
					//ignore
				}
				DataSourceUtils.resetConnectionAfterTransaction(conn, previousIsolationLevel);
				DataSourceUtils.releaseConnection(conn, datasource);
			} catch (Exception e) {
				logger.trace("cleanup connection:{} after completion occur exception.");
			}
		}
		TransactionSynchronizationManager.unbindResource(DynamicTransactionObject.DYNAMIC_KEY);
	}

	private static class TransactionHolder
			implements SmartTransactionObject, SavepointManager, DynamicTransactionObject {
		private TransactionDefinition definition;
		private boolean rollbackOnly = false;
		
		private int orderSubmit = 0;

		private Map<DataSource, TransactionConnectionHolder> holderResources = new LinkedHashMap<>();

		public Map<DataSource, TransactionConnectionHolder> getHolderResources() {
			return holderResources;
		}

		private Map<TransactionConnectionHolder, Stack<Object>> conSavePoint = null;

		private int nestedLevel = 0;

		public void setRollbackOnly() {
			rollbackOnly = true;
		}

		public void setDefinition(TransactionDefinition definition) {
			this.definition = definition;
		}

		private boolean isExsitingTransactionForDataSource(DataSource dataSource) {
			return holderResources.containsKey(dataSource);
		}

		/**
		 * @param dataSource
		 *            向'当前'事务Holder中添加DataSource对应的事务
		 *            当前是指PROPAGATION_REQUIRED、PROPAGATION_REQUIRES_NEW、
		 *            PROPAGATION_NESTED新添加的事务， 或其他事务行为所继承的外层事务
		 */
		@Override
		public void addTransaction(DataSource dataSource) {
			if (isExsitingTransactionForDataSource(dataSource)) {
				return;
			}

			openTransaction(dataSource);

			if (nestedLevel > 0) {
				handleNestedTransaction(dataSource);
			}
		}

		private void handleNestedTransaction(DataSource dataSource) {

			TransactionConnectionHolder conHolder = holderResources.get(dataSource);
			Stack<Object> saveStack = conSavePoint.get(conHolder);
			if (saveStack == null) {
				saveStack = new Stack<>();
				conSavePoint.put(conHolder, saveStack);
			}

			for (int i = saveStack.size(); i < nestedLevel; i++) {
				try {
					saveStack.push(conHolder.createSavepoint());
				} catch (Exception e) {
					throw new CannotCreateTransactionException("push savepoint in saveStack or create savepoint fail!",
							e);
				}
			}
		}

		private void openTransaction(DataSource dataSource) {
			logger.trace("get a new connection for dataSource:{}",dataSource);
			Connection con = null;
			try {
				con = dataSource.getConnection();
				boolean order = false;
				if(orderSubmit == ORDER_SUBMIT_DYNAMIC){
					order = TransactionExtensionConfig.isOrderSubmit();
				} else {
					order = orderSubmit == ORDER_SUBMIT_ALWAYS;
				}
				
				if(order){
					con = OrderedSubmitUtils.getOrderedProxy(dataSource, con);
				}
				if(TransactionExtensionConfig.isrRecordLog()){
					con = SqlLogConnection.newInstance(dataSource, con);
				}
			} catch (Exception e) {
				throw new DALException("can't get connection from dataSource ", e);
			}
			
			TransactionConnectionHolder conHolder = new TransactionConnectionHolder(con);
			try {
				Integer previousIsolationLevel = DataSourceUtils.prepareConnectionForTransaction(con, definition);
				conHolder.setPreviousIsolationLevel(previousIsolationLevel);
				conHolder.setTimeoutInMillis(definition.getTimeout());
				holderResources.put(dataSource, conHolder);
				if (con.getAutoCommit()) {
					conHolder.setMustRestoreAutoCommit(true);
					if (logger.isDebugEnabled()) {
						logger.debug("Switching JDBC Connection [" + con + "] to manual commit");
					}
					con.setAutoCommit(false);
				}
				TransactionSynchronizationManager.bindResource(dataSource, conHolder);
			} catch (Exception e) {
				DataSourceUtils.releaseConnection(con, dataSource);
				throw new DALException("prepare a new connection for dataSource faild! ", e);
			}
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
			TransactionHolder holder = (TransactionHolder) TransactionSynchronizationManager.getResource(DYNAMIC_KEY);
			if (holder != this) {
				return holder.createSavepoint();
			}
			if (conSavePoint == null) {
				conSavePoint = new HashMap<>();
			}
			for (Entry<DataSource, TransactionConnectionHolder> holdermap : holderResources.entrySet()) {
				TransactionConnectionHolder conHolder = holdermap.getValue();
				if (!conSavePoint.containsKey(conHolder)) {
					Stack<Object> savePoint = new Stack<>();
					try {
						savePoint.push(conHolder.createSavepoint());
					} catch (SQLException e) {
						throw new CannotCreateTransactionException(
								"push savepoint in saveStack or create savepoint fail!", e);
					}
					conSavePoint.put(conHolder, savePoint);
				}
			}

			return Integer.valueOf(++nestedLevel);
		}

		@Override
		public void rollbackToSavepoint(Object savepoint) throws TransactionException {
			TransactionHolder holder = (TransactionHolder) TransactionSynchronizationManager.getResource(DYNAMIC_KEY);
			if (holder != this) {
				holder.rollbackToSavepoint(savepoint);
				return;
			}

			if (!savepoint.equals(nestedLevel)) {
				throw new UnexpectedTransactionException("unexpected nested transaction. nested level:" + nestedLevel);
			}

			for (Entry<TransactionConnectionHolder, Stack<Object>> entry : conSavePoint.entrySet()) {
				TransactionConnectionHolder conHolder = entry.getKey();
				Stack<Object> savepoints = entry.getValue();
				if (savepoints.size() == nestedLevel) {
					try {
						conHolder.getConnection().rollback((Savepoint) savepoints.pop());
					} catch (SQLException e) {
						throw new TransactionSystemException("Could not roll back to JDBC savepoint", e);
					}
				}
			}
			nestedLevel--;
		}

		@Override
		public void releaseSavepoint(Object savepoint) throws TransactionException {
			TransactionHolder holder = (TransactionHolder) TransactionSynchronizationManager.getResource(DYNAMIC_KEY);
			if (holder != this) {
				holder.releaseSavepoint(savepoint);
				return;
			}

			if (!savepoint.equals(nestedLevel)) {
				throw new UnexpectedTransactionException("unexpected nested transaction. nested level:" + nestedLevel);
			}

			for (Entry<TransactionConnectionHolder, Stack<Object>> entry : conSavePoint.entrySet()) {
				TransactionConnectionHolder conHolder = entry.getKey();
				Stack<Object> savepoints = entry.getValue();
				if (savepoints.size() == nestedLevel) {
					try {
						conHolder.getConnection().releaseSavepoint((Savepoint) savepoints.pop());
					} catch (SQLException e) {
						logger.warn(conHolder + "release to Savepoint fail!", e);
					}
				}

			}
			nestedLevel--;
		}

		public void setOrderSubmit(int orderSubmit) {
			this.orderSubmit = orderSubmit;
		}
	}

	public int getOrderSubmit() {
		return orderSubmit;
	}

	public void setOrderSubmit(int orderSubmit) {
		this.orderSubmit = orderSubmit;
	}
}
