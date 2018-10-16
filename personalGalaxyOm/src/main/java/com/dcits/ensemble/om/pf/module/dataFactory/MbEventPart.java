package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/02/25 19:10:35.
 */
public class MbEventPart extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is MB_EVENT_PART.EVENT_TYPE 
     */
    @TablePk(index = 1)
    private String eventType;



    /**
     * This field is MB_EVENT_PART.ASSEMBLE_ID 
     */
    @TablePk(index = 2)
    private String assembleId;

    /**
     * This field is MB_EVENT_PART.ATTR_KEY 属性KEY
     */
    @TablePk(index = 3)
    private String attrKey;
    /**
     * This field is MB_EVENT_PART.COLUMN_STATUS
     */
    private String columnStatus;

    /**
     * This field is MB_EVENT_PART.ATTR_VALUE 属性值
     */
    private String attrValue;

    /**
     * @return the value of  MB_EVENT_PART.EVENT_TYPE 
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @param eventType the value for MB_EVENT_PART.EVENT_TYPE 
     */
    public void setEventType(String eventType) {
        this.eventType = eventType == null ? null : eventType.trim();
    }

    /**
     * @return the value of  MB_EVENT_PART.ASSEMBLE_ID 
     */
    public String getAssembleId() {
        return assembleId;
    }

    /**
     * @param assembleId the value for MB_EVENT_PART.ASSEMBLE_ID 
     */
    public void setAssembleId(String assembleId) {
        this.assembleId = assembleId == null ? null : assembleId.trim();
    }

    /**
     * @return the value of  MB_EVENT_PART.ATTR_KEY 属性KEY
     */
    public String getAttrKey() {
        return attrKey;
    }

    /**
     * @param attrKey the value for MB_EVENT_PART.ATTR_KEY 属性KEY
     */
    public void setAttrKey(String attrKey) {
        this.attrKey = attrKey == null ? null : attrKey.trim();
    }

    /**
     * @return the value of  MB_EVENT_PART.ATTR_VALUE 属性值
     */
    public String getAttrValue() {
        return attrValue;
    }

    /**
     * @param attrValue the value for MB_EVENT_PART.ATTR_VALUE 属性值
     */
    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue == null ? null : attrValue.trim();
    }
    /**
     * @return the value of  MB_EVENT_PART.COLUMN_STATUS
     */
    public String getColumnStatus() {
        return columnStatus;
    }

    /**
     * @param columnStatus the value for MB_EVENT_PART.COLUMN_STATUS
     */
    public void setColumnStatus(String columnStatus) {
        this.columnStatus = columnStatus == null ? null : columnStatus.trim();
    }
}