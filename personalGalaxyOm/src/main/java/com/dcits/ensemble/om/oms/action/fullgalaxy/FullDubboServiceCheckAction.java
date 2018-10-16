package com.dcits.ensemble.om.oms.action.fullgalaxy;


import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.service.fullgalaxy.FullDubboServiceCheckService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;


/**
 * FullDubboServiceCheckAction* 
 * @author tangxlf
 * @date 2015-01-06
 */
@Controller
public class FullDubboServiceCheckAction {		

	
	@Resource
	FullDubboServiceCheckService fullDubboServiceCheckService;


	@RequestMapping("findDubboCheck")
	public void find(HttpServletRequest request,PrintWriter printWriter,Integer midwareId) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			ActionResultWrite.setReadResult(printWriter, fullDubboServiceCheckService.checkService(userId, midwareId));
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}

}
