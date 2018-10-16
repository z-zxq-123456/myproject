package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/05/27 19:02:42.
 */
public class GlProdRulePf extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is GL_PROD_RULE.PROD_TYPE 
     */
    @TablePk(index = 1)
    private String prodType;

    /**
     * This field is GL_PROD_RULE.SYS_NAME 
     */
    @TablePk(index = 2)
    private String sysName;

    /**
     * This field is GL_PROD_RULE.SOURCE_TYPE 
     */
    @TablePk(index = 3)
    private String sourceType;

    /**
     * This field is GL_PROD_RULE.CLIENT_TYPE 
     */
    @TablePk(index = 4)
    private String clientType;

    /**
     * This field is GL_PROD_RULE.ACCOUNTING_STATUS
     */
    @TablePk(index = 5)
    private String accountingStatus;

    /**
     * This field is GL_PROD_RULE.TRAN_EVENT_TYPE 
     */
    @TablePk(index = 6)
    private String tranEventType;

    /**
     * This field is GL_PROD_RULE.TRAN_TYPE 
     */
    @TablePk(index = 7)
    private String tranType;

    /**
     * This field is GL_PROD_RULE.CCY 
     */
    @TablePk(index = 8)
    private String ccy;

    /**
     * This field is GL_PROD_RULE.ACCOUNTING_NO 
     */
    private String accountingNo;

    /**
     * This field is GL_PROD_RULE.CUSTOM_RULE 
     */
    private String customRule;

    /**
     * This field is GL_PROD_RULE.ACCOUNTING_DESC 
     */
    private String accountingDesc;

    public String getSourceModule() {
        return sourceModule;
    }

    public void setSourceModule(String sourceModule) {
        this.sourceModule = sourceModule;
    }

    private String sourceModule;

    /**
     * @return the value of  GL_PROD_RULE.PROD_TYPE 
     */
    public String getProdType() {
        return prodType;
    }

    /**
     * @param prodType the value for GL_PROD_RULE.PROD_TYPE 
     */
    public void setProdType(String prodType) {
        this.prodType = prodType == null ? null : prodType.trim();
    }

    /**
     * @return the value of  GL_PROD_RULE.SYS_NAME 
     */
    public String getSysName() {
        return sysName;
    }

    /**
     * @param sysName the value for GL_PROD_RULE.SYS_NAME 
     */
    public void setSysName(String sysName) {
        this.sysName = sysName == null ? null : sysName.trim();
    }

    /**
     * @return the value of  GL_PROD_RULE.SOURCE_TYPE 
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * @param sourceType the value for GL_PROD_RULE.SOURCE_TYPE 
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
    }

    /**
     * @return the value of  GL_PROD_RULE.CLIENT_TYPE 
     */
    public String getClientType() {
        return clientType;
    }

    /**
     * @param clientType the value for GL_PROD_RULE.CLIENT_TYPE 
     */
    public void setClientType(String clientType) {
        this.clientType = clientType == null ? null : clientType.trim();
    }

    /**
     * @return the value of  GL_PROD_RULE.ACCOUNTING_STATUS
     */
    public String getAccountingStatus() {
        return accountingStatus;
    }

    /**
     * @param accountingStatus the value for GL_PROD_RULE.ACCOUNTING_STATUS
     */
    public void setAccountingStatus(String accountingStatus) {
        this.accountingStatus = accountingStatus == null ? null : accountingStatus.trim();
    }

    /**
     * @return the value of  GL_PROD_RULE.TRAN_EVENT_TYPE 
     */
    public String getTranEventType() {
        return tranEventType;
    }

    /**
     * @param tranEventType the value for GL_PROD_RULE.TRAN_EVENT_TYPE 
     */
    public void setTranEventType(String tranEventType) {
        this.tranEventType = tranEventType == null ? null : tranEventType.trim();
    }

    /**
     * @return the value of  GL_PROD_RULE.ACCOUNTING_NO 
     */
    public String getAccountingNo() {
        return accountingNo;
    }

    /**
     * @param accountingNo the value for GL_PROD_RULE.ACCOUNTING_NO 
     */
    public void setAccountingNo(String accountingNo) {
        this.accountingNo = accountingNo == null ? null : accountingNo.trim();
    }

    /**
     * @return the value of  GL_PROD_RULE.CUSTOM_RULE 
     */
    public String getCustomRule() {
        return customRule;
    }

    /**
     * @param customRule the value for GL_PROD_RULE.CUSTOM_RULE 
     */
    public void setCustomRule(String customRule) {
        this.customRule = customRule == null ? null : customRule.trim();
    }

    /**
     * @return the value of  GL_PROD_RULE.ACCOUNTING_DESC 
     */
    public String getAccountingDesc() {
        return accountingDesc;
    }

    /**
     * @param accountingDesc the value for GL_PROD_RULE.ACCOUNTING_DESC 
     */
    public void setAccountingDesc(String accountingDesc) {
        this.accountingDesc = accountingDesc == null ? null : accountingDesc.trim();
    }

    /**
     * @return the value of  GL_PROD_RULE.CCY 
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * @param ccy the value for GL_PROD_RULE.CCY 
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * @return the value of  GL_PROD_RULE.TRAN_TYPE 
     */
    public String getTranType() {
        return tranType;
    }

    /**
     * @param tranType the value for GL_PROD_RULE.TRAN_TYPE 
     */
    public void setTranType(String tranType) {
        this.tranType = tranType == null ? null : tranType.trim();
    }
}