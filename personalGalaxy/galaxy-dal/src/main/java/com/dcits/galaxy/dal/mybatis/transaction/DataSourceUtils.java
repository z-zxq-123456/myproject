package com.dcits.galaxy.dal.mybatis.transaction;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class DataSourceUtils {

	private static final Logger logger = LoggerFactory.getLogger(DataSourceUtils.class);

	public static Connection getConnection(DataSource dataSource) {
		try {
			Connection connection = doGetConnection(dataSource);
			return connection;
		} catch (SQLException ex) {
			throw new CannotGetJdbcConnectionException("Could not get JDBC Connection", ex);
		}
	}

	public static Connection doGetConnection(DataSource dataSource) throws SQLException {

		DynamicTransactionObject object = (DynamicTransactionObject) TransactionSynchronizationManager.getResource(DynamicTransactionObject.DYNAMIC_KEY);
		if(object != null){
			object.addTransaction(dataSource);
		}

		Connection connection = org.springframework.jdbc.datasource.DataSourceUtils.doGetConnection(dataSource);
		return connection;
	}

	public static void releaseConnection(Connection con, DataSource dataSource) {
		try {
			doReleaseConnection(con, dataSource);
		} catch (SQLException ex) {
			logger.debug("Could not close JDBC Connection", ex);
		} catch (Throwable ex) {
			logger.debug("Unexpected exception on closing JDBC Connection", ex);
		}
	}

	public static void doReleaseConnection(Connection con, DataSource dataSource) throws SQLException {
		org.springframework.jdbc.datasource.DataSourceUtils.doReleaseConnection(con, dataSource);
	}
	
	public static boolean isSynchronizationActive(){
		return TransactionSynchronizationManager.isActualTransactionActive() && TransactionSynchronizationManager.isSynchronizationActive();
	}
}
