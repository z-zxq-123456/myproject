package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:13.
 */
public class CifMerchantTypeDef extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_MERCHANT_TYPE_DEF.CC_SUB_TYPE 鍟嗘埛绫诲瀷
     */
    @TablePk(index = 1)
    private String ccSubType;

    /**
     * This field is CIF_MERCHANT_TYPE_DEF.CC_TYPE 鍟嗘埛澶х被
     */
    private String ccType;

    /**
     * This field is CIF_MERCHANT_TYPE_DEF.CC_SUB_TYPE_DESC 鍟嗘埛绫诲瀷璇存槑
     */
    private String ccSubTypeDesc;

    /**
     * This field is CIF_MERCHANT_TYPE_DEF.CC_TYPE_DESC 鍟嗘埛澶х被璇存槑
     */
    private String ccTypeDesc;

    /**
     * This field is CIF_MERCHANT_TYPE_DEF.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_MERCHANT_TYPE_DEF.CC_SUB_TYPE 鍟嗘埛绫诲瀷
     */
    public String getCcSubType() {
        return ccSubType;
    }

    /**
     * @param ccSubType the value for CIF_MERCHANT_TYPE_DEF.CC_SUB_TYPE 鍟嗘埛绫诲瀷
     */
    public void setCcSubType(String ccSubType) {
        this.ccSubType = ccSubType == null ? null : ccSubType.trim();
    }

    /**
     * @return the value of  CIF_MERCHANT_TYPE_DEF.CC_TYPE 鍟嗘埛澶х被
     */
    public String getCcType() {
        return ccType;
    }

    /**
     * @param ccType the value for CIF_MERCHANT_TYPE_DEF.CC_TYPE 鍟嗘埛澶х被
     */
    public void setCcType(String ccType) {
        this.ccType = ccType == null ? null : ccType.trim();
    }

    /**
     * @return the value of  CIF_MERCHANT_TYPE_DEF.CC_SUB_TYPE_DESC 鍟嗘埛绫诲瀷璇存槑
     */
    public String getCcSubTypeDesc() {
        return ccSubTypeDesc;
    }

    /**
     * @param ccSubTypeDesc the value for CIF_MERCHANT_TYPE_DEF.CC_SUB_TYPE_DESC 鍟嗘埛绫诲瀷璇存槑
     */
    public void setCcSubTypeDesc(String ccSubTypeDesc) {
        this.ccSubTypeDesc = ccSubTypeDesc == null ? null : ccSubTypeDesc.trim();
    }

    /**
     * @return the value of  CIF_MERCHANT_TYPE_DEF.CC_TYPE_DESC 鍟嗘埛澶х被璇存槑
     */
    public String getCcTypeDesc() {
        return ccTypeDesc;
    }

    /**
     * @param ccTypeDesc the value for CIF_MERCHANT_TYPE_DEF.CC_TYPE_DESC 鍟嗘埛澶х被璇存槑
     */
    public void setCcTypeDesc(String ccTypeDesc) {
        this.ccTypeDesc = ccTypeDesc == null ? null : ccTypeDesc.trim();
    }

    /**
     * @return the value of  CIF_MERCHANT_TYPE_DEF.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_MERCHANT_TYPE_DEF.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}