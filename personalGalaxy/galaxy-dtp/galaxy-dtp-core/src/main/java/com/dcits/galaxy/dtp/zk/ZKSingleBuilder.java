package com.dcits.galaxy.dtp.zk;

import org.I0Itec.zkclient.ZkClient;

/**
 * 负责实例化ZKClient，并维持全局唯一的一份ZKClient实例
 * @author Yin.Weicai
 */
public class ZKSingleBuilder {
	
	private static ZkClient zkClient = null;
	
	private static int sessionTimeout = 10*1000;
	
	private static int connectionTimeout = 10*1000;
	
	private static String zkServers = "127.0.0.1:2181";
	
	public static synchronized ZkClient getZkClient(){
		if( zkClient == null){
			zkClient = new ZkClient(zkServers, sessionTimeout, connectionTimeout);
		}
		return zkClient;
	}
	
	public static synchronized void destroy(){
		if(zkClient != null){
			zkClient.unsubscribeAll();
			zkClient.close();
			zkClient = null;
		}
	}

	public static int getSessionTimeout() {
		return sessionTimeout;
	}

	public static void setSessionTimeout(int sessionTimeout) {
		ZKSingleBuilder.sessionTimeout = sessionTimeout;
	}

	public static int getConnectionTimeout() {
		return connectionTimeout;
	}

	public static void setConnectionTimeout(int connectionTimeout) {
		ZKSingleBuilder.connectionTimeout = connectionTimeout;
	}

	public static String getZkServers() {
		return zkServers;
	}

	public static void setZkServers(String zkServers) {
		ZKSingleBuilder.zkServers = zkServers;
	}
}
