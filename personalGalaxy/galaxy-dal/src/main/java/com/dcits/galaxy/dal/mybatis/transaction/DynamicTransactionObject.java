package com.dcits.galaxy.dal.mybatis.transaction;

import javax.sql.DataSource;

public interface DynamicTransactionObject {
	static final Object DYNAMIC_KEY = DynamicTransactionObject.class;
	void addTransaction(DataSource dataSource);
}
