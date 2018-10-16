package com.dcits.ensemble.om.oms.action.app;

import com.alibaba.fastjson.JSON;
import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.manager.app.ParsePropertiesFileService;
import com.dcits.ensemble.om.oms.module.app.AppConfigInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.List;

/**
 * EcmAppConfigFileAction* 
 * @author tangxlf
 * @date 2015-11-16
 */
@Controller
public class EcmAppConfigFileAction {	
	
	@Resource
	ParsePropertiesFileService parsePropertiesFileService;

	@RequestMapping("updateEcmAppFileConfig")
	public void update(HttpServletRequest request,PrintWriter printWriter,AppConfigInfo configInfo) {
		try {
            switch (configInfo.getUpdateType()) {
                case SysConfigConstants.APPCONF_UPDATE_INTANT:
                    parsePropertiesFileService.updateAppIntantFileProperties(configInfo);
                    break;
                case SysConfigConstants.APPCONF_UPDATE_APP:
                    parsePropertiesFileService.updateAppFileProperties(configInfo);
                    break;
                case SysConfigConstants.APPCONF_UPDATE_TOTAL:
                    parsePropertiesFileService.updateAllAppFileProperties(configInfo);
                    break;
            }
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}	

	@RequestMapping("findEcmAppFile")
	public void findFile(HttpServletRequest request, PrintWriter printWriter ,String appIntantId) {
		try {
			List<PkList> fileList = parsePropertiesFileService.getPropertiesFileList(Integer.parseInt(appIntantId));
			printWriter.print(JSON.toJSONString(fileList));
			printWriter.flush();
			printWriter.close();
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
    }
	
	
	@RequestMapping("findEcmAppFileConfig")
	public void findFileConfig(HttpServletRequest request, PrintWriter printWriter ,Integer appIntantId,String fileName) {
		try {
			List<AppConfigInfo> propertiesList = parsePropertiesFileService.getPropertiesList(appIntantId,fileName);
			ActionResultWrite.setReadResult(printWriter,propertiesList);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

    }
}