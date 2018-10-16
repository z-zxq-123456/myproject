package com.dcits.ensemble.om.pm.module.paraSetting;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by maruie on 2016/05/12 18:44:58.
 */
public class ParaTransactionOrg extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is PARA_TRANSACTION_ORG.TRANSACTION_NAME 交易名
     */
    @TablePk(index = 1)
    private String transactionName;

    /**
     * This field is PARA_TRANSACTION_ORG.TRANSACTION_DESC 交易描述
     */
    private String transactionDesc;

    /**
     * This field is PARA_TRANSACTION_ORG.SYSTEM_ID 目标系统ID
     */
    private String systemId;

    /**
     * This field is PARA_TRANSACTION_ORG.JSP_URL jsp链接
     */
    private String jspUrl;

    /**
     * This field is PARA_TRANSACTION_ORG.BAND_ENTER_CHECK 录入和复核是否可以是同一个人:Y;N
     */
    private String bandEnterCheck;

    /**
     * This field is PARA_TRANSACTION_ORG.DELETE_AUTH 能否删除数据:Y;N
     */
    private String deleteAuth;

    /**
     * @return the value of  PARA_TRANSACTION_ORG.TRANSACTION_NAME 交易名
     */
    public String getTransactionName() {
        return transactionName;
    }

    /**
     * @param transactionName the value for PARA_TRANSACTION_ORG.TRANSACTION_NAME 交易名
     */
    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName == null ? null : transactionName.trim();
    }

    /**
     * @return the value of  PARA_TRANSACTION_ORG.TRANSACTION_DESC 交易描述
     */
    public String getTransactionDesc() {
        return transactionDesc;
    }

    /**
     * @param transactionDesc the value for PARA_TRANSACTION_ORG.TRANSACTION_DESC 交易描述
     */
    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc == null ? null : transactionDesc.trim();
    }

    /**
     * @return the value of  PARA_TRANSACTION_ORG.SYSTEM_ID 目标系统ID
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * @param systemId the value for PARA_TRANSACTION_ORG.SYSTEM_ID 目标系统ID
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    /**
     * @return the value of  PARA_TRANSACTION_ORG.JSP_URL jsp链接
     */
    public String getJspUrl() {
        return jspUrl;
    }

    /**
     * @param jspUrl the value for PARA_TRANSACTION_ORG.JSP_URL jsp链接
     */
    public void setJspUrl(String jspUrl) {
        this.jspUrl = jspUrl == null ? null : jspUrl.trim();
    }

    /**
     * @return the value of  PARA_TRANSACTION_ORG.BAND_ENTER_CHECK 录入和复核是否可以是同一个人:Y;N
     */
    public String getBandEnteringcheck() {
        return bandEnterCheck;
    }

    /**
     * @param bandEnterCheck the value for PARA_TRANSACTION_ORG.BAND_ENTER_CHECK 录入和复核是否可以是同一个人:Y;N
     */
    public void setBandEnteringcheck(String bandEnterCheck) {
        this.bandEnterCheck = bandEnterCheck == null ? null : bandEnterCheck.trim();
    }

    /**
     * @return the value of  PARA_TRANSACTION_ORG.DELETE_AUTH 能否删除数据:Y;N
     */
    public String getDeleteAuth() {
        return deleteAuth;
    }

    /**
     * @param deleteAuth the value for PARA_TRANSACTION_ORG.DELETE_AUTH 能否删除数据:Y;N
     */
    public void setDeleteAuth(String deleteAuth) {
        this.deleteAuth = deleteAuth == null ? null : deleteAuth.trim();
    }
}