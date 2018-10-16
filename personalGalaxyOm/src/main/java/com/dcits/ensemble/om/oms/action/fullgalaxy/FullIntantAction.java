package com.dcits.ensemble.om.oms.action.fullgalaxy;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.service.fullgalaxy.FullIntantService;
import com.dcits.ensemble.om.oms.service.middleware.FullDataBaseIntantService;
import com.dcits.ensemble.om.oms.service.middleware.FullRedisIntantService;
import com.dcits.ensemble.om.oms.service.middleware.FullZookeeperIntantService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;


/**
 * FullIntantAction 全量实例管理action 管理所有应用实例、所有中间件实例* 
 * @author tangxlf
 * @date 2016-05-12
 */
@Controller
public class FullIntantAction {
	

	
	@Resource
	FullIntantService fullIntantService;
	@Resource
	FullRedisIntantService fullRedisIntantService;
	@Resource
	FullZookeeperIntantService fullZookeeperIntantService;
	@Resource
	FullDataBaseIntantService fullDataBaseIntantService;
	
	@RequestMapping("deployFullIntant")
	public void deployFullIntant(HttpServletRequest request, PrintWriter printWriter,String isRemainConfig) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			fullIntantService.deployFullIntant(userId,isRemainConfig);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	@RequestMapping("startFullIntant")
	public void startFullIntant(HttpServletRequest request, PrintWriter printWriter) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			fullIntantService.startFullIntant(userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

	}
	
	@RequestMapping("reStartFullIntant")
	public void reStartFullIntant(HttpServletRequest request, PrintWriter printWriter) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			fullIntantService.reStartFullIntant(userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

	}
	
	@RequestMapping("stopFullIntant")
	public void stopFullIntant(HttpServletRequest request, PrintWriter printWriter) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			fullIntantService.stopFullIntant(userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	@RequestMapping("clearFullIntant")
	public void clearFullIntant(HttpServletRequest request, PrintWriter printWriter) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			fullIntantService.clearFullIntant(userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	
	@RequestMapping("findFullRedisGroup")
	public void findFullRedisGroup(HttpServletRequest request,PrintWriter printWriter) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			ActionResultWrite.setReadResult(printWriter, fullRedisIntantService.findFullRedisAndIntantList(userId));
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

	}
	
	@RequestMapping("findFullZookeeperGroup")
	public void findFullZookeeperGroup(HttpServletRequest request,PrintWriter printWriter) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			ActionResultWrite.setReadResult(printWriter, fullZookeeperIntantService.findFullZookeeperAndIntantList(userId));
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

	}
	
	@RequestMapping("findFullDatabaseGroup")
	public void findFullDatabaseGroup(HttpServletRequest request,PrintWriter printWriter) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			ActionResultWrite.setReadResult(printWriter, fullDataBaseIntantService.findFullDataBaseAndIntantList(userId));
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
}
