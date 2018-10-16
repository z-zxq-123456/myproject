package com.dcits.ensemble.om.oms.action.fullgalaxy;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.fullgalaxy.EcmServerIndex;
import com.dcits.ensemble.om.oms.service.fullgalaxy.FullServerMonitorService;
import com.dcits.ensemble.om.oms.service.fullgalaxy.FullServerViewService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * FullSeverMonitorAction*
 * @author luolang
 * @date 2017-01-9
 */
@Controller
public class FullSeverMonitorAction {

	@Resource
	FullServerMonitorService serverMonitorService;
	@Resource
	ParamComboxService paramComboxService;

	@RequestMapping("findServerMonitorInfo")
	public void find(HttpServletRequest request,PrintWriter printWriter) {
		try {
            String serId = request.getParameter("serId");
			EcmServerIndex serverIndex = serverMonitorService.getLastServerMonitorInfo(serId);
			if(null!=serverIndex) {
				serverIndex.setRefreshTime(paramComboxService.getParaRemark1(SysConfigConstants.FULL_GALAXY_REFRESH_TIME));
				serverIndex.setSerMoniMs(getMS(serverIndex.getSerMoniDate(),serverIndex.getSerMoniTime()));
				List<EcmServerIndex> serverList = new ArrayList<EcmServerIndex>();
				serverList.add(serverIndex);
				ActionResultWrite.setReadResult(printWriter, serverList);
			}else {
				ActionResultWrite.setErrorResult(printWriter , "");
			}
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
    //将日期时间转为毫秒
	private long getMS(String date ,String time) throws ParseException {
        String newTime = time.replaceAll(":","");
		String realTime = date+newTime;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		long millionSeconds = sdf.parse(realTime).getTime();//毫秒
		return millionSeconds;
	}
	
}
