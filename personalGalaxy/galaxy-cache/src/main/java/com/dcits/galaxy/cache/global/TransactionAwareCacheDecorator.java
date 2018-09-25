/**
 * Title: Galaxy(Distributed service platform) 
 * Copyright:Copyright (c) 2014-2016
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.galaxy.cache.global;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.transaction.TransactionAwareCacheManagerProxy;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.Assert;

/**
 * cache的事务相关的装饰器
 *
 * @author xuecy
 * @see TransactionAwareCacheManagerProxy
 */
public class TransactionAwareCacheDecorator implements Cache {
	
	private static final Logger logger = LoggerFactory
			.getLogger(TransactionAwareCacheDecorator.class);
	
	/**
	 * 静态空对象，在运行期减少内存开销。
	 */
	private static final Object NULL_HOLDER = new NullHolder();

	private final Cache targetCache;

	/**
	 * Create a new TransactionAwareCache for the given target Cache.
	 * 
	 * @param targetCache
	 *            the target Cache to decorate
	 */
	public TransactionAwareCacheDecorator(Cache targetCache) {
		Assert.notNull(targetCache, "Target Cache must not be null");
		this.targetCache = targetCache;
	}

	@Override
	public String getName() {
		return this.targetCache.getName();
	}

	@Override
	public Object getNativeCache() {
		return this.targetCache.getNativeCache();
	}

	@Override
	public ValueWrapper get(Object key) {
		//
		if (TransactionSynchronizationManager.isSynchronizationActive()) {
			Map<Object, Object> transactionCaches = getTransactionCaches();
			if(transactionCaches.containsKey(key)){
				Object value = transactionCaches.get(key);
				// 事务过程中删除的，不使用任何缓存
				if (value == NULL_HOLDER) {
					return null;
				}
				// 事务过程中产生的缓存，优先使用，key一样的以后面的为准
				return new TwoTupleWrapper(value);
			}
		}

		// 常规处理
		ValueWrapper value = this.targetCache.get(key);
		//
		if (logger.isDebugEnabled()) {
			if (value == null) {
				logger.debug("Get the value from the cache[key= " + key
						+ ", not hit].");
			} else {
				logger.debug("Get the value from the cache[key= " + key
						+ ", hit].");
			}
		}
		//
		return value;
	}

	@Override
	public void put(final Object key, final Object value) {
		if (logger.isDebugEnabled()) {
			logger.debug("Setting value into cache[key = " + key + "].");
		}
		//
		if (TransactionSynchronizationManager.isSynchronizationActive()) {
			Map<Object, Object> transactionCaches = getTransactionCaches();
			// 将临时缓存信息跟事务bind，供get时使用，key一样的覆盖。
			transactionCaches.put(key, value);
		} else {
			this.targetCache.put(key, value);
		}
	}

	@Override
	public void evict(final Object key) {
		if (logger.isDebugEnabled()) {
			logger.debug("Delete value from cache[key = " + key + "].");
		}
		//
		if (TransactionSynchronizationManager.isSynchronizationActive()) {
			Map<Object, Object> transactionCaches = getTransactionCaches();
			// 事务中需要删除的用EvictMark的方式进行标记，在get时发现是该情况不进行任何缓存的获取。
			transactionCaches.put(key, NULL_HOLDER);
		} else {
			this.targetCache.evict(key);
		}
	}

	@Override
	public void clear() {
		this.targetCache.clear();
	}
	
	private Map<Object, Object> getTransactionCaches(){
		@SuppressWarnings("unchecked")
		Map<Object, Object> caches = (Map<Object, Object>) TransactionSynchronizationManager.getResource(targetCache);
		if(caches == null){
			caches = new HashMap<Object, Object>();
			TransactionSynchronizationManager.registerSynchronization(new CacheTransactionSynchronization(targetCache, caches));
			TransactionSynchronizationManager.bindResource(targetCache, caches);
		}
		return caches;
	}

	/**
	 * 空对象，支持序列化（有可能远程传输及存储）
	 * 
	 * @author xuecy2
	 *
	 */
	@SuppressWarnings("serial")
	private static class NullHolder implements Serializable {
	}
	
	private static class CacheTransactionSynchronization extends TransactionSynchronizationAdapter {
		
		private final Cache targetCache;
		private final Map<Object, Object> transactionCaches;
		
		public CacheTransactionSynchronization(Cache targetCache,Map<Object, Object> transactionCaches) {
			this.targetCache = targetCache;
			this.transactionCaches = transactionCaches;
		}
		
		@Override
		public void afterCommit() {
			if(transactionCaches.isEmpty()){
				return;
			}
			for(Entry<Object, Object> entry : transactionCaches.entrySet()){
				Object key = entry.getKey();
				Object value = entry.getValue();
				try {
					if(value == NULL_HOLDER){
						targetCache.evict(key);
					} else {
						targetCache.put(key, value);
					}
				} catch (Exception e) {
					if(logger.isErrorEnabled()){
						logger.error("can't update cache for {} : {}, cause:{}", new Object[]{key, value, e.getMessage()});
					}
				}
			}
		}
		
		@Override
		public void afterCompletion(int status) {
			super.afterCompletion(status);
			transactionCaches.clear();
			TransactionSynchronizationManager.unbindResourceIfPossible(targetCache);
		}
		
		@Override
		public void suspend() {
			TransactionSynchronizationManager.unbindResourceIfPossible(targetCache);
		}

		@Override
		public void resume() {
			TransactionSynchronizationManager.bindResource(targetCache, transactionCaches);
		}
	}
}
