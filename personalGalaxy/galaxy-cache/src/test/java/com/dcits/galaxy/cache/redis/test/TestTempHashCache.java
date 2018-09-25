package com.dcits.galaxy.cache.redis.test;

import com.dcits.galaxy.cache.base.CKey;
import com.dcits.galaxy.cache.base.HashCache;
import com.dcits.galaxy.junit.TestBase;

import java.util.HashMap;
import java.util.Map;

public class TestTempHashCache extends TestBase {
	
	public static void testCreate() {
		com.dcits.galaxy.cache.base.CKey  cKey = new com.dcits.galaxy.cache.base.CKey("aa33");
		cKey.setKey("1", "one");
		cKey.setKey("2", "two");
		Map<String,String> dataMap = new HashMap<String,String>();
		dataMap.put("first","1");
		dataMap.put("second","2");
		HashCache.addMap(cKey,dataMap,6); 
		
		
		Map<String,String> dataMap1 = new HashMap<String,String>();
		dataMap1.put("first","11");
		dataMap1.put("second11","12");
		HashCache.addMap(cKey,dataMap1,6);
		
		System.out.println(HashCache.getMap(cKey));		
		CKey  cKey1 = new CKey("aa33");
		cKey1.setKey("2", "two");
		cKey1.setKey("1", "one");		
		System.out.println(HashCache.getMap(cKey1));
	}
	
	public static void testAddKV() {
		CKey  cKey = new CKey("aa55");
		cKey.setKey("1", "one");
		cKey.setKey("2", "two");
		Map<String,String> dataMap = new HashMap<String,String>();
		dataMap.put("first","1");
		dataMap.put("second","2");
		 
		HashCache.addKV(cKey,"3","THREE"); 
		HashCache.addMap(cKey,dataMap,6);
		System.out.println(HashCache.getMap(cKey));
		System.out.println(HashCache.getDataValue(cKey, "3"));
		
		CKey  cKey1 = new CKey("aa33");
		cKey1.setKey("112", "one");
		HashCache.addKV(cKey1,"4","FOUR");
		HashCache.addMap(cKey1,dataMap,6); 
		System.out.println(HashCache.getMap(cKey1));
		System.out.println(HashCache.getDataValue(cKey1, "4"));
		
	}
	
	public static void testDelete() {
		CKey  cKey = new CKey("aa33");
		cKey.setKey("1", "one");
		cKey.setKey("2", "two");
		Map<String,String> dataMap = new HashMap<String,String>();
		dataMap.put("first","1");
		dataMap.put("second","2");
		HashCache.addMap(cKey,dataMap,6); 
		HashCache.addKV(cKey,"3","THREE"); 
		System.out.println(HashCache.getMap(cKey));
		System.out.println(HashCache.getDataValue(cKey, "3"));
		HashCache.deleteCache(cKey);
		System.out.println(HashCache.getMap(cKey));
		System.out.println(HashCache.getDataValue(cKey, "3"));
		
		CKey  cKey1 = new CKey("aa33");
		cKey1.setKey("112", "one");
		HashCache.addKV(cKey1,"4","FOUR");
		HashCache.addMap(cKey1,dataMap,6); 
		System.out.println(HashCache.getMap(cKey1));
		System.out.println(HashCache.getDataValue(cKey1, "4"));
		HashCache.deleteKV(cKey1,"4");
		System.out.println(HashCache.getMap(cKey1));
		System.out.println(com.dcits.galaxy.cache.base.HashCache.getDataValue(cKey1, "4"));
		
	}
	
	public static void testUpdateExpire() {
		CKey  cKey = new CKey("aa33");
		cKey.setKey("1", "one");
		cKey.setKey("2", "two");
		Map<String,String> dataMap = new HashMap<String,String>();
		dataMap.put("first","1");
		dataMap.put("second","2");
		HashCache.addMap(cKey,dataMap,6); 
		HashCache.addKV(cKey,"3","THREE"); 
		System.out.println(HashCache.getMap(cKey));
		System.out.println(HashCache.getDataValue(cKey, "3"));
		HashCache.updateExpire(cKey, HashCache.DAY_EXPIRE);
	    try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    System.out.println(HashCache.getMap(cKey));
		System.out.println(HashCache.getDataValue(cKey, "3"));		
	}

}
