package com.dcits.ensemble.om.oms.manager.app;


import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.sys.EcmParam;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 属性文件指定属性缓存Service * 
 * @author luolang
 * @date 2015-10-29 
 */
@Service
public class PropertiesCacheService {
	
	@Resource
	ParsePropertiesFileService parsePropertiesFileService;
	
	@Resource
	ParamComboxService paramComboxService;
	
	
	private static Map<Integer,Map<String,String>> propertiesContainer = new HashMap<Integer,Map<String,String>>();
	
	private String registryAddress;//注册中心地址
	
	public String getRegistryAddress() {
		return registryAddress;
	}
	/**
	 * 从intant取信息放入propertiesContainer中
	 * @param EcmAppIntant intant  应用实例对象
	 * @param return为一个Map，里面为所有的参数Map
	 */
	private synchronized Map<String,String> getProperties(EcmAppIntant intant) {
		Map<String,String> appIntantConfMap = new HashMap<String,String>();		
		if( propertiesContainer.get(intant.getAppIntantId())==null) {			
			List<EcmParam> propertiesFileList = paramComboxService.getParaColls(SysConfigConstants.PARAMETER_FILE, "false");
			for(EcmParam propertiesFile :propertiesFileList) {
				List<EcmParam> propertiesList = paramComboxService.getParaColls(propertiesFile.getRemark1(),"false");
				String filePath = AppPathHelp.createAppConfPath(intant, paramComboxService) + "/" + propertiesFile.getParaName();
				Map<String,String> propertiesMap  = parsePropertiesFileService.parseFileAllProperties(intant,filePath);
				for(EcmParam properties:propertiesList) {
					if(propertiesMap.containsKey(properties.getParaName())){
						setRegistryAddress(properties.getParaName(),intant,propertiesMap.get(properties.getParaName()));
						appIntantConfMap.put(properties.getParaName(),propertiesMap.get(properties.getParaName()));
					}					
				}				
			}
			propertiesContainer.put(intant.getAppIntantId(),appIntantConfMap);			
			return appIntantConfMap;			
		} 
		return propertiesContainer.get(intant.getAppIntantId());
	}
	
	
	
	/**
	 *根据实例ID删除propertiesContainer中对应数据
	 * @param Integer appIntantId  实例ID
	 */
	public void removeProperties( Integer appIntantId) {
		propertiesContainer.remove(appIntantId);		
	}
	
	/**
	 * 获取应用实例配置文件中，配置参数的值
	 * @param EcmAppIntant intant    应用实例对象
	 * @param String configArgsName  配置参数名
	 * @return String 配置参数对应值  若无该参数返回null
	 */
	public String getProperties(String configArgsName,EcmAppIntant intant) {
		return getProperties(intant).get(configArgsName);			
	}
	//设置注册中心地址
	private void setRegistryAddress(String configArgsName,EcmAppIntant intant,String configArgsValue) {
		if(configArgsName.equals(paramComboxService.getParaName(SysConfigConstants.GALAXY_REGISTRY_ADDRESS))){
			if(intant.getAppIntantStatus().equals(SysConfigConstants.APP_INTANTSTATUS_START)){
				this.registryAddress = configArgsValue;
			}else if(intant.getAppIntantStatus().equals(SysConfigConstants.APP_INTANTSTATUS_RESTART)){
            	this.registryAddress = configArgsValue;
			}
		}
		
	}
}
