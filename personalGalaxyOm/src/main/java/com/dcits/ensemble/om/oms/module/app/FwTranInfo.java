package com.dcits.ensemble.om.oms.module.app;

import com.dcits.galaxy.base.bean.AbstractBean;

/**
 * Created by oms on 2016/05/16 10:45:18.
 */
public class FwTranInfo extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is FW_TRAN_INFO.SERVICE_ID 
     */
    private String serviceId;

    /**
     * This field is FW_TRAN_INFO.KEY_VALUE 
     */
    private String keyValue;

    /**
     * This field is FW_TRAN_INFO.TRAN_DATE 
     */
    private String tranDate;

    /**
     * This field is FW_TRAN_INFO.TRAN_TIME 
     */
    private String tranTime;

    /**
     * This field is FW_TRAN_INFO.IN_MSG 
     */
    private String inMsg;

    /**
     * This field is FW_TRAN_INFO.OUT_MSG 
     */
    private String outMsg;

    /**
     * This field is FW_TRAN_INFO.END_TIME 
     */
    private String endTime;

    /**
     * This field is FW_TRAN_INFO.BEAN_RESULT 
     */
    private byte[] beanResult;

    /**
     * This field is FW_TRAN_INFO.SOURCE_TYPE 
     */
    private String sourceType;

    /**
     * This field is FW_TRAN_INFO.SEQ_NO 
     */
    private String seqNo;

    /**
     * This field is FW_TRAN_INFO.PROGRAM_ID 
     */
    private String programId;

    /**
     * This field is FW_TRAN_INFO.STATUS 
     */
    private String status;

    /**
     * This field is FW_TRAN_INFO.REFERENCE 
     */
    private String reference;

    /**
     * This field is FW_TRAN_INFO.PLATFORM_ID 
     */
    private String platformId;

    /**
     * This field is FW_TRAN_INFO.REVERSAL_KEY_VALUE 
     */
    private String reversalKeyValue;

    /**
     * This field is FW_TRAN_INFO.WEEK_DAY 
     */
    private Integer weekDay;
    
    private String demoInMsg;
    
    private String demoOutMsg;
    
    
    private String startTime; 
    

    public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
     * @return the value of  FW_TRAN_INFO.SERVICE_ID 
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * @param serviceId the value for FW_TRAN_INFO.SERVICE_ID 
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    /**
     * @return the value of  FW_TRAN_INFO.KEY_VALUE 
     */
    public String getKeyValue() {
        return keyValue;
    }

    /**
     * @param keyValue the value for FW_TRAN_INFO.KEY_VALUE 
     */
    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue == null ? null : keyValue.trim();
    }

    /**
     * @return the value of  FW_TRAN_INFO.TRAN_DATE 
     */
    public String getTranDate() {
        return tranDate;
    }

    /**
     * @param tranDate the value for FW_TRAN_INFO.TRAN_DATE 
     */
    public void setTranDate(String tranDate) {
        this.tranDate = tranDate == null ? null : tranDate.trim();
    }

    /**
     * @return the value of  FW_TRAN_INFO.TRAN_TIME 
     */
    public String getTranTime() {
        return tranTime;
    }

    /**
     * @param tranTime the value for FW_TRAN_INFO.TRAN_TIME 
     */
    public void setTranTime(String tranTime) {
        this.tranTime = tranTime == null ? null : tranTime.trim();
    }

    /**
     * @return the value of  FW_TRAN_INFO.IN_MSG 
     */
    public String getInMsg() {
        return inMsg;
    }

    /**
     * @param inMsg the value for FW_TRAN_INFO.IN_MSG 
     */
    public void setInMsg(String inMsg) {
        this.inMsg = inMsg == null ? null : inMsg.trim();
    }

    /**
     * @return the value of  FW_TRAN_INFO.OUT_MSG 
     */
    public String getOutMsg() {
        return outMsg;
    }

    /**
     * @param outMsg the value for FW_TRAN_INFO.OUT_MSG 
     */
    public void setOutMsg(String outMsg) {
        this.outMsg = outMsg == null ? null : outMsg.trim();
    }

    /**
     * @return the value of  FW_TRAN_INFO.END_TIME 
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the value for FW_TRAN_INFO.END_TIME 
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    /**
     * @return the value of  FW_TRAN_INFO.BEAN_RESULT 
     */
    public byte[] getBeanResult() {
        return beanResult;
    }

    /**
     * @param beanResult the value for FW_TRAN_INFO.BEAN_RESULT 
     */
    public void setBeanResult(byte[] beanResult) {
        this.beanResult = beanResult;
    }

    /**
     * @return the value of  FW_TRAN_INFO.SOURCE_TYPE 
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * @param sourceType the value for FW_TRAN_INFO.SOURCE_TYPE 
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
    }

    /**
     * @return the value of  FW_TRAN_INFO.SEQ_NO 
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * @param seqNo the value for FW_TRAN_INFO.SEQ_NO 
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * @return the value of  FW_TRAN_INFO.PROGRAM_ID 
     */
    public String getProgramId() {
        return programId;
    }

    /**
     * @param programId the value for FW_TRAN_INFO.PROGRAM_ID 
     */
    public void setProgramId(String programId) {
        this.programId = programId == null ? null : programId.trim();
    }

    /**
     * @return the value of  FW_TRAN_INFO.STATUS 
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the value for FW_TRAN_INFO.STATUS 
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * @return the value of  FW_TRAN_INFO.REFERENCE 
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference the value for FW_TRAN_INFO.REFERENCE 
     */
    public void setReference(String reference) {
        this.reference = reference == null ? null : reference.trim();
    }

    /**
     * @return the value of  FW_TRAN_INFO.PLATFORM_ID 
     */
    public String getPlatformId() {
        return platformId;
    }

    /**
     * @param platformId the value for FW_TRAN_INFO.PLATFORM_ID 
     */
    public void setPlatformId(String platformId) {
        this.platformId = platformId == null ? null : platformId.trim();
    }

    /**
     * @return the value of  FW_TRAN_INFO.REVERSAL_KEY_VALUE 
     */
    public String getReversalKeyValue() {
        return reversalKeyValue;
    }

    /**
     * @param reversalKeyValue the value for FW_TRAN_INFO.REVERSAL_KEY_VALUE 
     */
    public void setReversalKeyValue(String reversalKeyValue) {
        this.reversalKeyValue = reversalKeyValue == null ? null : reversalKeyValue.trim();
    }

    /**
     * @return the value of  FW_TRAN_INFO.WEEK_DAY 
     */
    public Integer getWeekDay() {
        return weekDay;
    }

    /**
     * @param weekDay the value for FW_TRAN_INFO.WEEK_DAY 
     */
    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

	public String getDemoInMsg() {
		return demoInMsg;
	}

	public void setDemoInMsg(String demoInMsg) {
		this.demoInMsg = demoInMsg;
	}

	public String getDemoOutMsg() {
		return demoOutMsg;
	}

	public void setDemoOutMsg(String demoOutMsg) {
		this.demoOutMsg = demoOutMsg;
	}
    
    
}