package com.dcits.ensemble.om.oms.manager.server;

import com.dcits.ensemble.om.oms.common.DataUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class MonitorServerFactory {
	private  final Map<String,IMonitorServer> monitorServerMap =new HashMap<String,IMonitorServer>();	
	@Resource
	LinuxMonitorServer  linuxMonitorServer;
	@Resource
	UnixMonitorServer   unixMonitorServer;
	
	void setMap(){
		monitorServerMap.put(SysConfigConstants.LINUX_OS,linuxMonitorServer);		
		monitorServerMap.put(SysConfigConstants.UNIX_OS, unixMonitorServer); 
	}
	
	public  IMonitorServer getIMonitorServer(String osCode){
		if(monitorServerMap.isEmpty()){
			setMap();
		}
		DataUtil.checkNull("操作系统代码",osCode);
		return monitorServerMap.get(osCode);
	}

}
