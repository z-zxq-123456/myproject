package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/08/25 10:35:58.
 */
public class IrlFeeMappingPf extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is IRL_FEE_MAPPING.IRL_SEQ_NO 费用代码
     */
    @TablePk(index = 1)
    private String irlSeqNo;

    /**
     * This field is IRL_FEE_MAPPING.FEE_TYPE 费率类型
     */
    private String feeType;

    /**
     * This field is IRL_FEE_MAPPING.BRANCH_RULE 机构代码
     */
    private String branchRule;

    /**
     * This field is IRL_FEE_MAPPING.EVENT_TYPE_RULE 事件类型
     */
    private String eventTypeRule;

    /**
     * This field is IRL_FEE_MAPPING.TRAN_TYPE_RULE 交易类型
     */
    private String tranTypeRule;

    /**
     * This field is IRL_FEE_MAPPING.PROD_GROUP_RULE 产品组
     比如：
     RB：负债
     CL：资产
     GL：总账
     MM：货币市场
     ALL-

     */
    private String prodGroupRule;

    /**
     * This field is IRL_FEE_MAPPING.PROD_TYPE_RULE 产品类型
     */
    private String prodTypeRule;

    /**
     * This field is IRL_FEE_MAPPING.URGENT_FLAG_RULE 加急标志
     Y-加急、
     N-不加急、
     NA-通配
     ALL

     */
    private String urgentFlagRule;

    /**
     * This field is IRL_FEE_MAPPING.IS_LOCAL_RULE 跨行标志
     Y-同行、
     N-跨行、
     NA-通配
     ALL

     */
    private String isLocalRule;

    /**
     * This field is IRL_FEE_MAPPING.AREA_RULE 地区
     LC–同城
     DP–异地
     OS–境外
     NA–通配
     ALL

     */
    private String areaRule;

    /**
     * This field is IRL_FEE_MAPPING.CCY_RULE 币种
     */
    private String ccyRule;

    /**
     * This field is IRL_FEE_MAPPING.CLIENT_TYPE_RULE 客户类型
     */
    private String clientTypeRule;

    /**
     * This field is IRL_FEE_MAPPING.CATEGORY_TYPE_RULE 客户类型细类
     */
    private String categoryTypeRule;

    /**
     * This field is IRL_FEE_MAPPING.SOURCE_TYPE_RULE 渠道类型
     */
    private String sourceTypeRule;

    /**
     * This field is IRL_FEE_MAPPING.DOC_TYPE_RULE 凭证类型
     */
    private String docTypeRule;

    /**
     * This field is IRL_FEE_MAPPING.OLD_STATUS_RULE 凭证/票据变更前状态
     */
    private String oldStatusRule;

    /**
     * This field is IRL_FEE_MAPPING.NEW_STATUS_RULE 凭证/票据变更后状态
     */
    private String newStatusRule;

    /**
     * This field is IRL_FEE_MAPPING.IS_RULE 是否使用规则
     Y-使用、
     N-不使用

     */
    private String isRule;

    /**
     * This field is IRL_FEE_MAPPING.COMPANY_RULE 所属法人
     */
    private String companyRule;

    /**
     * This field is IRL_FEE_MAPPING.SERVICE_ID_RULE 服务代码
     */
    private String serviceIdRule;

    /**
     * @return the value of  IRL_FEE_MAPPING.IRL_SEQ_NO 费用代码
     */
    public String getIrlSeqNo() {
        return irlSeqNo;
    }

    /**
     * @param irlSeqNo the value for IRL_FEE_MAPPING.IRL_SEQ_NO 费用代码
     */
    public void setIrlSeqNo(String irlSeqNo) {
        this.irlSeqNo = irlSeqNo == null ? null : irlSeqNo.trim();
    }

    /**
     * @return the value of  IRL_FEE_MAPPING.FEE_TYPE 费率类型
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * @param feeType the value for IRL_FEE_MAPPING.FEE_TYPE 费率类型
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
    }

    /**
     * @return the value of  IRL_FEE_MAPPING.BRANCH_RULE 机构代码
     */
    public String getBranchRule() {
        return branchRule;
    }

    /**
     * @param branchRule the value for IRL_FEE_MAPPING.BRANCH_RULE 机构代码
     */
    public void setBranchRule(String branchRule) {
        this.branchRule = branchRule == null ? null : branchRule.trim();
    }

    /**
     * @return the value of  IRL_FEE_MAPPING.EVENT_TYPE_RULE 事件类型
     */
    public String getEventTypeRule() {
        return eventTypeRule;
    }

    /**
     * @param eventTypeRule the value for IRL_FEE_MAPPING.EVENT_TYPE_RULE 事件类型
     */
    public void setEventTypeRule(String eventTypeRule) {
        this.eventTypeRule = eventTypeRule == null ? null : eventTypeRule.trim();
    }

    /**
     * @return the value of  IRL_FEE_MAPPING.TRAN_TYPE_RULE 交易类型
     */
    public String getTranTypeRule() {
        return tranTypeRule;
    }

    /**
     * @param tranTypeRule the value for IRL_FEE_MAPPING.TRAN_TYPE_RULE 交易类型
     */
    public void setTranTypeRule(String tranTypeRule) {
        this.tranTypeRule = tranTypeRule == null ? null : tranTypeRule.trim();
    }

    /**
     * @return the value of  IRL_FEE_MAPPING.PROD_GROUP_RULE 产品组
    比如：
    RB：负债
    CL：资产
    GL：总账
    MM：货币市场
    ALL-

     */
    public String getProdGroupRule() {
        return prodGroupRule;
    }

    /**
     * @param prodGroupRule the value for IRL_FEE_MAPPING.PROD_GROUP_RULE 产品组
    比如：
    RB：负债
    CL：资产
    GL：总账
    MM：货币市场
    ALL-

     */
    public void setProdGroupRule(String prodGroupRule) {
        this.prodGroupRule = prodGroupRule == null ? null : prodGroupRule.trim();
    }

    /**
     * @return the value of  IRL_FEE_MAPPING.PROD_TYPE_RULE 产品类型
     */
    public String getProdTypeRule() {
        return prodTypeRule;
    }

    /**
     * @param prodTypeRule the value for IRL_FEE_MAPPING.PROD_TYPE_RULE 产品类型
     */
    public void setProdTypeRule(String prodTypeRule) {
        this.prodTypeRule = prodTypeRule == null ? null : prodTypeRule.trim();
    }

    /**
     * @return the value of  IRL_FEE_MAPPING.URGENT_FLAG_RULE 加急标志
    Y-加急、
    N-不加急、
    NA-通配
    ALL

     */
    public String getUrgentFlagRule() {
        return urgentFlagRule;
    }

    /**
     * @param urgentFlagRule the value for IRL_FEE_MAPPING.URGENT_FLAG_RULE 加急标志
    Y-加急、
    N-不加急、
    NA-通配
    ALL

     */
    public void setUrgentFlagRule(String urgentFlagRule) {
        this.urgentFlagRule = urgentFlagRule == null ? null : urgentFlagRule.trim();
    }

    /**
     * @return the value of  IRL_FEE_MAPPING.IS_LOCAL_RULE 跨行标志
    Y-同行、
    N-跨行、
    NA-通配
    ALL

     */
    public String getIsLocalRule() {
        return isLocalRule;
    }

    /**
     * @param isLocalRule the value for IRL_FEE_MAPPING.IS_LOCAL_RULE 跨行标志
    Y-同行、
    N-跨行、
    NA-通配
    ALL

     */
    public void setIsLocalRule(String isLocalRule) {
        this.isLocalRule = isLocalRule == null ? null : isLocalRule.trim();
    }

    /**
     * @return the value of  IRL_FEE_MAPPING.AREA_RULE 地区
    LC–同城
    DP–异地
    OS–境外
    NA–通配
    ALL

     */
    public String getAreaRule() {
        return areaRule;
    }

    /**
     * @param areaRule the value for IRL_FEE_MAPPING.AREA_RULE 地区
    LC–同城
    DP–异地
    OS–境外
    NA–通配
    ALL

     */
    public void setAreaRule(String areaRule) {
        this.areaRule = areaRule == null ? null : areaRule.trim();
    }

    /**
     * @return the value of  IRL_FEE_MAPPING.CCY_RULE 币种
     */
    public String getCcyRule() {
        return ccyRule;
    }

    /**
     * @param ccyRule the value for IRL_FEE_MAPPING.CCY_RULE 币种
     */
    public void setCcyRule(String ccyRule) {
        this.ccyRule = ccyRule == null ? null : ccyRule.trim();
    }

    /**
     * @return the value of  IRL_FEE_MAPPING.CLIENT_TYPE_RULE 客户类型
     */
    public String getClientTypeRule() {
        return clientTypeRule;
    }

    /**
     * @param clientTypeRule the value for IRL_FEE_MAPPING.CLIENT_TYPE_RULE 客户类型
     */
    public void setClientTypeRule(String clientTypeRule) {
        this.clientTypeRule = clientTypeRule == null ? null : clientTypeRule.trim();
    }

    /**
     * @return the value of  IRL_FEE_MAPPING.CATEGORY_TYPE_RULE 客户类型细类
     */
    public String getCategoryTypeRule() {
        return categoryTypeRule;
    }

    /**
     * @param categoryTypeRule the value for IRL_FEE_MAPPING.CATEGORY_TYPE_RULE 客户类型细类
     */
    public void setCategoryTypeRule(String categoryTypeRule) {
        this.categoryTypeRule = categoryTypeRule == null ? null : categoryTypeRule.trim();
    }

    /**
     * @return the value of  IRL_FEE_MAPPING.SOURCE_TYPE_RULE 渠道类型
     */
    public String getSourceTypeRule() {
        return sourceTypeRule;
    }

    /**
     * @param sourceTypeRule the value for IRL_FEE_MAPPING.SOURCE_TYPE_RULE 渠道类型
     */
    public void setSourceTypeRule(String sourceTypeRule) {
        this.sourceTypeRule = sourceTypeRule == null ? null : sourceTypeRule.trim();
    }

    /**
     * @return the value of  IRL_FEE_MAPPING.DOC_TYPE_RULE 凭证类型
     */
    public String getDocTypeRule() {
        return docTypeRule;
    }

    /**
     * @param docTypeRule the value for IRL_FEE_MAPPING.DOC_TYPE_RULE 凭证类型
     */
    public void setDocTypeRule(String docTypeRule) {
        this.docTypeRule = docTypeRule == null ? null : docTypeRule.trim();
    }

    /**
     * @return the value of  IRL_FEE_MAPPING.OLD_STATUS_RULE 凭证/票据变更前状态
     */
    public String getOldStatusRule() {
        return oldStatusRule;
    }

    /**
     * @param oldStatusRule the value for IRL_FEE_MAPPING.OLD_STATUS_RULE 凭证/票据变更前状态
     */
    public void setOldStatusRule(String oldStatusRule) {
        this.oldStatusRule = oldStatusRule == null ? null : oldStatusRule.trim();
    }

    /**
     * @return the value of  IRL_FEE_MAPPING.NEW_STATUS_RULE 凭证/票据变更后状态
     */
    public String getNewStatusRule() {
        return newStatusRule;
    }

    /**
     * @param newStatusRule the value for IRL_FEE_MAPPING.NEW_STATUS_RULE 凭证/票据变更后状态
     */
    public void setNewStatusRule(String newStatusRule) {
        this.newStatusRule = newStatusRule == null ? null : newStatusRule.trim();
    }

    /**
     * @return the value of  IRL_FEE_MAPPING.IS_RULE 是否使用规则
    Y-使用、
    N-不使用

     */
    public String getIsRule() {
        return isRule;
    }

    /**
     * @param isRule the value for IRL_FEE_MAPPING.IS_RULE 是否使用规则
    Y-使用、
    N-不使用

     */
    public void setIsRule(String isRule) {
        this.isRule = isRule == null ? null : isRule.trim();
    }

    /**
     * @return the value of  IRL_FEE_MAPPING.COMPANY_RULE 所属法人
     */
    public String getCompanyRule() {
        return companyRule;
    }

    /**
     * @param companyRule the value for IRL_FEE_MAPPING.COMPANY_RULE 所属法人
     */
    public void setCompanyRule(String companyRule) {
        this.companyRule = companyRule == null ? null : companyRule.trim();
    }

    /**
     * @return the value of  IRL_FEE_MAPPING.SERVICE_ID_RULE 服务代码
     */
    public String getServiceIdRule() {
        return serviceIdRule;
    }

    /**
     * @param serviceIdRule the value for IRL_FEE_MAPPING.SERVICE_ID_RULE 服务代码
     */
    public void setServiceIdRule(String serviceIdRule) {
        this.serviceIdRule = serviceIdRule == null ? null : serviceIdRule.trim();
    }
}