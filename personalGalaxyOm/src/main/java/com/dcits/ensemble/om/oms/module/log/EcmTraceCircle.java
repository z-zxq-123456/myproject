package com.dcits.ensemble.om.oms.module.log;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

import java.util.List;

/**
 * Created by admin on 2016/09/23 11:23:29.
 * ECM_TRACE_CIRCLE 调用环表
 */
public class EcmTraceCircle extends AbstractBean {



    /**
     * This field is ECM_TRACE_CIRCLE.CIR_ID 调用环ID
     */

    private String cirId;

    /**
     * This field is ECM_TRACE_CIRCLE.TRACE_ID 调用链ID
     */
    private String traceId;

    /**
     * This field is ECM_TRACE_CIRCLE.CIR_PARENT_ID 上级调用环ID
     */
    private String cirParentId;

    /**
     * This field is ECM_TRACE_CIRCLE.CIR_TYPE 调用方式
     */
    private String cirType;

    private String cirTypeName;

    /**
     * This field is ECM_TRACE_CIRCLE.CIR_STATUS 调用状态
     */
    private String cirStatus;


    /**
     * This field is ECM_TRACE_CIRCLE.CIR_CLIENT_IPPORT 客户端IP端口
     */
    private String cirClientIpport;

    /**
     * This field is ECM_TRACE_CIRCLE.CIR_CLIENT_STTM 客户端开始时间
     */
    private String cirClientSttm;

    /**
     * This field is ECM_TRACE_CIRCLE.CIR_CLIENT_ENTM 客户端结束时间
     */
    private String cirClientEntm;

    /**
     * This field is ECM_TRACE_CIRCLE.CIR_SERVER_IPPORT 服务端IP端口
     */
    private String cirServerIpport;

    /**
     * This field is ECM_TRACE_CIRCLE.CIR_SERVER_SER 服务端服务名
     */
    private String cirServerSer;

    /**
     * This field is ECM_TRACE_CIRCLE.CIR_SERVER_MTD 服务端方法名
     */
    private String cirServerMtd;

    /**
     * This field is ECM_TRACE_CIRCLE.CIR_SERVER_STTM 服务端开始时间
     */
    private String cirServerSttm;

    /**
     * This field is ECM_TRACE_CIRCLE.CIR_SERVER_ENTM 服务端结束时间
     */
    private String cirServerEntm;


    private String cirExeTime;//执行时间


    private String circonsupTime;//网络消耗

    /**
     * This field is ECM_TRACE_CIRCLE.CIR_IN_MSG 输入报文
     */
    private String cirInMsg;

    /**
     * This field is ECM_TRACE_CIRCLE.CIR_OUT_MSG 输出报文
     */
    private String cirOutMsg;

    /**
     * This field is ECM_TRACE_CIRCLE.CIR_CLIENT_THDNUM 客户端线程号
     */
    private String cirClientThdnum;

    /**
     * This field is ECM_TRACE_CIRCLE.CIR_SERVER_THDNUM 服务端线程号
     */

    private String cirServerThdnum;

    private String cirClientStatus; //客户端调用状态

    private String cirClientStatusName;

    private String cirServerStatus; //服务端调用状态

    private String cirServerStatusName;

    private String cirServerFailinfo;//客户端失败信息

    private String appIntantName;//实用实例名称

    private String cirServerSerAndMtd;//服务端服务方法

    private List<EcmTraceAnnot> cirAnnot;//环注解链表




    /**
     * @return the value of  ECM_TRACE_CIRCLE.CIR_TYPE
     */
    public String getCirType() {
        return cirType;
    }

    /**
     * @param cirType the value for ECM_TRACE_CIRCLE.CIR_TYPE
     */
    public void setCirType(String cirType) {
        this.cirType = cirType == null ? null : cirType.trim();
    }

    /**
     * @return the value of  ECM_TRACE_CIRCLE.CIR_STATUS
     */
    public String getCirStatus() {
        return cirStatus;
    }

