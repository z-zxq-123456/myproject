/**
 * Title: Galaxy(Distributed service platform) 
 * Copyright:Copyright (c) 2014-2016
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.galaxy.cache.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;

import com.dcits.galaxy.cache.base.ByteHashCache;
import com.dcits.galaxy.cache.base.CKey;
import com.dcits.galaxy.cache.template.RedisTemplate;
import com.dcits.galaxy.core.serializer.SerializationUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;

/**
 * 基于redis.hash格式的cache实现<br>
 * 整体逻辑：<br>
 * 1、在redis中存储的数据方式为：name/hash，其中需要缓存的K/V当做该hash中的字段；<br>
 * 2、put/get/delete时对hash的字段处理； <br>
 * 3、clear时对整个key处理
 * 
 * 注意：如果某个name中的缓存数据比较多，会造成数据分布不均。
 * 
 * @author xuecy2
 *
 */
public class RedisHashCache implements Cache, InitializingBean {

	private static final Logger logger = LoggerFactory
			.getLogger(RedisHashCache.class);
	
	private RedisTemplate redisTemplate = null;

	private String name;

	public RedisHashCache() {
	}

	public RedisHashCache(String name) {
		this.name = name;
	}

	public void setName(String name) {
		logger.debug("Create a name for the '" + name + "' of RedisHashCache.");
		this.name = name;
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
		byte[] byteResult = redisTemplate.hget(getByteKey(), SerializationUtils.serializeObj(key));
		Object value = SerializationUtils.deserializeObj(byteResult);
		return (value != null ? new TwoTupleWrapper(value) : null);
	}

	@Override
	public void put(Object key, Object value) {
		ByteHashCache.addKV(getMapKey(), key, value);
	}

	@Override
	public void evict(Object key) {
		ByteHashCache.deleteKV(getMapKey(), key);
	}

	@Override
	public void clear() {
		ByteHashCache.deleteCache(getMapKey());
	}

	/**
	 * name转CKey
	 * 
	 * @return
	 */
	private CKey getMapKey() {
		return new CKey(getName());
	}
	
	private byte[] getByteKey(){
		return null;
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

}
