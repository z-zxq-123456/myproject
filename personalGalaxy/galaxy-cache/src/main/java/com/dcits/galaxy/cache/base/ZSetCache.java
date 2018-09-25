package com.dcits.galaxy.cache.base;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.cache.api.ICacheStore;
import com.dcits.galaxy.cache.api.ITempCache;
import com.dcits.galaxy.cache.exception.CacheException;

public class ZSetCache implements ITempCache,ICacheStore{
	private static final Logger log = LoggerFactory.getLogger(ZSetCache.class); 
	
	/**
     * 返回cKey对应的value
     * @param CKey cKey   
     * @return Set  若内存无值 返回空Set
     */ 
	@SuppressWarnings("unchecked")
	public static  Set<String> getValue(CKey cKey) {
		
		Set<String>  dataSet = null;
		try {
			dataSet = redisTemplate.zrange(cKey.createCkey(), 0, -1);
		} catch (Throwable t) {					
			throw new CacheException(t.getMessage());			
		}
		return dataSet;
	}
	
	public static  Set<String> getValueByScore(CKey cKey,double score) {
		return getValueByScore(cKey, score, score);
	}
	
	@SuppressWarnings("unchecked")
	public static  Set<String> getValueByScore(CKey cKey,double startScore,double endScore) {
		
		Set<String>  dataSet = null;
		try {
			dataSet = redisTemplate.zrangeByScore(cKey.createCkey(), startScore, endScore);
		} catch (Throwable t) {					
			throw new CacheException(t.getMessage());			
		}
		return dataSet;
	}
	
	@SuppressWarnings("unchecked")
	public static  Set<String> getValueByOrder(CKey cKey,int startOrder,int endOrder) {
		
		Set<String>  dataSet = null;
		try {
			dataSet = redisTemplate.zrange(cKey.createCkey(), startOrder, endOrder);
		} catch (Throwable t) {					
			throw new CacheException(t.getMessage());			
		}
		return dataSet;
	}
	
	public static  Set<String> getValueByOrder(CKey cKey, int order) {
		return getValueByOrder(cKey, order, order);
	}
	
	public static Double getScore(CKey cKey, String member){
		
		Double result = null;
		try {
			result = redisTemplate.zscore(cKey.createCkey(), member);
		} catch (Throwable t) {					
			throw new CacheException(t.getMessage());			
		}
		return result;
	}

	/**
	 * 在内存中创建 cKey缓存    给同一cKey 添加相同的dataValue只保存一个
	 * @param cKey
	 * @param score
	 * @param member
	 */
	public static void create(CKey cKey, double score, String member) {
				
		try {
			redisTemplate.zadd(cKey.createCkey(),score,member);
		} catch (Throwable t) {					
			throw new CacheException(t.getMessage());			
		}		
	}

	/**
	 * 在内存中创建 cKey缓存    给同一cKey 添加相同的dataValue只保存一个
	 * @param cKey
	 * @param scoreMembers
	 */
	public static void create(CKey cKey, Map<String,Double> scoreMembers) {
		try {
			for (Entry<String, Double> entry : scoreMembers.entrySet()) {
				redisTemplate.zadd(cKey.createCkey(), entry.getValue(), entry.getKey());
			}
		} catch (Throwable t) {					
			throw new CacheException(t.getMessage());			
		} 		
	}	
	
	/**
     * 判断cKey是否已设置缓存时间
     * @param CKey cKey   
     * @param boolean     
     */
	public static boolean isSetExpire(CKey cKey){
				
		try {
			return redisTemplate.ttl(cKey.createCkey())!=-1;	
		} catch (Throwable t) {					
			throw new CacheException(t.getMessage());			
		}		
	}
	/**
     * 删除缓存cKey 对应Set中的值
     * @param CKey cKey        
     */ 
	public static void deleteCacheValue(CKey cKey,String dataValue) {
				
		try {
						
			redisTemplate.zrem(cKey.createCkey(), dataValue);
		} catch (Throwable t) {					
			throw new CacheException(t.getMessage());			
		}	
	} 
	
	/**
     * 删除缓存
     * @param CKey cKey        
     */ 
	public static void deleteCache(CKey cKey) {
				
		try {
						
			redisTemplate.del(cKey.createCkey());
		} catch (Throwable t) {					
			throw new CacheException(t.getMessage());			
		}	
	} 
	
	/**
     * 获取cKey 对应Set的数量
     * @param CKey cKey  
     * @return int cKey 对应Set的数量
     */ 
	public static int getSetSize(CKey cKey) {
				
		int size = 0;
		try {
			
			size = Integer.valueOf(redisTemplate.zcard(cKey.createCkey()).toString());
		} catch (Throwable t) {				
			log.error(t.getMessage());					
		}
		return size;
	} 
	/**
     * 修改过期时间
     * @param CKey cKey   
     * @param int expirePeriod 在内存中保存的有效期限 单位为秒 可输入定制好的     FOREVER_EXPIRE = -1;       //永远
     *                                                                     MONTH_EXPIRE = 30*24*3600; //一月
     *                                                                     WEEK_EXPIRE = 7*24*3600;   //一周
	 *                                                                     DAY_EXPIRE = 1*12*3600;   //12小时    
     */ 
	public static void updateExpire(CKey cKey,int expirePeriod) {
		try {
			String key = cKey.createCkey();
			if(redisTemplate.exists(key)){
			   if(expirePeriod!=-1){				
				    setExpire(key, expirePeriod);
			   } else {
				   //TODO
			   }
			}
		} catch (Throwable t) {					
			throw new CacheException(t.getMessage());			
		}	
	} 
	
	/**
     * 缓存中是否存在cKey
     * @param CKey cKey   
     * @return boolean  true:存在  false:不存在    
     */ 
	public static boolean isExistCKey(CKey cKey) {
			
		boolean  isExist = false;
		try {
			isExist = redisTemplate.exists(cKey.createCkey());
		} catch (Throwable t) {					
			throw new CacheException(t.getMessage());			
		}
		return isExist;
	} 
	
	/**
     * 设置过期时间
     * @param CKey cKey   
     * @param ShardedSentientlJedis redisTemplate 
     * @param int expirePeriod 
     */ 
	private static void setExpire(String cKey, int expirePeriod){
		if(expirePeriod!=FOREVER_EXPIRE){
			redisTemplate.expire(cKey,expirePeriod);
		}		
	}
}
