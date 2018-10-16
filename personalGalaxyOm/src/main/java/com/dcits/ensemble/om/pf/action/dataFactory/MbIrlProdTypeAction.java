package com.dcits.ensemble.om.pf.action.dataFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pf.dao.dataFactory.IrlProdTypePfDao;
import com.dcits.ensemble.om.pf.module.dataFactory.IrlProdTypePf;
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
@RequestMapping("/irlProdType")
public class MbIrlProdTypeAction {
    @Resource
    IrlProdTypePfDao irlProdTypePfDao;

    @RequestMapping("/insert")
    public void insert(HttpServletRequest request, PrintWriter printWriter){
        IrlProdTypePf irlProdTypePf =new IrlProdTypePf();
        irlProdTypePf.setProdType(request.getParameter("prodType"));
        irlProdTypePf.setProdGrp(request.getParameter("prodGrp"));
        irlProdTypePf.setCompany(request.getParameter("company"));
        irlProdTypePf.setIntDateType(request.getParameter("intDateType"));
        irlProdTypePf.setFixedCall(request.getParameter("fixedCall"));
        irlProdTypePf.setProdTypeDesc(request.getParameter("prodTypeDesc"));
        Map  result =new HashMap();
        if(request.getParameter("reqNum")!=null) {
            if(null!=irlProdTypePfDao.selectByPrimaryKey(irlProdTypePf,irlProdTypePf.getProdType()))
            {
                result.put("retStatus", "F");
                result.put("retMsg", "主键冲突");
            }else {
                ProdToMap.irlProdTypePf(irlProdTypePf, "Insert", request.getParameter("reqNum"));
                result.put("retStatus", "S");
                result.put("retMsg", "添加成功");
            }
        }else{
            irlProdTypePfDao.insert(irlProdTypePf);
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
        IrlProdTypePf irlProdTypePf =new IrlProdTypePf();
        irlProdTypePf.setProdType(request.getParameter("prodType"));
        irlProdTypePf.setProdGrp(request.getParameter("prodGrp"));
        irlProdTypePf.setCompany(request.getParameter("company"));
        irlProdTypePf.setIntDateType(request.getParameter("intDateType"));
        irlProdTypePf.setFixedCall(request.getParameter("fixedCall"));
        irlProdTypePf.setProdTypeDesc(request.getParameter("prodTypeDesc"));
        Map  result =new HashMap();
        if(request.getParameter("reqNum")!=null) {
            ProdToMap.irlProdTypePf(irlProdTypePf, "Modify", request.getParameter("reqNum"));
        }else{
            irlProdTypePfDao.updateByPrimaryKey(irlProdTypePf);
        }
        result.put("retStatus", "S");
        result.put("retMsg", "修改成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/selectBase")
    public void  selectBase(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        IrlProdTypePf irlProdTypePf = new IrlProdTypePf();
        if(request.getParameter("prodType") ==""){
            irlProdTypePf.setProdType(null);
        }else{
            irlProdTypePf.setProdType(request.getParameter("prodType"));
        }
        if(request.getParameter("prodGrp") ==""){
            irlProdTypePf.setProdGrp(null);
        }else{
            irlProdTypePf.setProdGrp(request.getParameter("prodGrp"));
        }
        List<IrlProdTypePf> irlProdTypePfs = irlProdTypePfDao.selectBase(irlProdTypePf);
        out.put("data", irlProdTypePfs);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/getAll")
    public void  getAll(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<IrlProdTypePf> irlProdTypePf = irlProdTypePfDao.getAll(request.getParameter("prodType"));
        out.put("data", irlProdTypePf);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getAllByNewProdType")
    public void  getAllByNewProdType(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<IrlProdTypePf> irlProdTypePfList= irlProdTypePfDao.selectListByPrimaryKey(new IrlProdTypePf(),request.getParameter("prodType"));
        for(IrlProdTypePf irlProdTypePf:irlProdTypePfList)
        {
            irlProdTypePf.setProdType(request.getParameter("newProdType"));
            irlProdTypePf.setProdTypeDesc(request.getParameter("newProdDesc"));
            if(request.getParameter("reqNum")!=null) {
                ProdToMap.irlProdTypePf(irlProdTypePf,"Insert",request.getParameter("reqNum"));
            }else{
                irlProdTypePfDao.insert(irlProdTypePf);
            }
        }
        out.put("data", irlProdTypePfList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/delete")
    public void delete(HttpServletRequest request,PrintWriter printWriter){
        IrlProdTypePf irlProdTypePf = new IrlProdTypePf();
        irlProdTypePf.setProdType(request.getParameter("prodType"));
        Map result =new HashMap();
        if(request.getParameter("reqNum")!=null) {
            ProdToMap.irlProdTypePf(irlProdTypePf, "Delete", request.getParameter("reqNum") );
        }else{
            irlProdTypePfDao.deleteByPrimaryKey(irlProdTypePf);
        }
        result.put("retStatus", "S");
        result.put("retMsg", "删除成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getProdTypeKey")
    public void getProdTypeKey(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out=new JSONObject();
        IrlProdTypePf irlProdTypePf= irlProdTypePfDao.selectByPrimaryKey(new IrlProdTypePf(), request.getParameter("prodType"));
        if(irlProdTypePf != null){
            out.put("retStatus","F");
            out.put("retMsg","利率产品代码已存在,请重新输入新产品类型！！！");
        } else {
            out.put("retStatus", "S");
        }
        out.put("data", irlProdTypePf);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }


}
