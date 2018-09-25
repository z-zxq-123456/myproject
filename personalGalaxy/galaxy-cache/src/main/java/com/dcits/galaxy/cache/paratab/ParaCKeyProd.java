package com.dcits.galaxy.cache.paratab;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import com.dcits.galaxy.cache.base.CKey;
import com.dcits.galaxy.cache.utils.ParaCacheUtil;

/**
 * 缓存主键生成类
 * @author tangxlf
 * @date   2015-01-19
 * 
 */
@Component
public class ParaCKeyProd {
	  @Resource
	  private MusterParaTabCacheConfig musterParaTabCacheConfig;
	  private static final String IS_INIT ="IS_INIT";   //缓存初始化标志
	  private static final String TABLE_CACHE_tableName ="TABLE_CACHE";  //在缓存中保存对应 缓存参数表名称(tableName)是否初始化，若已经初始化不再继续初始化动作
	 /**
	  * 生成  是否已缓存表配置信息的cKey
	  * @param String tableName 缓存参数表名称    
	  * @return CKey   是否已缓存表配置信息的cKey
	  */
	  public  CKey getSingleIsInitCKey(String tableName){	
		    ParaCacheUtil.checkIsNull(tableName);
			CKey cKey = new CKey(TABLE_CACHE_tableName);
			cKey.setKey(IS_INIT,IS_INIT);
			cKey.setKey(tableName,tableName);
			return cKey;
	 }	
	 /**
      * 生成  是否已缓存表配置信息的cKey
	  * @param String tableName 缓存参数表名称
	  * @param String pkfield   主键名    
	  * @param Object pkValue   主键值
	  * @return CKey   是否已缓存表配置信息的cKey
	  */
	  public  CKey getIndexSingleKeyCKey(String tableName,String pkfield,Object pkValue){	
			ParaCacheUtil.checkIsNull(tableName);
			CKey rowCKey = new CKey(tableName);
			ParaCacheUtil.checkIsNull(pkfield);
			ParaCacheUtil.checkIsNull(pkValue);
			rowCKey.setKey("0","index");
			rowCKey.setKey("1",pkfield);	
			rowCKey.setKey("2",""+pkValue);			
			return rowCKey;
	  }	
	  /**
	   * 生成缓存表配置信息单条记录的cKey
	   * @param  String tableName  缓存参数表名称
	   * @param  Map keyMap
	   * @return CKey   表配置信息单条记录的cKey
	   */	
	  public  CKey  getCacheTableCKey(String tableName,Map<String,Object> keyMap){
		    ParaCacheUtil.checkIsNull(tableName);
			CKey rowCKey = new CKey(tableName);
			String pkStr = musterParaTabCacheConfig.getPkStrs(tableName);
			rowCKey.setKey("0","table");
			if(pkStr==null){
				return rowCKey;
			}
			String[] pks = ParaCacheUtil.splitPkNames(musterParaTabCacheConfig.getPkStrs(tableName));			
			for(int a=0;a<pks.length;a++){				
				ParaCacheUtil.checkIsNull(keyMap.get(pks[a]));
				rowCKey.setKey(pks[a],""+keyMap.get(pks[a]));
			}
			return rowCKey;
	  }
}
