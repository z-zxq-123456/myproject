package com.dcits.galaxy.dtp.resume;


import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.dcits.galaxy.core.client.ServiceProxy;
import com.dcits.galaxy.dtp.exception.DTPException;
import com.dcits.galaxy.dtp.resume.trunk.TrunkLockListener;
import com.dcits.galaxy.dtp.resume.trunk.TrunkResumer;
import com.dcits.galaxy.dtp.zk.DLock;
import com.dcits.galaxy.dtp.zk.DLockListener;
import com.dcits.galaxy.dtp.zk.ZKSingleBuilder;

public class Resumer {

	private String zkServers = null;
	private int zkSessionTimeout = 5000;
	private int zkConnectionTimeout = 5000;

	private String appGroup = null;
	private String appName = null;
	private int internal = 60;
	private int timeout = 180;
	
	private TrunkResumer trunkResumer = null;
	private DLock trunkLock = null;

	public void init() {
		
		check();
		
		// 初始化ZooKeeper相关信息
		ZKSingleBuilder.setZkServers(zkServers);
		ZKSingleBuilder.setSessionTimeout(zkSessionTimeout);
		ZKSingleBuilder.setConnectionTimeout(zkConnectionTimeout);
		RecoveryConfig.init(appGroup);

		// 启动该Jvm上的针对定时恢复调度
		trunkResumer = new TrunkResumer();
		trunkResumer.setAppName(appName);
		trunkResumer.setAppGroup(appGroup);
		trunkResumer.setInternal(internal);
		trunkResumer.setTimeout(timeout);

		String trunkLockPath = RecoveryConfig.getTrunkLockPath(appGroup);
		trunkLock = new DLock(trunkLockPath);
		DLockListener listener = new TrunkLockListener(trunkResumer);
		trunkLock.addListener(listener);
		trunkLock.tryLock();
	}
	
	private void check(){
		
		if(appName == null) {
			appName = ConfigUtils.getProperty("galaxy.application.name");
		}
		
		if(appGroup == null) {
			appGroup = ConfigUtils.getProperty("galaxy.application.group");
			
			if(appGroup == null || appGroup.trim().equals("")) {
				throw new DTPException("appGroup must not null");
			}
		}
		
		if(zkServers == null) {
			zkServers = ServiceProxy.getInstance().getZkUrl();
			
			if(zkServers == null || zkServers.trim().equals("")) {
				throw new DTPException("zkServers must not null");
			}
			
		}
		
		zkServers = zkServers.trim().replaceAll("zookeeper://", "").replace("?backup=", ",");
		
	}
	
	public void destroy(){
		if(trunkLock != null){
			trunkLock.unLock();
			trunkLock = null;
		}
		if(trunkResumer != null){
			trunkResumer.shutdown();
			trunkResumer = null;
		}
		ZKSingleBuilder.destroy();
	}

	public String getZkServers() {
		return zkServers;
	}

	public void setZkServers(String zkServers) {
		this.zkServers = zkServers;
	}

	public int getZkSessionTimeout() {
		return zkSessionTimeout;
	}

	public void setZkSessionTimeout(int zkSessionTimeout) {
		this.zkSessionTimeout = zkSessionTimeout;
	}

	public int getZkConnectionTimeout() {
		return zkConnectionTimeout;
	}

	public void setZkConnectionTimeout(int zkConnectionTimeout) {
		this.zkConnectionTimeout = zkConnectionTimeout;
	}

	public String getAppGroup() {
		return appGroup;
	}

	public void setAppGroup(String appGroup) {
		this.appGroup = appGroup;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public int getInternal() {
		return internal;
	}

	public void setInternal(int internal) {
		this.internal = internal;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}
