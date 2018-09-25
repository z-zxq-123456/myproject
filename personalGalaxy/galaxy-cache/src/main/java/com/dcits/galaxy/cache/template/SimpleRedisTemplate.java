package com.dcits.galaxy.cache.template;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.cache.api.RedisSource;
import com.dcits.galaxy.cache.binary.RedisCommands;
import com.dcits.galaxy.cache.exception.CacheException;

public class SimpleRedisTemplate extends RedisTemplate {
	
	private static final Logger logger = LoggerFactory.getLogger(SimpleRedisTemplate.class);
	
	private RedisSource redisSource = null;
	
	public SimpleRedisTemplate(){
		
	}
	
	public SimpleRedisTemplate(RedisSource redisSource) {
		this.redisSource = redisSource;
	}
	
	public void init() {
		if(redisSource == null) {
			throw new CacheException("SimpleRedisTemplate must a reidsSource !");
		}
	}
	
	@Override
	protected RedisCommands getRedis(byte[] key) {
		return redisSource.getRedis();
	}

	@Override
	public void close() throws IOException {
		try {
			redisSource.close();
		} catch (Exception e) {
			logger.warn("caught exception when close redisSource. ", e);
		}
	}
	
	@Override
	public Long del(byte[]... bKeys) {
		RedisCommands redis = null;
		try {
			redis = getRedis(null);
			return redis.del(bKeys);
		} finally {
			closeRedis(redis);
		}
	}

	public RedisSource getRedisSource() {
		return redisSource;
	}

	public void setRedisSource(RedisSource redisSource) {
		this.redisSource = redisSource;
	}
}
