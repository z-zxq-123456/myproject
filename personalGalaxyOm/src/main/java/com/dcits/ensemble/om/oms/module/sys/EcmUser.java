package com.dcits.ensemble.om.oms.module.sys;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by tangxl on 2014/07/24 13:54:58.
 */
public class EcmUser extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_USER.USER_ID 
     */
    @TablePk(index = 1)
    private String userId;

    /**
     * This field is ECM_USER.USER_NAME 
     */
    private String userName;
    /**
     * This field is ECM_USER.USER_CHNAME 
     */
    private String userChname;
    /**
     * This field is ECM_USER.USER_PWD 
     */
    private String userPwd;
    /**
     * This field is ECM_USER.USER_CREATE_DATE 
     */
    private String userCreateDate;
    /**
     * This field is ECM_USER.USER_CONTACT 
     */
    private String userContact;
    /**
     * This field is ECM_USER.USER_MOBILE 
     */
    private String userMobile;
    /**
     * This field is ECM_USER.USER_EMAIL 
     */
    private String userEmail;
    /**
     * This field is ECM_USER.USER_STATUS 
     */
    private String userStatus;

    /**
     * @return the value of  ECM_USER.USER_ID 
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the value for ECM_USER.USER_ID 
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * @return the value of  ECM_USER.USER_NAME 
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the value for ECM_USER.USER_NAME 
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }
    /**
     * @return the value of  ECM_USER.USER_CHNAME 
     */
    public String getUserChname() {
        return userChname;
    }

    /**
     * @param userChname the value for ECM_USER.USER_CHNAME 
     */
    public void setUserChname(String userChname) {
        this.userChname = userChname == null ? null : userChname.trim();
    }
    /**
     * @return the value of  ECM_USER.USER_PWD 
     */
    public String getUserPwd() {
        return userPwd;
    }

    /**
     * @param userPwd the value for ECM_USER.USER_PWD 
     */
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }
    /**
     * @return the value of  ECM_USER.USER_CREATE_DATE 
     */
    public String getUserCreateDate() {
        return userCreateDate;
    }

    /**
     * @param userCreateDate the value for ECM_USER.USER_CREATE_DATE 
     */
    public void setUserCreateDate(String userCreateDate) {
        this.userCreateDate = userCreateDate == null ? null : userCreateDate.trim();
    }
    /**
     * @return the value of  ECM_USER.USER_CONTACT 
     */
    public String getUserContact() {
        return userContact;
    }

    /**
     * @param userContact the value for ECM_USER.USER_CONTACT 
     */
    public void setUserContact(String userContact) {
        this.userContact = userContact == null ? null : userContact.trim();
    }
    /**
     * @return the value of  ECM_USER.USER_MOBILE 
     */
    public String getUserMobile() {
        return userMobile;
    }

    /**
     * @param userMobile the value for ECM_USER.USER_MOBILE 
     */
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }
    /**
     * @return the value of  ECM_USER.USER_EMAIL 
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @param userEmail the value for ECM_USER.USER_EMAIL 
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }
    /**
     * @return the value of  ECM_USER.USER_STATUS 
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * @param userStatus the value for ECM_USER.USER_STATUS 
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }
}