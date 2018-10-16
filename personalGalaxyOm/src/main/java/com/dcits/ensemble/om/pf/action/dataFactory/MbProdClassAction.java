package com.dcits.ensemble.om.pf.action.dataFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pf.dao.dataFactory.MbProdClassDao;
import com.dcits.ensemble.om.pf.dao.dataFactory.MbProdTypeDao;
import com.dcits.ensemble.om.pf.module.dataFactory.MbProdClass;
import com.dcits.ensemble.om.pf.module.dataFactory.MbProdType;
import com.dcits.ensemble.om.pf.util.action.ActionResultWrite;
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
@RequestMapping("/prod")
public class MbProdClassAction {
    @Resource
    private MbProdClassDao mbProdClassDao;
    @Resource
    private MbProdTypeDao mbProdTypeDao;
    @RequestMapping("/insert")
    public void insert(HttpServletRequest request, PrintWriter printWriter) {
        MbProdClass mbProdClass=new MbProdClass();
        mbProdClass.setProdClass(request.getParameter("prodClass"));
        mbProdClass.setProdClassDesc(request.getParameter("prodClassDesc"));
        mbProdClass.setProdClassLevel(request.getParameter("prodClassLevel"));
        mbProdClass.setParentProdClass(request.getParameter("parentProdClass"));
        Map  result =new HashMap();
        mbProdClassDao.insert(mbProdClass);
        result.put("retStatus", "S");
        result.put("retMsg", "添加成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/update")
    @Transactional
    public void  update(HttpServletRequest request, PrintWriter printWriter) {
        MbProdClass mbProdClass=new MbProdClass();
        MbProdClass mbProdClass1=new MbProdClass();
        mbProdClass1.setProdClass(request.getParameter("prodClass").split(",")[1]);
        mbProdClassDao.deleteByPrimaryKey(mbProdClass1); //根据主键删除原始数据
        mbProdClass.setProdClass(request.getParameter("prodClass").split(",")[0]);
        mbProdClass.setProdClassDesc(request.getParameter("prodClassDesc"));
        mbProdClass.setProdClassLevel(request.getParameter("prodClassLevel"));
        mbProdClass.setParentProdClass(request.getParameter("parentProdClass"));
        Map  result =new HashMap();
        mbProdClassDao.insert(mbProdClass);   //重新添加数据完成包含主键在内的字段修改
        result.put("retStatus", "S");
        result.put("retMsg", "修改成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/delete")
    public void delete(HttpServletRequest request,PrintWriter printWriter) {
        MbProdClass mbProdClass=new MbProdClass();
        mbProdClass.setProdClass(request.getParameter("prodClass"));
        String str = "%" + request.getParameter("prodClass") +"%";
        mbProdClass.setParentProdClass(str);
        Map  result =new HashMap();
        List<MbProdType> mbProdTypeList=mbProdTypeDao.getByProdClass(request.getParameter("prodClass"));
        List<MbProdClass> mbProdClassList=mbProdClassDao.getByParentProdClass(mbProdClass);
        if (!mbProdTypeList.isEmpty() || !mbProdClassList.isEmpty()){
            result.put("retStatus","F");
            result.put("retMsg","该分类下存在产品，不能删除！");
        } else {
            mbProdClassDao.deleteByPrimaryKey(mbProdClass);
            result.put("retStatus", "S");
            result.put("retMsg", "删除成功");
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/getAll")
    public void  getAll( PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<MbProdClass> mbProdClassList= mbProdClassDao.getAll();
        out.put("data",mbProdClassList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getByLevel")
    public void getByLevel(HttpServletRequest request ,PrintWriter printWriter,String prodClassLevel){
        MbProdClass mbProdClass = new MbProdClass();
        mbProdClass.setProdClassLevel(prodClassLevel);
        mbProdClass.setProdClass(request.getParameter("Bmodule"));
        List<MbProdClass> mbProdClassList= mbProdClassDao.getByLevel(mbProdClass);
        ActionResultWrite.setReadResult(printWriter, mbProdClassList);
    }
    @RequestMapping("/getByLevell")
    public void getByLevell(PrintWriter printWriter,String prodClassLevel){
        List<MbProdClass> mbProdClassList= mbProdClassDao.getByLevell(prodClassLevel);
        ActionResultWrite.setReadResult(printWriter, mbProdClassList);
    }
    @RequestMapping("/getByParP")
    public void getByParP(PrintWriter printWriter,MbProdClass info){
        List<MbProdClass> mbProdClassList= mbProdClassDao.getByProdClassLevel(info);
        ActionResultWrite.setReadResult(printWriter, mbProdClassList);

    }
    @RequestMapping("/getDesc")
    public void getDesc(PrintWriter printWriter){
        JSONObject out = new JSONObject();
        Map<String ,String> map=new HashMap<>();
        List<MbProdClass> mbPartClassList= mbProdClassDao.getAll();
        for(MbProdClass prodClass : mbPartClassList ){
            map.put(prodClass.getProdClass(),prodClass.getProdClassDesc());
        }
        out.put("data",map);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/getprodKey")
    public void getprodKey(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out=new JSONObject();
        MbProdClass mbProdClass=mbProdClassDao.selectByPrimaryKey(new MbProdClass(), request.getParameter("prodClass"));
        if(mbProdClass != null){
            out.put("retStatus","F");
            out.put("retMsg","产品分类代码已存在！");
        } else {
            out.put("retStatus","S");
        }
        out.put("data",mbProdClass);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getByBmodule")
    public void  getByBmodule(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<MbProdClass> mbProdClassList= mbProdClassDao.getByBmodule(request.getParameter("Bmodule"));
        out.put("data",mbProdClassList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
}

