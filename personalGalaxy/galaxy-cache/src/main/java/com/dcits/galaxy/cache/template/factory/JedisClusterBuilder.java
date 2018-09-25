package com.dcits.galaxy.cache.template.factory;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.HostAndPort;

import com.dcits.galaxy.cache.api.RedisSource;
import com.dcits.galaxy.cache.exception.CacheException;
import com.dcits.galaxy.cache.redis.JedisClusterSource;
import com.dcits.galaxy.cache.template.RedisTemplate;
import com.dcits.galaxy.cache.template.SimpleRedisTemplate;

public class JedisClusterBuilder {

	private GenericObjectPoolConfig poolConfig;
	
	public GenericObjectPoolConfig getPoolConfig() {
		return poolConfig;
	}

	public void setPoolConfig(GenericObjectPoolConfig poolConfig) {
		this.poolConfig = poolConfig;
	}

	public RedisTemplate build(String shards,int timeout,int maxRedirections) {
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

		Set<HostAndPort> servers = new HashSet<>();
		
		for(String shard:shardArray){
			servers.add(doGetHostAndPort(shard));
		}

		RedisSource redisSource = new JedisClusterSource(servers, timeout,maxRedirections);
		redisTemplate = new SimpleRedisTemplate(redisSource);
		
		return redisTemplate;
	}
	
	protected HostAndPort doGetHostAndPort(String shard) {
		String[] server = shard.split(":");
		if (server.length != 2)
			throw new CacheException("参数不匹配！不能解析" + server);

		String host = server[0];
		int port = Integer.parseInt(server[1]);
		return new HostAndPort(host, port);
	}
	
	public void destroy() throws Exception {
	}
	
}
