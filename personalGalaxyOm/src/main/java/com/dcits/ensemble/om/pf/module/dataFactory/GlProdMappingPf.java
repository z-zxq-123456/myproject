package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/08/25 10:35:58.
 */
public class GlProdMappingPf extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is GL_PROD_MAPPING.MAPPING_TYPE 映射产品类型
     */
    @TablePk(index = 1)
    private String mappingType;

    /**
     * This field is GL_PROD_MAPPING.PROD_TYPE 产品类型
     */
    private String prodType;

    /**
     * This field is GL_PROD_MAPPING.MAPPING_DESC 映射名称
     */
    private String mappingDesc;

    /**
     * This field is GL_PROD_MAPPING.PROD_DESC 产品类型描述
     */
    private String prodDesc;

    /**
     * @return the value of  GL_PROD_MAPPING.MAPPING_TYPE 映射产品类型
     */
    public String getMappingType() {
        return mappingType;
    }

    /**
     * @param mappingType the value for GL_PROD_MAPPING.MAPPING_TYPE 映射产品类型
     */
    public void setMappingType(String mappingType) {
        this.mappingType = mappingType == null ? null : mappingType.trim();
    }

    /**
     * @return the value of  GL_PROD_MAPPING.PROD_TYPE 产品类型
     */
    public String getProdType() {
        return prodType;
    }

    /**
     * @param prodType the value for GL_PROD_MAPPING.PROD_TYPE 产品类型
     */
    public void setProdType(String prodType) {
        this.prodType = prodType == null ? null : prodType.trim();
    }

    /**
     * @return the value of  GL_PROD_MAPPING.MAPPING_DESC 映射名称
     */
    public String getMappingDesc() {
        return mappingDesc;
    }

    /**
     * @param mappingDesc the value for GL_PROD_MAPPING.MAPPING_DESC 映射名称
     */
    public void setMappingDesc(String mappingDesc) {
        this.mappingDesc = mappingDesc == null ? null : mappingDesc.trim();
    }

    /**
     * @return the value of  GL_PROD_MAPPING.PROD_DESC 产品类型描述
     */
    public String getProdDesc() {
        return prodDesc;
    }

    /**
     * @param prodDesc the value for GL_PROD_MAPPING.PROD_DESC 产品类型描述
     */
    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc == null ? null : prodDesc.trim();
    }
}