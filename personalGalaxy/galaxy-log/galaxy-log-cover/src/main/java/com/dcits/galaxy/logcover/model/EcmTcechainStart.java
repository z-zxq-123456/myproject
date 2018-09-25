package com.dcits.galaxy.logcover.model;

import com.dcits.galaxy.base.bean.AbstractBean;




/**
 * Created by admin on 2016/10/11 16:03:10.
 */
public class EcmTcechainStart extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_TCECHAIN_START.TRACE_ID  调用链ID
     */    
    private String traceId;

    /**
     * This field is ECM_TCECHAIN_START.TRACE_STTIME 开始时间
     */
    private String traceSttime;

    /**
     * This field is ECM_TCECHAIN_START.TRACE_IN_SER 入口服务名
     */
    private String traceInSer;

    /**
     * This field is ECM_TCECHAIN_START.TRACE_IN_MTD 入口方法名
     */
    private String traceInMtd;

    /**
     * @return the value of  ECM_TCECHAIN_START.TRACE_ID 
     */
    public String getTraceId() {
        return traceId;
    }

    /**
     * @param traceId the value for ECM_TCECHAIN_START.TRACE_ID 
     */
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    /**
     * @return the value of  ECM_TCECHAIN_START.TRACE_STTIME 
     */
    public String getTraceSttime() {
        return traceSttime;
    }

    /**
     * @param traceSttime the value for ECM_TCECHAIN_START.TRACE_STTIME 
     */
    public void setTraceSttime(String traceSttime) {
        this.traceSttime = traceSttime == null ? "" : traceSttime.trim();
    }

    /**
     * @return the value of  ECM_TCECHAIN_START.TRACE_IN_SER 
     */
    public String getTraceInSer() {
        return traceInSer==null?"":traceInSer;
    }

    /**
     * @param traceInSer the value for ECM_TCECHAIN_START.TRACE_IN_SER 
     */
    public void setTraceInSer(String traceInSer) {
        this.traceInSer = traceInSer == null ? "" : traceInSer.trim();
    }

    /**
     * @return the value of  ECM_TCECHAIN_START.TRACE_IN_MTD 
     */
    public String getTraceInMtd() {
        return traceInMtd==null?"":traceInSer;
    }

    /**
     * @param traceInMtd the value for ECM_TCECHAIN_START.TRACE_IN_MTD 
     */
    public void setTraceInMtd(String traceInMtd) {
        this.traceInMtd = traceInMtd == null ? "" : traceInMtd.trim();
    }

	@Override
	public String toString() {
		return "EcmTcechainStart [traceId=" + traceId + ", traceSttime="
				+ traceSttime + ", traceInSer=" + traceInSer + ", traceInMtd="
				+ traceInMtd + "]";
	}
    
    
}