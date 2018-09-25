package com.dcits.galaxy.cache.redis.test;

import com.dcits.galaxy.cache.api.ICacheStore;
import com.dcits.galaxy.cache.redis.ParaTabCacheProxy;
import com.dcits.galaxy.junit.TestBase;

import java.util.HashMap;
import java.util.Map;

public class TestSiglePkTableParaCache extends TestBase implements ICacheStore{
	public  void testGetRow() {										
		System.out.println(ParaTabCacheProxy.getSigleKeyRowMap("tabUser",2));//入口MAP 出口MAP	
		TabUser user = ParaTabCacheProxy.getSigleKeyRowBean("tabUser",2,new TabUser());//入口MAP 出口Bean	
		System.out.println(user);	
	}
	
	public void testGetValue(){		
		System.out.println(ParaTabCacheProxy.getSigleKeyValue("tabUser","userName",2));//入口MAP
	}
	
	
	public void testDeleteRow(){	
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("prdId","103");
		map.put("prdType",113);
		ParaTabCacheProxy.deleteRow("tabProd", map);
		System.out.println(ParaTabCacheProxy.getRowMap("tabProd", map));//入口MAP 出口MAP
		TabProd prod = new TabProd();
		prod.setPrdId("104");
		prod.setPrdType("14");
		ParaTabCacheProxy.deleteRow("tabProd", prod);
		System.out.println(ParaTabCacheProxy.getRowBean("tabProd", prod, prod));//入口MAP 出口MAP
	}
	
	public void testColType(){
		Map<String,Object> comMap = ParaTabCacheProxy.getSigleKeyRowMap("tabUser",2);		
		for(Map.Entry<String,Object> entry : comMap.entrySet()){			
			System.out.println("字段名"+entry.getKey()+"字段类型"+entry.getValue().getClass());
		}
	}
	
	public void testInsertRow(){
		//clearCache();	
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("prdId","103");
		map.put("prdType",113);
		map.put("prdComment","103");
		map.put("prdStatus","1");
		map.put("prdDatetime","2015-01-09 17:30:11");
		ParaTabCacheProxy.insertRow("tabProd",map);//入口MAP 
		System.out.println(ParaTabCacheProxy.getRowMap("tabProd",map));//入口MAP 出口MAP
		TabProd prod = new TabProd();
		prod.setPrdId("104");
		prod.setPrdType("14");
		prod.setPrdComment("14comment");
		prod.setPrdStatus("2");		
		prod.setPrdDatetime("2015-01-09 17:30:11");
		
		ParaTabCacheProxy.insertRow("tabProd",prod);//入口BEAN 		
		System.out.println(ParaTabCacheProxy.getRowMap("tabProd",prod));//入口BEAN 出口MAP
		System.out.println(ParaTabCacheProxy.getRowBean("tabProd",prod,prod).getPrdDatetime());//入口BEAN 出口BEAN
		//viewCacheDetail();
	}
	
	public void testUpdateRow(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("prdId","103");
		map.put("prdType",113);
		map.put("prdComment","update103");
		map.put("prdStatus","1");
		map.put("prdDatetime","2015-01-09 18:30:11");
		ParaTabCacheProxy.updateRow("tabProd",map);//入口MAP 
		System.out.println(ParaTabCacheProxy.getRowMap("tabProd",map));//入口MAP 出口MAP
		TabProd prod = new TabProd();
		prod.setPrdId("104");
		prod.setPrdType("14");
		prod.setPrdComment("update14");
		prod.setPrdStatus("3");		
		prod.setPrdDatetime("2015-01-09 17:30:11");
		ParaTabCacheProxy.updateRow("tabProd",prod);//入口BEAN 		
		System.out.println(ParaTabCacheProxy.getRowMap("tabProd",prod));//入口BEAN 出口MAP
	}
	
	
}
