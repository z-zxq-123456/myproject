package com.dcits.galaxy.dal.mybatis.proactor.page;

import com.dcits.galaxy.dal.mybatis.plugins.RowArgs;

public class ParameterWrapper {

	private Object baseParam;
	
	private Object extParam;
	
	private RowArgs rowArgs = null;
	
	@Deprecated
	private boolean isChangeSql = false;

	public Object getBaseParam() {
		return baseParam;
	}

	public void setBaseParam(Object baseParam) {
		this.baseParam = baseParam;
	}

	public Object getExtParam() {
		return extParam;
	}

	public void setExtParam(Object extParam) {
		this.extParam = extParam;
	}

	public RowArgs getRowArgs() {
		return rowArgs;
	}

	public void setRowArgs(RowArgs rowArgs) {
		this.rowArgs = rowArgs;
	}

	@Deprecated
	public boolean isChangeSql() {
		return isChangeSql;
	}

	@Deprecated
	public void setChangeSql(boolean isChangeSql) {
		this.isChangeSql = isChangeSql;
	}
	
}
