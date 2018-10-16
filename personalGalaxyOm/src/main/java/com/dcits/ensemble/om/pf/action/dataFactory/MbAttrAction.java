package com.dcits.ensemble.om.pf.action.dataFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pf.dao.dataFactory.MbAttrClassDao;
import com.dcits.ensemble.om.pf.dao.dataFactory.MbAttrTypeDao;
import com.dcits.ensemble.om.pf.module.dataFactory.MbAttrClass;
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
@RequestMapping("/attr")
public class MbAttrAction {
    @Resource
    private MbAttrClassDao mbAttrClassDao;

    @Resource
    private MbAttrTypeDao mbAttrTypeDao;

    @RequestMapping("/insert")
    public void insert(HttpServletRequest request, PrintWriter printWriter) {
        MbAttrClass mbAttrClass=new MbAttrClass();
        mbAttrClass.setAttrClass(request.getParameter("attrClass"));
        mbAttrClass.setAttrClassDesc(request.getParameter("attrClassDesc"));
        mbAttrClass.setAttrClassLevel(request.getParameter("attrClassLevel"));
        mbAttrClass.setParentAttrClass(request.getParameter("parentAttrClass"));
        Map  result =new HashMap();
        mbAttrClassDao.insert(mbAttrClass);
        result.put("retStatus", "S");
        result.put("retMsg", "添加成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/update")
    @Transactional
    public void  update(HttpServletRequest request, PrintWriter printWriter) {
        MbAttrClass mbAttrClass=new MbAttrClass();
        MbAttrClass mbAttrClass1=new MbAttrClass();
        mbAttrClass1.setAttrClass(request.getParameter("attrClass").split(",")[1]);
        mbAttrClassDao.deleteByPrimaryKey(mbAttrClass1); //根据主键删除
        mbAttrClass.setAttrClass(request.getParameter("attrClass").split(",")[0]);
        mbAttrClass.setAttrClassDesc(request.getParameter("attrClassDesc"));
        mbAttrClass.setAttrClassLevel(request.getParameter("attrClassLevel"));
        mbAttrClass.setParentAttrClass(request.getParameter("parentAttrClass"));
        Map  result =new HashMap();
        mbAttrClassDao.insert(mbAttrClass); //重新添加   完成包括逐渐再内的修改
        result.put("retStatus", "S");
        result.put("retMsg", "修改成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request,PrintWriter printWriter) {
        Map  result =new HashMap();
        MbAttrClass mbAttrClass=new MbAttrClass();
        mbAttrClass.setAttrClass(request.getParameter("attrClass"));
        List<MbAttrClass> mbAttrClassList=mbAttrClassDao.getByAttrClass(mbAttrClass);
        List<MbAttrType> mbAttrTypeList=mbAttrTypeDao.getAttrKey(mbAttrClass);
        if (!mbAttrTypeList.isEmpty() || !mbAttrClassList.isEmpty()){
            result.put("retStatus","F");
            result.put("retMsg","该分类下存在参数，不能删除！");
        } else {
            mbAttrClassDao.deleteByPrimaryKey(mbAttrClass);
            result.put("retStatus", "S");
            result.put("retMsg", "删除成功");
        }

        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getAll")
    public void  getAll(PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<MbAttrClass> mbAttrClassList= mbAttrClassDao.getAll();
        out.put("data",mbAttrClassList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getOne")
    public void getOne(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out=new JSONObject();
        MbAttrClass mbAttrClass=mbAttrClassDao.selectByPrimaryKey(new MbAttrClass(), request.getParameter("attrClass"));
        if(mbAttrClass != null){
            out.put("retStatus","F");
            out.put("retMsg","参数分类代码已存在！");
        } else {
            out.put("retStatus","S");
        }
        out.put("data",mbAttrClass);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getList")
    public void getList(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out=new JSONObject();
        MbAttrClass mbAttrClass=new MbAttrClass();
        mbAttrClass.setParentAttrClass(request.getParameter("parentAttrClass"));
        List<MbAttrClass> mbAttrClassList=mbAttrClassDao.getList(mbAttrClass);
        out.put("data",mbAttrClassList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getDesc")
    public void  getDescByType( PrintWriter printWriter){
        JSONObject out=new JSONObject();
        List<MbAttrClass> mbAttrClassList=mbAttrClassDao.getAll();
        Map<String ,String> map=new HashMap<>();
        for(MbAttrClass attrClass:mbAttrClassList ){
            map.put(attrClass.getAttrClass(),attrClass.getAttrClassDesc());
        }
        out.put("data",map);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();

    }
}

