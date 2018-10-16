package com.dcits.ensemble.om.oms.module.log;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/10/24 09:43:20.
 */
public class EcmTcecinStat extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_TCECIN_STAT.TRACE_STAT_ID 
     */
    @TablePk(index = 1)
    private Integer traceStatId;

    /**
     * This field is ECM_TCECIN_STAT.TRACE_STAT_DATE 
     */
    private String traceStatDate;

    /**
     * This field is ECM_TCECIN_STAT.MESSAGE_CODE 
     */
    private String messageCode;

    /**
     * This field is ECM_TCECIN_STAT.MESSAGE_TYPE 
     */
    private String messageType;

    /**
     * This field is ECM_TCECIN_STAT.SERVICE_CODE 
     */
    private String serviceCode;

    /**
     * This field is ECM_TCECIN_STAT.TRACE_STAT_AMOUNT 
     */
    private String traceStatAmount;

    /**
     * This field is ECM_TCECIN_STAT.TRACE_STAT_OKNUM 
     */
    private String traceStatOknum;
    /**
     * This field is ECM_TCECIN_STAT.TRACE_STAT_OKNUM
     */
    private String traceStatFailnum;

    /**
     * This field is ECM_TCECIN_STAT.TRACE_STAT_OKPERC 
     */
    private String traceStatOkperc;

    /**
     * This field is ECM_TCECIN_STAT.TRACE_STAT_OKPERC
     */
    private String traceStatFailperc;

    /**
     * This field is ECM_TCECIN_STAT.TRACE_STAT_AVERTIME 
     */
    private String traceStatAvertime;

    /**
     * This field is ECM_TCECIN_STAT.TRACE_STAT_PEAKNUM 
     */
    private String traceStatPeaknum;

    /**
     * @return the value of  ECM_TCECIN_STAT.TRACE_STAT_ID 
     */
    public Integer getTraceStatId() {
        return traceStatId;
    }

    /**
     * @param traceStatId the value for ECM_TCECIN_STAT.TRACE_STAT_ID 
     */
    public void setTraceStatId(Integer traceStatId) {
        this.traceStatId = traceStatId;
    }

    /**
     * @return the value of  ECM_TCECIN_STAT.TRACE_STAT_DATE 
     */
    public String getTraceStatDate() {
        return traceStatDate;
    }

    /**
     * @param traceStatDate the value for ECM_TCECIN_STAT.TRACE_STAT_DATE 
     */
    public void setTraceStatDate(String traceStatDate) {
        this.traceStatDate = traceStatDate == null ? null : traceStatDate.trim();
    }

    /**
     * @return the value of  ECM_TCECIN_STAT.MESSAGE_CODE 
     */
    public String getMessageCode() {
        return messageCode;
    }

    /**
     * @param messageCode the value for ECM_TCECIN_STAT.MESSAGE_CODE 
     */
    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode == null ? null : messageCode.trim();
    }

    /**
     * @return the value of  ECM_TCECIN_STAT.MESSAGE_TYPE 
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * @param messageType the value for ECM_TCECIN_STAT.MESSAGE_TYPE 
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType == null ? null : messageType.trim();
    }

    /**
     * @return the value of  ECM_TCECIN_STAT.SERVICE_CODE 
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * @param serviceCode the value for ECM_TCECIN_STAT.SERVICE_CODE 
     */
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode == null ? null : serviceCode.trim();
    }

    /**
     * @return the value of  ECM_TCECIN_STAT.TRACE_STAT_AMOUNT 
     */
    public String getTraceStatAmount() {
        return traceStatAmount;
    }

    /**
     * @param traceStatAmount the value for ECM_TCECIN_STAT.TRACE_STAT_AMOUNT 
     */
    public void setTraceStatAmount(String traceStatAmount) {
        this.traceStatAmount = traceStatAmount == null ? null : traceStatAmount.trim();
    }

    /**
     * @return the value of  ECM_TCECIN_STAT.TRACE_STAT_OKNUM 
     */
    public String getTraceStatOknum() {
        return traceStatOknum;
    }

    /**
     * @param traceStatOknum the value for ECM_TCECIN_STAT.TRACE_STAT_OKNUM 
     */
    public void setTraceStatOknum(String traceStatOknum) {
        this.traceStatOknum = traceStatOknum == null ? null : traceStatOknum.trim();
    }

    /**
     * @return the value of  ECM_TCECIN_STAT.TRACE_STAT_OKPERC 
     */
    public String getTraceStatOkperc() {
        return traceStatOkperc;
    }

    /**
     * @param traceStatOkperc the value for ECM_TCECIN_STAT.TRACE_STAT_OKPERC 
     */
    public void setTraceStatOkperc(String traceStatOkperc) {
        this.traceStatOkperc = traceStatOkperc == null ? null : traceStatOkperc.trim();
    }

    /**
     * @return the value of  ECM_TCECIN_STAT.TRACE_STAT_AVERTIME 
     */
    public String getTraceStatAvertime() {
        return traceStatAvertime;
    }

    /**
     * @param traceStatAvertime the value for ECM_TCECIN_STAT.TRACE_STAT_AVERTIME 
     */
    public void setTraceStatAvertime(String traceStatAvertime) {
        this.traceStatAvertime = traceStatAvertime == null ? null : traceStatAvertime.trim();
    }

    /**
     * @return the value of  ECM_TCECIN_STAT.TRACE_STAT_PEAKNUM 
     */
    public String getTraceStatPeaknum() {
        return traceStatPeaknum;
    }

    /**
     * @param traceStatPeaknum the value for ECM_TCECIN_STAT.TRACE_STAT_PEAKNUM 
     */
    public void setTraceStatPeaknum(String traceStatPeaknum) {
        this.traceStatPeaknum = traceStatPeaknum == null ? null : traceStatPeaknum.trim();
    }

    public String getTraceStatFailperc() {
        return traceStatFailperc;
    }

    public void setTraceStatFailperc(String traceStatFailperc) {
        this.traceStatFailperc = traceStatFailperc;
    }

    public String getTraceStatFailnum() {
        return traceStatFailnum;
    }

    public void setTraceStatFailnum(String traceStatFailnum) {
        this.traceStatFailnum = traceStatFailnum;
    }
}