package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:11.
 */
public class CifEducation extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_EDUCATION.EDUCATION 鏁欒偛绋嬪害缂栧彿
     */
    @TablePk(index = 1)
    private String education;

    /**
     * This field is CIF_EDUCATION.EDUCATION_DESC 鏁欒偛绋嬪害鎻忚堪
     */
    private String educationDesc;

    /**
     * This field is CIF_EDUCATION.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_EDUCATION.EDUCATION 鏁欒偛绋嬪害缂栧彿
     */
    public String getEducation() {
        return education;
    }

    /**
     * @param education the value for CIF_EDUCATION.EDUCATION 鏁欒偛绋嬪害缂栧彿
     */
    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    /**
     * @return the value of  CIF_EDUCATION.EDUCATION_DESC 鏁欒偛绋嬪害鎻忚堪
     */
    public String getEducationDesc() {
        return educationDesc;
    }

    /**
     * @param educationDesc the value for CIF_EDUCATION.EDUCATION_DESC 鏁欒偛绋嬪害鎻忚堪
     */
    public void setEducationDesc(String educationDesc) {
        this.educationDesc = educationDesc == null ? null : educationDesc.trim();
    }

    /**
     * @return the value of  CIF_EDUCATION.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_EDUCATION.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}