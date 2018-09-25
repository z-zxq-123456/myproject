package com.dcits.galaxy.logcover.model;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.logcover.config.ConfigConstants;

/**
 * Created by admin on 2016/10/11 16:03:10.
 */
public class EcmTcecinEnd extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_TCECIN_END.TRACE_ID 调用链ID
     */    
    private String traceId;

    /**
     * This field is ECM_TCECIN_END.TRACE_ENTIME 结束时间
     */
    private String traceEntime;

    /**
     * This field is ECM_TCECIN_END.TRACE_STATUS 调用状态  0015001 成功,0015002 失败
     */
    private String traceStatus = ConfigConstants.INVOKE_STATUS_SUCCEED;

    /**
     * @return the value of  ECM_TCECIN_END.TRACE_ID 
     */
    public String getTraceId() {
        return traceId;
    }

    /**
     * @param traceId the value for ECM_TCECIN_END.TRACE_ID 
     */
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    /**
     * @return the value of  ECM_TCECIN_END.TRACE_ENTIME 
     */
    public String getTraceEntime() {
        return traceEntime;
    }

    /**
     * @param traceEntime the value for ECM_TCECIN_END.TRACE_ENTIME 
     */
    public void setTraceEntime(String traceEntime) {
        this.traceEntime = traceEntime == null ? "" : traceEntime.trim();
    }

    /**
     * @return the value of  ECM_TCECIN_END.TRACE_STATUS 
     */
    public String getTraceStatus() {
        return traceStatus;
    }

    /**
     * @param traceStatus the value for ECM_TCECIN_END.TRACE_STATUS 
     */
    public void setTraceStatus(String traceStatus) {
        this.traceStatus = traceStatus == null ? "" : traceStatus.trim();
    }
    //为了编码方便的过载方法，可屏蔽常量设置
    public void setTraceStatus(boolean traceStatus) {
    	if(traceStatus){
    		this.traceStatus = ConfigConstants.INVOKE_STATUS_SUCCEED;
    	}else{
    		this.traceStatus = ConfigConstants.INVOKE_STATUS_FAIL;
    	}
    }

	@Override
	public String toString() {
		return "EcmTcecinEnd [traceId=" + traceId + ", traceEntime="
				+ traceEntime + ", traceStatus=" + traceStatus + "]";
	}
    
    
}