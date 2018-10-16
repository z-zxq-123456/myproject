package com.dcits.ensemble.om.pm.action.paraSetting;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaFieldsTableDao;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaNamespaceOrgDao;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaNamespaceOrg;
import com.dcits.ensemble.om.pm.util.ParaMeterUtils;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
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
@RequestMapping("/paraNamespace")
public class ParaNamespaceAction {
    @Resource
    private ParaNamespaceOrgDao paraNamespaceOrgDao;

    @Resource
    private ParaFieldsTableDao tableDao;

    @RequestMapping("saveNamespace")
    public void save(HttpServletRequest request, PrintWriter printWriter, ParaNamespaceOrg namespaceOrg) throws Exception {
        Map<String, String> result = new HashMap<>();
        paraNamespaceOrgDao.insert(namespaceOrg);
        result.put("retStatus", "S");
        result.put("retMsg", "添加成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }


    @RequestMapping("updateNamespace")
    public void update(HttpServletRequest request, PrintWriter printWriter, ParaNamespaceOrg namespaceOrg) throws Exception {
        Map<String, String> result = new HashMap<>();
        paraNamespaceOrgDao.updateByPrimaryKey(namespaceOrg);
        result.put("retStatus", "S");
        result.put("retMsg", "修改成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("deleteNamespace")
    @Transactional
    public void delete(HttpServletRequest request, PrintWriter printWriter, ParaNamespaceOrg namespaceOrg) {
        try {
            List<PkList> table = tableDao.getTableByTable(namespaceOrg.getTransactionId());
            //if(table.isEmpty()){
            paraNamespaceOrgDao.deleteByPrimaryKey(namespaceOrg);
            if (table.size() > 0) {
                Map<String, String> result = new HashMap<>();
                result.put("retStatus", "Info");
                printWriter.print(JSON.toJSONString(result));
                printWriter.flush();
                printWriter.close();
            }
            else
            {
                ActionResultWrite.setsuccessfulResult(printWriter);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, "Error.");
        }
    }

    @RequestMapping("findAllTable")
    public void find(HttpServletRequest request, PrintWriter printWriter) throws Exception {
        JSONObject out = new JSONObject();
        List<ParaNamespaceOrg> webUserList = paraNamespaceOrgDao.getNamespaceList();
        out.put("data", webUserList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/userStep")
    public void userStep(HttpServletRequest request, PrintWriter printWriter) {
        try {
            Map<String, String> result = new HashMap<>();
            String userStep = ParaMeterUtils.getWorkFlowForUser(request);
            result.put("userStep", userStep);
            printWriter.print(JSON.toJSONString(result));
            printWriter.flush();
            printWriter.close();
        } catch (Exception e) {
            ActionResultWrite.setErrorResult(printWriter, "操作错误，系统异常退出！");
        }
    }
}
