package com.dcits.ensemble.om.pf.bean;

/**
 * Created by ligan on 2017/3/27.
 */
public class DbProdCheckList {
  private String operateType;
  private String currentSystemTime;
  private String operateId;
  private String tableFullName;
  private byte[] keyValue;
  private String checkTime;
  private String checkText;
  private String operatorCurrentSystem;
  private String reqNo;
  private String PublishTime;
  private String operatorCheck;
    /**
     * This field is PARA_DIFFERENCE_CHECK_PUBLISH.DATA_DUI 批量删除/添加/修改参数表数据的报文
     */
    private byte[] dataDui;

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getCurrentSystemTime() {
        return currentSystemTime;
    }

    public void setCurrentSystemTime(String currentSystemTime) {
        this.currentSystemTime = currentSystemTime;
    }

    public String getOperateId() {
        return operateId;
    }

    public void setOperateId(String operateId) {
        this.operateId = operateId;
    }

    public String getTableFullName() {
        return tableFullName;
    }

    public void setTableFullName(String tableFullName) {
        this.tableFullName = tableFullName;
    }

    public byte[] getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(byte[] keyValue) {
        this.keyValue = keyValue;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckText() {
        return checkText;
    }

    public void setCheckText(String checkText) {
        this.checkText = checkText;
    }

    public String getOperatorCurrentSystem() {
        return operatorCurrentSystem;
    }

    public void setOperatorCurrentSystem(String operatorCurrentSystem) {
        this.operatorCurrentSystem = operatorCurrentSystem;
    }

    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

    public String getPublishTime() {
        return PublishTime;
    }

    public void setPublishTime(String publishTime) {
        PublishTime = publishTime;
    }

    public String getOperatorCheck() {
        return operatorCheck;
    }

    public void setOperatorCheck(String operatorCheck) {
        this.operatorCheck = operatorCheck;
    }

    public byte[] getDataDui() {
        return dataDui;
    }

    public void setDataDui(byte[] dataDui) {
        this.dataDui = dataDui;
    }

    public byte[] getOlddataUpd() {
        return olddataUpd;
    }

    public void setOlddataUpd(byte[] olddataUpd) {
        this.olddataUpd = olddataUpd;
    }

    /**
     * This field is PARA_DIFFERENCE_CHECK_PUBLISH.OLDDATA_UPD 批量修改之前的参数表数据报文
     */
    private byte[] olddataUpd;
}
