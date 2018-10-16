package com.dcits.ensemble.om.oms.action.serv;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.serv.EcmServRule;
import com.dcits.ensemble.om.oms.module.utils.PageData;
import com.dcits.ensemble.om.oms.service.serv.EcmServManagerService;
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
import java.util.HashMap;
import com.alibaba.fastjson.JSON;

/**
 * EcmServRuleAction* 
 * @author LuoLang
 * @date 2016-03-8
 */
@Controller
public class EcmServRuleAction {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    PkServiceUtil serviceUtil;
    @Resource
    EcmServManagerService servManagerService;
    @Resource
    ParamComboxService paramComboxService;
    @Resource
    IService omsBaseService;

    @RequestMapping("saveEcmServRule")
    public void save(HttpServletRequest request, PrintWriter printWriter, EcmServRule servRule) {
        try {
            int servRuleId = serviceUtil.getMaxReqID(0, "ECM_SERV_RULE", "SERV_RULE_ID");
            servRule.setServRuleId(servRuleId);
            omsBaseService.insert(servRule);
          //  ActionResultWrite.setsuccessfulResult(printWriter);
            HashMap result = new HashMap();
            result.put("success", "success");
            result.put("id", servRuleId);
            String jsonStr = JSON.toJSONString(result);
            printWriter.print(jsonStr);
            printWriter.flush();
            printWriter.close();

        }catch (Exception e){
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }

    }

    @RequestMapping("updateEcmServRule")
    public void update(HttpServletRequest request, PrintWriter printWriter, EcmServRule servRule, String servRuleId, String routerCondId,
                       String serMtdId, String routerArgsPos) {
        try {
            if (servRule.getServRuleType().equals("0026001")) {
                servRule.setRouterCondId(Integer.parseInt(routerCondId));
                servRule.setRouterArgsPos(Integer.parseInt(routerArgsPos));
            }
            servRule.setServRuleId(Integer.parseInt(servRuleId));
            servRule.setSerMtdId(Integer.parseInt(serMtdId));
            if (servManagerService.toEcmServRule(servRule.getServRuleId())) {
                omsBaseService.updateByPrimaryKey(servRule);
            } else {
                ActionResultWrite.setErrorResult(printWriter, "存在状态为正在升级的实例在使用此条服务路由规则，禁止修改!");
            }
            ActionResultWrite.setsuccessfulResult(printWriter);
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }


    @RequestMapping("deleteEcmServRule")
    public void delete(HttpServletRequest request, PrintWriter printWriter, EcmServRule servRule) {
        try {
            if (servManagerService.toEcmServRule(servRule.getServRuleId())) {
                omsBaseService.deleteByPrimaryKey(servRule);
            } else {
                ActionResultWrite.setErrorResult(printWriter, "存在状态为正在升级的实例在使用此条服务路由规则，禁止删除!");
            }
            ActionResultWrite.setsuccessfulResult(printWriter);
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }

    @RequestMapping("findEcmServRule")
    public void findRole(HttpServletRequest request, PrintWriter printWriter, EcmServRule servRule) {
        try {
            int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
            int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 10);
            log.info("pageNo =" + pageNo + "pageSize =" + pageSize);
            PageData<EcmServRule> pageData = omsBaseService.findPageByCond(servRule, pageNo, pageSize);
            ActionResultWrite.setReadResult(printWriter, magicList(pageData.getList()));
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }

    private List<EcmServRule> magicList(List<EcmServRule> list) {
        for (EcmServRule servRule : list) {
            servRule.setServRuleTypeName(paramComboxService.getParaName(servRule.getServRuleType()));
            servRule.setRuleStatusName(paramComboxService.getParaName(servRule.getRuleStatus()));
            if (servRule.getServRuleType().equals(SysConfigConstants.AUTO_MAKING)) {
                servRule.setRouterArgsTypeName(paramComboxService.getParaName(servRule.getRouterArgsType()));
            }
        }
        return list;
    }
}