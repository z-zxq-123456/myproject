package com.dcits.galaxy.logcover.model;


import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.logcover.config.ConfigConstants;

/**
 * Created by admin on 2016/10/11 16:03:10.
 */
public class EcmTcecirSs extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_TCECIR_SS.CIR_ID 调用环ID
     */    
    private String cirId;

    /**
     * This field is ECM_TCECIR_SS.TRACE_ID 调用链ID
     */
    private String traceId;

    /**
     * This field is ECM_TCECIR_SS.CIR_SERVER_STATUS 服务端调用状态  0015001 成功,0015002 失败
     */
    private String cirServerStatus = ConfigConstants.INVOKE_STATUS_SUCCEED;

    /**
     * This field is ECM_TCECIR_SS.CIR_SERVER_ENTM 服务端结束时间
     */
    private String cirServerEntm;

   

    /**
     * @return the value of  ECM_TCECIR_SS.CIR_ID 
     */
    public String getCirId() {
        return cirId;
    }

    /**
     * @param cirId the value for ECM_TCECIR_SS.CIR_ID 
     */
    public void setCirId(String cirId) {
        this.cirId = cirId;
    }

    /**
     * @return the value of  ECM_TCECIR_SS.TRACE_ID 
     */
    public String getTraceId() {
        return traceId;
    }

    /**
     * @param traceId the value for ECM_TCECIR_SS.TRACE_ID 
     */
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

   

    public String getCirServerStatus() {
		return cirServerStatus;
	}

	public void setCirServerStatus(String cirServerStatus) {
		this.cirServerStatus = cirServerStatus ;
	}
	//为了编码方便的过载方法，可屏蔽常量设置
    public void setCirServerStatus(boolean traceStatus) {
    	if(traceStatus){
    		this.cirServerStatus = ConfigConstants.INVOKE_STATUS_SUCCEED;
    	}else{
    		this.cirServerStatus = ConfigConstants.INVOKE_STATUS_FAIL;
    	}
    }
	/**
     * @return the value of  ECM_TCECIR_SS.CIR_SERVER_ENTM 
     */
    public String getCirServerEntm() {
        return cirServerEntm;
    }

    /**
     * @param cirServerEntm the value for ECM_TCECIR_SS.CIR_SERVER_ENTM 
     */
    public void setCirServerEntm(String cirServerEntm) {
        this.cirServerEntm = cirServerEntm == null ? "" : cirServerEntm.trim();
    }

	@Override
	public String toString() {
		return "EcmTcecirSs [cirId=" + cirId + ", traceId=" + traceId
				+ ", cirServerStatus=" + cirServerStatus + ", cirServerEntm="
				+ cirServerEntm + "]";
	}
    
    
}