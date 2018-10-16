package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/04/28 13:43:54.
 */
public class MbEventLink extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is MB_EVENT_LINK.PROD_TYPE 
     */
    @TablePk(index = 1)
    private String prodType;

    /**
     * This field is MB_EVENT_LINK.ORIG_PROD_TYPE 
     */
    @TablePk(index = 2)
    private String origProdType;

    /**
     * This field is MB_EVENT_LINK.ORIG_EVENT_TYPE 
     */
    @TablePk(index = 3)
    private String origEventType;

    /**
     * This field is MB_EVENT_LINK.LINK_PROD_TYPE 关联产品类型
     */
    @TablePk(index = 4)
    private String linkProdType;

    /**
     * This field is MB_EVENT_LINK.LINK_EVENT_TYPE 
     */
    @TablePk(index = 5)
    private String linkEventType;

    /**
     * This field is MB_EVENT_LINK.LINK_CONDITION 关联条件
     */
    private String linkCondition;

    /**
     * This field is MB_EVENT_LINK.STATUS 状态
     */
    private String status;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * This field is MB_ATTR_CLASS.company 所属法人
     */
    private String company;
    /**
     * @return the value of  MB_EVENT_LINK.PROD_TYPE 
     */
    public String getProdType() {
        return prodType;
    }

    /**
     * @param prodType the value for MB_EVENT_LINK.PROD_TYPE 
     */
    public void setProdType(String prodType) {
        this.prodType = prodType == null ? null : prodType.trim();
    }

    /**
     * @return the value of  MB_EVENT_LINK.ORIG_PROD_TYPE 
     */
    public String getOrigProdType() {
        return origProdType;
    }

    /**
     * @param origProdType the value for MB_EVENT_LINK.ORIG_PROD_TYPE 
     */
    public void setOrigProdType(String origProdType) {
        this.origProdType = origProdType == null ? null : origProdType.trim();
    }

    /**
     * @return the value of  MB_EVENT_LINK.ORIG_EVENT_TYPE 
     */
    public String getOrigEventType() {
        return origEventType;
    }

    /**
     * @param origEventType the value for MB_EVENT_LINK.ORIG_EVENT_TYPE 
     */
    public void setOrigEventType(String origEventType) {
        this.origEventType = origEventType == null ? null : origEventType.trim();
    }

    /**
     * @return the value of  MB_EVENT_LINK.LINK_PROD_TYPE 关联产品类型
     */
    public String getLinkProdType() {
        return linkProdType;
    }

    /**
     * @param linkProdType the value for MB_EVENT_LINK.LINK_PROD_TYPE 关联产品类型
     */
    public void setLinkProdType(String linkProdType) {
        this.linkProdType = linkProdType == null ? null : linkProdType.trim();
    }

    /**
     * @return the value of  MB_EVENT_LINK.LINK_EVENT_TYPE 
     */
    public String getLinkEventType() {
        return linkEventType;
    }

    /**
     * @param linkEventType the value for MB_EVENT_LINK.LINK_EVENT_TYPE 
     */
    public void setLinkEventType(String linkEventType) {
        this.linkEventType = linkEventType == null ? null : linkEventType.trim();
    }

    /**
     * @return the value of  MB_EVENT_LINK.LINK_CONDITION 关联条件
     */
    public String getLinkCondition() {
        return linkCondition;
    }

    /**
     * @param linkCondition the value for MB_EVENT_LINK.LINK_CONDITION 关联条件
     */
    public void setLinkCondition(String linkCondition) {
        this.linkCondition = linkCondition == null ? null : linkCondition.trim();
    }

    /**
     * @return the value of  MB_EVENT_LINK.STATUS 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the value for MB_EVENT_LINK.STATUS 状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}