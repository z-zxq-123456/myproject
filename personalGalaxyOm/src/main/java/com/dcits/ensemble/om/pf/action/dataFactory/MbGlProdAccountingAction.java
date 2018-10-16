package com.dcits.ensemble.om.pf.action.dataFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pf.dao.dataFactory.GlProdAccountingPfDao;
import com.dcits.ensemble.om.pf.module.dataFactory.GlProdAccountingPf;
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
 * Created by zhoujbc on 2016/5/10.
 */
@Controller
@RequestMapping("/glProdAccounting")
public class MbGlProdAccountingAction {
    @Resource
    GlProdAccountingPfDao glProdAccountingPfDao;

    @RequestMapping("/insert")
    public void insert(HttpServletRequest request, PrintWriter printWriter){
        GlProdAccountingPf glProdAccountingPf = new GlProdAccountingPf();
        glProdAccountingPf.setProdType(request.getParameter("prodType"));
        glProdAccountingPf.setAccountingStatus(request.getParameter("accountingStatus"));
        glProdAccountingPf.setBusinessUnit(request.getParameter("businessUnit"));
        glProdAccountingPf.setGlCodeA(request.getParameter("glCodeA"));
        glProdAccountingPf.setGlCodeIntAcr(request.getParameter("glCodeIntAcr"));
        glProdAccountingPf.setGlCodeIntE(request.getParameter("glCodeIntE"));
        glProdAccountingPf.setGlCodeIntI(request.getParameter("glCodeIntI"));
        glProdAccountingPf.setGlCodeIntPay(request.getParameter("glCodeIntPay"));
        glProdAccountingPf.setGlCodeIntRec(request.getParameter("glCodeIntRec"));
        glProdAccountingPf.setGlCodeL(request.getParameter("glCodeL"));
        glProdAccountingPf.setGlCodeOdiAcr(request.getParameter("glCodeOdiAcr"));
        glProdAccountingPf.setGlCodeOdiRec(request.getParameter("glCodeOdiRec"));
        glProdAccountingPf.setGlCodeOdpAcr(request.getParameter("glCodeOdpAcr"));
        glProdAccountingPf.setGlCodeOdpI(request.getParameter("glCodeOdpI"));
        glProdAccountingPf.setGlCodeOdpRec(request.getParameter("glCodeOdpRec"));
        glProdAccountingPf.setGlCodeOdiI(request.getParameter("glCodeOdiI"));
        glProdAccountingPf.setProfitCentre(request.getParameter("profitCentre"));
        glProdAccountingPf.setGlCodeALoss(request.getParameter("glCodeALoss"));
        glProdAccountingPf.setGlCodeAdjust(request.getParameter("glCodeAdjust"));
        Map  result =new HashMap();
        if(null!=glProdAccountingPfDao.selectByPrimaryKey(glProdAccountingPf,glProdAccountingPf.getProdType(),glProdAccountingPf.getAccountingStatus()))
        {
            result.put("retStatus", "F");
            result.put("retMsg", "主键冲突");
        }else {
            if (request.getParameter("reqNum") != null) {
                ProdToMap.glProdAccountingPf(glProdAccountingPf, "Insert", request.getParameter("reqNum"));
            } else {
                glProdAccountingPfDao.insert(glProdAccountingPf);
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
    public void  update(HttpServletRequest request, PrintWriter printWriter) {
        GlProdAccountingPf glProdAccountingPf = new GlProdAccountingPf();
        glProdAccountingPf.setProdType(request.getParameter("prodType"));
        glProdAccountingPf.setAccountingStatus(request.getParameter("accountingStatus"));
        glProdAccountingPf.setBusinessUnit(request.getParameter("businessUnit"));
        glProdAccountingPf.setGlCodeA(request.getParameter("glCodeA"));
        glProdAccountingPf.setGlCodeIntAcr(request.getParameter("glCodeIntAcr"));
        glProdAccountingPf.setGlCodeIntE(request.getParameter("glCodeIntE"));
        glProdAccountingPf.setGlCodeIntI(request.getParameter("glCodeIntI"));
        glProdAccountingPf.setGlCodeIntPay(request.getParameter("glCodeIntPay"));
        glProdAccountingPf.setGlCodeIntRec(request.getParameter("glCodeIntRec"));
        glProdAccountingPf.setGlCodeL(request.getParameter("glCodeL"));
        glProdAccountingPf.setGlCodeOdiAcr(request.getParameter("glCodeOdiAcr"));
        glProdAccountingPf.setGlCodeOdiRec(request.getParameter("glCodeOdiRec"));
        glProdAccountingPf.setGlCodeOdpAcr(request.getParameter("glCodeOdpAcr"));
        glProdAccountingPf.setGlCodeOdpI(request.getParameter("glCodeOdpI"));
        glProdAccountingPf.setGlCodeOdpRec(request.getParameter("glCodeOdpRec"));
        glProdAccountingPf.setGlCodeOdiI(request.getParameter("glCodeOdiI"));
        glProdAccountingPf.setProfitCentre(request.getParameter("profitCentre"));
        glProdAccountingPf.setGlCodeALoss(request.getParameter("glCodeALoss"));
        glProdAccountingPf.setGlCodeAdjust(request.getParameter("glCodeAdjust"));
        Map  result =new HashMap();
        if(request.getParameter("reqNum")!=null) {
            ProdToMap.glProdAccountingPf(glProdAccountingPf, "Modify", request.getParameter("reqNum"));
        }else{
            glProdAccountingPfDao.updateByPrimaryKey(glProdAccountingPf);
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
        List<GlProdAccountingPf> glProdAccountingPf = glProdAccountingPfDao.getAll(request.getParameter("prodType"));
        out.put("data", glProdAccountingPf);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getAllByNewProdType")
    public void  getAllByNewProdType(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<GlProdAccountingPf> glProdAccountingPfList=glProdAccountingPfDao.selectListByPrimaryKey(new GlProdAccountingPf(),request.getParameter("prodType"),null);
       for(GlProdAccountingPf glProdAccountingPf:glProdAccountingPfList){
           glProdAccountingPf.setProdType(request.getParameter("newProdType"));
           if(request.getParameter("reqNum")!=null) {
               ProdToMap.glProdAccountingPf(glProdAccountingPf, "Insert", request.getParameter("reqNum"));
           }else{
               glProdAccountingPfDao.insert(glProdAccountingPf);
           }
       }
        out.put("data",glProdAccountingPfList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/delete")
    public void delete(HttpServletRequest request,PrintWriter printWriter) {
        GlProdAccountingPf glProdAccountingPf = new GlProdAccountingPf();
        glProdAccountingPf.setProdType(request.getParameter("prodType"));
        glProdAccountingPf.setAccountingStatus(request.getParameter("accountingStatus"));
        Map result =new HashMap();
        if(request.getParameter("reqNum")!=null) {
            ProdToMap.glProdAccountingPf(glProdAccountingPf, "Delete", request.getParameter("reqNum"));
        }else{
            glProdAccountingPfDao.deleteByPrimaryKey(glProdAccountingPf);
        }
        result.put("retStatus", "S");
        result.put("retMsg", "删除成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

}
