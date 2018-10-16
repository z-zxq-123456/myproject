package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/04/28 13:43:54.
 */
public class MbProdRelationDefine extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is MB_PROD_RELATION_DEFINE.PROD_TYPE 
     */
    @TablePk(index = 1)
    private String prodType;

    /**
     * This field is MB_PROD_RELATION_DEFINE.SUB_PROD_TYPE 
     */
    @TablePk(index = 2)
    private String subProdType;

    /**
     * This field is MB_PROD_RELATION_DEFINE.EVENT_TYPE 
     */
    @TablePk(index = 3)
    private String eventType;

    /**
     * This field is MB_PROD_RELATION_DEFINE.ASSEMBLE_ID 组件ID
     */
    @TablePk(index = 4)
    private String assembleId;

    /**
     * This field is MB_PROD_RELATION_DEFINE.ASSEMBLE_TYPE 组件类型
            EVENT  事件
            PART    部件
            ATTR    属性
     */
    private String assembleType;

    /**
     * This field is MB_PROD_RELATION_DEFINE.STATUS 生效状态 M 主产品生效, S-子产品生效 F-全部失效
     */
    private String status;

    /**
     * @return the value of  MB_PROD_RELATION_DEFINE.PROD_TYPE 
     */
    public String getProdType() {
        return prodType;
    }

    /**
     * @param prodType the value for MB_PROD_RELATION_DEFINE.PROD_TYPE 
     */
    public void setProdType(String prodType) {
        this.prodType = prodType == null ? null : prodType.trim();
    }

    /**
     * @return the value of  MB_PROD_RELATION_DEFINE.SUB_PROD_TYPE 
     */
    public String getSubProdType() {
        return subProdType;
    }

    /**
     * @param subProdType the value for MB_PROD_RELATION_DEFINE.SUB_PROD_TYPE 
     */
    public void setSubProdType(String subProdType) {
        this.subProdType = subProdType == null ? null : subProdType.trim();
    }

    /**
     * @return the value of  MB_PROD_RELATION_DEFINE.EVENT_TYPE 
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @param eventType the value for MB_PROD_RELATION_DEFINE.EVENT_TYPE 
     */
    public void setEventType(String eventType) {
        this.eventType = eventType == null ? null : eventType.trim();
    }

    /**
     * @return the value of  MB_PROD_RELATION_DEFINE.ASSEMBLE_TYPE 组件类型
            EVENT  事件
            PART    部件
            ATTR    属性
     */
    public String getAssembleType() {
        return assembleType;
    }

    /**
     * @param assembleType the value for MB_PROD_RELATION_DEFINE.ASSEMBLE_TYPE 组件类型
            EVENT  事件
            PART    部件
            ATTR    属性
     */
    public void setAssembleType(String assembleType) {
        this.assembleType = assembleType == null ? null : assembleType.trim();
    }

    /**
     * @return the value of  MB_PROD_RELATION_DEFINE.ASSEMBLE_ID 组件ID
     */
    public String getAssembleId() {
        return assembleId;
    }

    /**
     * @param assembleId the value for MB_PROD_RELATION_DEFINE.ASSEMBLE_ID 组件ID
     */
    public void setAssembleId(String assembleId) {
        this.assembleId = assembleId == null ? null : assembleId.trim();
    }

    /**
     * @return the value of  MB_PROD_RELATION_DEFINE.STATUS 生效状态 M 主产品生效, S-子产品生效 F-全部失效
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the value for MB_PROD_RELATION_DEFINE.STATUS 生效状态 M 主产品生效, S-子产品生效 F-全部失效
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}