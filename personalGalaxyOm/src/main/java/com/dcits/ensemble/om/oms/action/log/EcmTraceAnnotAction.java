package com.dcits.ensemble.om.oms.action.log;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.module.log.EcmTraceAnnot;
import com.dcits.ensemble.om.oms.module.log.EcmTraceChain;
import com.dcits.ensemble.om.oms.service.log.EcmLogInfoService;
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
 * EcmTraceAnnotAction*
 *
 * @author LuoLang
 * @date 2016-10-21
 */
@Controller
public class EcmTraceAnnotAction {



    @Resource
    EcmLogInfoService logInfoService;
    @Resource
    IService omsBaseService;
    @Resource
    ParamComboxService paramComboxService;

    private  final Logger log = LoggerFactory.getLogger(this.getClass());


    @RequestMapping("findEcmAnnotDetailInfo")
    public void find(HttpServletRequest request, PrintWriter printWriter) {
        try {
            String cirId = request.getParameter("cirId");
            Map<String ,Object> map = new HashMap<String,Object>();
            map.put("cirId",cirId);
            List<EcmTraceAnnot> annotList = omsBaseService.findListByCond(new EcmTraceAnnot(),"findTraceAnnotList",map);
            ActionResultWrite.setReadResult(printWriter, annotList);
        }catch (Exception e){
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
    }

    @RequestMapping("findTransactionLogInfo")
    public void findAll(HttpServletRequest request, PrintWriter printWriter) {
        try {
            String traceId = request.getParameter("traceId");
            Map<String ,Object> map = new HashMap<String,Object>();
            map.put("traceId",traceId);
            List<EcmTraceAnnot> annotList = logInfoService.getLogInfo(traceId);
        //    ActionResultWrite.setReadResult(printWriter, annotList);
            ActionResultWrite.setReadResult(printWriter, magicList(annotList));
        }catch (Exception e){
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
    }
    private List<EcmTraceAnnot> magicList(List<EcmTraceAnnot> list) {
        for (EcmTraceAnnot para : list) {
            para.setAnnotTypeName(paramComboxService.getParaName(para.getAnnotType()));
        }
        return list;
    }

}
