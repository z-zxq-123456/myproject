package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:06.
 */
public class CifClass3 extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_CLASS_3.CLASS_3 鐢ㄦ埛鑷畾涔夌殑鍒嗙被浠ｇ爜
     */
    @TablePk(index = 1)
    private String class3;

    /**
     * This field is CIF_CLASS_3.CLASS_3_DESC 瀵瑰垎绫讳唬鐮佺殑鎻忚堪
     */
    private String class3Desc;

    /**
     * This field is CIF_CLASS_3.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_CLASS_3.CLASS_3 鐢ㄦ埛鑷畾涔夌殑鍒嗙被浠ｇ爜
     */
    public String getClass3() {
        return class3;
    }

    /**
     * @param class3 the value for CIF_CLASS_3.CLASS_3 鐢ㄦ埛鑷畾涔夌殑鍒嗙被浠ｇ爜
     */
    public void setClass3(String class3) {
        this.class3 = class3 == null ? null : class3.trim();
    }

    /**
     * @return the value of  CIF_CLASS_3.CLASS_3_DESC 瀵瑰垎绫讳唬鐮佺殑鎻忚堪
     */
    public String getClass3Desc() {
        return class3Desc;
    }

    /**
     * @param class3Desc the value for CIF_CLASS_3.CLASS_3_DESC 瀵瑰垎绫讳唬鐮佺殑鎻忚堪
     */
    public void setClass3Desc(String class3Desc) {
        this.class3Desc = class3Desc == null ? null : class3Desc.trim();
    }

    /**
     * @return the value of  CIF_CLASS_3.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_CLASS_3.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}