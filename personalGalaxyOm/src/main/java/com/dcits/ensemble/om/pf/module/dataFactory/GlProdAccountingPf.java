package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/08/25 10:35:58.
 */
public class GlProdAccountingPf extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is GL_PROD_ACCOUNTING.ACCOUNTING_STATUS 核算状态
     */
    @TablePk(index = 2)
    private String accountingStatus;

    /**
     * This field is GL_PROD_ACCOUNTING.PROD_TYPE 产品类型
     */
    @TablePk(index = 1)
    private String prodType;

    /**
     * This field is GL_PROD_ACCOUNTING.PROFIT_CENTRE 利润中心
     */
    private String profitCentre;

    /**
     * This field is GL_PROD_ACCOUNTING.BUSINESS_UNIT 账套
     */
    private String businessUnit;

    /**
     * This field is GL_PROD_ACCOUNTING.GL_CODE_A 资产科目代码
     */
    private String glCodeA;

    /**
     * This field is GL_PROD_ACCOUNTING.GL_CODE_L 负债科目代码
     */
    private String glCodeL;

    /**
     * This field is GL_PROD_ACCOUNTING.GL_CODE_INT_E 利息支出科目代码
     */
    private String glCodeIntE;

    /**
     * This field is GL_PROD_ACCOUNTING.GL_CODE_INT_PAY 应付利息科目代码
     */
    private String glCodeIntPay;

    /**
     * This field is GL_PROD_ACCOUNTING.GL_CODE_INT_I 利息收入科目代码
     */
    private String glCodeIntI;

    /**
     * This field is GL_PROD_ACCOUNTING.GL_CODE_INT_REC 应收利息科目代码
     */
    private String glCodeIntRec;

    /**
     * This field is GL_PROD_ACCOUNTING.GL_CODE_INT_ACR 应计利息科目代码
     */
    private String glCodeIntAcr;

    /**
     * This field is GL_PROD_ACCOUNTING.GL_CODE_ODP_I 罚息收入科目代码
     */
    private String glCodeOdpI;

    /**
     * This field is GL_PROD_ACCOUNTING.GL_CODE_ODP_REC 应收罚息科目代码
     */
    private String glCodeOdpRec;

    /**
     * This field is GL_PROD_ACCOUNTING.GL_CODE_ODP_ACR 应计罚息科目代码
     */
    private String glCodeOdpAcr;

    /**
     * This field is GL_PROD_ACCOUNTING.GL_CODE_ODI_I 复利收入科目代码
     */
    private String glCodeOdiI;

    /**
     * This field is GL_PROD_ACCOUNTING.GL_CODE_ODI_REC 应收复利科目代码
     */
    private String glCodeOdiRec;

    /**
     * This field is GL_PROD_ACCOUNTING.GL_CODE_ODI_ACR 应计复利科目代码
     */
    private String glCodeOdiAcr;

    /**
     * This field is GL_PROD_ACCOUNTING.GL_CODE_A_LOSS 损失科目代码
     */
    private String glCodeALoss;

    /**
     * This field is GL_PROD_ACCOUNTING.GL_CODE_ADJUST 调整科目代码
     */
    private String glCodeAdjust;

    /**
     * @return the value of  GL_PROD_ACCOUNTING.PROD_TYPE 产品类型
     */
    public String getProdType() {
        return prodType;
    }

    /**
     * @param prodType the value for GL_PROD_ACCOUNTING.PROD_TYPE 产品类型
     */
    public void setProdType(String prodType) {
        this.prodType = prodType == null ? null : prodType.trim();
    }

    /**
     * @return the value of  GL_PROD_ACCOUNTING.ACCOUNTING_STATUS 核算状态
     */
    public String getAccountingStatus() {
        return accountingStatus;
    }

    /**
     * @param accountingStatus the value for GL_PROD_ACCOUNTING.ACCOUNTING_STATUS 核算状态
     */
    public void setAccountingStatus(String accountingStatus) {
        this.accountingStatus = accountingStatus == null ? null : accountingStatus.trim();
    }

    /**
     * @return the value of  GL_PROD_ACCOUNTING.PROFIT_CENTRE 利润中心
     */
    public String getProfitCentre() {
        return profitCentre;
    }

    /**
     * @param profitCentre the value for GL_PROD_ACCOUNTING.PROFIT_CENTRE 利润中心
     */
    public void setProfitCentre(String profitCentre) {
        this.profitCentre = profitCentre == null ? null : profitCentre.trim();
    }

    /**
     * @return the value of  GL_PROD_ACCOUNTING.BUSINESS_UNIT 账套
     */
    public String getBusinessUnit() {
        return businessUnit;
    }

    /**
     * @param businessUnit the value for GL_PROD_ACCOUNTING.BUSINESS_UNIT 账套
     */
    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit == null ? null : businessUnit.trim();
    }

    /**
     * @return the value of  GL_PROD_ACCOUNTING.GL_CODE_A 资产科目代码
     */
    public String getGlCodeA() {
        return glCodeA;
    }

    /**
     * @param glCodeA the value for GL_PROD_ACCOUNTING.GL_CODE_A 资产科目代码
     */
    public void setGlCodeA(String glCodeA) {
        this.glCodeA = glCodeA == null ? null : glCodeA.trim();
    }

    /**
     * @return the value of  GL_PROD_ACCOUNTING.GL_CODE_L 负债科目代码
     */
    public String getGlCodeL() {
        return glCodeL;
    }

    /**
     * @param glCodeL the value for GL_PROD_ACCOUNTING.GL_CODE_L 负债科目代码
     */
    public void setGlCodeL(String glCodeL) {
        this.glCodeL = glCodeL == null ? null : glCodeL.trim();
    }

    /**
     * @return the value of  GL_PROD_ACCOUNTING.GL_CODE_INT_E 利息支出科目代码
     */
    public String getGlCodeIntE() {
        return glCodeIntE;
    }

    /**
     * @param glCodeIntE the value for GL_PROD_ACCOUNTING.GL_CODE_INT_E 利息支出科目代码
     */
    public void setGlCodeIntE(String glCodeIntE) {
        this.glCodeIntE = glCodeIntE == null ? null : glCodeIntE.trim();
    }

    /**
     * @return the value of  GL_PROD_ACCOUNTING.GL_CODE_INT_PAY 应付利息科目代码
     */
    public String getGlCodeIntPay() {
        return glCodeIntPay;
    }

    /**
     * @param glCodeIntPay the value for GL_PROD_ACCOUNTING.GL_CODE_INT_PAY 应付利息科目代码
     */
    public void setGlCodeIntPay(String glCodeIntPay) {
        this.glCodeIntPay = glCodeIntPay == null ? null : glCodeIntPay.trim();
    }

    /**
     * @return the value of  GL_PROD_ACCOUNTING.GL_CODE_INT_I 利息收入科目代码
     */
    public String getGlCodeIntI() {
        return glCodeIntI;
    }

    /**
     * @param glCodeIntI the value for GL_PROD_ACCOUNTING.GL_CODE_INT_I 利息收入科目代码
     */
    public void setGlCodeIntI(String glCodeIntI) {
        this.glCodeIntI = glCodeIntI == null ? null : glCodeIntI.trim();
    }

    /**
     * @return the value of  GL_PROD_ACCOUNTING.GL_CODE_INT_REC 应收利息科目代码
     */
    public String getGlCodeIntRec() {
        return glCodeIntRec;
    }

    /**
     * @param glCodeIntRec the value for GL_PROD_ACCOUNTING.GL_CODE_INT_REC 应收利息科目代码
     */
    public void setGlCodeIntRec(String glCodeIntRec) {
        this.glCodeIntRec = glCodeIntRec == null ? null : glCodeIntRec.trim();
    }

    /**
     * @return the value of  GL_PROD_ACCOUNTING.GL_CODE_INT_ACR 应计利息科目代码
     */
    public String getGlCodeIntAcr() {
        return glCodeIntAcr;
    }

    /**
     * @param glCodeIntAcr the value for GL_PROD_ACCOUNTING.GL_CODE_INT_ACR 应计利息科目代码
     */
    public void setGlCodeIntAcr(String glCodeIntAcr) {
        this.glCodeIntAcr = glCodeIntAcr == null ? null : glCodeIntAcr.trim();
    }

    /**
     * @return the value of  GL_PROD_ACCOUNTING.GL_CODE_ODP_I 罚息收入科目代码
     */
    public String getGlCodeOdpI() {
        return glCodeOdpI;
    }

    /**
     * @param glCodeOdpI the value for GL_PROD_ACCOUNTING.GL_CODE_ODP_I 罚息收入科目代码
     */
    public void setGlCodeOdpI(String glCodeOdpI) {
        this.glCodeOdpI = glCodeOdpI == null ? null : glCodeOdpI.trim();
    }

    /**
     * @return the value of  GL_PROD_ACCOUNTING.GL_CODE_ODP_REC 应收罚息科目代码
     */
    public String getGlCodeOdpRec() {
        return glCodeOdpRec;
    }

    /**
     * @param glCodeOdpRec the value for GL_PROD_ACCOUNTING.GL_CODE_ODP_REC 应收罚息科目代码
     */
    public void setGlCodeOdpRec(String glCodeOdpRec) {
        this.glCodeOdpRec = glCodeOdpRec == null ? null : glCodeOdpRec.trim();
    }

    /**
     * @return the value of  GL_PROD_ACCOUNTING.GL_CODE_ODP_ACR 应计罚息科目代码
     */
    public String getGlCodeOdpAcr() {
        return glCodeOdpAcr;
    }

    /**
     * @param glCodeOdpAcr the value for GL_PROD_ACCOUNTING.GL_CODE_ODP_ACR 应计罚息科目代码
     */
    public void setGlCodeOdpAcr(String glCodeOdpAcr) {
        this.glCodeOdpAcr = glCodeOdpAcr == null ? null : glCodeOdpAcr.trim();
    }

    /**
     * @return the value of  GL_PROD_ACCOUNTING.GL_CODE_ODI_I 复利收入科目代码
     */
    public String getGlCodeOdiI() {
        return glCodeOdiI;
    }

    /**
     * @param glCodeOdiI the value for GL_PROD_ACCOUNTING.GL_CODE_ODI_I 复利收入科目代码
     */
    public void setGlCodeOdiI(String glCodeOdiI) {
        this.glCodeOdiI = glCodeOdiI == null ? null : glCodeOdiI.trim();
    }

    /**
     * @return the value of  GL_PROD_ACCOUNTING.GL_CODE_ODI_REC 应收复利科目代码
     */
    public String getGlCodeOdiRec() {
        return glCodeOdiRec;
    }

    /**
     * @param glCodeOdiRec the value for GL_PROD_ACCOUNTING.GL_CODE_ODI_REC 应收复利科目代码
     */
    public void setGlCodeOdiRec(String glCodeOdiRec) {
        this.glCodeOdiRec = glCodeOdiRec == null ? null : glCodeOdiRec.trim();
    }

    /**
     * @return the value of  GL_PROD_ACCOUNTING.GL_CODE_ODI_ACR 应计复利科目代码
     */
    public String getGlCodeOdiAcr() {
        return glCodeOdiAcr;
    }

    /**
     * @param glCodeOdiAcr the value for GL_PROD_ACCOUNTING.GL_CODE_ODI_ACR 应计复利科目代码
     */
    public void setGlCodeOdiAcr(String glCodeOdiAcr) {
        this.glCodeOdiAcr = glCodeOdiAcr == null ? null : glCodeOdiAcr.trim();
    }

    /**
     * @return the value of  GL_PROD_ACCOUNTING.GL_CODE_A_LOSS 损失科目代码
     */
    public String getGlCodeALoss() {
        return glCodeALoss;
    }

    /**
     * @param glCodeALoss the value for GL_PROD_ACCOUNTING.GL_CODE_A_LOSS 损失科目代码
     */
    public void setGlCodeALoss(String glCodeALoss) {
        this.glCodeALoss = glCodeALoss == null ? null : glCodeALoss.trim();
    }

    /**
     * @return the value of  GL_PROD_ACCOUNTING.GL_CODE_ADJUST 调整科目代码
     */
    public String getGlCodeAdjust() {
        return glCodeAdjust;
    }

    /**
     * @param glCodeAdjust the value for GL_PROD_ACCOUNTING.GL_CODE_ADJUST 调整科目代码
     */
    public void setGlCodeAdjust(String glCodeAdjust) {
        this.glCodeAdjust = glCodeAdjust == null ? null : glCodeAdjust.trim();
    }
}