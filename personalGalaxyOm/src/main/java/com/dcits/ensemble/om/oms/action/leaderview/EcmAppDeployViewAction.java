package com.dcits.ensemble.om.oms.action.leaderview;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.module.app.EcmAppinstDeploy;
import com.dcits.ensemble.om.oms.module.utils.PageData;
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
import java.util.Map;

/**
 * @author luolang
 * @date 2015-9-21
 */
@Controller
public class EcmAppDeployViewAction {
	
	    private final Logger log = LoggerFactory.getLogger(this.getClass());
		
		@Resource
		IService omsBaseService;
		
		@RequestMapping("findEcmAppDeployView")
		public void find(HttpServletRequest request, PrintWriter printWriter,String userId,String appId) {
			try {
				int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
				int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 10);
				log.info("pageNo =" + pageNo + "pageSize =" + pageSize);
				Map<String, Object> appinst = new HashMap<String, Object>();
				if(userId==""||userId==null){
					appinst.put("userId",null);
				}else {
					appinst.put("userId", userId);
				}
				if(appId==null||appId==""){
					appinst.put("appId",null);
				}else {
					appinst.put("appId",appId);
				}

				if(request.getParameter("startTime")==null&&request.getParameter("endTime") ==null) {
					appinst.put("startTime",request.getParameter("startTime")) ;
					appinst.put("endTime",request.getParameter("endTime"));
				}else {
					appinst.put("startTime",rmSomething(request.getParameter("startTime")) );
					appinst.put("endTime",rmSomething(request.getParameter("endTime")));
				}
				PageData<EcmAppinstDeploy> pageData = omsBaseService.findPageByCond(new EcmAppinstDeploy(), pageNo, pageSize, appinst);
				if(pageData.getList().size()==0){
					JSONObject resultJson = new JSONObject();
					resultJson.put("data", new JSONArray());
					printWriter.print(resultJson.toJSONString());
					printWriter.flush();
					printWriter.close();
					return;
				}
				ActionResultWrite.setReadResult(printWriter,pageData.getList());
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



			

	


