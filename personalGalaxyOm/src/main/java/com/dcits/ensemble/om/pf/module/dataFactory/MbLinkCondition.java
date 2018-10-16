package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/05/05 15:18:13.
 */
public class MbLinkCondition extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is MB_LINK_CONDITION.CONDITION_ID 链接条件规则ID
     */
    @TablePk(index = 1)
    private String conditionId;

    /**
     * This field is MB_LINK_CONDITION.CONDITION_DESC 规则描述
     */
    private String conditionDesc;

    /**
     * This field is MB_LINK_CONDITION.CONDITION_RULE 链接条件规则 OPEN：开户 CLOSE：销户 BAL：余额变化 DUE：到期
     */
    private String conditionRule;

    /**
     * This field is MB_LINK_CONDITION.STATUS 状态 A：有效  F：无效
     */
    private String status;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * This field is MB_ATTR_CLASS.company 所属法人
     */
    private String company;
    /**
     * @return the value of  MB_LINK_CONDITION.CONDITION_ID 链接条件规则ID
     */
    public String getConditionId() {
        return conditionId;
    }

    /**
     * @param conditionId the value for MB_LINK_CONDITION.CONDITION_ID 链接条件规则ID
     */
    public void setConditionId(String conditionId) {
        this.conditionId = conditionId == null ? null : conditionId.trim();
    }

    /**
     * @return the value of  MB_LINK_CONDITION.CONDITION_DESC 规则描述
     */
    public String getConditionDesc() {
        return conditionDesc;
    }

    /**
     * @param conditionDesc the value for MB_LINK_CONDITION.CONDITION_DESC 规则描述
     */
    public void setConditionDesc(String conditionDesc) {
        this.conditionDesc = conditionDesc == null ? null : conditionDesc.trim();
    }

    /**
     * @return the value of  MB_LINK_CONDITION.CONDITION_RULE 链接条件规则 OPEN：开户 CLOSE：销户 BAL：余额变化 DUE：到期
     */
    public String getConditionRule() {
        return conditionRule;
    }

    /**
     * @param conditionRule the value for MB_LINK_CONDITION.CONDITION_RULE 链接条件规则 OPEN：开户 CLOSE：销户 BAL：余额变化 DUE：到期
     */
    public void setConditionRule(String conditionRule) {
        this.conditionRule = conditionRule == null ? null : conditionRule.trim();
    }

    /**
     * @return the value of  MB_LINK_CONDITION.STATUS 状态 A：有效  F：无效
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the value for MB_LINK_CONDITION.STATUS 状态 A：有效  F：无效
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}