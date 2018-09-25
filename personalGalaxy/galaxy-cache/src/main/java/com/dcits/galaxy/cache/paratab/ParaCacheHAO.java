package com.dcits.galaxy.cache.paratab;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.dcits.galaxy.cache.base.ByteHashCache;
import com.dcits.galaxy.cache.base.CKey;
import com.dcits.galaxy.cache.utils.ForamtMapUtil;
import com.dcits.galaxy.cache.base.ObjCache;
import com.dcits.galaxy.cache.utils.ParaCacheUtil;
import com.dcits.galaxy.cache.base.SetCache;
/**
 * 全局参数表缓存辅助访问对象-HAO辅助访问对象 
 * @author tangxlf
 * @date   2015-01-19
 * 
 */
@Repository
public class ParaCacheHAO {
	// private static final Logger log = LoggerFactory.getLogger(ParaCacheHAO.class); 
	 @Resource
	 private  MusterParaTabCacheConfig musterParaTabCacheConfig;
	 @Resource
	 private ParaCKeyProd  paraCKeyProd;
	 /**
	  * 判断该表是否初始化
	  * @param String tableName 缓存参数表名称 
	  * @retrun boolean         true 已初始化  false未初始化   
	  */ 
	 public  boolean isInit(String tableName){	
		 CKey cKey = paraCKeyProd.getSingleIsInitCKey(tableName);
		 if(!ObjCache.isExistCKey(cKey)){ 
			 int expirePeriod = musterParaTabCacheConfig.getExpirePeriod(tableName);
			 ObjCache.create(cKey,true,expirePeriod);
			 return false;
		 }
		 return true;		 			
	 }	
	 /**
	  * 初始化数据库返回的记录
	  * @param String tableName 缓存参数表名称 
	  * @param Map<String,Object> dbRowMap 数据库记录MAP	
	  */ 
	 public  void initDbRow(String tableName,Map<String,Object> dbRowMap){			 		
		 dbRowMap = ForamtMapUtil.MapToHump(dbRowMap);			
		 CKey rowCKey = paraCKeyProd.getCacheTableCKey(tableName,dbRowMap);	
		 int expirePeriod = musterParaTabCacheConfig.getExpirePeriod(tableName);
	     ByteHashCache.addMap(rowCKey,dbRowMap,expirePeriod);
	 }		 
	
	 
	 /**
	  * 按主键值分别索引数据库返回的记录
	  * @param String tableName 缓存参数表名称 
	  * @param Map<String,Object> dbRowMap 数据库记录MAP	
	  */ 
	 public  void insertSigleKeyRowIndex(String tableName,Map<String,Object> dbRowMap){				
		 String[] pks = ParaCacheUtil.splitPkNames(musterParaTabCacheConfig.getPkStrs(tableName));
		 if(pks.length==1){
			 return;
		 }
		 CKey rowCKey = paraCKeyProd.getCacheTableCKey(tableName,dbRowMap);	
		 for(int i=0;i<pks.length;i++){
			 CKey indexCkey=paraCKeyProd.getIndexSingleKeyCKey(tableName,pks[i],dbRowMap.get(pks[i]));
			 SetCache.create(indexCkey,rowCKey.createCkey());
			 if(!SetCache.isSetExpire(indexCkey))SetCache.updateExpire(indexCkey,musterParaTabCacheConfig.getExpirePeriod(tableName));
		 }			 			
	 }
	 
	 /**
	   * 获取键值对应的记录列表
	   * @param String tableName 表名称          
	   * @param String pkfield   主键名
	   * @param Object pkValue   主键值
	   * @return List<Map<String, Object>>        返回主键对应的记录列表
	   */ 
	  public List<Map<String, Object>>  getList(String tableName,String pkfield,Object pkValue){
		CKey indexCkey=paraCKeyProd.getIndexSingleKeyCKey(tableName,pkfield,pkValue);
		Set<String>  rowCKeys = SetCache.getValue(indexCkey);
		List<Map<String, Object>>  rowList = new ArrayList<Map<String, Object>>();
		for(String rowCKey:rowCKeys){
			rowList.add(ByteHashCache.getMapByKey(rowCKey));
		}
		return rowList;
	  }	  
	 /**
	  * 从缓存获取主键对应的记录
	  * @param  String tableName 缓存参数表名称          
	  * @param  Map keyMap       该行主键Map
	  * @return Map             返回主键对应的记录  
	  */ 
	 public Map<String, Object> getRow(String tableName,Map<String, Object> keyMap ){		
		 CKey rowCKey = paraCKeyProd.getCacheTableCKey(tableName,keyMap);
		 return ByteHashCache.getMap(rowCKey);
	 }	
	 /**
	  * 从缓存获取主键对应的记录
	  * @param  String tableName 缓存参数表名称          
	  * @param  Map keyMap       该行主键Map
	  * @param  String field 需要获取的字段
	  * @return T            返回主键对应记录的该字段取值  
	  */ 
	 public <T> T  getValue(String tableName,Map<String, Object> keyMap,String field){		
		 CKey rowCKey = paraCKeyProd.getCacheTableCKey(tableName,keyMap);
		 return ByteHashCache.getDataValue(rowCKey, field);
	 }	
	 /**
	  * 同步插入缓存和数据库记录
	  * @param String tableName   缓存参数表名称         
	  * @param Map<String,Object> rowMap    记录Map
	  */ 	   
	 public  void  insertRow(String tableName,Map<String,Object> rowMap){	
		CKey rowCKey = paraCKeyProd.getCacheTableCKey(tableName,rowMap);		
		int expirePeriod = musterParaTabCacheConfig.getExpirePeriod(tableName);		   		  
		ByteHashCache.addMap(rowCKey,rowMap,expirePeriod);
	 }
	 	
	 /**
	  * 同步删除缓存和数据库记录 
	  * @param String mark    缓存标志          
	  * @param Map map        该行主键Map
	  */ 	 
	 public  void  deleteRow(String tableName,Map<String,Object> keyMap){  
		CKey rowCKey = paraCKeyProd.getCacheTableCKey(tableName,keyMap);
		ByteHashCache.deleteCache(rowCKey); 
	 }
	 
	 /**
	  * 同步删除缓存和数据库记录 
	  * @param String mark    缓存标志          
	  * @param Map map        该行主键Map
	  */ 	 
	 public  void  deleteSigleKeyRowIndex(String tableName,Map<String,Object> keyMap){  
		 String[] pks = ParaCacheUtil.splitPkNames(musterParaTabCacheConfig.getPkStrs(tableName));
		 if(pks.length==1){
			 return;
		 }
		 CKey rowCKey = paraCKeyProd.getCacheTableCKey(tableName,keyMap);	
		 for(int i=0;i<pks.length;i++){
			 CKey indexCkey=paraCKeyProd.getIndexSingleKeyCKey(tableName,pks[i],keyMap.get(pks[i]));
			 SetCache.deleteCacheValue(indexCkey,rowCKey.createCkey());		 
		 }	 
	 }
}
