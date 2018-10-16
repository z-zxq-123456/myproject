package com.dcits.ensemble.om.pf.action.dataFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pf.dao.dataFactory.MbProdTypeDao;
import com.dcits.ensemble.om.pf.module.dataFactory.MbProdType;
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
@RequestMapping("/prodType")
public class MbProdTypeAction {
    @Resource
    private MbProdTypeDao mbProdTypeDao;

    @RequestMapping("/insert")
    public void insert(HttpServletRequest request, PrintWriter printWriter) {
        MbProdType mbProdType=new MbProdType();
        mbProdType.setProdType(request.getParameter("prodType"));
        mbProdType.setProdDesc(request.getParameter("prodDesc"));
        mbProdType.setProdClass(request.getParameter("prodClass"));
        mbProdType.setProdGroup(request.getParameter("prodGroup"));
        mbProdType.setProdRange(request.getParameter("prodRange"));
        mbProdType.setBaseProdType(request.getParameter("baseProdType"));
        mbProdType.setStatus(request.getParameter("status"));
        Map  result =new HashMap();
        ProdToMap.mbProdType(mbProdType, "Insert", request.getParameter("reqNum"));
       // mbProdTypeDao.insert(mbProdType);
        result.put("retStatus", "S");
        result.put("retMsg", "添加成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/update")
    @Transactional
    public void  update(HttpServletRequest request, PrintWriter printWriter) {
        MbProdType mbProdType=new MbProdType();
        mbProdType.setProdType(request.getParameter("prodType"));
        mbProdType.setProdDesc(request.getParameter("prodDesc"));
        mbProdType.setProdClass(request.getParameter("prodClass"));
        mbProdType.setProdGroup(request.getParameter("prodGroup"));
        mbProdType.setProdRange(request.getParameter("prodRange"));
        mbProdType.setBaseProdType(request.getParameter("baseProdType"));
        mbProdType.setStatus(request.getParameter("status"));
        Map  result =new HashMap();
        ProdToMap.mbProdType(mbProdType, "Modify", request.getParameter("reqNum"));
  //      mbProdTypeDao.updateByPrimaryKey(mbProdType);
        result.put("retStatus", "S");
        result.put("retMsg", "修改成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request,PrintWriter printWriter) {
        MbProdType mbProdType = new MbProdType();
        mbProdType.setProdType(request.getParameter("prodType"));
         Map  result =new HashMap();
        ProdToMap.mbProdType(mbProdType, "Delete", request.getParameter("reqNum"));
    //    mbProdTypeDao.deleteByPrimaryKey(mbProdType);
        result.put("retStatus", "S");
        result.put("retMsg", "删除成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getAll")
    public void  getAll(PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<MbProdType> mbProdTypes= mbProdTypeDao.getAll("N");
        out.put("data",mbProdTypes);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getAllContrast")
    public void  getAllContrast( PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<MbProdType> mbProdTypes= mbProdTypeDao.getAllContrast("N");
        out.put("data",mbProdTypes);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/getComAll")
    public void  getComAll(PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<MbProdType> mbProdTypes= mbProdTypeDao.getComAll("Y");
        out.put("data",mbProdTypes);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getComAllContrast")
    public void  getComAllContrast( PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<MbProdType> mbProdTypes= mbProdTypeDao.getAllContrast("Y");
        out.put("data",mbProdTypes);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/getOne")
    public void  getOne(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        MbProdType mbProdType= mbProdTypeDao.selectByPrimaryKey(new MbProdType(), request.getParameter("prodType"));
        if(mbProdType==null){
            out.put("retStatus","F");
            out.put("retMsg","产品不存在！");
        } else {
            out.put("retStatus","S");
        }
        out.put("data",mbProdType);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/selectByProdClass")
    public void selectByProdClass(PrintWriter printWriter,String prodClass){
        JSONObject out = new JSONObject();
        MbProdType mbProdType = new MbProdType();
        mbProdType.setProdClass(prodClass);
        mbProdType.setProdGroup("N");
        List<MbProdType> mbProdTypes= mbProdTypeDao.selectByProdClass(mbProdType);
        out.put("data",mbProdTypes);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/selectByProdClass1")
    public void selectByProdClass1(PrintWriter printWriter,String prodClass){
        JSONObject out = new JSONObject();
        MbProdType mbProdType = new MbProdType();
        mbProdType.setProdClass(prodClass);
        mbProdType.setProdGroup("Y");
        List<MbProdType> mbProdTypes= mbProdTypeDao.selectByProdClass1(mbProdType);
        out.put("data",mbProdTypes);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/selectByProdClass2")
    public void selectByProdClass2(PrintWriter printWriter,String prodClass){
        JSONObject out = new JSONObject();
        MbProdType mbProdType = new MbProdType();
        mbProdType.setProdClass(prodClass);
        List<MbProdType> mbProdTypes= mbProdTypeDao.selectByProdClass2(mbProdType);
        out.put("data",mbProdTypes);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/selectByPrimaryKey")
    public void  selectByPrimaryKey(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        MbProdType mbProdType= mbProdTypeDao.selectByPrimaryKey(new MbProdType(), request.getParameter("prodType"));
        out.put("data",mbProdType);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getProdTypeKey")
    public void getProdTypeKey(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out=new JSONObject();
        MbProdType mbProdType= mbProdTypeDao.selectByPrimaryKey(new MbProdType(), request.getParameter("prodType"));
        if(mbProdType != null){
            out.put("retStatus","F");
            out.put("retMsg","产品代码已存在！");
        } else {
            out.put("retStatus", "S");
        }
        out.put("data", mbProdType);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/selectByProdType")
    public void  selectByProdType(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        MbProdType mbProdType = new MbProdType();
        mbProdType.setProdType(request.getParameter("prodType"));
        List<MbProdType> mbProdTypeList= mbProdTypeDao.selectByProdType(mbProdType);
        out.put("data",mbProdTypeList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
}

