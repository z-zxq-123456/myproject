package com.dcits.ensemble.om.oms.module.server;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by luolang on 2017/2/9.
 */
public class EcmServerAlarm extends AbstractBean {
    /**
     * This field is ECM_SERVER_INFO.SER_ID
     */
    @TablePk(index = 1)
    private Integer serAlarmId;

    private Integer serId;

    private String serIp;

    private String alarmTime;

    private String alarmType;

    private String alarmTypeName;

    private String alarmInfo;

    private String handleInfo;

    public Integer getSerAlarmId() {
        return serAlarmId;
    }

    public void setSerAlarmId(Integer serAlarmId) {
        this.serAlarmId = serAlarmId;
    }

    public Integer getSerId() {
        return serId;
    }

    public void setSerId(Integer serId) {
        this.serId = serId;
    }

    public String getSerIp() {
        return serIp;
    }

    public void setSerIp(String serIp) {
        this.serIp = serIp;
    }

    public String getAlarmInfo() {
        return alarmInfo;
    }

    public void setAlarmInfo(String alarmInfo) {
        this.alarmInfo = alarmInfo;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getHandleInfo() {
        return handleInfo;
    }

    public void setHandleInfo(String handleInfo) {
        this.handleInfo = handleInfo;
    }

    public String getAlarmTypeName() {
        return alarmTypeName;
    }

    public void setAlarmTypeName(String alarmTypeName) {
        this.alarmTypeName = alarmTypeName;
    }
}
