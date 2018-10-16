package com.dcits.ensemble.om.cmc.dbModel;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * 卡产品渠道限制
 * Created{ by zhouxqh} on 2018/2/23.
 */
public class CmcCardProductChannel extends AbstractBean {

    private static final long serialVersionUID = 1L;

    @TablePk(index = 1)
    private String cardProductCode;

    private String limitChannel;

    private String authTranType;

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

    public String getLimitChannel() {
        return limitChannel;
    }

    public void setLimitChannel(String limitChannel) {
        this.limitChannel = limitChannel;
    }

    public String getAuthTranType() {
        return authTranType;
    }

    public void setAuthTranType(String authTranType) {
        this.authTranType = authTranType;
    }
}
