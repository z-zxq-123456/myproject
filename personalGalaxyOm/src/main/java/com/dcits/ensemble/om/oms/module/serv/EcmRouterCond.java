package com.dcits.ensemble.om.oms.module.serv;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by oms on 2016/02/25 15:38:17.
 */
public class EcmRouterCond extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_ROUTER_COND.ROUTER_COND_ID 
     */
    @TablePk(index = 1)
    private Integer routerCondId;

    /**
     * This field is ECM_ROUTER_COND.ROUTER_COND_NAME 
     */
    private String routerCondName;

    /**
     * This field is ECM_ROUTER_COND.ROUTER_COND_OPER 
     */
    private String routerCondOper;
    
    private String routerCondOperName;

    /**
     * This field is ECM_ROUTER_COND.ROUTER_COL_ID 
     */
    private Integer routerColId;
    
    private String routerColCn;

    /**
     * This field is ECM_ROUTER_COL.ROUTER_COL_CDN 
     */
    private String routerColCdn;

    /**
     * This field is ECM_ROUTER_COND.ROUTER_COND_VAL 
     */
    private String routerCondVal;

    /**
     * @return the value of  ECM_ROUTER_COND.ROUTER_COND_ID 
     */
    public Integer getRouterCondId() {
        return routerCondId;
    }

    /**
     * @param routerCondId the value for ECM_ROUTER_COND.ROUTER_COND_ID 
     */
    public void setRouterCondId(Integer routerCondId) {
        this.routerCondId = routerCondId;
    }

    /**
     * @return the value of  ECM_ROUTER_COND.ROUTER_COND_NAME 
     */
    public String getRouterCondName() {
        return routerCondName;
    }

    /**
     * @param routerCondName the value for ECM_ROUTER_COND.ROUTER_COND_NAME 
     */
    public void setRouterCondName(String routerCondName) {
        this.routerCondName = routerCondName == null ? null : routerCondName.trim();
    }

    /**
     * @return the value of  ECM_ROUTER_COND.ROUTER_COND_OPER 
     */
    public String getRouterCondOper() {
        return routerCondOper;
    }

    /**
     * @param routerCondOper the value for ECM_ROUTER_COND.ROUTER_COND_OPER 
     */
    public void setRouterCondOper(String routerCondOper) {
        this.routerCondOper = routerCondOper == null ? null : routerCondOper.trim();
    }

    /**
     * @return the value of  ECM_ROUTER_COND.ROUTER_COL_ID 
     */
    public Integer getRouterColId() {
        return routerColId;
    }

    /**
     * @param routerColId the value for ECM_ROUTER_COND.ROUTER_COL_ID 
     */
    public void setRouterColId(Integer routerColId) {
        this.routerColId = routerColId;
    }

    /**
     * @return the value of  ECM_ROUTER_COND.ROUTER_COND_VAL 
     */
    public String getRouterCondVal() {
        return routerCondVal;
    }

    /**
     * @param routerCondVal the value for ECM_ROUTER_COND.ROUTER_COND_VAL 
     */
    public void setRouterCondVal(String routerCondVal) {
        this.routerCondVal = routerCondVal == null ? null : routerCondVal.trim();
    }

	/**
	 * @return the routerCondOperName
	 */
	public String getRouterCondOperName() {
		return routerCondOperName;
	}

	/**
	 * @param routerCondOperName the routerCondOperName to set
	 */
	public void setRouterCondOperName(String routerCondOperName) {
		this.routerCondOperName = routerCondOperName;
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
	 * @return the routerColCdn
	 */
	public String getRouterColCdn() {
		return routerColCdn;
	}

	/**
	 * @param routerColCdn the routerColCdn to set
	 */
	public void setRouterColCdn(String routerColCdn) {
		this.routerColCdn = routerColCdn;
	}
}