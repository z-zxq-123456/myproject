package com.dcits.ensemble.om.oms.module.utils;


/**
 * 操作进度通知*
 * @author tangxlf
 * @date   2015-11-06
 */
public  class ProgressInfo{
	String  actionName; //动作名称
	long    startTime; //开始时间
	String  ip;        //实际执行动作的机器IP
	String  appName;   //应用名称
	int     progressStage;
	
	public int getProgressStage() {
		return progressStage;
	}
	public void addProgressStage() {
		progressStage++;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
}

