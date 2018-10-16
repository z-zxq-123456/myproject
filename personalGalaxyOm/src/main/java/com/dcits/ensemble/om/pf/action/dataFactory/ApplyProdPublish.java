package com.dcits.ensemble.om.pf.action.dataFactory;

import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pf.bean.DbProdCheckList;
import com.dcits.ensemble.om.pf.bean.DbProdPublish;
import com.dcits.ensemble.om.pf.util.ApplyProdUtil;
import com.dcits.ensemble.om.pf.util.ObjectType;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaApplyCheckPublishDao;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaDifferenceCheckPublishDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ligan on 2017/3/21.
 */
@Controller
@RequestMapping("/applyProdPublish")
public class ApplyProdPublish {
    @Resource
    private ParaApplyCheckPublishDao paraApplyCheckPublishDao;
    @Resource
    private ParaDifferenceCheckPublishDao paraDifferenceCheckPublishDao;
    @RequestMapping("/list")
    public void listPublish(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<DbProdPublish> dbProdPublishList=new ArrayList<>();
        List<DbProdCheckList> applyList=  paraApplyCheckPublishDao.applyCheckProdList();
        for(DbProdCheckList dbProdCheckList:applyList){
            Map<String,String> getOperator = paraApplyCheckPublishDao.getOperator(dbProdCheckList.getReqNo());
            Map<String,String> getBulishOperator = paraApplyCheckPublishDao.getOperatorPublish(dbProdCheckList.getReqNo().toString());
                dbProdCheckList.setOperatorCurrentSystem(getOperator.get("OPERATOR_CURRENTSYSTEM"));
                dbProdCheckList.setCurrentSystemTime(getOperator.get("CURRENTSYSTEM_TIME"));
                dbProdCheckList.setCheckTime(getBulishOperator.get("CHECK_TIME"));
                dbProdCheckList.setOperatorCheck(getBulishOperator.get("OPERATOR_CHECK"));
            if(ApplyProdUtil.getProdTypePublishInfo(dbProdCheckList)!=null) {
                dbProdPublishList.addAll(ApplyProdUtil.getProdTypePublishInfo(dbProdCheckList));
            }
        }
        out.put("data",dbProdPublishList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/findProdType")
    public void findProdType(HttpServletRequest request, PrintWriter printWriter){
        DbProdPublish dbProdPublish=new DbProdPublish();
        Map<String,String> requestMap=new HashMap<>();
        String prodType=request.getParameter("prodType");
        String startTime=request.getParameter("startTime");
        String endTime=request.getParameter("endTime");
        requestMap.put("prodType",prodType);
        if(startTime!=null&&startTime.length()>0)
        requestMap.put("startTime",startTime);
        requestMap.put("endTime",endTime);
        JSONObject out = new JSONObject();
        List<DbProdPublish> dbProdPublishList=new ArrayList<>();
        List<DbProdCheckList> applyList=  paraApplyCheckPublishDao.byProdTypeList(requestMap);
        for(DbProdCheckList dbProdCheckList:applyList){
            Map<String,String> getOperator = paraApplyCheckPublishDao.getOperator(dbProdCheckList.getReqNo());
            Map<String,String> getBulishOperator = paraApplyCheckPublishDao.getOperatorPublish(dbProdCheckList.getReqNo());
                dbProdCheckList.setOperatorCurrentSystem(getOperator.get("OPERATOR_CURRENTSYSTEM"));
                dbProdCheckList.setCurrentSystemTime(getOperator.get("CURRENTSYSTEM_TIME"));
                dbProdCheckList.setCheckTime(getBulishOperator.get("CHECK_TIME"));
                dbProdCheckList.setOperatorCheck(getBulishOperator.get("OPERATOR_CHECK"));
            if(ApplyProdUtil.getProdTypePublishInfo(dbProdCheckList)!=null) {
                dbProdPublishList.addAll(ApplyProdUtil.getProdTypePublishInfo(dbProdCheckList));
            }
        }

        out.put("data",dbProdPublishList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

}
