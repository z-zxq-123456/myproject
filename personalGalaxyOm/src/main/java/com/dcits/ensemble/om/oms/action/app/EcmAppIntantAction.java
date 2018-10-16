package com.dcits.ensemble.om.oms.action.app;

import com.alibaba.fastjson.JSON;
import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.common.ProgressMessageUtil;
import com.dcits.ensemble.om.oms.manager.app.AppConfieAutoHandler;
import com.dcits.ensemble.om.oms.manager.app.IContainer;
import com.dcits.ensemble.om.oms.manager.dubbo.UpgAppIntantCache;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;
import com.dcits.ensemble.om.oms.service.app.EcmAppIntantService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * EcmAppIntantAction*
 *
 * @author tangxlf
 * @date 2015-08-18
 */
@Controller
public class EcmAppIntantAction {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource
    IService omsBaseService;
    @Resource
    ParamComboxService paramComboxService;
    @Resource
    IContainer appContainer;
    @Resource
    EcmAppIntantService ecmAppIntantService;
    @Resource
    UpgAppIntantCache upgAppIntantCache;
    @Resource
    AppConfieAutoHandler appConfieAutoHandler;

    @RequestMapping("saveAppIntant")
    public void save(HttpServletRequest request, PrintWriter printWriter, EcmAppIntant intant) {
        try {
            log.info("" + intant);
            String userId = (String) request.getSession().getAttribute("UserName");
            ProgressMessageUtil.startProgress(userId, intant.getSerIp(), intant.getAppIntantName());
            appContainer.assemContainer(intant, userId);
            ecmAppIntantService.save(intant, userId);
            appConfieAutoHandler.handlerAppIntantConfig(intant, userId);
            ProgressMessageUtil.stopProgress("" + userId);
            ActionResultWrite.setsuccessfulResult(printWriter);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
    }


    @RequestMapping("findAppIntant")
    public void find(HttpServletRequest request, PrintWriter printWriter, EcmAppIntant intant) {
        log.info("" + intant);
        try{
        List<EcmAppIntant> dataList = omsBaseService.findListByCond(intant);
        ActionResultWrite.setReadResult(printWriter, magicList(dataList));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
    }

    @RequestMapping("findComboxAppIntant")
    public void findComboxAppIntant(HttpServletRequest request, PrintWriter printWriter, EcmAppIntant intant) {
        log.info("" + intant);
        try{
        List<EcmAppIntant> dataList = omsBaseService.findListByCond(intant);
        List<PkList> pkLists = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            PkList pkList = new PkList();
            pkList.setPkDesc(dataList.get(i).getSerIp() + "--" + dataList.get(i).getAppIntantName());
            pkList.setPkValue(dataList.get(i).getAppIntantId().toString());
            pkLists.add(pkList);
        }
        String jsonStr = JSON.toJSONString(pkLists);
        printWriter.print(jsonStr);
        printWriter.flush();
        printWriter.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
    }

    private List<EcmAppIntant> magicList(List<EcmAppIntant> list) {
        for (EcmAppIntant intant : list) {
            intant.setAppIntantStatusName(paramComboxService.getParaName(intant.getAppIntantStatus()));
            ContainerCheckResult result = appContainer.checkContainerStatus(intant, null);
            intant.setCurrentAppIntantStatus(result.getCheckStatusName());
            intant.setHealthMessage(result.getMessage());
        }
        return list;
    }

    //先升级实例配置调整
    @RequestMapping("findEarlyComboxAppIntant")
    public void findEarlyComboxAppIntant(HttpServletRequest request, PrintWriter printWriter, String appId) {
        try {
            List<EcmAppIntant> dataList = upgAppIntantCache.getAppIntEarlyList(Integer.parseInt(appId));
            log.info("dataList=" + dataList);
            List<PkList> pkLists = new ArrayList<>();
            for (EcmAppIntant ecmAppIntant : dataList) {
                PkList pkList = new PkList();
                pkList.setPkDesc(ecmAppIntant.getAppIntantName());
                pkList.setPkValue(ecmAppIntant.getAppIntantId().toString());
                pkLists.add(pkList);
            }
            String jsonStr = JSON.toJSONString(pkLists);
            printWriter.print(jsonStr);
            printWriter.flush();
            printWriter.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }

    }

    //后升级实例配置调整
    @RequestMapping("findLateComboxAppIntant")
    public void findLateComboxAppIntant(HttpServletRequest request, PrintWriter printWriter, EcmAppIntant intant) {
        log.info("" + intant);
        try {
            List<EcmAppIntant> dataList = upgAppIntantCache.getAppIntLateList(intant.getAppId());
            List<PkList> pkLists = new ArrayList<>();
            for (EcmAppIntant ecmAppIntant:dataList) {
                PkList pkList = new PkList();
                pkList.setPkDesc(ecmAppIntant.getAppIntantName());
                pkList.setPkValue(ecmAppIntant.getAppIntantId().toString());
                pkLists.add(pkList);
            }
            String jsonStr = JSON.toJSONString(pkLists);
            printWriter.print(jsonStr);
            printWriter.flush();
            printWriter.close();
            log.info("" + dataList);
        }catch (Exception e){
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
    }

}
