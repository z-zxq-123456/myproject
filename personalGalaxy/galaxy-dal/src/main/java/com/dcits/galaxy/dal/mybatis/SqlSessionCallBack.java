package com.dcits.galaxy.dal.mybatis;

import org.apache.ibatis.session.SqlSession;

public interface SqlSessionCallBack {
	
	<T> T execute( SqlSession sqlSession);
}
