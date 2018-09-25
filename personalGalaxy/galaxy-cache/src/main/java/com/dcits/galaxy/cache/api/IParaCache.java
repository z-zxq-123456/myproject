package com.dcits.galaxy.cache.api;


import java.util.List;
import java.util.Map;

/**
 * 封装 分布式缓存 全局参数表
 * @author tangxlf
 * @date   2015-1-14
 * 
 */
public interface IParaCache  {	 
	/**
	   * 获取键值对应的记录列表
	   * @param String tableName 表名称          
	   * @param String pkfield   主键名
	   * @param Object pkValue   主键值
	   * @return List<Map<String, Object>>        返回主键对应的记录列表
	   */ 
	   public List<Map<String, Object>>  getList(String tableName,String pkfield,Object pkValue);
	
	  /**
	   * 获取主键对应的记录
	   * @param String tableName 表名称          
	   * @param Map<String, Object> keyMap  该行主键Map
	   * @return Map        返回主键对应的记录
	   */ 
	   public Map<String, Object>  getRow(String tableName, Map<String, Object> keyMap);
    /**
	   * 获取主键对应记录的该字段取值
	   * @param String tableName  表名称          
	   * @param Map keyMap   该行主键Map
	   * @param String field 需要获取的字段
	   * @return String      返回主键对应记录的该字段取值
	   */ 	   
	   public  <T> T  getValue(String tableName,Map<String,Object> keyMap,String field);
	   
	  /**
	   * 获取所设置key条件对应的记录集
     * @param String tableName    表名称          
	   * @param Map keyMap     该行主键Map  主键字段对应的value 可匹配为通配符以实现模糊查找      
	   * @return List<Map>     返回对应记录集
	   */			  
	   //该方法暂不开放public  List<Map<String,Object>>  getList(String tableName,Map keyMap );
	  /**
	   * 同步插入缓存和数据库记录
	   * @param String tableName    表名称          
	   * @param Map map        记录Map
	   * @return int           数据库操作结果
	   */ 	
	   public  int  insertRow(String tableName,Map<String,Object> map);
	  /**
	   * 同步修改缓存和数据库记录
	   * @param String tableName    表名称          
	   * @param Map map        记录Map
	   * @return int           数据库操作结果
	   */ 	
	   public  int  updateRow(String tableName,Map<String,Object> map);
	  /**
	   * 同步删除缓存和数据库记录 
	   * @param String tableName    表名称          
	   * @param Map map        该行主键Map
	   * @return int           数据库操作结果
	   */ 	
	   public  int  deleteRow(String tableName,Map<String,Object> keyMap);
	  
}
