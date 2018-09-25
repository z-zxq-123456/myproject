package com.dcits.galaxy.cache.redis;

import java.io.IOException;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import com.dcits.galaxy.cache.api.RedisSource;
import com.dcits.galaxy.cache.exception.CacheException;

public class JedisClusterSource implements RedisSource {

	protected static final int DEFAULT_TIMEOUT = 2000;
	protected static final int DEFAULT_MAX_REDIRECTIONS = 5;

	private JedisCluster jedisCluster = null;
	@SuppressWarnings("unused")
	private int connectionTimeout = DEFAULT_TIMEOUT;
	private int soTimeout = DEFAULT_TIMEOUT;

	public JedisClusterSource(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}

	public JedisClusterSource(String host, int port) {
		this(new HostAndPort(host, port), DEFAULT_TIMEOUT);
	}

	public JedisClusterSource(HostAndPort node, int timeout) {
		this(node,timeout,DEFAULT_MAX_REDIRECTIONS);
	}

	public JedisClusterSource(HostAndPort node, int timeout, int maxRedirections) {
		this(node,timeout,maxRedirections,new GenericObjectPoolConfig());
	}

	public JedisClusterSource(HostAndPort node,
			final GenericObjectPoolConfig poolConfig) {
		this(node,DEFAULT_TIMEOUT,DEFAULT_MAX_REDIRECTIONS,poolConfig);
	}

	public JedisClusterSource(HostAndPort node, int timeout,
			final GenericObjectPoolConfig poolConfig) {
		this(node,timeout,DEFAULT_MAX_REDIRECTIONS,poolConfig);
	}

	public JedisClusterSource(HostAndPort node, int timeout,
			int maxRedirections, final GenericObjectPoolConfig poolConfig) {
		this(node, timeout, timeout, maxRedirections, poolConfig);
	}

	public JedisClusterSource(HostAndPort node, int connectionTimeout,	int soTimeout, int maxRedirections,
			final GenericObjectPoolConfig poolConfig) {
		this.jedisCluster = new JedisCluster(node, connectionTimeout,
				soTimeout, maxRedirections, poolConfig);
		this.soTimeout = soTimeout;
		this.connectionTimeout = connectionTimeout;
	}

	public JedisClusterSource(Set<HostAndPort> nodes) {
		this(nodes,DEFAULT_TIMEOUT,DEFAULT_MAX_REDIRECTIONS);
	}

	public JedisClusterSource(Set<HostAndPort> nodes, int timeout) {
		this(nodes,timeout,DEFAULT_MAX_REDIRECTIONS);
	}

	public JedisClusterSource(Set<HostAndPort> nodes, int timeout,
			int maxRedirections) {
		this.jedisCluster = new JedisCluster(nodes, timeout,maxRedirections);
	}

	public JedisClusterSource(Set<HostAndPort> nodes,
			final GenericObjectPoolConfig poolConfig) {
		this(nodes, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT, DEFAULT_MAX_REDIRECTIONS, poolConfig);
	}

	public JedisClusterSource(Set<HostAndPort> nodes, int timeout,
			final GenericObjectPoolConfig poolConfig) {
		this(nodes, timeout, timeout, DEFAULT_MAX_REDIRECTIONS, poolConfig);
	}

	public JedisClusterSource(Set<HostAndPort> jedisClusterNode, int timeout,
			int maxRedirections, final GenericObjectPoolConfig poolConfig) {
		this(jedisClusterNode, timeout, timeout, maxRedirections, poolConfig);
	}

	public JedisClusterSource(Set<HostAndPort> jedisClusterNode,
			int connectionTimeout, int soTimeout, int maxRedirections,
			final GenericObjectPoolConfig poolConfig) {
			this.jedisCluster = new JedisCluster(jedisClusterNode, connectionTimeout, soTimeout, maxRedirections, poolConfig);
			this.soTimeout = soTimeout;
			this.connectionTimeout = connectionTimeout;
	}

	@Override
	public RedisImplJedisCluster getRedis() {
		return new RedisImplJedisCluster(this.jedisCluster);
	}

	@Override
	public String getHost() {
		throw new CacheException("Redis Cluster can't get host!");
	}

	@Override
	public int getPort() {
		throw new CacheException("Redis Cluster can't get port!");
	}

	@Override
	public int getTimeout() {
		return soTimeout;
	}

	@Override
	public void close() throws IOException {
		this.jedisCluster.close();
	}
}
