package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by wujuan on 2015/10/13 11:01:00.
 */
public class MbEventAttr extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is MB_EVENT_ATTR.EVENT_TYPE 事件类型
     */
    @TablePk(index = 1)
    private String eventType;

    /**
     * This field is MB_EVENT_ATTR.SEQ_NO 组装序号
     */
    @TablePk(index = 2)
    private String seqNo;
    /**
     * This field is MB_EVENT_ATTR.COLUMN_STATUS
     */

    private String columnStatus;

    /**
     * This field is MB_EVENT_ATTR.ASSEMBLE_TYPE 组装分类
     */
    private String assembleType;

    /**
     * This field is MB_EVENT_ATTR.ASSEMBLE_ID 属性KEY
     */
    private String assembleId;

    /**
     * This field is MB_EVENT_ATTR.ATTR_VALUE 属性值
     */
    private String attrValue;

    /**
     * This field is MB_EVENT_ATTR.ASSEMBLE_RULE 属性值
     */
    private String assembleRule;

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
     * @return the value of  MB_EVENT_ATTR.EVENT_TYPE 事件类型
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @param eventType the value for MB_EVENT_ATTR.EVENT_TYPE 事件类型
     */
    public void setEventType(String eventType) {
        this.eventType = eventType == null ? null : eventType.trim();
    }

    /**
     * @return the value of  MB_EVENT_ATTR.SEQ_NO 组装序号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * @param seqNo the value for MB_EVENT_ATTR.SEQ_NO 组装序号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * @return the value of  MB_EVENT_ATTR.ASSEMBLE_TYPE 组装分类
     */
    public String getAssembleType() {
        return assembleType;
    }

    /**
     * @param assembleType the value for MB_EVENT_ATTR.ASSEMBLE_TYPE 组装分类
     */
    public void setAssembleType(String assembleType) {
        this.assembleType = assembleType == null ? null : assembleType.trim();
    }

    /**
     * @return the value of  MB_EVENT_ATTR.ASSEMBLE_ID 属性KEY
     */
    public String getAssembleId() {
        return assembleId;
    }

    /**
     * @param assembleId the value for MB_EVENT_ATTR.ASSEMBLE_ID 属性KEY
     */
    public void setAssembleId(String assembleId) {
        this.assembleId = assembleId == null ? null : assembleId.trim();
    }

    /**
     * @return the value of  MB_EVENT_ATTR.ATTR_VALUE 属性值
     */
    public String getAttrValue() {
        return attrValue;
    }

    /**
     * @param attrValue the value for MB_EVENT_ATTR.ATTR_VALUE 属性值
     */
    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue == null ? null : attrValue.trim();
    }

    public String getAssembleRule() {
        return assembleRule;
    }

    public void setAssembleRule(String assembleRule) {
        this.assembleRule = assembleRule == null ? null : assembleRule.trim();
    }
    /**
     * @return the value of  MB_EVENT_ATTR.COLUMN_STATUS
     */
    public String getColumnStatus() {
        return columnStatus;
    }

    /**
     * @param columnStatus the value for MB_EVENT_ATTR.COLUMN_STATUS
     */
    public void setColumnStatus(String columnStatus) {
        this.columnStatus = columnStatus == null ? null : columnStatus.trim();
    }
}