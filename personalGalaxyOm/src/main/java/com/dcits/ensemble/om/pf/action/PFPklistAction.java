package com.dcits.ensemble.om.pf.action;

import com.alibaba.fastjson.JSON;

import com.dcits.ensemble.om.pf.dao.dataFactory.*;

import com.dcits.ensemble.om.pf.dao.tools.ParamTableSelectDao;
import com.dcits.ensemble.om.pf.module.PkList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Tim on 2015/6/15.
 */
@Controller()
@RequestMapping("/pklist")
public class PFPklistAction {


    @Resource
    MbEventNodesDao mbEventNodesDao;
    @Resource
    ParamTableSelectDao paramTableSelectDao;

    @Resource
    MbAttrClassDao mbAttrClassDao;

    @Resource
    MbAttrValueDao mbAttrValueDao;

    @Resource
    MbProdGroupDao mbProdGroupDao;

    @Resource
    MbPartClassDao mbPartClassDao;

    @Resource
    MbProdClassDao mbProdClassDao;

    @Resource
    MbProdTypeDao mbProdTypeDao;

    @Resource
    MbProdDefineDao mbProdDefineDao;

    @Resource
    MbEventClassDao mbEventClassDao;

    @Resource
    MbAttrTypeDao mbAttrTypeDao;

    @RequestMapping("/getEventNodes")
    public void getEventNodes(HttpServletRequest request, PrintWriter printWriter) {
        String prodType = request.getParameter("prodType");
        String eventType = request.getParameter("eventType");
        List<PkList> pklist = mbEventNodesDao.getEventNodeforPklist(prodType, eventType);
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getParameterPklist")
    public void getParameterPKlist(HttpServletRequest request, PrintWriter printWriter){
        Map<String,String> map=new HashMap<>();
        String value=request.getParameter("tableCol");
        map.put("value",value.substring(0,value.indexOf(",")));
        map.put("valueDesc",value.substring(value.indexOf(",")+1));
        map.put("tableName",request.getParameter("tableName"));
        List<PkList> pklist= paramTableSelectDao.selectParameterPklist(map);
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getattrKey1")
    public void getattrKey1( PrintWriter printWriter) {
        List<PkList> pklist =mbAttrTypeDao.getattrKey1();
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/getBmodule")
    public void getBmodule(PrintWriter printWriter) {
        List<PkList> pklist = mbProdClassDao.getBmodule();
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getBmodule1")
    public void getBmodule1( PrintWriter printWriter) {
        List<PkList> pklist = mbPartClassDao.getBmodule();
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getprodClass")
    public void getprodClass(HttpServletRequest request, PrintWriter printWriter) {
        List<PkList> pklist = mbProdClassDao.getprodClass(request.getParameter("Bmodule"));
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getbaseProd")
    public void getbaseProd(HttpServletRequest request, PrintWriter printWriter) {
        List<PkList> pklist = mbProdTypeDao.getbaseProd(request.getParameter("Bmodule"));
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getChildProd")
    public void getChildProd(HttpServletRequest request, PrintWriter printWriter) {
        List<PkList> pklist = mbProdTypeDao.getChildProd(request.getParameter("Bmodule"));
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getSubProdByProdType")
    public void getSubProdByProdType(HttpServletRequest request, PrintWriter printWriter) {
        List<PkList> pklist = mbProdGroupDao.getSubProdByProdType(request.getParameter("ProdType"));
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/geteventClass")
    public void geteventClass(HttpServletRequest request, PrintWriter printWriter) {
        List<PkList> pklist = mbEventClassDao.geteventClass(request.getParameter("Bmodule"));
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getpartClass")
    public void getpartClass(HttpServletRequest request, PrintWriter printWriter) {
        List<PkList> pklist = mbPartClassDao.getpartClass(request.getParameter("Bmodule"));
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/getparentAttrClass")
    public void getparentAttrClass(HttpServletRequest request, PrintWriter printWriter) {
        String attrClassLevel = request.getParameter("attrClassLevel");
        List<PkList> pklist = mbAttrClassDao.getparentAttrClass(attrClassLevel);
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getparentPartClass")
    public void getparentPartClass(HttpServletRequest request, PrintWriter printWriter) {
        String partClassLevel = request.getParameter("partClassLevel");
        List<PkList> pklist = mbPartClassDao.getparentPartClass(partClassLevel);
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getparentEventClass")
    public void getparentEventClass(HttpServletRequest request, PrintWriter printWriter) {
        String eventClassLevel = request.getParameter("eventClassLevel");
        List<PkList> pklist = mbEventClassDao.getparentEventClass(eventClassLevel);
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getparentProdClass")
    public void getparentProdClass(HttpServletRequest request, PrintWriter printWriter) {
        String prodClassLevel = request.getParameter("prodClassLevel");
        List<PkList> pklist = mbProdClassDao.getparentProdClass(prodClassLevel);
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getattrClass")
    public void getattrClass(HttpServletRequest request, PrintWriter printWriter) {
        String Bmodule = request.getParameter("Bmodule");
        List<PkList> pklist =mbAttrTypeDao.getattrClass(Bmodule);
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getEventByprod")
    public void getEventByprod(HttpServletRequest request, PrintWriter printWriter) {
        String prodType = request.getParameter("prodType");
        List<PkList> pklist = mbProdDefineDao.getEventByprod(prodType);
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }
}
