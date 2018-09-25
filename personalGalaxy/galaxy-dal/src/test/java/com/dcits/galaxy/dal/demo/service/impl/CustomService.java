package com.dcits.galaxy.dal.demo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.galaxy.dal.demo.dao.ICustomDao;
import com.dcits.galaxy.dal.demo.entities.Custom;
import com.dcits.galaxy.dal.demo.service.ICustomService;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;

@Service
public class CustomService implements ICustomService{

	@Resource
	private ICustomDao customDao;
	
	@Override
	public void insert(Custom entity) {
		
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
	@Transactional
	public void insertBatch(List<Custom> list) {
		customDao.insertBatch(list);
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

	@Override
	public List<Custom> findByPage(ParameterWrapper parameterWrapper) {
		return customDao.findByPage(parameterWrapper);
	}

	@Override
	public List<Object> query(String statement, Object parameter) {
		return customDao.query(statement, parameter);
	}
}
