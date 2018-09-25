package com.dcits.galaxy.cache.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dcits.galaxy.cache.api.IParaCache;
import com.dcits.galaxy.cache.exception.CacheException;
import com.dcits.galaxy.cache.paratab.MusterParaTabCacheConfig;
import com.dcits.galaxy.cache.utils.ForamtMapUtil;
import com.dcits.galaxy.cache.utils.ParaCacheUtil;
import com.dcits.galaxy.core.spring.SpringApplicationContext;

/**
 * 封装 分布式缓存 表数据  
 * @author tangxlf
 * @date   2015-1-14
 */
public  class ParaTabCacheProxy {
	 private static final String DEFAULT_HANDLERCLASS ="paraCache";
	 //private static static final ParaTabCacheProxy proxy = new ParaTabCacheProxy();
	 private static  final MusterParaTabCacheConfig musterParaTabCacheConfig = (MusterParaTabCacheConfig)SpringApplicationContext.getContext().getBean("musterParaTabCacheConfig");
	 private static  synchronized IParaCache getParaCache(String tableName){		 
		 String handlerClass = musterParaTabCacheConfig.getHanlderClass(tableName);
		 if(handlerClass==null||handlerClass.trim().equals("")){
			 handlerClass = DEFAULT_HANDLERCLASS;
		 }
		 return (IParaCache)SpringApplicationContext.getContext().getBean(handlerClass);
	 }	 
	 /**
	   * 获取主键对应的记录列表
	   * @param String tableName 参数缓存表名称          
	   * @param String pkfield   主键名
	   * @param Object pkValue   主键值
	   * @return List<Map<String, Object>>        返回主键对应的记录列表
	   */ 	
	   public static  List<Map<String, Object>> getListMap(String tableName,String pkfield,Object pkValue ) {				   
		 return getParaCache(tableName).getList(tableName, pkfield, pkValue);	   
	   }
	 /**
	   * 获取主键对应的记录
	   * @param String tableName 参数缓存表名称          
	   * @param Object keyObj    该行主键对象可是Bean 可以是Map
	   * @return Map             返回主键对应的记录	 
	   */ 	
	   public static  Map<String,Object> getRowMap(String tableName,Object keyObj ) { 
		   try {			   
			   return getParaCache(tableName).getRow(tableName,transToMap(keyObj));			     
		   }catch (Exception e) {			
			   throw new CacheException(e); 
		   }
	   }
	/**
      * 获取只有一条记录表的记录
	  * @param String tableName 参数缓存表名称          
	  * @param Object keyObj    该行主键对象可是Bean 可以是Map
	  * @return Map             返回主键对应的记录	 
	  */ 	
	  public static  Map<String,Object> getSigleRowMap(String tableName) { 
		   return getRowMap(tableName,new HashMap<String,Object>());
	  }
	/**
	  * 获取单主键表的记录
	  * @param String tableName  参数缓存表名称          
	  * @param Object pkValueObj 单主键值
	  * @return Map              返回主键对应的记录	 
	  */ 	  
	  public static  Map<String,Object> getSigleKeyRowMap(String tableName,Object pkValueObj) { 
		   return getRowMap(tableName,createSiglePkMap(tableName,pkValueObj));
	  }
	 /**
	  * 获取主键对应的记录列表
	  * @param String tableName 参数缓存表名称          
	  * @param String pkfield   主键名
	  * @param Object pkValue   主键值
	  * @return List<Map<String, Object>>        返回主键对应的记录列表
	 * @throws Exception 
	  */ 	
	  public static <T> List<T> getListBean(String tableName,String pkfield,Object pkValue,T beanObj) {	
		 List<Map<String, Object>> rowList = getParaCache(tableName).getList(tableName, pkfield, pkValue);
		 List<T>  beanList = new ArrayList<T>();
		 for(Map<String, Object> rowMap:rowList){
			 try {
				beanList.add(transToBean(rowMap,beanObj));
			 } catch (Exception e) {
				throw new CacheException(e); 
			 }
		 }
		 return beanList;	   
	  }
	 /**
	   * 获取主键对应的记录
	   * @param String tableName 参数缓存表名称          
	   * @param Object keyObj    该行主键对象可是Bean 可以是Map
	   * @param T beanObj        返回的Bean对象
	   * @return  T              返回主键对应Bean对象 
	   */ 	
	  public static  <T> T  getRowBean(String tableName,Object keyObj,T beanObj) { 
		   try {			   
				Map<String,Object> rowMap = getParaCache(tableName).getRow(tableName,transToMap(keyObj));		   
			    return transToBean(rowMap,beanObj);
		   }catch (Exception e) {			
				throw new CacheException(e); 
		   }
	  }
	 /**
	   * 获取主键对应的记录
	   * @param String tableName 参数缓存表名称          
	   * @param Object pkValueObj 单主键值
	   * @param T beanObj        返回的Bean对象
	   * @return  T              返回主键对应Bean对象 
	   */ 	
	  public static  <T> T  getSigleKeyRowBean(String tableName,Object pkValueObj,T beanObj) { 
		   try {			   
				Map<String,Object> rowMap = getParaCache(tableName).getRow(tableName,createSiglePkMap(tableName,pkValueObj));		   
			    return transToBean(rowMap,beanObj);
		   }catch (Exception e) {			
				throw new CacheException(e); 
		   }
	  }
	 /**
	   * 获取只有一条记录表的记录
	   * @param String tableName 参数缓存表名称          
	   * @param Object keyObj    该行主键对象可是Bean 可以是Map
	   * @return <T> T           返回主键对应Bean对象	 
	   */ 	
	  public static  <T> T getSigleRowBean(String tableName,T beanObj) { 
			return getRowBean(tableName,new HashMap<String,Object>(),beanObj);
	  }	   
     /**
	   * 获取主键对应记录的该字段取值
	   * @param String tableName  参数缓存表名称          
	   * @param Object keyObj     该行主键对象可是Bean 可以是Map
	   * @param String field      需要获取的字段
	   * @return String           返回主键对应记录的该字段取值
	   */ 	   
	  public static  <T> T  getValue(String tableName,Object keyObj,String field){	
		   try {			  	   
		       return getParaCache(tableName).getValue(tableName,transToMap(keyObj),field);
		   }catch (Exception e) {			
			   throw new CacheException(e); 
		   }
	   }
	 /**
	   * 获取单主键对应记录的该字段取值
	   * @param String tableName  参数缓存表名称        	  
	   * @param String field      需要获取的字段
	   * @param Object pkValueObj 单主键值
	   * @return String           返回主键对应记录的该字段取值
	   */ 	   
	  public static  <T> T  getSigleKeyValue(String tableName,String field,Object pkValueObj){		  			  	   
			  return getParaCache(tableName).getValue(tableName,createSiglePkMap(tableName,pkValueObj),field);		
	  }
	 /**	
	   * 获取只有一条记录表的记录字段取值
	   * @param String tableName  参数缓存表名称         	   
	   * @param String field      需要获取的字段
	   * @return String           返回主键对应记录的该字段取值
	   */ 	   
	  public static  <T> T  getSigleRowValue(String tableName,String field){		  			  	   
			  return getParaCache(tableName).getValue(tableName,new HashMap<String,Object>(),field);		
	  }
	  /**
	   * 同步插入缓存和数据库记录
	   * @param String tableName    参数缓存表名称          
	   * @param Object rowObj       该行对象可是Bean 可以是Map
	   * @return int           数据库操作结果
	   */	   
	   public static  int  insertRow(String tableName,Object rowObj){
		   try {
			   return getParaCache(tableName).insertRow(tableName,transToMap(rowObj));
		   } catch (Exception e) {
			   throw new CacheException(e);
		   }
	   }
	  /**
	   * 同步修改缓存和数据库记录
	   * @param String tableName    参数缓存表名称          
	   * @param Object rowObj       该行对象可是Bean 可以使Map
	   * @return int           数据库操作结果
	   */ 	  
	   public static  int  updateRow(String tableName,Object rowObj){
		   try {
			   return getParaCache(tableName).updateRow(tableName,transToMap(rowObj));
		   } catch (Exception e) {
			   throw new CacheException(e);
		   }		   
	   }
	  /**
	   * 同步删除缓存和数据库记录 
	   * @param String tableName    参数缓存表名称          
	   * @param Object rowObj       该行对象可是Bean 可以是Map
	   * @return int           数据库操作结果
	   */ 
	   public static  int  deleteRow(String tableName,Object rowObj){
		   try {
			   return getParaCache(tableName).deleteRow(tableName,transToMap(rowObj));
		   } catch (Exception e) {
			   throw new CacheException(e);
		   }		  
	   }	
	  /**
	   * 同步删除缓存和数据库单主键对应记录 
	   * @param String tableName    参数缓存表名称          
	   * @param Object pkValueObj   单主键值
	   * @return int           数据库操作结果
	   */ 
	  public static  int  deleteSigleKeyRow(String tableName,Object pkValueObj){
		  return getParaCache(tableName).deleteRow(tableName,createSiglePkMap(tableName,pkValueObj));	  
	  }	
		   	   
	   
	   @SuppressWarnings("unchecked")
	   private static Map<String,Object> transToMap(Object rowObj) throws Exception{		  
		   if(rowObj==null){
			   return new HashMap<String,Object>();
		   }
		   Map<String,Object> rowMap = null;
		   if(rowObj instanceof Map){
			   rowMap = (Map<String,Object>)rowObj;
		   }else{
			   rowMap = ForamtMapUtil.beanToMap(rowObj);
		   }
		   return rowMap;
	   }
	   
	   @SuppressWarnings("unchecked")
	   private static  <T> T transToBean(Map<String,Object> rowMap,T beanObj) throws Exception{
		   if(rowMap==null){
			   return null;
		   }
		   T  rowBean = (T)Class.forName(beanObj.getClass().getName()).newInstance();
		   ForamtMapUtil.mapToBean(rowMap,rowBean);
		   return rowBean;
	   }
	   
	 
	  /**
	   * 生成  单主键表的查询Map
	   * @param String tableName      参数缓存表名称 
	   * @param Object pkValueObj     单主键值	
	   * @return Map<String,Object>   单主键表的查询Map
	   */
	  private static Map<String,Object> createSiglePkMap(String tableName,Object pkValueObj){
		  ParaCacheUtil.checkIsNull(tableName);
		  String pkStr =musterParaTabCacheConfig.getPkStrs(tableName);
		  if(pkStr==null){
			  throw new CacheException("尚未配置主键值！");
		  }
		  String[] pks = ParaCacheUtil.splitPkNames(musterParaTabCacheConfig.getPkStrs(tableName));		  
		  Map<String,Object> keyMap = new HashMap<String,Object>();		  
		  keyMap.put(pks[0],pkValueObj);		
		  return keyMap;
	  }
}
