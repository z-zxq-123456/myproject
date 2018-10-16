package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by zhangyjw on 2015/09/24 10:04:26.
 */
public class MbEventClass extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is MB_EVENT_CLASS.EVENT_CLASS 事件分类
     */
    @TablePk(index = 1)
    private String eventClass;

    /**
     * This field is MB_EVENT_CLASS.EVENT_CLASS_DESC 事件分类描述
     */
    private String eventClassDesc;

    /**
     * This field is MB_EVENT_CLASS.EVENT_CLASS_LEVEL 事件分类层级
     */
    private String eventClassLevel;

    /**
     * This field is MB_EVENT_CLASS.PARENT_EVENT_CLASS 上级事件分类
     */
    private String parentEventClass;

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
     * @return the value of  MB_EVENT_CLASS.EVENT_CLASS 事件分类
     */
    public String getEventClass() {
        return eventClass;
    }

    /**
     * @param eventClass the value for MB_EVENT_CLASS.EVENT_CLASS 事件分类
     */
    public void setEventClass(String eventClass) {
        this.eventClass = eventClass == null ? null : eventClass.trim();
    }

    /**
     * @return the value of  MB_EVENT_CLASS.EVENT_CLASS_DESC 事件分类描述
     */
    public String getEventClassDesc() {
        return eventClassDesc;
    }

    /**
     * @param eventClassDesc the value for MB_EVENT_CLASS.EVENT_CLASS_DESC 事件分类描述
     */
    public void setEventClassDesc(String eventClassDesc) {
        this.eventClassDesc = eventClassDesc == null ? null : eventClassDesc.trim();
    }

    /**
     * @return the value of  MB_EVENT_CLASS.EVENT_CLASS_LEVEL 事件分类层级
     */
    public String getEventClassLevel() {
        return eventClassLevel;
    }

    /**
     * @param eventClassLevel the value for MB_EVENT_CLASS.EVENT_CLASS_LEVEL 事件分类层级
     */
    public void setEventClassLevel(String eventClassLevel) {
        this.eventClassLevel = eventClassLevel == null ? null : eventClassLevel.trim();
    }

    /**
     * @return the value of  MB_EVENT_CLASS.PARENT_EVENT_CLASS 上级事件分类
     */
    public String getParentEventClass() {
        return parentEventClass;
    }

    /**
     * @param parentEventClass the value for MB_EVENT_CLASS.PARENT_EVENT_CLASS 上级事件分类
     */
    public void setParentEventClass(String parentEventClass) {
        this.parentEventClass = parentEventClass == null ? null : parentEventClass.trim();
    }
}