package com.dcits.ensemble.om.cmc.dbModel;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;
import java.math.BigDecimal;

/**
 * Created by dangyk on 2018/3/26.
 */
public class CmcProductLimit extends AbstractBean {

    private static final Long serialVersionUID = 1L;

    @TablePk(index = 1)
    private String cardProductCode;

    private String channelType;

    private String ccy;

    private String period;

    private BigDecimal conLimitAmt;

    private BigDecimal tranInLimitAmt;

    private BigDecimal tranOutLimitAmt;

    private String conLimitTime;

    private String tranInLimitTime;

    private String tranOutLimitTime;

    private String reqNum;

    private String operateType;

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getReqNum() {
        return reqNum;
    }

    public void setReqNum(String reqNum) {
        this.reqNum = reqNum;
    }

    public String getCardProductCode() {
        return cardProductCode;
    }

    public void setCardProductCode(String cardProductCode) {
        this.cardProductCode = cardProductCode;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public BigDecimal getConLimitAmt() {
        return conLimitAmt;
    }

    public void setConLimitAmt(BigDecimal conLimitAmt) {
        this.conLimitAmt = conLimitAmt;
    }

    public BigDecimal getTranInLimitAmt() {
        return tranInLimitAmt;
    }

    public void setTranInLimitAmt(BigDecimal tranInLimitAmt) {
        this.tranInLimitAmt = tranInLimitAmt;
    }

    public BigDecimal getTranOutLimitAmt() {
        return tranOutLimitAmt;
    }

    public void setTranOutLimitAmt(BigDecimal tranOutLimitAmt) {
        this.tranOutLimitAmt = tranOutLimitAmt;
    }

    public String getConLimitTime() {
        return conLimitTime;
    }

    public void setConLimitTime(String conLimitTime) {
        this.conLimitTime = conLimitTime;
    }

    public String getTranInLimitTime() {
        return tranInLimitTime;
    }

    public void setTranInLimitTime(String tranInLimitTime) {
        this.tranInLimitTime = tranInLimitTime;
    }

    public String getTranOutLimitTime() {
        return tranOutLimitTime;
    }

    public void setTranOutLimitTime(String tranOutLimitTime) {
        this.tranOutLimitTime = tranOutLimitTime;
    }
}
