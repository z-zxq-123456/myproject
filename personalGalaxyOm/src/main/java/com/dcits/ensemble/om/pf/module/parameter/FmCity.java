package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/03/18 18:02:57.
 */
public class FmCity extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is FM_CITY.CITY 鍩庡競浠ｇ爜
     */
    @TablePk(index = 1)
    private String city;

    /**
     * This field is FM_CITY.COUNTRY 鍥藉
     */
    @TablePk(index = 2)
    private String country;

    /**
     * This field is FM_CITY.STATE 鐪併€佸窞
     */
    @TablePk(index = 3)
    private String state;

    /**
     * This field is FM_CITY.CITY_TEL 鐢佃瘽鍖哄彿
     */
    private String cityTel;

    /**
     * This field is FM_CITY.CITY_DESC 鎻忚堪
     */
    private String cityDesc;

    /**
     * This field is FM_CITY.COMPANY 
     */
    private String company;

    /**
     * @return the value of  FM_CITY.COUNTRY 鍥藉
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the value for FM_CITY.COUNTRY 鍥藉
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * @return the value of  FM_CITY.STATE 鐪併€佸窞
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the value for FM_CITY.STATE 鐪併€佸窞
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * @return the value of  FM_CITY.CITY 鍩庡競浠ｇ爜
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the value for FM_CITY.CITY 鍩庡競浠ｇ爜
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * @return the value of  FM_CITY.CITY_TEL 鐢佃瘽鍖哄彿
     */
    public String getCityTel() {
        return cityTel;
    }

    /**
     * @param cityTel the value for FM_CITY.CITY_TEL 鐢佃瘽鍖哄彿
     */
    public void setCityTel(String cityTel) {
        this.cityTel = cityTel == null ? null : cityTel.trim();
    }

    /**
     * @return the value of  FM_CITY.CITY_DESC 鎻忚堪
     */
    public String getCityDesc() {
        return cityDesc;
    }

    /**
     * @param cityDesc the value for FM_CITY.CITY_DESC 鎻忚堪
     */
    public void setCityDesc(String cityDesc) {
        this.cityDesc = cityDesc == null ? null : cityDesc.trim();
    }

    /**
     * @return the value of  FM_CITY.COMPANY 
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for FM_CITY.COMPANY 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}