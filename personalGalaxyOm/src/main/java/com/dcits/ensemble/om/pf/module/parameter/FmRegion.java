package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/03/18 18:02:59.
 */
public class FmRegion extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is FM_REGION.REGION 鍦板尯
     */
    @TablePk(index = 1)
    private String region;

    /**
     * This field is FM_REGION.REGION_DESC 鍚嶇О
     */
    private String regionDesc;

    /**
     * This field is FM_REGION.INTERNAL_CODE 
     */
    private String internalCode;

    /**
     * This field is FM_REGION.COMPANY 
     */
    private String company;

    /**
     * @return the value of  FM_REGION.REGION 鍦板尯
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region the value for FM_REGION.REGION 鍦板尯
     */
    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    /**
     * @return the value of  FM_REGION.REGION_DESC 鍚嶇О
     */
    public String getRegionDesc() {
        return regionDesc;
    }

    /**
     * @param regionDesc the value for FM_REGION.REGION_DESC 鍚嶇О
     */
    public void setRegionDesc(String regionDesc) {
        this.regionDesc = regionDesc == null ? null : regionDesc.trim();
    }

    /**
     * @return the value of  FM_REGION.INTERNAL_CODE 
     */
    public String getInternalCode() {
        return internalCode;
    }

    /**
     * @param internalCode the value for FM_REGION.INTERNAL_CODE 
     */
    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode == null ? null : internalCode.trim();
    }

    /**
     * @return the value of  FM_REGION.COMPANY 
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for FM_REGION.COMPANY 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}