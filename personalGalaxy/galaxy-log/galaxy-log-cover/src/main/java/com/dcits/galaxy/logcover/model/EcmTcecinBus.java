package com.dcits.galaxy.logcover.model;

import com.dcits.galaxy.base.bean.AbstractBean;




/**
 * Created by admin on 2016/10/11 16:03:10.
 */
public class EcmTcecinBus extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_TCECIN_BUS.TRACE_ID 调用链ID
     */   
    private String traceId;

    /**
     * This field is ECM_TCECIN_BUS.MESSAGE_CODE 消息码
     */
    private String messageCode;

    /**
     * This field is ECM_TCECIN_BUS.MESSAGE_TYPE 消息类型
     */
    private String messageType;

    /**
     * This field is ECM_TCECIN_BUS.SERVICE_CODE 服务码
     */
    private String serviceCode;

    /**
     * This field is ECM_TCECIN_BUS.ACCT_NO 帐号
     */
    private String acctNo;

    /**
     * This field is ECM_TCECIN_BUS.CARD_NO 卡号
     */
    private String cardNo;

    /**
     * This field is ECM_TCECIN_BUS.BRANCH_ID 网点号
     */
    private String branchId;

    /**
     * This field is ECM_TCECIN_BUS.TELLER_ID 柜员号
     */
    private String tellerId;
    /**
     * This field is ECM_TCECIN_BUS.OPER_TIME 柜员号
     */
    private String opetTime;

    /**
     * @return the value of  ECM_TCECIN_BUS.TRACE_ID 
     */
    public String getTraceId() {
        return traceId;
    }

    /**
     * @param traceId the value for ECM_TCECIN_BUS.TRACE_ID 
     */
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    /**
     * @return the value of  ECM_TCECIN_BUS.MESSAGE_CODE 
     */
    public String getMessageCode() {
        return messageCode;
    }

    /**
     * @param messageCode the value for ECM_TCECIN_BUS.MESSAGE_CODE 
     */
    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode == null ? "" : messageCode.trim();
    }

    /**
     * @return the value of  ECM_TCECIN_BUS.MESSAGE_TYPE 
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * @param messageType the value for ECM_TCECIN_BUS.MESSAGE_TYPE 
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType == null ? "" : messageType.trim();
    }

    /**
     * @return the value of  ECM_TCECIN_BUS.SERVICE_CODE 
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * @param serviceCode the value for ECM_TCECIN_BUS.SERVICE_CODE 
     */
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode == null ? "" : serviceCode.trim();
    }

    /**
     * @return the value of  ECM_TCECIN_BUS.ACCT_NO 
     */
    public String getAcctNo() {
        return acctNo;
    }

    /**
     * @param acctNo the value for ECM_TCECIN_BUS.ACCT_NO 
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo == null ? "" : acctNo.trim();
    }

    /**
     * @return the value of  ECM_TCECIN_BUS.CARD_NO 
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * @param cardNo the value for ECM_TCECIN_BUS.CARD_NO 
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? "" : cardNo.trim();
    }

    /**
     * @return the value of  ECM_TCECIN_BUS.BRANCH_ID 
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * @param branchId the value for ECM_TCECIN_BUS.BRANCH_ID 
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId == null ? "" : branchId.trim();
    }

    /**
     * @return the value of  ECM_TCECIN_BUS.TELLER_ID 
     */
    public String getTellerId() {
        return tellerId;
    }

    /**
     * @param tellerId the value for ECM_TCECIN_BUS.TELLER_ID 
     */
    public void setTellerId(String tellerId) {
        this.tellerId = tellerId == null ? "" : tellerId.trim();
    }

    
	public String getOpetTime() {
		return opetTime;
	}

	public void setOpetTime(String opetTime) {
		this.opetTime = opetTime;
	}

	@Override
	public String toString() {
		return "EcmTcecinBus [traceId=" + traceId + ", messageCode="
				+ messageCode + ", messageType=" + messageType
				+ ", serviceCode=" + serviceCode + ", acctNo=" + acctNo
				+ ", cardNo=" + cardNo + ", branchId=" + branchId
				+ ", tellerId=" + tellerId + ", opetTime=" + opetTime + "]";
	}
    
    
}