package com.dcits.ensemble.om.oms.module.middleware;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by oms on 2016/02/16 10:54:34.
 */
public class EcmMidwareZkint extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_MIDWARE_ZKINT.ZKINT_ID 
     */
    @TablePk(index = 1)
    private Integer zkintId;

    /**
     * This field is ECM_MIDWARE_ZKINT.MIDWARE_ID 
     */
    private Integer midwareId;
    /**
     * This field is ECM_MIDWARE_DBINT.MIDWARE_NAME 
     */
    private String midwareName;
    /**
     * This field is ECM_MIDWARE_ZKINT.SER_ID 
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
     * This field is ECM_MIDWARE_ZKINT.ZKINT_NAME 
     */
    private String zkintName;

    /**
     * This field is ECM_MIDWARE_ZKINT.ZKINT_DESC 
     */
    private String zkintDesc;

    /**
     * This field is ECM_MIDWARE_ZKINT.ZKINT_STATUS 
     */
    private String zkintStatus;
    private String zkintStatusName;
    /**
     * This field is ECM_MIDWARE_ZKINT.ZKINT_CLIENT_PORT 
     */
    private Integer zkintClientPort;
    

    /**
     * This field is ECM_MIDWARE_ZKINT.ZKINT_COMM_PORT 
     */
    private Integer zkintCommPort;

    /**
     * This field is ECM_MIDWARE_ZKINT.ZKINT_ELECT_PORT 
     */
    private Integer zkintElectPort;

    /**
     * This field is ECM_MIDWARE_ZKINT.ZKINT_NODE_NUM 
     */
    private Integer zkintNodeNum;
    /**
     * This field is ECM_MIDWARE_VER.MIDWARE_VER_NO 
     */
    private String midwareVerNo;
    /**
     * This field is ECM_MIDWARE_INFO.MIDWARE_PATH 
     */
    private String midwarePath;
    /**
     * This field is ECM_SERVER_INFO.SER_USER 
     */
    private String serUser;
    /**
    * This field is ECM_SERVER_INFO.SER_OS 
    */
      private String serOs;
      /**
       * This field is ECM_MIDWARE_INFO.MIDWARE_VER_ID 
       */
      private Integer midwareVerId;
      /**
       * This field is ECM_SERVER_INFO.SER_PWD 
       */
     private String serPwd; 
     /**
      * This field is ECM_MIDWARE_VER.MIDWARE_VER_PATH 
      */
     private String midwareVerPath;
     private String healthMessage;
     private String currentZkIntantStatus;//当前应用实例状态

    /**
     * @return the value of  ECM_MIDWARE_ZKINT.ZKINT_ID 
     */
    public Integer getZkintId() {
        return zkintId;
    }

    /**
     * @param zkintId the value for ECM_MIDWARE_ZKINT.ZKINT_ID 
     */
    public void setZkintId(Integer zkintId) {
        this.zkintId = zkintId;
    }

    /**
     * @return the value of  ECM_MIDWARE_ZKINT.MIDWARE_ID 
     */
    public Integer getMidwareId() {
        return midwareId;
    }

    /**
     * @param midwareId the value for ECM_MIDWARE_ZKINT.MIDWARE_ID 
     */
    public void setMidwareId(Integer midwareId) {
        this.midwareId = midwareId;
    }

    /**
     * @return the value of  ECM_MIDWARE_ZKINT.SER_ID 
     */
    public Integer getSerId() {
        return serId;
    }

    /**
     * @param serId the value for ECM_MIDWARE_ZKINT.SER_ID 
     */
    public void setSerId(Integer serId) {
        this.serId = serId;
    }

    /**
     * @return the value of  ECM_MIDWARE_ZKINT.ZKINT_NAME 
     */
    public String getZkintName() {
        return zkintName;
    }
    /**
     * @return the value of  ECM_MIDWARE_INFO.MIDWARE_NAME 
     */
    public String getMidwareName() {
		return midwareName;
	}
    /**
     * @param midwareName the value for  ECM_MIDWARE_INFO.MIDWARE_NAME 
     */
	public void setMidwareName(String midwareName) {
		this.midwareName = midwareName;
	}
	/**
     * @return the value of  ECM_MIDWARE_ZKINT.ZKINT_NAME 
     */
	public String getSerName() {
		return serName;
	}
	/**
     * @param midwareName the value for  ECM_MIDWARE_INFO.MIDWARE_NAME 
     */
	public void setSerName(String serName) {
		this.serName = serName;
	}
	/**
     * @return the value of  ECM_SER_INFO.SER_IP 
     */
	public String getSerIp() {
		return serIp;
	}
	/**
     * @param setSerIp the value for  ECM_SER_INFO.SER_IP 
     */
	public void setSerIp(String serIp) {
		this.serIp = serIp;
	}

	/**
     * @param zkintName the value for ECM_MIDWARE_ZKINT.ZKINT_NAME 
     */
    public void setZkintName(String zkintName) {
        this.zkintName = zkintName == null ? null : zkintName.trim();
    }

    /**
     * @return the value of  ECM_MIDWARE_ZKINT.ZKINT_DESC 
     */
    public String getZkintDesc() {
        return zkintDesc;
    }

    /**
     * @param zkintDesc the value for ECM_MIDWARE_ZKINT.ZKINT_DESC 
     */
    public void setZkintDesc(String zkintDesc) {
        this.zkintDesc = zkintDesc == null ? null : zkintDesc.trim();
    }

    /**
     * @return the value of  ECM_MIDWARE_ZKINT.ZKINT_STATUS 
     */
    public String getZkintStatus() {
        return zkintStatus;
    }

    /**
     * @param zkintStatus the value for ECM_MIDWARE_ZKINT.ZKINT_STATUS 
     */
    public void setZkintStatus(String zkintStatus) {
        this.zkintStatus = zkintStatus == null ? null : zkintStatus.trim();
    }
    /**
     * @return the value of  ECM_MIDWARE_ZKINT.ZKINT_STATUS 
     */
    public String getZkintStatusName() {
        return zkintStatusName;
    }

    /**
     * @param zkintStatus the value for ECM_MIDWARE_ZKINT.ZKINT_STATUS 
     */
    public void setZkintStatusName(String zkintStatusName) {
        this.zkintStatusName = zkintStatusName == null ? null : zkintStatusName.trim();
    }
    public Integer getZkintClientPort() {
		return zkintClientPort;
	}

	public void setZkintClientPort(Integer zkintClientPort) {
		this.zkintClientPort = zkintClientPort;
	}

	/**
     * @return the value of  ECM_MIDWARE_ZKINT.ZKINT_COMM_PORT 
     */
    public Integer getZkintCommPort() {
        return zkintCommPort;
    }

    /**
     * @param zkintCommPort the value for ECM_MIDWARE_ZKINT.ZKINT_COMM_PORT 
     */
    public void setZkintCommPort(Integer zkintCommPort) {
        this.zkintCommPort = zkintCommPort;
    }

    /**
     * @return the value of  ECM_MIDWARE_ZKINT.ZKINT_ELECT_PORT 
     */
    public Integer getZkintElectPort() {
        return zkintElectPort;
    }

    /**
     * @param zkintElectPort the value for ECM_MIDWARE_ZKINT.ZKINT_ELECT_PORT 
     */
    public void setZkintElectPort(Integer zkintElectPort) {
        this.zkintElectPort = zkintElectPort;
    }

    /**
     * @return the value of  ECM_MIDWARE_ZKINT.ZKINT_NODE_NUM 
     */
    public Integer getZkintNodeNum() {
        return zkintNodeNum;
    }

    /**
     * @param zkintNodeNum the value for ECM_MIDWARE_ZKINT.ZKINT_NODE_NUM 
     */
    public void setZkintNodeNum(Integer zkintNodeNum) {
        this.zkintNodeNum = zkintNodeNum;
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
     * @return the value of  ECM_MIDWARE_INFO.MIDWARE_PATH 
     */
    public String getMidwarePath() {
        return midwarePath;
    }

    /**
     * @param midwarePath the value for ECM_MIDWARE_INFO.MIDWARE_PATH 
     */
    public void setMidwarePath(String midwarePath) {
        this.midwarePath = midwarePath == null ? null : midwarePath.trim();
    }
    /**
     * @return the value of  ECM_SERVER_INFO.SER_USER 
     */
    public String getSerUser() {
        return serUser;
    }

    /**
     * @param serUser the value for ECM_SERVER_INFO.SER_USER 
     */
    public void setSerUser(String serUser) {
        this.serUser = serUser == null ? null : serUser.trim();
    }
    public String getSerOs() {
		return serOs;
	}

	public void setSerOs(String serOs) {
		this.serOs = serOs;
	}
	/**
     * @return the value of  ECM_MIDWARE_INFO.MIDWARE_VER_ID 
     */
    public Integer getMidwareVerId() {
        return midwareVerId;
    }

    /**
     * @param midwareVerId the value for ECM_MIDWARE_INFO.MIDWARE_VER_ID 
     */
    public void setMidwareVerId(Integer midwareVerId) {
        this.midwareVerId = midwareVerId;
    }
    /**
     * @return the value of  ECM_SERVER_INFO.SER_PWD 
     */
    public String getSerPwd() {
        return serPwd;
    }

    /**
     * @param serPwd the value for ECM_SERVER_INFO.SER_PWD 
     */
    public void setSerPwd(String serPwd) {
        this.serPwd = serPwd == null ? null : serPwd.trim();	
    }
    /**
     * @return the value of  ECM_MIDWARE_VER.MIDWARE_VER_PATH 
     */
    public String getMidwareVerPath() {
        return midwareVerPath;
    }

    /**
     * @param midwareVerPath the value for ECM_MIDWARE_VER.MIDWARE_VER_PATH 
     */
    public void setMidwareVerPath(String midwareVerPath) {
        this.midwareVerPath = midwareVerPath == null ? null : midwareVerPath.trim();
    }
    public String getHealthMessage() {
		return healthMessage;
	}

	public void setHealthMessage(String healthMessage) {
		this.healthMessage = healthMessage;
	}
	public String getCurrentZkIntantStatus() {
		return currentZkIntantStatus;
	}

	public void setCurrentZkIntantStatus(String currentZkIntantStatus) {
		this.currentZkIntantStatus = currentZkIntantStatus;
	}
}