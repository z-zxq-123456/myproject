package com.dcits.ensemble.om.oms.action.leaderview;

import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareoprInfo;
import com.dcits.ensemble.om.oms.module.utils.PageData;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * EcmMidwareOperViewAction* 
 * @author tangxlf
 * @date 2016-05-26
 */
@Controller
public class EcmMidwareOperViewAction {

	    private final Logger log = LoggerFactory.getLogger(this.getClass());
		
		@Resource
		IService omsBaseService;
		
		@Resource
		ParamComboxService paramComboxService;
		
		@RequestMapping("findMidwareOperInfo")
		public void find(HttpServletRequest request, PrintWriter printWriter,EcmMidwareoprInfo midwareOperInfo) {
			try {
				int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
				int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 10);
				log.info("pageNo =" + pageNo + "pageSize =" + pageSize);
				if(midwareOperInfo.getUserId()!=null&&midwareOperInfo.getUserId().equals("")) {
					midwareOperInfo.setUserId(null);
				}
				if(midwareOperInfo.getMidwareType()!=null&&midwareOperInfo.getMidwareType().equals("")) {
					midwareOperInfo.setMidwareType(null);
				}
				if(midwareOperInfo.getMidwareOperType()!=null&&midwareOperInfo.getMidwareOperType().equals("")) {
					midwareOperInfo.setMidwareOperType(null);
				}
				if (midwareOperInfo.getStartTime() != null && midwareOperInfo.getEndTime() != null) {
					midwareOperInfo.setStartTime(rmSomething(midwareOperInfo.getStartTime()) + " 00:00:00");
					midwareOperInfo.setEndTime(rmSomething(midwareOperInfo.getEndTime()) + " 23:59:59");
				}
				PageData<EcmMidwareoprInfo> pageData = omsBaseService.findPageByCond(midwareOperInfo, pageNo, pageSize);
				ActionResultWrite.setReadResult(printWriter, magicList(pageData.getList()));
			} catch (Exception e) {
				e.printStackTrace();
				ActionResultWrite.setErrorResult(printWriter, e.getMessage());
			}
		}
	     /**
	      * 通过遍历appoperInfo，将操作类型有由code转为name
	      */
		 private List<EcmMidwareoprInfo> magicList(List<EcmMidwareoprInfo> list) {
				for(EcmMidwareoprInfo midwareOperInfo:list){
					midwareOperInfo.setMidwareOperTypeName(paramComboxService.getParaName(midwareOperInfo.getMidwareOperType()));
					midwareOperInfo.setMidwareTypeName(paramComboxService.getParaName(midwareOperInfo.getMidwareType()));
					if(midwareOperInfo.getInstantType()!=null){
						midwareOperInfo.setInstantTypeName(paramComboxService.getParaName(midwareOperInfo.getInstantType()));
					}
				}
				return list;
	     }
	     /**
	      * 去除日期中的"-"字符
	      * @parm String 字符
	      */
		 private String rmSomething(String str) {
			return str.replaceAll("-", "");
		 }
}