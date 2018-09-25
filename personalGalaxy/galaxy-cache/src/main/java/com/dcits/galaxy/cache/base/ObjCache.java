package com.dcits.galaxy.cache.base;



import com.dcits.galaxy.cache.api.ICacheStore;
import com.dcits.galaxy.cache.api.ITempCache;
import com.dcits.galaxy.cache.exception.CacheException;
import com.dcits.galaxy.core.serializer.SerializationUtils;

/**
 * 封装 存储 中间数据-Object  
 * @author tangxlf
 * @date   2014-12-16
 * 
 */
public class ObjCache implements ITempCache,ICacheStore{
	/**
     * 返回cKey对应的value
     * @param CKey cKey   
     * @return Object  若内存无值 返回null
     */ 
	public static  Object getValue(CKey cKey) {
		byte[]  dataByte = null;
		try {
			dataByte = redisTemplate.get(SerializationUtils.serialize(cKey.createCkey()));
		} catch (Throwable t) {					
			throw new CacheException(t.getMessage());			
		} 	
		if(dataByte == null) return null; 
		return SerializationUtils.deserializeObj(dataByte);
	}


	/**
     * 在内存中创建 cKey缓存    同一cKey  多次调用该方法 会替换之前的object
     * @param CKey cKey   
     * @param Object obj
     * @param int expirePeriod 在内存中保存的有效期限 单位为秒 可输入定制好的     FOREVER_EXPIRE = -1;       //永远
     *                                                                     MONTH_EXPIRE = 30*24*3600; //一月
     *                                                                     WEEK_EXPIRE = 7*24*3600;   //一周
	 *                                                                     DAY_EXPIRE = 1*12*3600;   //12小时     
     */ 
	public static void create(CKey cKey, Object obj, int expirePeriod) {
		try {
			byte[] keyByte = SerializationUtils.serialize(cKey.createCkey());
			redisTemplate.set(keyByte,SerializationUtils.serializeObj(obj));
			setExpire(cKey.createCkey(), expirePeriod);
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
     * 修改过期时间
     * @param CKey cKey   
     * @param int expirePeriod 在内存中保存的有效期限 单位为秒 可输入定制好的     FOREVER_EXPIRE = -1;       //永远
     *                                                                     MONTH_EXPIRE = 30*24*3600; //一月
     *                                                                     WEEK_EXPIRE = 7*24*3600;   //一周
	 *                                                                     DAY_EXPIRE = 1*12*3600;   //12小时    
     */ 
	public static void updateExpire(CKey cKey,int expirePeriod) {
		try {
			if(redisTemplate.exists(cKey.createCkey())){
			   if(expirePeriod!=-1){				
				    setExpire(cKey.createCkey(),expirePeriod);
			   }else{
				    byte[] keyByte = SerializationUtils.serialize(cKey.createCkey());				    
				    redisTemplate.set(keyByte,redisTemplate.get(keyByte));				
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
     * @param ShardedSentientlJedis resource 
     * @param int expirePeriod 
     */ 
	private static void setExpire(String cKey,int expirePeriod){
		if(expirePeriod!=FOREVER_EXPIRE){
			redisTemplate.expire(cKey,expirePeriod);
		}		
	}
}