    /**
     * @param cirStatus the value for ECM_TRACE_CIRCLE.CIR_STATUS
     */
    public void setCirStatus(String cirStatus) {
        this.cirStatus = cirStatus == null ? null : cirStatus.trim();
    }

    /**
     * @return the value of  ECM_TRACE_CIRCLE.CIR_CLIENT_IPPORT
     */
    public String getCirClientIpport() {
        return cirClientIpport;
    }

    /**
     * @param cirClientIpport the value for ECM_TRACE_CIRCLE.CIR_CLIENT_IPPORT
     */
    public void setCirClientIpport(String cirClientIpport) {
        this.cirClientIpport = cirClientIpport == null ? null : cirClientIpport.trim();
    }

    /**
     * @return the value of  ECM_TRACE_CIRCLE.CIR_CLIENT_STTM
     */
    public String getCirClientSttm() {
        return cirClientSttm;
    }

    /**
     * @param cirClientSttm the value for ECM_TRACE_CIRCLE.CIR_CLIENT_STTM
     */
    public void setCirClientSttm(String cirClientSttm) {
        this.cirClientSttm = cirClientSttm == null ? null : cirClientSttm.trim();
    }

    /**
     * @return the value of  ECM_TRACE_CIRCLE.CIR_CLIENT_ENTM
     */
    public String getCirClientEntm() {
        return cirClientEntm;
    }

    /**
     * @param cirClientEntm the value for ECM_TRACE_CIRCLE.CIR_CLIENT_ENTM
     */
    public void setCirClientEntm(String cirClientEntm) {
        this.cirClientEntm = cirClientEntm == null ? null : cirClientEntm.trim();
    }

    /**
     * @return the value of  ECM_TRACE_CIRCLE.CIR_SERVER_IPPORT
     */
    public String getCirServerIpport() {
        return cirServerIpport;
    }

    /**
     * @param cirServerIpport the value for ECM_TRACE_CIRCLE.CIR_SERVER_IPPORT
     */
    public void setCirServerIpport(String cirServerIpport) {
        this.cirServerIpport = cirServerIpport == null ? null : cirServerIpport.trim();
    }

    /**
     * @return the value of  ECM_TRACE_CIRCLE.CIR_SERVER_SER
     */
    public String getCirServerSer() {
        return cirServerSer;
    }

    /**
     * @param cirServerSer the value for ECM_TRACE_CIRCLE.CIR_SERVER_SER
     */
    public void setCirServerSer(String cirServerSer) {
        this.cirServerSer = cirServerSer == null ? null : cirServerSer.trim();
    }

    /**
     * @return the value of  ECM_TRACE_CIRCLE.CIR_SERVER_MTD
     */
    public String getCirServerMtd() {
        return cirServerMtd;
    }

    /**
     * @param cirServerMtd the value for ECM_TRACE_CIRCLE.CIR_SERVER_MTD
     */
    public void setCirServerMtd(String cirServerMtd) {
        this.cirServerMtd = cirServerMtd == null ? null : cirServerMtd.trim();
    }

    /**
     * @return the value of  ECM_TRACE_CIRCLE.CIR_SERVER_STTM
     */
    public String getCirServerSttm() {
        return cirServerSttm;
    }

    /**
     * @param cirServerSttm the value for ECM_TRACE_CIRCLE.CIR_SERVER_STTM
     */
    public void setCirServerSttm(String cirServerSttm) {
        this.cirServerSttm = cirServerSttm == null ? null : cirServerSttm.trim();
    }

    /**
     * @return the value of  ECM_TRACE_CIRCLE.CIR_SERVER_ENTM
     */
    public String getCirServerEntm() {
        return cirServerEntm;
    }

