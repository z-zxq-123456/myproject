/**
 * Title: Galaxy(Distributed service platform) 
 * Copyright:Copyright (c) 2014-2016
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.galaxy.cache.global;

import java.util.Collection;

import org.springframework.beans.BeansException;
import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 支持事务的CacheManager
 * 
 * @author xuecy2
 *
 */
public class SimpleTransactionCacheManager extends
		AbstractTransactionSupportingCacheManager implements
		ApplicationContextAware {

	private ApplicationContext context;

	@Override
	protected Collection<? extends Cache> loadCaches() {
		// 原理如下：
		// 1、在该CacheManager实例化之前通过import的方式，先对各个子模块中的cache进行实例化；
		// 2、自动加载容器中所有的Cache接口的实现到CacheManager
		return context.getBeansOfType(Cache.class).values();
	}

	@Override
	protected Cache decorateCache(Cache cache) {
		return (isTransactionAware() ? new TransactionAwareCacheDecorator(cache)
				: cache);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext;
	}

}
