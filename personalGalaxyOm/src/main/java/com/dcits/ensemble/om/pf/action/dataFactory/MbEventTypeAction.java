package com.dcits.ensemble.om.pf.action.dataFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pf.dao.dataFactory.*;
import com.dcits.ensemble.om.pf.module.PkList;
import com.dcits.ensemble.om.pf.module.dataFactory.MbEventType;
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
@RequestMapping("/eventType")
public class MbEventTypeAction {
    @Resource
    private MbEventTypeDao mbEventTypeDao;
    @Resource
    private MbEventClassDao mbEventClassDao;
    @Resource
    private MbEventAttrDao mbEventAttrDao;

    @Resource
    private MbAttrTypeDao mbAttrTypeDao;

    @Resource
    private MbPartTypeDao mbPartTypeDao;

    @RequestMapping("/insert")
    public void insert(HttpServletRequest request, PrintWriter printWriter){
        MbEventType mbEventType=new MbEventType();
        mbEventType.setEventType(request.getParameter("eventType"));
        mbEventType.setEventDesc(request.getParameter("eventDesc"));
        mbEventType.setEventClass(request.getParameter("eventClass"));
        mbEventType.setProcessMethod(request.getParameter("processMethod"));
        mbEventType.setStatus(request.getParameter("status"));
        mbEventType.setIsStandard(request.getParameter("isStandard"));
        Map  result =new HashMap();
        ProdToMap.eventType(mbEventType, "Insert",request.getParameter("reqNum"));
       // mbEventTypeDao.insert(mbEventType);
        result.put("retStatus", "S");
        result.put("retMsg", "添加成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/update")
    @Transactional
    public void  update(HttpServletRequest request, PrintWriter printWriter){
        MbEventType mbEventType=new MbEventType();
        mbEventType.setEventType(request.getParameter("eventType"));
        MbEventType mbEventType1=mbEventTypeDao.selectByPrimaryKey(mbEventType,mbEventType.getEventType());
        mbEventType.setIsStandard(mbEventType1.getIsStandard());
        mbEventType.setEventDesc(request.getParameter("eventDesc"));
        mbEventType.setEventClass(request.getParameter("eventClass"));
        mbEventType.setProcessMethod(request.getParameter("processMethod"));
        mbEventType.setStatus(request.getParameter("status"));
        Map  result =new HashMap();
        ProdToMap.eventType(mbEventType, "Modify", request.getParameter("reqNum"));
       // mbEventTypeDao.updateByPrimaryKey(mbEventType);
        result.put("retStatus", "S");
        result.put("retMsg", "修改成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request,PrintWriter printWriter){
        MbEventType mbEventType=new MbEventType();
        mbEventType.setEventType(request.getParameter("eventType"));
         Map  result =new HashMap();
        ProdToMap.eventType(mbEventType, "Delete", request.getParameter("reqNum"));
//        mbEventTypeDao.deleteByPrimaryKey(mbEventType);
        result.put("retStatus", "S");
        result.put("retMsg", "删除成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getAll")
    public void  getAll( PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<MbEventType> mbEventTypes= mbEventTypeDao.getAll();
        out.put("data",mbEventTypes);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getAllContrast")
    public void  getAllContrast( PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<MbEventType> mbEventTypes= mbEventTypeDao.getAllContrast();
        out.put("data",mbEventTypes);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getAllN")
    public void  getAllN( PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<MbEventType> mbEventTypes= mbEventTypeDao.getAllN();
        out.put("data",mbEventTypes);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/selectByPrimaryKey")
    public void  selectByPrimaryKey(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        MbEventType mbEventType= mbEventTypeDao.selectByPrimaryKey(new MbEventType(), request.getParameter("eventType"));
        out.put("data",mbEventType);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getOne")
    public void  getOne(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        MbEventType mbEventType= mbEventTypeDao.selectByPrimaryKey(new MbEventType(), request.getParameter("eventType"));
        if(mbEventType==null){
            out.put("retStatus","F");
            out.put("retMsg","事件不存在！");
        } else {
            out.put("retStatus","S");
        }
        out.put("data",mbEventType);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getByEventClass")
    public void  getByEventClass( PrintWriter printWriter,String eventClass){
        JSONObject out = new JSONObject();
        List<MbEventType> mbEventTypes= mbEventTypeDao.getByEventClass(eventClass);
        out.put("data",mbEventTypes);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/forCheck")
    public void getForCheck(HttpServletRequest request,PrintWriter printWriter){
        JSONObject out = new JSONObject();
        String evevtClass= mbEventTypeDao.getForCheck(request.getParameter("eventType"));
        out.put("data", evevtClass);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getForPkList")
    public void getForPkList(PrintWriter printWriter){
        List<PkList> pklist=mbEventClassDao.getForPkList();
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getEventTypeKey")
    public void getEventTypeKey(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out=new JSONObject();
        MbEventType mbEventType= mbEventTypeDao.selectByPrimaryKey(new MbEventType(), request.getParameter("eventType"));
        if(mbEventType != null){
            out.put("retStatus","F");
            out.put("retMsg","事件代码已存在！");
        } else {
            out.put("retStatus", "S");
        }
        out.put("data", mbEventType);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getIsStandardAllY")
    public void  getIsStandardAllY( PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<MbEventType> mbAttrTypes= mbEventTypeDao.getIsStandardAllY();
        out.put("data",mbAttrTypes);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
 @RequestMapping("/getByBmodule")
    public void  getByBmodule(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<MbEventType> mbEventTypeList= mbEventTypeDao.getByBmodule(request.getParameter("prodType"));
        out.put("data",mbEventTypeList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
}