    /**
     * @param cirServerEntm the value for ECM_TRACE_CIRCLE.CIR_SERVER_ENTM
     */
    public void setCirServerEntm(String cirServerEntm) {
        this.cirServerEntm = cirServerEntm == null ? null : cirServerEntm.trim();
    }

    /**
     * @return the value of  ECM_TRACE_CIRCLE.CIR_IN_MSG
     */
    public String getCirInMsg() {
        return cirInMsg;
    }

    /**
     * @param cirInMsg the value for ECM_TRACE_CIRCLE.CIR_IN_MSG
     */
    public void setCirInMsg(String cirInMsg) {
        this.cirInMsg = cirInMsg == null ? null : cirInMsg.trim();
    }

    /**
     * @return the value of  ECM_TRACE_CIRCLE.CIR_OUT_MSG
     */
    public String getCirOutMsg() {
        return cirOutMsg;
    }

    /**
     * @param cirOutMsg the value for ECM_TRACE_CIRCLE.CIR_OUT_MSG
     */
    public void setCirOutMsg(String cirOutMsg) {
        this.cirOutMsg = cirOutMsg == null ? null : cirOutMsg.trim();
    }

    public String getCirClientThdnum() {
        return cirClientThdnum;
    }

    public void setCirClientThdnum(String cirClientThdnum) {
        this.cirClientThdnum = cirClientThdnum;
    }

    public String getCirServerThdnum() {
        return cirServerThdnum;
    }

    public void setCirServerThdnum(String cirServerThdnum) {
        this.cirServerThdnum = cirServerThdnum;
    }

    public String getCirExeTime() {
        return cirExeTime;
    }

    public void setCirExeTime(String cirExeTime) {
        this.cirExeTime = cirExeTime;
    }

    public String getCirconsupTime() {
        return circonsupTime;
    }

    public void setCirconsupTime(String circonsupTime) {
        this.circonsupTime = circonsupTime;
    }

    public List<EcmTraceAnnot> getCirAnnot() {
        return cirAnnot;
    }

    public void setCirAnnot(List<EcmTraceAnnot> cirAnnot) {
        this.cirAnnot = cirAnnot;
    }

    public String getCirClientStatus() {
        return cirClientStatus;
    }

    public void setCirClientStatus(String cirClientStatus) {
        this.cirClientStatus = cirClientStatus;
    }

    public String getCirServerStatus() {
        return cirServerStatus;
    }

    public void setCirServerStatus(String cirServerStatus) {
        this.cirServerStatus = cirServerStatus;
    }

    public String getCirServerFailinfo() {
        return cirServerFailinfo;
    }

    public void setCirServerFailinfo(String cirServerFailinfo) {
        this.cirServerFailinfo = cirServerFailinfo;
    }


    public String getCirClientStatusName() {
        return cirClientStatusName;
    }

    public void setCirClientStatusName(String cirClientStatusName) {
        this.cirClientStatusName = cirClientStatusName;
    }

    public String getCirServerStatusName() {
        return cirServerStatusName;
    }

    public void setCirServerStatusName(String cirServerStatusName) {
        this.cirServerStatusName = cirServerStatusName;
    }

    public String getCirTypeName() {
        return cirTypeName;
    }

    public void setCirTypeName(String cirTypeName) {
        this.cirTypeName = cirTypeName;
    }

    public String getAppIntantName() {
        return appIntantName;
    }

    public void setAppIntantName(String appIntantName) {
        this.appIntantName = appIntantName;
    }

    public String getCirId() {
        return cirId;
    }

    public void setCirId(String cirId) {
        this.cirId = cirId;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getCirParentId() {
        return cirParentId;
    }

    public void setCirParentId(String cirParentId) {
        this.cirParentId = cirParentId;
    }

    public String getCirServerSerAndMtd() {
        return cirServerSerAndMtd;
    }

    public void setCirServerSerAndMtd(String cirServerSerAndMtd) {
        this.cirServerSerAndMtd = cirServerSerAndMtd;
    }
}