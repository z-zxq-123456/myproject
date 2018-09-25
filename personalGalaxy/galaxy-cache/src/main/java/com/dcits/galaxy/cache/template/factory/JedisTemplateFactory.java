package com.dcits.galaxy.cache.template.factory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

import com.dcits.galaxy.cache.template.RedisTemplate;

public class JedisTemplateFactory implements FactoryBean<RedisTemplate>{

	private static Logger logger = LoggerFactory.getLogger(JedisTemplateFactory.class);
	
	private enum RedisType{
		NORMAL,SENTINEL,CLUSTER
	}
	
	private final ConcurrentMap<String, RedisTemplate> redisTemplates = new ConcurrentHashMap<String, RedisTemplate>();

	private boolean forceSharded = false;
	
	private GenericObjectPoolConfig poolConfig;
	
	private RedisType type = RedisType.NORMAL;
	
	private String masters = null;
	private String sentinels = null;
	private String clusters = null;
	
	private int timeout = 2000;
	private int maxRedirections = 5;
	
	@Override
	public RedisTemplate getObject() {
		try {

			RedisTemplate redisTemplate = null;

			if (type.equals(RedisType.NORMAL)) {
				redisTemplate = redisTemplates.get(masters);
				if (redisTemplate != null) {
					return redisTemplate;
				}
				JedisBuilder builder = new JedisBuilder();
				builder.setPoolConfig(poolConfig);
				builder.setForceSharded(forceSharded);
				redisTemplate = builder.build(masters);
				redisTemplates.put(masters, redisTemplate);
			} else if (type.equals(RedisType.SENTINEL)) {
				redisTemplate = redisTemplates.get(masters);
				if (redisTemplate != null) {
					return redisTemplate;
				}
				JedisSentientlBuilder builder = new JedisSentientlBuilder();
				builder.setPoolConfig(poolConfig);
				builder.setForceSharded(forceSharded);
				redisTemplate = builder.build(masters, sentinels);
				redisTemplates.put(masters, redisTemplate);
			} else if (type.equals(RedisType.CLUSTER)) {
				redisTemplate = redisTemplates.get(clusters);
				if (redisTemplate != null) {
					return redisTemplate;
				}
				JedisClusterBuilder builder = new JedisClusterBuilder();
				builder.setPoolConfig(poolConfig);
				redisTemplate = builder.build(clusters, timeout,
						maxRedirections);
				redisTemplates.put(clusters, redisTemplate);
			}
			return redisTemplate;
		} catch (Exception e) {
			logger.error("can't instantiation RedisTemplate! reason: {}",e.getMessage());
			return null;
		}
	}

	public GenericObjectPoolConfig getPoolConfig() {
		return poolConfig;
	}

	public void setPoolConfig(GenericObjectPoolConfig poolConfig) {
		this.poolConfig = poolConfig;
	}

	public String getMasters() {
		return masters;
	}

	public void setMasters(String masters) {
		this.masters = masters;
	}

	public String getSentinels() {
		return sentinels;
	}

	public void setSentinels(String sentinels) {
		this.sentinels = sentinels;
	}

	public String getClusters() {
		return clusters;
	}

	public void setClusters(String clusters) {
		this.clusters = clusters;
	}

	public boolean isForceSharded() {
		return forceSharded;
	}

	public void setForceSharded(boolean forceSharded) {
		this.forceSharded = forceSharded;
	}

	public RedisType getType() {
		return type;
	}

	public void setType(String type) {
		type = type.toUpperCase();
		this.type = RedisType.valueOf(type);
	}

	@Override
	public Class<?> getObjectType() {
		return RedisTemplate.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(String strTimeout) {
		try{
			int timeout = Integer.parseInt(strTimeout);
			this.timeout = timeout;
		} catch(Exception e){
			
		}
	}

	public int getMaxRedirections() {
		return maxRedirections;
	}

	public void setMaxRedirections(String strMaxRedirections) {
		try{
			int maxRedirections = Integer.parseInt(strMaxRedirections);
			this.maxRedirections = maxRedirections;
		} catch(Exception e){
			
		}
	}
}
