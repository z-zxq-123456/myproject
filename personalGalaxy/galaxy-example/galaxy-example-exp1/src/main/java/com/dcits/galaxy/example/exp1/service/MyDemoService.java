package com.dcits.galaxy.example.exp1.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.galaxy.example.exp1.api.DemoService;
import com.dcits.galaxy.example.exp1.api.User;
import com.dcits.galaxy.example.exp1.mapper.TestUserMapper;
import com.dcits.galaxy.example.exp1.model.TestUser;

@Service
public class MyDemoService implements DemoService {
	private static final Logger logger = LoggerFactory
			.getLogger(MyDemoService.class);

	@Resource
	TestUserMapper userMapperImpl;

	@Override
	public String sayHello(String name) {
		logger.info("sayHello...");
		return "Hello " + name;
	}

	@Override
	public User getUser(String name) {
		logger.info("getUser...");
		TestUser u = userMapperImpl.selectByPrimaryKey(name);
		User user = new User(u.getName(), u.getSex(), u.getAge());
		return user;
	}

	@Override
	@Transactional
	public void setUser(User user) {
		logger.info("setUser...");
		TestUser u = new TestUser();
		u.setName(user.getName());
		u.setSex(user.getSex());
		u.setAge(user.getAge());
		userMapperImpl.insert(u);
		System.out.println("setUser2...");
		u.setName(user.getName() + "2");
		userMapperImpl.insert(u);
		System.out.println("setuser ok.");
	}

	@Override
	public User updateUser(int age, User user) {
		logger.info("updateUser...");
		System.out.println("updateUser..");
		user.setAge(age);
		return user;
	}

}
