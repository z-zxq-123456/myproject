package com.dcits.ensemble.om.oms.module.app;

import com.dcits.galaxy.base.bean.AbstractBean;

public class AppConfigInfo extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;
    
    private Integer appId;
    
    private Integer appIntantId;
    
    private String  fileName;//配置文件名
    
    private String  configKey;//配置文件中的键    
    
    private String  configValue;//配置文件中的值
    
    private String  updateConfigValue;//要修改的值   
    
    private String  updateType;//应用配置修改方式  0003001 应用单实例修改|0003002 应用修改|0003003	全量修改
    
   
    
	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public String getUpdateType() {
		return updateType;
	}

	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}

	public Integer getAppIntantId() {
		return appIntantId;
	}

	public void setAppIntantId(Integer appIntantId) {
		this.appIntantId = appIntantId;
	}

	public String getUpdateConfigValue() {
		return updateConfigValue;
	}

	public void setUpdateConfigValue(String updateConfigValue) {
		this.updateConfigValue = updateConfigValue;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
    
    

}
