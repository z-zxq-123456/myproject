package com.dcits.ensemble.om.oms.module.middleware;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by oms on 2016/12/12 10:54:34.
 */
public class EcmMidwareKafkaint extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;


    public Integer getKafkaintId() {
        return kafkaintId;
    }

    public void setKafkaintId(Integer kafkaintId) {
        this.kafkaintId = kafkaintId;
    }

    public Integer getMidwareId() {
        return midwareId;
    }

    public void setMidwareId(Integer midwareId) {
        this.midwareId = midwareId;
    }

    public Integer getSerId() {
        return serId;
    }

    public void setSerId(Integer serId) {
        this.serId = serId;
    }

    public String getKafkaintName() {
        return kafkaintName;
    }

    public void setKafkaintName(String kafkaintName) {
        this.kafkaintName = kafkaintName;
    }

    public String getKafkaintDesc() {
        return kafkaintDesc;
    }

    public void setKafkaintDesc(String kafkaintDesc) {
        this.kafkaintDesc = kafkaintDesc;
    }

    public String getKafkaintStatus() {
        return kafkaintStatus;
    }

    public void setKafkaintStatus(String kafkaintStatus) {
        this.kafkaintStatus = kafkaintStatus;
    }

    public String getKafkaintPort() {
        return kafkaintPort;
    }

    public void setKafkaintPort(String kafkaintPort) {
        this.kafkaintPort = kafkaintPort;
    }

    /**
     * This field is ECM_MIDWARE_REDISINT.REDISINT_ID 
     */
    @TablePk(index = 1)
    private Integer kafkaintId;

    private Integer midwareId;

    private String midwareName;

    private Integer serId;

    private String serIp;

    private String zkMidwareName;

    private String kafkaintName;

    private String kafkaintDesc;

    private String kafkaintStatus;

    private String kafkaintStatusName;

    private String kafkaintPort;

    private Integer zkMidwareId;

    private Integer midwareVerId;

    private String serOs;

    private String midwarePath;

    private String serUser;

    private String serPwd;

    private String midwareVerPath;

    private String midwareVerNo;

    private String hostAndPort;


    private String currentKafkaintStatus;

    private String healthMessage;

    public String getMidwareName() {
        return midwareName;
    }

    public void setMidwareName(String midwareName) {
        this.midwareName = midwareName;
    }

    public String getSerIp() {
        return serIp;
    }

    public void setSerIp(String serIp) {
        this.serIp = serIp;
    }


    public String getKafkaintStatusName() {
        return kafkaintStatusName;
    }

    public void setKafkaintStatusName(String kafkaintStatusName) {
        this.kafkaintStatusName = kafkaintStatusName;
    }

    public String getZkMidwareName() {
        return zkMidwareName;
    }

    public void setZkMidwareName(String zkMidwareName) {
        this.zkMidwareName = zkMidwareName;
    }

    public Integer getZkMidwareId() {
        return zkMidwareId;
    }

    public void setZkMidwareId(Integer zkMidwareId) {
        this.zkMidwareId = zkMidwareId;
    }

    public Integer getMidwareVerId() {
        return midwareVerId;
    }

    public void setMidwareVerId(Integer midwareVerId) {
        this.midwareVerId = midwareVerId;
    }

    public String getSerOs() {
        return serOs;
    }

    public void setSerOs(String serOs) {
        this.serOs = serOs;
    }

    public String getMidwarePath() {
        return midwarePath;
    }

    public void setMidwarePath(String midwarePath) {
        this.midwarePath = midwarePath;
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

    public String getMidwareVerPath() {
        return midwareVerPath;
    }

    public void setMidwareVerPath(String midwareVerPath) {
        this.midwareVerPath = midwareVerPath;
    }

    public String getMidwareVerNo() {
        return midwareVerNo;
    }

    public void setMidwareVerNo(String midwareVerNo) {
        this.midwareVerNo = midwareVerNo;
    }

    public String getHostAndPort() {
        return hostAndPort;
    }

    public void setHostAndPort(String hostAndPort) {
        this.hostAndPort = hostAndPort;
    }

    public String getCurrentKafkaintStatus() {
        return currentKafkaintStatus;
    }

    public void setCurrentKafkaintStatus(String currentKafkaintStatus) {
        this.currentKafkaintStatus = currentKafkaintStatus;
    }

    public String getHealthMessage() {
        return healthMessage;
    }

    public void setHealthMessage(String healthMessage) {
        this.healthMessage = healthMessage;
    }

}