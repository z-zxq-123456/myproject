package com.dcits.ensemble.om.oms.action.leaderview;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.module.app.EcmAppoperInfo;
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
 * EcmAppInfoAction* 
 * @author LuoLang
 * @date 2015-09-23
 */
@Controller
public class EcmAppoperInfoViewAction {

	 private final Logger log = LoggerFactory.getLogger(this.getClass());
		
		@Resource
		IService omsBaseService;
		
		@Resource
		ParamComboxService  paramComboxService;
		
		@RequestMapping("findEcmAppoperInfo")
		public void find(HttpServletRequest request, PrintWriter printWriter,String userId,String appId,String ecmAppoperType) {
			try {
				int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
				int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 10);
				log.info("pageNo =" + pageNo + "pageSize =" + pageSize);
				Map<String, Object> appoper = new HashMap<String, Object>();
				if(userId==""||userId==null){
					appoper.put("userId",null);
				}else {
					appoper.put("userId", userId);
				}
				if(appId==null||appId==""){
					appoper.put("appId",null);
				}else {
					appoper.put("appId",appId);
				}
				if(ecmAppoperType==null||ecmAppoperType==""){
					appoper.put("paraCode",null);
				}else {
					appoper.put("paraCode",ecmAppoperType);
				}
				if(request.getParameter("startTime")==null&&request.getParameter("endTime")==null) {
					appoper.put("startTime",request.getParameter("startTime")) ;
					appoper.put("endTime",request.getParameter("endTime"));
				}else {
					appoper.put("startTime",rmSomething(request.getParameter("startTime")) );
					appoper.put("endTime",rmSomething(request.getParameter("endTime")));
				}
				PageData<EcmAppoperInfo> pageData = omsBaseService.findPageByCond(new EcmAppoperInfo(), pageNo, pageSize, appoper);
				if(pageData.getList().size()==0){
					JSONObject resultJson = new JSONObject();
					resultJson.put("data", new JSONArray());
					printWriter.print(resultJson.toJSONString());
					printWriter.flush();
					printWriter.close();
					return;
				}
				ActionResultWrite.setReadResult(printWriter, magicList(pageData.getList()));
			}catch (Exception e){
				e.printStackTrace();
				ActionResultWrite.setErrorResult(printWriter,e.getMessage());
			}

     }
		    /**
		     * 通过遍历appoperInfo，将操作类型有由code转为name。
		     */
		    private List<EcmAppoperInfo> magicList(List<EcmAppoperInfo> list) {
			for(EcmAppoperInfo appoperInfo:list){
				appoperInfo.setEcmAppoperTypeName(paramComboxService.getParaName(appoperInfo.getEcmAppoperType()));
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
}