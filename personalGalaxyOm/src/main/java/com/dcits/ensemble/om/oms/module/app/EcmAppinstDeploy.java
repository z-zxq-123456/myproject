package com.dcits.ensemble.om.oms.module.app;


import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by tangxl on 2015/08/26 09:44:42.
 */
public class EcmAppinstDeploy extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_APPINST_DEPLOY.APP_DEPLOY_ID 
     */
    @TablePk(index = 1)
    private Integer appDeployId;
    /**
     * This field is ECM_APPINST_DEPLOY.APP_INTANT_ID 
     */
    private Integer appIntantId;
    /**
     * This field is ECM_APPINST_DEPLOY.APP_VER_ID 
     */   
    private Integer appVerId;

    /**
     * This field is ECM_APPINST_DEPLOY.APP_DEPLOY_DATE 
     */
    private String appDeployDate;
    /**
     * This field is ECM_APPINST_DEPLOY.APP_DEPLOY_USERID 
     */
    private String appDeployUserid;
    
    private String appName;
    
    private String appIntantName;
    
    private String serIp;
    
    private String startTime;
    
    private String endTime;
    
    private String userId;
    
    private Integer appId;
    
    
    public String getAppIntantName() {
		return appIntantName;
	}

	public void setAppIntantName(String appIntantName) {
		this.appIntantName = appIntantName;
	}

	public String getSerIp() {
		return serIp;
	}

	public void setSerIp(String serIp) {
		this.serIp = serIp;
	}

	public String getAppIntantVer() {
		return appIntantVer;
	}

	public void setAppIntantVer(String appIntantVer) {
		this.appIntantVer = appIntantVer;
	}

	public String getAppPath() {
		return appPath;
	}

	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}

	public String getAppPort() {
		return appPort;
	}

	public void setAppPort(String appPort) {
		this.appPort = appPort;
	}

	public String getAppWork() {
		return appWork;
	}

	public void setAppWork(String appWork) {
		this.appWork = appWork;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String appIntantVer;
    
    private String appPath;
    
    private String appPort;
    
    private String appWork;
    
    private String userName;
    
    public Integer getAppDeployId() {
		return appDeployId;
	}

	public void setAppDeployId(Integer appDeployId) {
		this.appDeployId = appDeployId;
	}

	/**
     * @return the value of  ECM_APPINST_DEPLOY.APP_INTANT_ID 
     */
    public Integer getAppIntantId() {
        return appIntantId;
    }

    /**
     * @param appIntantId the value for ECM_APPINST_DEPLOY.APP_INTANT_ID 
     */
    public void setAppIntantId(Integer appIntantId) {
        this.appIntantId = appIntantId;
    }
    /**
     * @return the value of  ECM_APPINST_DEPLOY.APP_VER_ID 
     */
    public Integer getAppVerId() {
        return appVerId;
    }

    /**
     * @param appVerId the value for ECM_APPINST_DEPLOY.APP_VER_ID 
     */
    public void setAppVerId(Integer appVerId) {
        this.appVerId = appVerId;
    }
    /**
     * @return the value of  ECM_APPINST_DEPLOY.APP_DEPLOY_DATE 
     */
    public String getAppDeployDate() {
        return appDeployDate;
    }

    /**
     * @param appDeployDate the value for ECM_APPINST_DEPLOY.APP_DEPLOY_DATE 
     */
    public void setAppDeployDate(String appDeployDate) {
        this.appDeployDate = appDeployDate ;
    }
    /**
     * @return the value of  ECM_APPINST_DEPLOY.APP_DEPLOY_USERID 
     */
    public String getAppDeployUserid() {
        return appDeployUserid;
    }

    /**
     * @param appDeployUserid the value for ECM_APPINST_DEPLOY.APP_DEPLOY_USERID 
     */
    public void setAppDeployUserid(String appDeployUserid) {
        this.appDeployUserid = appDeployUserid;
    }

	/**
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * @param appName the appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	
	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the appId
	 */
	public Integer getAppId() {
		return appId;
	}

	/**
	 * @param appId the appId to set
	 */
	public void setAppId(Integer appId) {
		this.appId = appId;
	}

}