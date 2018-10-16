package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:07.
 */
public class CifClass5 extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_CLASS_5.CLASS_5 鐢ㄦ埛鑷畾涔夌殑鍒嗙被浠ｇ爜
     */
    @TablePk(index = 1)
    private String class5;

    /**
     * This field is CIF_CLASS_5.CLASS_5_DESC 瀵瑰垎绫讳唬鐮佺殑鎻忚堪
     */
    private String class5Desc;

    /**
     * This field is CIF_CLASS_5.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_CLASS_5.CLASS_5 鐢ㄦ埛鑷畾涔夌殑鍒嗙被浠ｇ爜
     */
    public String getClass5() {
        return class5;
    }

    /**
     * @param class5 the value for CIF_CLASS_5.CLASS_5 鐢ㄦ埛鑷畾涔夌殑鍒嗙被浠ｇ爜
     */
    public void setClass5(String class5) {
        this.class5 = class5 == null ? null : class5.trim();
    }

    /**
     * @return the value of  CIF_CLASS_5.CLASS_5_DESC 瀵瑰垎绫讳唬鐮佺殑鎻忚堪
     */
    public String getClass5Desc() {
        return class5Desc;
    }

    /**
     * @param class5Desc the value for CIF_CLASS_5.CLASS_5_DESC 瀵瑰垎绫讳唬鐮佺殑鎻忚堪
     */
    public void setClass5Desc(String class5Desc) {
        this.class5Desc = class5Desc == null ? null : class5Desc.trim();
    }

    /**
     * @return the value of  CIF_CLASS_5.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_CLASS_5.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}