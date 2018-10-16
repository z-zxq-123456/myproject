package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/03/07 19:04:51.
 */
public class MbProdRunRule extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is MB_PROD_RUN_RULE.EVENT_TYPE 事件类型
     */
    @TablePk(index = 1)
    private String eventType;

    /**
     * This field is MB_PROD_RUN_RULE.SEQ_NO 组装序号
     */
    @TablePk(index = 2)
    private Integer seqNo;

    /**
     * This field is MB_PROD_RUN_RULE.ASSEMBLE_ID 组件标示
     */
    private String assembleId;

    /**
     * This field is MB_PROD_RUN_RULE.ASSEMBLE_TYPE 
     */
    private String assembleType;

    /**
     * This field is MB_PROD_RUN_RULE.ASSEMBLE_RULE 组件角色
     */
    private String assembleRule;

    /**
     * This field is MB_PROD_RUN_RULE.ASSEMBLE_ARGS 组件参数列表
     */
    private String assembleArgs;

    /**
     * This field is MB_PROD_RUN_RULE.REMARK 备注
     */
    private String remark;

    /**
     * @return the value of  MB_PROD_RUN_RULE.EVENT_TYPE 事件类型
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @param eventType the value for MB_PROD_RUN_RULE.EVENT_TYPE 事件类型
     */
    public void setEventType(String eventType) {
        this.eventType = eventType == null ? null : eventType.trim();
    }

    /**
     * @return the value of  MB_PROD_RUN_RULE.SEQ_NO 组装序号
     */
    public Integer getSeqNo() {
        return seqNo;
    }

    /**
     * @param seqNo the value for MB_PROD_RUN_RULE.SEQ_NO 组装序号
     */
    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    /**
     * @return the value of  MB_PROD_RUN_RULE.ASSEMBLE_ID 组件标示
     */
    public String getAssembleId() {
        return assembleId;
    }

    /**
     * @param assembleId the value for MB_PROD_RUN_RULE.ASSEMBLE_ID 组件标示
     */
    public void setAssembleId(String assembleId) {
        this.assembleId = assembleId == null ? null : assembleId.trim();
    }

    /**
     * @return the value of  MB_PROD_RUN_RULE.ASSEMBLE_TYPE 
     */
    public String getAssembleType() {
        return assembleType;
    }

    /**
     * @param assembleType the value for MB_PROD_RUN_RULE.ASSEMBLE_TYPE 
     */
    public void setAssembleType(String assembleType) {
        this.assembleType = assembleType == null ? null : assembleType.trim();
    }

    /**
     * @return the value of  MB_PROD_RUN_RULE.ASSEMBLE_RULE 组件角色
     */
    public String getAssembleRule() {
        return assembleRule;
    }

    /**
     * @param assembleRule the value for MB_PROD_RUN_RULE.ASSEMBLE_RULE 组件角色
     */
    public void setAssembleRule(String assembleRule) {
        this.assembleRule = assembleRule == null ? null : assembleRule.trim();
    }

    /**
     * @return the value of  MB_PROD_RUN_RULE.ASSEMBLE_ARGS 组件参数列表
     */
    public String getAssembleArgs() {
        return assembleArgs;
    }

    /**
     * @param assembleArgs the value for MB_PROD_RUN_RULE.ASSEMBLE_ARGS 组件参数列表
     */
    public void setAssembleArgs(String assembleArgs) {
        this.assembleArgs = assembleArgs == null ? null : assembleArgs.trim();
    }

    /**
     * @return the value of  MB_PROD_RUN_RULE.REMARK 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the value for MB_PROD_RUN_RULE.REMARK 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}