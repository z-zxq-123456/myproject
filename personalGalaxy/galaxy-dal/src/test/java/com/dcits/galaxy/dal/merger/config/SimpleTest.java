package com.dcits.galaxy.dal.merger.config;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dcits.galaxy.dal.demo.entities.User;
import com.dcits.galaxy.dal.demo.service.IUserService;

public class SimpleTest {

	public static void main(String[] args) {
		String locations = "classpath:META-INF/spring/*.xml";
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext( locations );
		IUserService service = (IUserService) context.getBean("userService");
		
		User user = new User();
		user.setSid( 2L );
		user.setUserName("a1");
		user.setUserName("g1");
		user.setAge(6);
		user.setRemark("ttt");
		service.insert(user);
	}
}
