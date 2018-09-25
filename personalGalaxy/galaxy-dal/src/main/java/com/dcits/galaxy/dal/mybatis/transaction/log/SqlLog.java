package com.dcits.galaxy.dal.mybatis.transaction.log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * 用于记录每条sql以及其对应的shard信息、sql参数
 * @author huangzhec
 *
 */
public class SqlLog implements Serializable {

	private static final long serialVersionUID = 1L;
	private String sql = null;
	private LinkedList<ParameterValue> parameterValueList = new LinkedList<ParameterValue>();
	private boolean createParameterValue = true;
    
	
	public SqlLog() {
	}
	
	public SqlLog(String sql) {
		this.sql = sql;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public void addValue(Object object) {
		if (isCreateParameterValue()) {
			parameterValueList.addLast(new ParameterValue());
			createParameterValue = false;
		}
		parameterValueList.getLast().addValue(object);
	}

	public List<ParameterValue> getAllParameterValue() {
		return parameterValueList;
	}

	public boolean isCreateParameterValue() {
		return createParameterValue;
	}

	public void setCreateParameterValue(boolean createParameterValue) {
		this.createParameterValue = createParameterValue;
	}

	public static class ParameterValue implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private List<Object> values = new ArrayList<Object>();

		public void addValue(Object object) {
			values.add(object);
		}

		public List<Object> getAllValue() {
			return values;
		}

		public boolean isEmpty() {
			return values.isEmpty();
		}
	}

	public static class ShardInfo implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String shardManagerBeanId = null;
		private String shardId = null;

		public ShardInfo() {
		}

		public ShardInfo(String shardManagerBeanId, String shardId) {
			this.shardManagerBeanId = shardManagerBeanId;
			this.shardId = shardId;
		}

		public String getShardManagerBeanId() {
			return shardManagerBeanId;
		}

		public void setShardManagerBeanId(String shardManagerBeanId) {
			this.shardManagerBeanId = shardManagerBeanId;
		}

		public String getShardId() {
			return shardId;
		}

		public void setShardId(String shardId) {
			this.shardId = shardId;
		}

		@Override
		public String toString() {
			return "ShardInfo [shardManagerBeanId=" + shardManagerBeanId
					+ ", shardId=" + shardId + "]";
		}

	}

}
