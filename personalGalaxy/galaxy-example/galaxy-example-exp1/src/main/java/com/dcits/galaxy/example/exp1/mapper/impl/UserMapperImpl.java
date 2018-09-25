package com.dcits.galaxy.example.exp1.mapper.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.example.exp1.mapper.TestUserMapper;
import com.dcits.galaxy.example.exp1.model.TestUser;

@Repository
public class UserMapperImpl implements TestUserMapper {
	
	private static final String NAMESPACE = "com.dcits.galaxy.example.exp1.mapper.TestUserMapper";
	
	@Resource
	private ShardSqlSessionTemplate shardSqlSessionTemplate;
	
	@Override
	public int insert(TestUser record) {
		return shardSqlSessionTemplate.insert(NAMESPACE,"insert", record);
	}

	@Override
	public int deleteByPrimaryKey(String name) {
		return shardSqlSessionTemplate.delete(NAMESPACE, "deleteByPrimaryKey", name);
	}

	@Override
	public int updateByPrimaryKey(TestUser record) {
		return shardSqlSessionTemplate.update(NAMESPACE, "updateByPrimaryKey", record);
	}

	@Override
	public TestUser selectByPrimaryKey(String name) {
		return (TestUser) shardSqlSessionTemplate.selectOne(NAMESPACE, "selectByPrimaryKey", name);
	}
}
