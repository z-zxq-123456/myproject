package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by zhangyjw on 2015/09/24 14:36:28.
 */
public class MbPartClass extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is MB_PART_CLASS.PART_CLASS 部件分类
     */
    @TablePk(index = 1)
    private String partClass;

    /**
     * This field is MB_PART_CLASS.PART_CLASS_DESC 部件分类描述
     */
    private String partClassDesc;

    /**
     * This field is MB_PART_CLASS.PART_CLASS_LEVEL 部件分类层级
     */
    private String partClassLevel;

    /**
     * This field is MB_PART_CLASS.PARENT_PART_CLASS 上级部件分类
     */
    private String parentPartClass;

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
     * @return the value of  MB_PART_CLASS.PART_CLASS 部件分类
     */
    public String getPartClass() {
        return partClass;
    }

    /**
     * @param partClass the value for MB_PART_CLASS.PART_CLASS 部件分类
     */
    public void setPartClass(String partClass) {
        this.partClass = partClass == null ? null : partClass.trim();
    }

    /**
     * @return the value of  MB_PART_CLASS.PART_CLASS_DESC 部件分类描述
     */
    public String getPartClassDesc() {
        return partClassDesc;
    }

    /**
     * @param partClassDesc the value for MB_PART_CLASS.PART_CLASS_DESC 部件分类描述
     */
    public void setPartClassDesc(String partClassDesc) {
        this.partClassDesc = partClassDesc == null ? null : partClassDesc.trim();
    }

    /**
     * @return the value of  MB_PART_CLASS.PART_CLASS_LEVEL 部件分类层级
     */
    public String getPartClassLevel() {
        return partClassLevel;
    }

    /**
     * @param partClassLevel the value for MB_PART_CLASS.PART_CLASS_LEVEL 部件分类层级
     */
    public void setPartClassLevel(String partClassLevel) {
        this.partClassLevel = partClassLevel == null ? null : partClassLevel.trim();
    }

    /**
     * @return the value of  MB_PART_CLASS.PARENT_PART_CLASS 上级部件分类
     */
    public String getParentPartClass() {
        return parentPartClass;
    }

    /**
     * @param parentPartClass the value for MB_PART_CLASS.PARENT_PART_CLASS 上级部件分类
     */
    public void setParentPartClass(String parentPartClass) {
        this.parentPartClass = parentPartClass == null ? null : parentPartClass.trim();
    }
}