package com.dcits.ensemble.om.oms.action.fullgalaxy;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.fullgalaxy.EcmServerIndex;
import com.dcits.ensemble.om.oms.module.log.EcmTraceChain;
import com.dcits.ensemble.om.oms.module.server.EcmServerAlarm;
import com.dcits.ensemble.om.oms.module.utils.PageData;
import com.dcits.ensemble.om.oms.service.fullgalaxy.FullServerMonitorService;
import com.dcits.ensemble.om.oms.service.log.EcmTcechainTraceService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * FullSeverAlarmAction*
 * @author luolang
 * @date 2017-02-9
 */
@Controller
public class FullSeverAlarmAction {

	@Resource
	IService omsBaseService;
	@Resource
	ParamComboxService paramComboxService;
	@RequestMapping("findServerAlarmInfo")
	public void find(HttpServletRequest request,PrintWriter printWriter) {
		try {
			Map<String, Integer> appHead = ActionResultWrite.getPaginationNumber(request);
			PageData<EcmServerAlarm> pageData=null;
			if ( appHead!= null) {
				int totalNum = appHead.get("TOTAL_NUM");
				int currentNum = appHead.get("CURRENT_NUM");//获得本页记录总数,当前记录号
				pageData = omsBaseService.findPageByCond(new EcmServerAlarm(), EcmTcechainTraceService.getPagNo(currentNum, totalNum), totalNum);
			}
			if(null!=pageData) {
				ActionResultWrite.setPaginationQueryDataResult(request, printWriter, magicList(pageData.getList()), pageData.getTotalNum());
			}else{
				ActionResultWrite.setReadResult(printWriter, magicList(new ArrayList<EcmServerAlarm>()));}
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	private List<EcmServerAlarm> magicList(List<EcmServerAlarm> list) {
		for(EcmServerAlarm serverAlarm : list) {
			serverAlarm.setAlarmTypeName(paramComboxService.getParaName(serverAlarm.getAlarmType()));
		}
		return list;
	}

//    //将日期时间转为毫秒
//	private long getMS(String date ,String time) throws ParseException {
//        String newTime = time.replaceAll(":","");
//		String realTime = date+newTime;
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
//		long millionSeconds = sdf.parse(realTime).getTime();//毫秒
//		return millionSeconds;
//	}
	
}
