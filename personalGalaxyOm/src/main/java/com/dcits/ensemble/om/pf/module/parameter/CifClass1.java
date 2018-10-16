package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:05.
 */
public class CifClass1 extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_CLASS_1.CLASS_1 鍒嗙被1
     */
    @TablePk(index = 1)
    private String class1;

    /**
     * This field is CIF_CLASS_1.CLASS_1_DESC 鍒嗙被鎻忚堪
     */
    private String class1Desc;

    /**
     * This field is CIF_CLASS_1.COUNTER_PARTY 浜ゆ槗瀵规墜浠ｇ爜
     */
    private String counterParty;

    /**
     * This field is CIF_CLASS_1.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_CLASS_1.CLASS_1 鍒嗙被1
     */
    public String getClass1() {
        return class1;
    }

    /**
     * @param class1 the value for CIF_CLASS_1.CLASS_1 鍒嗙被1
     */
    public void setClass1(String class1) {
        this.class1 = class1 == null ? null : class1.trim();
    }

    /**
     * @return the value of  CIF_CLASS_1.CLASS_1_DESC 鍒嗙被鎻忚堪
     */
    public String getClass1Desc() {
        return class1Desc;
    }

    /**
     * @param class1Desc the value for CIF_CLASS_1.CLASS_1_DESC 鍒嗙被鎻忚堪
     */
    public void setClass1Desc(String class1Desc) {
        this.class1Desc = class1Desc == null ? null : class1Desc.trim();
    }

    /**
     * @return the value of  CIF_CLASS_1.COUNTER_PARTY 浜ゆ槗瀵规墜浠ｇ爜
     */
    public String getCounterParty() {
        return counterParty;
    }

    /**
     * @param counterParty the value for CIF_CLASS_1.COUNTER_PARTY 浜ゆ槗瀵规墜浠ｇ爜
     */
    public void setCounterParty(String counterParty) {
        this.counterParty = counterParty == null ? null : counterParty.trim();
    }

    /**
     * @return the value of  CIF_CLASS_1.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_CLASS_1.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}