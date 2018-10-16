package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by zhangyjw on 2015/09/22 11:33:48.
 */
public class MbAttrClass extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is MB_ATTR_CLASS.ATTR_CLASS 属性分类
     */
    @TablePk(index = 1)
    private String attrClass;
    /**
     * This field is MB_ATTR_CLASS.COLUMN_STATUS
     */

    private String columnStatus;

    /**
     * This field is MB_ATTR_CLASS.ATTR_CLASS_DESC 属性分类描述
     */
    private String attrClassDesc;

    /**
     * This field is MB_ATTR_CLASS.ATTR_CLASS_LEVEL 属性分类层级
     */
    private String attrClassLevel;

    /**
     * This field is MB_ATTR_CLASS.PARENT_ATTR_CLASS 上级属性分类
     */
    private String parentAttrClass;
    /**
     * This field is MB_ATTR_CLASS.company 所属法人
     */
    private String company;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the value of  MB_ATTR_CLASS.ATTR_CLASS 属性分类
     */
    public String getAttrClass() {
        return attrClass;
    }

    /**
     * @param attrClass the value for MB_ATTR_CLASS.ATTR_CLASS 属性分类
     */
    public void setAttrClass(String attrClass) {
        this.attrClass = attrClass == null ? null : attrClass.trim();
    }

    /**
     * @return the value of  MB_ATTR_CLASS.ATTR_CLASS_DESC 属性分类描述
     */
    public String getAttrClassDesc() {
        return attrClassDesc;
    }

    /**
     * @param attrClassDesc the value for MB_ATTR_CLASS.ATTR_CLASS_DESC 属性分类描述
     */
    public void setAttrClassDesc(String attrClassDesc) {
        this.attrClassDesc = attrClassDesc == null ? null : attrClassDesc.trim();
    }

    /**
     * @return the value of  MB_ATTR_CLASS.ATTR_CLASS_LEVEL 属性分类层级
     */
    public String getAttrClassLevel() {
        return attrClassLevel;
    }

    /**
     * @param attrClassLevel the value for MB_ATTR_CLASS.ATTR_CLASS_LEVEL 属性分类层级
     */
    public void setAttrClassLevel(String attrClassLevel) {
        this.attrClassLevel = attrClassLevel == null ? null : attrClassLevel.trim();
    }

    /**
     * @return the value of  MB_ATTR_CLASS.PARENT_ATTR_CLASS 上级属性分类
     */
    public String getParentAttrClass() {
        return parentAttrClass;
    }

    /**
     * @param parentAttrClass the value for MB_ATTR_CLASS.PARENT_ATTR_CLASS 上级属性分类
     */
    public void setParentAttrClass(String parentAttrClass) {
        this.parentAttrClass = parentAttrClass == null ? null : parentAttrClass.trim();
    }
    /**
     * @return the value of  MB_ATTR_CLASS.COLUMN_STATUS
     */
    public String getColumnStatus() {
        return columnStatus;
    }

    /**
     * @param columnStatus the value for MB_ATTR_CLASS.COLUMN_STATUS
     */
    public void setColumnStatus(String columnStatus) {
        this.columnStatus = columnStatus == null ? null : columnStatus.trim();
    }
}