package com.dcits.ensemble.om.oms.module.shshpoc;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by tangxl on 2016/07/27 14:19:17.
 */
public class EcmAppmoniIndexRec extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_APPMONI_INDEX_REC.APP_INDEXREC_ID 
     */
    @TablePk(index = 1)
    private Integer appIndexrecId;
    /**
     * This field is ECM_APPMONI_INDEX_REC.APP_INDEXREC_DATE 
     */
    private String appIndexrecDate;
    /**
     * This field is ECM_APPMONI_INDEX_REC.APP_INDEXREC_STTIME 
     */
    private String appIndexrecSttime;
    /**
     * This field is ECM_APPMONI_INDEX_REC.APP_INDEXREC_EDTIME 
     */
    private String appIndexrecEdtime;
    /**
     * This field is ECM_APPMONI_INDEX_REC.APP_INDEXREC_DIMEVAL 
     */
    private String appIndexrecDimeval;
    /**
     * This field is ECM_APPMONI_INDEX_REC.APP_INDEXREC_DIME 
     */
    private String appIndexrecDime;
    /**
     * This field is ECM_APPMONI_INDEX_REC.INDEX1 
     */
    private String index1;
    /**
     * This field is ECM_APPMONI_INDEX_REC.INDEX2 
     */
    private String index2;
    /**
     * This field is ECM_APPMONI_INDEX_REC.INDEX3 
     */
    private String index3;
    /**
     * This field is ECM_APPMONI_INDEX_REC.INDEX4 
     */
    private String index4;
    /**
     * This field is ECM_APPMONI_INDEX_REC.INDEX5 
     */
    private String index5;
    /**
     * This field is ECM_APPMONI_INDEX_REC.INDEX6 
     */
    private String index6;
    /**
     * This field is ECM_APPMONI_INDEX_REC.INDEX7 
     */
    private String index7;
    /**
     * This field is ECM_APPMONI_INDEX_REC.INDEX8 
     */
    private String index8;
    /**
     * This field is ECM_APPMONI_INDEX_REC.INDEX9 
     */
    private String index9;
    /**
     * This field is ECM_APPMONI_INDEX_REC.INDEX10 
     */
    private String index10;
    
    private String appIndexrecDimeName;
    
    private String startTime;
    
    private String endTime;
    
    private String appMoniPeriod;//应用指标监控周期
    
	public Integer getAppIndexrecId() {
		return appIndexrecId;
	}
	public void setAppIndexrecId(Integer appIndexrecId) {
		this.appIndexrecId = appIndexrecId;
	}
	public String getAppIndexrecDate() {
		return appIndexrecDate;
	}
	public void setAppIndexrecDate(String appIndexrecDate) {
		this.appIndexrecDate = appIndexrecDate;
	}
	public String getAppIndexrecDimeval() {
		return appIndexrecDimeval;
	}
	public void setAppIndexrecDimeval(String appIndexrecDimeval) {
		this.appIndexrecDimeval = appIndexrecDimeval;
	}
	public String getAppIndexrecDime() {
		return appIndexrecDime;
	}
	public void setAppIndexrecDime(String appIndexrecDime) {
		this.appIndexrecDime = appIndexrecDime;
	}
	public String getIndex1() {
		return index1;
	}
	public void setIndex1(String index1) {
		this.index1 = index1;
	}
	public String getIndex2() {
		return index2;
	}
	public void setIndex2(String index2) {
		this.index2 = index2;
	}
	public String getIndex3() {
		return index3;
	}
	public void setIndex3(String index3) {
		this.index3 = index3;
	}
	public String getIndex4() {
		return index4;
	}
	public void setIndex4(String index4) {
		this.index4 = index4;
	}
	public String getIndex5() {
		return index5;
	}
	public void setIndex5(String index5) {
		this.index5 = index5;
	}
	public String getIndex6() {
		return index6;
	}
	public void setIndex6(String index6) {
		this.index6 = index6;
	}
	public String getIndex7() {
		return index7;
	}
	public void setIndex7(String index7) {
		this.index7 = index7;
	}
	public String getIndex8() {
		return index8;
	}
	public void setIndex8(String index8) {
		this.index8 = index8;
	}
	public String getIndex9() {
		return index9;
	}
	public void setIndex9(String index9) {
		this.index9 = index9;
	}
	public String getIndex10() {
		return index10;
	}
	public void setIndex10(String index10) {
		this.index10 = index10;
	}
	public String getAppIndexrecSttime() {
		return appIndexrecSttime;
	}
	public void setAppIndexrecSttime(String appIndexrecSttime) {
		this.appIndexrecSttime = appIndexrecSttime;
	}
	public String getAppIndexrecEdtime() {
		return appIndexrecEdtime;
	}
	public void setAppIndexrecEdtime(String appIndexrecEdtime) {
		this.appIndexrecEdtime = appIndexrecEdtime;
	}
	public String getAppIndexrecDimeName() {
		return appIndexrecDimeName;
	}
	public void setAppIndexrecDimeName(String appIndexrecDimeName) {
		this.appIndexrecDimeName = appIndexrecDimeName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getAppMoniPeriod() {
		return appMoniPeriod;
	}
	public void setAppMoniPeriod(String appMoniPeriod) {
		this.appMoniPeriod = appMoniPeriod;
	}
}