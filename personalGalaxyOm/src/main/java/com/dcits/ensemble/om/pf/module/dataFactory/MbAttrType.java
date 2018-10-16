package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by zhangyjw on 2015/10/26 14:03:12.
 */
public class MbAttrType extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is MB_ATTR_TYPE.ATTR_KEY 属性定义
     */
    @TablePk(index = 1)
    private String attrKey;
    /**
     * This field is MB_ATTR_TYPE.COLUMN_STATUS
     */

    private String columnStatus;

    /**
     * This field is MB_ATTR_TYPE.ATTR_DESC 属性描述
     */
    private String attrDesc;

    /**
     * This field is MB_ATTR_TYPE.ATTR_CLASS 属性分类
     */
    private String attrClass;

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
     * This field is MB_ATTR_TYPE.VALUE_METHOD 取值方式
            F：固定 
            V：取值自MB_ATTR_VALUE
            R：取值自其它参数表，VALUE_SCORE中定义取值方式，定义表名及主键即可
            
     */
    private String valueMethod;

    /**
     * This field is MB_ATTR_TYPE.PROCESS_TYPE 处理类型   C:校验 S:赋值
     */
    private String processType;

    private String busiCategory;

    private String setValueFlag;

    private String attrType;

    /**
     * This field is MB_ATTR_TYPE.USE_METHOD 使用方式: EVAL-赋值类 CTR-控制类 IND-独立逻辑
     */
    private String useMethod;

    /**
     * This field is MB_ATTR_TYPE.STATUS 状态
            A：有效
            F：无效
     */
    private String status;

    /**
     * @return the value of  MB_ATTR_TYPE.ATTR_KEY 属性定义
     */
    public String getAttrKey() {
        return attrKey;
    }

    /**
     * @param attrKey the value for MB_ATTR_TYPE.ATTR_KEY 属性定义
     */
    public void setAttrKey(String attrKey) {
        this.attrKey = attrKey == null ? null : attrKey.trim();
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
        this.attrDesc = attrDesc == null ? null : attrDesc.trim();
    }

    /**
     * @return the value of  MB_ATTR_TYPE.ATTR_CLASS 属性分类
     */
    public String getAttrClass() {
        return attrClass;
    }

    /**
     * @param attrClass the value for MB_ATTR_TYPE.ATTR_CLASS 属性分类
     */
    public void setAttrClass(String attrClass) {
        this.attrClass = attrClass == null ? null : attrClass.trim();
    }

    /**
     * @return the value of  MB_ATTR_TYPE.VALUE_METHOD 取值方式
            F：固定 
            V：取值自MB_ATTR_VALUE
            R：取值自其它参数表，VALUE_SCORE中定义取值方式，定义表名及主键即可
            
     */
    public String getValueMethod() {
        return valueMethod;
    }

    /**
     * @param valueMethod the value for MB_ATTR_TYPE.VALUE_METHOD 取值方式
            F：固定 
            V：取值自MB_ATTR_VALUE
            R：取值自其它参数表，VALUE_SCORE中定义取值方式，定义表名及主键即可
            
     */
    public void setValueMethod(String valueMethod) {
        this.valueMethod = valueMethod == null ? null : valueMethod.trim();
    }

    /**
     * @return the value of  MB_ATTR_TYPE.PROCESS_TYPE 处理类型   C:校验 S:赋值
     */
    public String getProcessType() {
        return processType;
    }

    /**
     * @param processType the value for MB_ATTR_TYPE.PROCESS_TYPE 处理类型   C:校验 S:赋值
     */
    public void setProcessType(String processType) {
        this.processType = processType == null ? null : processType.trim();
    }

    /**
     * @return the value of  MB_ATTR_TYPE.USE_METHOD 使用方式: EVAL-赋值类 CTR-控制类 IND-独立逻辑
     */
    public String getUseMethod() {
        return useMethod;
    }

    /**
     * @param useMethod the value for MB_ATTR_TYPE.USE_METHOD 使用方式: EVAL-赋值类 CTR-控制类 IND-独立逻辑
     */
    public void setUseMethod(String useMethod) {
        this.useMethod = useMethod == null ? null : useMethod.trim();
    }

    /**
     * @return the value of  MB_ATTR_TYPE.STATUS 状态
            A：有效
            F：无效
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the value for MB_ATTR_TYPE.STATUS 状态
            A：有效
            F：无效
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType == null ? null : attrType.trim();
    }

    public String getBusiCategory() {
        return busiCategory;
    }

    public void setBusiCategory(String busiCategory) {
        this.busiCategory = busiCategory == null ? null : busiCategory.trim();
    }

    public String getSetValueFlag() {
        return setValueFlag;
    }

    public void setSetValueFlag(String setValueFlag) {
        this.setValueFlag = setValueFlag == null ? null : setValueFlag.trim();
    }
    /**
     * @return the value of  MB_ATTR_TYPE.COLUMN_STATUS
     */
    public String getColumnStatus() {
        return columnStatus;
    }

    /**
     * @param columnStatus the value for MB_ATTR_TYPE.COLUMN_STATUS
     */
    public void setColumnStatus(String columnStatus) {
        this.columnStatus = columnStatus == null ? null : columnStatus.trim();
    }
}