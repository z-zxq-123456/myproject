package com.dcits.galaxy.cache.paratab;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.galaxy.cache.api.IParaCache;
import com.dcits.galaxy.cache.colhandle.ColHandleProxy;
import com.dcits.galaxy.cache.exception.CacheException;
import com.dcits.galaxy.cache.utils.ForamtMapUtil;

/**
 * 封装 分布式缓存 全局参数表
 * @author tangxlf
 * @date   2014-12-30
 * 
 */
@Repository
public class ParaCache implements IParaCache{	 
	   @Resource
	   private ParaDbHAO paraDbHAO;
	   @Resource
	   private ParaCacheHAO paraCacheHAO;
	   @Resource
	   private  MusterParaTabCacheConfig musterParaTabCacheConfig;
	   private static final Logger log = LoggerFactory.getLogger(ParaCache.class);	
	   
	  /**
	   * 初始化装载
	   * @param String tableName 缓存参数表名称    
	   */ 
	  public synchronized void initLoad(String tableName){
		  if(!paraCacheHAO.isInit(tableName)){//如果没有初始化
		    	 List<Map<String,Object>> dataList = paraDbHAO.initQuery(tableName);
		    	 for(int i=0;i<dataList.size();i++){
		    		 paraCacheHAO.initDbRow(tableName,dataList.get(i));
				 }
		  }		   
	  }
      /**
	   * 获取主键对应记录的该字段取值
	   * @param String tableName  缓存参数表名称          
	   * @param Map keyMap   该行主键Map
	   * @param String field 需要获取的字段
	   * @return String      返回主键对应记录的该字段取值
	   */ 	   
	  @Override
	  public  <T> T  getValue(String tableName,Map<String, Object> keyMap,String field){		  
		   log.info("tableName="+tableName+" keyMap="+keyMap+" field="+field);		  
		   T fieldValue = null;
		   try {
			   initLoad(tableName);	
			   fieldValue = paraCacheHAO.getValue(tableName,keyMap,field);
		   } catch (Throwable t) {		
				log.error(t.getMessage());
				return castValue(tableName,keyMap,field);		
		   }
		   if(fieldValue ==null){
			   fieldValue =castValue(tableName,keyMap,field);
		   }
		   return fieldValue;	
	  }
	  
	  @SuppressWarnings("unchecked")
	private <T> T castValue(String tableName,Map<String, Object> keyMap,String field){
		  Object valueObj = checkGetValue(tableName,keyMap,field);
		  if(valueObj==null){			
				  return null;			  
		  }
		  return (T)valueObj;
	  }
	  
	  private Object checkGetValue(String tableName,Map<String, Object> keyMap,String field){
		  Map<String, Object> rowMap = getRecordMap(tableName,keyMap);
		  if(rowMap==null){
			  throw new CacheException("数据库表没有该条记录，请检查！");   
		  }
		  Object valueObj = rowMap.get(field);
		  if(valueObj==null){
			  return  ColHandleProxy.getDefaultValue(musterParaTabCacheConfig.getColType(tableName,field));
		  }
		  return valueObj;
	  }
	  /**
	   * 获取键值对应的记录列表
	   * @param String tableName 表名称          
	   * @param String pkfield   主键名
	   * @param Object pkValue   主键值
	   * @return List<Map<String, Object>>        返回主键对应的记录列表
	   */ 
	  @Override
	public List<Map<String, Object>>  getList(String tableName,String pkfield,Object pkValue){		
		  throw new CacheException("不支持该方法,请配置其他handerclass！");
	  }	  	  
	  /**
	   * 获取主键对应的记录
	   * @param String tableName 缓存参数表名称          
	   * @param Map keyMap  该行主键Map
	   * @return Map        返回主键对应的记录
	   */ 
	  @Override
	public  Map<String, Object>  getRow(String tableName,Map<String, Object> keyMap ){ 		  		  	
		   log.info("tableName="+tableName+" keyMap="+keyMap);
		   Map<String, Object> rowMap = null;		      
		   try {
			   initLoad(tableName);
			   rowMap = paraCacheHAO.getRow(tableName,keyMap);
		   } catch (Throwable t) {		
				log.error(t.getMessage());
				return getRecordMap(tableName, keyMap);			
		   }
		   if(rowMap==null||rowMap.size()==0){
			  rowMap=getRecordMap(tableName, keyMap);
		   }
		   return rowMap;	
	   }	   
	   
	   /**
		* 从数据库查询获取主键对应的记录
		* @param String tableName 缓存参数表名称          
	    * @param Map keyMap  该行主键Map
		* @return Map        返回主键对应的记录
		*/
	   private Map<String, Object> getRecordMap(String tableName,Map<String, Object> keyMap){
		   log.info("从数据库查询获取主键对应的记录     tableName="+tableName+" keyMap ="+keyMap);
		   Map<String, Object> rowMap = paraDbHAO.getRow(tableName, keyMap);		  
		   if(rowMap.size()==0){
			   return null;
		   }
		   rowMap = ForamtMapUtil.MapToHump(rowMap);
		   try {
			    paraCacheHAO.insertRow(tableName,rowMap);	
		   } catch (Throwable t) {		
			   log.error(t.getMessage());						
		   }		   	   
		   return rowMap;		   
	   }
	   
       /**
		* 同步插入缓存和数据库记录
		* @param String tableName   缓存参数表名称         
		* @param Map<String,Object> rowMap    行记录Map
		* @return int           数据库操作结果
		*/ 
	   @Override
	@Transactional
	   public  int  insertRow(String tableName,Map<String,Object> rowMap){		  
		   log.info("tableName="+tableName+" rowMap="+rowMap);
		   int insertRowInt = paraDbHAO.insertRow(tableName,rowMap);
		   paraCacheHAO.insertRow(tableName,rowMap);
		   return  insertRowInt ;
	   }
	   /**
	    * 同步修改缓存和数据库记录
		* @param String tableName   缓存参数表名称        
		* @param Map<String,Object> rowMap  行记录Map
		* @return int           数据库操作结果
		*/ 
	   @Override
	@Transactional
	   public  int  updateRow(String tableName,Map<String,Object> rowMap){
		   log.info("tableName="+tableName+" rowMap="+rowMap);
		   int updateRowInt = paraDbHAO.updateRow(tableName, rowMap);		   	   
		   if(updateRowInt==0){
			       log.error("数据库没有记录供修改，请检查！");
				   throw new CacheException("数据库没有记录供修改，请检查！"); 
		   }
		   paraCacheHAO.insertRow(tableName, rowMap);
		   return  updateRowInt;
	   }	   
	   /**
		* 同步删除缓存和数据库记录 
		* @param String mark    缓存标志          
		* @param Map map        该行主键Map
		* @return int           数据库操作结果
		*/ 
	   @Override
	@Transactional
	   public  int  deleteRow(String tableName,Map<String,Object> keyMap){
		   log.info("tableName="+tableName+" keyMap="+keyMap);
		   int deleteRowInt = paraDbHAO.deleteRow(tableName, keyMap);
		   paraCacheHAO.deleteRow(tableName, keyMap);
		   return  deleteRowInt ;
	   }
}
