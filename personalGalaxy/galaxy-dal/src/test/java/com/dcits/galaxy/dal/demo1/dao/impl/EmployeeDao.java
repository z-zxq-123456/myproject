package com.dcits.galaxy.dal.demo1.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.dcits.galaxy.dal.demo1.dao.IEmployeeDao;
import com.dcits.galaxy.dal.demo1.entities.Employee;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;
import com.dcits.galaxy.dal.mybatis.table.TableShardSqlSessionTemplate;

@Repository
public class EmployeeDao implements IEmployeeDao{

	private static final String NAMESPACE = "com.dcits.galaxy.dal.demo1.entities.Employee.";

//	@Resource
	 private TableShardSqlSessionTemplate tableShardSqlSessionTemplate;
	

	@Override
	public int insert(Employee entity) {
		return tableShardSqlSessionTemplate.insert(NAMESPACE + "insert", entity);
	}

	@Override
	public int delete(Employee entity) {
        return tableShardSqlSessionTemplate.delete(NAMESPACE + "delete", entity);
	}

	@Override
	public int update(Employee entity) {
		return tableShardSqlSessionTemplate.update(NAMESPACE + "update", entity);
	}

	@Override
	public Employee get(Employee entity) {
		return tableShardSqlSessionTemplate.selectOne(NAMESPACE + "findOne", entity);
	}

	@Override
	public int insertBatch(List<Employee> list) {
		return tableShardSqlSessionTemplate.insertBatch(NAMESPACE + "insertBatch", list);
	}

	@Override
	public int deleteBatch(List<Employee> list) {
		return tableShardSqlSessionTemplate.deleteBatch(NAMESPACE + "insertBatch",list);
	}

	@Override
	public int updateBatch(List<Employee> list) {
		int rs=0;
		if (list != null) {
			for (Employee entity : list) {
				int r=this.update(entity);
				rs=+r;
			}
		}
		return rs;
	}

	@Override
	public List<Employee> queryBatch(List<Employee> list) {
		return tableShardSqlSessionTemplate.selectList(NAMESPACE + "queryBatch", list);
	}

	@Override
	public List<Employee> findByCondition(Employee entity) {
		return tableShardSqlSessionTemplate.selectList(NAMESPACE + "findByCondition", entity);
	}

	@Override
	public List<Employee> findByPage(ParameterWrapper parameterWrapper) {
		return tableShardSqlSessionTemplate.selectList(NAMESPACE + "findPage", parameterWrapper);
	}

	@Override
	public List<Object> query(String statement, Object parameter) {
		return tableShardSqlSessionTemplate.selectList(NAMESPACE + statement, parameter);
	}
	
}
