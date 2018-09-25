package com.dcits.galaxy.dal.mybatis.utils;

import static org.springframework.transaction.support.TransactionSynchronizationManager.bindResource;
import static org.springframework.transaction.support.TransactionSynchronizationManager.getResource;
import static org.springframework.transaction.support.TransactionSynchronizationManager.isActualTransactionActive;
import static org.springframework.transaction.support.TransactionSynchronizationManager.unbindResourceIfPossible;
import static org.springframework.util.Assert.notNull;

import java.sql.Connection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.ResourceHolderSupport;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.dcits.galaxy.dal.mybatis.transaction.DataSourceUtils;

public abstract class SqlSessionUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(SqlSessionUtils.class);

	public static SqlSession getSession(SqlSessionFactory sessionFactory, DataSource dataSource) {
		return getSession(sessionFactory, ExecutorType.SIMPLE, dataSource);
	}

	/**
	 * 通过DataSource获取当前事务缓存的SqlSession
	 *
	 * @param sqlSessionFactory
	 * @param type
	 * @param dataSource
	 * @return
	 */
	public static SqlSession getSession(SqlSessionFactory sessionFactory, ExecutorType type, DataSource dataSource) {

		notNull(sessionFactory, "No SqlSessionFactory specified");
		notNull(type, "No ExecutorType specified");

		if(!TransactionSynchronizationManager.isSynchronizationActive()){
			return openSession(sessionFactory, type, dataSource);
		}

		SqlSessionsHolder holder = (SqlSessionsHolder) TransactionSynchronizationManager.getResource(sessionFactory);

		if(holder == null) {
			holder = new SqlSessionsHolder();
			holder.setSynchronizedWithTransaction(true);
			if (logger.isDebugEnabled()) {
				logger.debug("Regist transaction synchronization for SqlSessionHolder [{}]", holder);
			}
			TransactionSynchronizationManager.registerSynchronization(new SqlSessionSynchronization(holder, sessionFactory));
			if (logger.isDebugEnabled()) {
				logger.debug("bind transaction for SqlSessionHolder [{}]", holder);
			}
			TransactionSynchronizationManager.bindResource(sessionFactory, holder);
		}

		SqlSession sqlSession = holder.getSession(dataSource, type);

		if(sqlSession == null) {
			sqlSession = openSession(sessionFactory, type, dataSource);
			holder.putSession(dataSource, type, sqlSession);
		}

		return sqlSession;
	}

	private static SqlSession openSession(SqlSessionFactory sqlSessionFactory, ExecutorType type, DataSource dataSource){
		Connection conn = DataSourceUtils.getConnection(dataSource);
		return  sqlSessionFactory.openSession(type,conn);
	}

	public static void closeSqlSession(SqlSession session, DataSource dataSource, SqlSessionFactory sessionFactory){
		if (isSqlSessionTransactional(session, sessionFactory)) {
			logger.debug("Releasing transactional SqlSession [{}]", session);
		} else {
			logger.debug("Closing non transactional SqlSession [{}]",session);
			Connection conn = session.getConnection();
			DataSourceUtils.releaseConnection(conn, dataSource);
			session.close();
		}
	}

	public static boolean isSqlSessionTransactional(SqlSession session, SqlSessionFactory sessionFactory) {
		notNull(session, "No SqlSession specified");
		notNull(sessionFactory, "No SqlSessionFactory specified");

		SqlSessionsHolder holder = (SqlSessionsHolder) getResource(sessionFactory);

		return holder != null && holder.containsSession(session);
	}

	private static class SqlSessionsHolder extends ResourceHolderSupport {

		private Map<DataSource, Map<ExecutorType, SqlSession>> sessionMap = new HashMap<>();

		private Set<SqlSession> allSessions = new HashSet<>();

		public void putSession(DataSource ds, ExecutorType type, SqlSession session){
			Map<ExecutorType, SqlSession> sessions = sessionMap.get(ds);

			if(sessions == null) {
				sessions = new HashMap<>();
				sessionMap.put(ds, sessions);
			}

			sessions.put(type, session);
			allSessions.add(session);
		}

		public SqlSession getSession(DataSource ds, ExecutorType type){
			Map<ExecutorType, SqlSession> sessions = sessionMap.get(ds);

			if(sessions == null) {
				return null;
			}

			return sessions.get(type);
		}

		public void commit(){
			for(Map<ExecutorType, SqlSession> map : sessionMap.values()){
				for(SqlSession session : map.values()){
					session.commit();
				}
			}
		}

		public void rollback(){
			for(Map<ExecutorType, SqlSession> map : sessionMap.values()){
				for(SqlSession session : map.values()){
					session.rollback();
				}
			}
		}

		public void close(){
			for(Map<ExecutorType, SqlSession> map : sessionMap.values()){
				for(SqlSession session : map.values()){
					session.close();
				}
			}
		}

		@Override
		public void clear(){
			super.clear();
			for(Map<ExecutorType, SqlSession> map : sessionMap.values()){
				map.clear();
			}
			sessionMap.clear();
			allSessions.clear();
		}

		public boolean containsSession(SqlSession session){
			return allSessions.contains(session);
		}

	}

	private static class SqlSessionSynchronization extends TransactionSynchronizationAdapter {

		private static final Logger logger = LoggerFactory.getLogger(SqlSessionSynchronization.class);

		private SqlSessionFactory factory = null;
		private SqlSessionsHolder holder = null;

		public SqlSessionSynchronization(SqlSessionsHolder holder, SqlSessionFactory sessionFactory) {
			notNull(holder, "Parameter 'holder' must be not null");
			notNull(sessionFactory, "Parameter 'sessionFactory' must be not null");

			this.holder = holder;
			this.factory = sessionFactory;
		}

		@Override
		public void suspend() {
			unbindResourceIfPossible(this.factory);
		}

		@Override
		public void resume() {
			bindResource(this.factory, this.holder);
		}


		@Override
		public void afterCompletion(int status) {
			// Unbind the SqlSession from tx synchronization
			// Note, commit/rollback is needed to ensure 2nd level cache is properly updated
			// SpringTransaction will no-op the connection commit/rollback
			try {
				// Do not call commit unless there is really a transaction;
				// no need to commit if just tx synchronization is active but no transaction was started
				if (isActualTransactionActive()) {
					switch (status) {
					case STATUS_COMMITTED:
						if (logger.isDebugEnabled()) {
							logger.debug("Transaction synchronization committing SqlSessions. ");
						}

						holder.commit();
						break;
					case STATUS_ROLLED_BACK:
						if (logger.isDebugEnabled()) {
							logger.debug("Transaction synchronization rolling back SqlSessions.");
						}
						holder.rollback();
						break;
					default:
						if (logger.isDebugEnabled()) {
							logger.debug("Transaction synchronization ended with unknown status for SqlSessions.");
						}
					}
				}
			} finally {
				unbindResourceIfPossible(this.factory);
				try {
					if (logger.isDebugEnabled()) {
						logger.debug("Transaction synchronization closing SqlSessions .");
					}
					this.holder.close();
				} finally {
					this.holder.reset();
				}
			}
		}
		
		@Override
		public int getOrder() {
		      // order right before any Connection synchronization
		      return org.springframework.jdbc.datasource.DataSourceUtils.CONNECTION_SYNCHRONIZATION_ORDER - 1;
		}
	}
}
