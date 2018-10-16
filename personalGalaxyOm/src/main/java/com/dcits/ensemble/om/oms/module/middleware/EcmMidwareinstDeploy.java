package com.dcits.ensemble.om.oms.module.middleware;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by oms on 2016/02/16 10:54:34.
 */
public class EcmMidwareinstDeploy extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_MIDWAREINST_DEPLOY.MIDWARE_DEPLOY_ID 
     */
    @TablePk(index = 1)
    private Integer midwareDeployId;

    /**
     * This field is ECM_MIDWAREINST_DEPLOY.MIDWARE_INTANT_ID 
     */
    private Integer midwareIntantId;

    /**
     * This field is ECM_MIDWAREINST_DEPLOY.MIDWARE_TYPE 
     */
    private String midwareType;

    /**
     * This field is ECM_MIDWAREINST_DEPLOY.MIDWARE_VER_ID 
     */
    private Integer midwareVerId;

    /**
     * This field is ECM_MIDWAREINST_DEPLOY.MIDWARE_DEPLOY_DATE 
     */
    private String midwareDeployDate;

    /**
     * This field is ECM_MIDWAREINST_DEPLOY.MIDWARE_DEPLOY_USERID 
     */
    private String midwareDeployUserid;
    
    
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
     * @return the value of  ECM_MIDWAREINST_DEPLOY.MIDWARE_DEPLOY_ID 
     */
    public Integer getMidwareDeployId() {
        return midwareDeployId;
    }

    /**
     * @param midwareDeployId the value for ECM_MIDWAREINST_DEPLOY.MIDWARE_DEPLOY_ID 
     */
    public void setMidwareDeployId(Integer midwareDeployId) {
        this.midwareDeployId = midwareDeployId;
    }

    /**
     * @return the value of  ECM_MIDWAREINST_DEPLOY.MIDWARE_INTANT_ID 
     */
    public Integer getMidwareIntantId() {
        return midwareIntantId;
    }

    /**
     * @param midwareIntantId the value for ECM_MIDWAREINST_DEPLOY.MIDWARE_INTANT_ID 
     */
    public void setMidwareIntantId(Integer midwareIntantId) {
        this.midwareIntantId = midwareIntantId;
    }

    /**
     * @return the value of  ECM_MIDWAREINST_DEPLOY.MIDWARE_TYPE 
     */
    public String getMidwareType() {
        return midwareType;
    }

    /**
     * @param midwareType the value for ECM_MIDWAREINST_DEPLOY.MIDWARE_TYPE 
     */
    public void setMidwareType(String midwareType) {
        this.midwareType = midwareType == null ? null : midwareType.trim();
    }

    /**
     * @return the value of  ECM_MIDWAREINST_DEPLOY.MIDWARE_VER_ID 
     */
    public Integer getMidwareVerId() {
        return midwareVerId;
    }

    /**
     * @param midwareVerId the value for ECM_MIDWAREINST_DEPLOY.MIDWARE_VER_ID 
     */
    public void setMidwareVerId(Integer midwareVerId) {
        this.midwareVerId = midwareVerId;
    }

    /**
     * @return the value of  ECM_MIDWAREINST_DEPLOY.MIDWARE_DEPLOY_DATE 
     */
    public String getMidwareDeployDate() {
        return midwareDeployDate;
    }

    /**
     * @param midwareDeployDate the value for ECM_MIDWAREINST_DEPLOY.MIDWARE_DEPLOY_DATE 
     */
    public void setMidwareDeployDate(String midwareDeployDate) {
        this.midwareDeployDate = midwareDeployDate == null ? null : midwareDeployDate.trim();
    }

    /**
     * @return the value of  ECM_MIDWAREINST_DEPLOY.MIDWARE_DEPLOY_USERID 
     */
    public String getMidwareDeployUserid() {
        return midwareDeployUserid;
    }

    /**
     * @param midwareDeployUserid the value for ECM_MIDWAREINST_DEPLOY.MIDWARE_DEPLOY_USERID 
     */
    public void setMidwareDeployUserid(String midwareDeployUserid) {
        this.midwareDeployUserid = midwareDeployUserid;
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