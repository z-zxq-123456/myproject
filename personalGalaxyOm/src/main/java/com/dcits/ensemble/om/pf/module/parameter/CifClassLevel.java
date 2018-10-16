package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:07.
 */
public class CifClassLevel extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_CLASS_LEVEL.CLASS_LEVEL 缁煎悎璇勭骇
     */
    @TablePk(index = 1)
    private String classLevel;

    /**
     * This field is CIF_CLASS_LEVEL.CLASS_LEVEL_DESC 缁煎悎璇勭骇鎻忚堪
     */
    private String classLevelDesc;

    /**
     * This field is CIF_CLASS_LEVEL.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_CLASS_LEVEL.CLASS_LEVEL 缁煎悎璇勭骇
     */
    public String getClassLevel() {
        return classLevel;
    }

    /**
     * @param classLevel the value for CIF_CLASS_LEVEL.CLASS_LEVEL 缁煎悎璇勭骇
     */
    public void setClassLevel(String classLevel) {
        this.classLevel = classLevel == null ? null : classLevel.trim();
    }

    /**
     * @return the value of  CIF_CLASS_LEVEL.CLASS_LEVEL_DESC 缁煎悎璇勭骇鎻忚堪
     */
    public String getClassLevelDesc() {
        return classLevelDesc;
    }

    /**
     * @param classLevelDesc the value for CIF_CLASS_LEVEL.CLASS_LEVEL_DESC 缁煎悎璇勭骇鎻忚堪
     */
    public void setClassLevelDesc(String classLevelDesc) {
        this.classLevelDesc = classLevelDesc == null ? null : classLevelDesc.trim();
    }

    /**
     * @return the value of  CIF_CLASS_LEVEL.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_CLASS_LEVEL.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}