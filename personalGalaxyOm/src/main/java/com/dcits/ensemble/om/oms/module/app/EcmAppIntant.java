package com.dcits.ensemble.om.oms.module.app;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by tangxl on 2015/08/18 09:58:01.
 */
public class EcmAppIntant extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;


//	@Override
//	public String toString() {
//		return "EcmAppIntant [appIntantId=" + appIntantId + ", appId=" + appId
//				+ ", serId=" + serId + ", appIntantName=" + appIntantName
//				+ ", appIntantDesc=" + appIntantDesc + ", appIntantStatus="
//				+ appIntantStatus + ", appIntantVer=" + appIntantVer
//				+ ", appPath=" + appPath + ", appPort=" + appPort
//				+ ", appWork=" + appWork + ", appVerType=" + appVerType
//				+ ", serIp=" + serIp + ", serUser=" + serUser + ", serPwd="
//				+ serPwd + ", currentAppIntantStatus=" + currentAppIntantStatus
//				+ ", newAppIntantVer=" + newAppIntantVer
//				+ ", appIntantStatusName=" + appIntantStatusName + ", serOs="
//				+ serOs + ", appVerPath=" + appVerPath + ", appVerId="
//				+ appVerId +", appNumber=" + appNumber + "]";
//	}

    /**
     * This field is ECM_APP_INTANT.APP_INTANT_ID
     */
    @TablePk(index = 1)
    private Integer appIntantId;

    /**
     * This field is ECM_APP_INTANT.APP_ID
     */
    private Integer appId;
    /**
     * This field is ECM_APP_INTANT.SER_ID
     */
    private Integer serId;
    /**
     * This field is ECM_APP_INTANT.APP_INTANT_NAME
     */
    private String appIntantName;
    /**
     * This field is ECM_APP_INTANT.APP_NUMBER
     */
    private Integer appNumber;
    /**
     * This field is ECM_APP_INTANT.APP_INTANT_DESC
     */
    private String appIntantDesc;
    /**
     * This field is ECM_APP_INTANT.APP_INTANT_STATUS
     */
    private String appIntantStatus;
    /**
     * This field is ECM_APP_INTANT.APP_INTANT_VER
     */
    private Integer appIntantVer;

    private String appPath;

    private String appPort;

    private String appWork;

    private String appVerType;

    private String serIp;

    private String serUser;

    private String serPwd;

    private String currentAppIntantStatus;//当前应用实例状态

    private Integer newAppIntantVer;//待部署版本号

    private String appIntantStatusName;

    private String serOs;

    private String appName;

    private String appVerPath;

    private Integer appVerId;

    private String serName;

    private String healthMessage;

    private String appSimpenNm;

    private String isRemainConfig;

    private String appType;

    private String intantIdString;

    public String getIsRemainConfig() {
        return isRemainConfig;
    }

    public void setIsRemainConfig(String isRemainConfig) {
        this.isRemainConfig = isRemainConfig;
    }

    public String getAppSimpenNm() {
        return appSimpenNm;
    }

    public void setAppSimpenNm(String appSimpenNm) {
        this.appSimpenNm = appSimpenNm;
    }

    public String getHealthMessage() {
        return healthMessage;
    }

    public void setHealthMessage(String healthMessage) {
        this.healthMessage = healthMessage;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getSerOs() {
        return serOs;
    }

    public void setSerOs(String serOs) {
        this.serOs = serOs;
    }

    public String getCurrentAppIntantStatus() {
        return currentAppIntantStatus;
    }

    public void setCurrentAppIntantStatus(String currentAppIntantStatus) {
        this.currentAppIntantStatus = currentAppIntantStatus;
    }


    public String getAppIntantStatusName() {
        return appIntantStatusName;
    }

    public void setAppIntantStatusName(String appIntantStatusName) {
        this.appIntantStatusName = appIntantStatusName;
    }

    public String getSerUser() {
        return serUser;
    }

    public void setSerUser(String serUser) {
        this.serUser = serUser;
    }

    public String getSerPwd() {
        return serPwd;
    }

    public void setSerPwd(String serPwd) {
        this.serPwd = serPwd;
    }

    public String getSerIp() {
        return serIp;
    }

    public void setSerIp(String serIp) {
        this.serIp = serIp;
    }

    /**
     * @return the value of  ECM_APP_INTANT.APP_INTANT_ID
     */
    public Integer getAppIntantId() {
        return appIntantId;
    }

    /**
     * @param appIntantId the value for ECM_APP_INTANT.APP_INTANT_ID
     */
    public void setAppIntantId(Integer appIntantId) {
        this.appIntantId = appIntantId;
    }

    /**
     * @return the value of  ECM_APP_INTANT.APP_ID
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * @param appId the value for ECM_APP_INTANT.APP_ID
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * @return the value of  ECM_APP_INTANT.SER_ID
     */
    public Integer getSerId() {
        return serId;
    }

    /**
     * @param serId the value for ECM_APP_INTANT.SER_ID
     */
    public void setSerId(Integer serId) {
        this.serId = serId;
    }

    /**
     * @return the value of  ECM_APP_INTANT.APP_INTANT_NAME
     */
    public String getAppIntantName() {
        return appIntantName;
    }

    /**
     * @param appIntantName the value for ECM_APP_INTANT.APP_INTANT_NAME
     */
    public void setAppIntantName(String appIntantName) {
        this.appIntantName = appIntantName == null ? null : appIntantName.trim();
    }

    /**
     * @return the value of  ECM_APP_INTANT.APP_INTANT_DESC
     */
    public String getAppIntantDesc() {
        return appIntantDesc;
    }

    /**
     * @param appIntantDesc the value for ECM_APP_INTANT.APP_INTANT_DESC
     */
    public void setAppIntantDesc(String appIntantDesc) {
        this.appIntantDesc = appIntantDesc == null ? null : appIntantDesc.trim();
    }

    /**
     * @return the value of  ECM_APP_INTANT.APP_INTANT_STATUS
     */
    public String getAppIntantStatus() {
        return appIntantStatus;
    }

    /**
     * @param appIntantStatus the value for ECM_APP_INTANT.APP_INTANT_STATUS
     */
    public void setAppIntantStatus(String appIntantStatus) {
        this.appIntantStatus = appIntantStatus == null ? null : appIntantStatus.trim();
    }

    /**
     * @return the value of  ECM_APP_INTANT.APP_INTANT_VER
     */
    public Integer getAppIntantVer() {
        return appIntantVer;
    }

    /**
     * @param appIntantVer the value for ECM_APP_INTANT.APP_INTANT_VER
     */
    public void setAppIntantVer(Integer appIntantVer) {
        this.appIntantVer = appIntantVer;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    public String getAppPort() {
        return appPort;
    }

    public void setAppPort(String appPort) {
        this.appPort = appPort;
    }

    public String getAppWork() {
        return appWork;
    }

    public void setAppWork(String appWork) {
        this.appWork = appWork;
    }

    public String getAppVerType() {
        return appVerType;
    }

    public void setAppVerType(String appVerType) {
        this.appVerType = appVerType;
    }

    public Integer getNewAppIntantVer() {
        return newAppIntantVer;
    }

    public void setNewAppIntantVer(Integer newAppIntantVer) {
        this.newAppIntantVer = newAppIntantVer;
    }

    public String getAppVerPath() {
        return appVerPath;
    }

    public void setAppVerPath(String appVerPath) {
        this.appVerPath = appVerPath;
    }

    public Integer getAppVerId() {
        return appVerId;
    }

    public void setAppVerId(Integer appVerId) {
        this.appVerId = appVerId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getSerName() {
        return serName;
    }

    public void setSerName(String serName) {
        this.serName = serName;
    }

    public Integer getAppNumber() {
        return appNumber;
    }

    public void setAppNumber(Integer appNumber) {
        this.appNumber = appNumber;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    /**
     * @return the intantIdString
     */
    public String getIntantIdString() {
        return intantIdString;
    }

    /**
     * @param intantIdString the intantIdString to set
     */
    public void setIntantIdString(String intantIdString) {
        this.intantIdString = intantIdString;
    }

    public boolean equals(Object obj) {
        // modify for sonar扫描
        // NullPointerException might be thrown as 'obj' is nullable here
        if (null == obj)
            return false;
        if (((EcmAppIntant) obj).appIntantId.equals(this.appIntantId)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.appIntantId.hashCode();
    }
}