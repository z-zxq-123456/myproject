package com.dcits.ensemble.om.oms.action.sys;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
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

@Controller
public class ParaManagerAction {

    @Resource
    IService omsBaseService;
    @Resource
    ParamComboxService paramComboxService;


    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("savePara")
    public void save(HttpServletRequest request, PrintWriter printWriter, EcmParam paraManager) {
        try {
            omsBaseService.insert(paraManager);
            ActionResultWrite.setsuccessfulResult(printWriter);
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
    }


    @RequestMapping("updatePara")
    public void update(HttpServletRequest request, PrintWriter printWriter, EcmParam paraManager) {
        try {
            omsBaseService.updateByPrimaryKey(paraManager);
            paramComboxService.deleteCacheComboxData(paraManager.getParaCode());
            ActionResultWrite.setsuccessfulResult(printWriter);
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
    }


    @RequestMapping("deletePara")
    public void delete(HttpServletRequest request, PrintWriter printWriter, EcmParam paraManager) {
        try {
            omsBaseService.deleteByPrimaryKey(paraManager);
            paramComboxService.deleteCacheComboxData(paraManager.getParaCode());
            ActionResultWrite.setsuccessfulResult(printWriter);
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }

    }

    @RequestMapping("findPara")
    public void find(HttpServletRequest request, PrintWriter printWriter, EcmParam paraManager) {
        try {
            int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
            int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 1000000);
            log.info("pageNo =" + pageNo + "pageSize =" + pageSize);
            PageData<EcmParam> pageData = omsBaseService.findPageByCond(paraManager, pageNo, pageSize);
            List<EcmParam> ecmParams = pageData.getList();
            ActionResultWrite.setReadResult(printWriter, magicList(ecmParams));
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
    }


    private List<EcmParam> magicList(List<EcmParam> list) {
        for (EcmParam para : list) {
            para.setIsValidateName(paramComboxService.getParaName(para.getIsValidate()));
        }
        return list;
    }
}
