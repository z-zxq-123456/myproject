package com.dcits.galaxy.cache.redis.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.dcits.galaxy.base.util.JsonUtils;
import com.dcits.galaxy.cache.api.ICacheStore;
import com.dcits.galaxy.cache.paratab.ParaCache;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.junit.TestBase;

public class TestParaTabCache extends TestBase implements ICacheStore {
	ParaCache  paraCache;
	
	public  void testInit() {
		initParaCache();
		paraCache.initLoad("tabProd");		
		
	}
	public  void testInsert() {
		initParaCache();
		Map<String,Object> insertMap = new HashMap<String,Object>();
		insertMap.put("prdId","10");
		insertMap.put("prdType","1");
		insertMap.put("prdComment","test");
		insertMap.put("prdDatetime","20150109173011");
		insertMap.put("prdDate","20150109");
		paraCache.insertRow("tabProd",insertMap);		
		System.out.println(paraCache.getRow("tabProd",insertMap));
	}
	
	private void initParaCache(){
		paraCache = (ParaCache)SpringApplicationContext.getContext().getBean("paraCache");
	}
	
	public  void testClear() {
	}
	
	
	public void testUpdate(){
			
	}
	
	public void testDelete(){
		String key = JsonUtils.convertHumpCase("hvm_OOD_chD");
		System.out.println(key);
	}
	
	
	
	
	public  void testGetRow() {
		initParaCache();
		Map<String,Object>  dataMap = new HashMap<String,Object>();
		dataMap.put("userId", 1);
		System.out.println(paraCache.getRow("tabUser",dataMap));
		Map<String,Object>  dataMap2 = new HashMap<String,Object>();
		dataMap2.put("prdId", 1);
		dataMap2.put("prdType",1);
		System.out.println(paraCache.getRow("tabProd",dataMap2));
		//viewCache();
	}
	
	
	
	
	public  void testGetField() {
		//System.out.println(TableCacheProxy.getInstance().getList(mark,null));
		initParaCache();
		Map<String,Object>  dataMap = new HashMap<String,Object>();
		dataMap.put("userId", 1);
		String userAge = paraCache.getValue("tab_user",dataMap,"userAge");
		System.out.println(paraCache.getRow("tab_user",dataMap));
		System.out.println("userAge ="+ userAge );
		
		BigDecimal userSalary = paraCache.getValue("tab_user",dataMap,"userSalary");
		System.out.println("userSalary ="+ userSalary );
	}
}
