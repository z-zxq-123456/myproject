package com.dcits.galaxy.dal.mybatis.extension;

import org.apache.ibatis.mapping.MappedStatement;

import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;

public interface StatementHandler {
	public abstract void handle(ShardSqlSessionTemplate sqlSessionTemplate, MappedStatement statement);
}
