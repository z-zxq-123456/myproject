package com.dcits.ensemble.om.oms.module.app;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by oms on 2016/02/25 15:47:29.
 */
public class EcmAppUpginfo extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_APP_UPGINFO.APP_UPG_ID 
     */
    @TablePk(index = 1)
    private Integer appUpgId;

    /**
     * This field is ECM_APP_UPGINFO.APP_ID 
     */
    private Integer appId;
    
    private String appName;
    
    private Integer appSerId;
    
    private Integer serMtdId;
    
    private Integer routerColId;
    
    private Integer routerCondId;
    
    private Integer servRuleId;
    
    private Integer appValruleId;
    
    private String appUpgOpertime;
    
    private String startTime;
    
    private String endTime;
    /**
     * This field is ECM_APP_UPGINFO.UPGFLOW_NODE_ID 
     */
    private Integer upgflowNodeId;
    /**
     * This field is ECM_APP_UPGINFO.UPGFLOW_NODE_ID 
     */
    private Integer  oldUpgflowNodeId;

    /**
     * This field is ECM_APP_UPGINFO.APP_UPG_STATUS 
     */
    private String appUpgStatus;
    
    private String appUpgStatusName;

    /**
     * This field is ECM_APP_UPGINFO.APP_UPG_NEWVERNO 
     */
    private Integer appUpgNewverno;
    /**
    * This field is ECM_UPGFLOW_NODE.UPGFLOW_NODE_SEQ 
    */
    private Integer upgflowNodeSeq;

    /**
     * This field is ECM_APP_UPGINFO.APP_UPG_OLDVERNO 
     */
    private Integer appUpgOldverno;
    
    /**
     * This field is ECM_APP_UPGINFO.APP_UPG_INSTIDSTR 
     */
    private String appUpgInstidstr;

    /**
     * @return the value of  ECM_APP_UPGINFO.APP_UPG_ID 
     */
    public Integer getAppUpgId() {
        return appUpgId;
    }

    /**
     * @param appUpgId the value for ECM_APP_UPGINFO.APP_UPG_ID 
     */
    public void setAppUpgId(Integer appUpgId) {
        this.appUpgId = appUpgId;
    }

    /**
     * @return the value of  ECM_APP_UPGINFO.APP_ID 
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * @param appId the value for ECM_APP_UPGINFO.APP_ID 
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    

    public Integer getUpgflowNodeId() {
		return upgflowNodeId;
	}

	public void setUpgflowNodeId(Integer upgflowNodeId) {
		this.upgflowNodeId = upgflowNodeId;
	}
	
	public Integer getOldUpgflowNodeId() {
		return oldUpgflowNodeId;
	}

	public void setOldUpgflowNodeId(Integer oldUpgflowNodeId) {
		this.oldUpgflowNodeId = oldUpgflowNodeId;
	}

	/**
     * @return the value of  ECM_UPGFLOW_NODE.UPGFLOW_NODE_SEQ 
     */
    public Integer getUpgflowNodeSeq() {
        return upgflowNodeSeq;
    }

    /**
     * @param upgflowNodeSeq the value for ECM_UPGFLOW_NODE.UPGFLOW_NODE_SEQ 
     */
    public void setUpgflowNodeSeq(Integer upgflowNodeSeq) {
        this.upgflowNodeSeq = upgflowNodeSeq;
    }
	/**
     * @return the value of  ECM_APP_UPGINFO.APP_UPG_STATUS 
     */
    public String getAppUpgStatus() {
        return appUpgStatus;
    }

    /**
     * @param appUpgStatus the value for ECM_APP_UPGINFO.APP_UPG_STATUS 
     */
    public void setAppUpgStatus(String appUpgStatus) {
        this.appUpgStatus = appUpgStatus == null ? null : appUpgStatus.trim();
    }

    /**
     * @return the value of  ECM_APP_UPGINFO.APP_UPG_NEWVERNO 
     */
    public Integer getAppUpgNewverno() {
        return appUpgNewverno;
    }

    /**
     * @param appUpgNewverno the value for ECM_APP_UPGINFO.APP_UPG_NEWVERNO 
     */
    public void setAppUpgNewverno(Integer appUpgNewverno) {
        this.appUpgNewverno = appUpgNewverno;
    }

    /**
     * @return the value of  ECM_APP_UPGINFO.APP_UPG_OLDVERNO 
     */
    public Integer getAppUpgOldverno() {
        return appUpgOldverno;
    }

    /**
     * @param appUpgOldverno the value for ECM_APP_UPGINFO.APP_UPG_OLDVERNO 
     */
    public void setAppUpgOldverno(Integer appUpgOldverno) {
        this.appUpgOldverno = appUpgOldverno;
    }

	/**
	 * @return the appValruleId
	 */
	public Integer getAppValruleId() {
		return appValruleId;
	}

	/**
	 * @param appValruleId the appValruleId to set
	 */
	public void setAppValruleId(Integer appValruleId) {
		this.appValruleId = appValruleId;
	}

	/**
	 * @return the servRuleId
	 */
	public Integer getServRuleId() {
		return servRuleId;
	}

	/**
	 * @param servRuleId the servRuleId to set
	 */
	public void setServRuleId(Integer servRuleId) {
		this.servRuleId = servRuleId;
	}

	/**
	 * @return the routerCondId
	 */
	public Integer getRouterCondId() {
		return routerCondId;
	}

	/**
	 * @param routerCondId the routerCondId to set
	 */
	public void setRouterCondId(Integer routerCondId) {
		this.routerCondId = routerCondId;
	}

	/**
	 * @return the routerColId
	 */
	public Integer getRouterColId() {
		return routerColId;
	}

	/**
	 * @param routerColId the routerColId to set
	 */
	public void setRouterColId(Integer routerColId) {
		this.routerColId = routerColId;
	}

	/**
	 * @return the serMtdId
	 */
	public Integer getSerMtdId() {
		return serMtdId;
	}

	/**
	 * @param serMtdId the serMtdId to set
	 */
	public void setSerMtdId(Integer serMtdId) {
		this.serMtdId = serMtdId;
	}

	/**
	 * @return the appSerId
	 */
	public Integer getAppSerId() {
		return appSerId;
	}

	/**
	 * @param appSerId the appSerId to set
	 */
	public void setAppSerId(Integer appSerId) {
		this.appSerId = appSerId;
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
	 * @return the appUpgStatusName
	 */
	public String getAppUpgStatusName() {
		return appUpgStatusName;
	}

	/**
	 * @param appUpgStatusName the appUpgStatusName to set
	 */
	public void setAppUpgStatusName(String appUpgStatusName) {
		this.appUpgStatusName = appUpgStatusName;
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
	 * @return the appUpgOpertime
	 */
	public String getAppUpgOpertime() {
		return appUpgOpertime;
	}

	/**
	 * @param appUpgOpertime the appUpgOpertime to set
	 */
	public void setAppUpgOpertime(String appUpgOpertime) {
		this.appUpgOpertime = appUpgOpertime;
	}

	public String getAppUpgInstidstr() {
		return appUpgInstidstr;
	}

	public void setAppUpgInstidstr(String appUpgInstidstr) {
		this.appUpgInstidstr = appUpgInstidstr;
	}
	
	
}