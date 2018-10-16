package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;

/**
 * Created by admin on 2016/03/09 16:09:11.
 */
public class FmParameter extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is FM_PARAMETER.PARA_KEY 
     */
    private String paraKey;

    /**
     * This field is FM_PARAMETER.PARA_VALUE 
     */
    private String paraValue;

    /**
     * This field is FM_PARAMETER.PARA_DESC 
     */
    private String paraDesc;

    /**
     * This field is FM_PARAMETER.COMPANY 
     */
    private String company;

    /**
     * @return the value of  FM_PARAMETER.PARA_KEY 
     */
    public String getParaKey() {
        return paraKey;
    }

    /**
     * @param paraKey the value for FM_PARAMETER.PARA_KEY 
     */
    public void setParaKey(String paraKey) {
        this.paraKey = paraKey == null ? null : paraKey.trim();
    }

    /**
     * @return the value of  FM_PARAMETER.PARA_VALUE 
     */
    public String getParaValue() {
        return paraValue;
    }

    /**
     * @param paraValue the value for FM_PARAMETER.PARA_VALUE 
     */
    public void setParaValue(String paraValue) {
        this.paraValue = paraValue == null ? null : paraValue.trim();
    }

    /**
     * @return the value of  FM_PARAMETER.PARA_DESC 
     */
    public String getParaDesc() {
        return paraDesc;
    }

    /**
     * @param paraDesc the value for FM_PARAMETER.PARA_DESC 
     */
    public void setParaDesc(String paraDesc) {
        this.paraDesc = paraDesc == null ? null : paraDesc.trim();
    }

    /**
     * @return the value of  FM_PARAMETER.COMPANY 
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for FM_PARAMETER.COMPANY 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}