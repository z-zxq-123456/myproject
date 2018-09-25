package com.dcits.galaxy.logcover.model;


import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.logcover.config.ConfigConstants;

/**
 * Created by admin on 2016/10/11 16:03:10.
 */
public class EcmTcecirCr extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_TCECIR_CR.CIR_ID 调用环ID
     */   
    private String cirId;

    /**
     * This field is ECM_TCECIR_CR.TRACE_ID 调用链ID
     */
    private String traceId;

    /**
     * This field is ECM_TCECIR_CR.CIR_CLIENT_STATUS 客户端调用状态  0015001 成功,0015002 失败
     */
    private String cirClientStatus = ConfigConstants.INVOKE_STATUS_SUCCEED;

    /**
     * This field is ECM_TCECIR_CR.CIR_CLIENT_ENTM 客户端结束时间
     */
    private String cirClientEntm;
    /**
     * This field is ECM_TCECIR_CR.CIR_OUT_MSG 输出报文
     */
    private String cirOutMsg;

   
    /**
     * This field is ECM_TCECIR_CR.CIR_SERVER_FAILINFO 客户端失败信息
     */
    private String cirServerFailinfo;
    

    /**
     * @return the value of  ECM_TCECIR_CR.CIR_ID 
     */
    public String getCirId() {
        return cirId;
    }

    /**
     * @param cirId the value for ECM_TCECIR_CR.CIR_ID 
     */
    public void setCirId(String cirId) {
        this.cirId = cirId;
    }

    /**
     * @return the value of  ECM_TCECIR_CR.TRACE_ID 
     */
    public String getTraceId() {
        return traceId;
    }

    /**
     * @param traceId the value for ECM_TCECIR_CR.TRACE_ID 
     */
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }


    /**
     * @return the value of  ECM_TCECIR_CR.CIR_CLIENT_ENTM 
     */
    public String getCirClientEntm() {
        return cirClientEntm;
    }

    /**
     * @param cirClientEntm the value for ECM_TCECIR_CR.CIR_CLIENT_ENTM 
     */
    public void setCirClientEntm(String cirClientEntm) {
        this.cirClientEntm = cirClientEntm == null ? "" : cirClientEntm.trim();
    }

    public String getCirOutMsg() {
    	return cirOutMsg==null?"":cirOutMsg;
	}

	public void setCirOutMsg(String cirOutMsg) {
		this.cirOutMsg = cirOutMsg==null?"": cirOutMsg.trim();
	}

	
	public String getCirClientStatus() {
		return cirClientStatus;
	}

	public void setCirClientStatus(String cirClientStatus) {
		this.cirClientStatus = cirClientStatus== null ? "" : cirClientStatus.trim();
	}
	
	//为了编码方便的过载方法，可屏蔽常量设置
    public void setCirClientStatus(boolean traceStatus) {
    	if(traceStatus){
    		this.cirClientStatus = ConfigConstants.INVOKE_STATUS_SUCCEED;
    	}else{
    		this.cirClientStatus = ConfigConstants.INVOKE_STATUS_FAIL;
    	}
    }

	public String getCirServerFailinfo() {
		return cirServerFailinfo==null?"":cirServerFailinfo;
	}

	public void setCirServerFailinfo(String cirServerFailinfo) {
		this.cirServerFailinfo = cirServerFailinfo== null ? "" : cirServerFailinfo.trim();
	}

	@Override
	public String toString() {
		return "EcmTcecirCr [cirId=" + cirId + ", traceId=" + traceId
				+ ", cirClientStatus=" + cirClientStatus + ", cirClientEntm="
				+ cirClientEntm + ", cirOutMsg=" + cirOutMsg
				+ ", cirServerFailinfo=" + cirServerFailinfo + "]";
	}
    
    
}