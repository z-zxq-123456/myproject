package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:04.
 */
public class CifBusiness extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_BUSINESS.BUSINESS 琛屼笟浠ｇ爜
     */
    @TablePk(index = 1)
    private String business;

    /**
     * This field is CIF_BUSINESS.BUSINESS_DESC 琛屼笟浠ｇ爜璇存槑
     */
    private String businessDesc;

    /**
     * This field is CIF_BUSINESS.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_BUSINESS.BUSINESS 琛屼笟浠ｇ爜
     */
    public String getBusiness() {
        return business;
    }

    /**
     * @param business the value for CIF_BUSINESS.BUSINESS 琛屼笟浠ｇ爜
     */
    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }

    /**
     * @return the value of  CIF_BUSINESS.BUSINESS_DESC 琛屼笟浠ｇ爜璇存槑
     */
    public String getBusinessDesc() {
        return businessDesc;
    }

    /**
     * @param businessDesc the value for CIF_BUSINESS.BUSINESS_DESC 琛屼笟浠ｇ爜璇存槑
     */
    public void setBusinessDesc(String businessDesc) {
        this.businessDesc = businessDesc == null ? null : businessDesc.trim();
    }

    /**
     * @return the value of  CIF_BUSINESS.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_BUSINESS.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}