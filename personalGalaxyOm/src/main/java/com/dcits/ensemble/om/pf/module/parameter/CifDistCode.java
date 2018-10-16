package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:10.
 */
public class CifDistCode extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_DIST_CODE.CITY 鍩庡競
     */
    @TablePk(index = 1)
    private String city;

    /**
     * This field is CIF_DIST_CODE.DIST_CODE 鍦板尯浠ｇ爜
     */
    @TablePk(index = 2)
    private String distCode;

    /**
     * This field is CIF_DIST_CODE.DIST_NAME 鍚嶇О
     */
    @TablePk(index = 3)
    private String distName;

    /**
     * This field is CIF_DIST_CODE.PROVINCE 
     */
    @TablePk(index = 4)
    private String province;

    /**
     * This field is CIF_DIST_CODE.DIST_GRADE 绾у埆
     */
    private String distGrade;

    /**
     * This field is CIF_DIST_CODE.STATE 
     */
    private String state;

    /**
     * This field is CIF_DIST_CODE.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_DIST_CODE.DIST_CODE 鍦板尯浠ｇ爜
     */
    public String getDistCode() {
        return distCode;
    }

    /**
     * @param distCode the value for CIF_DIST_CODE.DIST_CODE 鍦板尯浠ｇ爜
     */
    public void setDistCode(String distCode) {
        this.distCode = distCode == null ? null : distCode.trim();
    }

    /**
     * @return the value of  CIF_DIST_CODE.DIST_NAME 鍚嶇О
     */
    public String getDistName() {
        return distName;
    }

    /**
     * @param distName the value for CIF_DIST_CODE.DIST_NAME 鍚嶇О
     */
    public void setDistName(String distName) {
        this.distName = distName == null ? null : distName.trim();
    }

    /**
     * @return the value of  CIF_DIST_CODE.DIST_GRADE 绾у埆
     */
    public String getDistGrade() {
        return distGrade;
    }

    /**
     * @param distGrade the value for CIF_DIST_CODE.DIST_GRADE 绾у埆
     */
    public void setDistGrade(String distGrade) {
        this.distGrade = distGrade == null ? null : distGrade.trim();
    }

    /**
     * @return the value of  CIF_DIST_CODE.CITY 鍩庡競
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the value for CIF_DIST_CODE.CITY 鍩庡競
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * @return the value of  CIF_DIST_CODE.PROVINCE 
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province the value for CIF_DIST_CODE.PROVINCE 
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * @return the value of  CIF_DIST_CODE.STATE 
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the value for CIF_DIST_CODE.STATE 
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * @return the value of  CIF_DIST_CODE.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_DIST_CODE.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}