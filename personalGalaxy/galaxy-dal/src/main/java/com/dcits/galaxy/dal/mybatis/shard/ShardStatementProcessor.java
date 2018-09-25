package com.dcits.galaxy.dal.mybatis.shard;

import java.util.List;

public interface ShardStatementProcessor {
    
    List<ShardResultSet> process( List<ShardStatement> shardStatements );
}
