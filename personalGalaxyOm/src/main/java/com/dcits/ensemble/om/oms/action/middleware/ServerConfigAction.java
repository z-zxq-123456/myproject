package com.dcits.ensemble.om.oms.action.middleware;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.alibaba.fastjson.JSON;
import com.dcits.ensemble.om.oms.module.middleware.RedisServerInfo;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.List;
import java.util.HashMap;


@Controller
public class ServerConfigAction {
	

	private int max_req_no = 0;
	
	@Resource
	PkServiceUtil  serviceUtil;
	
	@Resource
	IService omsBaseService;
	
	@RequestMapping("saveServerConfig")
	public void save(HttpServletRequest request, PrintWriter printWriter,RedisServerInfo serverInfo) {
		try {
			serverInfo.setUserId((String)request.getSession().getAttribute("UserName"));
			int serverId=serviceUtil.getMaxReqID(max_req_no,"redis_server_info","server_id");
			serverInfo.setServerId(serverId);
			//System.out.println("server_id="+serverId+ " serverIp ="+serverInfo.getServerIp() +  " serverPort ="+serverInfo.getServerPort());
			omsBaseService.insert(serverInfo);
			//ActionResultWrite.setsuccessfulResult(printWriter);
			HashMap result = new HashMap();
			result.put("success", "success");
			result.put("id", serverId);
			String jsonStr = JSON.toJSONString(result);
			printWriter.print(jsonStr);
			printWriter.flush();
			printWriter.close();

		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	

	@RequestMapping("updateServerConfig")
	public void update(HttpServletRequest request, PrintWriter printWriter,RedisServerInfo serverInfo) {
		try {
			//System.out.println("server_id="+serverInfo.getServerId()+ " serverIp ="+serverInfo.getServerIp() +  " serverPort ="+serverInfo.getServerPort());
			omsBaseService.updateByPrimaryKey(serverInfo);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	
	@RequestMapping("deleteServerConfig")
	public void delete(HttpServletRequest request, PrintWriter printWriter,RedisServerInfo serverInfo) {
		 try {
			 //System.out.println("server_id="+serverInfo.getServerId()+ " serverIp ="+serverInfo.getServerIp() +  " serverPort ="+serverInfo.getServerPort());
			 omsBaseService.deleteByPrimaryKey(serverInfo);
			 ActionResultWrite.setsuccessfulResult(printWriter);
		 }catch (Exception e){
			 e.printStackTrace();
			 ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		 }
	}

	@RequestMapping("findServerConfig")
	public void find(HttpServletRequest request, PrintWriter printWriter) {
		try {
			List<RedisServerInfo> infoList = null;
			RedisServerInfo info = new RedisServerInfo();
			//System.out.println("userId="+request.getSession().getAttribute("userId"));
			info.setUserId((String)request.getSession().getAttribute("UserName"));
			infoList= omsBaseService.findListByCond(info);
			ActionResultWrite.setReadResult(printWriter,infoList);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
}
