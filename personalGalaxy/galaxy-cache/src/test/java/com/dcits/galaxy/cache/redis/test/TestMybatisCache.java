package com.dcits.galaxy.cache.redis.test;

import java.util.HashMap;
import java.util.Map;

import com.dcits.galaxy.cache.api.ICacheStore;
import com.dcits.galaxy.cache.api.IDBStore;
import com.dcits.galaxy.junit.TestBase;

public class TestMybatisCache extends TestBase implements ICacheStore,IDBStore{
    static String  mark = "test";
	
	public  void testInsert() {
		Map<String,Object> rowMap = new HashMap<>();
		rowMap.put("id","199");
		rowMap.put("name","张三");
		rowMap.put("age","43");
		cobarSessionTemplate.insert(assemSqlId("test1","INSERT"),rowMap);	
		System.out.println(cobarSessionTemplate.selectList(assemSqlId("test1","INITQUERY")));		
	
	}
	
	
	public  void testSelect() {
		System.out.println(cobarSessionTemplate.selectList(assemSqlId("test1","INITQUERY")));	
		System.out.println(cobarSessionTemplate.selectList(assemSqlId("test2","INITQUERY")));	
	}
	
	public void testUpdate(){
		Map<String,Object> rowMap = new HashMap<>();
		rowMap.put("id","199");
		rowMap.put("name","UPDATE");
		rowMap.put("age","67");
		cobarSessionTemplate.update(assemSqlId("test1","UPDATE"),rowMap);			
		//System.out.println(cobarSessionTemplate.selectList(assemSqlId("test1","INITQUERY")));
	}
	/**
	   * 拼装SQLID
	   * @param String mark    缓存标志
	   * @param String subSqlId    待拼装的SQLID
	   * @return String   拼装好的SQLID
	   */
	   private  String assemSqlId(String mark,String subSqlId){
		   return mark + "_"+subSqlId;
	   }
}
