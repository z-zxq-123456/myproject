package com.dcits.ensemble.om.oms.module.middleware;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by oms on 2016/02/16 10:54:34.
 */
public class EcmMidwareRedisint extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_MIDWARE_REDISINT.REDISINT_ID 
     */
    @TablePk(index = 1)
    private Integer redisintId;

    /**
     * This field is ECM_MIDWARE_REDISINT.MIDWARE_ID 
     */
    private Integer midwareId;

    /**
     * This field is ECM_MIDWARE_REDISINT.SER_ID 
     */
    private Integer serId;
    
    private String serIp;
    
    private String midwareName;
    
    private String serOs;
    
    private String midwarePath;
    
    private String serUser;
    
    private String midwareVerPath;
    
    private Integer midwareVerId;
    
    private String serPwd;

    private String healthMessage;
    
    private String currentRedisintStatus;
    
    private String hostAndPort;
    
    /**
     * This field is ECM_MIDWARE_REDISINT.REDISINT_NAME 
     */
    private String redisintName;

    /**
     * This field is ECM_MIDWARE_REDISINT.REDISINT_DESC 
     */
    private String redisintDesc;

    /**
     * This field is ECM_MIDWARE_REDISINT.REDISINT_STATUS 
     */
    private String redisintStatus;
    
    private String redisintStatusName;

    /**
     * This field is ECM_MIDWARE_REDISINT.REDISINT_TYPE 
     */
    private String redisintType;
    
    private String redisintTypeName;
    
    private String midwareVerNo;

    /**
     * This field is ECM_MIDWARE_REDISINT.REDISINT_PORT 
     */
    private Integer redisintPort;

    /**
     * This field is ECM_MIDWARE_REDISINT.REDISINT_NODE_NUM 
     */
    private Integer redisintNodeNum;

    /**
     * This field is ECM_MIDWARE_REDISINT.REDISINT_MEMORY 
     */
    private Integer redisintMemory;

    private String redisintMemoryUnit;//在内存后面拼接单位为了更好地显示，

    /**
     * @return the value of  ECM_MIDWARE_REDISINT.REDISINT_ID 
     */
    public Integer getRedisintId() {
        return redisintId;
    }

    /**
     * @param redisintId the value for ECM_MIDWARE_REDISINT.REDISINT_ID 
     */
    public void setRedisintId(Integer redisintId) {
        this.redisintId = redisintId;
    }

    /**
     * @return the value of  ECM_MIDWARE_REDISINT.MIDWARE_ID 
     */
    public Integer getMidwareId() {
        return midwareId;
    }

    /**
     * @param midwareId the value for ECM_MIDWARE_REDISINT.MIDWARE_ID 
     */
    public void setMidwareId(Integer midwareId) {
        this.midwareId = midwareId;
    }

    /**
     * @return the value of  ECM_MIDWARE_REDISINT.SER_ID 
     */
    public Integer getSerId() {
        return serId;
    }

    /**
     * @param serId the value for ECM_MIDWARE_REDISINT.SER_ID 
     */
    public void setSerId(Integer serId) {
        this.serId = serId;
    }

    /**
     * @return the value of  ECM_MIDWARE_REDISINT.REDISINT_NAME 
     */
    public String getRedisintName() {
        return redisintName;
    }

    /**
     * @param redisintName the value for ECM_MIDWARE_REDISINT.REDISINT_NAME 
     */
    public void setRedisintName(String redisintName) {
        this.redisintName = redisintName == null ? null : redisintName.trim();
    }

    /**
     * @return the value of  ECM_MIDWARE_REDISINT.REDISINT_DESC 
     */
    public String getRedisintDesc() {
        return redisintDesc;
    }

    /**
     * @param redisintDesc the value for ECM_MIDWARE_REDISINT.REDISINT_DESC 
     */
    public void setRedisintDesc(String redisintDesc) {
        this.redisintDesc = redisintDesc == null ? null : redisintDesc.trim();
    }

    /**
     * @return the value of  ECM_MIDWARE_REDISINT.REDISINT_STATUS 
     */
    public String getRedisintStatus() {
        return redisintStatus;
    }

    /**
     * @param redisintStatus the value for ECM_MIDWARE_REDISINT.REDISINT_STATUS 
     */
    public void setRedisintStatus(String redisintStatus) {
        this.redisintStatus = redisintStatus == null ? null : redisintStatus.trim();
    }

    /**
     * @return the value of  ECM_MIDWARE_REDISINT.REDISINT_TYPE 
     */
    public String getRedisintType() {
        return redisintType;
    }

    /**
     * @param redisintType the value for ECM_MIDWARE_REDISINT.REDISINT_TYPE 
     */
    public void setRedisintType(String redisintType) {
        this.redisintType = redisintType == null ? null : redisintType.trim();
    }

    /**
     * @return the value of  ECM_MIDWARE_REDISINT.REDISINT_PORT 
     */
    public Integer getRedisintPort() {
        return redisintPort;
    }

    /**
     * @param redisintPort the value for ECM_MIDWARE_REDISINT.REDISINT_PORT 
     */
    public void setRedisintPort(Integer redisintPort) {
        this.redisintPort = redisintPort;
    }

    /**
     * @return the value of  ECM_MIDWARE_REDISINT.REDISINT_NODE_NUM 
     */
    public Integer getRedisintNodeNum() {
        return redisintNodeNum;
    }

    /**
     * @param redisintNodeNum the value for ECM_MIDWARE_REDISINT.REDISINT_NODE_NUM 
     */
    public void setRedisintNodeNum(Integer redisintNodeNum) {
        this.redisintNodeNum = redisintNodeNum;
    }

    /**
     * @return the value of  ECM_MIDWARE_REDISINT.REDISINT_MEMORY 
     */
    public Integer getRedisintMemory() {
        return redisintMemory;
    }

    /**
     * @param redisintMemory the value for ECM_MIDWARE_REDISINT.REDISINT_MEMORY 
     */
    public void setRedisintMemory(Integer redisintMemory) {
        this.redisintMemory = redisintMemory;
    }

	public String getRedisintStatusName() {
		return redisintStatusName;
	}

	public void setRedisintStatusName(String redisintStatusName) {
		this.redisintStatusName = redisintStatusName;
	}

	public String getSerIp() {
		return serIp;
	}

	public void setSerIp(String serIp) {
		this.serIp = serIp;
	}

	public String getMidwareName() {
		return midwareName;
	}

	public void setMidwareName(String midwareName) {
		this.midwareName = midwareName;
	}

	/**
	 * @return the redisintTypeName
	 */
	public String getRedisintTypeName() {
		return redisintTypeName;
	}

	/**
	 * @param redisintTypeName the redisintTypeName to set
	 */
	public void setRedisintTypeName(String redisintTypeName) {
		this.redisintTypeName = redisintTypeName;
	}

	/**
	 * @return the serOs
	 */
	public String getSerOs() {
		return serOs;
	}

	/**
	 * @param serOs the serOs to set
	 */
	public void setSerOs(String serOs) {
		this.serOs = serOs;
	}

	/**
	 * @return the midwarePath
	 */
	public String getMidwarePath() {
		return midwarePath;
	}

	/**
	 * @param midwarePath the midwarePath to set
	 */
	public void setMidwarePath(String midwarePath) {
		this.midwarePath = midwarePath;
	}

	/**
	 * @return the serUser
	 */
	public String getSerUser() {
		return serUser;
	}

	/**
	 * @param serUser the serUser to set
	 */
	public void setSerUser(String serUser) {
		this.serUser = serUser;
	}

	/**
	 * @return the midwareVerPath
	 */
	public String getMidwareVerPath() {
		return midwareVerPath;
	}

	/**
	 * @param midwareVerPath the midwareVerPath to set
	 */
	public void setMidwareVerPath(String midwareVerPath) {
		this.midwareVerPath = midwareVerPath;
	}

	/**
	 * @return the midwareVerId
	 */
	public Integer getMidwareVerId() {
		return midwareVerId;
	}

	/**
	 * @param midwareVerId the midwareVerId to set
	 */
	public void setMidwareVerId(Integer midwareVerId) {
		this.midwareVerId = midwareVerId;
	}

	/**
	 * @return the serPwd
	 */
	public String getSerPwd() {
		return serPwd;
	}

	/**
	 * @param serPwd the serPwd to set
	 */
	public void setSerPwd(String serPwd) {
		this.serPwd = serPwd;
	}

	/**
	 * @return the midwareVerNo
	 */
	public String getMidwareVerNo() {
		return midwareVerNo;
	}

	/**
	 * @param midwareVerNo the midwareVerNo to set
	 */
	public void setMidwareVerNo(String midwareVerNo) {
		this.midwareVerNo = midwareVerNo;
	}

	/**
	 * @return the healthMessage
	 */
	public String getHealthMessage() {
		return healthMessage;
	}

	/**
	 * @param healthMessage the healthMessage to set
	 */
	public void setHealthMessage(String healthMessage) {
		this.healthMessage = healthMessage;
	}

	/**
	 * @return the currentRedisintStatus
	 */
	public String getCurrentRedisintStatus() {
		return currentRedisintStatus;
	}

	/**
	 * @param currentRedisintStatus the currentRedisintStatus to set
	 */
	public void setCurrentRedisintStatus(String currentRedisintStatus) {
		this.currentRedisintStatus = currentRedisintStatus;
	}

	/**
	 * @return the hostAndPort
	 */
	public String getHostAndPort() {
		return hostAndPort;
	}

	/**
	 * @param hostAndPort the hostAndPort to set
	 */
	public void setHostAndPort(String hostAndPort) {
		this.hostAndPort = hostAndPort;
	}

    public String getRedisintMemoryUnit() {
        return redisintMemoryUnit;
    }

    public void setRedisintMemoryUnit(String redisintMemoryUnit) {
        this.redisintMemoryUnit = redisintMemoryUnit;
    }
}