package com.dcits.ensemble.om.pm.action.paraSetting;


import com.dcits.dynamic.web.util.ResourcesUtils;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaCircleFlowDao;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaCircleFlow;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaUserAuthority;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaApplyCheckPublishDao;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.dcits.ensemble.om.pm.dao.paraSetting.ParaUserAuthorityDao;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaApplyCheckPublish;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/paraAcp")
public class ParaApplyCheckPublishAction {
    @Resource
    private ParaApplyCheckPublishDao paraApplyCheckPublishDao;
    @Resource
    private ParaCircleFlowDao paraCircleFlowDao;

    @RequestMapping("/findHistory")
    public void findHistory(HttpServletRequest request, PrintWriter printWriter) {
        try {
            List<ParaApplyCheckPublish> list = paraApplyCheckPublishDao.getNeedCheckInfoList(request.getParameter("reqNo"));
            ActionResultWrite.setReadResult(printWriter, list);
        } catch (Exception e) {
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }

    @RequestMapping("/findCheckOrPublish")
    public void findCheckOrPublish(HttpServletRequest request, PrintWriter printWriter) {
        try {
            List<ParaCircleFlow> list = new ArrayList<>();

            ParaUserAuthorityDao paraUserAuthorityDao = SpringApplicationContext.getContext().getBean(ParaUserAuthorityDao.class);
            ParaUserAuthority paraUserAuthority = new ParaUserAuthority();
            String userId = ResourcesUtils.getSystemUser(request);
            paraUserAuthority.setUserId(userId);
            String flag = request.getParameter("currentStatus");
            paraUserAuthority = paraUserAuthorityDao.selectDataByDbPrimaryKey(paraUserAuthority);
            if (paraUserAuthority == null) {
                ActionResultWrite.setReadResult(printWriter, list);
                return;
            }

            if ((flag.equals("2") && paraUserAuthority.getAuthCheck().equals("N")) || (flag.equals("3")&&paraUserAuthority.getAuthPublish().equals("N"))) {
                ActionResultWrite.setReadResult(printWriter, list);
                return;
            }

            list = paraCircleFlowDao.getNeedInfoList(flag);
            if (list != null) {
                ActionResultWrite.setReadResult(printWriter, list);
            } else {
                ActionResultWrite.setReadResult(printWriter, new ArrayList<>());
            }

        } catch (Exception e) {
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }

    @RequestMapping("/findList")
    public void findList(HttpServletRequest request, PrintWriter printWriter) {
        List<Map<String, Object>> checkPublishs = paraApplyCheckPublishDao.findList();
        ActionResultWrite.setReadResult(printWriter, checkPublishs);

    }

    @RequestMapping("/findByDate")
    public void findByDate(HttpServletRequest request, PrintWriter printWriter) {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        List<Map<String, Object>> checkPublishs = paraApplyCheckPublishDao.findByDate(startDate, endDate);
        ActionResultWrite.setReadResult(printWriter, checkPublishs);
    }
}
