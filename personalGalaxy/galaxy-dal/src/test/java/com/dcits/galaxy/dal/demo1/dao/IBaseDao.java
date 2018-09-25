package com.dcits.galaxy.dal.demo1.dao;

import java.io.Serializable;
import java.util.List;

import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;

public interface IBaseDao<T extends Serializable> {
	// 基本操作接口
	public int insert(T entity);

	public int delete(T entity);

	public int update(T entity);

	public T get(T entity);

	// 批量操作接口
	public int insertBatch(List<T> list);

	public int deleteBatch(List<T> list);

	public int updateBatch(List<T> list);

	public List<T> queryBatch(List<T> list);

	// 条件、分页查询
	public List<T> findByCondition(T entity);

	public List<T> findByPage(ParameterWrapper parameterWrapper);
	
	public List<Object> query(String statement, Object parameter);
}
