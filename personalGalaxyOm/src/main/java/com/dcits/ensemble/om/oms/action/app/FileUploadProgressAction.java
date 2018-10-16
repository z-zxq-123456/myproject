package com.dcits.ensemble.om.oms.action.app;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


/**
 * FileUploadProgressAction* 
 * @author tangxlf
 * @date 2015-08-17
 */
@Controller
public class FileUploadProgressAction {
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());	
   
	@RequestMapping("findFileProgress")
	public void findFileProgress(HttpServletRequest request,PrintWriter printWriter) {
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put(SysConfigConstants.progressName,request.getSession().getAttribute(SysConfigConstants.progressName));
			result.put(SysConfigConstants.fileTotalSize,request.getSession().getAttribute(SysConfigConstants.fileTotalSize));
			log.info("progressName="+request.getSession().getAttribute(SysConfigConstants.progressName)
					+"  fileTotalSize="+request.getSession().getAttribute(SysConfigConstants.fileTotalSize));
			ActionResultWrite.setMapResult(printWriter,result);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

	}
	
	

}
