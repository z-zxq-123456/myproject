package com.dcits.galaxy.cache.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.cache.api.ICacheStore;
import com.dcits.galaxy.cache.api.ITempCache;
import com.dcits.galaxy.cache.exception.CacheException;
import com.dcits.galaxy.cache.utils.RedisUtils;

/**
 * 封装 存储 中间数据-Hash<k,v>
 * 
 * @author tangxlf
 * @date 2014-12-15
 * 
 */
public class HashCache implements ITempCache, ICacheStore {

	private static final Logger log = LoggerFactory.getLogger(HashCache.class);


	protected static Map<String, String> getMap(String key) {
		Map<String, String> dataMap = new HashMap<>();
		Map<String,Object> map = redisTemplate.hgetAll(key);
		if(map == null)
			return null;
		for (Entry<String, Object> entry : map.entrySet()) {
			dataMap.put(entry.getKey(),
					RedisUtils.safeConvert(entry.getValue(), String.class));
		}
		return dataMap;
	}
	
	/**
	 * 返回cKey对应的Map
	 * @param CKey cKey
	 * @return Map 若内存无值 返回空map
	 */
	public static Map<String, String> getMap(CKey cKey) {
		log.debug("********start getMap*******");
		log.debug("cKey=" + cKey.createCkey());
		Map<String, String> dataMap;
		try {
			dataMap = getMap(cKey.createCkey());
			log.debug("********end getMap********");
		} catch (Throwable t) {
			log.error(t.getMessage());
			throw new CacheException(t.getMessage());
		}
		return dataMap;
	}
	
	/**
	 * 返回cKey dataK对应的 value	 * 
	 * @param CKey     cKey
	 * @param String   dataK
	 * @return String 若内存无值 返回null
	 */
	public static String getDataValue(CKey cKey, String dataK) {
		log.debug("********start getDataValue*******");
		log.debug("cKey=" + cKey.createCkey() + " dataK=" + dataK);
		Object dataStr = redisTemplate.hget(cKey.createCkey(), dataK);
		log.debug("********end getDataValue********");
		return dataStr.toString();
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
	public static void addMap(CKey cKey, Map<String, String> dataMap,
			int expirePeriod) {
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
	 * @param String   dataK
	 * @param String   dataV
	 */
	public static void addKV(CKey cKey, String dataK, String dataV) {
		log.debug("********start addKV*******");
		
		try {
			log.debug("cKey=" + cKey.createCkey() + " dataK=" + dataK
					+ " dataV=" + dataV);
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
	 * @param String   dataK
	 */
	public static void deleteKV(CKey cKey, String dataK) {
		log.debug("********start deleteKV*******");
		
		try {
			log.debug("cKey=" + cKey.createCkey() + " dataK=" + dataK);
			redisTemplate.hremove(cKey.createCkey(), dataK);
		} catch (Throwable t) {
			log.error(t.getMessage());
			throw new CacheException(t.getMessage());
		} 
			
				
		log.debug("********end deleteKV********");
	}

	/**
	 * 删除缓存	 * 
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
		
		try {
			
			log.debug("cKey=" + cKey.createCkey());
			if (redisTemplate.exists(cKey.createCkey())) {
				if (expirePeriod != -1) {
					setExpire(cKey.createCkey(), expirePeriod);
				} else {
					redisTemplate.hmset(cKey.createCkey(),
							redisTemplate.hgetAll(cKey.createCkey()));
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
	private static void setExpire(String key, int expirePeriod) {
		if (expirePeriod != FOREVER_EXPIRE) {
			redisTemplate.expire(key, expirePeriod);
		}
	}
}

