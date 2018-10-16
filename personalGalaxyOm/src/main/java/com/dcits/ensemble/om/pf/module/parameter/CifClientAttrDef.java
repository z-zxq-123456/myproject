package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:07.
 */
public class CifClientAttrDef extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_CLIENT_ATTR_DEF.ATTR_TYPE 
     */
    @TablePk(index = 1)
    private String attrType;

    /**
     * This field is CIF_CLIENT_ATTR_DEF.ATTR_TYPE_DESC 绫诲瀷鎻忚堪
     */
    private String attrTypeDesc;

    /**
     * This field is CIF_CLIENT_ATTR_DEF.LOSS_IND 
     */
    private String lossInd;

    /**
     * This field is CIF_CLIENT_ATTR_DEF.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_CLIENT_ATTR_DEF.ATTR_TYPE 
     */
    public String getAttrType() {
        return attrType;
    }

    /**
     * @param attrType the value for CIF_CLIENT_ATTR_DEF.ATTR_TYPE 
     */
    public void setAttrType(String attrType) {
        this.attrType = attrType == null ? null : attrType.trim();
    }

    /**
     * @return the value of  CIF_CLIENT_ATTR_DEF.ATTR_TYPE_DESC 绫诲瀷鎻忚堪
     */
    public String getAttrTypeDesc() {
        return attrTypeDesc;
    }

    /**
     * @param attrTypeDesc the value for CIF_CLIENT_ATTR_DEF.ATTR_TYPE_DESC 绫诲瀷鎻忚堪
     */
    public void setAttrTypeDesc(String attrTypeDesc) {
        this.attrTypeDesc = attrTypeDesc == null ? null : attrTypeDesc.trim();
    }

    /**
     * @return the value of  CIF_CLIENT_ATTR_DEF.LOSS_IND 
     */
    public String getLossInd() {
        return lossInd;
    }

    /**
     * @param lossInd the value for CIF_CLIENT_ATTR_DEF.LOSS_IND 
     */
    public void setLossInd(String lossInd) {
        this.lossInd = lossInd == null ? null : lossInd.trim();
    }

    /**
     * @return the value of  CIF_CLIENT_ATTR_DEF.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_CLIENT_ATTR_DEF.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}