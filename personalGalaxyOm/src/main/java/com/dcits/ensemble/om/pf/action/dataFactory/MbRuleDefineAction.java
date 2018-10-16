package com.dcits.ensemble.om.pf.action.dataFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pf.dao.dataFactory.MbAttrClassDao;
import com.dcits.ensemble.om.pf.dao.dataFactory.MbAttrTypeDao;
import com.dcits.ensemble.om.pf.dao.dataFactory.MbEventLinkDao;
import com.dcits.ensemble.om.pf.dao.dataFactory.MbLinkConditionDao;
import com.dcits.ensemble.om.pf.module.PkList;
import com.dcits.ensemble.om.pf.module.dataFactory.MbAttrType;
import com.dcits.ensemble.om.pf.module.dataFactory.MbEventLink;
import com.dcits.ensemble.om.pf.module.dataFactory.MbLinkCondition;
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
@RequestMapping("/ruleDefine")
public class MbRuleDefineAction {
    @Resource
    private MbLinkConditionDao mbLinkConditionDao;

    @Resource
    private MbEventLinkDao mbEventLinkDao;

    @RequestMapping("/insert")
    public void insert(HttpServletRequest request, PrintWriter printWriter) {
        MbLinkCondition mbLinkCondition=new MbLinkCondition();
        mbLinkCondition.setConditionId(request.getParameter("conditionId"));
        mbLinkCondition.setConditionDesc(request.getParameter("conditionDesc"));
        mbLinkCondition.setConditionRule(request.getParameter("conditionRule"));
        mbLinkCondition.setStatus(request.getParameter("status"));
        Map  result =new HashMap();
        mbLinkConditionDao.insert(mbLinkCondition);
        result.put("retStatus", "S");
        result.put("retMsg", "添加成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/update")
    @Transactional
    public void  update(HttpServletRequest request, PrintWriter printWriter) {
        MbLinkCondition mbLinkCondition = new MbLinkCondition();
        mbLinkCondition.setConditionId(request.getParameter("conditionId"));
        mbLinkCondition.setConditionDesc(request.getParameter("conditionDesc"));
        mbLinkCondition.setConditionRule(request.getParameter("conditionRule"));
        mbLinkCondition.setStatus(request.getParameter("status"));
        Map  result =new HashMap();
        mbLinkConditionDao.updateByPrimaryKey(mbLinkCondition);
        result.put("retStatus", "S");
        result.put("retMsg", "修改成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/delete")
    public void delete(HttpServletRequest request,PrintWriter printWriter) {
        MbLinkCondition mbLinkCondition=new MbLinkCondition();
        mbLinkCondition.setConditionId(request.getParameter("conditionId"));
         Map  result =new HashMap();
        List<MbEventLink> mbEventLinkList=mbEventLinkDao.getByLinkCondition(request.getParameter("conditionId"));
        if (!mbEventLinkList.isEmpty()){
            result.put("retStatus","F");
            result.put("retMsg","该规则已被引用，不能删除！");
        } else {
            mbLinkConditionDao.deleteByPrimaryKey(mbLinkCondition);
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
        List<MbLinkCondition> mbLinkConditions= mbLinkConditionDao.getAll();
        out.put("data",mbLinkConditions);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getOne")
    public void getOne(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out=new JSONObject();
        MbLinkCondition mbLinkCondition=mbLinkConditionDao.selectByPrimaryKey(new MbLinkCondition(),request.getParameter("conditionId"));
        if(mbLinkCondition != null){
            out.put("retStatus","F");
            out.put("retMsg","规则代码已存在！");
        } else {
            out.put("retStatus","S");
        }
        out.put("data",mbLinkCondition);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

}

