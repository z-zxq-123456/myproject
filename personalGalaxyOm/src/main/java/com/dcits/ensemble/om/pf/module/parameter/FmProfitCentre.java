package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/03/18 18:03:01.
 */
public class FmProfitCentre extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is FM_PROFIT_CENTRE.PROFIT_CENTRE 鍒╂鼎涓績
     */
    @TablePk(index = 1)
    private String profitCentre;

    /**
     * This field is FM_PROFIT_CENTRE.PROFIT_CENTRE_DESC 涓枃璇存槑
     */
    private String profitCentreDesc;

    /**
     * This field is FM_PROFIT_CENTRE.PROFIT_CENTRE_DESC_EN 鑻辨枃璇存槑
     */
    private String profitCentreDescEn;

    /**
     * This field is FM_PROFIT_CENTRE.PROFIT_CENTRE_TYPE 
     */
    private String profitCentreType;

    /**
     * This field is FM_PROFIT_CENTRE.PROFIT_CENTRE_LEVEL 鍒╂鼎涓績绾у埆
     */
    private String profitCentreLevel;

    /**
     * This field is FM_PROFIT_CENTRE.COMPANY 
     */
    private String company;

    /**
     * @return the value of  FM_PROFIT_CENTRE.PROFIT_CENTRE 鍒╂鼎涓績
     */
    public String getProfitCentre() {
        return profitCentre;
    }

    /**
     * @param profitCentre the value for FM_PROFIT_CENTRE.PROFIT_CENTRE 鍒╂鼎涓績
     */
    public void setProfitCentre(String profitCentre) {
        this.profitCentre = profitCentre == null ? null : profitCentre.trim();
    }

    /**
     * @return the value of  FM_PROFIT_CENTRE.PROFIT_CENTRE_DESC 涓枃璇存槑
     */
    public String getProfitCentreDesc() {
        return profitCentreDesc;
    }

    /**
     * @param profitCentreDesc the value for FM_PROFIT_CENTRE.PROFIT_CENTRE_DESC 涓枃璇存槑
     */
    public void setProfitCentreDesc(String profitCentreDesc) {
        this.profitCentreDesc = profitCentreDesc == null ? null : profitCentreDesc.trim();
    }

    /**
     * @return the value of  FM_PROFIT_CENTRE.PROFIT_CENTRE_DESC_EN 鑻辨枃璇存槑
     */
    public String getProfitCentreDescEn() {
        return profitCentreDescEn;
    }

    /**
     * @param profitCentreDescEn the value for FM_PROFIT_CENTRE.PROFIT_CENTRE_DESC_EN 鑻辨枃璇存槑
     */
    public void setProfitCentreDescEn(String profitCentreDescEn) {
        this.profitCentreDescEn = profitCentreDescEn == null ? null : profitCentreDescEn.trim();
    }

    /**
     * @return the value of  FM_PROFIT_CENTRE.PROFIT_CENTRE_TYPE 
     */
    public String getProfitCentreType() {
        return profitCentreType;
    }

    /**
     * @param profitCentreType the value for FM_PROFIT_CENTRE.PROFIT_CENTRE_TYPE 
     */
    public void setProfitCentreType(String profitCentreType) {
        this.profitCentreType = profitCentreType == null ? null : profitCentreType.trim();
    }

    /**
     * @return the value of  FM_PROFIT_CENTRE.PROFIT_CENTRE_LEVEL 鍒╂鼎涓績绾у埆
     */
    public String getProfitCentreLevel() {
        return profitCentreLevel;
    }

    /**
     * @param profitCentreLevel the value for FM_PROFIT_CENTRE.PROFIT_CENTRE_LEVEL 鍒╂鼎涓績绾у埆
     */
    public void setProfitCentreLevel(String profitCentreLevel) {
        this.profitCentreLevel = profitCentreLevel == null ? null : profitCentreLevel.trim();
    }

    /**
     * @return the value of  FM_PROFIT_CENTRE.COMPANY 
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for FM_PROFIT_CENTRE.COMPANY 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}