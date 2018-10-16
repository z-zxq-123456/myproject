package com.dcits.ensemble.om.oms.module.middleware;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by oms on 2016/02/16 10:54:34.
 */
public class EcmMidwareoprInfo extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_MIDWAREOPR_INFO.MIDWARE_OPER_ID 
     */
    @TablePk(index = 1)
    private Integer midwareOperId;

    /**
     * This field is ECM_MIDWAREOPR_INFO.MIDWARE_INTANT_ID 
     */
    private Integer midwareIntantId;

    /**
     * This field is ECM_MIDWAREOPR_INFO.MIDWARE_TYPE 
     */
    private String midwareType;

    /**
     * This field is ECM_MIDWAREOPR_INFO.MIDWARE_OPER_TYPE 
     */
    private String midwareOperType;

    /**
     * This field is ECM_MIDWAREOPR_INFO.MIDWARE_OPER_TIME 
     */
    private String midwareOperTime;

    /**
     * This field is ECM_MIDWAREOPR_INFO.MIDWARE_OPER_USERID 
     */
    private String midwareOperUserid;
    
    private String midwareOperTypeName;
    
    private String midwareName;
    
    private String midwarePath;
    
    private String instantName;
    
    private String instantType;
    
    private String midwareVerNo;
    
    private String userName;
    
    private String serIp;
    
    private String startTime;
    
    private String endTime;
    
    private String userId;
    
    private String midwareTypeName;
    
    private String instantTypeName;

    /**
     * @return the value of  ECM_MIDWAREOPR_INFO.MIDWARE_OPER_ID 
     */
    public Integer getMidwareOperId() {
        return midwareOperId;
    }

    /**
     * @param midwareOperId the value for ECM_MIDWAREOPR_INFO.MIDWARE_OPER_ID 
     */
    public void setMidwareOperId(Integer midwareOperId) {
        this.midwareOperId = midwareOperId;
    }

    /**
     * @return the value of  ECM_MIDWAREOPR_INFO.MIDWARE_INTANT_ID 
     */
    public Integer getMidwareIntantId() {
        return midwareIntantId;
    }

    /**
     * @param midwareIntantId the value for ECM_MIDWAREOPR_INFO.MIDWARE_INTANT_ID 
     */
    public void setMidwareIntantId(Integer midwareIntantId) {
        this.midwareIntantId = midwareIntantId;
    }

    /**
     * @return the value of  ECM_MIDWAREOPR_INFO.MIDWARE_TYPE 
     */
    public String getMidwareType() {
        return midwareType;
    }

    /**
     * @param midwareType the value for ECM_MIDWAREOPR_INFO.MIDWARE_TYPE 
     */
    public void setMidwareType(String midwareType) {
        this.midwareType = midwareType == null ? null : midwareType.trim();
    }

    /**
     * @return the value of  ECM_MIDWAREOPR_INFO.MIDWARE_OPER_TYPE 
     */
    public String getMidwareOperType() {
        return midwareOperType;
    }

    /**
     * @param midwareOperType the value for ECM_MIDWAREOPR_INFO.MIDWARE_OPER_TYPE 
     */
    public void setMidwareOperType(String midwareOperType) {
        this.midwareOperType = midwareOperType == null ? null : midwareOperType.trim();
    }

    /**
     * @return the value of  ECM_MIDWAREOPR_INFO.MIDWARE_OPER_TIME 
     */
    public String getMidwareOperTime() {
        return midwareOperTime;
    }

    /**
     * @param midwareOperTime the value for ECM_MIDWAREOPR_INFO.MIDWARE_OPER_TIME 
     */
    public void setMidwareOperTime(String midwareOperTime) {
        this.midwareOperTime = midwareOperTime == null ? null : midwareOperTime.trim();
    }

    /**
     * @return the value of  ECM_MIDWAREOPR_INFO.MIDWARE_OPER_USERID 
     */
    public String getMidwareOperUserid() {
        return midwareOperUserid;
    }

    /**
     * @param midwareOperUserid the value for ECM_MIDWAREOPR_INFO.MIDWARE_OPER_USERID 
     */
    public void setMidwareOperUserid(String midwareOperUserid) {
        this.midwareOperUserid = midwareOperUserid;
    }

	public String getMidwareOperTypeName() {
		return midwareOperTypeName;
	}

	public void setMidwareOperTypeName(String midwareOperTypeName) {
		this.midwareOperTypeName = midwareOperTypeName;
	}

	public String getMidwareName() {
		return midwareName;
	}

	public void setMidwareName(String midwareName) {
		this.midwareName = midwareName;
	}

	public String getMidwarePath() {
		return midwarePath;
	}

	public void setMidwarePath(String midwarePath) {
		this.midwarePath = midwarePath;
	}

	public String getInstantName() {
		return instantName;
	}

	public void setInstantName(String instantName) {
		this.instantName = instantName;
	}

	public String getInstantType() {
		return instantType;
	}

	public void setInstantType(String instantType) {
		this.instantType = instantType;
	}

	public String getMidwareVerNo() {
		return midwareVerNo;
	}

	public void setMidwareVerNo(String midwareVerNo) {
		this.midwareVerNo = midwareVerNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSerIp() {
		return serIp;
	}

	public void setSerIp(String serIp) {
		this.serIp = serIp;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMidwareTypeName() {
		return midwareTypeName;
	}

	public void setMidwareTypeName(String midwareTypeName) {
		this.midwareTypeName = midwareTypeName;
	}

	public String getInstantTypeName() {
		return instantTypeName;
	}

	public void setInstantTypeName(String instantTypeName) {
		this.instantTypeName = instantTypeName;
	}
    
    
}