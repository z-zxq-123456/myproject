package com.dcits.galaxy.cache.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

import com.dcits.galaxy.cache.binary.RedisCommands;

public class SCSSource extends JedisSource {
	
	public SCSSource(GenericObjectPoolConfig poolConfig, String host, int port,
			int timeout) {
		super(poolConfig, host, port, timeout);
	}

	public SCSSource(Pool<Jedis> jedisPool) {
		super(jedisPool);
	}

	@Override
	public RedisCommands getRedis() {
		return new SCSImpl(this.getJedis());
	}
}
