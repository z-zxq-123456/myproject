package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/03/18 18:03:01.
 */
public class FmBank extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is FM_BANK.BANK_CODE 閾惰浠ｇ爜
     */
    @TablePk(index = 1)
    private String bankCode;

    /**
     * This field is FM_BANK.BANK_ID 閾惰ID
     */
    private String bankId;

    /**
     * This field is FM_BANK.BANK_NAME 閾惰鍚嶇О
     */
    private String bankName;

    /**
     * This field is FM_BANK.BANK_TYPE 
     */
    private String bankType;

    /**
     * This field is FM_BANK.STATUS 鐘舵€�A-鏈夋晥 C-鍏抽棴
     */
    private String status;

    /**
     * This field is FM_BANK.COMPANY 
     */
    private String company;

    /**
     * @return the value of  FM_BANK.BANK_CODE 閾惰浠ｇ爜
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * @param bankCode the value for FM_BANK.BANK_CODE 閾惰浠ｇ爜
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    /**
     * @return the value of  FM_BANK.BANK_ID 閾惰ID
     */
    public String getBankId() {
        return bankId;
    }

    /**
     * @param bankId the value for FM_BANK.BANK_ID 閾惰ID
     */
    public void setBankId(String bankId) {
        this.bankId = bankId == null ? null : bankId.trim();
    }

    /**
     * @return the value of  FM_BANK.BANK_NAME 閾惰鍚嶇О
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName the value for FM_BANK.BANK_NAME 閾惰鍚嶇О
     */
    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    /**
     * @return the value of  FM_BANK.BANK_TYPE 
     */
    public String getBankType() {
        return bankType;
    }

    /**
     * @param bankType the value for FM_BANK.BANK_TYPE 
     */
    public void setBankType(String bankType) {
        this.bankType = bankType == null ? null : bankType.trim();
    }

    /**
     * @return the value of  FM_BANK.STATUS 鐘舵€�A-鏈夋晥 C-鍏抽棴
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the value for FM_BANK.STATUS 鐘舵€�A-鏈夋晥 C-鍏抽棴
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * @return the value of  FM_BANK.COMPANY 
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for FM_BANK.COMPANY 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}