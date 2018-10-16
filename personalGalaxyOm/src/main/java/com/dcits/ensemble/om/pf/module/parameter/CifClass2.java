package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:05.
 */
public class CifClass2 extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_CLASS_2.CLASS_2 鐢ㄦ埛鑷畾涔夌殑鍒嗙被浠ｇ爜
     */
    @TablePk(index = 1)
    private String class2;

    /**
     * This field is CIF_CLASS_2.CLASS_2_DESC 瀵瑰垎绫讳唬鐮佺殑鎻忚堪
     */
    private String class2Desc;

    /**
     * This field is CIF_CLASS_2.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_CLASS_2.CLASS_2 鐢ㄦ埛鑷畾涔夌殑鍒嗙被浠ｇ爜
     */
    public String getClass2() {
        return class2;
    }

    /**
     * @param class2 the value for CIF_CLASS_2.CLASS_2 鐢ㄦ埛鑷畾涔夌殑鍒嗙被浠ｇ爜
     */
    public void setClass2(String class2) {
        this.class2 = class2 == null ? null : class2.trim();
    }

    /**
     * @return the value of  CIF_CLASS_2.CLASS_2_DESC 瀵瑰垎绫讳唬鐮佺殑鎻忚堪
     */
    public String getClass2Desc() {
        return class2Desc;
    }

    /**
     * @param class2Desc the value for CIF_CLASS_2.CLASS_2_DESC 瀵瑰垎绫讳唬鐮佺殑鎻忚堪
     */
    public void setClass2Desc(String class2Desc) {
        this.class2Desc = class2Desc == null ? null : class2Desc.trim();
    }

    /**
     * @return the value of  CIF_CLASS_2.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_CLASS_2.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}