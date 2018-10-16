package com.dcits.ensemble.om.oms.module.server;



import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;


/**
 * Created by tangxl on 2014/07/20 14:40:44.
 */
public class EcmServerInfo extends AbstractBean {
	
	

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    @Override
	public String toString() {
		return "EcmServerInfo [serId=" + serId + ", serName=" + serName
				+ ", serIp=" + serIp + ", serUser=" + serUser + ", serPwd="
				+ serPwd + ", serOs=" + serOs + "]";
	}

	/**
     * This field is ECM_SERVER_INFO.SER_ID 
     */
    @TablePk(index = 1)
    private Integer serId;

    /**
     * This field is ECM_SERVER_INFO.SER_NAME 
     */
    private String serName;
    /**
     * This field is ECM_SERVER_INFO.SER_IP 
     */
    private String serIp;
    /**
     * This field is ECM_SERVER_INFO.SER_USER 
     */
    private String serUser;
    /**
     * This field is ECM_SERVER_INFO.SER_PWD 
     */
    private String serPwd;    
    /**
     * This field is ECM_SERVER_INFO.SER_OS 
     */
    private String serOs;
    
    private String serOsName;
    

    
    public String getSerOs() {
		return serOs;
	}

	public void setSerOs(String serOs) {
		this.serOs = serOs;
	}

	/**
     * @return the value of  ECM_SERVER_INFO.SER_ID 
     */
    public Integer getSerId() {
        return serId;
    }

    /**
     * @param serId the value for ECM_SERVER_INFO.SER_ID 
     */
    public void setSerId(Integer serId) {
        this.serId = serId;
    }
    /**
     * @return the value of  ECM_SERVER_INFO.SER_NAME 
     */
    public String getSerName() {
        return serName;
    }

    /**
     * @param serName the value for ECM_SERVER_INFO.SER_NAME 
     */
    public void setSerName(String serName) {
        this.serName = serName == null ? null : serName.trim();
    }
    /**
     * @return the value of  ECM_SERVER_INFO.SER_IP 
     */
    public String getSerIp() {
        return serIp;
    }

    /**
     * @param serIp the value for ECM_SERVER_INFO.SER_IP 
     */
    public void setSerIp(String serIp) {
        this.serIp = serIp == null ? null : serIp.trim();
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
	 * @return the serOsName
	 */
	public String getSerOsName() {
		return serOsName;
	}

	/**
	 * @param serOsName the serOsName to set
	 */
	public void setSerOsName(String serOsName) {
		this.serOsName = serOsName;
	}
}