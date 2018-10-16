package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by zhangyjw on 2015/09/28 10:26:21.
 */
public class MbPartType extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is MB_PART_TYPE.PART_TYPE 部件类型
     */
    @TablePk(index = 1)
    private String partType;

    /**
     * This field is MB_PART_TYPE.PART_DESC 部件类型描述
     */
    private String partDesc;

    /**
     * This field is MB_PART_TYPE.PART_CLASS 部件分类
     */
    private String partClass;
    private String isStandard;
    private String processMethod;

    private String busiCategory;


    private String defaultPart;

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
     * This field is MB_PART_TYPE.STATUS 状态
            A：有效
            F：无效
     */
    private String status;
    /**
     * This field is MB_PART_TYPE.COLUMN_STATUS
     */
    private String columnStatus;

    /**
     * @return the value of  MB_PART_TYPE.PART_TYPE 部件类型
     */
    public String getPartType() {
        return partType;
    }

    /**
     * @param partType the value for MB_PART_TYPE.PART_TYPE 部件类型
     */
    public void setPartType(String partType) {
        this.partType = partType == null ? null : partType.trim();
    }

    /**
     * @return the value of  MB_PART_TYPE.PART_DESC 部件类型描述
     */
    public String getPartDesc() {
        return partDesc;
    }

    /**
     * @param partDesc the value for MB_PART_TYPE.PART_DESC 部件类型描述
     */
    public void setPartDesc(String partDesc) {
        this.partDesc = partDesc == null ? null : partDesc.trim();
    }

    /**
     * @return the value of  MB_PART_TYPE.PART_CLASS 部件分类
     */
    public String getPartClass() {
        return partClass;
    }

    /**
     * @param partClass the value for MB_PART_TYPE.PART_CLASS 部件分类
     */
    public void setPartClass(String partClass) {
        this.partClass = partClass == null ? null : partClass.trim();
    }

    /**
     * @return the value of  MB_PART_TYPE.STATUS 状态
            A：有效
            F：无效
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the value for MB_PART_TYPE.STATUS 状态
            A：有效
            F：无效
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public void setIsStandard(String isStandard){this.isStandard = isStandard ==null ?null:isStandard.trim();}
    public String getIsStandard(){return isStandard;}

    public String getProcessMethod() {
        return processMethod;
    }
    public void setProcessMethod(String processMethod) {
        this.processMethod = processMethod == null ? null : processMethod.trim();
    }
    public String getDefaultPart() {
        return defaultPart;
    }
    public void setDefaultPart(String defaultPart) {
        this.defaultPart = defaultPart == null ? null :defaultPart.trim();
    }

    public String getBusiCategory() {
        return busiCategory;
    }

    public void setBusiCategory(String busiCategory) {
        this.busiCategory = busiCategory == null ? null :busiCategory.trim();
    }

    /**
     * @return the value of  MB_PART_TYPE.COLUMN_STATUS
     */
    public String getColumnStatus() {
        return columnStatus;
    }

    /**
     * @param columnStatus the value for MB_PART_TYPE.COLUMN_STATUS
     */
    public void setColumnStatus(String columnStatus) {
        this.columnStatus = columnStatus == null ? null : columnStatus.trim();
    }
}