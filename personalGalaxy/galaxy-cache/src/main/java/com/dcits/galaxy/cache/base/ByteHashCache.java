package com.dcits.galaxy.cache.base;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.cache.api.ICacheStore;
import com.dcits.galaxy.cache.api.ITempCache;
import com.dcits.galaxy.cache.exception.CacheException;
import com.dcits.galaxy.core.serializer.SerializationUtils;

/**
 * 封装 存储 中间数据-Hash<k,v> k/v 为byte格式
 * 
 * @author tangxlf
 * @date 2015-1-8
 * 
 */
public class ByteHashCache implements ITempCache, ICacheStore {

	private static final Logger log = LoggerFactory.getLogger(ByteHashCache.class);

	/**
	 * 返回cKey对应的Map
	 * @param CKey cKey
	 * @return Map 若内存无值 返回空map
	 */
	public static Map<String, Object> getMap(CKey cKey) {
		log.debug("********start getMap*******");
		
		Map<String, Object> dataMap = null;
		try {
			log.debug("cKey=" + cKey.createCkey());
			dataMap = redisTemplate.hgetAll(cKey.createCkey());
		} catch (Throwable t) {
			log.error(t.getMessage());
			throw new CacheException(t.getMessage());
		}
		log.debug("********end getMap********");
		return dataMap;
	}
	
	/**
	 * 返回cKey对应的Map
	 * @param CKey cKey
	 * @return Map 若内存无值 返回空map
	 */
	public static Map<String, Object> getMapByKey(String cacheKey) {
		log.debug("********start getMapByKey*******");
		
		Map<String, Object> dataMap = null;
		try {
			log.debug("cacheKey=" + cacheKey);
			dataMap = redisTemplate.hgetAll(cacheKey);
		} catch (Throwable t) {
			log.error(t.getMessage());
			throw new CacheException(t.getMessage());
		}
		log.debug("********end getMapByKey********");
		return dataMap;
	}

	/**
	 * 返回cKey dataK对应的 value
	 * @param CKey     cKey
	 * @param String   dataK
	 * @return String 若内存无值 返回null
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getDataValue(CKey cKey, String dataK) {
		log.debug("********start getDataValue*******");
		
		T dataV = null;
		try {
			log.debug("cKey=" + cKey.createCkey() + " dataK=" + dataK);
			dataV = (T) redisTemplate.hget(cKey.createCkey(), dataK);
		} catch (Throwable t) {
			log.error(t.getMessage());
			throw new CacheException(t.getMessage());
		}
		log.debug("********end getDataValue********");
		return dataV;
	}
	
	/**
	 * 返回缓存cKey中的字段dataK的数据
	 * 
	 * @param cKey
	 * @param dataK
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getDataValue(CKey cKey, Object dataK) {
		log.debug("********start getDataValue*******");
		
		T dataV = null;
		try {
			log.debug("cKey=" + cKey.createCkey() + " dataK=" + dataK);
			byte[] key = SerializationUtils.serialize(cKey.createCkey());
			byte[] byteVal = redisTemplate.hget(key,SerializationUtils.serializeObj(dataK));
			dataV = (T)SerializationUtils.deserializeObj(byteVal);
		} catch (Throwable t) {
			log.error(t.getMessage());
			throw new CacheException(t.getMessage());
		}
		log.debug("********end getDataValue********");
		return dataV;
	}


	/**
	 * 在内存中创建 cKey缓存 同一cKey 多次调用该方法，会把把新MAP加入进去 如有相同k 会替换	 * 
	 * @param CKey           cKey
	 * @param Map	         dataMap
	 * @param int expirePeriod 在内存中保存的有效期限 单位为秒 可输入定制好的     FOREVER_EXPIRE = -1;  //永远
	 *                                                                     MONTH_EXPIRE = 30*24*3600; //一月 
	 *                                                                     WEEK_EXPIRE = 7*24*3600; //一周
	 *                                                                     DAY_EXPIRE = 1*12*3600; //12小时
	 */
	public static void addMap(CKey cKey, Map<String, Object> dataMap,int expirePeriod) {
		log.debug("********start addMap*******");
		
		try {
			log.debug("cKey=" + cKey.createCkey());
			log.debug("dataMap=" + dataMap);
			redisTemplate.hmset(cKey.createCkey(), dataMap);
			setExpire(cKey.createCkey(), expirePeriod);
		} catch (Throwable t) {
			log.error(t.getMessage());
			throw new CacheException(t.getMessage());
		}
		log.debug("********end addMap********");
	}

