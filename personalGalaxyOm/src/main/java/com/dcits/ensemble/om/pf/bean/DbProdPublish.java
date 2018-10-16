package com.dcits.ensemble.om.pf.bean;

/**
 * Created by ligan on 2017/3/21.
 */
public class DbProdPublish {
    //产品类型
    private String prodType;
    //变更，发布日期
    private String publishTime ;
    //摘要
    private String checkText;
    //修改项
    private String uprightColumn;
    //变更前值
    private String oldValue;
    //变更后值
    private String newValue;
    //交易柜员
    private String operator;
    //交易时间
    private String operatorTime;
    //复核柜员
    private String checkOperator;
    //复核时间
    private String checkTime;
    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getCheckText() {
        return checkText;
    }

    public void setCheckText(String checkText) {
        this.checkText = checkText;
    }

    public String getUprightColumn() {
        return uprightColumn;
    }

    public void setUprightColumn(String uprightColumn) {
        this.uprightColumn = uprightColumn;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(String operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getCheckOperator() {
        return checkOperator;
    }

    public void setCheckOperator(String checkOperator) {
        this.checkOperator = checkOperator;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }



}
