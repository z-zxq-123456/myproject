package com.dcits.galaxy.cache.redis.test;

import com.dcits.galaxy.cache.base.CKey;
import com.dcits.galaxy.cache.base.ObjCache;
import com.dcits.galaxy.junit.TestBase;

public class TestTempObjCache extends TestBase {
	
	public static void testCreate() {
		CKey  cKey = new CKey("aa");
		cKey.setKey("1", "one");
		cKey.setKey("2", "two");
		ObjCache.create(cKey,new String("first test"),6); 
		System.out.println(ObjCache.getValue(cKey));
		ObjCache.create(cKey,new String("second test"),6); 
		System.out.println(ObjCache.getValue(cKey));
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjCache.updateExpire(cKey, ObjCache.FOREVER_EXPIRE);
		try {
			Thread.sleep(3*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CKey  cKey1 = new CKey("aa");
		cKey1.setKey("2", "two");
		cKey1.setKey("1", "one");		
		System.out.println("cKey1="+ObjCache.getValue(cKey1));
	}
	
	
	public static void testDeleteCache() {
		CKey  cKey = new CKey("bb");
		cKey.setKey("1", "one");
		cKey.setKey("2", "two");
		ObjCache.create(cKey,new String("first test"),ObjCache.FOREVER_EXPIRE); 
		System.out.println(ObjCache.getValue(cKey));		
		CKey  cKey1 = new CKey("bb");
		cKey1.setKey("2", "two");
		cKey1.setKey("1", "one");
		ObjCache.deleteCache(cKey);				
		System.out.println(ObjCache.getValue(cKey1));
	}

}
