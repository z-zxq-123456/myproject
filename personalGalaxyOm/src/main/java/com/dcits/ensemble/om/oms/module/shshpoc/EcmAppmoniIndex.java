package com.dcits.ensemble.om.oms.module.shshpoc;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by tangxl on 2016/07/26 14:19:17.
 */
public class EcmAppmoniIndex extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_APPMONI_INDEX.APP_INDEX_ID 
     */
    @TablePk(index = 1)
    private Integer appIndexId;
    /**
     * This field is ECM_APPMONI_INDEX.APP_INDEX_NAME 
     */
    private String appIndexName;
    /**
     * This field is ECM_APPMONI_INDEX.APP_INDEX_NO 
     */
    private Integer appIndexNo;   
    /**
     * This field is ECM_APPMONI_INDEX.APP_INDEX_ISVIEW 
     */
    private String appIndexIsview;
    /**
     * This field is ECM_APPMONI_INDEX.APP_INDEX_FAC 
     */
    private String appIndexFac;
    /**
     * This field is ECM_APPMONI_INDEX.APP_INDEX_DESC 
     */
    private String appIndexDesc;    
 
    
    private String appIndexIsviewName;

	public Integer getAppIndexId() {
		return appIndexId;
	}

	public void setAppIndexId(Integer appIndexId) {
		this.appIndexId = appIndexId;
	}

	public String getAppIndexName() {
		return appIndexName;
	}

	public void setAppIndexName(String appIndexName) {
		this.appIndexName = appIndexName;
	}

	public Integer getAppIndexNo() {
		return appIndexNo;
	}

	public void setAppIndexNo(Integer appIndexNo) {
		this.appIndexNo = appIndexNo;
	}

	

	public String getAppIndexIsview() {
		return appIndexIsview;
	}

	public void setAppIndexIsview(String appIndexIsview) {
		this.appIndexIsview = appIndexIsview;
	}

	public String getAppIndexFac() {
		return appIndexFac;
	}

	public void setAppIndexFac(String appIndexFac) {
		this.appIndexFac = appIndexFac;
	}

	public String getAppIndexDesc() {
		return appIndexDesc;
	}

	public void setAppIndexDesc(String appIndexDesc) {
		this.appIndexDesc = appIndexDesc;
	}


	public String getAppIndexIsviewName() {
		return appIndexIsviewName;
	}

	public void setAppIndexIsviewName(String appIndexIsviewName) {
		this.appIndexIsviewName = appIndexIsviewName;
	}
}