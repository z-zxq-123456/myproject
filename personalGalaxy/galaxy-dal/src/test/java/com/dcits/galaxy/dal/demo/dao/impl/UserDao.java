package com.dcits.galaxy.dal.demo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.dcits.galaxy.dal.demo.dao.IUserDao;
import com.dcits.galaxy.dal.demo.entities.User;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;

/**
 * 切分表示例
 * @author wangyqm
 *
 */
@Repository
public class UserDao implements IUserDao {

	
	private static final String NAMESPACE = "com.dcits.galaxy.dal.demo.entities.User.";

	
	@Resource
	private ShardSqlSessionTemplate shardSqlSessionTemplate;

	@Override
	public void insert(User entity) {
		shardSqlSessionTemplate.insert(NAMESPACE + "insert", entity);
	}

	@Override
	public void delete(User entity) {
		shardSqlSessionTemplate.delete(NAMESPACE + "delete", entity);
	}

	@Override
	public void update(User entity) {
		shardSqlSessionTemplate.update(NAMESPACE + "update", entity);
	}

	@Override
	public User get(User entity) {
		return (User) shardSqlSessionTemplate.selectOne(NAMESPACE + "get", entity);
	}

	@Override
	public void insertBatch(List<User> list) {
		if (list != null) {
			shardSqlSessionTemplate.insertBatch(NAMESPACE + "insertBatch", list);
		}
	}

	@Override
	public void deleteBatch(List<User> list) {
		if (list != null) {
			for (User entity : list) {
				this.delete(entity);
			}
		}
	}

	@Override
	public void updateBatch(List<User> list) {
		if (list != null) {
			for (User entity : list) {
				this.update(entity);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> queryBatch(List<User> list) {
		List<User> result = shardSqlSessionTemplate.selectList(NAMESPACE + "queryBatch", list);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByCondition(User entity) {
		List<User> list = shardSqlSessionTemplate.selectList(NAMESPACE + "findByCondition", entity);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByPage(ParameterWrapper parameterWrapper) {
		List<User> list = shardSqlSessionTemplate.selectList(NAMESPACE + "findPage", parameterWrapper);
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