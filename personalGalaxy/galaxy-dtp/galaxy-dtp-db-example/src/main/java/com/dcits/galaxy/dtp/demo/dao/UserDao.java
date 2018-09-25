package com.dcits.galaxy.dtp.demo.dao;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dtp.demo.entities.User;

@Repository("dtpUserDao")
public class UserDao {
	
	@Resource
	ShardSqlSessionTemplate sqlSession;
	
	private static String NAMESPACE = User.class.getName();
	
	protected String getNamespace() {
		return NAMESPACE;
	}

	public User getUser(String userId) throws SQLException{
		User tmp = new User();
		tmp.setUserId(userId);
		User user = (User) sqlSession.selectOne(getNamespace(),"getUser", tmp);
		return user;
	}
	
}
