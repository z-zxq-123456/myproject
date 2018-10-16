package com.dcits.ensemble.om.oms.manager.db;


import com.dcits.ensemble.om.oms.action.utils.OMSPassWordBase64;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.manager.app.ParsePropertiesFileService;
import com.dcits.ensemble.om.oms.module.app.AppConfigInfo;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareDbint;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 修改db配置文件 
 * @author tangxlf
 * @date 2016-05-24
 */
@Component
public class DbConfUpdate {
	
	
	
	private final static String DB_SQL_ID ="findListByAppId";//查询APP对应的zk实例
	@Resource
	OMSIDao omsBaseDao;
	@Resource
	ParsePropertiesFileService parsePropertiesFileService;
	@Resource
	ParamComboxService paramComboxService;
	
	/**
	 * 获取APPID对应的DB列表
	 * @param Integer   appId   应用ID
	 * @return  List<EcmMidwareDbint>  DB实例列表	
	 */
	public List<EcmMidwareDbint> getDbMidwareInfo(Integer  appId) {
		Map<String,Object>  queryMap =new HashMap<String,Object>();
		queryMap.put("appId",appId);
		List<EcmMidwareDbint> zkIntantList =omsBaseDao.findListByCond(new EcmMidwareDbint(),DB_SQL_ID,queryMap);
		return zkIntantList;
	}
	
	
	
	/**
	 * 修改应用实例DB配置文件所有DB实例对应的配置
	 * @param EcmAppIntant  intant 应用实例对象	
	 */
	public void dbConfUpdate(EcmAppIntant intant) {
	  List<EcmMidwareDbint> dbIntantList =getDbMidwareInfo(intant.getAppId());//通过APPID查找对应的db实例
	  String fileName =paramComboxService.getParaNameByCode(SysConfigConstants.DB_FILE_NAME);
	  for(EcmMidwareDbint dbInt:dbIntantList){
		  AppConfigInfo configInfo = new AppConfigInfo();
		  configInfo.setAppIntantId(intant.getAppIntantId());
		  configInfo.setAppId(intant.getAppId());
		  configInfo.setFileName(fileName);
		  configInfo.setConfigKey(replaceDbMark(paramComboxService.getParaName(SysConfigConstants.DB_FILE_URL),""+dbInt.getDbintNodeNum()));
		  configInfo.setUpdateConfigValue(DbIntHelpUtil.createDbUrl(dbInt));
		  parsePropertiesFileService.updateAppIntantFileProperties(configInfo);
		  configInfo.setConfigKey(replaceDbMark(paramComboxService.getParaName(SysConfigConstants.DB_FILE_USERNAME),""+dbInt.getDbintNodeNum()));
		  configInfo.setUpdateConfigValue(dbInt.getDbintUserName());
		  parsePropertiesFileService.updateAppIntantFileProperties(configInfo);
		  configInfo.setConfigKey(replaceDbMark(paramComboxService.getParaName(SysConfigConstants.DB_FILE_PWD),""+dbInt.getDbintNodeNum()));
		  configInfo.setUpdateConfigValue(OMSPassWordBase64.decodeToString(dbInt.getDbintUserPwd()));
		  parsePropertiesFileService.updateAppIntantFileProperties(configInfo);
	  }
	}
	/**
	 * 修改应用实例DB配置文件指定DB实例对应的配置
	 * @param EcmAppIntant    intant 应用实例对象	
	 * @param EcmMidwareDbint dbInt  DB实例对象	
	 */
	public void dbIntConfUpdate(EcmAppIntant intant,EcmMidwareDbint dbInt){
		  String fileName =paramComboxService.getParaNameByCode(SysConfigConstants.DB_FILE_NAME);
		  AppConfigInfo configInfo = new AppConfigInfo();
		  configInfo.setAppIntantId(intant.getAppIntantId());
		  configInfo.setAppId(intant.getAppId());
		  configInfo.setFileName(fileName);
		  configInfo.setConfigKey(replaceDbMark(paramComboxService.getParaName(SysConfigConstants.DB_FILE_URL),""+dbInt.getDbintNodeNum()));
		  configInfo.setUpdateConfigValue(DbIntHelpUtil.createDbUrl(dbInt));
		  parsePropertiesFileService.updateAppIntantFileProperties(configInfo);
		  configInfo.setConfigKey(replaceDbMark(paramComboxService.getParaName(SysConfigConstants.DB_FILE_USERNAME),""+dbInt.getDbintNodeNum()));
		  configInfo.setUpdateConfigValue(dbInt.getDbintUserName());
		  parsePropertiesFileService.updateAppIntantFileProperties(configInfo);
		  configInfo.setConfigKey(replaceDbMark(paramComboxService.getParaName(SysConfigConstants.DB_FILE_PWD),""+dbInt.getDbintNodeNum()));
		  configInfo.setUpdateConfigValue(OMSPassWordBase64.decodeToString(dbInt.getDbintUserPwd()));
		  parsePropertiesFileService.updateAppIntantFileProperties(configInfo);
	}
	
	private String replaceDbMark(String configKey,String mark){
		return configKey.replace("{0}", mark);
	}
}
