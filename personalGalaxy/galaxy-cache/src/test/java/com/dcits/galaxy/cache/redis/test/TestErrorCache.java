package com.dcits.galaxy.cache.redis.test;

import com.dcits.galaxy.cache.api.ICacheStore;
import com.dcits.galaxy.cache.redis.ParaTabCacheProxy;
import com.dcits.galaxy.junit.TestBase;
public class TestErrorCache extends TestBase implements ICacheStore{
	public  void testGetRow() {	
	
//		TabUser user = ParaTabCacheProxy.getSigleKeyRowBean("tabUser",12,new TabUser());//入口MAP 出口Bean	
//		System.out.println("user="+user);//入口MAP 出口MAP	
//		
		
		java.math.BigDecimal dd = ParaTabCacheProxy.getSigleKeyValue("tabUser","userSalary","11");
		System.out.println(dd);
		try {
			System.out.println("two thread start");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.math.BigDecimal dd2 = ParaTabCacheProxy.getSigleKeyValue("tabUser","userSalary","12");
		System.out.println(dd2);
		
		String dd3 = ParaTabCacheProxy.getSigleKeyValue("tabUser","userName","10");
		System.out.println(dd3);
		//System.out.println(dd+2);
		
//		System.out.println("UserAge="+user.getUserAge()  
//				            +"UserId="+  user.getUserId()
//				       +"UserName="+ user.getUserName() 
//				       +"UserDate="+ user.getUserDate()
//				       +"UserSalary="+ user.getUserSalary());	
	}
}
