package com.dcits.ensemble.om.pf.action.dataFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pf.dao.dataFactory.IrlProdIntPfDao;
import com.dcits.ensemble.om.pf.module.dataFactory.IrlProdIntPf;
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
@RequestMapping("/irlProdInt")
public class MbIrlProdIntAction {
    @Resource
    IrlProdIntPfDao irlProdIntPfDao;

    @RequestMapping("/insert")
    public void insert(HttpServletRequest request, PrintWriter printWriter){
        IrlProdIntPf irlProdIntPf = new IrlProdIntPf();
        irlProdIntPf.setEventType(request.getParameter("eventType"));
        irlProdIntPf.setIntClass(request.getParameter("intClass"));
        irlProdIntPf.setProdType(request.getParameter("prodType"));
        irlProdIntPf.setCompany(request.getParameter("company"));
        irlProdIntPf.setIntAmtId(request.getParameter("intAmtId"));
        irlProdIntPf.setIntCalcBal(request.getParameter("intCalcBal"));
        irlProdIntPf.setIntDaysType(request.getParameter("intDaysType"));
        irlProdIntPf.setIntStart(request.getParameter("intStart"));
        irlProdIntPf.setIntType(request.getParameter("intType"));
        irlProdIntPf.setRateAmtId(request.getParameter("rateAmtId"));
        irlProdIntPf.setRecalMethod(request.getParameter("recalMethod"));
        irlProdIntPf.setTaxType(request.getParameter("taxType"));
        irlProdIntPf.setIntApplType(request.getParameter("intApplType"));
        irlProdIntPf.setMaxRate(bigDecimal(request.getParameter("maxRate")));
        irlProdIntPf.setMinRate(bigDecimal(request.getParameter("minRate")));
        irlProdIntPf.setMonthBasis(request.getParameter("monthBasis"));
        irlProdIntPf.setIntRateInd(request.getParameter("intRateInd"));
        irlProdIntPf.setRollFreq(request.getParameter("rollFreq"));
        irlProdIntPf.setRollDay(bigDecimal(request.getParameter("rollDay")));
        irlProdIntPf.setGroupRuleType(request.getParameter("groupRuleType"));
        irlProdIntPf.setSplitId(request.getParameter("splitId"));
        irlProdIntPf.setSplitType(request.getParameter("splitType"));
        irlProdIntPf.setRuleId(request.getParameter("ruleId"));
        Map  result =new HashMap();
        if(request.getParameter("reqNum")!=null) {
            if(null!=irlProdIntPfDao.selectByPrimaryKey(irlProdIntPf,irlProdIntPf.getProdType(),irlProdIntPf.getEventType(),irlProdIntPf.getIntClass()))
            {
                result.put("retStatus", "F");
                result.put("retMsg", "主键冲突");
            }else {
                ProdToMap.irlProdIntPf(irlProdIntPf, "Insert", request.getParameter("reqNum"));
                result.put("retStatus", "S");
                result.put("retMsg", "添加成功");
            }
        }else{
            irlProdIntPfDao.insert(irlProdIntPf);
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
        IrlProdIntPf irlProdIntPf = new IrlProdIntPf();
        irlProdIntPf.setEventType(request.getParameter("eventType"));
        irlProdIntPf.setIntClass(request.getParameter("intClass"));
        irlProdIntPf.setProdType(request.getParameter("prodType"));
        irlProdIntPf.setCompany(request.getParameter("company"));
        irlProdIntPf.setIntAmtId(request.getParameter("intAmtId"));
        irlProdIntPf.setIntCalcBal(request.getParameter("intCalcBal"));
        irlProdIntPf.setIntDaysType(request.getParameter("intDaysType"));
        irlProdIntPf.setIntStart(request.getParameter("intStart"));
        irlProdIntPf.setIntType(request.getParameter("intType"));
        irlProdIntPf.setRateAmtId(request.getParameter("rateAmtId"));
        irlProdIntPf.setRecalMethod(request.getParameter("recalMethod"));
        irlProdIntPf.setTaxType(request.getParameter("taxType"));
        irlProdIntPf.setIntApplType(request.getParameter("intApplType"));
        irlProdIntPf.setMaxRate(bigDecimal(request.getParameter("maxRate")));
        irlProdIntPf.setMinRate(bigDecimal(request.getParameter("minRate")));
        irlProdIntPf.setMonthBasis(request.getParameter("monthBasis"));
        irlProdIntPf.setIntRateInd(request.getParameter("intRateInd"));
        irlProdIntPf.setRollFreq(request.getParameter("rollFreq"));
        irlProdIntPf.setRollDay(bigDecimal(request.getParameter("rollDay")));
        irlProdIntPf.setGroupRuleType(request.getParameter("groupRuleType"));
        irlProdIntPf.setSplitId(request.getParameter("splitId"));
        irlProdIntPf.setSplitType(request.getParameter("splitType"));
        irlProdIntPf.setRuleId(request.getParameter("ruleId"));
        Map  result =new HashMap();
        if(request.getParameter("reqNum")!=null) {
            ProdToMap.irlProdIntPf(irlProdIntPf, "Modify", request.getParameter("reqNum"));
        }else{
            irlProdIntPfDao.updateByPrimaryKey(irlProdIntPf);
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
        IrlProdIntPf irlProdIntPf = new IrlProdIntPf();
        if(request.getParameter("prodType") == "") {
            irlProdIntPf.setProdType(null);
        } else {
            irlProdIntPf.setProdType(request.getParameter("prodType"));
        }
        if(request.getParameter("eventType") == "") {
            irlProdIntPf.setEventType(null);
        } else {
            irlProdIntPf.setEventType(request.getParameter("eventType"));
        }
        if(request.getParameter("intClass") == "") {
            irlProdIntPf.setIntClass(null);
        } else {
            irlProdIntPf.setIntClass(request.getParameter("intClass"));
        }
        List<IrlProdIntPf> irlProdIntPfs = irlProdIntPfDao.selectBase(irlProdIntPf);
        out.put("data", irlProdIntPfs);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/getAll")
    public void  getAll(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<IrlProdIntPf> irlProdIntPf = irlProdIntPfDao.getAll(request.getParameter("prodType"));
        out.put("data", irlProdIntPf);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getAllByNewProdType")
    public void  getAllByNewProdType(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<IrlProdIntPf> irlProdIntPfList=irlProdIntPfDao.selectListByPrimaryKey(new IrlProdIntPf(), request.getParameter("prodType"), null, null);
        for(IrlProdIntPf irlProdIntPf:irlProdIntPfList)
        {
            irlProdIntPf.setProdType(request.getParameter("newProdType"));
            if(request.getParameter("reqNum")!=null) {
                ProdToMap.irlProdIntPf(irlProdIntPf,"Insert",request.getParameter("reqNum"));
            }else{
                irlProdIntPfDao.insert(irlProdIntPf);
            }
        }
        out.put("data", irlProdIntPfList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request,PrintWriter printWriter){
        IrlProdIntPf irlProdIntPf = new IrlProdIntPf();
        irlProdIntPf.setProdType(request.getParameter("prodType"));
        irlProdIntPf.setEventType(request.getParameter("eventType"));
        irlProdIntPf.setIntClass(request.getParameter("intClass"));
        Map result =new HashMap();
        if(request.getParameter("reqNum")!=null) {
            ProdToMap.irlProdIntPf(irlProdIntPf, "Delete", request.getParameter("reqNum"));
        }else{
            irlProdIntPfDao.deleteByPrimaryKey(irlProdIntPf);
        }
        result.put("retStatus", "S");
        result.put("retMsg", "删除成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    public BigDecimal bigDecimal(String str){
        BigDecimal decimal;
        if(str.equals("") || str == null){
            decimal = null;
            return decimal;
        }else{
            decimal = new BigDecimal(str);
            return decimal;
        }
    }

}
