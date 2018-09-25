package com.dcits.galaxy.cache.redis;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.cache.api.ITempCache;
import com.dcits.galaxy.cache.base.CKey;
import com.dcits.galaxy.cache.base.ObjCache;
import com.dcits.galaxy.cache.base.SetCache;

/**
 * 封装 存储 中间数据-Object  
 * @author tangxlf
 * @date   2014-12-25
 * 
 */
public class RedisCache implements Cache {
	private static Logger log = LoggerFactory.getLogger(RedisCache.class);		
	/** The ReadWriteLock. */
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	private static final  int PERIOD = ITempCache.DAY_EXPIRE;
	
	private static final String MARK="MYBATIS_REDIS_DCFS";
	
	private String id;
	
	public RedisCache(final String id) {
		if (id == null) {
			log.error("Cache instances require an ID");
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>MybatisRedisCache:id=" + id);
		this.id = id;
	}

	@Override
	public String getId() {
		return this.id;
	}


	@Override
	public int getSize() {		
		return (int) SetCache.getSetSize(getMybatisCKey());
	}

	/**
	* 生成mybatis cKey
	* @return CKey   
    */	
	private  CKey  getMybatisCKey(){	
		CKey rowCKey = new CKey(MARK);		
		rowCKey.setKey(id,id);	
		return rowCKey;
	}
	
	/**
	* 生成mybatis cKey
	* @return CKey   
    */	
	private  CKey  getResultCKey(Object key){	
		CKey rowCKey = new CKey(""+key);			
		return rowCKey;
	}
	
	@Override
	public void putObject(Object key, Object value) {
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>putObject:" + key + "=" + value);	
		CKey resultCKey = getResultCKey(key);
		CKey myBatisCKey = getMybatisCKey();
		ObjCache.create(resultCKey,value,PERIOD);
		SetCache.create(myBatisCKey,resultCKey.createCkey());
		if(!SetCache.isSetExpire(myBatisCKey)) SetCache.updateExpire(myBatisCKey,PERIOD);
	}

	
	@Override
	public Object getObject(Object key) {				
		return ObjCache.getValue(getResultCKey(key));
	}

	
	@Override
	public Object removeObject(Object key) {		
		CKey resultCKey = getResultCKey(key);
		Object  data = ObjCache.getValue(resultCKey);
		ObjCache.deleteCache(resultCKey);
		SetCache.deleteCacheValue(getMybatisCKey(),resultCKey.createCkey());
		return data;
	}

	
	@Override
	public void clear() {
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>clear:");	
		CKey myBatisCKey = getMybatisCKey();
		Set<String> set = SetCache.getValue(myBatisCKey);
		for(String key:set){
			ObjCache.deleteCache(getResultCKey(key));
		}
		SetCache.deleteCache(myBatisCKey);
	}


	@Override
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}
	
}
