package com.dcits.ensemble.om.oms.action.fullgalaxy;

import com.dcits.ensemble.om.oms.action.utils.ActionResultWrite;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.service.fullgalaxy.FullServerViewService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;


/**
 * FullSeverViewAction* 
 * @author tangxlf
 * @date 2015-12-18
 */
@Controller
public class FullSeverViewAction {		

	
	@Resource
	FullServerViewService fullServerViewService;
	@Resource
	ParamComboxService paramComboxService;

	@RequestMapping("findFullServerView")
	public void find(HttpServletRequest request,PrintWriter printWriter,String isAutoStartApp) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
		//	ActionResultWrite.setReadResult(printWriter, fullServerViewService.findFullServerView(userId));
			ActionResultWrite.setReadResult(printWriter,fullServerViewService.findFullServerView(userId,isAutoStartApp));
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	@RequestMapping("getRefreshTime")
	public void  getTime(PrintWriter printWriter) {
		printWriter.print(paramComboxService.getParaRemark1(SysConfigConstants.FULL_GALAXY_REFRESH_TIME));
	}
	
}
