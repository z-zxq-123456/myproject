package com.dcits.galaxy.oms.monitor.api.health;

import com.dcits.galaxy.base.bean.AbstractBean;

/**
 * HealthCheckResult* 
 * 健康检查返回结果
 * @author tangxlf
 * @date 2015-10-20
 */
public class HealthCheckResult extends AbstractBean {
    // serialVersionUID
    private static final long serialVersionUID = 1L; 
    
	private   int    healthStatus;  //健康检查状态  0健康  1不健康
    private   String message;       //检查信息  
	public int getHealthStatus() {
		return healthStatus;
	}
	public void setHealthStatus(int healthStatus) {
		this.healthStatus = healthStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
