package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by zhangyjw on 2015/09/25 15:32:27.
 */
public class MbEventType extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is MB_EVENT_TYPE.EVENT_TYPE 事件类型
     */
    @TablePk(index = 1)
    private String eventType;
    /**
     * This field is MB_EVENT_TYPE.COLUMN_STATUS
     */

    private String columnStatus;

    /**
     * This field is MB_EVENT_TYPE.EVENT_DESC 事件类型描述
     */
    private String eventDesc;

    /**
     * This field is MB_EVENT_TYPE.EVENT_CLASS 事件分类
     */
    private String eventClass;

    /**
     * This field is MB_EVENT_TYPE.PROCESS_METHOD 处理类型
            A：检查  C：提交
     */
    private String processMethod;

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
     * This field is MB_EVENT_TYPE.STATUS 状态
            A：有效
            F：无效
     */
    private String status;
    private String isStandard;
    /**
     * @return the value of  MB_EVENT_TYPE.EVENT_TYPE 事件类型
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @param eventType the value for MB_EVENT_TYPE.EVENT_TYPE 事件类型
     */
    public void setEventType(String eventType) {
        this.eventType = eventType == null ? null : eventType.trim();
    }

    /**
     * @return the value of  MB_EVENT_TYPE.EVENT_DESC 事件类型描述
     */
    public String getEventDesc() {
        return eventDesc;
    }

    /**
     * @param eventDesc the value for MB_EVENT_TYPE.EVENT_DESC 事件类型描述
     */
    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc == null ? null : eventDesc.trim();
    }

    /**
     * @return the value of  MB_EVENT_TYPE.EVENT_CLASS 事件分类
     */
    public String getEventClass() {
        return eventClass;
    }

    /**
     * @param eventClass the value for MB_EVENT_TYPE.EVENT_CLASS 事件分类
     */
    public void setEventClass(String eventClass) {
        this.eventClass = eventClass == null ? null : eventClass.trim();
    }

    /**
     * @return the value of  MB_EVENT_TYPE.PROCESS_METHOD 处理类型
            A：检查  C：提交
     */
    public String getProcessMethod() {
        return processMethod;
    }

    /**
     * @param processMethod the value for MB_EVENT_TYPE.PROCESS_METHOD 处理类型
            A：检查  C：提交
     */
    public void setProcessMethod(String processMethod) {
        this.processMethod = processMethod == null ? null : processMethod.trim();
    }

    /**
     * @return the value of  MB_EVENT_TYPE.STATUS 状态
            A：有效
            F：无效
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the value for MB_EVENT_TYPE.STATUS 状态
            A：有效
            F：无效
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
    public void setIsStandard(String isStandard){this.isStandard=isStandard==null ?null : isStandard.trim();}
    public String getIsStandard(){return isStandard;}
    /**
     * @return the value of  MB_EVENT_TYPE.COLUMN_STATUS
     */
    public String getColumnStatus() {
        return columnStatus;
    }

    /**
     * @param columnStatus the value for MB_EVENT_TYPE.COLUMN_STATUS
     */
    public void setColumnStatus(String columnStatus) {
        this.columnStatus = columnStatus == null ? null : columnStatus.trim();
    }
}