	/**
	 * 给cKey添加一个k/v	 
	 * @param CKey     cKey
	 * @param Object   dataK
	 * @param Object   dataV
	 */
	public static void addKV(CKey cKey, Object dataK, Object dataV) {
		log.debug("********start addKV*******");
		
		try {
			log.debug("cKey=" + cKey.createCkey() + " dataK=" + dataK + " dataV=" + dataV);
			byte[] key = SerializationUtils.serialize(cKey.createCkey());
			redisTemplate.hset(key, SerializationUtils.serializeObj(dataK),SerializationUtils.serializeObj(dataV));
		} catch (Throwable t) {
			log.error(t.getMessage());
			throw new CacheException(t.getMessage());
		}
		log.debug("********end addKV********");
	}
	
	public static void addKV(CKey cKey, String dataK, Object dataV) {
		log.debug("********start addKV*******");
		
		try {
			log.debug("cKey=" + cKey.createCkey() + " dataK=" + dataK + " dataV=" + dataV);
			redisTemplate.hset(cKey.createCkey(), dataK, dataV);
		} catch (Throwable t) {
			log.error(t.getMessage());
			throw new CacheException(t.getMessage());
		}
		log.debug("********end addKV********");
	}

	/**
	 * 删除K/V	  
	 * @param CKey     cKey
	 * @param Object   dataK
	 */
	public static void deleteKV(CKey cKey, Object dataK) {
		log.debug("********start deleteKV*******");
		
		try {
			log.debug("cKey=" + cKey.createCkey() + " dataK=" + dataK);
			byte[] key = SerializationUtils.serialize(cKey.createCkey());
			redisTemplate.hremove(key, SerializationUtils.serializeObj(dataK));
		} catch (Throwable t) {
			log.error(t.getMessage());
			throw new CacheException(t.getMessage());
		}
		log.debug("********end deleteKV********");
	}

	/**
	 * 删除缓存	  
	 * @param CKey    cKey
	 */
	public static void deleteCache(CKey cKey) {
		log.debug("********start deleteCache*******");
		
		try {
			log.debug("cKey=" + cKey.createCkey());
			redisTemplate.del(cKey.createCkey());
		} catch (Throwable t) {
			log.error(t.getMessage());
			throw new CacheException(t.getMessage());
		}
		log.debug("********end deleteCache********");
	}


	/**
	 * 修改过期时间
	 * @param CKey cKey
	 * @param int  expirePeriod 在内存中保存的有效期限 单位为秒 可输入定制好的  FOREVER_EXPIRE = -1;//永远 
	 *                                                                     MONTH_EXPIRE = 30*24*3600; //一月 
	 *                                                                     WEEK_EXPIRE = 7*24*3600; //一周
	 *                                                                     DAY_EXPIRE = 1*12*3600; //12小时
	 */
	public static void updateExpire(CKey cKey, int expirePeriod) {
		log.debug("********start updateExpire*******");
		String key = cKey.createCkey();
		try {
			log.debug("cKey=" + key);
			if (redisTemplate.exists(key)) {
				if (expirePeriod != -1) {
					setExpire(key, expirePeriod);
				} else {
					byte[] keyB = SerializationUtils.serialize(cKey.createCkey());
					redisTemplate.hmset(keyB,redisTemplate.hgetAll(keyB));
				}
			}
		} catch (Throwable t) {
			log.error(t.getMessage());
			throw new CacheException(t.getMessage());
		}
		log.debug("********end updateExpire********");
	}

	/**
	 * 缓存中是否存在cKey
	 * @param CKey   cKey
	 * @return boolean true:存在 false:不存在
	 */
	public static boolean isExistCKey(CKey cKey) {
		log.debug("********start isExistCKey*******");
		
		boolean isExist = false;
		try {
			log.debug("cKey=" + cKey.createCkey());
			isExist = redisTemplate.exists(cKey.createCkey());
		} catch (Throwable t) {
			log.error(t.getMessage());
			throw new CacheException(t.getMessage());
		}
		log.debug("********end isExistCKey********");
		return isExist;
	}

	/**
	 * 设置过期时间
	 * @param CKey             cKey
	 * @param ShardedSentientlJedis     resource
	 * @param int expirePeriod
	 */
	private static void setExpire(String cKey, int expirePeriod) {
		if (expirePeriod != FOREVER_EXPIRE) {
			redisTemplate.expire(cKey, expirePeriod);
		}
	}
}

