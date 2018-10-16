package com.dcits.ensemble.om.oms.module.app;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by oms on 2016/02/25 15:47:29.
 */
public class EcmUpgflowNode extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_UPGFLOW_NODE.UPGFLOW_NODE_ID 
     */
    @TablePk(index = 1)
    private Integer upgflowNodeId;

    /**
     * This field is ECM_UPGFLOW_NODE.UPGFLOW_NODE_NAME 
     */
    private String upgflowNodeName;
    /**
     * This field is ECM_UPGFLOW_NODE.APP_UPG_ 
     */
    private Integer  appUpgId;
    /**
     * This field is ECM_UPGFLOW_NODE.UPGFLOW_NODE_SEQ 
     */
    private Integer upgflowNodeSeq;

    /**
     * This field is ECM_UPGFLOW_NODE.UPGFLOW_NODE_URL 
     */
    private String upgflowNodeUrl;

    /**
     * @return the value of  ECM_UPGFLOW_NODE.UPGFLOW_NODE_ID 
     */
    public Integer getUpgflowNodeId() {
        return upgflowNodeId;
    }

    /**
     * @param upgflowNodeId the value for ECM_UPGFLOW_NODE.UPGFLOW_NODE_ID 
     */
    public void setUpgflowNodeId(Integer upgflowNodeId) {
        this.upgflowNodeId = upgflowNodeId;
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
     * @return the value of  ECM_UPGFLOW_NODE.UPGFLOW_NODE_URL 
     */
    public String getUpgflowNodeUrl() {
        return upgflowNodeUrl;
    }

    /**
     * @param upgflowNodeUrl the value for ECM_UPGFLOW_NODE.UPGFLOW_NODE_URL 
     */
    public void setUpgflowNodeUrl(String upgflowNodeUrl) {
        this.upgflowNodeUrl = upgflowNodeUrl == null ? null : upgflowNodeUrl.trim();
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
}