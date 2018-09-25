package com.dcits.galaxy.dal.demo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.galaxy.dal.demo.dao.IUserDao;
import com.dcits.galaxy.dal.demo.entities.User;
import com.dcits.galaxy.dal.demo.service.IUserService;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;

@Service
public class UserService implements IUserService {

	@Resource
	private IUserDao userDao;

	@Override
	@Transactional
	public void insert(User entity) {
		userDao.insert(entity);
//		int i = 1/0;
//		System.out.println( i );
	}

	@Override
	@Transactional
	public void delete(User entity) {
		userDao.delete(entity);
	}

	@Override
	@Transactional
	public void update(User entity) {
		userDao.update(entity);
	}

	@Override
	public User get(User entity) {
		return userDao.get(entity);
	}

	@Override
	@Transactional
	public void insertBatch(List<User> list) {
		userDao.insertBatch(list);
	}

	@Override
	@Transactional
	public void deleteBatch(List<User> list) {
		userDao.deleteBatch(list);
	}

	@Override
	@Transactional
	public void updateBatch(List<User> list) {
		userDao.updateBatch(list);
	}

	@Override
	public List<User> queryBatch(List<User> list) {
		return userDao.queryBatch(list);
	}

	@Override
	public List<User> findByCondition(User entity) {
		return userDao.findByCondition(entity);
	}

	@Override
	public List<User> findByPage(ParameterWrapper parameterWrapper) {
		return userDao.findByPage(parameterWrapper);
	}

	@Override
	public List<Object> query(String statement, Object parameter) {
		return userDao.query(statement, parameter);
	}
}
