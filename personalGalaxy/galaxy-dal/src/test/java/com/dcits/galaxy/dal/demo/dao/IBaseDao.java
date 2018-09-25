package com.dcits.galaxy.dal.demo.dao;

import java.io.Serializable;
import java.util.List;

import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;

public interface IBaseDao<T extends Serializable> {
	// 基本操作接口
	public void insert(T entity);

	public void delete(T entity);

	public void update(T entity);

	public T get(T entity);

	// 批量操作接口
	public void insertBatch(List<T> list);

	public void deleteBatch(List<T> list);

	public void updateBatch(List<T> list);

	public List<T> queryBatch(List<T> list);

	// 条件、分页查询
	public List<T> findByCondition(T entity);

	public List<T> findByPage(ParameterWrapper parameterWrapper);
	
	public List<Object> query(String statement, Object parameter);
}
