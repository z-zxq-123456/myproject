package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

import java.math.BigDecimal;

/**
 * Created by admin on 2016/05/10 09:49:37.
 */
public class IrlProdIntPf extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is IRL_PROD_INT.PROD_TYPE 
     */
    @TablePk(index = 1)
    private String prodType;

    /**
     * This field is IRL_PROD_INT.INT_CLASS 
     */
    @TablePk(index = 2)
    private String intClass;

    /**
     * This field is IRL_PROD_INT.EVENT_TYPE 
     */
    @TablePk(index = 3)
    private String eventType;


    /**
     * This field is IRL_PROD_INT.INT_TYPE 
     */
    private String intType;

    /**
     * This field is IRL_PROD_INT.TAX_TYPE 
     */
    private String taxType;

    /**
     * This field is IRL_PROD_INT.RATE_AMT_ID 
     */
    private String rateAmtId;

    /**
     * This field is IRL_PROD_INT.INT_AMT_ID 
     */
    private String intAmtId;

    /**
     * This field is IRL_PROD_INT.RECAL_METHOD 
     */
    private String recalMethod;

    /**
     * This field is IRL_PROD_INT.COMPANY 
     */
    private String company;

    /**
     * This field is IRL_PROD_INT.INT_START 
     */
    private String intStart;

    /**
     * This field is IRL_PROD_INT.INT_DAYS_TYPE 靠档天数计算方式 
            取值   A：按存期（起息日加上存期） 
            B：实际天数（从起息日开始到计算日） C： 计提天数（从上一结息日开始到计算日）
            默认值为B
            
     */
    private String intDaysType;

    /**
     * This field is IRL_PROD_INT.INT_APPL_TYPE
     */
    private String intApplType;

    /**
     * This field is IRL_PROD_INT.ROLL_FREQ
     */
    private String rollFreq;

    /**
     * This field is IRL_PROD_INT.ROLL_DAY
     */
    private BigDecimal rollDay;

    /**
     * This field is IRL_PROD_INT.MIN_RATE
     */
    private BigDecimal minRate;

    /**
     * This field is IRL_PROD_INT.MAX_RATE
     */
    private BigDecimal maxRate;

    /**
     * This field is IRL_PROD_INT.INT_RATE_IND
     */
    private String intRateInd;

    /**
     * This field is IRL_PROD_INT.MONTHLY_BASIS
     */
    private String monthBasis;

    /**
     * This field is IRL_PROD_INT.INT_CALC_BAL 利息计算方法
            取值：AB：积数计息
            EB：分段计息
            BS：差减法计息
            
            
     */
    private String groupRuleType;

    private String splitId;

    private String splitType;

    private String ruleId;

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }



    private String intCalcBal;

    public String getSplitId() {
        return splitId;
    }

    public void setSplitId(String splitId) {
        this.splitId = splitId;
    }

    public String getSplitType() {
        return splitType;
    }

    public void setSplitType(String splitType) {
        this.splitType = splitType;
    }

    public String getGroupRuleType() {
        return groupRuleType;
    }

    public void setGroupRuleType(String groupRuleType) {
        this.groupRuleType = groupRuleType;
    }

