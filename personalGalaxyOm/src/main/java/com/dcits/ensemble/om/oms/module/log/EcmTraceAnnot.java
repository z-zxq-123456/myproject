package com.dcits.ensemble.om.oms.module.log;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/09/23 11:23:29.
 * ECM_TRACE_ANNOT 日志标注表
 */
public class EcmTraceAnnot extends AbstractBean {


    /**
     * This field is ECM_TRACE_ANNOT.CIR_ID 调用环ID
     */
    private String cirId;

    /**
     * This field is ECM_TRACE_ANNOT.ANNOT_TIME 标注时间
     */
    private String annotTime;

    /**
     * This field is ECM_TRACE_ANNOT.ANNOT_LEV 标注级别
     */
    private String annotLev;

    /**
     * This field is ECM_TRACE_ANNOT.ANNOT_TYPE 标注类型
     */
    private String annotType;

    /**
     * 标注类型名
     */
    private String annotTypeName;

    /**
     * This field is ECM_TRACE_ANNOT.ANNOT_CLS 标注类名
     */
    private String annotCls;

    /**
     * This field is ECM_TRACE_ANNOT.ANNOT_MTD 标注方法名
     */
    private String annotMtd;

    /**
     * This field is ECM_TRACE_ANNOT.ANNOT_ROWNM 标注行数
     */
    private String annotRownm;

    /**
     * This field is ECM_TRACE_ANNOT.ANNOT_TEXT 标注内容
     */
    private String annotText;

    /**
     * This field is ECM_TRACE_ANNOT.ANNOT_THREAD_NUM 线程号
     */
    private String annotThreadNum;


    /**
     * @return the value of  ECM_TRACE_ANNOT.CIR_ID 
     */


    /**
     * @return the value of  ECM_TRACE_ANNOT.ANNOT_TIME 
     */
    public String getAnnotTime() {
        return annotTime;
    }

    /**
     * @param annotTime the value for ECM_TRACE_ANNOT.ANNOT_TIME 
     */
    public void setAnnotTime(String annotTime) {
        this.annotTime = annotTime == null ? null : annotTime.trim();
    }

    /**
     * @return the value of  ECM_TRACE_ANNOT.ANNOT_LEV 
     */
    public String getAnnotLev() {
        return annotLev;
    }

    /**
     * @param annotLev the value for ECM_TRACE_ANNOT.ANNOT_LEV 
     */
    public void setAnnotLev(String annotLev) {
        this.annotLev = annotLev == null ? null : annotLev.trim();
    }

    /**
     * @return the value of  ECM_TRACE_ANNOT.ANNOT_TYPE 
     */
    public String getAnnotType() {
        return annotType;
    }

    /**
     * @param annotType the value for ECM_TRACE_ANNOT.ANNOT_TYPE 
     */
    public void setAnnotType(String annotType) {
        this.annotType = annotType == null ? null : annotType.trim();
    }

    public String getAnnotTypeName() {return annotTypeName;}

    public void setAnnotTypeName(String annotTypeName) {this.annotTypeName = annotTypeName == null ? null : annotTypeName.trim();}

    /**
     * @return the value of  ECM_TRACE_ANNOT.ANNOT_CLS 
     */
    public String getAnnotCls() {
        return annotCls;
    }

    /**
     * @param annotCls the value for ECM_TRACE_ANNOT.ANNOT_CLS 
     */
    public void setAnnotCls(String annotCls) {
        this.annotCls = annotCls == null ? null : annotCls.trim();
    }

    /**
     * @return the value of  ECM_TRACE_ANNOT.ANNOT_MTD 
     */
    public String getAnnotMtd() {
        return annotMtd;
    }

    /**
     * @param annotMtd the value for ECM_TRACE_ANNOT.ANNOT_MTD 
     */
    public void setAnnotMtd(String annotMtd) {
        this.annotMtd = annotMtd == null ? null : annotMtd.trim();
    }

    /**
     * @return the value of  ECM_TRACE_ANNOT.ANNOT_ROWNM 
     */
    public String getAnnotRownm() {
        return annotRownm;
    }

    /**
     * @param annotRownm the value for ECM_TRACE_ANNOT.ANNOT_ROWNM 
     */
    public void setAnnotRownm(String annotRownm) {
        this.annotRownm = annotRownm == null ? null : annotRownm.trim();
    }

    /**
     * @return the value of  ECM_TRACE_ANNOT.ANNOT_TEXT 
     */
    public String getAnnotText() {
        return annotText;
    }

    /**
     * @param annotText the value for ECM_TRACE_ANNOT.ANNOT_TEXT 
     */
    public void setAnnotText(String annotText) {
        this.annotText = annotText == null ? null : annotText.trim();
    }

    /**
     * @return the value of  ECM_TRACE_ANNOT.ANNOT_THREAD_NUM 
     */
    public String getAnnotThreadNum() {
        return annotThreadNum;
    }

    /**
     * @param annotThreadNum the value for ECM_TRACE_ANNOT.ANNOT_THREAD_NUM 
     */
    public void setAnnotThreadNum(String annotThreadNum) {
        this.annotThreadNum = annotThreadNum == null ? null : annotThreadNum.trim();
    }

    public String getCirId() {
        return cirId;
    }

    public void setCirId(String cirId) {
        this.cirId = cirId;
    }
}