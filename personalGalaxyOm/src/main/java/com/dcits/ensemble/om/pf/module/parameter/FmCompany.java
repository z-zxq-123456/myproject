package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/03/18 18:02:59.
 */
public class FmCompany extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is FM_COMPANY.COMPANY 鍏徃浠ｇ爜
     */
    @TablePk(index = 1)
    private String company;

    /**
     * This field is FM_COMPANY.COMPANY_NAME 鍏徃鍚嶇О
     */
    private String companyName;

    /**
     * @return the value of  FM_COMPANY.COMPANY 鍏徃浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for FM_COMPANY.COMPANY 鍏徃浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    /**
     * @return the value of  FM_COMPANY.COMPANY_NAME 鍏徃鍚嶇О
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the value for FM_COMPANY.COMPANY_NAME 鍏徃鍚嶇О
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }
}