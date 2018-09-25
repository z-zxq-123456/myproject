package com.dcits.galaxy.dal.mybatis.transaction;

import java.sql.Connection;

import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.transaction.TransactionDefinition;

public class TransactionConnectionHolder extends ConnectionHolder {
	public TransactionConnectionHolder(Connection connection) {
		super(connection, true);
	}

	private Integer previousIsolationLevel = TransactionDefinition.ISOLATION_DEFAULT;
	private boolean mustRestoreAutoCommit = false;

	public Integer getPreviousIsolationLevel() {
		return previousIsolationLevel;
	}

	public void setPreviousIsolationLevel(Integer previousIsolationLevel) {
		this.previousIsolationLevel = previousIsolationLevel;
	}

	public boolean isMustRestoreAutoCommit() {
		return mustRestoreAutoCommit;
	}

	public void setMustRestoreAutoCommit(boolean mustRestoreAutoCommit) {
		this.mustRestoreAutoCommit = mustRestoreAutoCommit;
	}
	
	
}
