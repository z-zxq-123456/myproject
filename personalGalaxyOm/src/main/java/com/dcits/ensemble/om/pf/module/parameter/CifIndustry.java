package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:12.
 */
public class CifIndustry extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_INDUSTRY.INDUSTRY 閫氱敤琛屼笟浠ｇ爜
     */
    @TablePk(index = 1)
    private String industry;

    /**
     * This field is CIF_INDUSTRY.INDUSTRY_DESC 璇存槑
     */
    private String industryDesc;

    /**
     * This field is CIF_INDUSTRY.PARENT_INDUSTRY 涓婄骇閫氱敤琛屼笟浠ｇ爜
     */
    private String parentIndustry;

    /**
     * This field is CIF_INDUSTRY.RISK_LEVEL 
     */
    private String riskLevel;

    /**
     * This field is CIF_INDUSTRY.INDUSTRY_LEVEL 灞傜骇
     */
    private String industryLevel;

    /**
     * This field is CIF_INDUSTRY.DETAIL_IND 
     */
    private String detailInd;

    /**
     * This field is CIF_INDUSTRY.STANDARD_IND 
     */
    private String standardInd;

    /**
     * This field is CIF_INDUSTRY.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_INDUSTRY.INDUSTRY 閫氱敤琛屼笟浠ｇ爜
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * @param industry the value for CIF_INDUSTRY.INDUSTRY 閫氱敤琛屼笟浠ｇ爜
     */
    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    /**
     * @return the value of  CIF_INDUSTRY.INDUSTRY_DESC 璇存槑
     */
    public String getIndustryDesc() {
        return industryDesc;
    }

    /**
     * @param industryDesc the value for CIF_INDUSTRY.INDUSTRY_DESC 璇存槑
     */
    public void setIndustryDesc(String industryDesc) {
        this.industryDesc = industryDesc == null ? null : industryDesc.trim();
    }

    /**
     * @return the value of  CIF_INDUSTRY.PARENT_INDUSTRY 涓婄骇閫氱敤琛屼笟浠ｇ爜
     */
    public String getParentIndustry() {
        return parentIndustry;
    }

    /**
     * @param parentIndustry the value for CIF_INDUSTRY.PARENT_INDUSTRY 涓婄骇閫氱敤琛屼笟浠ｇ爜
     */
    public void setParentIndustry(String parentIndustry) {
        this.parentIndustry = parentIndustry == null ? null : parentIndustry.trim();
    }

    /**
     * @return the value of  CIF_INDUSTRY.RISK_LEVEL 
     */
    public String getRiskLevel() {
        return riskLevel;
    }

    /**
     * @param riskLevel the value for CIF_INDUSTRY.RISK_LEVEL 
     */
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel == null ? null : riskLevel.trim();
    }

    /**
     * @return the value of  CIF_INDUSTRY.INDUSTRY_LEVEL 灞傜骇
     */
    public String getIndustryLevel() {
        return industryLevel;
    }

    /**
     * @param industryLevel the value for CIF_INDUSTRY.INDUSTRY_LEVEL 灞傜骇
     */
    public void setIndustryLevel(String industryLevel) {
        this.industryLevel = industryLevel == null ? null : industryLevel.trim();
    }

    /**
     * @return the value of  CIF_INDUSTRY.DETAIL_IND 
     */
    public String getDetailInd() {
        return detailInd;
    }

    /**
     * @param detailInd the value for CIF_INDUSTRY.DETAIL_IND 
     */
    public void setDetailInd(String detailInd) {
        this.detailInd = detailInd == null ? null : detailInd.trim();
    }

    /**
     * @return the value of  CIF_INDUSTRY.STANDARD_IND 
     */
    public String getStandardInd() {
        return standardInd;
    }

    /**
     * @param standardInd the value for CIF_INDUSTRY.STANDARD_IND 
     */
    public void setStandardInd(String standardInd) {
        this.standardInd = standardInd == null ? null : standardInd.trim();
    }

    /**
     * @return the value of  CIF_INDUSTRY.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_INDUSTRY.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}