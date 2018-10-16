package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/04/23 11:25:55.
 */
public class MbEventPartRelation extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is MB_EVENT_PART_RELATION.EVENT_DEFAULT_TYPE 
     */
    @TablePk(index = 1)
    private String eventType;

    /**
     * This field is MB_EVENT_PART_RELATION.ASSEMBLE_TYPE 
     */
    @TablePk(index = 2)
    private String assembleType;

    /**
     * This field is MB_EVENT_PART_RELATION.ASSEMBLE_ID 
     */
    @TablePk(index = 3)
    private String assembleId;

    /**
     * This field is MB_EVENT_PART_RELATION.PART_DESC 
     */
    private String partDesc;

    /**
     * This field is MB_EVENT_PART_RELATION.STATUS 生效状态 A-生效 F-失效
     */
    private String status;

    /**
     * @return the value of  MB_EVENT_PART_RELATION.EVENT_DEFAULT_TYPE 
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @param eventType the value for MB_EVENT_PART_RELATION.EVENT_TYPE
     */
    public void setEventType(String eventType) {
        this.eventType = eventType == null ? null : eventType.trim();
    }

    /**
     * @return the value of  MB_EVENT_PART_RELATION.ASSEMBLE_TYPE 
     */
    public String getAssembleType() {
        return assembleType;
    }

    /**
     * @param assembleType the value for MB_EVENT_PART_RELATION.ASSEMBLE_TYPE 
     */
    public void setAssembleType(String assembleType) {
        this.assembleType = assembleType == null ? null : assembleType.trim();
    }

    /**
     * @return the value of  MB_EVENT_PART_RELATION.ASSEMBLE_ID 
     */
    public String getAssembleId() {
        return assembleId;
    }

    /**
     * @param assembleId the value for MB_EVENT_PART_RELATION.ASSEMBLE_ID 
     */
    public void setAssembleId(String assembleId) {
        this.assembleId = assembleId == null ? null : assembleId.trim();
    }

    /**
     * @return the value of  MB_EVENT_PART_RELATION.PART_DESC 
     */
    public String getPartDesc() {
        return partDesc;
    }

    /**
     * @param partDesc the value for MB_EVENT_PART_RELATION.PART_DESC 
     */
    public void setPartDesc(String partDesc) {
        this.partDesc = partDesc == null ? null : partDesc.trim();
    }

    /**
     * @return the value of  MB_EVENT_PART_RELATION.STATUS 生效状态 A-生效 F-失效
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the value for MB_EVENT_PART_RELATION.STATUS 生效状态 A-生效 F-失效
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}