/**
 * Title: Galaxy(Distributed service platform) 
 * Copyright:Copyright (c) 2014-2016
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.galaxy.cache.global;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;

import com.dcits.galaxy.cache.api.ITempCache;
import com.dcits.galaxy.cache.exception.CacheException;
import com.dcits.galaxy.cache.template.RedisTemplate;
import com.dcits.galaxy.core.serializer.SerializationUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;

/**
 * 基于redis格式的cache实现<br>
 * 整体逻辑：<br>
 * 1、业务缓存数据根据K/V分散存储在所有的redis节点；<br>
 * 2、put/delete时同时记录/删除当前cache与K的关系；<br>
 * 2、get时直接通过K获取； <br>
 * 3、clear时通过cache与K的关系，将所有相关的K对应的缓存进行删除；<br>
 * 
 * 注意：clear时如果缓存数量比较大时，需要的时间较长。
 * 
 * @author xuecy2
 *
 */
public class RedisCache implements Cache, InitializingBean {

	private static final Logger logger = LoggerFactory
			.getLogger(RedisCache.class);

	/**
	 * 为了区分跟具体业务的key的冲突，引入平台级的key前缀 
	 */
	private static final String KEY_PREFIX = "@G_APP_";
	
	private RedisTemplate redisTemplate = null;

	private String name;

	private byte[] setKey;

	private int period = ITempCache.FOREVER_EXPIRE;
	
	private ExceptionPolicy exceptionPolicy = ExceptionPolicy.IGNORE;
	
	private static enum ExceptionPolicy {
		IGNORE, THROW, RETRY
	}

	public RedisCache() {
	}

	public RedisCache(String name) {
		this.setName(name);
	}

	public RedisCache(String name, int period) {
		this.setName(name);
		this.period = period;
	}

	public void setName(String name) {
		if(logger.isDebugEnabled()){
			logger.debug("Create a name for the '" + name + "' of RedisCache.");
		}
		this.name = name;
		this.setKey = SerializationUtils.serialize(KEY_PREFIX + getName());
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		switch (period) {
		case 0:
			this.period = ITempCache.FOREVER_EXPIRE;
			break;
		case 1:
			this.period = ITempCache.MONTH_EXPIRE;
			break;
		case 2:
			this.period = ITempCache.WEEK_EXPIRE;
			break;
		case 3:
			this.period = ITempCache.DAY_EXPIRE;
			break;
		default:
			this.period = ITempCache.FOREVER_EXPIRE;
			break;
		}
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Object getNativeCache() {
		return null;
	}

	@Override
	public ValueWrapper get(Object key) {
		byte[] bKey = getByteKey(key);
		try {
			Object objRet = SerializationUtils.deserializeObj(redisTemplate
					.get(bKey));
			return (objRet != null ? new TwoTupleWrapper(objRet) : null);
		} catch (Throwable t) {
			handleException(t);
		}
		return null;
	}

	@Override
	public void put(Object key, Object value) {
		// 采用相对原生的调用，避免多次的resource的get和return
		byte[] bKey = getByteKey(key);
		try {
			// set缓存集合与K的关系
			redisTemplate.sadd(this.setKey, bKey);
			
			// set本身的K/V
			redisTemplate.set(bKey, SerializationUtils.serializeObj(value));
			if (this.period != ITempCache.FOREVER_EXPIRE) {
				redisTemplate.expire(bKey, this.period);
			}
		} catch (Throwable t) {
			handleException(t);
		}
	}

	@Override
	public void evict(Object key) {
		// 采用相对原生的调用，避免多次的resource的get和return
		byte[] bKey = getByteKey(key);
		try {
			// set缓存集合与K的关系
			redisTemplate.sremove(this.setKey, bKey);
			// set本身的K/V
			redisTemplate.del(bKey);
		} catch (Throwable t) {
			throw new CacheException(t.getMessage(), t);
		}
	}

	/**
	 * 保证业务key为byte[]类型，并添加前缀支持。
	 * 
	 * @param key
	 * @return
	 */
	private byte[] getByteKey(Object key) {
		byte[] bKey = SerializationUtils.serializeObj(key);
		return byteMerger(this.setKey, bKey);
	}

	@Override
	public void clear() {
		try {
			Set<byte[]> keys = redisTemplate.smembers(this.setKey);
			for (byte[] key : keys) {
				redisTemplate.del(key); // 不存在的key会被忽略
			}
			redisTemplate.del(this.setKey);
		} catch (Throwable t) {
			throw new CacheException(t.getMessage(), t);
		}
	}

	private byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
		byte[] byte_3 = new byte[byte_1.length + byte_2.length];
		System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
		System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
		return byte_3;
	}

	protected RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(redisTemplate == null){
			redisTemplate = SpringApplicationContext.getContext().getBean(RedisTemplate.class);
		}
	}
	
	public void handleException(Throwable t){
		if(exceptionPolicy == ExceptionPolicy.THROW){
			throw new CacheException(t.getMessage(), t);
		} else if(exceptionPolicy == ExceptionPolicy.IGNORE){
			if(logger.isDebugEnabled()){
				logger.debug("cache operate exception. " + t.getMessage(), t);
			} else if(logger.isWarnEnabled()){
				logger.warn("cache operate exception. " + t.getMessage());
			}
		} else {
			
		}
	}
	
	public boolean checkPlaceHolder(){
		return false;
	}
}
