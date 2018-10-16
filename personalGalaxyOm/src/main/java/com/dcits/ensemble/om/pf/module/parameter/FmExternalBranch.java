package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/03/18 18:03:01.
 */
public class FmExternalBranch extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is FM_EXTERNAL_BRANCH.BANK_CODE 
     */
    @TablePk(index = 1)
    private String bankCode;

    /**
     * This field is FM_EXTERNAL_BRANCH.BRANCH_CODE 
     */
    @TablePk(index = 2)
    private String branchCode;

    /**
     * This field is FM_EXTERNAL_BRANCH.BRANCH_NAME 
     */
    private String branchName;

    /**
     * This field is FM_EXTERNAL_BRANCH.COUNTRY 
     */
    private String country;

    /**
     * This field is FM_EXTERNAL_BRANCH.STATE 
     */
    private String state;

    /**
     * This field is FM_EXTERNAL_BRANCH.COMPANY 
     */
    private String company;

    /**
     * @return the value of  FM_EXTERNAL_BRANCH.BANK_CODE 
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * @param bankCode the value for FM_EXTERNAL_BRANCH.BANK_CODE 
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    /**
     * @return the value of  FM_EXTERNAL_BRANCH.BRANCH_CODE 
     */
    public String getBranchCode() {
        return branchCode;
    }

    /**
     * @param branchCode the value for FM_EXTERNAL_BRANCH.BRANCH_CODE 
     */
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode == null ? null : branchCode.trim();
    }

    /**
     * @return the value of  FM_EXTERNAL_BRANCH.BRANCH_NAME 
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * @param branchName the value for FM_EXTERNAL_BRANCH.BRANCH_NAME 
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName == null ? null : branchName.trim();
    }

    /**
     * @return the value of  FM_EXTERNAL_BRANCH.COUNTRY 
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the value for FM_EXTERNAL_BRANCH.COUNTRY 
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * @return the value of  FM_EXTERNAL_BRANCH.STATE 
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the value for FM_EXTERNAL_BRANCH.STATE 
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * @return the value of  FM_EXTERNAL_BRANCH.COMPANY 
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for FM_EXTERNAL_BRANCH.COMPANY 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}