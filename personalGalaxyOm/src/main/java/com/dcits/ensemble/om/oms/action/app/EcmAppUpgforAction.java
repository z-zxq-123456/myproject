package com.dcits.ensemble.om.oms.action.app;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.common.ProgressMessageUtil;
import com.dcits.ensemble.om.oms.manager.dubbo.UpgAppIntantCache;
import com.dcits.ensemble.om.oms.module.app.EcmAppUpginfo;
import com.dcits.ensemble.om.oms.service.app.EcmAppUpgBtnMidService;
import com.dcits.ensemble.om.oms.service.app.EcmAppUpgforService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

/**
 * EcmAppUpgforAction* 
 * @author wangbinaf
 * @date 2016-03-010
 */
@Controller
public class EcmAppUpgforAction {	
	//private  final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	EcmAppUpgforService ecmAppUpgforService;
	@Resource
	UpgAppIntantCache upgAppIntantCache;
	@Resource
	EcmAppUpgBtnMidService ecmAppUpgBtnMidService;
	
	//根据按钮更新相应的信息，当前按钮是正常的下一步时调用，对应的是第一，四步的部署完成按钮
	@RequestMapping("updateProcess")
	public void updateProcess(HttpServletRequest request, PrintWriter printWriter,EcmAppUpginfo upgInfo ) {
		try {
			String userId = (String )request.getSession().getAttribute("UserName");
			ecmAppUpgBtnMidService.updateProcess(upgInfo,userId );
			ProgressMessageUtil.stopProgress("" + userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}

	//先升级启动，对应的是第二步的先升级启动按钮
//	@RequestMapping("earlyUpgStart")
//	public void earlyUpgStart(HttpServletRequest request, PrintWriter printWriter,EcmAppUpginfo upgInfo) {
//		try {
//			String userId = (String )request.getSession().getAttribute("UserName");
//			ecmAppUpgBtnMidService.earlyUpgStart(upgInfo,userId );
//			ProgressMessageUtil.stopProgress("" + userId);
//			ActionResultWrite.setsuccessfulResult(printWriter);
//		}catch (Exception e){
//			e.printStackTrace();
//			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
//		}
//	}
	//先升级上线，对应的是第三步的先升级上线按钮
	@RequestMapping("earlyUpgOnline")
	public void earlyUpgOnline(HttpServletRequest request, PrintWriter printWriter,EcmAppUpginfo upgInfo) {
		try {
			String userId = (String )request.getSession().getAttribute("UserName");
			ecmAppUpgBtnMidService.earlyUpgOnline(upgInfo,userId );
			ProgressMessageUtil.stopProgress("" + userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	//第三步验证失败回退函数,还原部署之前的状态，对应的是第三步的验证失败回退按钮
	@RequestMapping("earlyUpgBack")
	public void earlyUpgBack(HttpServletRequest request, PrintWriter printWriter,EcmAppUpginfo upgInfo) {
		try {
			String userId = (String )request.getSession().getAttribute("UserName");
			ecmAppUpgBtnMidService.earlyUpgBack(upgInfo,userId );
			ProgressMessageUtil.stopProgress("" + userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e ){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}

	//后升级启动，对应的是第五步的后升级启动按钮
//	@RequestMapping("lateUpgStart")
//	public void lateUpgStart(HttpServletRequest request, PrintWriter printWriter,EcmAppUpginfo upgInfo) {
//		try {
//			String userId = (String )request.getSession().getAttribute("UserName");
//			ecmAppUpgBtnMidService.lateUpgStart(upgInfo,userId );
//			ProgressMessageUtil.stopProgress("" + userId);
//			ActionResultWrite.setsuccessfulResult(printWriter);
//		}catch (Exception e ){
//			e.printStackTrace();
//			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
//		}
//	}

	//后升级上线，对应的是第六步的后升级上线按钮
	@RequestMapping("lateUpgOnline")
	public void lateUpgOnline(HttpServletRequest request, PrintWriter printWriter,EcmAppUpginfo upgInfo) {
		try {
			String userId = (String )request.getSession().getAttribute("UserName");
			ecmAppUpgBtnMidService.lateUpgOnline(upgInfo,userId );
			ProgressMessageUtil.stopProgress("" + userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	//最后一步验证失败回退函数,还原部署之前的状态，对应的是第六步的验证失败回退按钮
	@RequestMapping("lateUpgBack")
	public void lateUpgBack(HttpServletRequest request, PrintWriter printWriter,EcmAppUpginfo upgInfo) {
		try {
			String userId = (String )request.getSession().getAttribute("UserName");
			ecmAppUpgBtnMidService.lateUpgBack(upgInfo,userId );
			ProgressMessageUtil.stopProgress("" + userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	//第一步部署失败回滚
	@RequestMapping("existAction")
	public void existAction(HttpServletRequest request, PrintWriter printWriter,EcmAppUpginfo upgInfo) {
		ActionResultWrite.setsuccessfulResult(printWriter);
	}
}
