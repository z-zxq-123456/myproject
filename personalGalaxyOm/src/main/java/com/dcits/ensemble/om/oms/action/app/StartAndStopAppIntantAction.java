package com.dcits.ensemble.om.oms.action.app;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.common.ProgressMessageUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.manager.app.IContainer;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.service.app.EcmAppIntantService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;


/**
 * EcmAppIntantAction* 
 * @author tangxlf
 * @date 2015-08-27
 */
@Controller
public class StartAndStopAppIntantAction {	
	
	@Resource
	IContainer appContainer;
	@Resource
	EcmAppIntantService ecmAppIntantService;	
	
	private static final int timeMill=10;//间隔时间

	
	@RequestMapping("startAppIntant")
	public void startAppIntant(HttpServletRequest request, PrintWriter printWriter,EcmAppIntant  intant) {
		try {
			String userId = (String )request.getSession().getAttribute("UserName");
			ProgressMessageUtil.startProgress(""+userId,intant.getSerIp(),intant.getAppIntantName());
			appContainer.startContainer(intant,""+userId);
			ecmAppIntantService.saveStartOrStopAppIntantStatus(intant,SysConfigConstants.APP_INTANTSTATUS_START,userId);
			stopProgress(""+userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

	}
	
	@RequestMapping("stopAppIntant")
	public void stopAppIntant(HttpServletRequest request, PrintWriter printWriter,EcmAppIntant  intant) {
		try {
			String userId = (String )request.getSession().getAttribute("UserName");
			ProgressMessageUtil.startProgress(""+userId,intant.getSerIp(),intant.getAppIntantName());
			appContainer.stopContainer(intant, "" + userId);
			ecmAppIntantService.saveStartOrStopAppIntantStatus(intant,SysConfigConstants.APP_INTANTSTATUS_STOP,userId);
			stopProgress(""+userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

	}
	
	@RequestMapping("reStartAppIntant")
	public void reStartAppIntant(HttpServletRequest request, PrintWriter printWriter,EcmAppIntant  intant) {
		try {
			String userId = (String )request.getSession().getAttribute("UserName");
			ProgressMessageUtil.startProgress(""+userId,intant.getSerIp(),intant.getAppIntantName());
			appContainer.reStartContainer(intant,""+userId);
			ecmAppIntantService.saveStartOrStopAppIntantStatus(intant,SysConfigConstants.APP_INTANTSTATUS_RESTART,userId);
			stopProgress(""+userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
		
	
	/**
	 * 停止进度消息机制
	 * @param  String userId   用户ID     	
	 *
     */	
	private void stopProgress(String userId){
		try {
			Thread.sleep(timeMill*1000);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
        ProgressMessageUtil.stopProgress(userId);		
	}
}
