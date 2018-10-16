package com.dcits.ensemble.om.oms.module.serv;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by oms on 2016/02/29 10:09:40.
 */
public class EcmServRule extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_SERV_RULE.SERV_RULE_ID 
     */
    @TablePk(index = 1)
    private Integer servRuleId;

    /**
     * This field is ECM_SERV_RULE.SERV_RULE_TYPE 
     */
    private String servRuleType;
    
    private String servRuleTypeName;

    /**
     * This field is ECM_SERV_RULE.ROUTER_COND_ID 
     */
    private Integer routerCondId;

    private String routerCondName;
    /**
     * This field is ECM_SERV_RULE.SER_MTD_ID 
     */
    private Integer serMtdId;
    
    private String serMtdCnm;

    /**
     * This field is ECM_SER_MTDINFO.SER_MTD_ENM 
     */
    private String serMtdEnm;

    /**
     * This field is ECM_SERV_RULE.RULE_STATUS 
     */
    private String ruleStatus;
    
    private String ruleStatusName;

    /**
     * This field is ECM_SERV_RULE.ROUTER_ARGS_POS 
     */
    private Integer routerArgsPos;

    /**
     * This field is ECM_SERV_RULE.ROUTER_ARGS_TYPE 
     */
    private String routerArgsType;
    
    private String routerArgsTypeName;

    /**
     * This field is ECM_SERV_RULE.SERV_RULE_SELF 
     */
    private String servRuleSelf;
    
    private String routerCondOper;
    
    private String routerCondVal;
    
    private String routerColCdn;
    
    private String appSerClsnm;
    
    private String appSerClassName;
    
    private String appSersource;
    

    private String routerColType;

    private String handlerClsName;

    private String handlerClsMthName;

    private String messageCode;

    private String messageType;

    private String serviceCode;
    /**
     * @return the value of  ECM_SERV_RULE.SERV_RULE_ID 
     */
    public Integer getServRuleId() {
        return servRuleId;
    }

    /**
     * @param servRuleId the value for ECM_SERV_RULE.SERV_RULE_ID 
     */
    public void setServRuleId(Integer servRuleId) {
        this.servRuleId = servRuleId;
    }

    /**
     * @return the value of  ECM_SERV_RULE.SERV_RULE_TYPE 
     */
    public String getServRuleType() {
        return servRuleType;
    }

    /**
     * @param servRuleType the value for ECM_SERV_RULE.SERV_RULE_TYPE 
     */
    public void setServRuleType(String servRuleType) {
        this.servRuleType = servRuleType == null ? null : servRuleType.trim();
    }

    /**
     * @return the value of  ECM_SERV_RULE.ROUTER_COND_ID 
     */
    public Integer getRouterCondId() {
        return routerCondId;
    }

    /**
     * @param routerCondId the value for ECM_SERV_RULE.ROUTER_COND_ID 
     */
    public void setRouterCondId(Integer routerCondId) {
        this.routerCondId = routerCondId;
    }

    /**
     * @return the value of  ECM_SERV_RULE.SER_MTD_ID 
     */
    public Integer getSerMtdId() {
        return serMtdId;
    }

    /**
     * @param serMtdId the value for ECM_SERV_RULE.SER_MTD_ID 
     */
    public void setSerMtdId(Integer serMtdId) {
        this.serMtdId = serMtdId;
    }

    /**
     * @return the value of  ECM_SERV_RULE.RULE_STATUS 
     */
    public String getRuleStatus() {
        return ruleStatus;
    }

    /**
     * @param ruleStatus the value for ECM_SERV_RULE.RULE_STATUS 
     */
    public void setRuleStatus(String ruleStatus) {
        this.ruleStatus = ruleStatus == null ? null : ruleStatus.trim();
    }

    /**
     * @return the value of  ECM_SERV_RULE.ROUTER_ARGS_POS 
     */
    public Integer getRouterArgsPos() {
        return routerArgsPos;
    }

    /**
     * @param routerArgsPos the value for ECM_SERV_RULE.ROUTER_ARGS_POS 
     */
    public void setRouterArgsPos(Integer routerArgsPos) {
        this.routerArgsPos = routerArgsPos;
    }

    /**
     * @return the value of  ECM_SERV_RULE.ROUTER_ARGS_TYPE 
     */
    public String getRouterArgsType() {
        return routerArgsType;
    }

    /**
     * @param routerArgsType the value for ECM_SERV_RULE.ROUTER_ARGS_TYPE 
     */
    public void setRouterArgsType(String routerArgsType) {
        this.routerArgsType = routerArgsType == null ? null : routerArgsType.trim();
    }

    /**
     * @return the value of  ECM_SERV_RULE.SERV_RULE_SELF 
     */
    public String getServRuleSelf() {
        return servRuleSelf;
    }

    /**
     * @param servRuleSelf the value for ECM_SERV_RULE.SERV_RULE_SELF 
     */
    public void setServRuleSelf(String servRuleSelf) {
        this.servRuleSelf = servRuleSelf == null ? null : servRuleSelf.trim();
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
	 * @return the ruleStatusName
	 */
	public String getRuleStatusName() {
		return ruleStatusName;
	}

	/**
	 * @param ruleStatusName the ruleStatusName to set
	 */
	public void setRuleStatusName(String ruleStatusName) {
		this.ruleStatusName = ruleStatusName;
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

	public String getRouterCondOper() {
		return routerCondOper;
	}

	public void setRouterCondOper(String routerCondOper) {
		this.routerCondOper = routerCondOper;
	}

	public String getRouterCondVal() {
		return routerCondVal;
	}

	public void setRouterCondVal(String routerCondVal) {
		this.routerCondVal = routerCondVal;
	}

	public String getRouterColCdn() {
		return routerColCdn;
	}

	public void setRouterColCdn(String routerColCdn) {
		this.routerColCdn = routerColCdn;
	}

	public String getAppSerClsnm() {
		return appSerClsnm;
	}

	public void setAppSerClsnm(String appSerClsnm) {
		this.appSerClsnm = appSerClsnm;
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

	public String getRouterColType() {
		return routerColType;
	}

	public void setRouterColType(String routerColType) {
		this.routerColType = routerColType;
	}

    public String getHandlerClsName() {
        return handlerClsName;
    }

    public void setHandlerClsName(String handlerClsName) {
        this.handlerClsName = handlerClsName;
    }

    public String getHandlerClsMthName() {
        return handlerClsMthName;
    }

    public void setHandlerClsMthName(String handlerClsMthName) {
        this.handlerClsMthName = handlerClsMthName;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
}