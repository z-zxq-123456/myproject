package com.dcits.ensemble.om.cmc.dbModel;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/8/25
 */
public class CmcProductType extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is MB_PROD_TYPE.PROD_TYPE 产品类型
     */
    @TablePk(index = 1)
    private String cardProductCode;
    /**
     * This field is MB_PROD_TYPE.COLUMN_STATUS
     */
    private String columnStatus;

    /**
     * This field is MB_PROD_TYPE.PROD_DESC 产品类型描述
     */
    private String cardProductName;

    /**
     * This field is MB_PROD_TYPE.STATUS 状态
     A：有效
     F：无效
     */
    private String status;

    private String reqNum;

    private String publishChannel;

    private String cardProductType;

    private String operateType;

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getPublishChannel() {
        return publishChannel;
    }

    public void setPublishChannel(String publishChannel) {
        this.publishChannel = publishChannel;
    }

    public String getCardProductType() {
        return cardProductType;
    }

    public void setCardProductType(String cardProductType) {
        this.cardProductType = cardProductType;
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

    public String getColumnStatus() {
        return columnStatus;
    }

    public void setColumnStatus(String columnStatus) {
        this.columnStatus = columnStatus;
    }

    public String getCardProductName() {
        return cardProductName;
    }

    public void setCardProductName(String cardProductName) {
        this.cardProductName = cardProductName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
