package com.dcits.ensemble.om.oms.action.log;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.manager.dubbo.DubboServcieAppIntatNameParse;
import com.dcits.ensemble.om.oms.module.app.EcmAppInfo;
import com.dcits.ensemble.om.oms.module.log.EcmTraceChain;
import com.dcits.ensemble.om.oms.module.log.EcmTraceCircle;
import com.dcits.ensemble.om.oms.service.log.EcmTcechainTraceService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * EcmTraceCircleAction*
 *
 * @author LuoLang
 * @date 2016-10-21
 */
@Controller
public class EcmTraceCircleAction {

    @Resource
    ParamComboxService paramComboxService;
    @Resource
    EcmTcechainTraceService tcechainTraceService;
    @Resource
    IService omsBaseService;
    @Resource
    DubboServcieAppIntatNameParse dubboServcieAppIntatNameParse;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @RequestMapping("findEcmCircleDetailInfo")
    public void find(HttpServletRequest request, PrintWriter printWriter) {
        try {
            String cirId = request.getParameter("cirId");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cirId", cirId);
            List<EcmTraceCircle> annotList = omsBaseService.findListByCond(new EcmTraceCircle(), "findCircle", map);
            ActionResultWrite.setReadResult(printWriter, magicList(annotList));
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }


    @RequestMapping("findCircleInfoByTraceId")
    public void findCircle(HttpServletRequest request, PrintWriter printWriter) {
        try {
            String traceId = request.getParameter("traceId");
            EcmTraceChain traceChain = new EcmTraceChain();
            traceChain.setTraceId(traceId);
            List<EcmTraceCircle> circleList = tcechainTraceService.getTceCircleInfo(traceChain);
            ActionResultWrite.setReadResult(printWriter, magicCircleList(magicList(circleList)));
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }

    private List<EcmTraceCircle> magicList(List<EcmTraceCircle> list) {
        for(EcmTraceCircle traceCircle:list) {
            traceCircle.setCirClientStatusName(paramComboxService.getParaName(traceCircle.getCirClientStatus()));
            if(traceCircle.getCirType().equals(SysConfigConstants.CIR_TYPE_RPC)) {
                traceCircle.setCirServerStatusName(paramComboxService.getParaName(traceCircle.getCirServerStatus()));
            }
            traceCircle.setCirTypeName(paramComboxService.getParaName(traceCircle.getCirType()));
        }
        return list;
    }

    private List<EcmTraceCircle> magicCircleList(List<EcmTraceCircle> list) {
        for(EcmTraceCircle traceCircle :list) {
            if(null==traceCircle.getCirServerIpport()) {
            traceCircle.setAppIntantName(" ");
            } else
            traceCircle.setAppIntantName(dubboServcieAppIntatNameParse.providerIpParse(traceCircle.getCirServerIpport().split(":")[0],traceCircle.getCirServerIpport().split(":")[1]));
        }
        return list;
    }
}
