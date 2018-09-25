package com.dcits.galaxy.cache.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.util.Pool;

import com.dcits.galaxy.cache.api.RedisSource;
import com.dcits.galaxy.cache.binary.RedisCommands;

public class JedisSource implements RedisSource {
	
	private Pool<Jedis> jedisPool = null;
	
	private String host = null;
	private int port = 6379;
	private int timeout = 20;
	
	private String passwd = null;
	
	public JedisSource(Pool<Jedis> jedisPool) {
		this.jedisPool = jedisPool;
		Jedis j = jedisPool.getResource();
		Client client = j.getClient();
		this.host = client.getHost();
		this.port = client.getPort();
		this.timeout = client.getSoTimeout();
		j.close();
	}
	
	public JedisSource(GenericObjectPoolConfig poolConfig,String host,int port,int timeout) {
		this(poolConfig, host, port, timeout, null);
	}
	
	public JedisSource(GenericObjectPoolConfig poolConfig,String host,int port,int timeout, String passwd) {
		this.host = host;
		this.port = port;
		this.timeout = timeout;
		this.passwd = passwd;
		this.jedisPool = new JedisPool(poolConfig, host, port, timeout, passwd);
	}

	public JedisSource(JedisSentinelPool sentinelPool, String host, int port, int timeout) {
		this.host = host;
		this.port = port;
		this.timeout = timeout;
		this.jedisPool = sentinelPool;
	}

	@Override
	public RedisCommands getRedis() {
		return new RedisImplJedis(this.getJedis());
	}
	
	protected Jedis getJedis(){
		return jedisPool.getResource();
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public int getTimeout() {
		return timeout;
	}
	
	public Pool<Jedis> getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(Pool<Jedis> jedisPool) {
		this.jedisPool = jedisPool;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Override
	public void close() {
		this.jedisPool.close();
	}

}
