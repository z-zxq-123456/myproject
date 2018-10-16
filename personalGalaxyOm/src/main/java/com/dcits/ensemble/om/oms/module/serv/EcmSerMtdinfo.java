package com.dcits.ensemble.om.oms.module.serv;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by oms on 2016/02/29 10:09:40.
 */
public class EcmSerMtdinfo extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_SER_MTDINFO.SER_MTD_ID 
     */
    @TablePk(index = 1)
    private Integer serMtdId;

    /**
     * This field is ECM_SER_MTDINFO.APP_SER_ID 
     */
    private Integer appSerId;

    /**
     * This field is ECM_SER_MTDINFO.SER_MTD_CNM 
     */
    private String serMtdCnm;

    /**
     * This field is ECM_SER_MTDINFO.SER_MTD_ENM 
     */
    private String serMtdEnm;
    
    private String appSerName;

    /**
     * @return the value of  ECM_SER_MTDINFO.SER_MTD_ID 
     */
    public Integer getSerMtdId() {
        return serMtdId;
    }

    /**
     * @param serMtdId the value for ECM_SER_MTDINFO.SER_MTD_ID 
     */
    public void setSerMtdId(Integer serMtdId) {
        this.serMtdId = serMtdId;
    }

    /**
     * @return the value of  ECM_SER_MTDINFO.APP_SER_ID 
     */
    public Integer getAppSerId() {
        return appSerId;
    }

    /**
     * @param appSerId the value for ECM_SER_MTDINFO.APP_SER_ID 
     */
    public void setAppSerId(Integer appSerId) {
        this.appSerId = appSerId;
    }

    /**
     * @return the value of  ECM_SER_MTDINFO.SER_MTD_CNM 
     */
    public String getSerMtdCnm() {
        return serMtdCnm;
    }

    /**
     * @param serMtdCnm the value for ECM_SER_MTDINFO.SER_MTD_CNM 
     */
    public void setSerMtdCnm(String serMtdCnm) {
        this.serMtdCnm = serMtdCnm == null ? null : serMtdCnm.trim();
    }

    /**
     * @return the value of  ECM_SER_MTDINFO.SER_MTD_ENM 
     */
    public String getSerMtdEnm() {
        return serMtdEnm;
    }

    /**
     * @param serMtdEnm the value for ECM_SER_MTDINFO.SER_MTD_ENM 
     */
    public void setSerMtdEnm(String serMtdEnm) {
        this.serMtdEnm = serMtdEnm == null ? null : serMtdEnm.trim();
    }

	/**
	 * @return the appSerName
	 */
	public String getAppSerName() {
		return appSerName;
	}

	/**
	 * @param appSerName the appSerName to set
	 */
	public void setAppSerName(String appSerName) {
		this.appSerName = appSerName;
	}
}