package com.dcits.ensemble.om.pf.action.dataFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pf.dao.dataFactory.IrlFeeMappingPfDao;
import com.dcits.ensemble.om.pf.module.dataFactory.IrlFeeMappingPf;
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
@RequestMapping("/irlFeeMapping")
public class MbIrlFeeMappingAction {
    @Resource
    IrlFeeMappingPfDao irlFeeMappingPfDao;

    //SOURCE_TYPE,DOC_TYPE,OLD_STATUS,NEW_STATUS,IS_RULE,COMPANY,SERVICE_ID

    @RequestMapping("/insert")
    public void insert(HttpServletRequest request, PrintWriter printWriter){
        IrlFeeMappingPf irlFeeMappingPf = new IrlFeeMappingPf();
        irlFeeMappingPf.setProdTypeRule(request.getParameter("prodTypeRule"));
        irlFeeMappingPf.setAreaRule(request.getParameter("areaRule"));
        irlFeeMappingPf.setBranchRule(request.getParameter("branchRule"));
        irlFeeMappingPf.setCategoryTypeRule(request.getParameter("categoryTypeRule"));
        irlFeeMappingPf.setCcyRule(request.getParameter("ccyRule"));
        irlFeeMappingPf.setClientTypeRule(request.getParameter("clientTypeRule"));
        irlFeeMappingPf.setCompanyRule(request.getParameter("companyRule"));
        irlFeeMappingPf.setDocTypeRule(request.getParameter("docTypeRule"));
        irlFeeMappingPf.setEventTypeRule(request.getParameter("eventTypeRule"));
        irlFeeMappingPf.setIrlSeqNo(request.getParameter("irlSeqNo"));
        irlFeeMappingPf.setFeeType(request.getParameter("feeType"));
        irlFeeMappingPf.setIsLocalRule(request.getParameter("isLocalRule"));
        irlFeeMappingPf.setIsRule(request.getParameter("isRule"));
        irlFeeMappingPf.setNewStatusRule(request.getParameter("newStatusRule"));
        irlFeeMappingPf.setOldStatusRule(request.getParameter("oldStatusRule"));
        irlFeeMappingPf.setUrgentFlagRule(request.getParameter("urgentFlagRule"));
        irlFeeMappingPf.setSourceTypeRule(request.getParameter("sourceTypeRule"));
        irlFeeMappingPf.setServiceIdRule(request.getParameter("serviceIdRule"));
        irlFeeMappingPf.setTranTypeRule(request.getParameter("tranTypeRule"));
        irlFeeMappingPf.setProdGroupRule(request.getParameter("prodGroupRule"));
        Map  result =new HashMap();
        if(null!=irlFeeMappingPfDao.selectByPrimaryKey(irlFeeMappingPf,irlFeeMappingPf.getIrlSeqNo()))
        {
            result.put("retStatus", "F");
            result.put("retMsg", "主键冲突");
        }else{
            ProdToMap.irlFeeMappingPf(irlFeeMappingPf, "Insert", request.getParameter("reqNum"));
            // irlFeeMappingPfDao.insert(irlFeeMappingPf);
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
        IrlFeeMappingPf irlFeeMappingPf = new IrlFeeMappingPf();
        irlFeeMappingPf.setProdTypeRule(request.getParameter("prodTypeRule"));
        irlFeeMappingPf.setAreaRule(request.getParameter("areaRule"));
        irlFeeMappingPf.setBranchRule(request.getParameter("branchRule"));
        irlFeeMappingPf.setCategoryTypeRule(request.getParameter("categoryTypeRule"));
        irlFeeMappingPf.setCcyRule(request.getParameter("ccyRule"));
        irlFeeMappingPf.setClientTypeRule(request.getParameter("clientTypeRule"));
        irlFeeMappingPf.setCompanyRule(request.getParameter("companyRule"));
        irlFeeMappingPf.setDocTypeRule(request.getParameter("docTypeRule"));
        irlFeeMappingPf.setEventTypeRule(request.getParameter("eventTypeRule"));
        irlFeeMappingPf.setIrlSeqNo(request.getParameter("irlSeqNo"));
        irlFeeMappingPf.setFeeType(request.getParameter("feeType"));
        irlFeeMappingPf.setIsLocalRule(request.getParameter("isLocalRule"));
        irlFeeMappingPf.setIsRule(request.getParameter("isRule"));
        irlFeeMappingPf.setNewStatusRule(request.getParameter("newStatusRule"));
        irlFeeMappingPf.setOldStatusRule(request.getParameter("oldStatusRule"));
        irlFeeMappingPf.setUrgentFlagRule(request.getParameter("urgentFlagRule"));
        irlFeeMappingPf.setSourceTypeRule(request.getParameter("sourceTypeRule"));
        irlFeeMappingPf.setServiceIdRule(request.getParameter("serviceIdRule"));
        irlFeeMappingPf.setTranTypeRule(request.getParameter("tranTypeRule"));
        irlFeeMappingPf.setProdGroupRule(request.getParameter("prodGroupRule"));
        Map  result =new HashMap();
        ProdToMap.irlFeeMappingPf(irlFeeMappingPf, "Modify", request.getParameter("reqNum"));
     //   irlFeeMappingPfDao.updateByPrimaryKey(irlFeeMappingPf);
        result.put("retStatus", "S");
        result.put("retMsg", "修改成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getAll")
    public void  getAll(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<IrlFeeMappingPf> irlFeeMappingPf = irlFeeMappingPfDao.getAll(request.getParameter("prodType"));
        out.put("data", irlFeeMappingPf);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getAllByNewProdType")
    public void  getAllByNewProdType(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<IrlFeeMappingPf> irlFeeMappingPfList = irlFeeMappingPfDao.getAll(request.getParameter("prodType"));
        String seqNo = irlFeeMappingPfDao.getMax() == null?"0":irlFeeMappingPfDao.getMax();
        for(int i=0;i<irlFeeMappingPfList.size();i++){
            seqNo = Integer.parseInt(seqNo) + 1+"";
            irlFeeMappingPfList.get(i).setIrlSeqNo(seqNo+"");
            irlFeeMappingPfList.get(i).setProdTypeRule(request.getParameter("newProdType"));
            ProdToMap.irlFeeMappingPf(irlFeeMappingPfList.get(i), "Insert", request.getParameter("reqNum"));
        }
        out.put("data", irlFeeMappingPfList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/delete")
    public void delete(HttpServletRequest request,PrintWriter printWriter){
        IrlFeeMappingPf irlFeeMappingPf = new IrlFeeMappingPf();
        irlFeeMappingPf.setIrlSeqNo(request.getParameter("irlSeqNo"));
        Map result =new HashMap();
        ProdToMap.irlFeeMappingPf(irlFeeMappingPf, "Delete", request.getParameter("reqNum"));
     //   irlFeeMappingPfDao.deleteByPrimaryKey(irlFeeMappingPf);
        result.put("retStatus", "S");
        result.put("retMsg", "删除成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
}
