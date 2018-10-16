package com.dcits.ensemble.om.oms.action.app;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.manager.app.UpgNodeCache;
import com.dcits.ensemble.om.oms.module.app.EcmUpgflowNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.List;

/**
 * EcmAppUpgflowAction* 
 * @author LuoLang
 * @date 2016-03-1
 */
@Controller
public class EcmLoadAppUpgNodeAction {	
	
	@Resource
	UpgNodeCache upgNodeCache;
	
	@RequestMapping("loadAppUpgNode")
	public void find(HttpServletRequest request, PrintWriter printWriter,EcmUpgflowNode  upgflowNodeinfo) {
		try {
			List<EcmUpgflowNode> node = upgNodeCache.getUpgFlowNodeList();
			ActionResultWrite.setReadResult(printWriter,node);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}

}
