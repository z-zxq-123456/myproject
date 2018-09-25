package com.dcits.galaxy.dtp.redis.util;

import com.dcits.galaxy.cache.base.CKey;


/**
 * 生成dtp所需的CKey的工具类
 * @author fan.kaijie
 *
 */
public class CKeyUtil {
	
	/**
	 * 生成带主键的实体key
	 * @param businessMark
	 * @param key
	 * @param value
	 * @return
	 */
	public static CKey createCKey(String businessMark,String key,String value){
		businessMark = businessMark.toUpperCase();
		key = key.toUpperCase();
		
		CKey cKey = new CKey(businessMark);
		cKey.setKey("key", key);
		cKey.setKey("value", value);
		return cKey;
	}
	
	/**
	 * 生成索引key
	 * @param businessMark
	 * @param key
	 * @param value
	 * @return
	 */
	public static CKey createIndexCKey(String businessMark,String key,String value){
		businessMark = businessMark.toUpperCase();
		key = key.toUpperCase();
		
		CKey cKey = new CKey(businessMark);
		cKey.setKey("index", "INDEX");
		cKey.setKey("key", key);
		cKey.setKey("value", value);
		return cKey;
	}
	
	/**
	 * 生成主事务key
	 * @param appGroup
	 * @param partition
	 * @return
	 */
	public static CKey createTrunkIndexCKey(String appGroup, long partition){
		CKey cKey = new CKey("TRUNK");
		cKey.setKey("index", "INDEX");
		cKey.setKey("key", "APPGROUP");
		cKey.setKey("prefix", appGroup);
		cKey.setKey("suffix", Long.toString(partition));
		return cKey;
	}
	
	private CKeyUtil(){
		
	}
}
