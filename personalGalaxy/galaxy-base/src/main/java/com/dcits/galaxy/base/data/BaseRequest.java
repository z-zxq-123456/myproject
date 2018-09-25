/**
 * <p>Title: BusiRequest.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2015年1月13日 下午2:43:03
 * @version V1.0
 */
package com.dcits.galaxy.base.data;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.base.validate.V;

/**
 * @author Tim
 * @version V1.0
 * @description
 * @update 2015年1月13日 下午2:43:03
 */

public class BaseRequest<S extends ISysHead, L extends ILocalHead, A extends IAppHead> extends AbstractBean {

    private static final long serialVersionUID = 1L;

    /**
     * 请求报文
     *
     * @fields inMsg
     */
    @Deprecated
    private String inMsg;

    /**
     * 平台uid
     *
     * @fields uid
     */
    private String uid;

    /**
     * 是否子服务流程请求
     */
    private boolean subRequest = false;

    /**
     * 是否执行提交，此属性在subRequest为true检查是否需要执行C类提交服务
     */
    private boolean doSubmit = false;

    /**
     * 是否执行检查，此属性在subRequest为true检查是否需要执行A\D\O类检查服务
     */
    private boolean doCheck = true;

    /**
     * 是否执行授权、确认检查，此属性在subRequest为true检查是否需要执行A\D\O类检查服务
     */
    private boolean doACCheck = false;

    /**
     * 提交服务的顺序号，表明一个服务的submit提交的节点数据
     */
    private int submitSeqNo = 0;

    /**
     * 全局事物控制开启标志
     */
    private String dtpFlag = "N";

    /**
     * 系统头
     *
     * @fields sysHead
     */
    @V(desc = "系统头", notNull = true)
    protected S sysHead;

    /**
     * 应用头
     *
     * @fields appHead
     */
    @V(desc = "应用头")
    protected A appHead;

    /**
     * 本地头
     *
     * @fields localHead
     */
    @V(desc = "本地头")
    protected L localHead;

    /**
     * 获取系统头
     *
     * @return
     */
    public S getSysHead() {
        return this.sysHead;
    }

    /**
     * 设置系统头
     *
     * @param sysHead
     */
    public void setSysHead(S sysHead) {
        this.sysHead = sysHead;
    }

    /**
     * 获取本地头
     *
     * @return
     */
    public L getLocalHead() {
        return this.localHead;
    }

    /**
     * 设置本地头
     *
     * @param localHead
     */
    public void setLocalHead(L localHead) {
        this.localHead = localHead;
    }

    /**
     * 获取应用头
     *
     * @return
     */
    public A getAppHead() {
        return this.appHead;
    }

    /**
     * 设置应用头
     *
     * @param appHead
     */
    public void setAppHead(A appHead) {
        this.appHead = appHead;
    }

    /**
     * @return inMsg : return the property inMsg.
     */
    @Deprecated
    public String getInMsg() {
        return inMsg;
    }

    /**
     * @param inMsg : set the property inMsg.
     */
    @Deprecated
    public void setInMsg(String inMsg) {
        this.inMsg = inMsg;
    }

    /**
     * @return uid : return the property uid.
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid : set the property uid.
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isSubRequest() {
        return subRequest;
    }

    public void setSubRequest(boolean subRequest) {
        this.subRequest = subRequest;
    }

    public boolean isDoSubmit() {
        return doSubmit;
    }

    public void setDoSubmit(boolean doSubmit) {
        this.doSubmit = doSubmit;
    }

    public boolean isDoCheck() {
        return doCheck;
    }

    public void setDoCheck(boolean doCheck) {
        this.doCheck = doCheck;
    }

    public int getSubmitSeqNo() {
        return submitSeqNo;
    }

    public void setSubmitSeqNo(int submitSeqNo) {
        this.submitSeqNo = submitSeqNo;
    }

    public String getDtpFlag() {
        return dtpFlag;
    }

    public void setDtpFlag(String dtpFlag) {
        this.dtpFlag = dtpFlag;
    }

    public boolean isDoACCheck() {
        return doACCheck;
    }

    public void setDoACCheck(boolean doACCheck) {
        this.doACCheck = doACCheck;
    }
}
