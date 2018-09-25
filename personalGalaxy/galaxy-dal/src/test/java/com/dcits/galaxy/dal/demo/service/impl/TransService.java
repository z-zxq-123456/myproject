package com.dcits.galaxy.dal.demo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.galaxy.dal.demo.entities.User;
import com.dcits.galaxy.dal.demo.service.ITransService;
import com.dcits.galaxy.dal.demo.service.IUserService;

@Service
public class TransService implements ITransService {
	
	@Resource
	private IUserService userService;
	
	@Override
	@Transactional
	public void testTrans(User user) {
		
		//新增，事务提交
		userService.insert(user);
		//修改
		user.setRemark("test");
		userService.update(user);
		//删除，异常，回滚
		//roleInfoService.delete(roleInfo);
	}
}
