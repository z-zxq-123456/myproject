package com.dcits.ensemble.om.oms.action.log;

import com.alibaba.fastjson.JSON;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.ensemble.om.oms.module.log.EcmLogconfInfo;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareInfo;
import com.dcits.ensemble.om.oms.module.utils.PageData;
import com.dcits.ensemble.om.oms.service.log.EcmLogconfInfoService;
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
 * EcmLogconfInfoAction*
 *
 * @author LuoLang
 * @date 2016-10-10
 */
@Controller
public class EcmLogconfInfoAction {

    @Resource
    PkServiceUtil  serviceUtil;

    @Resource
    ParamComboxService  paramComboxService;

    @Resource
    IService  omsBaseService;

    @Resource
    EcmLogconfInfoService ecmLogconfInfoService;

    private  final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("saveEcmLogconfInfo")
    public void  save(HttpServletRequest request, PrintWriter printWriter, EcmLogconfInfo logconfInfo)  {
        int logconfId = serviceUtil.getMaxReqID(0,"ECM_LOGCONF_INFO","LOG_CONF_ID");
        logconfInfo.setLogconfId(logconfId);
        ecmLogconfInfoService.save(logconfInfo);
        HashMap result = new HashMap();
        result.put("success", "success");
        result.put("id", logconfId);
        String jsonStr = JSON.toJSONString(result);
        printWriter.print(jsonStr);
        printWriter.flush();
        printWriter.close();

    }

    @RequestMapping("updateEcmLogconfInfo")
    public void update(HttpServletRequest request, PrintWriter printWriter,EcmLogconfInfo logconfInfo) {
        try {
            ecmLogconfInfoService.update(logconfInfo);
        ActionResultWrite.setsuccessfulResult(printWriter);
        } catch (Exception e){
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
    }

    @RequestMapping("deleteEcmLogconfInfo")
    public void delete(HttpServletRequest request, PrintWriter printWriter,EcmLogconfInfo logconfInfo) {
        try {
            ecmLogconfInfoService.delete(logconfInfo);
            ActionResultWrite.setsuccessfulResult(printWriter);
        } catch (Exception e){
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
    }
    @RequestMapping("findEcmLogconfInfo")
     public void find(HttpServletRequest request, PrintWriter printWriter, EcmLogconfInfo logconfInfo) {
        try {
            int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
            int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 10);
            log.info("pageNo =" + pageNo + "pageSize =" + pageSize);
            PageData<EcmLogconfInfo> pageData =omsBaseService.findPageByCond(logconfInfo,pageNo,pageSize);
            ActionResultWrite.setReadResult(printWriter, magicList(pageData.getList()));
        }catch (Exception e){
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
    }

    private List<EcmLogconfInfo> magicList(List<EcmLogconfInfo> list) {
        List<EcmMidwareInfo> midwareList = omsBaseService.findListByCond(new EcmMidwareInfo(), "findMidwareList", new HashMap<String, Object>());
        Map<Integer, String> midwareMap = new HashMap<Integer, String>();
        for (EcmMidwareInfo midware : midwareList) {
            midwareMap.put(midware.getMidwareId(), midware.getMidwareName());
        }
        for(EcmLogconfInfo logconfInfo:list){
            logconfInfo.setKkMidwareIdName(midwareMap.get(logconfInfo.getKkMidwareId()));
            logconfInfo.setZkMidwareName(midwareMap.get(logconfInfo.getMidwareId()));
            logconfInfo.setLogPlatModeName(paramComboxService.getParaName(logconfInfo.getLogPlatMode()));
            logconfInfo.setOutPrtnModeName(paramComboxService.getParaName(logconfInfo.getOutPrtnMode()));
            logconfInfo.setLogLevelName(paramComboxService.getParaName(logconfInfo.getLogLevel()));
            logconfInfo.setIsScanName(paramComboxService.getParaName(logconfInfo.getIsScan()));
        }
        return list;
    }
}
