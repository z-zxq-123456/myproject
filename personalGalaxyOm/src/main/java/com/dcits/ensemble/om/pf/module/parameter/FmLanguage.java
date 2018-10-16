package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/03/18 18:03:00.
 */
public class FmLanguage extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is FM_LANGUAGE.LANGUAGE_CODE 鐢ㄦ埛璇█ E:涓枃  C:涓枃
     */
    @TablePk(index = 1)
    private String languageCode;

    /**
     * This field is FM_LANGUAGE.LANGUAGE_DESC 浣欐埛璇█鎻忚堪
     */
    private String languageDesc;

    /**
     * This field is FM_LANGUAGE.COMPANY 
     */
    private String company;

    /**
     * This field is FM_LANGUAGE.CHAR_VALUE 
     */
    private String charValue;

    /**
     * @return the value of  FM_LANGUAGE.LANGUAGE_CODE 鐢ㄦ埛璇█ E:涓枃  C:涓枃
     */
    public String getLanguageCode() {
        return languageCode;
    }

    /**
     * @param languageCode the value for FM_LANGUAGE.LANGUAGE_CODE 鐢ㄦ埛璇█ E:涓枃  C:涓枃
     */
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode == null ? null : languageCode.trim();
    }

    /**
     * @return the value of  FM_LANGUAGE.LANGUAGE_DESC 浣欐埛璇█鎻忚堪
     */
    public String getLanguageDesc() {
        return languageDesc;
    }

    /**
     * @param languageDesc the value for FM_LANGUAGE.LANGUAGE_DESC 浣欐埛璇█鎻忚堪
     */
    public void setLanguageDesc(String languageDesc) {
        this.languageDesc = languageDesc == null ? null : languageDesc.trim();
    }

    /**
     * @return the value of  FM_LANGUAGE.COMPANY 
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for FM_LANGUAGE.COMPANY 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    /**
     * @return the value of  FM_LANGUAGE.CHAR_VALUE 
     */
    public String getCharValue() {
        return charValue;
    }

    /**
     * @param charValue the value for FM_LANGUAGE.CHAR_VALUE 
     */
    public void setCharValue(String charValue) {
        this.charValue = charValue == null ? null : charValue.trim();
    }
}