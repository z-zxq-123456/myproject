package com.dcits.ensemble.om.oms.action.app;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.common.ProgressMessageUtil;
import com.dcits.ensemble.om.oms.manager.app.IContainer;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.app.EcmAppUpginfo;
import com.dcits.ensemble.om.oms.service.app.EcmAppIntantUpgService;
import com.dcits.ensemble.om.oms.service.app.EcmAppUpgBtnMidService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;


/**
 * EcmAppLateInstantAction* 
 * @author wangbinaf
 * @date 2016-03-17
 */
@Controller
public class 	EcmAppInstantUpgAction {
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
	EcmAppUpgBtnMidService ecmAppUpgBtnMidService;
	//后升级部署
	@RequestMapping("updateLateEcmAppInstant")
	public void lateAppInstant(HttpServletRequest request, PrintWriter printWriter,EcmAppIntant appIntant) {
		log.info("appIntant:" + appIntant);
		try {
			String userId =(String)request.getSession().getAttribute("UserName");
			ecmAppIntantUpgService.deployLateAppIntant(userId, appIntant);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	//先升级部署
	@RequestMapping("updateEarlyEcmAppInstant")
	public void earlyAppInstant(HttpServletRequest request, PrintWriter printWriter,EcmAppIntant appIntant) {
		try {
			String userId =(String)request.getSession().getAttribute("UserName");
			log.info("appIntant:" + appIntant);
			ecmAppIntantUpgService.deployEarlyAppIntant(appIntant,userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	//先升级启动
	@RequestMapping("startEarlyEcmAppInstant")
	public void startEarlyAppInstant(HttpServletRequest request, PrintWriter printWriter,EcmAppIntant appIntant) {
	    try {
			String userId =(String) request.getSession().getAttribute("UserName");
			ecmAppIntantUpgService.starEarlyAppIntant(appIntant,userId);
			ProgressMessageUtil.stopProgress("" + userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
		
	//后升级启动
	@RequestMapping("startLateEcmAppInstant")
	public void startLateAppInstant(HttpServletRequest request, PrintWriter printWriter,EcmAppIntant appIntant) {
		try {
			String userId =(String) request.getSession().getAttribute("UserName");
			ecmAppIntantUpgService.startLateAppIntant(appIntant,userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	//第三步验证失败回退
	@RequestMapping("earlyBackEcmAppInstant")
	public void earlyBackEcmAppInstant(HttpServletRequest request, PrintWriter printWriter,int appId) {
		try {
			String userId =(String) request.getSession().getAttribute("UserName");
			ecmAppIntantUpgService.earlybackAppIntant(appId,userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	//第六步验证失败回退
	@RequestMapping("lateBackEcmAppInstant")
	public void lateBackEcmAppInstant(HttpServletRequest request, PrintWriter printWriter,Integer appId) {
		try {
			String userId =(String) request.getSession().getAttribute("UserName");
			ecmAppIntantUpgService.latebackAppIntant(appId,userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}	
	//第一步部署失败回滚
	@RequestMapping("earlyFailBack")
	public void earlyFailBackInstant(HttpServletRequest request, PrintWriter printWriter,EcmAppUpginfo upgInfo) {
		try {
			String userId =(String) request.getSession().getAttribute("UserName");
			ecmAppUpgBtnMidService.earlyFailBackInstant(upgInfo,userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

	}
	//第四步部署失败回滚
	@RequestMapping("lateFailBack")
	public void lateFailBackInstant(HttpServletRequest request, PrintWriter printWriter,EcmAppUpginfo upgInfo) {
		try {
			String userId =(String) request.getSession().getAttribute("UserName");
			ecmAppUpgBtnMidService.lateFailBackInstant(upgInfo,userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
}
