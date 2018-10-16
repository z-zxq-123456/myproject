package com.dcits.ensemble.om.oms.action.sys;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.module.sys.EcmEwrInfo;
import com.dcits.ensemble.om.oms.module.sys.EcmParam;
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
import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */

@Controller
public class EarWarningRuleAction {
    @Resource
    IService omsBaseService;
    @Resource
    ParamComboxService paramComboxService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("saveRule")
    public void save(HttpServletRequest request, PrintWriter printWriter, EcmEwrInfo ecmEwrInfo) {
        System.out.println(ecmEwrInfo.toString());
        try {
            omsBaseService.insert(ecmEwrInfo);
            ActionResultWrite.setsuccessfulResult(printWriter);
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
    }

    @RequestMapping("findRule")
    public void find(HttpServletRequest request, PrintWriter printWriter, EcmEwrInfo ecmEwrInfo) {
        try {
            int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
            int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 1000000);
            log.info("pageNo =" + pageNo + "pageSize =" + pageSize);
            PageData<EcmEwrInfo> pageData = omsBaseService.findPageByCond(ecmEwrInfo, pageNo, pageSize);
            List<EcmEwrInfo> ecmParams = pageData.getList();
            ActionResultWrite.setReadResult(printWriter, magicList(ecmParams));
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
    }

    @RequestMapping("updateRule")
    public void update(HttpServletRequest request, PrintWriter printWriter, EcmEwrInfo ecmEwrInfo) {
        System.out.println(ecmEwrInfo.toString());
        try {
            omsBaseService.updateByPrimaryKey(ecmEwrInfo);
//            paramComboxService.deleteCacheComboxData(ecmEwrInfo.getRuleId());
            ActionResultWrite.setsuccessfulResult(printWriter);
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
    }

    @RequestMapping("deleteRule")
    public void delete(HttpServletRequest request, PrintWriter printWriter, EcmEwrInfo ecmEwrInfo) {
        try {
            omsBaseService.deleteByPrimaryKey(ecmEwrInfo);
//            paramComboxService.deleteCacheComboxData(paraManager.getParaCode());
            ActionResultWrite.setsuccessfulResult(printWriter);
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }

    }


    private List<EcmEwrInfo> magicList(List<EcmEwrInfo> list) {
        for (EcmEwrInfo para : list) {
            para.setRuleRankName(paramComboxService.getParaName(para.getRuleRank()));
        }
        return list;
    }
}
