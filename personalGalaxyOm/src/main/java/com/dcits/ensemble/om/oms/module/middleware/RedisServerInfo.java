package com.dcits.ensemble.om.oms.module.middleware;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by tangxl on 2014/07/24 13:54:58.
 */
public class RedisServerInfo extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is REDIS_SERVER_INFO.SERVER_ID 
     */
    @TablePk(index = 1)
    private Integer serverId;

    /**
     * This field is REDIS_SERVER_INFO.SERVER_IP 
     */
    private String serverIp;
    /**
     * This field is REDIS_SERVER_INFO.SERVER_PORT 
     */
    private String serverPort;
    /**
     * This field is REDIS_SERVER_INFO.USER_ID 
     */
    private String userId;

    /**
     * @return the value of  REDIS_SERVER_INFO.SERVER_ID 
     */
    public Integer getServerId() {
        return serverId;
    }

    /**
     * @param serverId the value for REDIS_SERVER_INFO.SERVER_ID 
     */
    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }
    /**
     * @return the value of  REDIS_SERVER_INFO.SERVER_IP 
     */
    public String getServerIp() {
        return serverIp;
    }

    /**
     * @param serverIp the value for REDIS_SERVER_INFO.SERVER_IP 
     */
    public void setServerIp(String serverIp) {
        this.serverIp = serverIp == null ? null : serverIp.trim();
    }
    /**
     * @return the value of  REDIS_SERVER_INFO.SERVER_PORT 
     */
    public String getServerPort() {
        return serverPort;
    }

    /**
     * @param serverPort the value for REDIS_SERVER_INFO.SERVER_PORT 
     */
    public void setServerPort(String serverPort) {
        this.serverPort = serverPort == null ? null : serverPort.trim();
    }
    /**
     * @return the value of  REDIS_SERVER_INFO.USER_ID 
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the value for REDIS_SERVER_INFO.USER_ID 
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}