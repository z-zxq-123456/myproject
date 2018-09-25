package com.dcits.galaxy.dal.mybatis.shard;

import java.util.concurrent.ExecutorService;

import javax.sql.DataSource;

import org.apache.ibatis.session.ExecutorType;

import com.dcits.galaxy.dal.mybatis.SqlSessionCallBack;

public class ShardStatement {
	
	private String shardId;
	
	private DataSource dataSource;
	
	@Deprecated
	private ShardParameter parameter;
	
	private SqlSessionCallBack action;
	
	private ExecutorService executor;

	private ExecutorType executorType = ExecutorType.SIMPLE;
	
	public String getShardId() {
		return shardId;
	}

	public void setShardId(String shardId) {
		this.shardId = shardId;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Deprecated
	public ShardParameter getParameter() {
		return parameter;
	}

	@Deprecated
	public void setParameter(ShardParameter parameter) {
		this.parameter = parameter;
	}

	public SqlSessionCallBack getAction() {
		return action;
	}

	public void setAction(SqlSessionCallBack action) {
		this.action = action;
	}

	public ExecutorService getExecutor() {
		return executor;
	}

	public void setExecutor(ExecutorService executor) {
		this.executor = executor;
	}

	public ExecutorType getExecutorType() {
		return executorType;
	}

	public void setExecutorType(ExecutorType executorType) {
		this.executorType = executorType;
	}
	
}
