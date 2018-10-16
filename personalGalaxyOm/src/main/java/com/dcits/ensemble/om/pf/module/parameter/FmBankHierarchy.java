package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/03/18 18:03:02.
 */
public class FmBankHierarchy extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is FM_BANK_HIERARCHY.HIERARCHY_CODE 灞傜骇浠ｇ爜
     */
    @TablePk(index = 1)
    private String hierarchyCode;

    /**
     * This field is FM_BANK_HIERARCHY.HIERARCHY_NAME 灞傜骇璇存槑
     */
    private String hierarchyName;

    /**
     * This field is FM_BANK_HIERARCHY.HIERARCHY_LEVEL 绾у埆
     */
    private String hierarchyLevel;

    /**
     * This field is FM_BANK_HIERARCHY.COMPANY 
     */
    private String company;

    /**
     * @return the value of  FM_BANK_HIERARCHY.HIERARCHY_CODE 灞傜骇浠ｇ爜
     */
    public String getHierarchyCode() {
        return hierarchyCode;
    }

    /**
     * @param hierarchyCode the value for FM_BANK_HIERARCHY.HIERARCHY_CODE 灞傜骇浠ｇ爜
     */
    public void setHierarchyCode(String hierarchyCode) {
        this.hierarchyCode = hierarchyCode == null ? null : hierarchyCode.trim();
    }

    /**
     * @return the value of  FM_BANK_HIERARCHY.HIERARCHY_NAME 灞傜骇璇存槑
     */
    public String getHierarchyName() {
        return hierarchyName;
    }

    /**
     * @param hierarchyName the value for FM_BANK_HIERARCHY.HIERARCHY_NAME 灞傜骇璇存槑
     */
    public void setHierarchyName(String hierarchyName) {
        this.hierarchyName = hierarchyName == null ? null : hierarchyName.trim();
    }

    /**
     * @return the value of  FM_BANK_HIERARCHY.HIERARCHY_LEVEL 绾у埆
     */
    public String getHierarchyLevel() {
        return hierarchyLevel;
    }

    /**
     * @param hierarchyLevel the value for FM_BANK_HIERARCHY.HIERARCHY_LEVEL 绾у埆
     */
    public void setHierarchyLevel(String hierarchyLevel) {
        this.hierarchyLevel = hierarchyLevel == null ? null : hierarchyLevel.trim();
    }

    /**
     * @return the value of  FM_BANK_HIERARCHY.COMPANY 
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for FM_BANK_HIERARCHY.COMPANY 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}