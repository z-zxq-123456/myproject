package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:14.
 */
public class CifOccupation extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_OCCUPATION.OCCUPATION_CODE 鑱屼笟绫诲埆
     */
    @TablePk(index = 1)
    private String occupationCode;

    /**
     * This field is CIF_OCCUPATION.OCCUPATION_DESC 鑱屼笟璇存槑
     */
    private String occupationDesc;

    /**
     * This field is CIF_OCCUPATION.RISK_LEVEL 
     */
    private String riskLevel;

    /**
     * This field is CIF_OCCUPATION.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_OCCUPATION.OCCUPATION_CODE 鑱屼笟绫诲埆
     */
    public String getOccupationCode() {
        return occupationCode;
    }

    /**
     * @param occupationCode the value for CIF_OCCUPATION.OCCUPATION_CODE 鑱屼笟绫诲埆
     */
    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode == null ? null : occupationCode.trim();
    }

    /**
     * @return the value of  CIF_OCCUPATION.OCCUPATION_DESC 鑱屼笟璇存槑
     */
    public String getOccupationDesc() {
        return occupationDesc;
    }

    /**
     * @param occupationDesc the value for CIF_OCCUPATION.OCCUPATION_DESC 鑱屼笟璇存槑
     */
    public void setOccupationDesc(String occupationDesc) {
        this.occupationDesc = occupationDesc == null ? null : occupationDesc.trim();
    }

    /**
     * @return the value of  CIF_OCCUPATION.RISK_LEVEL 
     */
    public String getRiskLevel() {
        return riskLevel;
    }

    /**
     * @param riskLevel the value for CIF_OCCUPATION.RISK_LEVEL 
     */
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel == null ? null : riskLevel.trim();
    }

    /**
     * @return the value of  CIF_OCCUPATION.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_OCCUPATION.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}