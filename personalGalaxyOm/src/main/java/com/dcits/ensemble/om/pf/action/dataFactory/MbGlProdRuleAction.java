package com.dcits.ensemble.om.pf.action.dataFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pf.dao.dataFactory.GlProdRulePfDao;
import com.dcits.ensemble.om.pf.module.dataFactory.GlProdRulePf;
import com.dcits.ensemble.om.pf.util.ProdToMap;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoujbc on 2016/5/10.
 */
@Controller
@RequestMapping("/glProdRule")
public class MbGlProdRuleAction {
    @Resource
    GlProdRulePfDao glProdRulePfDao;
    @RequestMapping("/insert")
    public void insert(HttpServletRequest request, PrintWriter printWriter){
        GlProdRulePf glProdRulePf = new GlProdRulePf();
        glProdRulePf.setProdType(request.getParameter("prodType"));
        glProdRulePf.setAccountingDesc(request.getParameter("accountingDesc"));
        glProdRulePf.setAccountingNo(request.getParameter("accountingNo"));
        glProdRulePf.setAccountingStatus(request.getParameter("accountingStatus"));
        glProdRulePf.setCcy(request.getParameter("ccy"));
        glProdRulePf.setClientType(request.getParameter("clientType"));
        glProdRulePf.setCustomRule(request.getParameter("customRule"));
        glProdRulePf.setSourceType(request.getParameter("sourceType"));
        glProdRulePf.setSysName(request.getParameter("sysName"));
        glProdRulePf.setTranEventType(request.getParameter("tranEventType"));
        glProdRulePf.setTranType(request.getParameter("tranType"));
        glProdRulePf.setSourceModule(request.getParameter("sourceModule"));
        Map  result =new HashMap();
        if(null!=glProdRulePfDao.selectByPrimaryKey(glProdRulePf,glProdRulePf.getProdType(),glProdRulePf.getSysName(),glProdRulePf.getSourceType(),glProdRulePf.getClientType(),glProdRulePf.getAccountingStatus(), glProdRulePf.getTranEventType(),glProdRulePf.getCcy(),glProdRulePf.getTranType()))
        {
            result.put("retStatus", "F");
            result.put("retMsg", "主键冲突");
        }else {
            if (request.getParameter("reqNum") != null) {
                ProdToMap.glProdRulePf(glProdRulePf, "Insert", request.getParameter("reqNum"));
            } else {
                glProdRulePfDao.insert(glProdRulePf);
            }
            result.put("retStatus", "S");
            result.put("retMsg", "添加成功");
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/update")
    @Transactional
    public void  update(HttpServletRequest request, PrintWriter printWriter){
        GlProdRulePf glProdRulePf = new GlProdRulePf();
        glProdRulePf.setProdType(request.getParameter("prodType"));
        glProdRulePf.setAccountingDesc(request.getParameter("accountingDesc"));
        glProdRulePf.setAccountingNo(request.getParameter("accountingNo"));
        glProdRulePf.setAccountingStatus(request.getParameter("accountingStatus"));
        glProdRulePf.setCcy(request.getParameter("ccy"));
        glProdRulePf.setClientType(request.getParameter("clientType"));
        glProdRulePf.setCustomRule(request.getParameter("customRule"));
        glProdRulePf.setSourceType(request.getParameter("sourceType"));
        glProdRulePf.setSysName(request.getParameter("sysName"));
        glProdRulePf.setTranEventType(request.getParameter("tranEventType"));
        glProdRulePf.setTranType(request.getParameter("tranType"));
        glProdRulePf.setSourceModule(request.getParameter("sourceModule"));
        Map  result =new HashMap();
        if(request.getParameter("reqNum")!=null) {
            ProdToMap.glProdRulePf(glProdRulePf, "Modify", request.getParameter("reqNum"));
        }else{
            glProdRulePfDao.updateByPrimaryKey(glProdRulePf);
        }
        result.put("retStatus", "S");
        result.put("retMsg", "修改成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/getAll")
    public void  getAll(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<GlProdRulePf> glProdRulePfs = glProdRulePfDao.getAll(request.getParameter("prodType"));
        out.put("data", glProdRulePfs);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getAllByNewProdType")
    public void  getAllByNewProdType(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        GlProdRulePf glProdRulePf = new GlProdRulePf();
        List<GlProdRulePf> glProdRulePfList=glProdRulePfDao.selectListByPrimaryKey(glProdRulePf,request.getParameter("prodType"),null,null,null,null,null,null,null);
        for(GlProdRulePf glProdRulePf1:glProdRulePfList)
        {
            glProdRulePf1.setProdType(request.getParameter("newProdType"));
            if(request.getParameter("reqNum")!=null) {
                ProdToMap.glProdRulePf(glProdRulePf1, "Insert", request.getParameter("reqNum"));
            }
            else{
                glProdRulePfDao.insert(glProdRulePf1);
            }

        }
        out.put("data",glProdRulePfList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/delete")
    public void delete(HttpServletRequest request,PrintWriter printWriter){
        GlProdRulePf glProdRulePf = new GlProdRulePf();
        glProdRulePf.setProdType(request.getParameter("prodType"));
        glProdRulePf.setSysName(request.getParameter("sysName"));
        glProdRulePf.setSourceType(request.getParameter("sourceType"));
        glProdRulePf.setClientType(request.getParameter("clientType"));
        glProdRulePf.setAccountingStatus(request.getParameter("accountingStatus"));
        glProdRulePf.setTranEventType(request.getParameter("tranEventType"));
        glProdRulePf.setCcy(request.getParameter("ccy"));
        glProdRulePf.setTranType(request.getParameter("tranType"));
        glProdRulePf.setSourceModule(request.getParameter("sourceModule"));
        Map result =new HashMap();
        if(request.getParameter("reqNum")!=null) {
            ProdToMap.glProdRulePf(glProdRulePf, "Delete", request.getParameter("reqNum"));
        }else{
            glProdRulePfDao.deleteByPrimaryKey(glProdRulePf);
        }
        result.put("retStatus", "S");
        result.put("retMsg", "删除成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    public BigDecimal bigDecimal(String str){
        BigDecimal decimal;
        if(str == "" || str == null){
            decimal = null;
            return decimal;
        }else{
            decimal = new BigDecimal(str);
            return decimal;
        }
    }
}
