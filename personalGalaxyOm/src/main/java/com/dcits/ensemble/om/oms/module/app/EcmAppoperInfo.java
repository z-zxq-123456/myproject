package com.dcits.ensemble.om.oms.module.app;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by tangxl on 2015/08/27 14:19:17.
 */
public class EcmAppoperInfo extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_APPOPER_INFO.ECM_APPOPER_ID 
     */
    @TablePk(index = 1)
    private Integer ecmAppoperId;

    /**
     * This field is ECM_APPOPER_INFO.APP_INTANT_ID 
     */
    private Integer appIntantId;
    /**
     * This field is ECM_APPOPER_INFO.ECM_APPOPER_TYPE 
     */
    private String ecmAppoperType;
    /**
     * This field is ECM_APPOPER_INFO.ECM_APPOPER_TIME 
     */
    private String ecmAppoperTime;
    /**
     * This field is ECM_APPOPER_INFO.ECM_APPOPER_USERID 
     */
    private String ecmAppoperUserid;
    
    private String startTime;
    
    private String endTime;
    
    private String userId;
    
    private Integer appId;
    
    private String paraCode;
    
    private String appName;
    
    private String appIntantName;
    
    private String serIp;
    
    private Integer appVerId;
    
    private String userName;
    
    private String ecmAppoperTypeName;
    
    private String appPort;
    
    private String appPath; 
    
    private String  appIntantVer;

    /**
     * @return the value of  ECM_APPOPER_INFO.ECM_APPOPER_ID 
     */
    public Integer getEcmAppoperId() {
        return ecmAppoperId;
    }

    /**
     * @param ecmAppoperId the value for ECM_APPOPER_INFO.ECM_APPOPER_ID 
     */
    public void setEcmAppoperId(Integer ecmAppoperId) {
        this.ecmAppoperId = ecmAppoperId;
    }
    /**
     * @return the value of  ECM_APPOPER_INFO.APP_INTANT_ID 
     */
    public Integer getAppIntantId() {
        return appIntantId;
    }

    /**
     * @param appIntantId the value for ECM_APPOPER_INFO.APP_INTANT_ID 
     */
    public void setAppIntantId(Integer appIntantId) {
        this.appIntantId = appIntantId;
    }
    /**
     * @return the value of  ECM_APPOPER_INFO.ECM_APPOPER_TYPE 
     */
    public String getEcmAppoperType() {
        return ecmAppoperType;
    }

    /**
     * @param ecmAppoperType the value for ECM_APPOPER_INFO.ECM_APPOPER_TYPE 
     */
    public void setEcmAppoperType(String ecmAppoperType) {
        this.ecmAppoperType = ecmAppoperType == null ? null : ecmAppoperType.trim();
    }
    /**
     * @return the value of  ECM_APPOPER_INFO.ECM_APPOPER_TIME 
     */
    public String getEcmAppoperTime() {
        return ecmAppoperTime;
    }

    /**
     * @param ecmAppoperTime the value for ECM_APPOPER_INFO.ECM_APPOPER_TIME 
     */
    public void setEcmAppoperTime(String ecmAppoperTime) {
        this.ecmAppoperTime = ecmAppoperTime == null ? null : ecmAppoperTime.trim();
    }
    /**
     * @return the value of  ECM_APPOPER_INFO.ECM_APPOPER_USERID 
     */
    public String getEcmAppoperUserid() {
        return ecmAppoperUserid;
    }

    /**
     * @param ecmAppoperUserid the value for ECM_APPOPER_INFO.ECM_APPOPER_USERID 
     */
    public void setEcmAppoperUserid(String ecmAppoperUserid) {
        this.ecmAppoperUserid = ecmAppoperUserid;
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

	/**
	 * @return the paraCode
	 */
	public String getParaCode() {
		return paraCode;
	}

	/**
	 * @param paraCode the paraCode to set
	 */
	public void setParaCode(String paraCode) {
		this.paraCode = paraCode;
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
	 * @return the appIntantName
	 */
	public String getAppIntantName() {
		return appIntantName;
	}

	/**
	 * @param appIntantName the appIntantName to set
	 */
	public void setAppIntantName(String appIntantName) {
		this.appIntantName = appIntantName;
	}

	/**
	 * @return the serIp
	 */
	public String getSerIp() {
		return serIp;
	}

	/**
	 * @param serIp the serIp to set
	 */
	public void setSerIp(String serIp) {
		this.serIp = serIp;
	}

	/**
	 * @return the appVerId
	 */
	public Integer getAppVerId() {
		return appVerId;
	}

	/**
	 * @param appVerId the appVerId to set
	 */
	public void setAppVerId(Integer appVerId) {
		this.appVerId = appVerId;
	}


	/**
	 * @return the appPort
	 */
	public String getAppPort() {
		return appPort;
	}

	/**
	 * @param appPort the appPort to set
	 */
	public void setAppPort(String appPort) {
		this.appPort = appPort;
	}

	/**
	
	/**
	 * @return the appPath
	 */
	public String getAppPath() {
		return appPath;
	}

	/**
	 * @param appPath the appPath to set
	 */
	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}

	/**
	 * @return the appIntantVer
	 */
	public String getAppIntantVer() {
		return appIntantVer;
	}

	/**
	 * @param appIntantVer the appIntantVer to set
	 */
	public void setAppIntantVer(String appIntantVer) {
		this.appIntantVer = appIntantVer;
	}

	/**
	 * @return the ecmAppoperTypeName
	 */
	public String getEcmAppoperTypeName() {
		return ecmAppoperTypeName;
	}

	/**
	 * @param ecmAppoperTypeName the ecmAppoperTypeName to set
	 */
	public void setEcmAppoperTypeName(String ecmAppoperTypeName) {
		this.ecmAppoperTypeName = ecmAppoperTypeName;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}