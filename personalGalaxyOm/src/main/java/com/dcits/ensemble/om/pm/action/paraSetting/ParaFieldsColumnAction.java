package com.dcits.ensemble.om.pm.action.paraSetting;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaFieldsColumnDao;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaFieldsTableDao;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaFieldsColumn;

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
@RequestMapping("/paraFieldsColumn")
public class ParaFieldsColumnAction {
    @Resource
    private ParaFieldsColumnDao columnDao;

    @Resource
    private ParaFieldsTableDao tableDao;

    @RequestMapping("/findAllColumn")
    public void find(HttpServletRequest request, PrintWriter printWriter) throws Exception {
        JSONObject out = new JSONObject();
        List<ParaFieldsColumn> columnList = columnDao.getColumnList();
        out.put("data", columnList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("saveColumn")
    public void save(HttpServletRequest request, PrintWriter printWriter, ParaFieldsColumn column) throws Exception {
        Map<String, String> result = new HashMap<>();
        columnDao.insert(column);
        result.put("retStatus", "S");
        result.put("retMsg", "添加成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("updateColumn")
    public void update(HttpServletRequest request, PrintWriter printWriter, ParaFieldsColumn column) throws Exception {
        Map<String, String> result = new HashMap<>();
        columnDao.updateByPrimaryKey(column);
        result.put("retStatus", "S");
        result.put("retMsg", "修改成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("deleteColumn")//删除之前先判断字段是否绑定表
    public void delete(HttpServletRequest request, PrintWriter printWriter, ParaFieldsColumn column) {
        try {
            List<PkList> table = tableDao.getTableByColumn(column.getColumnName());
            if (table.isEmpty()) {
                columnDao.deleteByPrimaryKey(column);
                ActionResultWrite.setsuccessfulResult(printWriter);
            } else {
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

    @RequestMapping("deleteFieldsColumn")//删除之前不判断字段是否绑定表
    public void deleteFieldsColumn(HttpServletRequest request, PrintWriter printWriter, ParaFieldsColumn column) {
        try {
            columnDao.deleteByPrimaryKey(column);
            ActionResultWrite.setsuccessfulResult(printWriter);
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, "Error.");
        }
    }

    @RequestMapping("/vaildColumn")
    public void vaildColumn(HttpServletRequest request, PrintWriter printWriter) {
        String columnName = request.getParameter("param");
        ParaFieldsColumn column = columnDao.selectByPrimaryKey(new ParaFieldsColumn(), columnName);
        Map<String, String> result = new HashMap<>();
        if (BusinessUtils.isNull(column)) {
            result.put("status", "y");
            result.put("info", "值正确！");
        } else {
            result.put("status", "n");
            result.put("info", "值重复！");
        }

        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

}
