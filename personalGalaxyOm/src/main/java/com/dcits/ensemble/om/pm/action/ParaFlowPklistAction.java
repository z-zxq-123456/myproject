package com.dcits.ensemble.om.pm.action;
import com.alibaba.fastjson.JSON;
import com.dcits.dynamic.web.dao.WebUserDao;
import com.dcits.dynamic.web.dao.sys.WebMenuDao;
import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.ensemble.om.pm.dao.paraSetting.*;
import com.dcits.ensemble.om.pm.dao.utils.WebBaseDao;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaNamespaceOrg;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaTableFileds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.*;

@Controller()
@RequestMapping("/pklist")
public class ParaFlowPklistAction {
    private static final Logger log = LoggerFactory
            .getLogger(ParaFlowPklistAction.class);
    @Resource
    WebUserDao webUserDao;
    @Resource
    WebMenuDao webMenuDao;
    @Resource
    WebBaseDao webBaseDao;
    @Resource
    ParaModuleOrgDao moduleOrgDao;
    @Resource
    ParaNamespaceOrgDao namespaceOrgDao;
    @Resource
    ParaSystemOrgDao systemOrgDao;
    @Resource
    ParaFieldsColumnDao columnDao;
    @Resource
    ParaFieldsTableDao tableDao;
    @Resource
    ParaEnvOrgDao paraEnvOrgDao;
    @Resource
    FmRefCodeDao fmRefCodeDao;

    @RequestMapping("/getModuleBySystem")
    public void  getModuleBySystem(HttpServletRequest request,PrintWriter printWriter){
        String systemId = request.getParameter("systemId");
        List<PkList> pklist = new ArrayList<>();
        if (systemId != null && !systemId.isEmpty()) {
            pklist = moduleOrgDao.getModuleBySystem(systemId);
        }
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/getSystem")
    public void  getSystem(HttpServletRequest request,PrintWriter printWriter){
        List<PkList> pklist = systemOrgDao.getSystem();
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getSystemIgnoreProd")
    public void  getSystemIgnoreProd(HttpServletRequest request,PrintWriter printWriter){
        List<PkList> pklist = systemOrgDao.getSystemIngnoreProd();
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/getCtrlValue")
    public void getCtrlValue(HttpServletRequest request, PrintWriter printWriter) {
        List<PkList> pklist = fmRefCodeDao.getCtrlValue();
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/getTableName")
    public void  getTransactionId(HttpServletRequest request,PrintWriter printWriter){
        List<PkList> pklist = webMenuDao.getRootMenu();
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getUserIdList")
    public void getUserIdList(HttpServletRequest request,PrintWriter printWriter){
        List<PkList> pklist=webUserDao.getUserNamePklistToUpdate();
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/getTableBySystemModule")
    public void  getTableByNamespace(HttpServletRequest request,PrintWriter printWriter){
        List<PkList> pklist = new ArrayList<>();
        ParaNamespaceOrg namespaceOrg = new ParaNamespaceOrg();
        String systemId = request.getParameter("systemId");
        String moduleId = request.getParameter("moduleId");
        if (systemId != null && !systemId.isEmpty() && moduleId != null && !moduleId.isEmpty()) {
            namespaceOrg.setModuleId(moduleId);
            namespaceOrg.setSystemId(systemId);
            pklist = namespaceOrgDao.getTableBySystemModule(namespaceOrg);
            printWriter.print(JSON.toJSONString(pklist));
            printWriter.flush();
            printWriter.close();
        }else{
            printWriter.print(JSON.toJSONString(pklist));
            printWriter.flush();
            printWriter.close();
        }
    }

    @RequestMapping("/getTableForModifingDataBySystemModule")
    public void  getTableForModifingDataBySystemModule(HttpServletRequest request,PrintWriter printWriter){
        List<PkList> pklist = new ArrayList<>();
        ParaNamespaceOrg namespaceOrg = new ParaNamespaceOrg();
        String systemId = request.getParameter("systemId");
        String moduleId = request.getParameter("moduleId");
        if (systemId != null && !systemId.isEmpty() && moduleId != null && !moduleId.isEmpty()) {
            namespaceOrg.setModuleId(moduleId);
            namespaceOrg.setSystemId(systemId);
            pklist = namespaceOrgDao.getTableForModifingDataBySystemModule(namespaceOrg);
            printWriter.print(JSON.toJSONString(pklist));
            printWriter.flush();
            printWriter.close();
        }else{
            printWriter.print(JSON.toJSONString(pklist));
            printWriter.flush();
            printWriter.close();
        }
    }

    @RequestMapping("/getTableNameByNamespace")
    public void getTableNameByNamespaceOrg(HttpServletRequest request, PrintWriter printWriter) {
        List<PkList> pklist = namespaceOrgDao.getTableNamePklist();
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/getColumnNameByColumn")
    public void getColumnNameByColumn(HttpServletRequest request, PrintWriter printWriter) {
        List<PkList> pklist = columnDao.getColumnNamePklist();
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/getColumnNameByTable")
    private void getColumnNameByTable(HttpServletRequest request, PrintWriter printWriter){
        String tableName=request.getParameter("tableName");
        ParaTableFileds table = new ParaTableFileds();
        table.setTableName(tableName);
        List<ParaTableFileds> tableList = tableDao.getColumnList(table);
        printWriter.print(JSON.toJSONString(tableList));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getGlProdMappingType")
    public void getGlProdMappingType(HttpServletRequest request, PrintWriter printWriter) {
        HashMap<String, Object> keyMap = new HashMap<>();
        HashMap<String,String> map=new HashMap<>();
        List<PkList> pklist = new ArrayList<>();
        String[] primaryFieldsName =new String[]{"prod_range"};
        keyMap.put("prod_range","S");
        keyMap.put("tableName","mb_prod_type");
        keyMap.put("value","prod_type");
        keyMap.put("valueDesc","prod_desc");
        keyMap.put("pks", primaryFieldsName);
        keyMap.put("baseSpace", "com.dcits.ensemble.om.pm.dao.utils.WebBaseDao");
        map.put("baseSpace", "com.dcits.ensemble.om.pm.dao.utils.WebBaseDao");
        pklist.addAll(webBaseDao.selectParameterPklistWithWhere(keyMap));
        map.put("tableName", "irl_fee_type");
        map.put("value","fee_type");
        map.put("valueDesc", "fee_type");
        pklist.addAll(webBaseDao.selectParameterPklist(map));
        map.put("tableName","tb_voucher_def");
        map.put("value","doc_type");
        map.put("valueDesc","doc_type_desc");
        pklist.addAll(webBaseDao.selectParameterPklist(map));
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getEnvId")
    private void getEnvUrlByEnvId(HttpServletRequest request, PrintWriter printWriter){
        List<PkList> pklist = paraEnvOrgDao.getEnvId();
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }
}
