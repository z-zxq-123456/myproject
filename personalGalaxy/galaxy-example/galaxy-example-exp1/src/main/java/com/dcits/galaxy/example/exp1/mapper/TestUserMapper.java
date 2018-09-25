package com.dcits.galaxy.example.exp1.mapper;

import com.dcits.galaxy.example.exp1.model.TestUser;

public interface TestUserMapper {

	int insert(TestUser record);

	int deleteByPrimaryKey(String name);

	int updateByPrimaryKey(TestUser record);

	TestUser selectByPrimaryKey(String name);

}