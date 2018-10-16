package com.dcits.ensemble.om.oms.module.app;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by oms on 2016/02/25 15:47:29.
 */
public class EcmAppUpgfor extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_APP_UPGFOR.APP_UPGFOR_ID 
     */
    @TablePk(index = 1)
    private Integer appUpgforId;

    /**
     * This field is ECM_APP_UPGFOR.APP_UPG_ID 
     */
    private Integer appUpgId;

    /**
     * This field is ECM_APP_UPGFOR.UPGFLOW_NODE_ID 
     */
    private Integer upgflowNodeId;
    
    private String upgflowNodeName;
    
    private String upgflowNodeUrl;

    /**
     * This field is ECM_APP_UPGFOR.APP_UPGFOR_TIME 
     */
    private String appUpgforTime;

    /**
     * This field is ECM_APP_UPGFOR.APP_UPGFOR_USERID 
     */
    private String appUpgforUserid;
    
    private String userName;

    /**
     * @return the value of  ECM_APP_UPGFOR.APP_UPGFOR_ID 
     */
    public Integer getAppUpgforId() {
        return appUpgforId;
    }

    /**
     * @param appUpgforId the value for ECM_APP_UPGFOR.APP_UPGFOR_ID 
     */
    public void setAppUpgforId(Integer appUpgforId) {
        this.appUpgforId = appUpgforId;
    }

    /**
     * @return the value of  ECM_APP_UPGFOR.APP_UPG_ID 
     */
    public Integer getAppUpgId() {
        return appUpgId;
    }

    /**
     * @param appUpgId the value for ECM_APP_UPGFOR.APP_UPG_ID 
     */
    public void setAppUpgId(Integer appUpgId) {
        this.appUpgId = appUpgId;
    }

    /**
     * @return the value of  ECM_APP_UPGFOR.UPGFLOW_NODE_ID 
     */
    public Integer getUpgflowNodeId() {
        return upgflowNodeId;
    }

    /**
     * @param upgflowNodeId the value for ECM_APP_UPGFOR.UPGFLOW_NODE_ID 
     */
    public void setUpgflowNodeId(Integer upgflowNodeId) {
        this.upgflowNodeId = upgflowNodeId;
    }

    /**
     * @return the value of  ECM_APP_UPGFOR.APP_UPGFOR_TIME 
     */
    public String getAppUpgforTime() {
        return appUpgforTime;
    }

    /**
     * @param appUpgforTime the value for ECM_APP_UPGFOR.APP_UPGFOR_TIME 
     */
    public void setAppUpgforTime(String appUpgforTime) {
        this.appUpgforTime = appUpgforTime == null ? null : appUpgforTime.trim();
    }

    /**
     * @return the value of  ECM_APP_UPGFOR.APP_UPGFOR_USERID 
     */
    public String getAppUpgforUserid() {
        return appUpgforUserid;
    }

    /**
     * @param appUpgforUserid the value for ECM_APP_UPGFOR.APP_UPGFOR_USERID 
     */
    public void setAppUpgforUserid(String appUpgforUserid) {
        this.appUpgforUserid = appUpgforUserid;
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

	/**
	 * @return the upgflowNodeName
	 */
	public String getUpgflowNodeName() {
		return upgflowNodeName;
	}

	/**
	 * @param upgflowNodeName the upgflowNodeName to set
	 */
	public void setUpgflowNodeName(String upgflowNodeName) {
		this.upgflowNodeName = upgflowNodeName;
	}

	/**
	 * @return the upgflowNodeUrl
	 */
	public String getUpgflowNodeUrl() {
		return upgflowNodeUrl;
	}

	/**
	 * @param upgflowNodeUrl the upgflowNodeUrl to set
	 */
	public void setUpgflowNodeUrl(String upgflowNodeUrl) {
		this.upgflowNodeUrl = upgflowNodeUrl;
	}
}