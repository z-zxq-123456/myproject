package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:15.
 */
public class CifRelationType extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_RELATION_TYPE.RELATION_TYPE 绫诲瀷
     */
    @TablePk(index = 1)
    private String relationType;

    /**
     * This field is CIF_RELATION_TYPE.RELATION_DESC 绫诲瀷鎻忚堪
     */
    private String relationDesc;

    /**
     * This field is CIF_RELATION_TYPE.COUNTER_REL 鍙嶇浉鍏崇郴绫诲瀷 鍙栧€�FM_RELATION_TYPE.RELATION_TYPE
     */
    private String counterRel;

    /**
     * This field is CIF_RELATION_TYPE.EXPOSURE 椋庨櫓 Y/N
     */
    private String exposure;

    /**
     * This field is CIF_RELATION_TYPE.EQUITY 鑲℃潈 Y/N
     */
    private String equity;

    /**
     * This field is CIF_RELATION_TYPE.JOINT_ACCT 鑱斿悎甯愭埛 Y/N
     */
    private String jointAcct;

    /**
     * This field is CIF_RELATION_TYPE.AUTHORISED 鎺堟潈鏂�Y/N
     */
    private String authorised;

    /**
     * This field is CIF_RELATION_TYPE.EMPLOYMENT 闆囦剑 Y/N
     */
    private String employment;

    /**
     * This field is CIF_RELATION_TYPE.RELATIVE 浜叉垰 Y/N
     */
    private String relative;

    /**
     * This field is CIF_RELATION_TYPE.SYMMENTRIC 瀵圭瓑 Y/N
     */
    private String symmentric;

    /**
     * This field is CIF_RELATION_TYPE.JOIN_COLLAT 鑱斿悎浣�Y/N
     */
    private String joinCollat;

    /**
     * This field is CIF_RELATION_TYPE.RELATION_TYPE_FLAG 鍏崇郴绫诲瀷鏍囪瘑 1-涓汉-涓汉 2-涓汉-闈炰釜浜�3-闈炰釜浜�闈炰釜浜�4-闈炰釜浜�涓汉 5-涓汉-闈炰釜浜�鎴�闈炰釜浜�闈炰釜浜�6-闈炰釜浜�闈炰釜浜�鎴�闈炰釜浜�涓汉
     */
    private String relationTypeFlag;

    /**
     * This field is CIF_RELATION_TYPE.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_RELATION_TYPE.RELATION_TYPE 绫诲瀷
     */
    public String getRelationType() {
        return relationType;
    }

    /**
     * @param relationType the value for CIF_RELATION_TYPE.RELATION_TYPE 绫诲瀷
     */
    public void setRelationType(String relationType) {
        this.relationType = relationType == null ? null : relationType.trim();
    }

    /**
     * @return the value of  CIF_RELATION_TYPE.RELATION_DESC 绫诲瀷鎻忚堪
     */
    public String getRelationDesc() {
        return relationDesc;
    }

    /**
     * @param relationDesc the value for CIF_RELATION_TYPE.RELATION_DESC 绫诲瀷鎻忚堪
     */
    public void setRelationDesc(String relationDesc) {
        this.relationDesc = relationDesc == null ? null : relationDesc.trim();
    }

    /**
     * @return the value of  CIF_RELATION_TYPE.COUNTER_REL 鍙嶇浉鍏崇郴绫诲瀷 鍙栧€�FM_RELATION_TYPE.RELATION_TYPE
     */
    public String getCounterRel() {
        return counterRel;
    }

    /**
     * @param counterRel the value for CIF_RELATION_TYPE.COUNTER_REL 鍙嶇浉鍏崇郴绫诲瀷 鍙栧€�FM_RELATION_TYPE.RELATION_TYPE
     */
    public void setCounterRel(String counterRel) {
        this.counterRel = counterRel == null ? null : counterRel.trim();
    }

    /**
     * @return the value of  CIF_RELATION_TYPE.EXPOSURE 椋庨櫓 Y/N
     */
    public String getExposure() {
        return exposure;
    }

    /**
     * @param exposure the value for CIF_RELATION_TYPE.EXPOSURE 椋庨櫓 Y/N
     */
    public void setExposure(String exposure) {
        this.exposure = exposure == null ? null : exposure.trim();
    }

    /**
     * @return the value of  CIF_RELATION_TYPE.EQUITY 鑲℃潈 Y/N
     */
    public String getEquity() {
        return equity;
    }

    /**
     * @param equity the value for CIF_RELATION_TYPE.EQUITY 鑲℃潈 Y/N
     */
    public void setEquity(String equity) {
        this.equity = equity == null ? null : equity.trim();
    }

    /**
     * @return the value of  CIF_RELATION_TYPE.JOINT_ACCT 鑱斿悎甯愭埛 Y/N
     */
    public String getJointAcct() {
        return jointAcct;
    }

    /**
     * @param jointAcct the value for CIF_RELATION_TYPE.JOINT_ACCT 鑱斿悎甯愭埛 Y/N
     */
    public void setJointAcct(String jointAcct) {
        this.jointAcct = jointAcct == null ? null : jointAcct.trim();
    }

    /**
     * @return the value of  CIF_RELATION_TYPE.AUTHORISED 鎺堟潈鏂�Y/N
     */
    public String getAuthorised() {
        return authorised;
    }

    /**
     * @param authorised the value for CIF_RELATION_TYPE.AUTHORISED 鎺堟潈鏂�Y/N
     */
    public void setAuthorised(String authorised) {
        this.authorised = authorised == null ? null : authorised.trim();
    }

    /**
     * @return the value of  CIF_RELATION_TYPE.EMPLOYMENT 闆囦剑 Y/N
     */
    public String getEmployment() {
        return employment;
    }

    /**
     * @param employment the value for CIF_RELATION_TYPE.EMPLOYMENT 闆囦剑 Y/N
     */
    public void setEmployment(String employment) {
        this.employment = employment == null ? null : employment.trim();
    }

    /**
     * @return the value of  CIF_RELATION_TYPE.RELATIVE 浜叉垰 Y/N
     */
    public String getRelative() {
        return relative;
    }

    /**
     * @param relative the value for CIF_RELATION_TYPE.RELATIVE 浜叉垰 Y/N
     */
    public void setRelative(String relative) {
        this.relative = relative == null ? null : relative.trim();
    }

    /**
     * @return the value of  CIF_RELATION_TYPE.SYMMENTRIC 瀵圭瓑 Y/N
     */
    public String getSymmentric() {
        return symmentric;
    }

    /**
     * @param symmentric the value for CIF_RELATION_TYPE.SYMMENTRIC 瀵圭瓑 Y/N
     */
    public void setSymmentric(String symmentric) {
        this.symmentric = symmentric == null ? null : symmentric.trim();
    }

    /**
     * @return the value of  CIF_RELATION_TYPE.JOIN_COLLAT 鑱斿悎浣�Y/N
     */
    public String getJoinCollat() {
        return joinCollat;
    }

    /**
     * @param joinCollat the value for CIF_RELATION_TYPE.JOIN_COLLAT 鑱斿悎浣�Y/N
     */
    public void setJoinCollat(String joinCollat) {
        this.joinCollat = joinCollat == null ? null : joinCollat.trim();
    }

    /**
     * @return the value of  CIF_RELATION_TYPE.RELATION_TYPE_FLAG 鍏崇郴绫诲瀷鏍囪瘑 1-涓汉-涓汉 2-涓汉-闈炰釜浜�3-闈炰釜浜�闈炰釜浜�4-闈炰釜浜�涓汉 5-涓汉-闈炰釜浜�鎴�闈炰釜浜�闈炰釜浜�6-闈炰釜浜�闈炰釜浜�鎴�闈炰釜浜�涓汉
     */
    public String getRelationTypeFlag() {
        return relationTypeFlag;
    }

    /**
     * @param relationTypeFlag the value for CIF_RELATION_TYPE.RELATION_TYPE_FLAG 鍏崇郴绫诲瀷鏍囪瘑 1-涓汉-涓汉 2-涓汉-闈炰釜浜�3-闈炰釜浜�闈炰釜浜�4-闈炰釜浜�涓汉 5-涓汉-闈炰釜浜�鎴�闈炰釜浜�闈炰釜浜�6-闈炰釜浜�闈炰釜浜�鎴�闈炰釜浜�涓汉
     */
    public void setRelationTypeFlag(String relationTypeFlag) {
        this.relationTypeFlag = relationTypeFlag == null ? null : relationTypeFlag.trim();
    }

    /**
     * @return the value of  CIF_RELATION_TYPE.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_RELATION_TYPE.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}