package com.dcits.ensemble.om.pm.module.paraSetting;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/08/11 16:24:01.
 */
public class ParaTransactionTableOrg extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is PARA_TRANSACTION_TABLE_ORG.TRANSACTION_ID 参数表全名:系统名_表名
     */
    @TablePk(index = 1)
    private String transactionId;

    /**
     * This field is PARA_TRANSACTION_TABLE_ORG.REQ_NO 主交易日期+时间
     */
    private String reqNo;

    /**
     * This field is PARA_TRANSACTION_TABLE_ORG.SUB_REQ_NO 附属交易日期+时间
     */
    private String subReqNo;

    /**
     * @return the value of  PARA_TRANSACTION_TABLE_ORG.REQ_NO 主交易日期+时间
     */
    public String getReqNo() {
        return reqNo;
    }

    /**
     * @param reqNo the value for PARA_TRANSACTION_TABLE_ORG.REQ_NO 主交易日期+时间
     */
    public void setReqNo(String reqNo) {
        this.reqNo = reqNo == null ? null : reqNo.trim();
    }

    /**
     * @return the value of  PARA_TRANSACTION_TABLE_ORG.TRANSACTION_ID 参数表全名:系统名_表名
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId the value for PARA_TRANSACTION_TABLE_ORG.TRANSACTION_ID 参数表全名:系统名_表名
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId == null ? null : transactionId.trim();
    }

    /**
     * @return the value of  PARA_TRANSACTION_TABLE_ORG.SUB_REQ_NO 附属交易日期+时间
     */
    public String getSubReqNo() {
        return subReqNo;
    }

    /**
     * @param subReqNo the value for PARA_TRANSACTION_TABLE_ORG.SUB_REQ_NO 附属交易日期+时间
     */
    public void setSubReqNo(String subReqNo) {
        this.subReqNo = subReqNo == null ? null : subReqNo.trim();
    }
}