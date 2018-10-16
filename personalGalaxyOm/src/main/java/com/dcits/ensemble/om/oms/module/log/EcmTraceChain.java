package com.dcits.ensemble.om.oms.module.log;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

import java.util.List;

/**
 * Created by admin on 2016/09/23 11:23:29.
 * ECM_TRACE_CHAIN 调用链表 
 */
public class EcmTraceChain extends AbstractBean {

    /**
     * This field is ECM_TRACE_CHAIN.TRACE_ID 调用链ID
     */

    private String traceId;

    /**
     * This field is ECM_TRACE_CHAIN.TRACE_STTIME 开始时间
     */
    private String traceSttime;

    /**
     * This field is ECM_TRACE_CHAIN.TRACE_ENTIME 结束时间
     */
    private String traceEntime;

    /**
     * This field is ECM_TRACE_CHAIN.TRACE_STATUS 调用状态
     */
    private String traceStatus;

    private String traceStatusName;

    /**
     * This field is ECM_TRACE_CHAIN.TRACE_IN_SER 入口服务名
     */
    private String traceInSer;

    /**
     * This field is ECM_TRACE_CHAIN.TRACE_IN_MTD 入口方法名
     */
    private String traceInMtd;

    /**
     * This field is ECM_TRACE_CHAIN.ACCT_NO 账号
     */
    private String acctNo;

    /**
     * This field is ECM_TRACE_CHAIN.CARD_NO 卡号
     */
    private String cardNo;

    /**
     * This field is ECM_TRACE_CHAIN.BRANCH_ID 网点号
     */
    private String branchId;

    /**
     * This field is ECM_TRACE_CHAIN.TELLER_ID 柜员号
     */
    private String tellerId;


    private String uuid;

    private String exeTime;//执行时长

    private String messageCode;

    private String messageType;

    private String serviceCode;

    private String allInfo;

    private String circleView;

 //   private List<EcmTraceCircle> circleList;

    private String startTime;//查询开始时间

    private String endTime;//查询结束时间

    /**
     * @return the value of  ECM_TRACE_CHAIN.TRACE_STTIME 
     */


    /**
     * @return the value of  ECM_TRACE_CHAIN.TRACE_ENTIME 
     */
    public String getTraceEntime() {
        return traceEntime;
    }

    /**
     * @param traceEntime the value for ECM_TRACE_CHAIN.TRACE_ENTIME 
     */
    public void setTraceEntime(String traceEntime) {
        this.traceEntime = traceEntime == null ? null : traceEntime.trim();
    }

    /**
     * @return the value of  ECM_TRACE_CHAIN.TRACE_STATUS 
     */
    public String getTraceStatus() {
        return traceStatus;
    }

    /**
     * @param traceStatus the value for ECM_TRACE_CHAIN.TRACE_STATUS 
     */
    public void setTraceStatus(String traceStatus) {
        this.traceStatus = traceStatus == null ? null : traceStatus.trim();
    }

    /**
     * @return the value of  ECM_TRACE_CHAIN.TRACE_IN_SER 
     */
    public String getTraceInSer() {
        return traceInSer;
    }

    /**
     * @param traceInSer the value for ECM_TRACE_CHAIN.TRACE_IN_SER 
     */
    public void setTraceInSer(String traceInSer) {
        this.traceInSer = traceInSer == null ? null : traceInSer.trim();
    }

    /**
     * @return the value of  ECM_TRACE_CHAIN.TRACE_IN_MTD 
     */
    public String getTraceInMtd() {
        return traceInMtd;
    }

    /**
     * @param traceInMtd the value for ECM_TRACE_CHAIN.TRACE_IN_MTD 
     */
    public void setTraceInMtd(String traceInMtd) {
        this.traceInMtd = traceInMtd == null ? null : traceInMtd.trim();
    }

    /**
     * @return the value of  ECM_TRACE_CHAIN.ACCT_NO 
     */
    public String getAcctNo() {
        return acctNo;
    }

    /**
     * @param acctNo the value for ECM_TRACE_CHAIN.ACCT_NO 
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo == null ? null : acctNo.trim();
    }

    /**
     * @return the value of  ECM_TRACE_CHAIN.CARD_NO 
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * @param cardNo the value for ECM_TRACE_CHAIN.CARD_NO 
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    /**
     * @return the value of  ECM_TRACE_CHAIN.BRANCH_ID 
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * @param branchId the value for ECM_TRACE_CHAIN.BRANCH_ID 
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId == null ? null : branchId.trim();
    }

    /**
     * @return the value of  ECM_TRACE_CHAIN.TELLER_ID 
     */
    public String getTellerId() {
        return tellerId;
    }

    /**
     * @param tellerId the value for ECM_TRACE_CHAIN.TELLER_ID 
     */
    public void setTellerId(String tellerId) {
        this.tellerId = tellerId == null ? null : tellerId.trim();
    }

    public String getExeTime() {
        return exeTime;
    }

    public void setExeTime(String exeTime) {
        this.exeTime = exeTime;
    }

    public String getTraceSttime() {
        return traceSttime;
    }

    public void setTraceSttime(String traceSttime) {
        this.traceSttime = traceSttime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }


    public String getTraceStatusName() {
        return traceStatusName;
    }

    public void setTraceStatusName(String traceStatusName) {
        this.traceStatusName = traceStatusName;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getAllInfo() {
        return allInfo;
    }

    public void setAllInfo(String allInfo) {
        this.allInfo = allInfo;
    }

    public String getCircleView() {
        return circleView;
    }

    public void setCircleView(String circleView) {
        this.circleView = circleView;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}