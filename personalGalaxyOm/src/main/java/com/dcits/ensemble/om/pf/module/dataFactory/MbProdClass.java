package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by zhangyjw on 2015/09/25 10:30:51.
 */
public class MbProdClass extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is MB_PROD_CLASS.PROD_CLASS 产品分类
     */
    @TablePk(index = 1)
    private String prodClass;

    /**
     * This field is MB_PROD_CLASS.PROD_CLASS_DESC 产品分类描述
     */
    private String prodClassDesc;

    /**
     * This field is MB_PROD_CLASS.PROD_CLASS_LEVEL 产品分类层级
     */
    private String prodClassLevel;

    /**
     * This field is MB_PROD_CLASS.PARENT_PROD_CLASS 上级产品分类
     */
    private String parentProdClass;

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
     * @return the value of  MB_PROD_CLASS.PROD_CLASS 产品分类
     */
    public String getProdClass() {
        return prodClass;
    }

    /**
     * @param prodClass the value for MB_PROD_CLASS.PROD_CLASS 产品分类
     */
    public void setProdClass(String prodClass) {
        this.prodClass = prodClass == null ? null : prodClass.trim();
    }

    /**
     * @return the value of  MB_PROD_CLASS.PROD_CLASS_DESC 产品分类描述
     */
    public String getProdClassDesc() {
        return prodClassDesc;
    }

    /**
     * @param prodClassDesc the value for MB_PROD_CLASS.PROD_CLASS_DESC 产品分类描述
     */
    public void setProdClassDesc(String prodClassDesc) {
        this.prodClassDesc = prodClassDesc == null ? null : prodClassDesc.trim();
    }

    /**
     * @return the value of  MB_PROD_CLASS.PROD_CLASS_LEVEL 产品分类层级
     */
    public String getProdClassLevel() {
        return prodClassLevel;
    }

    /**
     * @param prodClassLevel the value for MB_PROD_CLASS.PROD_CLASS_LEVEL 产品分类层级
     */
    public void setProdClassLevel(String prodClassLevel) {
        this.prodClassLevel = prodClassLevel == null ? null : prodClassLevel.trim();
    }

    /**
     * @return the value of  MB_PROD_CLASS.PARENT_PROD_CLASS 上级产品分类
     */
    public String getParentProdClass() {
        return parentProdClass;
    }

    /**
     * @param parentProdClass the value for MB_PROD_CLASS.PARENT_PROD_CLASS 上级产品分类
     */
    public void setParentProdClass(String parentProdClass) {
        this.parentProdClass = parentProdClass == null ? null : parentProdClass.trim();
    }
}