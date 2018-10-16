package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:16.
 */
public class CifSalutation extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_SALUTATION.SALUTATION 绉板懠浠ｇ爜
     */
    @TablePk(index = 1)
    private String salutation;

    /**
     * This field is CIF_SALUTATION.SALUTATION_DESC 绉板懠浠ｇ爜璇存槑
     */
    private String salutationDesc;

    /**
     * This field is CIF_SALUTATION.GENDER_FLAG 閫傜敤鎬у埆 M-鐢�F-濂�U-鏈煡
     */
    private String genderFlag;

    /**
     * This field is CIF_SALUTATION.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_SALUTATION.SALUTATION 绉板懠浠ｇ爜
     */
    public String getSalutation() {
        return salutation;
    }

    /**
     * @param salutation the value for CIF_SALUTATION.SALUTATION 绉板懠浠ｇ爜
     */
    public void setSalutation(String salutation) {
        this.salutation = salutation == null ? null : salutation.trim();
    }

    /**
     * @return the value of  CIF_SALUTATION.SALUTATION_DESC 绉板懠浠ｇ爜璇存槑
     */
    public String getSalutationDesc() {
        return salutationDesc;
    }

    /**
     * @param salutationDesc the value for CIF_SALUTATION.SALUTATION_DESC 绉板懠浠ｇ爜璇存槑
     */
    public void setSalutationDesc(String salutationDesc) {
        this.salutationDesc = salutationDesc == null ? null : salutationDesc.trim();
    }

    /**
     * @return the value of  CIF_SALUTATION.GENDER_FLAG 閫傜敤鎬у埆 M-鐢�F-濂�U-鏈煡
     */
    public String getGenderFlag() {
        return genderFlag;
    }

    /**
     * @param genderFlag the value for CIF_SALUTATION.GENDER_FLAG 閫傜敤鎬у埆 M-鐢�F-濂�U-鏈煡
     */
    public void setGenderFlag(String genderFlag) {
        this.genderFlag = genderFlag == null ? null : genderFlag.trim();
    }

    /**
     * @return the value of  CIF_SALUTATION.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_SALUTATION.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}