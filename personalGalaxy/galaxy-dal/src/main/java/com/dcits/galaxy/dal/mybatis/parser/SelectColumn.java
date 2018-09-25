package com.dcits.galaxy.dal.mybatis.parser;

public class SelectColumn {
	
	private String columnName = null;
	
	private String columnLabel = null;
	
	public SelectColumn() {
	}
	
	public SelectColumn(String columnName) {
		this(columnName, null);
	}
	
	public SelectColumn(String columnName, String columnLabel) {
		this.columnName = columnName;
		this.columnLabel = columnLabel;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnLabel() {
		return columnLabel;
	}

	public void setColumnLabel(String columnLabel) {
		this.columnLabel = columnLabel;
	}
}
