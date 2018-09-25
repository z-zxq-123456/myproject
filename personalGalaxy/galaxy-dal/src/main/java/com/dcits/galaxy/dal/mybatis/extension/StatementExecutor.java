package com.dcits.galaxy.dal.mybatis.extension;

import java.util.List;

import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.shard.Shard;
import com.dcits.galaxy.dal.mybatis.shard.ShardResultSet;

public interface StatementExecutor {
	
	public abstract Object execute(ShardSqlSessionTemplate sqlSessionTemplate, String statement, Object parameter);

	public abstract List<ShardResultSet> execute(ShardSqlSessionTemplate sqlSessionTemplate, String statement, Object parameter, List<Shard> shards);

}