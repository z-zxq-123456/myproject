package com.dcits.galaxy.dal.mybatis.transaction.log;

import static org.springframework.transaction.support.TransactionSynchronizationManager.bindResource;
import static org.springframework.transaction.support.TransactionSynchronizationManager.unbindResourceIfPossible;
import static org.springframework.util.Assert.notNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dal.mybatis.transaction.TransactionExtensionConfig;


/**
 * 用于从事物上下文获取SqlLogGroupHolder
 * @author huangzhec
 */
public class SqlLogGroupHolderUtil {
	public static final Object SQL_LOG_KEY = "SQL_LOG_KEY";

	private static final Logger logger = LoggerFactory.getLogger(SqlLogGroupHolderUtil.class);

	public static SqlLogGroupHolder getSqlLogGroupHolder() {
		SqlLogGroupHolder sqlLogGroupHolder = (SqlLogGroupHolder) TransactionSynchronizationManager.getResource(SqlLogGroupHolderUtil.SQL_LOG_KEY);
		if (sqlLogGroupHolder == null) {
			sqlLogGroupHolder = new SqlLogGroupHolder();
			TransactionSynchronizationManager.registerSynchronization(new SqlLogGroupSynchronization(sqlLogGroupHolder));
			if (logger.isDebugEnabled()) {
				logger.debug("bind transaction for SqlIfnoHolder [{}]", sqlLogGroupHolder);
			}
			TransactionSynchronizationManager.bindResource(SqlLogGroupHolderUtil.SQL_LOG_KEY, sqlLogGroupHolder);
		}
		return sqlLogGroupHolder;
	}

	public static class SqlLogGroupHolder {

		private Map<DataSource, SqlLogGroup> sqlLogGroupMap = new HashMap<DataSource, SqlLogGroup>();

		public SqlLogGroup getSqlLogGroup(DataSource dataSource) {
			SqlLogGroup sqlLogGroup =  sqlLogGroupMap.get(dataSource);
			if(sqlLogGroup==null){
				sqlLogGroup = new SqlLogGroup();
				sqlLogGroup.setShardInfo(ShardInfoUtil.getShardInfo(dataSource));
				sqlLogGroupMap.put(dataSource, sqlLogGroup);
				
			}
			return sqlLogGroup;
		}

		public void putSqlLogGroup(DataSource dataSource, SqlLogGroup sqlInfoGroup) {
			sqlLogGroupMap.put(dataSource, sqlInfoGroup);
		}

		public Map<DataSource, SqlLogGroup> getSqlLogsMap() {
			return Collections.unmodifiableMap(sqlLogGroupMap);
		}

		public void clear() {
			sqlLogGroupMap.clear();
		}

	}

	private static class SqlLogGroupSynchronization extends TransactionSynchronizationAdapter {

		private SqlLogGroupHolder holder;

		public SqlLogGroupSynchronization(SqlLogGroupHolder holder) {
			notNull(holder, "Parameter 'holder' must be not null");
			this.holder = holder;
		}

		@Override
		public void suspend() {
			unbindResourceIfPossible(SqlLogGroupHolderUtil.SQL_LOG_KEY);
		}

		@Override
		public void resume() {
			bindResource(SqlLogGroupHolderUtil.SQL_LOG_KEY, this.holder);
		}

		@Override
		public void beforeCommit(boolean readOnly) {
			if (readOnly == true) {
				return;
			}
			Map<String, SqlLogRegistrar> registrarMap = SpringApplicationContext.getContext().getBeansOfType(SqlLogRegistrar.class);
			if (registrarMap.isEmpty()) {
				return;
			}
			for (SqlLogRegistrar registrar : registrarMap.values()) {
				registrar.doBeforeCommit(holder.getSqlLogsMap());
			}
		}

		@Override
		public void afterCommit() {
			unbindResourceIfPossible(SqlLogGroupHolderUtil.SQL_LOG_KEY);
			Map<String, SqlLogRegistrar> registrarMap = SpringApplicationContext.getContext().getBeansOfType(SqlLogRegistrar.class);
			if (registrarMap.isEmpty()) {
				return;
			}
			for (SqlLogRegistrar registrar : registrarMap.values()) {
				registrar.doAfterCommit(holder.getSqlLogsMap());
			}
		}
		
		@Override
		public void afterCompletion(int status) {
			unbindResourceIfPossible(SqlLogGroupHolderUtil.SQL_LOG_KEY);
			TransactionExtensionConfig.setRecordLog(true);
		}

	}

}
