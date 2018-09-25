package com.dcits.galaxy.cache.redis.test;

import com.dcits.galaxy.cache.paratab.ParaTabCacheConfig;
import com.dcits.galaxy.cache.paratab.ParaTabCacheInfo;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.junit.TestBase;

import java.util.List;

public class TestParaTabConfig extends TestBase {
	
	
	public  void testCreate() {	
		 ParaTabCacheConfig  paraTabCacheConfig = (ParaTabCacheConfig)SpringApplicationContext.getContext().getBean("paraTabCacheConfig");
		 List<ParaTabCacheInfo>  list = paraTabCacheConfig.getConfigList();
		 for(ParaTabCacheInfo info:list){
			 System.out.println("tableName="+info.getTableName());
			 System.out.println("pkStr="+info.getPkStr());
			 System.out.println("hanlderClass="+info.getHanlderClass());
			 System.out.println("queryCon="+info.getQueryCon());
			 System.out.println("expirePeriod="+info.getExpirePeriod());
		 }
	}
	
	
	

	


}
