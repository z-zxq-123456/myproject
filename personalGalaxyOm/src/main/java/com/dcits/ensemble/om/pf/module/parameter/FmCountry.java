package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/03/18 18:02:58.
 */
public class FmCountry extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is FM_COUNTRY.COUNTRY 鍥藉
     */
    @TablePk(index = 1)
    private String country;

    /**
     * This field is FM_COUNTRY.COUNTRY_DESC 鍚嶇О
     */
    private String countryDesc;

    /**
     * This field is FM_COUNTRY.CCY 甯佺
     */
    private String ccy;

    /**
     * This field is FM_COUNTRY.COUNTRY_TEL 鍥藉鐢佃瘽鍖哄彿
     */
    private String countryTel;

    /**
     * This field is FM_COUNTRY.NCCT 
     */
    private String ncct;

    /**
     * This field is FM_COUNTRY.PSC 
     */
    private String psc;

    /**
     * This field is FM_COUNTRY.ISO_CODE ISO缂栫爜
     */
    private String isoCode;

    /**
     * This field is FM_COUNTRY.SAFE_CODE SAFE缂栫爜
     */
    private String safeCode;

    /**
     * This field is FM_COUNTRY.COMPANY 
     */
    private String company;

    /**
     * This field is FM_COUNTRY.REGION 鍦板尯
     */
    private String region;

    /**
     * @return the value of  FM_COUNTRY.COUNTRY 鍥藉
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the value for FM_COUNTRY.COUNTRY 鍥藉
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * @return the value of  FM_COUNTRY.COUNTRY_DESC 鍚嶇О
     */
    public String getCountryDesc() {
        return countryDesc;
    }

    /**
     * @param countryDesc the value for FM_COUNTRY.COUNTRY_DESC 鍚嶇О
     */
    public void setCountryDesc(String countryDesc) {
        this.countryDesc = countryDesc == null ? null : countryDesc.trim();
    }

    /**
     * @return the value of  FM_COUNTRY.CCY 甯佺
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * @param ccy the value for FM_COUNTRY.CCY 甯佺
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * @return the value of  FM_COUNTRY.COUNTRY_TEL 鍥藉鐢佃瘽鍖哄彿
     */
    public String getCountryTel() {
        return countryTel;
    }

    /**
     * @param countryTel the value for FM_COUNTRY.COUNTRY_TEL 鍥藉鐢佃瘽鍖哄彿
     */
    public void setCountryTel(String countryTel) {
        this.countryTel = countryTel == null ? null : countryTel.trim();
    }

    /**
     * @return the value of  FM_COUNTRY.NCCT 
     */
    public String getNcct() {
        return ncct;
    }

    /**
     * @param ncct the value for FM_COUNTRY.NCCT 
     */
    public void setNcct(String ncct) {
        this.ncct = ncct == null ? null : ncct.trim();
    }

    /**
     * @return the value of  FM_COUNTRY.PSC 
     */
    public String getPsc() {
        return psc;
    }

    /**
     * @param psc the value for FM_COUNTRY.PSC 
     */
    public void setPsc(String psc) {
        this.psc = psc == null ? null : psc.trim();
    }

    /**
     * @return the value of  FM_COUNTRY.ISO_CODE ISO缂栫爜
     */
    public String getIsoCode() {
        return isoCode;
    }

    /**
     * @param isoCode the value for FM_COUNTRY.ISO_CODE ISO缂栫爜
     */
    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode == null ? null : isoCode.trim();
    }

    /**
     * @return the value of  FM_COUNTRY.SAFE_CODE SAFE缂栫爜
     */
    public String getSafeCode() {
        return safeCode;
    }

    /**
     * @param safeCode the value for FM_COUNTRY.SAFE_CODE SAFE缂栫爜
     */
    public void setSafeCode(String safeCode) {
        this.safeCode = safeCode == null ? null : safeCode.trim();
    }

    /**
     * @return the value of  FM_COUNTRY.COMPANY 
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for FM_COUNTRY.COMPANY 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    /**
     * @return the value of  FM_COUNTRY.REGION 鍦板尯
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region the value for FM_COUNTRY.REGION 鍦板尯
     */
    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }
}