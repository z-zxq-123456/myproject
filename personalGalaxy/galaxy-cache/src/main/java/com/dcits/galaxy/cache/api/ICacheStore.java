package com.dcits.galaxy.cache.api;

import com.dcits.galaxy.cache.template.RedisTemplate;
import com.dcits.galaxy.core.spring.SpringApplicationContext;

public interface ICacheStore {
	
	public static final RedisTemplate redisTemplate = (RedisTemplate) SpringApplicationContext.getContext().getBean("redisTemplate");
}
