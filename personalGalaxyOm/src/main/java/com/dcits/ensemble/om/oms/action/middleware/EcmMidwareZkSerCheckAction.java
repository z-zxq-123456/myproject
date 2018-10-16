package com.dcits.ensemble.om.oms.action.middleware;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.dcits.dynamic.web.util.action.ActionResultWrite;

import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkSer;
import com.dcits.ensemble.om.oms.service.middleware.EcmMidwareZkCheckService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * EcmMidwareZkInstantAction* 
 * @author wangbinaf
 * @date 2016-04-29
 */
@Controller
public class EcmMidwareZkSerCheckAction {
	
	@Resource
	EcmMidwareZkCheckService ecmMidwareZkCheckService;
    @Resource
	IService omsBaseService;
	@RequestMapping("enableZkSers")
	public void save(HttpServletRequest request, PrintWriter printWriter,EcmMidwareZkSer  midwareZkSer) {
		try {
			ecmMidwareZkCheckService.enableAllZkSers(midwareZkSer);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e) {
			e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
		}
	}
	@RequestMapping("disableZkSers")
	public void startZkIntant(HttpServletRequest request, PrintWriter printWriter,EcmMidwareZkSer midwareZkSer) {
		try {
		    ecmMidwareZkCheckService.disableAllZkSers(midwareZkSer);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e) {
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter, e.getMessage());
		}
	}
	@RequestMapping("findZkSers")
	public void find(HttpServletRequest request, PrintWriter printWriter ,EcmMidwareZkSer midwareZkSer) {
		try {
		    List<EcmMidwareZkSer> reustList= ecmMidwareZkCheckService.findZkSers(midwareZkSer);
			ActionResultWrite.setReadResult(printWriter, reustList);
		}catch (Exception e) {
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter, e.getMessage());
		}
	}
}
