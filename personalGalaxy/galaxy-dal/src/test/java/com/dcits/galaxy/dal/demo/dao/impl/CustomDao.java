package com.dcits.galaxy.dal.demo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.dcits.galaxy.dal.demo.dao.ICustomDao;
import com.dcits.galaxy.dal.demo.entities.Custom;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;

@Repository
public class CustomDao implements ICustomDao {

	private static final String NAMESPACE = "com.dcits.galaxy.dal.demo.entities.Custom.";

	@Resource
	private ShardSqlSessionTemplate shardSqlSessionTemplate;

	@Override
	public void insert(Custom entity) {
		shardSqlSessionTemplate.insert(NAMESPACE + "insert", entity);
	}

	@Override
	public void delete(Custom entity) {

	}

	@Override
	public void update(Custom entity) {

	}

	@Override
	public Custom get(Custom entity) {
		return null;
	}

	@Override
	public void insertBatch(List<Custom> list) {
		if (list != null) {
			for (Custom entity : list) {
				this.insert(entity);
			}
		}
	}

	@Override
	public void deleteBatch(List<Custom> list) {

	}

	@Override
	public void updateBatch(List<Custom> list) {

	}

	@Override
	public List<Custom> queryBatch(List<Custom> list) {

		return null;
	}

	@Override
	public List<Custom> findByCondition(Custom entity) {

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Custom> findByPage(ParameterWrapper parameterWrapper) {
		List<Custom> list = shardSqlSessionTemplate.selectList(NAMESPACE
				+ "findPage", parameterWrapper);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> query(String statement, Object parameter) {
		List<Object> resultList = new ArrayList<Object>();
		resultList = shardSqlSessionTemplate.selectList(NAMESPACE + statement, parameter);
		return resultList;
	}
}
