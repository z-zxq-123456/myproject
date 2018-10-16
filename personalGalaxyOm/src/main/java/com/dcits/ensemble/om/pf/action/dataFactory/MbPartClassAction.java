package com.dcits.ensemble.om.pf.action.dataFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pf.dao.dataFactory.MbPartClassDao;
import com.dcits.ensemble.om.pf.dao.dataFactory.MbPartTypeDao;
import com.dcits.ensemble.om.pf.module.dataFactory.MbPartClass;
import com.dcits.ensemble.om.pf.module.dataFactory.MbPartType;
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
@RequestMapping("/part")
public class MbPartClassAction {
    @Resource
    private MbPartClassDao mbPartClassDao;

    @Resource
    private MbPartTypeDao mbPartTypeDao;

    @RequestMapping("/insert")
    public void insert(HttpServletRequest request, PrintWriter printWriter){
        MbPartClass mbPartClass=new MbPartClass();
        mbPartClass.setPartClass(request.getParameter("partClass"));
        mbPartClass.setPartClassDesc(request.getParameter("partClassDesc"));
        mbPartClass.setPartClassLevel(request.getParameter("partClassLevel"));
        mbPartClass.setParentPartClass(request.getParameter("parentPartClass"));
        Map  result =new HashMap();
        mbPartClassDao.insert(mbPartClass);
        result.put("retStatus", "S");
        result.put("retMsg", "添加成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/update")
    @Transactional
    public void  update(HttpServletRequest request, PrintWriter printWriter) {
        MbPartClass mbPartClass = new MbPartClass();
        MbPartClass mbPartClass1 = new MbPartClass();
        String partClass = request.getParameter("partClass");
        if (partClass != null) {
            mbPartClass1.setPartClass(partClass.split(",")[1]);
        }
        mbPartClassDao.deleteByPrimaryKey(mbPartClass1);  //根据主键删除原始数据
        if (partClass != null) {
            mbPartClass.setPartClass(partClass.split(",")[0]);
        }
        mbPartClass.setPartClassDesc(request.getParameter("partClassDesc"));
        mbPartClass.setPartClassLevel(request.getParameter("partClassLevel"));
        mbPartClass.setParentPartClass(request.getParameter("parentPartClass"));
        Map  result =new HashMap();
        mbPartClassDao.insert(mbPartClass);    //重新添加完成包含主键在内的数据的修改
        result.put("retStatus", "S");
        result.put("retMsg", "修改成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request,PrintWriter printWriter) {
        MbPartClass mbPartClass=new MbPartClass();
        mbPartClass.setPartClass(request.getParameter("partClass"));
         Map  result =new HashMap();
        List<MbPartClass> mbPartClassList=mbPartClassDao.getByPartClass(mbPartClass);
        List<MbPartType> mbPartTypeList=mbPartTypeDao.getPartKey(mbPartClass);
        if(mbPartClassList!=null&&mbPartTypeList!=null) {
            if (!mbPartTypeList.isEmpty()|| !mbPartClassList.isEmpty()){
                result.put("retStatus","F");
                result.put("retMsg","该分类下存在指标，不能删除！");
            } else {
                mbPartClassDao.deleteByPrimaryKey(mbPartClass);
                result.put("retStatus", "S");
                result.put("retMsg", "删除成功");
            }
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getAll")
    public void  getAll( PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<MbPartClass> mbPartClassList= mbPartClassDao.getAll();
        out.put("data",mbPartClassList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getByBmodule")
    public void  getByBmodule(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<MbPartClass> mbPartClassList= mbPartClassDao.getByBmodule(request.getParameter("Bmodule"));
        out.put("data",mbPartClassList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getDesc")
    public void getDesc(PrintWriter printWriter){
        JSONObject out = new JSONObject();
        Map<String ,String> map=new HashMap<>();
        List<MbPartClass> mbPartClassList= mbPartClassDao.getAll();
        for(MbPartClass partClass : mbPartClassList ){
            if(partClass!=null)
            map.put(partClass.getPartClass(),partClass.getPartClassDesc());
        }
        out.put("data",map);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();

    }
    @RequestMapping("/getpartKey")
    public void getpartKey(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out=new JSONObject();
        MbPartClass mbPartClass=mbPartClassDao.selectByPrimaryKey(new MbPartClass(), request.getParameter("partClass"));
        if(mbPartClass != null){
            out.put("retStatus","F");
            out.put("retMsg","指标分类代码已存在！");
        } else {
            out.put("retStatus","S");
        }
        out.put("data",mbPartClass);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
}

