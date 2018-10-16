package com.dcits.ensemble.om.pf.action.dataFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pf.dao.dataFactory.MbPartClassDao;
import com.dcits.ensemble.om.pf.dao.dataFactory.MbPartTypeDao;
import com.dcits.ensemble.om.pf.module.PkList;
import com.dcits.ensemble.om.pf.module.dataFactory.MbPartType;
import com.dcits.ensemble.om.pf.util.ProdToMap;
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
@RequestMapping("/partType")
public class MbPartTypeAction {
    @Resource
    private MbPartTypeDao mbPartTypeDao;
    @Resource
    private MbPartClassDao mbPartClassDao;
    @RequestMapping("/insert")
    public void insert(HttpServletRequest request, PrintWriter printWriter) {
        MbPartType mbPartType=new MbPartType();
        mbPartType.setPartType(request.getParameter("partType"));
        mbPartType.setPartDesc(request.getParameter("partDesc"));
        mbPartType.setPartClass(request.getParameter("partClass"));
        mbPartType.setProcessMethod(request.getParameter("processMethod"));
        mbPartType.setBusiCategory(request.getParameter("busiCategory"));
        mbPartType.setStatus(request.getParameter("status"));
        mbPartType.setDefaultPart(request.getParameter("partType"));
        mbPartType.setIsStandard("Y");
        Map  result =new HashMap();
        ProdToMap.partType(mbPartType, "Insert",request.getParameter("reqNum"));
      //  mbPartTypeDao.insert(mbPartType);
        result.put("retStatus", "S");
        result.put("retMsg", "添加成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/update")
    @Transactional
    public void  update(HttpServletRequest request, PrintWriter printWriter) {
        MbPartType mbPartType=new MbPartType();
        mbPartType.setPartType(request.getParameter("partType"));
        MbPartType mbPartType1=mbPartTypeDao.selectByPrimaryKey(mbPartType,mbPartType.getPartType());
        mbPartType.setIsStandard(mbPartType1.getIsStandard());
        mbPartType.setDefaultPart(mbPartType1.getDefaultPart());
        mbPartType.setPartDesc(request.getParameter("partDesc"));
        mbPartType.setPartClass(request.getParameter("partClass"));
        mbPartType.setStatus(request.getParameter("status"));
        mbPartType.setProcessMethod(request.getParameter("processMethod"));
        mbPartType.setBusiCategory(request.getParameter("busiCategory"));
        Map result =new HashMap();
        ProdToMap.partType(mbPartType, "Modify", request.getParameter("reqNum"));
        //mbPartTypeDao.updateByPrimaryKey(mbPartType);
        result.put("retStatus", "S");
        result.put("retMsg", "修改成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request,PrintWriter printWriter) {
        MbPartType mbPartType=new MbPartType();
        mbPartType.setPartType(request.getParameter("partType"));
         Map  result =new HashMap();
        ProdToMap.partType(mbPartType, "Delete", request.getParameter("reqNum"));
        //mbPartTypeDao.deleteByPrimaryKey(mbPartType);
        result.put("retStatus", "S");
        result.put("retMsg", "删除成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getAll")
    public void  getAll(PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<MbPartType> mbPartTypes= mbPartTypeDao.getAll();
        out.put("data",mbPartTypes);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getAllContrast")
    public void  getAllContrast( PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<MbPartType> mbPartTypes= mbPartTypeDao.getAllContrast();
        out.put("data",mbPartTypes);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getAllN")
    public void  getAllN( PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<MbPartType> mbPartTypes= mbPartTypeDao.getAllN();
        out.put("data",mbPartTypes);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/selectByPrimaryKey")
    public void  selectByPrimaryKey(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        MbPartType mbPartTypes= mbPartTypeDao.selectByPrimaryKey(new MbPartType(), request.getParameter("partType"));
        out.put("data",mbPartTypes);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("getOne")
    public void getOne(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        MbPartType mbPartType=mbPartTypeDao.selectByPrimaryKey(new MbPartType(), request.getParameter("partType"));
        if(mbPartType==null){
            out.put("retStatus","F");
            out.put("retMsg","部件不存在！");
        } else {
            out.put("retStatus","S");
        }
        out.put("data",mbPartType);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
      }


    @RequestMapping("/forCheck")
    public void forCheck(HttpServletRequest request,PrintWriter printWriter){
        JSONObject out = new JSONObject();
        String mbPartClass=mbPartTypeDao.getForCheck(request.getParameter("partType"));
        out.put("data",mbPartClass);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getForPkList")
    public void getForPkList(PrintWriter printWriter){
        List<PkList> pklist=mbPartClassDao.getForPkList();
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getByPartClass")
    public void getByPartClass(HttpServletRequest request ,PrintWriter printWriter){
        MbPartType mbPartType=new MbPartType();
        mbPartType.setPartClass(request.getParameter("partClass"));
        List<MbPartType> partType=mbPartTypeDao.getPartTypeByClass(mbPartType);
        printWriter.print(JSON.toJSONString(partType));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/getPartTypeKey")
    public void getPartTypeKey(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out=new JSONObject();
        MbPartType mbPartType=mbPartTypeDao.selectByPrimaryKey(new MbPartType(), request.getParameter("partType"));
        if(mbPartType != null){
            out.put("retStatus","F");
            out.put("retMsg","指标代码已存在！");
        } else {
            out.put("retStatus","S");
        }
        out.put("data", mbPartType);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

}

