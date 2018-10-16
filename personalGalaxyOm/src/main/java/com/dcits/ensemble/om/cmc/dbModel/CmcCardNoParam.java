package com.dcits.ensemble.om.cmc.dbModel;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * This class corresponds to the database table cmc_card_no_param
 * @description 预制卡参数表
 */
public class CmcCardNoParam extends AbstractBean {

    private static final long serialVersionUID = 1L;
    //卡号规则
    @TablePk(index = 1)
    private String productRuleNo;
    //制卡数量
    private String cardNum;
    //预制卡标识 0-未启用  1-启用A表 2-启用B表
    private String flag;
    //制卡数量警戒值
    private String thresholdNum;

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

    public String getProductRuleNo() {
        return productRuleNo;
    }

    public void setProductRuleNo(String productRuleNo) {
        this.productRuleNo = productRuleNo;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }



    public String getFlag() {
        return flag;

    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


    public String getThresholdNum() {
        return thresholdNum;
    }

    public void setThresholdNum(String thresholdNum) {
        this.thresholdNum = thresholdNum;
    }
}