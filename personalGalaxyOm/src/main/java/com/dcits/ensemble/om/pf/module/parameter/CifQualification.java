package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:14.
 */
public class CifQualification extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_QUALIFICATION.QUALIFICATION 鑱岀О
     */
    @TablePk(index = 1)
    private String qualification;

    /**
     * This field is CIF_QUALIFICATION.QUALIFICATION_DESC 鑱岀О鎻忚堪
     */
    private String qualificationDesc;

    /**
     * This field is CIF_QUALIFICATION.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_QUALIFICATION.QUALIFICATION 鑱岀О
     */
    public String getQualification() {
        return qualification;
    }

    /**
     * @param qualification the value for CIF_QUALIFICATION.QUALIFICATION 鑱岀О
     */
    public void setQualification(String qualification) {
        this.qualification = qualification == null ? null : qualification.trim();
    }

    /**
     * @return the value of  CIF_QUALIFICATION.QUALIFICATION_DESC 鑱岀О鎻忚堪
     */
    public String getQualificationDesc() {
        return qualificationDesc;
    }

    /**
     * @param qualificationDesc the value for CIF_QUALIFICATION.QUALIFICATION_DESC 鑱岀О鎻忚堪
     */
    public void setQualificationDesc(String qualificationDesc) {
        this.qualificationDesc = qualificationDesc == null ? null : qualificationDesc.trim();
    }

    /**
     * @return the value of  CIF_QUALIFICATION.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_QUALIFICATION.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}