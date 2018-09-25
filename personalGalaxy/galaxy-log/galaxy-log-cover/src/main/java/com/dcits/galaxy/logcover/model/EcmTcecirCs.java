package com.dcits.galaxy.logcover.model;


import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.logcover.config.ConfigConstants;

/**
 * Created by admin on 2016/10/11 16:03:10.
 */
public class EcmTcecirCs extends AbstractBean {
	
	private String link_mark =":";

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_TCECIR_CS.CIR_ID 调用环ID
     */    
    private String cirId;

    /**
     * This field is ECM_TCECIR_CS.TRACE_ID 调用链ID
     */
    private String traceId;

    /**
     * This field is ECM_TCECIR_CS.CIR_PARENT_ID 上级调用环ID
     */
    private String cirParentId;

    /**
     * This field is ECM_TCECIR_CS.CIR_TYPE 调用方式   0070001 rpc调用,0070002 injvm调用
     */
    private String cirType = ConfigConstants.CIR_TYPE_INJVM;

    /**
     * This field is ECM_TCECIR_CS.CIR_CLIENT_IPPORT 客户端IP端口
     */
    private String cirClientIpport;

    /**
     * This field is ECM_TCECIR_CS.CIR_CLIENT_STTM 客户端开始时间
     */
    private String cirClientSttm;

    /**
     * This field is ECM_TCECIR_CS.CIR_IN_MSG 输入报文
     */
    private String cirInMsg;

    /**
     * This field is ECM_TCECIR_CS.CIR_CLIENT_THDNUM 客户端线程号
     */
    private String cirClientThdnum;
    /**
     * This field is ECM_TCECIR_CS.CIR_SERVER_IPPORT 服务端IP端口
     */
    private String cirServerIpport;

    /**
     * This field is ECM_TCECIR_CS.CIR_SERVER_SER 服务端服务名
     */
    private String cirServerSer;

    /**
     * This field is ECM_TCECIR_CS.CIR_SERVER_MTD 服务端方法 名
     */
    private String cirServerMtd;
    
   

    public String getCirServerIpport() {
		return cirServerIpport;
	}

	public void setCirServerIpport(String cirServerIpport) {
		this.cirServerIpport = cirServerIpport== null ? "" : cirServerIpport.trim();
	}
	//为了编码方便的过载方法，可屏蔽IP和端口的拼接格式
    public void setCirServerIpport(String serverIp,String serverPort) {
        this.cirServerIpport = serverIp+link_mark+ serverPort;
    }
	public String getCirServerSer() {
		return cirServerSer;
	}

	public void setCirServerSer(String cirServerSer) {
		this.cirServerSer = cirServerSer== null ? "" : cirServerSer.trim();
	}

	public String getCirServerMtd() {
		return cirServerMtd;
	}

	public void setCirServerMtd(String cirServerMtd) {
		this.cirServerMtd = cirServerMtd== null ? "" : cirServerMtd.trim();
	}

	/**
     * @return the value of  ECM_TCECIR_CS.CIR_ID 
     */
    public String getCirId() {
        return cirId;
    }

    /**
     * @param cirId the value for ECM_TCECIR_CS.CIR_ID 
     */
    public void setCirId(String cirId) {
        this.cirId = cirId;
    }

    /**
     * @return the value of  ECM_TCECIR_CS.TRACE_ID 
     */
    public String getTraceId() {
        return traceId;
    }

    /**
     * @param traceId the value for ECM_TCECIR_CS.TRACE_ID 
     */
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    /**
     * @return the value of  ECM_TCECIR_CS.CIR_PARENT_ID 
     */
    public String getCirParentId() {
        return cirParentId;
    }

    /**
     * @param cirParentId the value for ECM_TCECIR_CS.CIR_PARENT_ID 
     */
    public void setCirParentId(String cirParentId) {
        this.cirParentId = cirParentId== null ? "" : cirParentId.trim();
    }

    /**
     * @return the value of  ECM_TCECIR_CS.CIR_TYPE 
     */
    public String getCirType() {
        return cirType;
    }

    /**
     * @param cirType the value for ECM_TCECIR_CS.CIR_TYPE 
     */
    public void setCirType(String cirType) {
        this.cirType = cirType == null ? "" : cirType.trim();
    }

    /**
     * @return the value of  ECM_TCECIR_CS.CIR_CLIENT_IPPORT 
     */
    public String getCirClientIpport() {
        return cirClientIpport;
    }

    /**
     * @param cirClientIpport the value for ECM_TCECIR_CS.CIR_CLIENT_IPPORT 
     */
    public void setCirClientIpport(String cirClientIpport) {
        this.cirClientIpport = cirClientIpport == null ? "" : cirClientIpport.trim();
    }
    //为了编码方便的过载方法，可屏蔽IP和端口的拼接格式
    public void setCirClientIpport(String clientIp,String clientPort) {
        this.cirClientIpport = clientIp+link_mark+ clientPort;
    }

    /**
     * @return the value of  ECM_TCECIR_CS.CIR_CLIENT_STTM 
     */
    public String getCirClientSttm() {
        return cirClientSttm;
    }

    /**
     * @param cirClientSttm the value for ECM_TCECIR_CS.CIR_CLIENT_STTM 
     */
    public void setCirClientSttm(String cirClientSttm) {
        this.cirClientSttm = cirClientSttm == null ? "" : cirClientSttm.trim();
    }

    /**
     * @return the value of  ECM_TCECIR_CS.CIR_IN_MSG 
     */
    public String getCirInMsg() {
    	 return cirInMsg==null?"":cirInMsg;
    }

    /**
     * @param cirInMsg the value for ECM_TCECIR_CS.CIR_IN_MSG 
     */
    public void setCirInMsg(String cirInMsg) {
        this.cirInMsg = cirInMsg == null ? "" : cirInMsg.trim();
    }

    /**
     * @return the value of  ECM_TCECIR_CS.CIR_CLIENT_THDNUM 
     */
    public String getCirClientThdnum() {
        return cirClientThdnum;
    }

    /**
     * @param cirClientThdnum the value for ECM_TCECIR_CS.CIR_CLIENT_THDNUM 
     */
    public void setCirClientThdnum(String cirClientThdnum) {
        this.cirClientThdnum = cirClientThdnum == null ? "" : cirClientThdnum.trim();
    }

	@Override
	public String toString() {
		return "EcmTcecirCs [link_mark=" + link_mark + ", cirId=" + cirId
				+ ", traceId=" + traceId + ", cirParentId=" + cirParentId
				+ ", cirType=" + cirType + ", cirClientIpport="
				+ cirClientIpport + ", cirClientSttm=" + cirClientSttm
				+ ", cirInMsg=" + cirInMsg + ", cirClientThdnum="
				+ cirClientThdnum + ", cirServerIpport=" + cirServerIpport
				+ ", cirServerSer=" + cirServerSer + ", cirServerMtd="
				+ cirServerMtd + "]";
	}
    
    
}