/**
     * This field is IRL_PROD_INT.PROD_MATCH_TYPE 产品级规则匹配方式
     默认为M
     L-最大值
     M-叠加
     S-最小值

     */


    /**
     * @return the value of  IRL_PROD_INT.PROD_TYPE 
     */
    public String getProdType() {
        return prodType;
    }

    /**
     * @param prodType the value for IRL_PROD_INT.PROD_TYPE 
     */
    public void setProdType(String prodType) {
        this.prodType = prodType == null ? null : prodType.trim();
    }

    /**
     * @return the value of  IRL_PROD_INT.EVENT_TYPE 
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @param eventType the value for IRL_PROD_INT.EVENT_TYPE 
     */
    public void setEventType(String eventType) {
        this.eventType = eventType == null ? null : eventType.trim();
    }

    /**
     * @return the value of  IRL_PROD_INT.INT_TYPE 
     */
    public String getIntType() {
        return intType;
    }

    /**
     * @param intType the value for IRL_PROD_INT.INT_TYPE 
     */
    public void setIntType(String intType) {
        this.intType = intType == null ? null : intType.trim();
    }

    /**
     * @return the value of  IRL_PROD_INT.INT_CLASS 
     */
    public String getIntClass() {
        return intClass;
    }

    /**
     * @param intClass the value for IRL_PROD_INT.INT_CLASS 
     */
    public void setIntClass(String intClass) {
        this.intClass = intClass == null ? null : intClass.trim();
    }

    /**
     * @return the value of  IRL_PROD_INT.TAX_TYPE 
     */
    public String getTaxType() {
        return taxType;
    }

    /**
     * @param taxType the value for IRL_PROD_INT.TAX_TYPE 
     */
    public void setTaxType(String taxType) {
        this.taxType = taxType == null ? null : taxType.trim();
    }

    /**
     * @return the value of  IRL_PROD_INT.RATE_AMT_ID 
     */
    public String getRateAmtId() {
        return rateAmtId;
    }

    /**
     * @param rateAmtId the value for IRL_PROD_INT.RATE_AMT_ID 
     */
    public void setRateAmtId(String rateAmtId) {
        this.rateAmtId = rateAmtId == null ? null : rateAmtId.trim();
    }

    /**
     * @return the value of  IRL_PROD_INT.INT_AMT_ID 
     */
    public String getIntAmtId() {
        return intAmtId;
    }

    /**
     * @param intAmtId the value for IRL_PROD_INT.INT_AMT_ID 
     */
    public void setIntAmtId(String intAmtId) {
        this.intAmtId = intAmtId == null ? null : intAmtId.trim();
    }

    /**
     * @return the value of  IRL_PROD_INT.RECAL_METHOD 
     */
    public String getRecalMethod() {
        return recalMethod;
    }

    /**
     * @param recalMethod the value for IRL_PROD_INT.RECAL_METHOD 
     */
    public void setRecalMethod(String recalMethod) {
        this.recalMethod = recalMethod == null ? null : recalMethod.trim();
    }

    /**
     * @return the value of  IRL_PROD_INT.COMPANY 
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for IRL_PROD_INT.COMPANY 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    /**
     * @return the value of  IRL_PROD_INT.INT_START 
     */
    public String getIntStart() {
        return intStart;
    }

    /**
     * @param intStart the value for IRL_PROD_INT.INT_START 
     */
    public void setIntStart(String intStart) {
        this.intStart = intStart == null ? null : intStart.trim();
    }

    /**
     * @return the value of  IRL_PROD_INT.INT_DAYS_TYPE 靠档天数计算方式 
            取值   A：按存期（起息日加上存期） 
            B：实际天数（从起息日开始到计算日） C： 计提天数（从上一结息日开始到计算日）
            默认值为B
            
     */
    public String getIntDaysType() {
        return intDaysType;
    }

    /**
     * @param intDaysType the value for IRL_PROD_INT.INT_DAYS_TYPE 靠档天数计算方式 
            取值   A：按存期（起息日加上存期） 
            B：实际天数（从起息日开始到计算日） C： 计提天数（从上一结息日开始到计算日）
            默认值为B
            
     */
    public void setIntDaysType(String intDaysType) {
        this.intDaysType = intDaysType == null ? null : intDaysType.trim();
    }

    /**
     * @return the value of  IRL_PROD_INT.INT_CALC_BAL 利息计算方法
            取值：AB：积数计息
            EB：分段计息
            BS：差减法计息
            
            
     */
    public String getIntCalcBal() {
        return intCalcBal;
    }

    /**
     * @param intCalcBal the value for IRL_PROD_INT.INT_CALC_BAL 利息计算方法
            取值：AB：积数计息
            EB：分段计息
            BS：差减法计息
            
            
     */
    public void setIntCalcBal(String intCalcBal) {
        this.intCalcBal = intCalcBal == null ? null : intCalcBal.trim();
    }


    /**
     * @return the value of  IRL_PROD_INT.INT_APPL_TYPE
     */
    public String getIntApplType() {
        return intApplType;
    }

    /**
     * @param intApplType the value for IRL_PROD_INT.INT_APPL_TYPE
     */
    public void setIntApplType(String intApplType) {
        this.intApplType = intApplType == null ? null : intApplType.trim();
    }

    /**
     * @return the value of  IRL_PROD_INT.ROLL_FREQ
     */
    public String getRollFreq() {
        return rollFreq;
    }

    /**
     * @param rollFreq the value for IRL_PROD_INT.ROLL_FREQ
     */
    public void setRollFreq(String rollFreq) {
        this.rollFreq = rollFreq == null ? null : rollFreq.trim();
    }

    /**
     * @return the value of  IRL_PROD_INT.ROLL_DAY
     */
    public BigDecimal getRollDay() {
        return rollDay;
    }

    /**
     * @param rollDay the value for IRL_PROD_INT.ROLL_DAY
     */
    public void setRollDay(BigDecimal rollDay) {
        this.rollDay = rollDay;
    }

    /**
     * @return the value of  IRL_PROD_INT.MIN_RATE
     */
    public BigDecimal getMinRate() {
        return minRate;
    }

    /**
     * @param minRate the value for IRL_PROD_INT.MIN_RATE
     */
    public void setMinRate(BigDecimal minRate) {
        this.minRate = minRate;
    }

    /**
     * @return the value of  IRL_PROD_INT.MAX_RATE
     */
    public BigDecimal getMaxRate() {
        return maxRate;
    }

    /**
     * @param maxRate the value for IRL_PROD_INT.MAX_RATE
     */
    public void setMaxRate(BigDecimal maxRate) {
        this.maxRate = maxRate;
    }

    /**
     * @return the value of  IRL_PROD_INT.INT_RATE_IND
     */
    public String getIntRateInd() {
        return intRateInd;
    }

    /**
     * @param intRateInd the value for IRL_PROD_INT.INT_RATE_IND
     */
    public void setIntRateInd(String intRateInd) {
        this.intRateInd = intRateInd == null ? null : intRateInd.trim();
    }

    /**
     * @return the value of  IRL_PROD_INT.MONTHLY_BASIS
     */
    public String getMonthBasis() {
        return monthBasis;
    }

    public void setMonthBasis(String monthBasis) {
        this.monthBasis = monthBasis;
    }
}