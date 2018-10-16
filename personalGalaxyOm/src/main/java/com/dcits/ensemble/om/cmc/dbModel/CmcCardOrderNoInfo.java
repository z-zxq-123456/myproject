package com.dcits.ensemble.om.cmc.dbModel;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * This class corresponds to the database table cmc_card_order_no_info
 * @description 卡号顺序号表
 */
public class CmcCardOrderNoInfo extends AbstractBean {

    private static final long serialVersionUID = 1L;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_card_order_no_info.CARD_PRODUCT_CODE
     * @description: 卡产品编号
     */
    @TablePk(index = 1)
    private String productRuleNo;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_card_order_no_info.CARD_NO_BEGIN
     * @description: 卡顺序号起始值
     */
    private String cardNoBegin;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_card_order_no_info.CARD_NO_END
     * @description: 卡顺序号终止值
     */
    private String cardNoEnd;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_card_order_no_info.CARD_NO_NOW
     * @description: 卡顺序号当前值
     */
    private String cardNoNow;

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

    /**
     * This method was generated by DAO-Tools Generator.
     * This method returns the value of the database column cmc_card_order_no_info.CARD_PRODUCT_CODE
     *
     * @return the value of cmc_card_order_no_info.CARD_PRODUCT_CODE
     */

    public String getProductRuleNo() {  return productRuleNo;  }

    /**
     * This method was generated by DAO-Tools Generator.
     * This method sets the value of the database column cmc_card_order_no_info.CARD_PRODUCT_CODE
     *
     * @param productRuleNo the value for cmc_card_order_no_info.CARD_PRODUCT_CODE
     */

    public void setProductRuleNo(String productRuleNo) {
        this.productRuleNo = productRuleNo == null ? null : productRuleNo.trim();;
    }

    /**
     * This method was generated by DAO-Tools Generator.
     * This method returns the value of the database column cmc_card_order_no_info.CARD_NO_BEGIN
     *
     * @return the value of cmc_card_order_no_info.CARD_NO_BEGIN
     */
    public String getCardNoBegin() {
        return cardNoBegin;
    }

    /**
     * This method was generated by DAO-Tools Generator.
     * This method sets the value of the database column cmc_card_order_no_info.CARD_NO_BEGIN
     *
     * @param cardNoBegin the value for cmc_card_order_no_info.CARD_NO_BEGIN
     */
    public void setCardNoBegin(String cardNoBegin) {
        this.cardNoBegin = cardNoBegin == null ? null : cardNoBegin.trim();
    }

    /**
     * This method was generated by DAO-Tools Generator.
     * This method returns the value of the database column cmc_card_order_no_info.CARD_NO_END
     *
     * @return the value of cmc_card_order_no_info.CARD_NO_END
     */
    public String getCardNoEnd() {
        return cardNoEnd;
    }

    /**
     * This method was generated by DAO-Tools Generator.
     * This method sets the value of the database column cmc_card_order_no_info.CARD_NO_END
     *
     * @param cardNoEnd the value for cmc_card_order_no_info.CARD_NO_END
     */
    public void setCardNoEnd(String cardNoEnd) {
        this.cardNoEnd = cardNoEnd == null ? null : cardNoEnd.trim();
    }

    /**
     * This method was generated by DAO-Tools Generator.
     * This method returns the value of the database column cmc_card_order_no_info.CARD_NO_NOW
     *
     * @return the value of cmc_card_order_no_info.CARD_NO_NOW
     */
    public String getCardNoNow() {
        return cardNoNow;
    }

    /**
     * This method was generated by DAO-Tools Generator.
     * This method sets the value of the database column cmc_card_order_no_info.CARD_NO_NOW
     *
     * @param cardNoNow the value for cmc_card_order_no_info.CARD_NO_NOW
     */
    public void setCardNoNow(String cardNoNow) {
        this.cardNoNow = cardNoNow == null ? null : cardNoNow.trim();
    }

}