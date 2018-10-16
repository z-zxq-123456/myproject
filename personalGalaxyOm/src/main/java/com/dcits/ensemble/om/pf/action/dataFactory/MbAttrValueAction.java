package com.dcits.ensemble.om.pf.action.dataFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pf.dao.dataFactory.MbAttrValueDao;
import com.dcits.ensemble.om.pf.module.dataFactory.MbAttrValue;
import com.dcits.ensemble.om.pf.module.PkList;
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
 * Created by zhangyjw on 2015/9/22.
 */
@Controller
@RequestMapping("/attrValue")
public class MbAttrValueAction {
    @Resource
    private MbAttrValueDao mbAttrValueDao;

    @RequestMapping("/insert")
    public void insert(HttpServletRequest request, PrintWriter printWriter) {
        MbAttrValue mbAttrValue=new MbAttrValue();
        mbAttrValue.setAttrKey(request.getParameter("attrKey"));
        mbAttrValue.setAttrValue(request.getParameter("attrValue"));
        mbAttrValue.setValueDesc(request.getParameter("valueDesc"));
        mbAttrValue.setRefTable(request.getParameter("refTable"));
        mbAttrValue.setRefCondition(request.getParameter("refCondition"));
        mbAttrValue.setRefColumns(request.getParameter("refColumns"));
        Map  result =new HashMap();
        mbAttrValueDao.insert(mbAttrValue);
        result.put("retStatus", "S");
        result.put("retMsg", "添加成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/update")
    @Transactional
    public void  update(HttpServletRequest request, PrintWriter printWriter) {
        MbAttrValue mbAttrValue=new MbAttrValue();
        mbAttrValue.setAttrKey(request.getParameter("attrKey"));
        mbAttrValue.setAttrValue(request.getParameter("attrValue"));
        mbAttrValue.setValueDesc(request.getParameter("valueDesc"));
        mbAttrValue.setRefTable(request.getParameter("refTable"));
        mbAttrValue.setRefCondition(request.getParameter("refCondition"));
        mbAttrValue.setRefColumns(request.getParameter("refColumns"));
        Map  result = new HashMap();
        mbAttrValueDao.updateByPrimaryKey(mbAttrValue);  //再重新添加完成包含主键在内的修改
        result.put("retStatus", "S");
        result.put("retMsg", "修改成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request,PrintWriter printWriter) {
        MbAttrValue mbAttrValue=new MbAttrValue();
        mbAttrValue.setAttrKey(request.getParameter("attrKey"));
        mbAttrValue.setAttrValue(request.getParameter("attrValue"));
        Map  result =new HashMap();
        mbAttrValueDao.deleteByPrimaryKey(mbAttrValue);
        result.put("retStatus", "S");
        result.put("retMsg", "删除成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getAll")
    public void  getAll( PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<MbAttrValue> mbAttrValues= mbAttrValueDao.getAll();
        out.put("data",mbAttrValues);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getOne")
    public void getOne(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out=new JSONObject();
        MbAttrValue mbAttrValue1 = new MbAttrValue();
        mbAttrValue1.setAttrKey(request.getParameter("attrKey"));
        mbAttrValue1.setAttrValue(request.getParameter("attrValue"));
        MbAttrValue mbAttrValue=mbAttrValueDao.selectByPrimaryKey1(mbAttrValue1);
        if (mbAttrValue != null){
            out.put("retStatus","F");
            out.put("retMsg","参数值代码已存在！");
        }else{
            out.put("retStatus","S");
        }
        out.put("data",mbAttrValue);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getAttrValueByKey")
    public void getAttrValueByKey(HttpServletRequest request, PrintWriter printWriter) {
        List<PkList> pklist = mbAttrValueDao.getAttrValueByKey(request.getParameter("attrKey"));
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }

}

