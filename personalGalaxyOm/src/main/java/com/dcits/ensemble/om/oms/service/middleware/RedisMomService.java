package com.dcits.ensemble.om.oms.service.middleware;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dcits.ensemble.om.oms.module.middleware.CacheModel;
import com.dcits.ensemble.om.oms.module.middleware.ServerModel;


 class RedisMomService {
	
	private static final Map<String,List<CacheModel>>  cacheInfo = new HashMap<String,List<CacheModel>>();
	
	private static final Map<String,ServerModel>  serverInfo = new HashMap<String,ServerModel>();
	
	public static void setCacheInfo(String server,List<CacheModel> list){
		cacheInfo.put(server,list);
	}
	
	public static List<CacheModel> getCacheInfo(String server){
		return cacheInfo.get(server);
	}
	
	public static void removeCacheInfo(String server){
		cacheInfo.remove(server);
	}
	
	public static void setServerInfo(String server,ServerModel model){
		serverInfo.put(server,model);
	}
	
	public static ServerModel getServerInfo(String server){
		return serverInfo.get(server);
	}
	
	public static void removeServerInfo(String server){
		serverInfo.remove(server);
	}

}
