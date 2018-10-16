package com.dcits.ensemble.om.pf.action.dataFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.orion.stria.api.rpc.IFlowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tim on 2015/5/25.
 */


@Controller
@RequestMapping("/eventFlow")
public class MbEventFlowAction {

    private static final Logger log = LoggerFactory
            .getLogger(MbEventFlowAction.class);

    @Resource
    IFlowService iFlowService;

    @RequestMapping("/getEventFlow")
    public void getEventFlow(HttpServletRequest request, PrintWriter printWriter) {
        JSONObject out = new JSONObject();
        try {
            JSONArray flows = iFlowService.getFLow("02");// flowId 01:服务流程，02:事件流程
            out.put("data", flows);
        } catch (RuntimeException  e) {
            if (log.isDebugEnabled())
                log.debug(ExceptionUtils.getStackTrace(e));
            out.put("data", new JSONArray());
        }
        printWriter.print(out.toJSONString());
        printWriter.flush();
        printWriter.close();
    }


    @RequestMapping("/getFlowByFlowId")
    public void getFlowByFlowId(HttpServletRequest request, PrintWriter printWriter) {
        String flowId = request.getParameter("flowId");
        JSONObject flow = iFlowService.getFLowById(flowId);
        Map result = new HashMap();
        if (null == flow) {
            throw new GalaxyException("流程未定义");
        }
        result.put("retStatus", "S");
        result.put("body", flow.toJSONString());
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/deployEventFlow")
    public void deployEventFlow(HttpServletRequest request, PrintWriter printWriter) {
        Map result = new HashMap();
        result.put("retStatus", "S");
        String flowJson = request.getParameter("flowJson");
        String creater = (String) request.getSession().getAttribute("UserName");
        JSONObject flow = JSON.parseObject(flowJson);
        flow.put("flowType", "02");  // 事件流程发布
        iFlowService.deploy(flow, creater);
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
}