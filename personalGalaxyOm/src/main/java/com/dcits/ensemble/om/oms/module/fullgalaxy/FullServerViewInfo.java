package com.dcits.ensemble.om.oms.module.fullgalaxy;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FullServerViewInfo extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;
    
    private Integer unitId;//服务器ID
    
    private String name; //服务器IP
    
    private String imageUrl; //标签图标
    
    private String statusCode;//健康状态码
    
    private String statusImage;//健康状态图标
    
    private String unitType;//类型    
    
    private List<Map<String,String>>  infoList = new ArrayList<Map<String,String>>();
    
    private List<ServerUnitInfo>  unitList = new ArrayList<ServerUnitInfo>();
    
    public void addInfo(String dataName,String dataValue){
    	Map<String,String>  dataMap = new HashMap<String,String>();
    	dataMap.put(SysConfigConstants.SVGVIEW_DATA_NAME, dataName);
    	dataMap.put(SysConfigConstants.SVGVIEW_DATA_VALUE,dataValue);
    	infoList.add(dataMap);
    }
    
    public void addUnit(ServerUnitInfo unitInfo){
    	unitList.add(unitInfo);
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

	public String getStatusImage() {
		return statusImage;
	}

	public void setStatusImage(String statusImage) {
		this.statusImage = statusImage;
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

	public List<ServerUnitInfo> getUnitList() {
		return unitList;
	}

	public void setUnitList(List<ServerUnitInfo> unitList) {
		this.unitList = unitList;
	}
    
    
    
}
