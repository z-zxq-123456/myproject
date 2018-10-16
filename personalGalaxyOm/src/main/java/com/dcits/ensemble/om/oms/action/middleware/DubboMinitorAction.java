package com.dcits.ensemble.om.oms.action.middleware;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.service.middleware.DubboMinitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * DubboMinitorAction* 
 * @author Luolang
 * @date 2016-02-23
 */
@Controller
public class DubboMinitorAction {
	
	@Resource
	DubboMinitorService dubboMinitorService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@RequestMapping("findDubboInfoList")
	public void findMenuList(HttpServletRequest request, PrintWriter printWriter) {
		try {
			String midwareId = request.getParameter("midwareId");
			if (midwareId.equals("")){
				ActionResultWrite.setReadResult(printWriter, null);
			}else {
				Long  startTime= System.currentTimeMillis();
				List<Map<String, Object>> infoList = dubboMinitorService.getDubboInfoThread(Integer.parseInt(midwareId));
				Long  endTime= System.currentTimeMillis();
				log.info("线程执行时间+zkconnectTime：" + (endTime - startTime));
				ActionResultWrite.setReadResult(printWriter, infoList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter, e.getMessage());
		}
	}
}
