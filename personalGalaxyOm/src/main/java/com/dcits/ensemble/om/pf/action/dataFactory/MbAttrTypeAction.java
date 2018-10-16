package com.dcits.ensemble.om.pf.action.dataFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pf.dao.dataFactory.MbAttrClassDao;
import com.dcits.ensemble.om.pf.dao.dataFactory.MbAttrTypeDao;
import com.dcits.ensemble.om.pf.module.PkList;
import com.dcits.ensemble.om.pf.module.dataFactory.MbAttrType;
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
@RequestMapping("/attrType")
public class MbAttrTypeAction {
    @Resource
    private MbAttrTypeDao mbAttrTypeDao;
    @Resource
    private MbAttrClassDao mbAttrClassDao;
    @RequestMapping("/insert")
    public void insert(HttpServletRequest request, PrintWriter printWriter) {
        MbAttrType mbAttrType=new MbAttrType();
        mbAttrType.setAttrKey(request.getParameter("attrKey"));
        mbAttrType.setAttrDesc(request.getParameter("attrDesc"));
        mbAttrType.setAttrClass(request.getParameter("attrClass"));
        mbAttrType.setUseMethod(request.getParameter("useMethod"));
        mbAttrType.setValueMethod(request.getParameter("valueMethod"));
        mbAttrType.setBusiCategory(request.getParameter("busiCategory"));
        mbAttrType.setStatus(request.getParameter("status"));
        mbAttrType.setAttrType(request.getParameter("attrType"));
        Map  result =new HashMap();
        mbAttrTypeDao.insert(mbAttrType);
        result.put("retStatus", "S");
        result.put("retMsg", "添加成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/update")
    @Transactional
    public void  update(HttpServletRequest request, PrintWriter printWriter) {
        MbAttrType mbAttrType=new MbAttrType();
        MbAttrType mbAttrType1=new MbAttrType();
        mbAttrType1.setAttrKey(request.getParameter("attrKey").split(",")[1]);
        mbAttrTypeDao.deleteByPrimaryKey(mbAttrType1); //根据主键删除原数据
        mbAttrType.setAttrKey(request.getParameter("attrKey").split(",")[0]);
        mbAttrType.setAttrDesc(request.getParameter("attrDesc"));
        mbAttrType.setAttrClass(request.getParameter("attrClass"));
        mbAttrType.setUseMethod(request.getParameter("useMethod"));
        mbAttrType.setValueMethod(request.getParameter("valueMethod"));
        mbAttrType.setBusiCategory(request.getParameter("busiCategory"));
        mbAttrType.setStatus(request.getParameter("status"));
        mbAttrType.setAttrType(request.getParameter("attrType"));
        Map  result =new HashMap();
        mbAttrTypeDao.insert(mbAttrType);   //重新添加完成包含主键在内的数据修改
        result.put("retStatus", "S");
        result.put("retMsg", "修改成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request,PrintWriter printWriter) {
        MbAttrType mbAttrType=new MbAttrType();
        mbAttrType.setAttrKey(request.getParameter("attrKey"));
         Map  result =new HashMap();
        mbAttrTypeDao.deleteByPrimaryKey(mbAttrType);
        result.put("retStatus", "S");
        result.put("retMsg", "删除成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getAll")
    public void  getAll( PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<MbAttrType> mbAttrTypes= mbAttrTypeDao.getAll();
        out.put("data",mbAttrTypes);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getForPkList")
    public void getForPkList(PrintWriter printWriter){
        List<PkList> pklist=mbAttrClassDao.getForPkList();
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getOne")
    public void getOne(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out=new JSONObject();
        MbAttrType mbAttrType=mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(), request.getParameter("attrKey"));
        if(mbAttrType != null){
            out.put("retStatus","F");
            out.put("retMsg","参数代码已存在！");
        } else {
            out.put("retStatus","S");
        }
        out.put("data",mbAttrType);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getattrKey")
    public void getattrKey(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out=new JSONObject();
        MbAttrType mbAttrType=mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(), request.getParameter("attrKey"));
        if(mbAttrType == null||mbAttrType.getAttrKey() ==null){
            out.put("retStatus","F");
            out.put("retMsg","参数代码不存在！");
        } else {
            out.put("retStatus","S");
        }
        out.put("data",mbAttrType);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/getAttrType")
    public void getAttrType(PrintWriter printWriter) {
        List<PkList> pklist = mbAttrTypeDao.getAttrType();
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getValueMethod")
    public void  getValueMethod(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        MbAttrType mbAttrType=mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(), request.getParameter("attrKey"));
        out.put("data",mbAttrType);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

}

