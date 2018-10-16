package com.dcits.ensemble.om.pf.action.dataFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pf.dao.dataFactory.GlProdMappingPfDao;
import com.dcits.ensemble.om.pf.module.dataFactory.GlProdMappingPf;
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
@RequestMapping("/glProdMapping")
public class MbGlProdMappingAction {
    @Resource
    GlProdMappingPfDao glProdMappingPfDao;

    @RequestMapping("/insert")
    public void insert(HttpServletRequest request, PrintWriter printWriter){
        GlProdMappingPf glProdMappingPf = new GlProdMappingPf();
        glProdMappingPf.setProdType(request.getParameter("prodType"));
        glProdMappingPf.setProdDesc(request.getParameter("prodDesc"));
        glProdMappingPf.setMappingType(request.getParameter("mappingType"));
        glProdMappingPf.setMappingDesc(request.getParameter("mappingDesc"));
        Map  result =new HashMap();
        if(null==glProdMappingPfDao.selectByPrimaryKey(glProdMappingPf,glProdMappingPf.getMappingType()))
        {
            result.put("retStatus", "F");
            result.put("retMsg", "主键冲突");
        }else {
            if (request.getParameter("reqNum") != null) {
                ProdToMap.glProdMappingPf(glProdMappingPf, "Insert", request.getParameter("reqNum"));
            } else {
                glProdMappingPfDao.insert(glProdMappingPf);
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
        GlProdMappingPf glProdMappingPf = new GlProdMappingPf();
        glProdMappingPf.setProdType(request.getParameter("prodType"));
        glProdMappingPf.setProdDesc(request.getParameter("prodDesc"));
        glProdMappingPf.setMappingType(request.getParameter("mappingType"));
        glProdMappingPf.setMappingDesc(request.getParameter("mappingDesc"));
        Map  result =new HashMap();
        if(request.getParameter("reqNum")!=null) {
            ProdToMap.glProdMappingPf(glProdMappingPf, "Modify", request.getParameter("reqNum"));
        }else{
            glProdMappingPfDao.updateByPrimaryKey(glProdMappingPf);
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
        List<GlProdMappingPf> glProdMappingPfs = glProdMappingPfDao.getAll(request.getParameter("prodType"));
        out.put("data", glProdMappingPfs);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getAllByNewProdType")
    public void  getAllByNewProdType(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<GlProdMappingPf> glProdMappingPfList=glProdMappingPfDao.selectListByPrimaryKey(new GlProdMappingPf(),request.getParameter("prodType"));
       for(GlProdMappingPf glProdMappingPf1:glProdMappingPfList){
           glProdMappingPf1.setProdType(request.getParameter("newProdType"));
           glProdMappingPf1.setMappingType(request.getParameter("newProdType"));
           glProdMappingPf1.setMappingDesc(request.getParameter("newProdDesc"));
           glProdMappingPf1.setProdDesc(request.getParameter("newProdDesc"));
           if(request.getParameter("reqNum")!=null) {
               ProdToMap.glProdMappingPf(glProdMappingPf1, "Insert", request.getParameter("reqNum"));
           }else {
               glProdMappingPfDao.insert(glProdMappingPf1);
           }
       }
        //glProdMappingPfDao.insertNewProdType(glProdMappingPf);
        //List<GlProdMappingPf> glProdMappingPfList= glProdMappingPfDao.getAll(request.getParameter("newProdType"));
        //System.out.print("长度：" + glProdMappingPfList.size());
        out.put("data",glProdMappingPfList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/delete")
    public void delete(HttpServletRequest request,PrintWriter printWriter){
        GlProdMappingPf glProdMappingPf = new GlProdMappingPf();
        glProdMappingPf.setMappingType(request.getParameter("mappingType"));
        Map result =new HashMap();
        if(request.getParameter("reqNum")!=null) {
            ProdToMap.glProdMappingPf(glProdMappingPf, "Delete", request.getParameter("reqNum") + "A");
        }else{
            glProdMappingPfDao.deleteByPrimaryKey(glProdMappingPf);
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
