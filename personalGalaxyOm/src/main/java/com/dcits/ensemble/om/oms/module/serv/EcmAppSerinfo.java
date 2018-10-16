package com.dcits.ensemble.om.oms.module.serv;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by oms on 2016/02/25 15:38:17.
 */
public class EcmAppSerinfo extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_APP_SERINFO.APP_SER_ID 
     */
    @TablePk(index = 1)
    private Integer appSerId;

    /**
     * This field is ECM_APP_SERINFO.APP_SER_NAME 
     */
    private String appSerName;

    /**
     * This field is ECM_APP_SERINFO.APP_SER_CLSNM 
     */
    private String appSerClsnm;

    /**
     * This field is ECM_APP_SERINFO.APP_SER_TYPE 
     */
    private String appSerType;
    
    private String appSerTypeName;

    /**
     * This field is ECM_APP_SERINFO.APP_SER_DESC 
     */
    private String appSerDesc;

    /**
     * @return the value of  ECM_APP_SERINFO.APP_SER_ID 
     */
    public Integer getAppSerId() {
        return appSerId;
    }

    /**
     * @param appSerId the value for ECM_APP_SERINFO.APP_SER_ID 
     */
    public void setAppSerId(Integer appSerId) {
        this.appSerId = appSerId;
    }

   

    /**
     * @return the value of  ECM_APP_SERINFO.APP_SER_CLSNM 
     */
    public String getAppSerClsnm() {
        return appSerClsnm;
    }

    /**
     * @param appSerClsnm the value for ECM_APP_SERINFO.APP_SER_CLSNM 
     */
    public void setAppSerClsnm(String appSerClsnm) {
        this.appSerClsnm = appSerClsnm == null ? null : appSerClsnm.trim();
    }

    /**
     * @return the value of  ECM_APP_SERINFO.APP_SER_TYPE 
     */
    public String getAppSerType() {
        return appSerType;
    }

    /**
     * @param appSerType the value for ECM_APP_SERINFO.APP_SER_TYPE 
     */
    public void setAppSerType(String appSerType) {
        this.appSerType = appSerType == null ? null : appSerType.trim();
    }

    /**
     * @return the value of  ECM_APP_SERINFO.APP_SER_DESC 
     */
    public String getAppSerDesc() {
        return appSerDesc;
    }

    /**
     * @param appSerDesc the value for ECM_APP_SERINFO.APP_SER_DESC 
     */
    public void setAppSerDesc(String appSerDesc) {
        this.appSerDesc = appSerDesc == null ? null : appSerDesc.trim();
    }

	/**
	 * @return the appSerName
	 */
	public String getAppSerName() {
		return appSerName;
	}

	/**
	 * @param appSerName the appSerName to set
	 */
	public void setAppSerName(String appSerName) {
		this.appSerName = appSerName;
	}

	/**
	 * @return the appSerTypeName
	 */
	public String getAppSerTypeName() {
		return appSerTypeName;
	}

	/**
	 * @param appSerTypeName the appSerTypeName to set
	 */
	public void setAppSerTypeName(String appSerTypeName) {
		this.appSerTypeName = appSerTypeName;
	}
}