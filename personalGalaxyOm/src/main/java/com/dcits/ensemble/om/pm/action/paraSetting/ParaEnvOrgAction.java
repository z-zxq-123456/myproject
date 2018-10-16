package com.dcits.ensemble.om.pm.action.paraSetting;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.dynamic.web.dao.WebUserDao;
import com.dcits.dynamic.web.mapper.WebUser;
import com.dcits.dynamic.web.util.ResourcesUtils;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaEnvOrgDao;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaEnvOrg;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.orion.core.util.BusinessUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/paraEnv")
public class ParaEnvOrgAction {
    @Resource
    private ParaEnvOrgDao envDao;
    
    @Resource
    private WebUserDao webUserDao;
    @RequestMapping("saveEnv")
    public void save(HttpServletRequest request, PrintWriter printWriter ,ParaEnvOrg envOrg) throws Exception {
        Map<String, String> result = new HashMap<>();
        envOrg.setIp(ResourcesUtils.getClientIP(request));
        envOrg.setOperator(ResourcesUtils.getSystemUser(request));
        envOrg.setTime(ResourcesUtils.getCurrentSystemTime());
        envDao.insert(envOrg);
        result.put("retStatus", "S");
        result.put("retMsg", "添加成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }


    @RequestMapping("updateEnv")
    public void update(HttpServletRequest request, PrintWriter printWriter,ParaEnvOrg envOrg) throws Exception {
        Map<String, String> result = new HashMap<>();
        envOrg.setIp(ResourcesUtils.getClientIP(request));
        envOrg.setOperator(ResourcesUtils.getSystemUser(request));
        envOrg.setTime(ResourcesUtils.getCurrentSystemTime());
        envDao.updateByPrimaryKey(envOrg);
        result.put("retStatus", "S");
        result.put("retMsg", "修改成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("deleteEnv")
    public void delete(HttpServletRequest request, PrintWriter printWriter, ParaEnvOrg envOrg) {
        try {
            envDao.deleteByPrimaryKey(envOrg);
            ActionResultWrite.setsuccessfulResult(printWriter);
            Map<String, String> result = new HashMap<>();
            result.put("retStatus", "Info");
            printWriter.print(JSON.toJSONString(result));
            printWriter.flush();
            printWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, "Error.");
        }
    }

    @RequestMapping("findAllEnv")
    public void find(HttpServletRequest request, PrintWriter printWriter) throws Exception {
        JSONObject out = new JSONObject();
        List<ParaEnvOrg> envList = envDao.getEnvList();
        out.put("data", envList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/vaildEnv")
    public void  vaildModule(HttpServletRequest request,PrintWriter printWriter){
        String envId = request.getParameter("param");
        ParaEnvOrg envOrg = envDao.selectByPrimaryKey(new ParaEnvOrg(), envId);
        Map<String, String> result = new HashMap<>();
        if(BusinessUtils.isNull(envOrg)){
            result.put("status","y");
            result.put("info","值正确！");
        }else {
            result.put("status","n");
            result.put("info","值重复！");
        }

        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/getEnvUrlByEnvId")
    public void getEnvUrlByEnvId(HttpServletRequest request, PrintWriter printWriter){
        try{
            ParaEnvOrg envInfo=envDao.getEnvUrlByEnvId(request.getParameter("envId"));
            Map<String, String> result = new HashMap<>();
            result.put("envUrl",envInfo.getUrl());
            result.put("module",envInfo.getModule());
            result.put("serviceCode",envInfo.getServiceCode());
            result.put("messageType",envInfo.getMessageType());
            result.put("messageCode", envInfo.getMessageCode());
            String userID = (String) request.getSession().getAttribute("UserName");
            if(userID==null)
            {
                userID="systemUserTestNull";
            }
            WebUser webUser = webUserDao.getUser(userID);
            if(webUser!=null) {
                result.put("legalentity", webUser.getLegalentity());
            }
            else
            {
                result.put("legalentity", "无");
            }
            printWriter.print(JSON.toJSONString(result));
            printWriter.flush();
            printWriter.close();
        }catch(Exception e){
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, "操作错误！");
        }
    }
}
