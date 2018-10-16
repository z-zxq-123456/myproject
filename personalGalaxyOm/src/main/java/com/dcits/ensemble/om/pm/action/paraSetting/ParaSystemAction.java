package com.dcits.ensemble.om.pm.action.paraSetting;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaModuleOrgDao;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaSystemOrgDao;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaSystemOrg;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.orion.core.util.BusinessUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/paraSystem")
public class ParaSystemAction {

    @Resource
    private ParaSystemOrgDao systemOrgDao;

    @Resource
    private ParaModuleOrgDao moduleDao;

    @RequestMapping("saveSystem")
    public void save(HttpServletRequest request, PrintWriter printWriter ,ParaSystemOrg systemOrg) throws Exception {
        Map<String, String> result = new HashMap<>();
        systemOrgDao.insert(systemOrg);
        result.put("retStatus", "S");
        result.put("retMsg", "添加成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }


    @RequestMapping("updateSystem")
    public void update(HttpServletRequest request, PrintWriter printWriter,ParaSystemOrg systemOrg) throws Exception {
        Map<String, String> result = new HashMap<>();
        systemOrgDao.updateByPrimaryKey(systemOrg);
        result.put("retStatus", "S");
        result.put("retMsg", "修改成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("deleteSystem")//删除前先判断模块设置表里是否为该系统绑定模块
    @Transactional
    public void delete(HttpServletRequest request, PrintWriter printWriter, ParaSystemOrg systemOrg) {
        try {
            List<PkList> module= moduleDao.getModuleBySystem(systemOrg.getSystemId());
            if(module.isEmpty()){
                systemOrgDao.deleteByPrimaryKey(systemOrg);
                ActionResultWrite.setsuccessfulResult(printWriter);
            }else{
                Map<String, String> result = new HashMap<>();
                result.put("retStatus", "Info");
                printWriter.print(JSON.toJSONString(result));
                printWriter.flush();
                printWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, "Error.");
        }
    }

    @RequestMapping("findAllSystem")
    public void find(HttpServletRequest request, PrintWriter printWriter) throws Exception {
        JSONObject out = new JSONObject();
        List<ParaSystemOrg> systemInfoList = systemOrgDao.getSystemList();
        out.put("data", systemInfoList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/vaildSystem")
    public void  vaildSystem(HttpServletRequest request,PrintWriter printWriter){
        String systemId = request.getParameter("param");
        ParaSystemOrg systemOrg = systemOrgDao.selectByPrimaryKey(new ParaSystemOrg(), systemId);
        Map<String, String> result = new HashMap<>();
        if(BusinessUtils.isNull(systemOrg)){
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
}
