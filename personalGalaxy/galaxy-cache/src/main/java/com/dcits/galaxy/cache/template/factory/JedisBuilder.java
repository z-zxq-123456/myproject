package com.dcits.galaxy.cache.template.factory;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.cache.api.RedisSource;
import com.dcits.galaxy.cache.api.Route;
import com.dcits.galaxy.cache.exception.CacheException;
import com.dcits.galaxy.cache.redis.JedisSource;
import com.dcits.galaxy.cache.shard.ShardedRedisTemplate;
import com.dcits.galaxy.cache.shard.route.MD5HashingRoute;
import com.dcits.galaxy.cache.template.RedisTemplate;
import com.dcits.galaxy.cache.template.SimpleRedisTemplate;

public class JedisBuilder {

	private boolean forceSharded = false;
	
	private GenericObjectPoolConfig poolConfig;
	
	public GenericObjectPoolConfig getPoolConfig() {
		return poolConfig;
	}

	public void setPoolConfig(GenericObjectPoolConfig poolConfig) {
		this.poolConfig = poolConfig;
	}

	private boolean isForceSharded() {
		return forceSharded;
	}

	public void setForceSharded(boolean forceSharded) {
		this.forceSharded = forceSharded;
	}

	public RedisTemplate build(String shards) {
		if ("".equals(shards)) {
			throw new CacheException(
					"Redis server configuration information is empty.");
		}

		RedisTemplate redisTemplate = null;

		String[] shardArray = shards.split(";");

		if (shardArray.length == 0) {
			throw new CacheException(
					"Redis server configuration information is empty.");
		}

		Map<RedisSource, Integer> redisSources = getRedisSourceMap(shardArray);

		if (redisSources.size() == 1 && !isForceSharded()) {
			RedisSource redisSource = (RedisSource) redisSources.keySet()
					.toArray()[0];
			redisTemplate = new SimpleRedisTemplate(redisSource);
		} else {
			Route route = buildRoute(redisSources);
			redisTemplate = new ShardedRedisTemplate(route,
					redisSources.keySet());
		}
		
		return redisTemplate;
	}
	
	protected RedisSource doGetRedisSource(String shard) {
		String[] server = shard.split(":");

		String host = server[0];
		int port = Integer.parseInt(server[1]);
		int timeout = getvalue(server[3], 2000);
		String passwd = server[4];
		if(passwd != null && (passwd.trim().isEmpty() || passwd.trim().equals("-"))){
			passwd = null;
		}
//		JedisPool pool = new JedisPool(getPoolConfig(), host, port,timeout);
		return new JedisSource(getPoolConfig(), host, port, timeout, passwd);
	}
	
	private Map<RedisSource,Integer> getRedisSourceMap(String[] shards){
		
		Map<RedisSource, Integer> redisSourceMap = new HashMap<RedisSource, Integer>();
		
		for(int i = 0; i < shards.length ;i++){
			String shardStr = shards[i];
			if(shardStr == null || shardStr.trim().isEmpty()){
				continue;
			}
			String[] server = shardStr.split(":");
			if(server.length != 5)
				throw new CacheException("参数不匹配！不能解析" + server);
			
			RedisSource redisSource = doGetRedisSource(shards[i]);
			Integer weight = Integer.parseInt(server[2]);

			redisSourceMap.put(redisSource, weight);
		}
		
		return redisSourceMap;
	}
	
	private Route buildRoute(Map<RedisSource,Integer> redisSources){
		return new MD5HashingRoute(redisSources);
	}
	
	public void destroy() throws Exception {
	}
	
	/**
	 * 判断传入的值是否为空
	 * 
	 * @param value
	 * @return
	 */
	private boolean isNull(String value) {
		if (StringUtils.isBlank(value) || value.equals("-"))
			return true;
		else
			return false;
	}
	/**
	 * 如果传入的value为空返回默认值
	 * 
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	private int getvalue(String value, int defaultValue) {
		if (isNull(value))
			return defaultValue;
		else
			return Integer.parseInt(value);
	}
}
