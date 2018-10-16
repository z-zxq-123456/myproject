package com.dcits.ensemble.om.oms.action.middleware;

import com.alibaba.fastjson.JSON;


import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.module.middleware.ServerModel;
import com.dcits.ensemble.om.oms.service.middleware.MonitorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author maruie
 * @date   2016-01-12
 * ServerInfoAction
 * redis 清空缓存
 */
@Controller
@RequestMapping("emptyCatch")
public class ServerInfoAction {
	@Resource
	MonitorService service;

	@RequestMapping("findserverinfo")
	public void findServerInfo(HttpServletRequest request, PrintWriter printWriter) {
		String serverUrl = request.getParameter("redisUrl");
		String masterName = request.getParameter("masterName");
        service.clearServer(serverUrl);
		ServerModel model= service.getServerRows(serverUrl, masterName);
		if(model == null) {
            model = new ServerModel();
            model.setKeyNum("");
        }
		printWriter.print(JSON.toJSONString(model));
		printWriter.flush();
		printWriter.close();
	}


	@RequestMapping("clearserverinfo")
	public void clearServerInfo(HttpServletRequest request, PrintWriter printWriter) {
		String serverUrl = request.getParameter("redisUrl");
		String masterName = request.getParameter("masterName");
		service.clearCacheRowsAndCacheDB(serverUrl, masterName);
		ActionResultWrite.setsuccessfulResult(printWriter);
	}
}
