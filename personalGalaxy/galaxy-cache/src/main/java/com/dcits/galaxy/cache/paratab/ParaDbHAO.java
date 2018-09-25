package com.dcits.galaxy.cache.paratab;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;

/**
 * 全局参数表数据库辅助访问对象-HAO辅助访问对象 
 * @author tangxlf
 * @date   2015-01-19
 * 
 */
@Repository
public class ParaDbHAO {
	 private static final Logger log = LoggerFactory.getLogger(ParaDbHAO.class); 
	 private static final String NAME_SPACE="galaxy.cache.dynamicTemplate"; //mybatis mapper文件namespace
	 private static final String INIT_SQLID="dynamic_initselect"; //初始化查询SQL_ID
	 private static final String QUERYLIST_SQLID="dynamic_selectlist"; //初始化查询SQL_ID
	 private static final String QUERYBYPK_SQLID="dynamic_selectbypk"; //主键查询SQL_ID
	 private static final String DELETE_SQLID="dynamic_delete"; //删除操作SQL_ID
	 private static final String INSERT_SQLID="dynamic_insert"; //新增操作SQL_ID
	 private static final String UPDATE_SQLID="dynamic_update"; //修改操作SQL_ID	   
		 
	 @Resource
	 private ShardSqlSessionTemplate shardSqlSessionTemplate;
	 @Resource
	 private SqlParameterProvider sqlParameterProvider;
	 /**
	  * 全局参数表初始化查询
	  * @param  String tableName 缓存参数表名称 
	  * @retrun List<Map<String,Object>>      查询结果   
	  */ 
	 public  List<Map<String,Object>> initQuery(String tableName){		 
		   Map<String,String> initMap =sqlParameterProvider.initParaProv(tableName);
		   log.info(tableName+"初始化查询  sqlId="+assemSqlId(INIT_SQLID)+" paraMap ="+initMap);
		   @SuppressWarnings("unchecked")			   
		   List<Map<String,Object>> dataList = (List<Map<String,Object>>)shardSqlSessionTemplate
		                                                                .selectList(assemSqlId(INIT_SQLID),initMap);
		   return dataList;		
	 }	
	 /**
	  * 获取键值对应的记录列表
	  * @param String tableName 表名称          
	  * @param String pkfield   主键名
	  * @param Object pkValue   主键值
	  * @return List<Map<String, Object>>        返回主键对应的记录列表
	  */ 
	 public List<Map<String, Object>>  getList(String tableName,String pkfield,Object pkValue){
		   Map<String, Object> queryMap = new HashMap<String, Object>();
		   queryMap.put(pkfield, pkValue);
		   Map<String,String> initMap =sqlParameterProvider.selectListParaProv(tableName,queryMap);
		   log.info(tableName+"初始化查询  sqlId="+assemSqlId(QUERYLIST_SQLID)+" paraMap ="+initMap);
		   @SuppressWarnings("unchecked")			   
		   List<Map<String,Object>> dataList = (List<Map<String,Object>>)shardSqlSessionTemplate
		                                                                .selectList(assemSqlId(QUERYLIST_SQLID),initMap);
		   return dataList;	
	  }	  
	 /**
	  * 从缓存获取主键对应的记录
	  * @param  String tableName 缓存参数表名称          
	  * @param  Map keyMap       该行主键Map
	  * @return Map             返回主键对应的记录  
	  */ 
	 @SuppressWarnings("unchecked")
	 public Map<String, Object> getRow(String tableName,Map<String, Object> keyMap ){		
		 Map<String, Object> rowMap = new HashMap<String, Object>();
		 Map<String,String> selectPkMap =  sqlParameterProvider.selectPkParaProv(tableName,keyMap);
		 log.info(tableName+" 主键查询SQL MAP为"+selectPkMap);
		 Object obj = shardSqlSessionTemplate.selectOne(assemSqlId(QUERYBYPK_SQLID),selectPkMap);
		 if(obj==null){
			   return rowMap;
		 }else{
			   rowMap = (Map<String,Object>)obj;
		 }		
		 return rowMap;		 
	 }
	 /**
	  * 同步插入缓存和数据库记录
	  * @param String tableName   缓存参数表名称         
	  * @param Map<String,Object> rowMap    记录Map
	  * @return int       新增结果
	  */ 	   
	 public  int  insertRow(String tableName,Map<String,Object> rowMap){			
		Map<String,String> insertMap =  sqlParameterProvider.insertParaProv(tableName,rowMap);
		log.info(tableName+" 新增SQL MAP为"+insertMap);
		int insertRowInt =shardSqlSessionTemplate.insert(assemSqlId(INSERT_SQLID),insertMap);	
		return insertRowInt;
	 }
	 
	 /**
	  * 同步修改缓存和数据库记录
	  * @param String tableName   缓存参数表名称        
	  * @param Map<String,Object> rowMap  记录Map
	  * @return int       修改行数
	  */ 
	 public  int  updateRow(String tableName,Map<String,Object> rowMap){
		 Map<String,String> updateMap =  sqlParameterProvider.updateParaProv(tableName,rowMap);
		 log.info(tableName+" 修改SQL MAP为"+updateMap);
		 int updateRowInt = shardSqlSessionTemplate.update(assemSqlId(UPDATE_SQLID),updateMap);
		 log.info(tableName+" updateRowInt="+updateRowInt);
		 return updateRowInt;
	 }
	 /**
	  * 同步删除缓存和数据库记录 
	  * @param String mark    缓存标志          
	  * @param Map map        该行主键Map
	  * @return int           删除行数
	  */ 	 
	 public  int  deleteRow(String tableName,Map<String,Object> keyMap){  
		 Map<String,String> deleteMap =  sqlParameterProvider.deleteParaProv(tableName,keyMap);
		 log.info(tableName+" 删除SQL MAP为"+deleteMap);
		 int deleteRowInt =shardSqlSessionTemplate.delete(assemSqlId(DELETE_SQLID),deleteMap);
		 log.info(tableName+" deleteRowInt="+deleteRowInt);	 
		 return deleteRowInt;
	 }
	 /**
	  * 拼装SQLID	  
	  * @param String subSqlId    待拼装的SQLID
	  * @return String   拼装好的SQLID
	  */
	 private  String assemSqlId(String subSqlId){
		 return NAME_SPACE + "."+subSqlId;
	 }
	 
}
