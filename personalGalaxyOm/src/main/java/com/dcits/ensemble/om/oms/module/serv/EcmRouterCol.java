package com.dcits.ensemble.om.oms.module.serv;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by oms on 2016/02/25 15:38:17.
 */
public class EcmRouterCol extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_ROUTER_COL.ROUTER_COL_ID 
     */
    @TablePk(index = 1)
    private Integer routerColId;

    /**
     * This field is ECM_ROUTER_COL.ROUTER_COL_CN 
     */
    private String routerColCn;

    /**
     * This field is ECM_ROUTER_COL.ROUTER_COL_CDN 
     */
    private String routerColCdn;
    
    /**
     * This field is ECM_ROUTER_COL.ROUTER_COL_TYPE
     */
    private String routerColType;
    
    private String routerColTypeName;

    /**
     * @return the value of  ECM_ROUTER_COL.ROUTER_COL_ID 
     */
    public Integer getRouterColId() {
        return routerColId;
    }

    /**
     * @param routerColId the value for ECM_ROUTER_COL.ROUTER_COL_ID 
     */
    public void setRouterColId(Integer routerColId) {
        this.routerColId = routerColId;
    }

    /**
     * @return the value of  ECM_ROUTER_COL.ROUTER_COL_CN 
     */
    public String getRouterColCn() {
        return routerColCn;
    }

    /**
     * @param routerColCn the value for ECM_ROUTER_COL.ROUTER_COL_CN 
     */
    public void setRouterColCn(String routerColCn) {
        this.routerColCn = routerColCn == null ? null : routerColCn.trim();
    }

    /**
     * @return the value of  ECM_ROUTER_COL.ROUTER_COL_CDN 
     */
    public String getRouterColCdn() {
        return routerColCdn;
    }

    /**
     * @param routerColCdn the value for ECM_ROUTER_COL.ROUTER_COL_CDN 
     */
    public void setRouterColCdn(String routerColCdn) {
        this.routerColCdn = routerColCdn == null ? null : routerColCdn.trim();
    }

	public String getRouterColType() {
		return routerColType;
	}

	public void setRouterColType(String routerColType) {
		this.routerColType = routerColType;
	}

	/**
	 * @return the routerColTypeName
	 */
	public String getRouterColTypeName() {
		return routerColTypeName;
	}

	/**
	 * @param routerColTypeName the routerColTypeName to set
	 */
	public void setRouterColTypeName(String routerColTypeName) {
		this.routerColTypeName = routerColTypeName;
	}
    
    
}