package com.dcits.galaxy.cache.api;

import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;

public interface IDBStore {
	public static final  ShardSqlSessionTemplate cobarSessionTemplate =(ShardSqlSessionTemplate)SpringApplicationContext.getContext().getBean("shardSqlSessionTemplate");
	
}
