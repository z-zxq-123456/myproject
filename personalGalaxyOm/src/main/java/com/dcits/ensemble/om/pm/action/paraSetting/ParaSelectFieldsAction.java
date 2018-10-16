package com.dcits.ensemble.om.pm.action.paraSetting;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaSelectFieldsDao;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaSelectFields;
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
@RequestMapping("/paraSelectFields")
public class ParaSelectFieldsAction {
    @Resource
    private ParaSelectFieldsDao selectDao;

    @RequestMapping("/findAllSelect")
    private void find(HttpServletRequest request, PrintWriter printWriter) throws Exception {
        JSONObject out = new JSONObject();
        List<ParaSelectFields> selectList = selectDao.getSelectList();
        out.put("data", selectList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("saveSelect")
    public void save(HttpServletRequest request, PrintWriter printWriter, ParaSelectFields select) throws Exception {
        Map<String, String> result = new HashMap<>();
        String select1 = select.getSelect1();
        String select2 = select.getSelect2();
        String select3 = select.getSelect3();
        if(select1.equals(select2) || select1.equals(select3) || (select2.equals(select3) && !select2.isEmpty())){
            result.put("retStatus", "F");
            result.put("retMsg", "条件1、条件2、条件3不可重复");
        }
        else {
            selectDao.insert(select);
            result.put("retStatus", "S");
            result.put("retMsg", "添加成功");
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("updateSelect")
    public void update(HttpServletRequest request, PrintWriter printWriter,ParaSelectFields select) throws Exception {
        Map<String, String> result = new HashMap<>();
        String select1 = select.getSelect1();
        String select2 = select.getSelect2();
        String select3 = select.getSelect3();
        if(select1.equals(select2) || select1.equals(select3) || (select2.equals(select3) && !select2.isEmpty())){
            result.put("retStatus", "F");
            result.put("retMsg", "条件1、条件2、条件3不可重复");
        }
        else {
            selectDao.updateByPrimaryKey(select);
            result.put("retStatus", "S");
            result.put("retMsg", "添加成功");
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("deleteSelect")
    public void delete(HttpServletRequest request, PrintWriter printWriter, ParaSelectFields select) {
        try {
            selectDao.deleteByPrimaryKey(select);
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

    @RequestMapping("/vaildSelect")
    public void  vaildSelect(HttpServletRequest request,PrintWriter printWriter){
        String columnName = request.getParameter("param");
        ParaSelectFields select = selectDao.selectByPrimaryKey(new ParaSelectFields(), columnName);
        Map<String, String> result = new HashMap<>();
        if(BusinessUtils.isNull(select)){
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
