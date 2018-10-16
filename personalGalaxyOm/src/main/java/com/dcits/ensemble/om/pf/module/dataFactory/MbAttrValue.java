package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by zhangyjw on 2015/09/24 16:54:51.
 */
public class MbAttrValue extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is MB_ATTR_VALUE.ATTR_KEY 属性KEY
     */
    @TablePk(index = 1)
    private String attrKey;
    /**
     * This field is MB_ATTR_TYPE.ATTR_DESC 属性描述
     */
    private String attrDesc;
    /**
     * This field is MB_ATTR_VALUE.ATTR_VALUE 属性值
     */
    @TablePk(index = 2)
    private String attrValue;
    /**
     * This field is MB_ATTR_VALUE.COLUMN_STATUS
     */

    private String columnStatus;

    /**
     * This field is MB_ATTR_VALUE.VALUE_DESC 属性值描述
     */
    private String valueDesc;

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
    public String getRefColumns() {
        return refColumns;
    }

    public void setRefColumns(String refColumns) {
        this.refColumns = refColumns;
    }

    private String refColumns;

    private String refTable;
    private String refCondition;
    /**
     * @return the value of  MB_ATTR_VALUE.ATTR_KEY 属性KEY
     */
    public String getAttrKey() {
        return attrKey;
    }

    /**
     * @param attrKey the value for MB_ATTR_VALUE.ATTR_KEY 属性KEY
     */
    public void setAttrKey(String attrKey) {
        this.attrKey = attrKey;
    }

    /**
     * @return the value of  MB_ATTR_VALUE.ATTR_VALUE 属性值
     */
    public String getAttrValue() {
        return attrValue;
    }

    /**
     * @param attrValue the value for MB_ATTR_VALUE.ATTR_VALUE 属性值
     */
    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    /**
     * @return the value of  MB_ATTR_VALUE.VALUE_DESC 属性值描述
     */
    public String getValueDesc() {
        return valueDesc;
    }

    /**
     * @param valueDesc the value for MB_ATTR_VALUE.VALUE_DESC 属性值描述
     */
    public void setValueDesc(String valueDesc) {
        this.valueDesc = valueDesc ;
    }

    /**
     * @return the value of  MB_ATTR_TYPE.ATTR_DESC 属性描述
     */
    public String getAttrDesc() {
        return attrDesc;
    }

    /**
     * @param attrDesc the value for MB_ATTR_TYPE.ATTR_DESC 属性描述
     */
    public void setAttrDesc(String attrDesc) {
        this.attrDesc = attrDesc;
    }

    public String getRefTable() {
        return refTable;
    }

    public void setRefTable(String refTable) {
        this.refTable = refTable;
    }

    public String getRefCondition() {
        return refCondition;
    }

    public void setRefCondition(String refCondition) {
        this.refCondition = refCondition;
    }
    /**
     * @return the value of  MB_ATTR_VALUE.COLUMN_STATUS
     */
    public String getColumnStatus() {
        return columnStatus;
    }

    /**
     * @param columnStatus the value for MB_ATTR_VALUE.COLUMN_STATUS
     */
    public void setColumnStatus(String columnStatus) {
        this.columnStatus = columnStatus == null ? null : columnStatus.trim();
    }
}