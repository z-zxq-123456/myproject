package com.dcits.ensemble.om.oms.action.fullgalaxy;

import com.dcits.ensemble.om.oms.action.utils.ActionResultWrite;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.service.fullgalaxy.FullCleanAppIntantService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;


/**
 * FullCLeanAppIntantAction* 
 * @author tangxlf
 * @date 2015-11-23
 */
@Controller
public class FullCLeanAppIntantAction {	
	
	@Resource
	FullCleanAppIntantService fullCleanAppIntantService;
	
	
	@RequestMapping("cleanAppIntant")
	public void cleanAppIntant(HttpServletRequest request,PrintWriter printWriter,EcmAppIntant intant) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			fullCleanAppIntantService.cleanAppIntant(intant,userId);
			ActionResultWrite.setSuccedResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	@RequestMapping("cleanAllAppIntant")
	public void cleanAllAppIntant(HttpServletRequest request,PrintWriter printWriter) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			fullCleanAppIntantService.cleanAllAppIntant(userId);
			ActionResultWrite.setSuccedResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	
	
	
	
}
