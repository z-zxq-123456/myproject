package com.dcits.ensemble.om.oms.module.app;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by tangxl on 2015/08/11 10:00:01.
 */
public class EcmAppInfo extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_APP_INFO.APP_ID 
     */
    @TablePk(index = 1)
    private Integer appId;

    /**
     * This field is ECM_APP_INFO.APP_NAME 
     */
    private String appName;
    /**
     * This field is ECM_APP_INFO.APP_SIMPEN_NM 
     */
    private String appSimpenNm;
    /**
     * This field is ECM_APP_INFO.APP_DESC 
     */
    private String appDesc;
    /**
     * This field is ECM_APP_INFO.APP_TYPE 
     */
    private String appType;
    
    private Integer midwareRedisId;
    
    private Integer midwareZKId;
    
    private Integer midwareDBId;
    
    
    private String  redisMidwareName;
    
    private String  zkMidwareName;
    
    private String  dbMidwareName;
    /**
     * This field is ECM_APP_INFO.APP_PATH
     */
    private String appPath;
    /**
     * This field is ECM_APP_INFO.APP_PORT 
     */
    private String appPort;
    /**
     * This field is ECM_APP_INFO.APP_WORK 
     */
    private String appWork;

	private String  appTypeName;
	
	private Integer maxAppVerNum;

    public Integer getMaxAppVerNum() {
		return maxAppVerNum;
	}

	public void setMaxAppVerNum(Integer maxAppVerNum) {
		this.maxAppVerNum = maxAppVerNum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	/**
     * @return the value of  ECM_APP_INFO.APP_ID 
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * @param appId the value for ECM_APP_INFO.APP_ID 
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }
    /**
     * @return the value of  ECM_APP_INFO.APP_NAME 
     */
    public String getAppName() {
        return appName;
    }

    /**
     * @param appName the value for ECM_APP_INFO.APP_NAME 
     */
    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }
    /**
     * @return the value of  ECM_APP_INFO.APP_SIMPEN_NM 
     */
    public String getAppSimpenNm() {
        return appSimpenNm;
    }

    /**
     * @param appSimpenNm the value for ECM_APP_INFO.APP_SIMPEN_NM 
     */
    public void setAppSimpenNm(String appSimpenNm) {
        this.appSimpenNm = appSimpenNm == null ? null : appSimpenNm.trim();
    }
    /**
     * @return the value of  ECM_APP_INFO.APP_DESC 
     */
    public String getAppDesc() {
        return appDesc;
    }

    /**
     * @param appDesc the value for ECM_APP_INFO.APP_DESC 
     */
    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc == null ? null : appDesc.trim();
    }
    /**
     * @return the value of  ECM_APP_INFO.APP_TYPE 
     */
    public String getAppType() {
        return appType;
    }

    /**
     * @param appType the value for ECM_APP_INFO.APP_TYPE 
     */
    public void setAppType(String appType) {
        this.appType = appType == null ? null : appType.trim();
    }

    public String getAppTypeName() {
		return appTypeName;
	}

	public void setAppTypeName(String appTypeName) {
		this.appTypeName = appTypeName;
		
	}

	/**
	 * @return the zkMidwareName
	 */
	public String getZkMidwareName() {
		return zkMidwareName;
	}

	/**
	 * @param zkMidwareName the zkMidwareName to set
	 */
	public void setZkMidwareName(String zkMidwareName) {
		this.zkMidwareName = zkMidwareName;
	}

	/**
	 * @return the redisMidwareName
	 */
	public String getRedisMidwareName() {
		return redisMidwareName;
	}

	/**
	 * @param redisMidwareName the redisMidwareName to set
	 */
	public void setRedisMidwareName(String redisMidwareName) {
		this.redisMidwareName = redisMidwareName;
	}

	/**
	 * @return the dbMidwareName
	 */
	public String getDbMidwareName() {
		return dbMidwareName;
	}

	/**
	 * @param dbMidwareName the dbMidwareName to set
	 */
	public void setDbMidwareName(String dbMidwareName) {
		this.dbMidwareName = dbMidwareName;
	}

	/**
	 * @return the midwareRedisId
	 */
	public Integer getMidwareRedisId() {
		return midwareRedisId;
	}

	/**
	 * @param midwareRedisId the midwareRedisId to set
	 */
	public void setMidwareRedisId(Integer midwareRedisId) {
		this.midwareRedisId = midwareRedisId;
	}

	/**
	 * @return the midwareZKId
	 */
	public Integer getMidwareZKId() {
		return midwareZKId;
	}

	/**
	 * @param midwareZKId the midwareZKId to set
	 */
	public void setMidwareZKId(Integer midwareZKId) {
		this.midwareZKId = midwareZKId;
	}

	/**
	 * @return the midwareDBId
	 */
	public Integer getMidwareDBId() {
		return midwareDBId;
	}

	/**
	 * @param midwareDBId the midwareDBId to set
	 */
	public void setMidwareDBId(Integer midwareDBId) {
		this.midwareDBId = midwareDBId;
	}
}