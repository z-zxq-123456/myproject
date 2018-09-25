package com.dcits.galaxy.dal.mybatis.transaction.log;

import java.util.Map;

import javax.sql.DataSource;

public interface SqlLogRegistrar {
	
	public void doBeforeCommit(Map<DataSource, SqlLogGroup> sqlLogGroupMap);

	public void doAfterCommit(Map<DataSource, SqlLogGroup> sqlLogGroupMap);
}
