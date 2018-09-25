package com.dcits.galaxy.dal.mybatis.shard;

import java.util.List;

/**
 * 单个分库返回的结果集
 * @author yin.weicai
 */
public class ShardResultSet {

	/**
	 * 分库标识
	 */
	private String shardId;
	
	/**
	 * 基于Mybatis的结果集
	 */

	private List result ;
	
	public ShardResultSet() {
		
	}
	
	public ShardResultSet(String shardId) {
		this.shardId = shardId;
	}
	
	public ShardResultSet(String shardId, List result) {
		this.shardId = shardId;
		this.result = result;
	}
	
	public String getShardId() {
		return shardId;
	}
	
	public void setShardId(String shardId) {
		this.shardId = shardId;
	}
	
	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}
}
