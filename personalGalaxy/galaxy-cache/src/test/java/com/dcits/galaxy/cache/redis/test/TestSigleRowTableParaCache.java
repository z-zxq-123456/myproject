package com.dcits.galaxy.cache.redis.test;

import com.dcits.galaxy.cache.api.ICacheStore;
import com.dcits.galaxy.cache.redis.ParaTabCacheProxy;
import com.dcits.galaxy.junit.TestBase;

public class TestSigleRowTableParaCache extends TestBase implements ICacheStore{
	public  void testGetRow() {	
		System.out.println(ParaTabCacheProxy.getSigleRowMap("tabFmSystem"));//入口MAP 出口MAP	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TabFmSystem fmSystem = ParaTabCacheProxy.getSigleRowBean("tabFmSystem",new TabFmSystem());//入口MAP 出口Bean	
		System.out.println(" ****txl test**** "+fmSystem);	
	}
	
	public void testGetValue(){		
		System.out.println(ParaTabCacheProxy.getSigleRowValue("tabFmSystem","sysName"));//入口MAP
	}
}
