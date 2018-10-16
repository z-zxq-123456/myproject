package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by zhangyjw on 2015/10/14 09:46:11.
 */
public class MbPartAttr extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is MB_PART_ATTR.PART_TYPE 部件类型
     */
    @TablePk(index = 1)
    private String partType;

    /**
     * This field is MB_PART_ATTR.ATTR_KEY 属性KEY
     */
    @TablePk(index = 2)
    private String attrKey;

    /**
     * This field is MB_PART_ATTR.COLUMN_STATUS
     */
    private String columnStatus;

    /**
     * This field is MB_PART_ATTR.ATTR_VALUE 属性值
     */
    private String attrValue;

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
     * @return the value of  MB_PART_ATTR.PART_TYPE 部件类型
     */
    public String getPartType() {
        return partType;
    }

    /**
     * @param partType the value for MB_PART_ATTR.PART_TYPE 部件类型
     */
    public void setPartType(String partType) {
        this.partType = partType == null ? null : partType.trim();
    }

    /**
     * @return the value of  MB_PART_ATTR.ATTR_KEY 属性KEY
     */
    public String getAttrKey() {
        return attrKey;
    }

    /**
     * @param attrKey the value for MB_PART_ATTR.ATTR_KEY 属性KEY
     */
    public void setAttrKey(String attrKey) {
        this.attrKey = attrKey == null ? null : attrKey.trim();
    }

    /**
     * @return the value of  MB_PART_ATTR.ATTR_VALUE 属性值
     */
    public String getAttrValue() {
        return attrValue;
    }

    /**
     * @param attrValue the value for MB_PART_ATTR.ATTR_VALUE 属性值
     */
    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue == null ? null : attrValue.trim();
    }
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