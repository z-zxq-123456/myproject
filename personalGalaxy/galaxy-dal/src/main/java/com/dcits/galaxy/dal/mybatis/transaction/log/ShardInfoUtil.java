package com.dcits.galaxy.dal.mybatis.transaction.log;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dal.mybatis.shard.Shard;
import com.dcits.galaxy.dal.mybatis.shard.ShardManager;
import com.dcits.galaxy.dal.mybatis.transaction.log.SqlLog.ShardInfo;
/**
 * 获取指定DataSource对应的ShardInfo的工具类
 * @author huangzhec
 *
 */
@Service
public class ShardInfoUtil implements InitializingBean {
	private static Map<DataSource,ShardInfo> shardInfoMap = null;
	
	
	public static ShardInfo getShardInfo(DataSource dataSource){
		return shardInfoMap.get(dataSource);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		shardInfoMap =  new HashMap<DataSource,ShardInfo>();
		Map<String,ShardManager> shardManagerBeanMap =SpringApplicationContext.getContext().getBeansOfType(ShardManager.class);
		if(shardManagerBeanMap.isEmpty()){
			return;
		}
		for(Map.Entry<String, ShardManager> entry:shardManagerBeanMap.entrySet()){
			for(Shard shard:entry.getValue().getAllShard()){
				DataSource dataSource = shard.getDataSource();
				if(shardInfoMap.containsKey(dataSource)){
					continue;
				}else{
					ShardInfo shardInfo = new ShardInfo(entry.getKey(), shard.getId());
					shardInfoMap.put(dataSource, shardInfo);
				}
			}
		}
	}
	
}
