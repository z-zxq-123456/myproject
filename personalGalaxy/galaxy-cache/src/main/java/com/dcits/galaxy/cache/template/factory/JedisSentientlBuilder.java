package com.dcits.galaxy.cache.template.factory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.JedisSentinelPool;

import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.cache.api.RedisSource;
import com.dcits.galaxy.cache.api.Route;
import com.dcits.galaxy.cache.exception.CacheException;
import com.dcits.galaxy.cache.redis.JedisSource;
import com.dcits.galaxy.cache.shard.ShardedRedisTemplate;
import com.dcits.galaxy.cache.shard.route.MD5HashingRoute;
import com.dcits.galaxy.cache.template.RedisTemplate;
import com.dcits.galaxy.cache.template.SimpleRedisTemplate;

/**
 * 一致性hash扩展的redis Sentientl连接池工厂
 * 
 * @author tangxlf
 * 
 */
public class JedisSentientlBuilder {

//	private static final Logger logger = LoggerFactory.getLogger(JedisSentientlFactory.class);

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

	public RedisTemplate build(String shards,String sentinelServers) {
		if ("".equals(shards)) {
			throw new CacheException(
					"Redis server configuration information is empty.");
		}
		if ("".equals(sentinelServers)) {
			throw new CacheException(
					"Redis sentinelServers configuration information is empty.");
		}

		RedisTemplate redisTemplate = null;
		Set<String> sentinels = new HashSet<String>();

		String[] sentinelServerList = sentinelServers.split(";");
		for (int i = 0; i < sentinelServerList.length; i++) {
			String sentinel = sentinelServerList[i];
			if(sentinel != null && !sentinel.trim().isEmpty()){
				sentinels.add(sentinel.trim());
			}
		}

		Map<RedisSource, Integer> redisSourceMap = getRedisSourceMap(shards.split(";"), sentinels);

		if (redisSourceMap.size() == 1 && !isForceSharded()) {
			RedisSource redisSource = (RedisSource) redisSourceMap.keySet().toArray()[0];
			redisTemplate = new SimpleRedisTemplate(redisSource);
		} else {
			Route route = buildRoute(redisSourceMap);
			redisTemplate = new ShardedRedisTemplate(route, redisSourceMap.keySet());
		}
		return redisTemplate;
	}

	private RedisSource doGetRedisSource(String shard, Set<String> sentinels) {
			String[] server = shard.split(":");
			if(server.length != 4)
				throw new CacheException("参数不匹配！不能解析" + server);
			
			String host = server[0];
			int timeout = getvalue(server[2],2000);
			String passwd = server[3];
			if(passwd != null && (passwd.trim().isEmpty() || passwd.trim().equals("-"))){
				passwd = null;
			}
			
			return getSentinalRedis(host, sentinels, poolConfig, timeout, passwd);
	}

	private JedisSource getSentinalRedis(String masterName, Set<String> sentinels,
		      final GenericObjectPoolConfig poolConfig, final int timeout, String passwd){
		JedisSentinelPool sentinelPool = new JedisSentinelPool(masterName,
				sentinels, poolConfig, timeout, passwd);
		return new JedisSource(sentinelPool,masterName,0,timeout);
	}

	private Map<RedisSource,Integer> getRedisSourceMap(String[] shards, Set<String> sentinels){
		
		Map<RedisSource, Integer> redisSourceMap = new HashMap<RedisSource, Integer>();
		
		for(int i = 0; i < shards.length ;i++){
			String shardStr = shards[i];
			if(shardStr == null || shardStr.trim().isEmpty()){
				continue;
			}
			
			String[] server = shardStr.trim().split(":");
			if(server.length != 4)
				throw new CacheException("参数不匹配！不能解析" + server);
			
			RedisSource redisSource = doGetRedisSource(shards[i],sentinels);
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
