package com.dcits.ensemble.om.pm.module.paraSetting;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by maruie on 2016/04/07 13:57:00.
 */
public class ParaApplyCheckPublish extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is PARA_APPLY_CHECK_PUBLISH.REQ_NO 日期+时间
     */
    @TablePk(index = 1)
    private String reqNo;

    /**
     * This field is PARA_APPLY_CHECK_PUBLISH.TRAN_TIMESTAMP 时间戳
     */
    @TablePk(index = 2)
    private String tranTimestamp;

    /**
     * This field is PARA_APPLY_CHECK_PUBLISH.OPERATOR_TYPE 操作类型: apply/check/publish
     */
    private String operatorType;

    /**
     * This field is PARA_APPLY_CHECK_PUBLISH.CHECK_TEXT 复核发布意见
     */
    private String checkText;

    /**
     * This field is PARA_APPLY_CHECK_PUBLISH.APPROVE 是否通过:Y/N,作废:C
     */
    private String approve;

    /**
     * This field is PARA_APPLY_CHECK_PUBLISH.OPERATOR_ID 操作人ID
     */
    private String operatorId;

    /**
     * This field is PARA_APPLY_CHECK_PUBLISH.CURRENTSYSTEM_TIME 操作时间
     */
    private String currentsystemTime;

    /**
     * This field is PARA_APPLY_CHECK_PUBLISH.CLIENT_IP 操作人IP
     */
    private String clientIp;

    /**
     * @return the value of  PARA_APPLY_CHECK_PUBLISH.REQ_NO 日期+时间
     */
    public String getReqNo() {
        return reqNo;
    }

    /**
     * @param reqNo the value for PARA_APPLY_CHECK_PUBLISH.REQ_NO 日期+时间
     */
    public void setReqNo(String reqNo) {
        this.reqNo = reqNo == null ? null : reqNo.trim();
    }

    /**
     * @return the value of  PARA_APPLY_CHECK_PUBLISH.TRAN_TIMESTAMP 时间戳
     */
    public String getTranTimestamp() {
        return tranTimestamp;
    }

    /**
     * @param tranTimestamp the value for PARA_APPLY_CHECK_PUBLISH.TRAN_TIMESTAMP 时间戳
     */
    public void setTranTimestamp(String tranTimestamp) {
        this.tranTimestamp = tranTimestamp == null ? null : tranTimestamp.trim();
    }

    /**
     * @return the value of  PARA_APPLY_CHECK_PUBLISH.OPERATOR_TYPE 操作类型: apply/check/publish
     */
    public String getOperatorType() {
        return operatorType;
    }

    /**
     * @param operatorType the value for PARA_APPLY_CHECK_PUBLISH.OPERATOR_TYPE 操作类型: apply/check/publish
     */
    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType == null ? null : operatorType.trim();
    }

    /**
     * @return the value of  PARA_APPLY_CHECK_PUBLISH.CHECK_TEXT 复核发布意见
     */
    public String getCheckText() {
        return checkText;
    }

    /**
     * @param checkText the value for PARA_APPLY_CHECK_PUBLISH.CHECK_TEXT 复核发布意见
     */
    public void setCheckText(String checkText) {
        this.checkText = checkText == null ? null : checkText.trim();
    }

    /**
     * @return the value of  PARA_APPLY_CHECK_PUBLISH.APPROVE 是否通过:Y/N,作废:C
     */
    public String getApprove() {
        return approve;
    }

    /**
     * @param approve the value for PARA_APPLY_CHECK_PUBLISH.APPROVE 是否通过:Y/N,作废:C
     */
    public void setApprove(String approve) {
        this.approve = approve == null ? null : approve.trim();
    }

    /**
     * @return the value of  PARA_APPLY_CHECK_PUBLISH.OPERATOR_ID 操作人ID
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     * @param operatorId the value for PARA_APPLY_CHECK_PUBLISH.OPERATOR_ID 操作人ID
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    /**
     * @return the value of  PARA_APPLY_CHECK_PUBLISH.CURRENTSYSTEM_TIME 操作时间
     */
    public String getCurrentsystemTime() {
        return currentsystemTime;
    }

    /**
     * @param currentsystemTime the value for PARA_APPLY_CHECK_PUBLISH.CURRENTSYSTEM_TIME 操作时间
     */
    public void setCurrentsystemTime(String currentsystemTime) {
        this.currentsystemTime = currentsystemTime == null ? null : currentsystemTime.trim();
    }

    /**
     * @return the value of  PARA_APPLY_CHECK_PUBLISH.CLIENT_IP 操作人IP
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * @param clientIp the value for PARA_APPLY_CHECK_PUBLISH.CLIENT_IP 操作人IP
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp == null ? null : clientIp.trim();
    }
}