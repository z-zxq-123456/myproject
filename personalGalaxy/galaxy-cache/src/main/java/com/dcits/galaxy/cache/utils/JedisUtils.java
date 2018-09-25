package com.dcits.galaxy.cache.utils;

import org.apache.ibatis.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.cache.api.ICacheStore;
import com.dcits.galaxy.cache.template.RedisTemplate;
import com.dcits.galaxy.core.serializer.SerializationUtils;

/**
 * @description
 * @version V1.0
 * @author Tim
 * @update 2014年11月18日 下午1:52:04
 * @author xuecy 对hash数据结构中的key不存在，以及value为null的情况进行容错处理
 * @update 2015年4月3日 下午4:13:04
 */

public class JedisUtils implements ICacheStore {
	
	private static final Logger logger = LoggerFactory.getLogger(JedisUtils.class);

	/**
	 * @param key
	 * @param value
	 * @description 向分布式缓存设置哈希缓存
	 * @version 1.0
	 * @author Tim
	 * @update 2014年11月14日 下午3:50:19
	 */
	public static void setResource(String key, String field, String value) {
		try {
			redisTemplate.hset(key, field, value);
		} catch (Exception e) {
			handleException("put cache exception. ", e);
		}
	}

	/**
	 * @param key
	 * @return
	 * @description 取分布式缓存设置哈希缓存
	 * @version 1.0
	 * @author Tim
	 * @update 2014年11月14日 下午3:52:18
	 */
	public static String getResource(String key, String field) {
		try {
			return (String) redisTemplate.hget(key, field);
		} catch (Exception e) {
			handleException("hget cache exception, will return null. ", e);
			return null;
		}
	}

	/**
	 * @param key
	 * @param value
	 * @description 向分布式缓存设置哈希缓存
	 * @version 1.0
	 * @author Tim
	 * @update 2014年11月14日 下午3:50:19
	 */
	public static void setObjectResource(String key, String field, Object value) {
		byte[] keyb = SerializationUtils.serialize(key);
		byte[] fieldb = SerializationUtils.serialize(field);
		byte[] valueb = SerializationUtils.serializeObj(value);
		setObjectResource(keyb, fieldb, valueb);
	}

	/**
	 * @param keyb
	 * @param fieldb
	 * @param valueb
	 * @description 向分布式缓存设置哈希缓存。
	 *   对string类型的使用SerializationUtils.serialize()进行序列化处理
	 *   对Object类型的使用SerializationUtils.serializeObj()进行序列化处理
	 * @version 1.0
	 * @author Tim
	 * @update 2014年12月11日 下午4:31:39
	 */
	public static void setObjectResource(byte[] keyb, byte[] fieldb,
			byte[] valueb) {
		try {
			redisTemplate.hset(keyb, fieldb, valueb);
		} catch (Exception e) {
			handleException("put cache exception. ", e);
		}
	}

	/**
	 * @param key
	 * @return
	 * @description 取分布式缓存设置哈希缓存
	 * @version 1.0
	 * @author Tim
	 * @param <T>
	 * @update 2014年11月14日 下午3:52:18
	 */
	public static <T> T getObjectResource(String key, String field) {
		byte[] keyb = SerializationUtils.serialize(key);
		byte[] fieldb = SerializationUtils.serialize(field);
		return getObjectResource(keyb, fieldb);
	}

	/**
	 * @param keyb
	 * @param fieldb
	 * @return
	 * @description 取分布式缓存设置哈希缓存,
	 *   对string类型的使用SerializationUtils.serialize()进行序列化处理
	 *   对Object类型的使用SerializationUtils.serializeObj()进行序列化处理
	 * @version 1.0
	 * @author Tim
	 * @update 2014年12月11日 下午4:53:06
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getObjectResource(byte[] keyb, byte[] fieldb) {
		try {
			byte[] bytes = redisTemplate.hget(keyb, fieldb);
			return (T) SerializationUtils.deserializeObj(bytes);
		} catch (Exception e) {
			handleException("get cache exception, will return null. ", e);
			return null;
		}
	}

	/**
	 * @param key
	 * @description 删除指定key的缓存，对key的value的类型不做限制
	 * @version 1.0
	 * @author Tim
	 * @update 2014年11月18日 下午1:45:35
	 */
	public static void delAllResource(String key) {
		try {
			redisTemplate.del(key);
		} catch (Exception e) {
			throw new CacheException(e.getMessage(), e);
		}
	}

