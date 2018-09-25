package com.dcits.galaxy.cache.utils;

import com.dcits.galaxy.cache.exception.CacheException;

/**
 * 缓存全局参数表工具类
 * @author tangxlf
 * @date   2015-01-19
 * 
 */
public class ParaCacheUtil {
	  
	  /**
	   * 生成  是否已缓存表配置信息的cKey
	   * @param Object obj  待检查的    cKey 中的tableName 或者 主键值		
	   */		
	  public static void  checkIsNull(Object obj){
			if(obj==null||(""+obj).trim().equals("")){
				throw new CacheException("tableName或者主键信息不全!"); 
			}
	  }
	  
	  /**
	   * 分割主键串
	   * @param String pkStr   主键串
	   * @return String[]      主键数组
	   */
      public static  String[] splitPkNames(String pkStr){
    	   if(pkStr==null){
    	       throw new CacheException("主键信息不全!"); 
    	   }
		   return pkStr.split(",");
	  }	  

}
