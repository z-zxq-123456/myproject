package com.dcits.ensemble.om.oms.action.leaderview;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.app.EcmAppUpgfor;
import com.dcits.ensemble.om.oms.module.app.EcmAppUpginfo;
import com.dcits.ensemble.om.oms.module.app.EcmAppValrule;
import com.dcits.ensemble.om.oms.module.utils.PageData;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EcmAppUpgViewAction*
 * @author luolang
 * @date 2016-4-7
 */
@Controller
public class EcmAppUpgViewAction {
	 
	    private final Logger log = LoggerFactory.getLogger(this.getClass());
	    @Resource
		ParamComboxService  paramComboxService;
		@Resource
		IService omsBaseService;
		
		@RequestMapping("findEcmAppUpgView")
		public void find(HttpServletRequest request, PrintWriter printWriter,String appId) {
			try {
				int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
				int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 10);
				log.info("pageNo =" + pageNo + "pageSize =" + pageSize);
				Map<String, Object> appUpg = new HashMap<String, Object>();
				if(appId==null||appId==""){
					appUpg.put("appId", null);
				}else {
					appUpg.put("appId", appId);
				}
				appUpg.put("appUpgStatus",SysConfigConstants.APP_UPGSTATUS_PROGRESS);
				if(request.getParameter("startTime")==null&&request.getParameter("endTime")==null) {
					appUpg.put("startTime",request.getParameter("startTime")) ;
					appUpg.put("endTime",request.getParameter("endTime"));
				}else {
					appUpg.put("startTime",rmSomething(request.getParameter("startTime")) );
					appUpg.put("endTime",rmSomething(request.getParameter("endTime")));
				}
				PageData<EcmAppUpginfo> pageData = omsBaseService.findPageByCond(new EcmAppUpginfo(),"findUpgList",pageNo, pageSize, appUpg);
				if(pageData.getList().size()==0){
					JSONObject resultJson = new JSONObject();
					resultJson.put("data", new JSONArray());
					printWriter.print(resultJson.toJSONString());
					printWriter.flush();
					printWriter.close();
					return;
				}
				ActionResultWrite.setReadResult(printWriter,magicList(pageData.getList()));
			}catch (Exception e){
				e.printStackTrace();
				ActionResultWrite.setErrorResult(printWriter,e.getMessage());
			}
		}
		
		//根据APPUPGID查找出应用升级验证规则
		@RequestMapping("findAppValrule")
		public void finAppValrules(HttpServletRequest request, PrintWriter printWriter,Integer appUpgId) {
			try {
				Map<String,Object> ruleMap = new HashMap<String,Object>();
				ruleMap.put("appUpgId", appUpgId);
				List<EcmAppValrule> ruleList = omsBaseService.findListByCond(new EcmAppValrule(),"findValruleByUpgId",ruleMap);
				ActionResultWrite.setReadResult(printWriter, magicListValrule(ruleList));
			}catch (Exception e){
				e.printStackTrace();
				ActionResultWrite.setErrorResult(printWriter,e.getMessage());
			}
		}
		private List<EcmAppValrule> magicListValrule(List<EcmAppValrule> ruleList) {
			for(EcmAppValrule appValrule:ruleList) {
				appValrule.setAppValruleTypeName(paramComboxService.getParaName(appValrule.getAppValruleType()));
				
			}
			return ruleList;
		}
		private List<EcmAppUpginfo> magicList(List<EcmAppUpginfo> upgList) {
			for(EcmAppUpginfo appUpginfo:upgList) {
				appUpginfo.setAppUpgStatusName(paramComboxService.getParaName(appUpginfo.getAppUpgStatus()));
				
			}
			return upgList;
		}
		
		//根据应用升级ID查找应用升级扭转信息
		@RequestMapping("findAppUpgfor")
		public void findUpgfor(HttpServletRequest request, PrintWriter printWriter,Integer appUpgId) {
			try {
				Map<String,Object> upgforMap = new HashMap<String,Object>();
				upgforMap.put("appUpgId", appUpgId);
				List<EcmAppUpgfor> upgforList = omsBaseService.findListByCond(new EcmAppUpgfor(),upgforMap);
				ActionResultWrite.setReadResult(printWriter, upgforList);
			}catch (Exception e){
				e.printStackTrace();
				ActionResultWrite.setErrorResult(printWriter,e.getMessage());
			}
		}
		
		   /**
	       * 去除日期中的"-"字符
	       * @parm String 字符串
	       */
	    private String rmSomething(String str) {
		    return str.replaceAll("-", "");
			
		}
}



			

	