	/**
	 * @param key
	 * @description 删除指定key&field的hash类型对应的数据
	 * @version 1.0
	 * @author Tim
	 * @update 2014年11月18日 下午1:45:35
	 */
	public static void delResource(String key, String field) {
		// 删除所有缓存Key的数据
		try {
			redisTemplate.hremove(key, field);
		} catch (Exception e) {
			throw new CacheException(e.getMessage(), e);
		}
	}

	/**
	 * @param keyb
	 * @param fieldb
	 * @description 删除指定key&field(byte[]类型)的hash类型对应的数据
	 *   对string类型的使用SerializationUtils.serialize()进行序列化处理
	 *   对Object类型的使用SerializationUtils.serializeObj()进行序列化处理
	 * @version 1.0
	 * @author Tim
	 * @update 2014年12月11日 下午5:11:42
	 */
	public static void delResource(byte[] keyb, byte[] fieldb) {
		// 删除所有缓存Key的数据
		try {
			redisTemplate.hremove(keyb, fieldb);
		} catch (Exception e) {
			throw new CacheException(e.getMessage(), e);
		}
	}

	/**
	 * @param key
	 * @return
	 * @description 获取计数器
	 * @version 1.0
	 * @author Tim
	 * @update 2014年11月14日 下午3:52:18
	 */
	public static long getResourceIncrBy(String key, String field) {
		return getResourceIncrBy(key, field, 1);
	}

	/**
	 * @param key
	 * @return
	 * @description 获取计数器
	 * @version 1.0
	 * @author Tim
	 * @update 2014年11月14日 下午3:52:18
	 */
	public static long getResourceIncrBy(String key, String field, long value) {
		long v = -1;
		try {
			v = redisTemplate.hincrBy(key, field, value).longValue();
		} catch (Exception e) {
			handleException("cache hincr for " + key + "." + field + " exception. ", e);
		}
		return v;
	}

	/**
	 * @param key
	 * @return
	 * @description 检查key是否存在
	 * @version 1.0
	 * @author Tim
	 * @update 2014年11月14日 下午3:52:18
	 */
	public static boolean existsKey(String key) {
		try {
			return redisTemplate.exists(key);
		} catch (Exception e) {
			handleException("cache exists for " + key  + " exception. ", e);
			return false;
		}
	}

	/**
	 * @param key
	 * @return
	 * @description 检查对象缓存key是否存在
	 * @version 1.0
	 * @author Tim
	 * @update 2014年11月14日 下午3:52:18
	 */
	public static boolean existsObjectKey(String key) {
		byte[] keyb = SerializationUtils.serialize(key);
		try {
			return redisTemplate.exists(keyb);
		} catch (Exception e) {
			handleException("cache exists for " + key  + " exception. ", e);
			return false;
		}
	}

	/**
	 * @param key
	 * @param field
	 * @return
	 * @description 检查哈希缓存中是否存在filed
	 * @version 1.0
	 * @author Tim
	 * @update 2014年11月14日 下午3:52:18
	 */
	public static boolean hexistsKey(String key, String field) {
		try {
			return redisTemplate.hcontainsKey(key, field);
		} catch (Exception e) {
			handleException("cache hexists for " + key + "." + field + " exception. ", e);
			return false;
		}
	}

	/**
	 * @param key
	 * @return
	 * @description 检查哈希缓存中是否存在filed
	 * @version 1.0
	 * @author Tim
	 * @update 2014年11月14日 下午3:52:18
	 */
	public static boolean hexistsObjectKey(String key, String field) {
		byte[] keyb = SerializationUtils.serialize(key);
		byte[] fieldb = SerializationUtils.serialize(field);
		try {
			return redisTemplate.hcontainsKey(keyb, fieldb);
		} catch (Exception e) {
			handleException("cache hexists for " + key + "." + field + " exception. ", e);
			return false;
		}
	}
	
	public static RedisTemplate getRedisTemplate(){
		return redisTemplate;
	}
	
	private static void handleException(String messagePrefix, Throwable t){
		messagePrefix = (messagePrefix == null ? "" : messagePrefix);
		if(logger.isDebugEnabled()){
			logger.debug(messagePrefix + t.getMessage(), t);
		} else if(logger.isWarnEnabled()){
			logger.warn(messagePrefix + t.getMessage());
		}
	}
}
