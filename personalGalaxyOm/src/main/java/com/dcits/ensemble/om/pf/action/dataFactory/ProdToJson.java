package com.dcits.ensemble.om.pf.action.dataFactory;

import com.alibaba.fastjson.JSON;
import com.dcits.dynamic.web.util.ResourcesUtils;
import com.dcits.ensemble.om.pf.util.ProdJsonBundlingAndSend;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaCircleFlowDao;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaCircleFlow;
import com.dcits.ensemble.om.pm.util.ParaMeterUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ligan on 2016/8/2.
 */
@Controller
@RequestMapping("/ProdToJson")
public class ProdToJson {
       @Resource
    private ParaCircleFlowDao paraCircleFlowDao;
    @RequestMapping("/End")
         public void End(HttpServletRequest request, PrintWriter printWriter){
        String reqNum=request.getParameter("reqNum");
        String[] tableNames=request.getParameter("tableNames").split(",");
        String transActionId=request.getParameter("transActionId");
        Boolean end= ProdJsonBundlingAndSend.ProdJsonStartOrEnd(reqNum, request, "End",tableNames,transActionId);
        Map result =new HashMap();
        if(end==true) {
            result.put("retStatus", "S");
            result.put("retMsg", "提交成功");
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/Start")
    public void Start(HttpServletRequest request, PrintWriter printWriter){
        String userId = ResourcesUtils.getSystemUser(request);
        String ipAddress = ResourcesUtils.getClientIP(request);
        String reqNum=request.getParameter("reqNum");
        String tableName=request.getParameter("tableName");
        String tableDesc=request.getParameter("tableDesc");
        String tableNamesS=request.getParameter("tableNames");
        String transActionId=request.getParameter("transActionId");
        Map<String, String> result = new HashMap<>();
        if(reqNum==null||"".equals(reqNum)) {
           ParaMeterUtils.getApplicationApproval(result, tableName, "apply", userId, ipAddress, tableDesc, "");
           reqNum=result.get("appNo");
           ParaCircleFlow paraCircleFlow=  paraCircleFlowDao.selectByPrimaryKey(new ParaCircleFlow(), reqNum);
           result.put("currentStatus",paraCircleFlow.getCurrentStatus());
        }else{
            result.put("appNo", reqNum);
        }
        if(tableNamesS!=null)
        {
            String[]   tableNames=tableNamesS.split(",");
            ProdJsonBundlingAndSend.ProdJsonStartOrEnd(reqNum, request, "Start", tableNames, transActionId);
        }
        result.put("retStatus", "S");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
}
