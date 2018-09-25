package com.dcits.galaxy.dal.mybatis.idempotent.dao.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.dcits.galaxy.dal.mybatis.idempotent.dao.IdempotentDao;
import com.dcits.galaxy.dal.mybatis.shard.Shard;
import com.dcits.galaxy.dal.mybatis.utils.SqlSessionUtils;

public class IdempotentDaoImpl implements IdempotentDao {
	@Resource
	SqlSessionFactory sqlSessionFactory;

	private final String statement = "com.dcits.galaxy.dal.mybatis.idempotent.";

	@Override
	public int selectCount(Object entity, Shard shard) {
		SqlSession sqlsession = getSession(shard);
		return sqlsession.selectOne(getStatement("selectCount"), entity);
	}

	@Override
	public int insert(Object entity, Shard shard) {
		SqlSession sqlsession = getSession(shard);
		return sqlsession.insert(getStatement("insert"), entity);
	}

	/**
	 * 根据DataSource获取SqlSession
	 *
	 * @return shard
	 */
	private SqlSession getSession(Shard shard) {
		return SqlSessionUtils.getSession(sqlSessionFactory, shard.getDataSource());
	}

	private String getStatement(String id) {
		return statement + id;
	}

}
