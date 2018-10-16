package com.dcits.ensemble.om.oms.action.app;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.module.app.FwTranInfo;
import com.dcits.ensemble.om.oms.module.log.EcmTcecirStat;
import com.dcits.ensemble.om.oms.module.shshpoc.EcmAppmoniIndexRec;
import com.dcits.ensemble.om.oms.module.utils.PageData;
import com.dcits.ensemble.om.oms.service.log.EcmTcechainTraceService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FwTranInfoAction* 
 * @author tangxlf
 * @date 2016-05-16
 */
@Controller
public class FwTranInfoAction {	


	@Resource
	IService omsBaseService;
	
	@Resource
	ParamComboxService paramComboxService;

	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	
	@RequestMapping("findFwTranInfo")
	public void find(HttpServletRequest request, PrintWriter printWriter ,FwTranInfo info) {
		try {
//			int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
//			int pageSize = ServletRequestUtils.getIntParameter(request, "rows",10);
//			log.info("pageNo ="+pageNo +"pageSize ="+ pageSize);
			Map<String, Object> appinst = new HashMap<String, Object>();
			if(info.getStartTime()==null&&info.getEndTime()==null) {
				appinst.put("startTime",info.getStartTime()) ;
				appinst.put("endTime",info.getEndTime());
			}else {
				appinst.put("startTime",rmSomething(info.getStartTime()) );
				appinst.put("endTime",rmSomething(info.getEndTime()));
			}
			log.info("info=" + info);
			Map<String, Integer> appHead = ActionResultWrite.getPaginationNumber(request);
			PageData<FwTranInfo> pageData=null;
	//		pageData =omsBaseService.findPageByCond(info,pageNo,pageSize,appinst);
//			ActionResultWrite.setPageReadResult(printWriter, magicList(pageData.getList()), pageData.getTotalNum());
			if(null!=appHead) {
				int totalNum = appHead.get("TOTAL_NUM");
				int currentNum = appHead.get("CURRENT_NUM");//获得本页记录总数,当前记录号
				pageData = omsBaseService.findPageByCond(info, getPagNo(currentNum, totalNum), totalNum,appinst);
			}
			if(null!=pageData) {
				ActionResultWrite.setPaginationQueryDataResult(request, printWriter, magicList(pageData.getList()), pageData.getTotalNum());
			}else{
				ActionResultWrite.setReadResult(printWriter, magicList(new ArrayList<FwTranInfo>()));}
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}


	private List<FwTranInfo> magicList(List<FwTranInfo> list) {		
		for (int i = 0 ; i < list.size() ;i++) {
			list.get(i).setDemoInMsg("<span onMouseOver='colOverInMsg("+i+")' onMouseOut='colOutInMsg("+i+")'>详细信息</span>");
			list.get(i).setDemoOutMsg("<span onMouseOver='colOverOutMsg("+i+")' onMouseOut='colOutOutMsg("+i+")'>详细信息</span>");
		}		
		return list;
	}

	/**
     * 去除日期中的"-"字符
     * @parm String 字符串
     */
	  private String rmSomething(String str) {
		    return str.replaceAll("-", "");
		
	  }
	/**
	 *
	 * @param currentNum 当前索引
	 * @param totalNum  每页总记录数
	 * @return
	 */
	public static int getPagNo(int currentNum ,int totalNum){
		return currentNum/totalNum+1;
	}
}
