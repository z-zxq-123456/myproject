package com.dcits.galaxy.cache.base;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.cache.api.ICacheStore;
import com.dcits.galaxy.cache.api.ITempCache;
import com.dcits.galaxy.cache.exception.CacheException;


/**
 * 封装 存储 中间数据-Object  
 * @author tangxlf
 * @date   2014-12-25
 * 
 */
public class SetCache implements ITempCache,ICacheStore{
	private static final Logger log = LoggerFactory.getLogger(SetCache.class); 
	
	/**
	 * 返回cKey对应的value
	 * @param CKey cKey   
	 * @return Set  若内存无值 返回空Set
	 */ 
	public static Set<String> getValue(CKey cKey) {
		
		Set<String>  dataSet = new HashSet<>();
		Set<?> set = redisTemplate.smembers(cKey.createCkey());
		for(Object obj : set) {
			dataSet.add(obj.toString());
		}
		return dataSet;
	}
	
	
	/**
	 * 在内存中创建 cKey缓存    给同一cKey 添加相同的dataValue只保存一个
	 * @param CKey cKey   
	 * @param String dataValue     
	 */ 
	public static void create(CKey cKey, String dataValue) {
		try {
			redisTemplate.sadd(cKey.createCkey(),dataValue);			
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
			redisTemplate.sremove(cKey.createCkey(), dataValue);
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
	public static long getSetSize(CKey cKey) {
		
		long size = 0;
		try {
			size = redisTemplate.ssize(cKey.createCkey());
		} catch (Throwable t) {				
			log.error(t.getMessage());					
		}
		return size;
	} 
	/**
	 * 修改过期时间
	 * @param CKey cKey   
	 * @param int expirePeriod 在内存中保存的有效期限 单位为秒 可输入定制好的<br/>
 	 *				FOREVER_EXPIRE = -1;       永远<br/>
	 *              MONTH_EXPIRE = 30*24*3600; 一月<br/>
	 *              WEEK_EXPIRE = 7*24*3600;   一周<br/>
	 *              DAY_EXPIRE = 1*12*3600;   12小时
	 */ 
	public static void updateExpire(CKey cKey,int expirePeriod) {
		
		try {
			String key = cKey.createCkey();
			if(redisTemplate.exists(key)){
				if(expirePeriod!=-1){				
					setExpire(cKey.createCkey(), expirePeriod);
				}else{	
					redisTemplate.sadd(key, redisTemplate.spop(key));
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
	 * @param int expirePeriod 
	 */ 
	private static void setExpire(String cKey,int expirePeriod){
		if(expirePeriod!=FOREVER_EXPIRE){
			redisTemplate.expire(cKey,expirePeriod);
		}
	}
}
