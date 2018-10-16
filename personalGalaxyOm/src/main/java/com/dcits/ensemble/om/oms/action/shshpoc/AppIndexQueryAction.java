package com.dcits.ensemble.om.oms.action.shshpoc;

import java.io.PrintWriter;
import java.util.HashMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.dcits.ensemble.om.oms.service.shshpoc.AppIndexResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
//import com.dcits.ensemble.om.oms.action.utils.ActionResultWrite;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.module.shshpoc.EcmAppmoniIndexRec;
import com.dcits.ensemble.om.oms.service.shshpoc.AppIndexQueryService;

/**
 * AppIndexQueryAction* 
 * @author tangxlf
 * @createdate 2016-07-28
 */

@Controller
public class AppIndexQueryAction {
	
	
    @Resource
    AppIndexQueryService  appIndexQueryService;
    
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());

   
    @RequestMapping("findAppIndexMonitor")
    public void findAppIndexMonitor(HttpServletRequest request, PrintWriter printWriter,EcmAppmoniIndexRec  appmoniIndexRec) {
        try {
            int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
            int pageSize = ServletRequestUtils.getIntParameter(request, "rows",10);
            System.out.println("appmoniIndexRec="+appmoniIndexRec+" pageNo ="+pageNo +" pageSize ="+ pageSize);
            ActionResultWrite.setReadResult(printWriter,appIndexQueryService.findAppIndex(appmoniIndexRec, pageNo, pageSize,0));
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
	}
    
    @RequestMapping("findAppIndexQuery")
    public void findAppIndexQuery(HttpServletRequest request, PrintWriter printWriter,EcmAppmoniIndexRec  appmoniIndexRec) {
        try{
            int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
            int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 10);
            System.out.println("appmoniIndexRec="+appmoniIndexRec+" pageNo ="+pageNo +" pageSize ="+ pageSize);
            ActionResultWrite.setReadResult(printWriter,appIndexQueryService.findAppIndex(appmoniIndexRec, pageNo, pageSize,1));
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
    }
	}
    
}

