package com.dcits.galaxy.cache.redis.test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.dcits.galaxy.cache.api.ICacheStore;
import com.dcits.galaxy.cache.redis.ParaTabCacheProxy;
import com.dcits.galaxy.junit.TestBase;

public class TestHanPu extends TestBase implements ICacheStore{

	
	
	public void testDeleteRow(){	
		System.out.println(ParaTabCacheProxy.getSigleKeyRowMap("tabUser",13));//入口MAP 出口MAP
	}
	
	public void  testInsert(){
		System.out.println( "初次查询   读入缓存");
		TabUser user = ParaTabCacheProxy.getSigleKeyRowBean("tabUser",""+13,new TabUser());//入口MAP 出口Bean
		System.out.println(user.toString());
		user = ParaTabCacheProxy.getSigleKeyRowBean("tabUser",""+4,new TabUser());//入口MAP 出口Bean
		System.out.println(user.toString());
		System.out.println( "查寻完毕  开始修改数据");
		TabUser insertuser=new TabUser();
		insertuser.setUserId("44");
		insertuser.setUserAge("33");
		insertuser.setUserName("jieji");
		insertuser.setUserSalary(new BigDecimal("34.56"));
		Calendar cal= Calendar.getInstance();
		cal.set(2015,0,1);
		Date date1 = cal.getTime();
		System.out.println(date1);
		insertuser.setUserDate(date1);
		
		ParaTabCacheProxy.insertRow("tabUser",insertuser);
		user = ParaTabCacheProxy.getSigleKeyRowBean("tabUser",""+44,new TabUser());//入口MAP 出口Bean
		System.out.println(user.toString());
		
	}
	public  void testGetRow() {		
		//初次查询   读入缓存
		System.out.println( "初次查询   读入缓存");
		System.out.println(ParaTabCacheProxy.getSigleKeyRowMap("tabUser",13));//入口MAP 出口MAP
		System.out.println( "查寻完毕  开始修改数据");
		testUpdateRow();
		System.out.println( "修改完毕  开始开始关闭数据库链接");
		
//		try {
//			Thread.sleep(20000);
//		} catch (InterruptedException e) {
//			
//			e.printStackTrace();
//		}
		System.out.println( "关闭数据库");
		System.out.println( "读取缓存中修改过的数据     返回Bean");
		System.out.println(ParaTabCacheProxy.getSigleKeyRowMap("tabUser",13));
		
		TabUser user = ParaTabCacheProxy.getSigleKeyRowBean("tabUser",""+13,new TabUser());//入口MAP 出口Bean
		System.out.println(user.toString());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId",13);
		System.out.println(ParaTabCacheProxy.getRowMap("tabUser",map));//入口MAP 出口MAP
			
		System.out.println( "读取缓存中修改过的数据     返回username");
		System.out.println(ParaTabCacheProxy.getSigleKeyValue("tabUser","userName",4));//入口MAP	
		TabUser user1 = new TabUser();
		user1.setUserId("3");
		System.out.println(ParaTabCacheProxy.getRowMap("tabProd",user1));//入口BEAN 出口MAP
	}
	public void testUpdateRow(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId",13);
		map.put("userName","hanpu");
		System.out.println("入参为Map类型的修改数据操作，入参为："+map);
		ParaTabCacheProxy.updateRow("tabUser",map);//入口MAP 
		
		TabUser user = new TabUser();
		user.setUserId("3");
		user.setUserAge("999");
		user.setUserName("dada");
		System.out.println("入参为Bean类型的修改数据操作，入参为："+user);
		ParaTabCacheProxy.updateRow("tabUser",user);//入口BEAN 				
	}
	
	public void testGetValue(){		
		
	}
}
