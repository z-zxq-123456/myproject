package com.dcits.galaxy.cache.redis.test;

import com.dcits.galaxy.cache.api.ICacheStore;
import com.dcits.galaxy.cache.redis.ParaTabCacheProxy;
import com.dcits.galaxy.junit.TestBase;

public class TestFmCache extends TestBase implements ICacheStore{
	public  void testGetRow() {	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ParaTabCacheProxy.getSigleRowMap("fmSystem"));//入口MAP 出口MAP	
//		TabFmSystem fmSystem = ParaTabCacheProxy.getSigleRowBean("tabFmSystem",new TabFmSystem());//入口MAP 出口Bean	
//		System.out.println(fmSystem);	
	}
	
	public void testGetValue(){		
		System.out.println(ParaTabCacheProxy.getSigleRowValue("tabFmSystem","sysName"));//入口MAP
	}
}
