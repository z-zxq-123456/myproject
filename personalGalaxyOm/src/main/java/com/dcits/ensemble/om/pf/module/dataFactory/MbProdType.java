package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by zhangyjw on 2015/09/28 10:32:46.
 */
public class MbProdType extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is MB_PROD_TYPE.PROD_TYPE 产品类型
     */
    @TablePk(index = 1)
    private String prodType;
    /**
     * This field is MB_PROD_TYPE.COLUMN_STATUS
     */
    private String columnStatus;

    /**
     * This field is MB_PROD_TYPE.PROD_DESC 产品类型描述
     */
    private String prodDesc;

    /**
     * This field is MB_PROD_TYPE.PROD_CLASS 产品分类
     */
    private String prodClass;

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
     * This field is MB_PROD_TYPE.PROD_GROUP 是否产品组
     */
    private String prodGroup;

    private String prodRange;

    /**
     * This field is MB_PROD_TYPE.BASE_PROD_TYPE 基础产品
     */
    private String baseProdType;

    /**
     * This field is MB_PROD_TYPE.STATUS 状态
            A：有效
            F：无效
     */
    private String status;

    /**
     * @return the value of  MB_PROD_TYPE.PROD_TYPE 产品类型
     */
    public String getProdType() {
        return prodType;
    }

    /**
     * @param prodType the value for MB_PROD_TYPE.PROD_TYPE 产品类型
     */
    public void setProdType(String prodType) {
        this.prodType = prodType == null ? null : prodType.trim();
    }

    /**
     * @return the value of  MB_PROD_TYPE.PROD_DESC 产品类型描述
     */
    public String getProdDesc() {
        return prodDesc;
    }

    /**
     * @param prodDesc the value for MB_PROD_TYPE.PROD_DESC 产品类型描述
     */
    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc == null ? null : prodDesc.trim();
    }

    /**
     * @return the value of  MB_PROD_TYPE.PROD_CLASS 产品分类
     */
    public String getProdClass() {
        return prodClass;
    }

    /**
     * @param prodClass the value for MB_PROD_TYPE.PROD_CLASS 产品分类
     */
    public void setProdClass(String prodClass) {
        this.prodClass = prodClass == null ? null : prodClass.trim();
    }

    /**
     * @return the value of  MB_PROD_TYPE.PROD_GROUP 是否产品组
     */
    public String getProdGroup() {
        return prodGroup;
    }

    /**
     * @param prodGroup the value for MB_PROD_TYPE.PROD_GROUP 是否产品组
     */
    public void setProdGroup(String prodGroup) {
        this.prodGroup = prodGroup == null ? null : prodGroup.trim();
    }

    /**
     * @return the value of  MB_PROD_TYPE.STATUS 状态
            A：有效
            F：无效
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the value for MB_PROD_TYPE.STATUS 状态
            A：有效
            F：无效
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getProdRange() {
        return prodRange;
    }

    public void setProdRange(String prodRange) {
        this.prodRange = prodRange ==null ? null : prodRange.trim();
    }

    public String getBaseProdType() {
        return baseProdType;
    }

    public void setBaseProdType(String baseProdType) {
        this.baseProdType = baseProdType ==null ? null : baseProdType.trim();
    }
    /**
     * @return the value of  MB_PROD_TYPE.COLUMN_STATUS
     */
    public String getColumnStatus() {
        return columnStatus;
    }

    /**
     * @param columnStatus the value for MB_PROD_TYPE.COLUMN_STATUS
     */
    public void setColumnStatus(String columnStatus) {
        this.columnStatus = columnStatus == null ? null : columnStatus.trim();
    }
}