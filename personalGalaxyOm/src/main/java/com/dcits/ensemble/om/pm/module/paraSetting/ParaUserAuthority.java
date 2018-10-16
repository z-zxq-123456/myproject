package com.dcits.ensemble.om.pm.module.paraSetting;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by maruie on 2016/03/30 18:35:22.
 */
public class ParaUserAuthority extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is PARA_USER_AUTHORITY.USER_ID 
     */
    @TablePk(index = 1)
    private String userId;

    /**
     * This field is PARA_USER_AUTHORITY.AUTH_APPLICATION 申请：Y/N
     */
    private String authApplication;

    /**
     * This field is PARA_USER_AUTHORITY.AUTH_ENTERING 录入:Y/N
     */
    private String authEntering;

    /**
     * This field is PARA_USER_AUTHORITY.AUTH_CHECK 复核:Y/N
     */
    private String authCheck;

    /**
     * This field is PARA_USER_AUTHORITY.AUTH_PUBLISH 发布:Y/N
     */
    private String authPublish;

    /**
     * @return the value of  PARA_USER_AUTHORITY.USER_ID 
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the value for PARA_USER_AUTHORITY.USER_ID 
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * @return the value of  PARA_USER_AUTHORITY.AUTH_APPLICATION 申请：Y/N
     */
    public String getAuthApplication() {
        return authApplication;
    }

    /**
     * @param authApplication the value for PARA_USER_AUTHORITY.AUTH_APPLICATION 申请：Y/N
     */
    public void setAuthApplication(String authApplication) {
        this.authApplication = authApplication == null ? null : authApplication.trim();
    }

    /**
     * @return the value of  PARA_USER_AUTHORITY.AUTH_ENTERING 录入:Y/N
     */
    public String getAuthEntering() {
        return authEntering;
    }

    /**
     * @param authEntering the value for PARA_USER_AUTHORITY.AUTH_ENTERING 录入:Y/N
     */
    public void setAuthEntering(String authEntering) {
        this.authEntering = authEntering == null ? null : authEntering.trim();
    }

    /**
     * @return the value of  PARA_USER_AUTHORITY.AUTH_CHECK 复核:Y/N
     */
    public String getAuthCheck() {
        return authCheck;
    }

    /**
     * @param authCheck the value for PARA_USER_AUTHORITY.AUTH_CHECK 复核:Y/N
     */
    public void setAuthCheck(String authCheck) {
        this.authCheck = authCheck == null ? null : authCheck.trim();
    }

    /**
     * @return the value of  PARA_USER_AUTHORITY.AUTH_PUBLISH 发布:Y/N
     */
    public String getAuthPublish() {
        return authPublish;
    }

    /**
     * @param authPublish the value for PARA_USER_AUTHORITY.AUTH_PUBLISH 发布:Y/N
     */
    public void setAuthPublish(String authPublish) {
        this.authPublish = authPublish == null ? null : authPublish.trim();
    }
}