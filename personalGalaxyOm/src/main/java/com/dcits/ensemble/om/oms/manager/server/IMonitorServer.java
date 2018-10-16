package com.dcits.ensemble.om.oms.manager.server;

import com.dcits.ensemble.om.oms.module.server.EcmServerInfo;

import java.util.Map;

/**
 * 服务器监控接口* 
 * @author tangxlf
 * @date 2016-01-18 
 */
public interface IMonitorServer {	
   /**
    * 执行监控服务器相关信息	 
    * @param EcmServerInfo serverInfo    服务器信息 	 
	* @return Map<String,String>         服务器相关信息
	*/
	public Map<String,String> executeMonitor(EcmServerInfo serverInfo);
  
}
