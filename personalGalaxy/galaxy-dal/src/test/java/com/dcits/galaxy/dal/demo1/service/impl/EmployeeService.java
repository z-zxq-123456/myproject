package com.dcits.galaxy.dal.demo1.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.galaxy.dal.demo1.dao.IEmployeeDao;
import com.dcits.galaxy.dal.demo1.entities.Employee;
import com.dcits.galaxy.dal.demo1.service.IEmployeeService;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;

@Service
public class EmployeeService implements IEmployeeService {
	@Resource
	private IEmployeeDao employeeDao;

	@Override
	@Transactional
	public int insert(Employee entity) {
		return employeeDao.insert(entity);
	}

	@Override
	@Transactional
	public int delete(Employee entity) {
		return employeeDao.delete(entity);
	}

	@Override
	@Transactional
	public int update(Employee entity) {
		return employeeDao.update(entity);
	}

	@Override
	public Employee get(Employee entity) {
		return employeeDao.get(entity);
	}

	@Override
	@Transactional
	public int insertBatch(List<Employee> list) {
		return employeeDao.insertBatch(list);
	}

	@Override
	@Transactional
	public int deleteBatch(List<Employee> list) {
		return employeeDao.deleteBatch(list);
	}

	@Override
	@Transactional
	public int updateBatch(List<Employee> list) {
		return employeeDao.updateBatch(list);
	}

	@Override
	public List<Employee> queryBatch(List<Employee> list) {
		return employeeDao.queryBatch(list);
	}

	@Override
	public List<Employee> findByCondition(Employee entity) {
		return employeeDao.findByCondition(entity);
	}

	@Override
	public List<Employee> findByPage(ParameterWrapper parameterWrapper) {
		return employeeDao.findByPage(parameterWrapper);
	}

	@Override
	public List<Object> query(String statement, Object parameter) {
		return employeeDao.query(statement, parameter);
	}
	
}
