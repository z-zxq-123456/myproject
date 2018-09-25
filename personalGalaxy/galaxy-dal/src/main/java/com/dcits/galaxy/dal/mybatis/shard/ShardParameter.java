package com.dcits.galaxy.dal.mybatis.shard;

import com.dcits.galaxy.dal.mybatis.plugins.RowArgs;

/**
 * 针对某个分库的SQL参数
 * @author yin.weicai
 */
public class ShardParameter {

	private String shardId;
	
	private RowArgs rowArgs = null;

	public String getShardId() {
		return shardId;
	}

	public void setShardId(String shardId) {
		this.shardId = shardId;
	}

	public RowArgs getRowArgs() {
		return rowArgs;
	}

	public void setRowArgs(RowArgs rowArgs) {
		this.rowArgs = rowArgs;
	}

	
}
