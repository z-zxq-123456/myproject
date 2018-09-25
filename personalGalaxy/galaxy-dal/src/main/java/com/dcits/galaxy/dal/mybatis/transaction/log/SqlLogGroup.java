package com.dcits.galaxy.dal.mybatis.transaction.log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.dcits.galaxy.dal.mybatis.transaction.log.SqlLog.ShardInfo;

public class SqlLogGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	private ShardInfo shardInfo = null;

	private List<SqlLog> sqlLogs = new ArrayList<SqlLog>();

	public List<SqlLog> getAllSqlLog() {
		return sqlLogs;
	}

	public void addSqlLog(SqlLog sqlLog) {
		sqlLogs.add(sqlLog);
	}

	public ShardInfo getShardInfo() {
		return shardInfo;
	}

	public void setShardInfo(ShardInfo shardInfo) {
		this.shardInfo = shardInfo;
	}

}
