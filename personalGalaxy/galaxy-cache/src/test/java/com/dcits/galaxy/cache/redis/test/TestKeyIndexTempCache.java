package com.dcits.galaxy.cache.redis.test;

import java.util.HashMap;
import java.util.Map;

import com.dcits.galaxy.cache.api.ICacheStore;
import com.dcits.galaxy.cache.redis.ParaTabCacheProxy;
import com.dcits.galaxy.junit.TestBase;

public class TestKeyIndexTempCache extends TestBase implements ICacheStore{
	public  void testGetList() {		
		//TabTrail trail = new TabTrail();		
		//System.out.println(ParaTabCacheProxy.getListBean("fmStructureDigitPos","structureType","1",trail).size());//出口BEAN  LIST	
		
		System.out.println(ParaTabCacheProxy.getListMap("fmStructureDigitPos","structureType","CD2"));//出口MAP list
		
		//viewCacheDetail();
				
	}
	public  void testGetRow() {		
		//clearCache();	
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("prdId","1");	
		map.put("prdType","1");
		System.out.println(ParaTabCacheProxy.getRowMap("tabTrail",map));//入口MAP 出口MAP
		TabTrail trail = new TabTrail();
		trail.setPrdId("2");
		trail.setPrdType("1");
		System.out.println(ParaTabCacheProxy.getRowBean("tabTrail",trail,trail));//入口BEAN 出口BEAN
		System.out.println(ParaTabCacheProxy.getRowBean("tabTrail",map,trail));//入口MAP 出口BEAN
		System.out.println(ParaTabCacheProxy.getRowBean("tabTrail",map,trail).getPrdDatetime());//入口MAP 出口BEAN
		System.out.println(ParaTabCacheProxy.getRowMap("tabTrail",trail));//入口BEAN 出口MAP
		//viewCacheDetail();
	}
	public void testGetValue(){		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("prdId","1");	
		map.put("prdType","1");
		System.out.println(ParaTabCacheProxy.getValue("tabTrail",map,"prdComment"));//入口MAP		
		TabTrail trail = new TabTrail();
		trail.setPrdId("4");
		trail.setPrdType("2");	
		System.out.println(ParaTabCacheProxy.getValue("tabTrail",trail,"prdComment"));//入口BEAN
		System.out.println(ParaTabCacheProxy.getValue("tabTrail",trail,"prdDatetime"));//入口BEAN
		//System.out.println(ParaTabCacheProxy.getValue("tabTrail",trail,"prdDatetime").getClass());//入口BEAN
	}
	
	public void testInsertRow(){
		//clearCache();	
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("prdId","103");
		map.put("prdType",113);
		map.put("prdComment","103");
		map.put("prdStatus","1");
		map.put("prdDatetime","2015-01-09 17:30:11");
		ParaTabCacheProxy.insertRow("tabTrail",map);//入口MAP 
		System.out.println(ParaTabCacheProxy.getRowMap("tabTrail",map));//入口MAP 出口MAP
		TabTrail trail = new TabTrail();
		trail.setPrdId("104");
		trail.setPrdType("14");
		trail.setPrdComment("14comment");
		trail.setPrdStatus("2");		
		trail.setPrdDatetime("2015-01-09 17:30:11");
		
		System.out.println(ParaTabCacheProxy.insertRow("tabTrail",trail));//入口BEAN 		
		System.out.println(ParaTabCacheProxy.getRowMap("tabTrail",trail));//入口BEAN 出口MAP
		System.out.println(ParaTabCacheProxy.getRowBean("tabTrail",trail,trail).getPrdDatetime());//入口BEAN 出口BEAN
		//viewCacheDetail();
	}
	
	public void testUpdateRow(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("prdId","103");
		map.put("prdType",113);
		map.put("prdComment","update103");
		map.put("prdStatus","1");
		map.put("prdDatetime","2015-01-09 18:30:11");
		System.out.println(ParaTabCacheProxy.updateRow("tabTrail",map));//入口MAP 
		System.out.println(ParaTabCacheProxy.getRowMap("tabTrail",map));//入口MAP 出口MAP
		TabTrail trail = new TabTrail();
		trail.setPrdId("104");
		trail.setPrdType("14");
		trail.setPrdComment("update14");
		trail.setPrdStatus("3");		
		trail.setPrdDatetime("2015-01-09 17:30:11");
		ParaTabCacheProxy.updateRow("tabTrail",trail);//入口BEAN 		
		System.out.println(ParaTabCacheProxy.getRowMap("tabTrail",trail));//入口BEAN 出口MAP
	}
	
	public void testDeleteRow(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("prdId","103");
		map.put("prdType",113);
		System.out.println(ParaTabCacheProxy.deleteRow("tabTrail",map));//入口MAP 
		System.out.println(ParaTabCacheProxy.getRowMap("tabTrail",map));//入口MAP 出口MAP
		TabTrail trail = new TabTrail();
		trail.setPrdId("104");
		trail.setPrdType("14");
		ParaTabCacheProxy.deleteRow("tabTrail",trail);//入口BEAN 		
		System.out.println(ParaTabCacheProxy.getRowMap("tabTrail",trail));//入口BEAN 出口MAP
	}
}
