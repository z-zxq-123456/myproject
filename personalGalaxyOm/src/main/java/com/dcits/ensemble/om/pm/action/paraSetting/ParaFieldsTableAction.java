package com.dcits.ensemble.om.pm.action.paraSetting;

import com.alibaba.fastjson.JSON;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaSelectFieldsDao;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaFieldsTableDao;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaSelectFields;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaTableFileds;
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

/**
 * Created by maruie on 2016/4/1.
 */
@Controller
@RequestMapping("/paraFieldsTable")
public class ParaFieldsTableAction {
    @Resource
    private ParaFieldsTableDao paraFieldsTableDao;

    @Resource
    private ParaSelectFieldsDao selectDao;

    @RequestMapping("/findAllTable")
    public void  getApplicationId(HttpServletRequest request,PrintWriter printWriter) {
        try {
            List<ParaTableFileds> list = paraFieldsTableDao.getTableList();
            ActionResultWrite.setReadResult(printWriter, list);
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }
    @RequestMapping("saveTable")
    public void save(HttpServletRequest request, PrintWriter printWriter, ParaTableFileds table) throws Exception {
        try {
            Map<String, String> result = new HashMap<>();
            if(paraFieldsTableDao.getFieldDataByPKValue(table).size()==0) {
                paraFieldsTableDao.insert(table);
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


    @RequestMapping("updateTable")
    public void update(HttpServletRequest request, PrintWriter printWriter,ParaTableFileds table) throws Exception {
        Map<String, String> result = new HashMap<>();
        paraFieldsTableDao.updateByPrimaryKey(table);
        result.put("retStatus", "S");
        result.put("retMsg", "修改成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("deleteTable")//删除之前先判断表是否绑定表条件
    @Transactional
    public void delete(HttpServletRequest request, PrintWriter printWriter, ParaTableFileds table) {
        try {
            ParaSelectFields select= selectDao.getSelectByTable(table.getTableName(), table.getColumnName());
            paraFieldsTableDao.deleteByPrimaryKey(table);
            if(select == null){
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

    @RequestMapping("/vaildTable")
    public void  vaildTable(HttpServletRequest request,PrintWriter printWriter){
        String tableName = request.getParameter("param");
        ParaTableFileds table = paraFieldsTableDao.selectByPrimaryKey(new ParaTableFileds(), tableName);
        Map<String, String> result = new HashMap<>();
        if(BusinessUtils.isNull(table)){
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
