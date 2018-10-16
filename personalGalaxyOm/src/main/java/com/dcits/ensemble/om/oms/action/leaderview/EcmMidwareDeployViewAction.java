package com.dcits.ensemble.om.oms.action.leaderview;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.common.DataUtil;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareinstDeploy;
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
 * EcmMidwareDeployViewAction*
 * @author tangxlf
 * @date 2016-05-26
 */
@Controller
public class EcmMidwareDeployViewAction {
	
	    private final Logger log = LoggerFactory.getLogger(this.getClass());
		
		@Resource
		IService omsBaseService;
		@Resource
		ParamComboxService paramComboxService;
		@RequestMapping("findMidwareDeployView")
		public void find(HttpServletRequest request, PrintWriter printWriter,String userId,String midwareType) {
			try {
				log.info(userId + midwareType);
				int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
				int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 10);
				Map<String, Object> appoper = new HashMap<String, Object>();
				if(!DataUtil.isNull(userId)){
					appoper.put("userId", userId);
				}
				if(!DataUtil.isNull(midwareType)){
					appoper.put("midwareType", midwareType);
				}
				if(request.getParameter("startTime")!=null&&request.getParameter("endTime")!=null) {
					appoper.put("startTime",rmSomething(request.getParameter("startTime"))+ " 00:00:00") ;
					appoper.put("endTime",rmSomething(request.getParameter("endTime")) + " 23:59:59");
				}
				log.info("appoper="+appoper);
				PageData<EcmMidwareinstDeploy> pageData = omsBaseService.findPageByCond(new EcmMidwareinstDeploy(), pageNo, pageSize, appoper);
				if(pageData.getList().size()==0){
					JSONObject resultJson = new JSONObject();
					resultJson.put("data", new JSONArray());
					printWriter.print(resultJson.toJSONString());
					printWriter.flush();
					printWriter.close();
					return;
				}
				ActionResultWrite.setReadResult(printWriter, magicList(pageData.getList()));
			} catch (Exception e) {
				e.printStackTrace();
				ActionResultWrite.setErrorResult(printWriter,e.getMessage());
			}
		}
			/**
			 * 通过遍历appoperInfo，将操作类型有由code转为name。
			 */
			private List<EcmMidwareinstDeploy> magicList(List<EcmMidwareinstDeploy> list) {
				for(EcmMidwareinstDeploy deployInfo:list){
					deployInfo.setMidwareTypeName(paramComboxService.getParaName(deployInfo.getMidwareType()));
					if(deployInfo.getInstantType()!=null){
						deployInfo.setInstantTypeName(paramComboxService.getParaName(deployInfo.getInstantType()));
					}
				}
				return list;
			}
		/**
		/
	     * 去除日期中的"-"字符
	     * @parm String 字符�?
	     */
		private String rmSomething(String str) {
		    return str.replaceAll("-", "");
		}
}



			

	


