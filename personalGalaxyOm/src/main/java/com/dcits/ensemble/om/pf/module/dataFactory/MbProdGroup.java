package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/04/28 13:43:54.
 */
public class MbProdGroup extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is MB_PROD_GROUP.PROD_TYPE 产品类型
     */
    @TablePk(index = 1)
    private String prodType;

    /**
     * This field is MB_PROD_GROUP.PROD_SUB_TYPE 产品子类型
     */
    @TablePk(index = 2)
    private String prodSubType;

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
    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    private String seqNo;

    private String defaultProd;

    public String getDefaultProd() {
        return defaultProd;
    }

    public void setDefaultProd(String defaultProd) {
        this.defaultProd = defaultProd == null ? null : defaultProd.trim();
    }

    /**
     * @return the value of  MB_PROD_GROUP.PROD_TYPE 产品类型
     */
    public String getProdType() {
        return prodType;
    }

    /**
     * @param prodType the value for MB_PROD_GROUP.PROD_TYPE 产品类型
     */
    public void setProdType(String prodType) {
        this.prodType = prodType == null ? null : prodType.trim();
    }

    /**
     * @return the value of  MB_PROD_GROUP.PROD_SUB_TYPE 产品子类型
     */
    public String getProdSubType() {
        return prodSubType;
    }

    /**
     * @param prodSubType the value for MB_PROD_GROUP.PROD_SUB_TYPE 产品子类型
     */
    public void setProdSubType(String prodSubType) {
        this.prodSubType = prodSubType == null ? null : prodSubType.trim();
    }
}