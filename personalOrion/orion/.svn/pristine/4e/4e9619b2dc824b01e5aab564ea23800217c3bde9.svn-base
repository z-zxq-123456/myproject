package com.dcits.orion.stria.dao.mapper;


import com.dcits.orion.stria.model.FlowModel;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Tim on 2015/5/18.
 */
public class Flow implements Serializable {

    private static final long serialVersionUID = -3180237083211273963L;
    /**
     * This field is stria_flow.flowid 流程ID
     */
    private String flowid;

    /**
     * This field is stria_flow.flowType 流程类型 01-服务流程；02-事件流程
     */
    private String flowType = "01";


    /**
     * This field is stria_flow.title 流程描述
     */
    private String title;


    /**
     * This field is stria_flow.init_num 初始流程节数
     */
    private BigDecimal initNum;


    /**
     * This field is stria_flow.version 版本号
     */
    private String version;


    /**
     * This field is stria_flow.state 可用开关
     */
    private BigDecimal state;


    /**
     * This field is stria_flow.createtime 创建时间
     */
    private String createtime;


    /**
     * This field is stria_flow.creator 创建人
     */
    private String creator;

    /**
     * This field is stria_flow.dtp_flag 全局事物开关
     */
    private String dtpFlag;

    /**
     * This field is stria_flow.time_out 超时时间
     */
    private BigDecimal timeOut;

    /**
     * This field is stria_flow.prodType 产品类型
     */
    private String prodType;

    /**
     * This field is stria_flow.eventType 事件类型
     */
    private String eventType;

    /**
     * This field is stria_flow.txFlag Spring事务标识
     */
    private String txFlag;


    private FlowModel flowModel;

    /**
     * @return the value of stria_flow.flowid 流程ID
     */
    public String getFlowid() {
        return flowid;
    }

    /**
     * @param flowid the value for stria_flow.flowid 流程ID
     */
    public void setFlowid(String flowid) {
        this.flowid = flowid == null ? null : flowid.trim();
    }

    /**
     * @return the value of stria_flow.title 流程描述
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the value for stria_flow.title 流程描述
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * @return the value of stria_flow.init_num 初始流程节数
     */
    public BigDecimal getInitNum() {
        return initNum;
    }

    /**
     * @param initNum the value for stria_flow.init_num 初始流程节数
     */
    public void setInitNum(BigDecimal initNum) {
        this.initNum = initNum;
    }

    /**
     * @return the value of stria_flow.version 版本号
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the value for stria_flow.version 版本号
     */
    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    /**
     * @return the value of stria_flow.state 可用开关
     */
    public BigDecimal getState() {
        return state;
    }

    /**
     * @param state the value for stria_flow.state 可用开关
     */
    public void setState(BigDecimal state) {
        this.state = state;
    }

    /**
     * @return the value of stria_flow.createtime 创建时间
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime the value for stria_flow.createtime 创建时间
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    /**
     * @return the value of stria_flow.creator 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator the value for stria_flow.creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * @return the value of stria_flow.dtp_flag 全局事物开关
     */
    public String getDtpFlag() {
        return this.dtpFlag == null ? "N" : this.dtpFlag.trim();
    }

    /**
     * @param dtpFlag the value for stria_flow.dtp_flag 全局事物开关
     */
    public void setDtpFlag(String dtpFlag) {
        this.dtpFlag = dtpFlag == null ? null : dtpFlag.trim();
    }

    public FlowModel getFlowModel() {
        return flowModel;
    }

    public void setFlowModel(FlowModel flowModel) {
        this.flowModel = flowModel;
    }

    public BigDecimal getTimeOut() {
        return null == timeOut ? new BigDecimal(-1) : timeOut;
    }

    public void setTimeOut(BigDecimal timeOut) {
        this.timeOut = timeOut;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getTxFlag() {
        return txFlag;
    }

    public void setTxFlag(String txFlag) {
        this.txFlag = txFlag;
    }
}
