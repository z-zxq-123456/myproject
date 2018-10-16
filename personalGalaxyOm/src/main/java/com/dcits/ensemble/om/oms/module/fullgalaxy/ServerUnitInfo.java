package com.dcits.ensemble.om.oms.module.fullgalaxy;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerUnitInfo extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;
    
    private Integer unitId;//id
    
    private String name; //名称
    
    private String imageUrl; //图片地址
    
    private String statusCode;//健康状态码
    
    private String statusImage;//健康状态图标
    
    private String unitType;//类型    
    
    private String bgColor;//背景颜色

	private List<Map<String,String>>  infoList = new ArrayList<Map<String,String>>();
    
	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}
	
    public void addInfo(String dataName,String dataValue){
    	Map<String,String>  dataMap = new HashMap<String,String>();
    	dataMap.put(SysConfigConstants.SVGVIEW_DATA_NAME, dataName);
    	dataMap.put(SysConfigConstants.SVGVIEW_DATA_VALUE,dataValue);
    	infoList.add(dataMap);
    }
    
    public String getStatusImage() {
		return statusImage;
	}

	public void setStatusImage(String statusImage) {
		this.statusImage = statusImage;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public List<Map<String, String>> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<Map<String, String>> infoList) {
		this.infoList = infoList;
	}
}
