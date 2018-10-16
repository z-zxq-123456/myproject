package com.dcits.ensemble.om.oms.action.app;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.manager.app.UpgNodeCache;
import com.dcits.ensemble.om.oms.module.app.EcmUpgndBtn;
import com.dcits.ensemble.om.oms.module.app.GuideNodeInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.List;

/**
 * EcmLoadAppUpgNodeBtnAction* 
 * @author LuoLang
 * @date 2016-03-2
 */
@Controller
public class EcmLoadAppUpgNodeBtnAction {	
	
	@Resource
	UpgNodeCache upgNodeCache;
	
	@RequestMapping("loadAppUpgNodeBtn")
	public void find(HttpServletRequest request, PrintWriter printWriter,Integer nodeSeq) {
		try {
			GuideNodeInfo guideNode = upgNodeCache.getGuideNodeInfo(nodeSeq);
			List<EcmUpgndBtn> btn = guideNode.getBtnList();
			ActionResultWrite.setReadResult(printWriter,btn);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}

}
