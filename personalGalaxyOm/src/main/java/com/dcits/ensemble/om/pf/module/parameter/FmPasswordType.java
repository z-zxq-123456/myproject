package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/03/18 18:03:03.
 */
public class FmPasswordType extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is FM_PASSWORD_TYPE.PASSWORD_TYPE 
     */
    @TablePk(index = 1)
    private String passwordType;

    /**
     * This field is FM_PASSWORD_TYPE.PASSWORD_TYPE_DESC 瀵嗙爜绫诲瀷鎻忚堪
     */
    private String passwordTypeDesc;

    /**
     * This field is FM_PASSWORD_TYPE.CHANNEL_MUSTER 娓犻亾闆嗗悎
澶氫釜娓犻亾涔嬮棿鐢�鍒嗛殧锛屽鏋滄槸鎵€鏈夋笭閬擄紝鍒欑敤"ALL"琛ㄧず
     */
    private String channelMuster;

    /**
     * This field is FM_PASSWORD_TYPE.ERROR_CHANNEL_IND 閿欒娆℃暟鏄惁鍒嗘笭閬擄細Y-鏄�N-鍚�榛樿涓篘
     */
    private String errorChannelInd;

    /**
     * This field is FM_PASSWORD_TYPE.MAX_FAILUER_TIMES 
     */
    private String maxFailuerTimes;

    /**
     * This field is FM_PASSWORD_TYPE.RESET_IND 鏄惁褰撴棩娓呴浂鏂瑰紡锛�-褰撴棩娓呴浂 0-闈炲綋鏃ユ竻闆� 榛樿1
     */
    private String resetInd;

    /**
     * This field is FM_PASSWORD_TYPE.BRANCH 缁存姢鏈烘瀯
     */
    private String branch;

    /**
     * This field is FM_PASSWORD_TYPE.LAST_CHANGE_OFFICER 涓婃淇敼鏌滃憳
     */
    private String lastChangeOfficer;

    /**
     * This field is FM_PASSWORD_TYPE.LAST_CHANGE_DATE 涓婃淇敼鏃ユ湡
     */
    private String lastChangeDate;

    /**
     * This field is FM_PASSWORD_TYPE.LAST_CHANGE_TIME 涓婃淇敼鏃ユ湡
     */
    private String lastChangeTime;

    /**
     * This field is FM_PASSWORD_TYPE.COMPANY 
     */
    private String company;

    /**
     * @return the value of  FM_PASSWORD_TYPE.PASSWORD_TYPE 
     */
    public String getPasswordType() {
        return passwordType;
    }

    /**
     * @param passwordType the value for FM_PASSWORD_TYPE.PASSWORD_TYPE 
     */
    public void setPasswordType(String passwordType) {
        this.passwordType = passwordType == null ? null : passwordType.trim();
    }

    /**
     * @return the value of  FM_PASSWORD_TYPE.PASSWORD_TYPE_DESC 瀵嗙爜绫诲瀷鎻忚堪
     */
    public String getPasswordTypeDesc() {
        return passwordTypeDesc;
    }

    /**
     * @param passwordTypeDesc the value for FM_PASSWORD_TYPE.PASSWORD_TYPE_DESC 瀵嗙爜绫诲瀷鎻忚堪
     */
    public void setPasswordTypeDesc(String passwordTypeDesc) {
        this.passwordTypeDesc = passwordTypeDesc == null ? null : passwordTypeDesc.trim();
    }

    /**
     * @return the value of  FM_PASSWORD_TYPE.CHANNEL_MUSTER 娓犻亾闆嗗悎
澶氫釜娓犻亾涔嬮棿鐢�鍒嗛殧锛屽鏋滄槸鎵€鏈夋笭閬擄紝鍒欑敤"ALL"琛ㄧず
     */
    public String getChannelMuster() {
        return channelMuster;
    }

    /**
     * @param channelMuster the value for FM_PASSWORD_TYPE.CHANNEL_MUSTER 娓犻亾闆嗗悎
澶氫釜娓犻亾涔嬮棿鐢�鍒嗛殧锛屽鏋滄槸鎵€鏈夋笭閬擄紝鍒欑敤"ALL"琛ㄧず
     */
    public void setChannelMuster(String channelMuster) {
        this.channelMuster = channelMuster == null ? null : channelMuster.trim();
    }

    /**
     * @return the value of  FM_PASSWORD_TYPE.ERROR_CHANNEL_IND 閿欒娆℃暟鏄惁鍒嗘笭閬擄細Y-鏄�N-鍚�榛樿涓篘
     */
    public String getErrorChannelInd() {
        return errorChannelInd;
    }

    /**
     * @param errorChannelInd the value for FM_PASSWORD_TYPE.ERROR_CHANNEL_IND 閿欒娆℃暟鏄惁鍒嗘笭閬擄細Y-鏄�N-鍚�榛樿涓篘
     */
    public void setErrorChannelInd(String errorChannelInd) {
        this.errorChannelInd = errorChannelInd == null ? null : errorChannelInd.trim();
    }

    /**
     * @return the value of  FM_PASSWORD_TYPE.MAX_FAILUER_TIMES 
     */
    public String getMaxFailuerTimes() {
        return maxFailuerTimes;
    }

    /**
     * @param maxFailuerTimes the value for FM_PASSWORD_TYPE.MAX_FAILUER_TIMES 
     */
    public void setMaxFailuerTimes(String maxFailuerTimes) {
        this.maxFailuerTimes = maxFailuerTimes == null ? null : maxFailuerTimes.trim();
    }

    /**
     * @return the value of  FM_PASSWORD_TYPE.RESET_IND 鏄惁褰撴棩娓呴浂鏂瑰紡锛�-褰撴棩娓呴浂 0-闈炲綋鏃ユ竻闆� 榛樿1
     */
    public String getResetInd() {
        return resetInd;
    }

    /**
     * @param resetInd the value for FM_PASSWORD_TYPE.RESET_IND 鏄惁褰撴棩娓呴浂鏂瑰紡锛�-褰撴棩娓呴浂 0-闈炲綋鏃ユ竻闆� 榛樿1
     */
    public void setResetInd(String resetInd) {
        this.resetInd = resetInd == null ? null : resetInd.trim();
    }

    /**
     * @return the value of  FM_PASSWORD_TYPE.BRANCH 缁存姢鏈烘瀯
     */
    public String getBranch() {
        return branch;
    }

    /**
     * @param branch the value for FM_PASSWORD_TYPE.BRANCH 缁存姢鏈烘瀯
     */
    public void setBranch(String branch) {
        this.branch = branch == null ? null : branch.trim();
    }

    /**
     * @return the value of  FM_PASSWORD_TYPE.LAST_CHANGE_OFFICER 涓婃淇敼鏌滃憳
     */
    public String getLastChangeOfficer() {
        return lastChangeOfficer;
    }

    /**
     * @param lastChangeOfficer the value for FM_PASSWORD_TYPE.LAST_CHANGE_OFFICER 涓婃淇敼鏌滃憳
     */
    public void setLastChangeOfficer(String lastChangeOfficer) {
        this.lastChangeOfficer = lastChangeOfficer == null ? null : lastChangeOfficer.trim();
    }

    /**
     * @return the value of  FM_PASSWORD_TYPE.LAST_CHANGE_DATE 涓婃淇敼鏃ユ湡
     */
    public String getLastChangeDate() {
        return lastChangeDate;
    }

    /**
     * @param lastChangeDate the value for FM_PASSWORD_TYPE.LAST_CHANGE_DATE 涓婃淇敼鏃ユ湡
     */
    public void setLastChangeDate(String lastChangeDate) {
        this.lastChangeDate = lastChangeDate == null ? null : lastChangeDate.trim();
    }

    /**
     * @return the value of  FM_PASSWORD_TYPE.LAST_CHANGE_TIME 涓婃淇敼鏃ユ湡
     */
    public String getLastChangeTime() {
        return lastChangeTime;
    }

    /**
     * @param lastChangeTime the value for FM_PASSWORD_TYPE.LAST_CHANGE_TIME 涓婃淇敼鏃ユ湡
     */
    public void setLastChangeTime(String lastChangeTime) {
        this.lastChangeTime = lastChangeTime == null ? null : lastChangeTime.trim();
    }

    /**
     * @return the value of  FM_PASSWORD_TYPE.COMPANY 
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for FM_PASSWORD_TYPE.COMPANY 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}