package com.dcits.ensemble.om.pm.action.paraSetting;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaModuleOrgDao;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaNamespaceOrgDao;

import com.dcits.ensemble.om.pm.module.paraSetting.ParaModuleOrg;
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
@RequestMapping("/paraModule")
public class ParaModuleAction {
      @Resource
      private ParaModuleOrgDao moduleOrgDao;

      @Resource
      private ParaNamespaceOrgDao namespaceOrgDao;

      @RequestMapping("saveModule")
      public void save(HttpServletRequest request, PrintWriter printWriter ,ParaModuleOrg moduleOrg) throws Exception {
          try {
              Map<String, String> result = new HashMap<>();
              if(moduleOrgDao.selectModule(moduleOrg)==null) {
                  moduleOrgDao.insert(moduleOrg);
                  result.put("retStatus", "S");
                  result.put("retMsg", "添加成功");
              }else{
                  result.put("retStatus", "F");
                  result.put("retMsg", "主键冲突");
              }
              printWriter.print(JSON.toJSONString(result));
              printWriter.flush();
              printWriter.close();
              ActionResultWrite.setsuccessfulResult(printWriter);
          } catch (Exception e) {
              e.printStackTrace();
              ActionResultWrite.setErrorResult(printWriter, "数据重复！");
          }
      }


    @RequestMapping("updateModule")
    public void update(HttpServletRequest request, PrintWriter printWriter,ParaModuleOrg moduleOrg) throws Exception {
        Map<String, String> result = new HashMap<>();
        moduleOrgDao.updateByPrimaryKey(moduleOrg);
        result.put("retStatus", "S");
        result.put("retMsg", "修改成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("deleteModule")  //删除之前先判断参数表中是否为该模块绑定表
    @Transactional
    public void delete(HttpServletRequest request, PrintWriter printWriter, ParaModuleOrg moduleOrg) {
        try {
            List<PkList> table= namespaceOrgDao.getTableByModule(moduleOrg.getModuleId());
            if(table.isEmpty()){
                moduleOrgDao.deleteByPrimaryKey(moduleOrg);
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

    @RequestMapping("findAllModule")
    public void find(HttpServletRequest request, PrintWriter printWriter) throws Exception {
        JSONObject out = new JSONObject();
        List<ParaModuleOrg> webUserList = moduleOrgDao.getModuleList();
        out.put("data", webUserList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/vaildModule")
    public void  vaildModule(HttpServletRequest request,PrintWriter printWriter){
        String moduleId = request.getParameter("moduleId");
        ParaModuleOrg moduleOrg = moduleOrgDao.selectByPrimaryKey(new ParaModuleOrg(), moduleId);
        Map<String, String> result = new HashMap<>();
        if(BusinessUtils.isNull(moduleOrg)){
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
