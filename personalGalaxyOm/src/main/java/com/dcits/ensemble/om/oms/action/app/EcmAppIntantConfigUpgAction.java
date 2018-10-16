package com.dcits.ensemble.om.oms.action.app;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.manager.app.IContainer;
import com.dcits.ensemble.om.oms.manager.app.ParsePropertiesFileService;
import com.dcits.ensemble.om.oms.manager.dubbo.UpgAppIntantCache;
import com.dcits.ensemble.om.oms.module.app.AppConfigInfo;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.service.app.EcmAppIntantUpgService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.List;

/**
 * EcmAppIntantConfigUpgAction* 
 * @author wangbinaf
 * @date 2016-03-23
 */
@Controller
public class EcmAppIntantConfigUpgAction {
	

	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	IService  omsBaseService;
	@Resource
	ParamComboxService  paramComboxService;	
	@Resource
	IContainer appContainer;
	@Resource
	EcmAppIntantUpgService  ecmAppIntantUpgService;
	@Resource
	ParsePropertiesFileService  parsePropertiesFileService;	
	@Resource
	UpgAppIntantCache  upgAppIntantCache;
	//先升级实例配置调整
	@RequestMapping("earlyUpdateEcmAppConfig")
	public void earlyUpdateAppIntantFileProperties(HttpServletRequest request, PrintWriter printWriter,AppConfigInfo configInfo) {
		try {
			if(configInfo.getUpdateType().equals(SysConfigConstants.APPCONF_EARLY_APP)){
				List<EcmAppIntant>  intantList= upgAppIntantCache.getAppIntEarlyList(configInfo.getAppId());
				parsePropertiesFileService.updateAppConfig(configInfo,intantList);
			}
			if(configInfo.getUpdateType().equals(SysConfigConstants.APPCONF_EARLY_INTANT)){
				parsePropertiesFileService.updateAppIntantFileProperties(configInfo);
			}
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	//后升级实例配置调整
	@RequestMapping("lateUpdateEcmAppConfig")
	public void lateUpdateAppIntantFileProperties(HttpServletRequest request, PrintWriter printWriter,AppConfigInfo configInfo) {
		try {
			if(configInfo.getUpdateType().equals(SysConfigConstants.APPCONF_LATE_APP)){
				List<EcmAppIntant>  intantList= upgAppIntantCache.getAppIntLateList(configInfo.getAppId());
				parsePropertiesFileService.updateAppConfig(configInfo,intantList);
				log.info("intantList="+intantList);
			}
			if(configInfo.getUpdateType().equals(SysConfigConstants.APPCONF_LATE_INTANT)){
				parsePropertiesFileService.updateAppIntantFileProperties(configInfo);
			}

			log.info("configInfo="+configInfo);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

	}
}