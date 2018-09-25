package com.dcits.galaxy.dal.mybatis.router.index.config.vo;

import java.util.List;

/**
 * 
 * @author huang.zhe
 *
 */
public class InternalRule {

    private String namespace;
    private String sqlmap;
    private String shardingExpression;
    private List<String> field;
    private String shards;
    private List<String> sqlmaps;
    
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public String getSqlmap() {
		return sqlmap;
	}
	public void setSqlmap(String sqlmap) {
		this.sqlmap = sqlmap;
	}
	public String getShardingExpression() {
		return shardingExpression;
	}
	public void setShardingExpression(String shardingExpression) {
		this.shardingExpression = shardingExpression;
	}
	public String getShards() {
		return shards;
	}
	public void setShards(String shards) {
		this.shards = shards;
	}
	public List<String> getSqlmaps() {
		return sqlmaps;
	}
	public void setSqlmaps(List<String> sqlmaps) {
		this.sqlmaps = sqlmaps;
	}
	public List<String> getField() {
		return field;
	}
	public void setField(List<String> field) {
		this.field = field;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result
				+ ((namespace == null) ? 0 : namespace.hashCode());
		result = prime
				* result
				+ ((shardingExpression == null) ? 0 : shardingExpression
						.hashCode());
		result = prime * result + ((shards == null) ? 0 : shards.hashCode());
		result = prime * result + ((sqlmap == null) ? 0 : sqlmap.hashCode());
		result = prime * result + ((sqlmaps == null) ? 0 : sqlmaps.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InternalRule other = (InternalRule) obj;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		if (namespace == null) {
			if (other.namespace != null)
				return false;
		} else if (!namespace.equals(other.namespace))
			return false;
		if (shardingExpression == null) {
			if (other.shardingExpression != null)
				return false;
		} else if (!shardingExpression.equals(other.shardingExpression))
			return false;
		if (shards == null) {
			if (other.shards != null)
				return false;
		} else if (!shards.equals(other.shards))
			return false;
		if (sqlmap == null) {
			if (other.sqlmap != null)
				return false;
		} else if (!sqlmap.equals(other.sqlmap))
			return false;
		if (sqlmaps == null) {
			if (other.sqlmaps != null)
				return false;
		} else if (!sqlmaps.equals(other.sqlmaps))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("InternalRule [namespace=");
		stringBuilder.append(namespace);
		stringBuilder.append(", sqlmap=");
		stringBuilder.append(sqlmap);
		stringBuilder.append(", shardingExpression=");
		stringBuilder.append(shardingExpression);
		stringBuilder.append(",field=");
		stringBuilder.append(field);
		stringBuilder.append(", shards=");
		stringBuilder.append(shards);
		stringBuilder.append(", sqlmaps=");
		stringBuilder.append(sqlmaps);
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

    
}
