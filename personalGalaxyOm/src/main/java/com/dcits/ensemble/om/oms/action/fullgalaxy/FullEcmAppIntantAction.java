package com.dcits.ensemble.om.oms.action.fullgalaxy;



import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.manager.app.CommonShellService;
import com.dcits.ensemble.om.oms.manager.app.IContainer;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.service.app.EcmAppIntantService;
import com.dcits.ensemble.om.oms.service.fullgalaxy.FullEcmAppIntantService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;


/**
 * FullEcmAppIntantAction* 
 * @author tangxlf
 * @date 2015-10-29
 */
@Controller
public class FullEcmAppIntantAction {		
	

	@Resource
	IService omsBaseService;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	IContainer appContainer;
	@Resource
	CommonShellService commonShellService;
	@Resource
	EcmAppIntantService ecmAppIntantService;
	@Resource
	FullEcmAppIntantService fullEcmAppIntantService;
	
	
	@RequestMapping("deployFullAppIntant")
	public void deploy(HttpServletRequest request, PrintWriter printWriter,String isRemainConfig) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			fullEcmAppIntantService.deployFullAppIntant(userId,isRemainConfig);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	@RequestMapping("startFullAppIntant")
	public void startAppIntant(HttpServletRequest request, PrintWriter printWriter,EcmAppIntant intant) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			fullEcmAppIntantService.startFullAppIntant(userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	@RequestMapping("stopFullAppIntant")
	public void stopAppIntant(HttpServletRequest request, PrintWriter printWriter,EcmAppIntant intant) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			fullEcmAppIntantService.stopFullAppIntant(userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	@RequestMapping("reStartFullAppIntant")
	public void reStartAppIntant(HttpServletRequest request, PrintWriter printWriter,EcmAppIntant intant) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			fullEcmAppIntantService.reStartFullAppIntant(userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}

	@RequestMapping("findFullAppIntant")
	public void find(HttpServletRequest request,PrintWriter printWriter) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			ActionResultWrite.setReadResult(printWriter, fullEcmAppIntantService.findFullAppAndIntantList(userId));
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

	}
	
	
}
