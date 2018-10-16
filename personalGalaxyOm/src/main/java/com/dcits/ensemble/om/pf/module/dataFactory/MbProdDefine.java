package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by wujuan on 2015/10/21 10:16:06.
 */
public class MbProdDefine extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is MB_PROD_DEFINE.PROD_TYPE 产品类型
     */
    @TablePk(index = 1)
    private String prodType;

    /**
     * This field is MB_PROD_DEFINE.SEQ_NO 组装序号
     */
    @TablePk(index = 2)
    private String seqNo;
    /**
     * This field is MB_PROD_DEFINE.COLUMN_STATUS
     */
    private String columnStatus;

    /**
     * This field is MB_PROD_DEFINE.ASSEMBLE_TYPE 组装类型
            EVENT  事件
            PART    部件
     */
    private String assembleType;

    /**
     * This field is MB_PROD_DEFINE.ASSEMBLE_ID 组件ID
     */
    private String assembleId;
    /**
     * This field is MB_PROD_DEFINE.EVENT_DEFAULT 组件ID
     */
    private String eventDefault;

    /**
     * This field is MB_PROD_DEFINE.ATTR_KEY 
     */
    private String attrKey;

    /**
     * This field is MB_PROD_DEFINE.ATTR_VALUE 
     */
    private String attrValue;

    /**
     * This field is MB_PROD_DEFINE.STATUS 状态
            A：有效
            F：无效
     */
    private String status;

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
     * @return the value of  MB_PROD_DEFINE.PROD_TYPE 产品类型
     */
    public String getProdType() {
        return prodType;
    }

    /**
     * @param prodType the value for MB_PROD_DEFINE.PROD_TYPE 产品类型
     */
    public void setProdType(String prodType) {
        this.prodType = prodType == null ? null : prodType.trim();
    }

    /**
     * @return the value of  MB_PROD_DEFINE.SEQ_NO 组装序号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * @param seqNo the value for MB_PROD_DEFINE.SEQ_NO 组装序号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * @return the value of  MB_PROD_DEFINE.ASSEMBLE_TYPE 组装类型
            EVENT  事件
            PART    部件
     */
    public String getAssembleType() {
        return assembleType;
    }

    /**
     * @param assembleType the value for MB_PROD_DEFINE.ASSEMBLE_TYPE 组装类型
            EVENT  事件
            PART    部件
     */
    public void setAssembleType(String assembleType) {
        this.assembleType = assembleType == null ? null : assembleType.trim();
    }

    /**
     * @return the value of  MB_PROD_DEFINE.ATTR_KEY 
     */
    public String getAttrKey() {
        return attrKey;
    }

    /**
     * @param attrKey the value for MB_PROD_DEFINE.ATTR_KEY 
     */
    public void setAttrKey(String attrKey) {
        this.attrKey = attrKey == null ? null : attrKey.trim();
    }

    /**
     * @return the value of  MB_PROD_DEFINE.ATTR_VALUE 
     */
    public String getAttrValue() {
        return attrValue;
    }

    /**
     * @param attrValue the value for MB_PROD_DEFINE.ATTR_VALUE 
     */
    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue == null ? null : attrValue.trim();
    }

    /**
     * @return the value of  MB_PROD_DEFINE.STATUS 状态
            A：有效
            F：无效
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the value for MB_PROD_DEFINE.STATUS 状态
            A：有效
            F：无效
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * @return the value of  MB_PROD_DEFINE.ASSEMBLE_ID 组件ID
     */
    public String getAssembleId() {
        return assembleId;
    }

    /**
     * @param assembleId the value for MB_PROD_DEFINE.ASSEMBLE_ID 组件ID
     */
    public void setAssembleId(String assembleId) {
        this.assembleId = assembleId == null ? null : assembleId.trim();
    }

    public String getEventDefault() {
        return eventDefault;
    }

    public void setEventDefault(String eventDefault) {
        this.eventDefault = eventDefault == null ? null : eventDefault.trim();
    }
    /**
     * @return the value of  MB_PROD_DEFINE.COLUMN_STATUS
     */
    public String getColumnStatus() {
        return columnStatus;
    }

    /**
     * @param columnStatus the value for MB_PROD_DEFINE.COLUMN_STATUS
     */
    public void setColumnStatus(String columnStatus) {
        this.columnStatus = columnStatus == null ? null : columnStatus.trim();
    }
}