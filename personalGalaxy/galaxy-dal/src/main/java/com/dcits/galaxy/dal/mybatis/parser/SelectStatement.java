package com.dcits.galaxy.dal.mybatis.parser;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;

public class SelectStatement {

	private String sql = null;

	private String selectSql = null;

	private Map<String, SelectColumn> selectColumns = new HashMap<String, SelectColumn>();

	private String fromSql = null;

	private String whereSql = null;

	private String orderBySql = null;

	private SqlNode whereNode = null;

	public SelectStatement() {
	}

	public SelectStatement(String selectSql) {
		this(selectSql, null, null, null, null);
	}

	public SelectStatement(String selectSql, String fromSql) {
		this(selectSql, fromSql, null, null, null);
	}

	public SelectStatement(String selectSql, String fromSql, String whereSql) {
		this(selectSql, fromSql, whereSql, null, null);
	}

	// public SelectStatement(String selectSql, String fromSql,
	// String whereSql, String orderBySql) {
	// this.selectSql = selectSql;
	// this.fromSql = fromSql;
	// this.whereSql = whereSql;
	// this.orderBySql = orderBySql;
	// }
	public SelectStatement(String selectSql, String fromSql, String whereSql, String orderBySql) {
		this(selectSql, fromSql, whereSql, orderBySql, null);
	}

	public SelectStatement(String selectSql, String fromSql, String whereSql, String orderBySql,MixedSqlNode whereNode) {
		this.selectSql = selectSql;
		this.fromSql = fromSql;
		this.whereSql = whereSql;
		this.orderBySql = orderBySql;
		this.whereNode = whereNode;
	}

	public String getSelectSql() {
		return selectSql;
	}

	public String getFromSql() {
		return fromSql;
	}

	public String getWhereSql() {
		return whereSql;
	}

	public String getOrderBySql() {
		return orderBySql;
	}

	public void setSelectSql(String selectSql) {
		this.selectSql = selectSql;
	}

	public void setFromSql(String fromSql) {
		this.fromSql = fromSql;
	}

	public void setWhereSql(String whereSql) {
		this.whereSql = whereSql;
	}

	public void setOrderBySql(String orderBySql) {
		this.orderBySql = orderBySql;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public void addSelectColumns(SelectColumn selectColumn) {
		if (selectColumn != null) {
			selectColumns.put(selectColumn.getColumnName(), selectColumn);
		}
	}

	public Map<String, SelectColumn> getSelectColumns() {
		return selectColumns;
	}

	public String getColumnLabel(String columnName) {
		String columnLabel = "";
		SelectColumn column = selectColumns.get(columnName);
		if (column != null) {
			columnLabel = column.getColumnLabel();
		}
		return columnLabel;
	}

	public SqlNode getWhereNode() {
		return whereNode;
	}

	public void setWhereNode(SqlNode whereNode) {
		this.whereNode = whereNode;
	}
}
