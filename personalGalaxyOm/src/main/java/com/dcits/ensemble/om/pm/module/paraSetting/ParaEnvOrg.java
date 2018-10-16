package com.dcits.ensemble.om.pm.module.paraSetting;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by maruie on 2016/04/14 09:40:04.
 */
public class ParaEnvOrg extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is PARA_ENV_ORG.ENV_ID 环境ID
     */
    @TablePk(index = 1)
    private String envId;

    /**
     * This field is PARA_ENV_ORG.SYSTEM_ID 系统ID
     */
    private String systemId;

    /**
     * This field is PARA_ENV_ORG.ENV_DESC 环境描述
     */
    private String envDesc;

    /**
     * This field is PARA_ENV_ORG.URL HTTP接入URL
     */
    private String url;

    /**
     * This field is PARA_ENV_ORG.MODULE 模块
     */
    private String module;

    /**
     * This field is PARA_ENV_ORG.SERVICE_CODE 服务代码
     */
    private String serviceCode;

    /**
     * This field is PARA_ENV_ORG.MESSAGE_TYPE 报文类型
     */
    private String messageType;

    /**
     * This field is PARA_ENV_ORG.MESSAGE_CODE 报文代码
     */
    private String messageCode;

    /**
     * This field is PARA_ENV_ORG.OPERATOR 最新修改人ID
     */
    private String operator;

    /**
     * This field is PARA_ENV_ORG.TIME 最新修改时间
     */
    private String time;

    /**
     * This field is PARA_ENV_ORG.IP 最后修改人IP
     */
    private String ip;

    /**
     * @return the value of  PARA_ENV_ORG.ENV_ID 环境ID
     */
    public String getEnvId() {
        return envId;
    }

    /**
     * @param envId the value for PARA_ENV_ORG.ENV_ID 环境ID
     */
    public void setEnvId(String envId) {
        this.envId = envId == null ? null : envId.trim();
    }

    /**
     * @return the value of  PARA_ENV_ORG.SYSTEM_ID 系统ID
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * @param systemId the value for PARA_ENV_ORG.SYSTEM_ID 系统ID
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    /**
     * @return the value of  PARA_ENV_ORG.ENV_DESC 环境描述
     */
    public String getEnvDesc() {
        return envDesc;
    }

    /**
     * @param envDesc the value for PARA_ENV_ORG.ENV_DESC 环境描述
     */
    public void setEnvDesc(String envDesc) {
        this.envDesc = envDesc == null ? null : envDesc.trim();
    }

    /**
     * @return the value of  PARA_ENV_ORG.URL HTTP接入URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the value for PARA_ENV_ORG.URL HTTP接入URL
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * @return the value of  PARA_ENV_ORG.MODULE 模块
     */
    public String getModule() {
        return module;
    }

    /**
     * @param module the value for PARA_ENV_ORG.MODULE 模块
     */
    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
    }

    /**
     * @return the value of  PARA_ENV_ORG.SERVICE_CODE 服务代码
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * @param serviceCode the value for PARA_ENV_ORG.SERVICE_CODE 服务代码
     */
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode == null ? null : serviceCode.trim();
    }

    /**
     * @return the value of  PARA_ENV_ORG.MESSAGE_TYPE 报文类型
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * @param messageType the value for PARA_ENV_ORG.MESSAGE_TYPE 报文类型
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType == null ? null : messageType.trim();
    }

    /**
     * @return the value of  PARA_ENV_ORG.MESSAGE_CODE 报文代码
     */
    public String getMessageCode() {
        return messageCode;
    }

    /**
     * @param messageCode the value for PARA_ENV_ORG.MESSAGE_CODE 报文代码
     */
    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode == null ? null : messageCode.trim();
    }

    /**
     * @return the value of  PARA_ENV_ORG.OPERATOR 最新修改人ID
     */
    public String getOperator() {
        return operator;
    }

    /**
     * @param operator the value for PARA_ENV_ORG.OPERATOR 最新修改人ID
     */
    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    /**
     * @return the value of  PARA_ENV_ORG.TIME 最新修改时间
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the value for PARA_ENV_ORG.TIME 最新修改时间
     */
    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    /**
     * @return the value of  PARA_ENV_ORG.IP 最后修改人IP
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the value for PARA_ENV_ORG.IP 最后修改人IP
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }
}