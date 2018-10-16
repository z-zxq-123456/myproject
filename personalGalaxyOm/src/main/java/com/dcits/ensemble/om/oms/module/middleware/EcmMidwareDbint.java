package com.dcits.ensemble.om.oms.module.middleware;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by oms on 2016/02/16 10:54:34.
 */
public class EcmMidwareDbint extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_MIDWARE_DBINT.DBINT_ID 
     */
    @TablePk(index = 1)
    private Integer dbintId;

    /**
     * This field is ECM_MIDWARE_DBINT.MIDWARE_ID 
     */
    private Integer midwareId;
    /**
     * This field is ECM_MIDWARE_DBINT.MIDWARE_NAME 
     */
    private String midwareName;

    /**
     * This field is ECM_MIDWARE_DBINT.SER_ID 
     */
    private Integer serId;
    /**
     * This field is ECM_MIDWARE_DBINT.SER_NAME 
     */
    private String serName;
    /**
     * This field is ECM_MIDWARE_DBINT.SER_IP 
     */
    private String serIp;

    /**
     * This field is ECM_MIDWARE_DBINT.DBINT_NAME 
     */
    private String dbintName;

    /**
     * This field is ECM_MIDWARE_DBINT.DBINT_DESC 
     */
    private String dbintDesc;

    /**
     * This field is ECM_MIDWARE_DBINT.DBINT_STATUS 
     */
    private String dbintStatus;

    /**
     * This field is ECM_MIDWARE_DBINT.DB_TYPE 
     */
    private String dbType;
    
    private String dbTypeName;

    /**
     * This field is ECM_MIDWARE_DBINT.DBINT_NODE_NUM 
     */
    private String dbintNodeNum;

    /**
     * This field is ECM_MIDWARE_DBINT.DBINT_USER_NAME 
     */
    private String dbintUserName;

    /**
     * This field is ECM_MIDWARE_DBINT.DBINT_USER_PWD 
     */
    private String dbintUserPwd;

    /**
     * This field is ECM_MIDWARE_DBINT.DBINT_PORT 
     */
    private String dbintPort;
    

    /**
     * This field is ECM_MIDWARE_DBINT.DBINT_SERVICE_NAME
     */
    private String dbintServiceName;
    
    private String dbintCurrentStatus;
    
    private String dbintCurrentStatusName;

    private String healthMessage;    
    
    private String midwarePath;
   
    /**
     * @return the value of  ECM_MIDWARE_DBINT.DBINT_ID 
     */
    public Integer getDbintId() {
        return dbintId;
    }

    /**
     * @param dbintId the value for ECM_MIDWARE_DBINT.DBINT_ID 
     */
    public void setDbintId(Integer dbintId) {
        this.dbintId = dbintId;
    }

    /**
     * @return the value of  ECM_MIDWARE_DBINT.MIDWARE_ID 
     */
    public Integer getMidwareId() {
        return midwareId;
    }

    /**
     * @param midwareId the value for ECM_MIDWARE_DBINT.MIDWARE_ID 
     */
    public void setMidwareId(Integer midwareId) {
        this.midwareId = midwareId;
    }

    /**
     * @return the value of  ECM_MIDWARE_DBINT.SER_ID 
     */
    public Integer getSerId() {
        return serId;
    }

    /**
     * @param serId the value for ECM_MIDWARE_DBINT.SER_ID 
     */
    public void setSerId(Integer serId) {
        this.serId = serId;
    }

    /**
     * @return the value of  ECM_MIDWARE_DBINT.DBINT_NAME 
     */
    public String getDbintName() {
        return dbintName;
    }

    /**
     * @param dbintName the value for ECM_MIDWARE_DBINT.DBINT_NAME 
     */
    public void setDbintName(String dbintName) {
        this.dbintName = dbintName == null ? null : dbintName.trim();
    }

    /**
     * @return the value of  ECM_MIDWARE_DBINT.DBINT_DESC 
     */
    public String getDbintDesc() {
        return dbintDesc;
    }

    /**
     * @param dbintDesc the value for ECM_MIDWARE_DBINT.DBINT_DESC 
     */
    public void setDbintDesc(String dbintDesc) {
        this.dbintDesc = dbintDesc == null ? null : dbintDesc.trim();
    }

    /**
     * @return the value of  ECM_MIDWARE_DBINT.DBINT_STATUS 
     */
    public String getDbintStatus() {
        return dbintStatus;
    }
    /**
     * @return the value of  ECM_MIDWARE_DBINT.SER_NAME 
     */
    public String getSerName() {
		return serName;
	}
    /**
     * @param dbintDesc the value for ECM_MIDWARE_DBINT.SER_NAME  
     */
	public void setSerName(String serName) {
		this.serName = serName;
	}
	/**
     * @param setSerIp the value for  ECM_SER_INFO.SER_IP 
     */
	public String getSerIp() {
		return serIp;
	}

	public String getDbTypeName() {
		return dbTypeName;
	}

	public void setDbTypeName(String dbTypeName) {
		this.dbTypeName = dbTypeName;
	}
	/**
     * @return the value of  ECM_SER_INFO.SER_IP 
     */
	public void setSerIp(String serIp) {
		this.serIp = serIp;
	}
	/**
     * @param midwareName the value for  ECM_MIDWARE_INFO.MIDWARE_NAME 
     */
	public String getMidwareName() {
		return midwareName;
	}
	/**
     * @return the value of  ECM_MIDWARE_INFO.MIDWARE_NAME 
     */
	public void setMidwareName(String midwareName) {
		this.midwareName = midwareName;
	}

	/**
     * @param dbintStatus the value for ECM_MIDWARE_DBINT.DBINT_STATUS 
     */
    public void setDbintStatus(String dbintStatus) {
        this.dbintStatus = dbintStatus == null ? null : dbintStatus.trim();
    }

    /**
     * @return the value of  ECM_MIDWARE_DBINT.DB_TYPE 
     */
    public String getDbType() {
        return dbType;
    }

    /**
     * @param dbType the value for ECM_MIDWARE_DBINT.DB_TYPE 
     */
    public void setDbType(String dbType) {
        this.dbType = dbType == null ? null : dbType.trim();
    }

    /**
     * @return the value of  ECM_MIDWARE_DBINT.DBINT_NODE_NUM 
     */
    public String getDbintNodeNum() {
        return dbintNodeNum;
    }

    /**
     * @param dbintNodeNum the value for ECM_MIDWARE_DBINT.DBINT_NODE_NUM 
     */
    public void setDbintNodeNum(String dbintNodeNum) {
        this.dbintNodeNum = dbintNodeNum;
    }

    /**
     * @return the value of  ECM_MIDWARE_DBINT.DBINT_USER_NAME 
     */
    public String getDbintUserName() {
        return dbintUserName;
    }

    /**
     * @param dbintUserName the value for ECM_MIDWARE_DBINT.DBINT_USER_NAME 
     */
    public void setDbintUserName(String dbintUserName) {
        this.dbintUserName = dbintUserName == null ? null : dbintUserName.trim();
    }

    /**
     * @return the value of  ECM_MIDWARE_DBINT.DBINT_USER_PWD 
     */
    public String getDbintUserPwd() {
        return dbintUserPwd;
    }

    /**
     * @param dbintUserPwd the value for ECM_MIDWARE_DBINT.DBINT_USER_PWD 
     */
    public void setDbintUserPwd(String dbintUserPwd) {
        this.dbintUserPwd = dbintUserPwd == null ? null : dbintUserPwd.trim();
    }

	public String getDbintPort() {
		return dbintPort;
	}

	public void setDbintPort(String dbintPort) {
		this.dbintPort = dbintPort;
	}

	public String getDbintServiceName() {
		return dbintServiceName;
	}

	public void setDbintServiceName(String dbintServiceName) {
		this.dbintServiceName = dbintServiceName;
	}

	public String getDbintCurrentStatus() {
		return dbintCurrentStatus;
	}

	public void setDbintCurrentStatus(String dbintCurrentStatus) {
		this.dbintCurrentStatus = dbintCurrentStatus;
	}

	public String getDbintCurrentStatusName() {
		return dbintCurrentStatusName;
	}

	public void setDbintCurrentStatusName(String dbintCurrentStatusName) {
		this.dbintCurrentStatusName = dbintCurrentStatusName;
	}

	public String getHealthMessage() {
		return healthMessage;
	}

	public void setHealthMessage(String healthMessage) {
		this.healthMessage = healthMessage;
	}

	public String getMidwarePath() {
		return midwarePath;
	}

	public void setMidwarePath(String midwarePath) {
		this.midwarePath = midwarePath;
	}
	
	
}