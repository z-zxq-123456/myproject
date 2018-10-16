package com.dcits.ensemble.om.pm.module.paraSetting;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by maruie on 2016/05/25 11:39:26.
 */
public class ParaCircleFlow extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is PARA_CIRCLE_FLOW.REQ_NO 日期+时间
     */
    @TablePk(index = 1)
    private String reqNo;

    /**
     * This field is PARA_CIRCLE_FLOW.TRANSACTION_ID 参数交易名(MenuID)/参数表全名
     */
    private String transactionId;

    /**
     * This field is PARA_CIRCLE_FLOW.CURRENT_STATUS 当前状态:已申请1，已录入2，已复核3，已发布4，已作废5，已驳回6
     */
    private String currentStatus;

    /**
     * This field is PARA_CIRCLE_FLOW.MULTITBDATA 批量修改/删除/添加参数表数据的报文
     */
    private byte[] multitbdata;

    /**
     * This field is PARA_CIRCLE_FLOW.MULTITBDATA_OLD 修改之前的数据报文
     */
    private byte[] multitbdataOld;

    /**
     * This field is PARA_CIRCLE_FLOW.TRANSACTION_DESC 交易中文描述
     */
    private String transactionDesc;

    /**
     * @return the value of  PARA_CIRCLE_FLOW.REQ_NO 日期+时间
     */
    public String getReqNo() {
        return reqNo;
    }

    /**
     * @param reqNo the value for PARA_CIRCLE_FLOW.REQ_NO 日期+时间
     */
    public void setReqNo(String reqNo) {
        this.reqNo = reqNo == null ? null : reqNo.trim();
    }

    /**
     * @return the value of  PARA_CIRCLE_FLOW.TRANSACTION_ID 参数交易名(MenuID)/参数表全名
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId the value for PARA_CIRCLE_FLOW.TRANSACTION_ID 参数交易名(MenuID)/参数表全名
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId == null ? null : transactionId.trim();
    }

    /**
     * @return the value of  PARA_CIRCLE_FLOW.CURRENT_STATUS 当前状态:已申请1，已录入2，已复核3，已发布4，已作废5，已驳回6
     */
    public String getCurrentStatus() {
        return currentStatus;
    }

    /**
     * @param currentStatus the value for PARA_CIRCLE_FLOW.CURRENT_STATUS 当前状态:已申请1，已录入2，已复核3，已发布4，已作废5，已驳回6
     */
    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus == null ? null : currentStatus.trim();
    }

    /**
     * @return the value of  PARA_CIRCLE_FLOW.MULTITBDATA 批量修改/删除/添加参数表数据的报文
     */
    public byte[] getMultitbdata() {
        byte[] tmp = multitbdata;
        return tmp;
    }

    /**
     * @param multitbdata the value for PARA_CIRCLE_FLOW.MULTITBDATA 批量修改/删除/添加参数表数据的报文
     */
    public void setMultitbdata(byte[] multitbdata) {
        this.multitbdata = multitbdata;
    }

    /**
     * @return the value of  PARA_CIRCLE_FLOW.MULTITBDATA_OLD 修改之前的数据报文
     */
    public byte[] getMultitbdataOld() {
        byte[] tmp = multitbdataOld;
        return tmp;
    }

    /**
     * @param multitbdataOld the value for PARA_CIRCLE_FLOW.MULTITBDATA_OLD 修改之前的数据报文
     */
    public void setMultitbdataOld(byte[] multitbdataOld) {
        this.multitbdataOld = multitbdataOld;
    }

    /**
     * @return the value of  PARA_CIRCLE_FLOW.TRANSACTION_DESC 交易中文描述
     */
    public String getTransactionDesc() {
        return transactionDesc;
    }

    /**
     * @param transactionDesc the value for PARA_CIRCLE_FLOW.TRANSACTION_DESC 交易中文描述
     */
    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc == null ? null : transactionDesc.trim();
    }
}