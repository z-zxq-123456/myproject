package com.dcits.galaxy.cache.api;


import java.util.Map;


public interface ITableCache extends ITempCache {	 
	
	  /**
	   * 获取主键对应的记录
	   * @param String mark 缓存标志          
	   * @param Map keyMap  该行主键Map
	   * @return Map        返回主键对应的记录
	   */ 
	   public  Map<String,String>  getRow(String mark,Map<String,String> keyMap );
    /**
	   * 获取主键对应记录的该字段取值
	   * @param String mark  缓存标志          
	   * @param Map keyMap   该行主键Map
	   * @param String field 需要获取的字段
	   * @return String      返回主键对应记录的该字段取值
	   */ 	   
	   public  String  getValue(String mark,Map<String,String> keyMap,String field);
	   
	  /**
	   * 获取所设置key条件对应的记录集
     * @param String mark    缓存标志          
	   * @param Map keyMap     该行主键Map  主键字段对应的value 可匹配为通配符以实现模糊查找      
	   * @return List<Map>     返回对应记录集
	   */			  
	   //该方法暂不开放public  List<Map<String,String>>  getList(String mark,Map keyMap );
	  /**
	   * 同步插入缓存和数据库记录
	   * @param String mark    缓存标志          
	   * @param Map map        记录Map
	   */ 	
	   public  void  insertRow(String mark,Map<String,String> map);
	  /**
	   * 同步修改缓存和数据库记录
	   * @param String mark    缓存标志          
	   * @param Map map        记录Map
	   */ 	
	   public  void  updateRow(String mark,Map<String,String> map);
	  /**
	   * 同步删除缓存和数据库记录 
	   * @param String mark    缓存标志          
	   * @param Map map        该行主键Map
	   */ 	
	   public  void  deleteRow(String mark,Map<String,String> keyMap);
	  /**
	   * 删除所有缓存标志对应的缓存中的记录，不删除数据库 ，供管理端使用
	   * @param String mark    缓存标志
	   */ 	
	   public  void  deleteCache(String mark);
}
