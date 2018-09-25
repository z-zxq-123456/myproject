package com.dcits.galaxy.logcover.model;

import com.dcits.galaxy.base.bean.AbstractBean;




/**
 * Created by admin on 2016/10/11 16:03:10.
 */
public class EcmTcecirSr extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_TCECIR_SR.CIR_ID 调用环ID
     */    
    private String cirId;

    /**
     * This field is ECM_TCECIR_SR.TRACE_ID 调用链ID
     */
    private String traceId;

    /**
     * This field is ECM_TCECIR_SR.CIR_SERVER_STTM 服务端开始时间
     */
    private String cirServerSttm;

    /**
     * This field is ECM_TCECIR_SR.CIR_SERVER_THDNUM 服务端线程号
     */
    private String cirServerThdnum;

    /**
     * @return the value of  ECM_TCECIR_SR.CIR_ID 
     */
    public String getCirId() {
        return cirId;
    }

    /**
     * @param cirId the value for ECM_TCECIR_SR.CIR_ID 
     */
    public void setCirId(String cirId) {
        this.cirId = cirId;
    }

    /**
     * @return the value of  ECM_TCECIR_SR.TRACE_ID 
     */
    public String getTraceId() {
        return traceId;
    }

    /**
     * @param traceId the value for ECM_TCECIR_SR.TRACE_ID 
     */
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    /**
     * @return the value of  ECM_TCECIR_SR.CIR_SERVER_STTM 
     */
    public String getCirServerSttm() {
        return cirServerSttm;
    }

    /**
     * @param cirServerSttm the value for ECM_TCECIR_SR.CIR_SERVER_STTM 
     */
    public void setCirServerSttm(String cirServerSttm) {
        this.cirServerSttm = cirServerSttm == null ? "" : cirServerSttm.trim();
    }

    /**
     * @return the value of  ECM_TCECIR_SR.CIR_SERVER_THDNUM 
     */
    public String getCirServerThdnum() {
        return cirServerThdnum;
    }

    /**
     * @param cirServerThdnum the value for ECM_TCECIR_SR.CIR_SERVER_THDNUM 
     */
    public void setCirServerThdnum(String cirServerThdnum) {
        this.cirServerThdnum = cirServerThdnum == null ? "" : cirServerThdnum.trim();
    }

	@Override
	public String toString() {
		return "EcmTcecirSr [cirId=" + cirId + ", traceId=" + traceId
				+ ", cirServerSttm=" + cirServerSttm + ", cirServerThdnum="
				+ cirServerThdnum + "]";
	}
    
    
}