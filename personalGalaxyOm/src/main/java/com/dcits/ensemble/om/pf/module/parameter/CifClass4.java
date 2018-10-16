package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:06.
 */
public class CifClass4 extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_CLASS_4.CLASS_4 鐢ㄦ埛鑷畾涔夌殑鍒嗙被浠ｇ爜
     */
    @TablePk(index = 1)
    private String class4;

    /**
     * This field is CIF_CLASS_4.CLASS_4_DESC 瀵瑰垎绫讳唬鐮佺殑鎻忚堪
     */
    private String class4Desc;

    /**
     * This field is CIF_CLASS_4.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_CLASS_4.CLASS_4 鐢ㄦ埛鑷畾涔夌殑鍒嗙被浠ｇ爜
     */
    public String getClass4() {
        return class4;
    }

    /**
     * @param class4 the value for CIF_CLASS_4.CLASS_4 鐢ㄦ埛鑷畾涔夌殑鍒嗙被浠ｇ爜
     */
    public void setClass4(String class4) {
        this.class4 = class4 == null ? null : class4.trim();
    }

    /**
     * @return the value of  CIF_CLASS_4.CLASS_4_DESC 瀵瑰垎绫讳唬鐮佺殑鎻忚堪
     */
    public String getClass4Desc() {
        return class4Desc;
    }

    /**
     * @param class4Desc the value for CIF_CLASS_4.CLASS_4_DESC 瀵瑰垎绫讳唬鐮佺殑鎻忚堪
     */
    public void setClass4Desc(String class4Desc) {
        this.class4Desc = class4Desc == null ? null : class4Desc.trim();
    }

    /**
     * @return the value of  CIF_CLASS_4.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_CLASS_4.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}