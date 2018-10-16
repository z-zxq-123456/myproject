package com.dcits.ensemble.om.oms.module.app;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by oms on 2016/02/25 15:47:29.
 */
public class EcmAppValrule extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_APP_VALRULE.APP_VALRULE_ID 
     */
    @TablePk(index = 1)
    private Integer appValruleId;

    /**
     * This field is ECM_APP_VALRULE.SERV_RULE_ID 
     */
    private Integer servRuleId;

    /**
     * This field is ECM_APP_VALRULE.APP_UPG_ID 
     */
    private Integer appUpgId;
    
    private String appSerClsnm;
    
    private String servRuleTypeName;
    
    private String  routerCondName;
    
    private String serMtdCnm;
    
    private String servRuleSelf;
    
    private String servRuleType;
    
    private String appSersource;
    
    private String appSerClassName;
    
    private String appSerName;
    
    private String serMtdEnm;
    
    private String routerArgsPos;
    
    private String routerArgsType;
    
    private String routerArgsTypeName;
    
    private String routerColCn;
    
    private String routerCondVal;
    
    
    

    /**
     * This field is ECM_APP_VALRULE.APP_VALRULE_TYPE 
     */
    private String appValruleType;
    
    private String appValruleTypeName;
    

    /**
     * @return the value of  ECM_APP_VALRULE.APP_VALRULE_ID 
     */
    public Integer getAppValruleId() {
        return appValruleId;
    }

    /**
     * @param appValruleId the value for ECM_APP_VALRULE.APP_VALRULE_ID 
     */
    public void setAppValruleId(Integer appValruleId) {
        this.appValruleId = appValruleId;
    }

    /**
     * @return the value of  ECM_APP_VALRULE.SERV_RULE_ID 
     */
    public Integer getServRuleId() {
        return servRuleId;
    }

    /**
     * @param servRuleId the value for ECM_APP_VALRULE.SERV_RULE_ID 
     */
    public void setServRuleId(Integer servRuleId) {
        this.servRuleId = servRuleId;
    }

    /**
     * @return the value of  ECM_APP_VALRULE.APP_UPG_ID 
     */
    public Integer getAppUpgId() {
        return appUpgId;
    }

    /**
     * @param appUpgId the value for ECM_APP_VALRULE.APP_UPG_ID 
     */
    public void setAppUpgId(Integer appUpgId) {
        this.appUpgId = appUpgId;
    }

    /**
     * @return the value of  ECM_APP_VALRULE.APP_VALRULE_TYPE 
     */
    public String getAppValruleType() {
        return appValruleType;
    }

    /**
     * @param appValruleType the value for ECM_APP_VALRULE.APP_VALRULE_TYPE 
     */
    public void setAppValruleType(String appValruleType) {
        this.appValruleType = appValruleType == null ? null : appValruleType.trim();
    }

	/**
	 * @return the appSerClsnm
	 */
	public String getAppSerClsnm() {
		return appSerClsnm;
	}

	/**
	 * @param appSerClsnm the appSerClsnm to set
	 */
	public void setAppSerClsnm(String appSerClsnm) {
		this.appSerClsnm = appSerClsnm;
	}

	/**
	 * @return the servRuleTypeName
	 */
	public String getServRuleTypeName() {
		return servRuleTypeName;
	}

	/**
	 * @param servRuleTypeName the servRuleTypeName to set
	 */
	public void setServRuleTypeName(String servRuleTypeName) {
		this.servRuleTypeName = servRuleTypeName;
	}

	/**
	 * @return the routerCondName
	 */
	public String getRouterCondName() {
		return routerCondName;
	}

	/**
	 * @param routerCondName the routerCondName to set
	 */
	public void setRouterCondName(String routerCondName) {
		this.routerCondName = routerCondName;
	}

	/**
	 * @return the serMtdCnm
	 */
	public String getSerMtdCnm() {
		return serMtdCnm;
	}

	/**
	 * @param serMtdCnm the serMtdCnm to set
	 */
	public void setSerMtdCnm(String serMtdCnm) {
		this.serMtdCnm = serMtdCnm;
	}

	/**
	 * @return the servRuleSelf
	 */
	public String getServRuleSelf() {
		return servRuleSelf;
	}

	/**
	 * @param servRuleSelf the servRuleSelf to set
	 */
	public void setServRuleSelf(String servRuleSelf) {
		this.servRuleSelf = servRuleSelf;
	}

	/**
	 * @return the servRuleType
	 */
	public String getServRuleType() {
		return servRuleType;
	}

	/**
	 * @param servRuleType the servRuleType to set
	 */
	public void setServRuleType(String servRuleType) {
		this.servRuleType = servRuleType;
	}

	/**
	 * @return the appSerClassName
	 */
	public String getAppSerClassName() {
		return appSerClassName;
	}

	/**
	 * @param appSerClassName the appSerClassName to set
	 */
	public void setAppSerClassName(String appSerClassName) {
		this.appSerClassName = appSerClassName;
	}

	/**
	 * @return the appSersource
	 */
	public String getAppSersource() {
		return appSersource;
	}

	/**
	 * @param appSersource the appSersource to set
	 */
	public void setAppSersource(String appSersource) {
		this.appSersource = appSersource;
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
	 * @return the serMtdEnm
	 */
	public String getSerMtdEnm() {
		return serMtdEnm;
	}

	/**
	 * @param serMtdEnm the serMtdEnm to set
	 */
	public void setSerMtdEnm(String serMtdEnm) {
		this.serMtdEnm = serMtdEnm;
	}

	/**
	 * @return the routerArgsPos
	 */
	public String getRouterArgsPos() {
		return routerArgsPos;
	}

	/**
	 * @param routerArgsPos the routerArgsPos to set
	 */
	public void setRouterArgsPos(String routerArgsPos) {
		this.routerArgsPos = routerArgsPos;
	}

	/**
	 * @return the routerArgsType
	 */
	public String getRouterArgsType() {
		return routerArgsType;
	}

	/**
	 * @param routerArgsType the routerArgsType to set
	 */
	public void setRouterArgsType(String routerArgsType) {
		this.routerArgsType = routerArgsType;
	}

	/**
	 * @return the routerArgsTypeName
	 */
	public String getRouterArgsTypeName() {
		return routerArgsTypeName;
	}

	/**
	 * @param routerArgsTypeName the routerArgsTypeName to set
	 */
	public void setRouterArgsTypeName(String routerArgsTypeName) {
		this.routerArgsTypeName = routerArgsTypeName;
	}

	/**
	 * @return the routerColCn
	 */
	public String getRouterColCn() {
		return routerColCn;
	}

	/**
	 * @param routerColCn the routerColCn to set
	 */
	public void setRouterColCn(String routerColCn) {
		this.routerColCn = routerColCn;
	}

	/**
	 * @return the routerCondVal
	 */
	public String getRouterCondVal() {
		return routerCondVal;
	}

	/**
	 * @param routerCondVal the routerCondVal to set
	 */
	public void setRouterCondVal(String routerCondVal) {
		this.routerCondVal = routerCondVal;
	}

	/**
	 * @return the appValruleTypeName
	 */
	public String getAppValruleTypeName() {
		return appValruleTypeName;
	}

	/**
	 * @param appValruleTypeName the appValruleTypeName to set
	 */
	public void setAppValruleTypeName(String appValruleTypeName) {
		this.appValruleTypeName = appValruleTypeName;
	}

}