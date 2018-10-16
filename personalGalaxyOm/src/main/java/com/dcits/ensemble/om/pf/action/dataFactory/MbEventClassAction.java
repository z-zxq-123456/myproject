package com.dcits.ensemble.om.pf.action.dataFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pf.dao.dataFactory.MbEventClassDao;
import com.dcits.ensemble.om.pf.dao.dataFactory.MbEventTypeDao;
import com.dcits.ensemble.om.pf.module.PkList;
import com.dcits.ensemble.om.pf.module.dataFactory.MbEventClass;
import com.dcits.ensemble.om.pf.module.dataFactory.MbEventType;
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
@RequestMapping("/event")
public class MbEventClassAction {
    @Resource
    private MbEventClassDao mbEventClassDao;

    @Resource
    private MbEventTypeDao mbEventTypeDao;

    @RequestMapping("/insert")
    public void insert(HttpServletRequest request, PrintWriter printWriter) {
        MbEventClass mbEventClass=new MbEventClass();
        mbEventClass.setEventClass(request.getParameter("eventClass"));
        mbEventClass.setEventClassDesc(request.getParameter("eventClassDesc"));
        mbEventClass.setEventClassLevel(request.getParameter("eventClassLevel"));
        mbEventClass.setParentEventClass(request.getParameter("parentEventClass"));
        Map  result =new HashMap();
        mbEventClassDao.insert(mbEventClass);
        result.put("retStatus", "S");
        result.put("retMsg", "添加成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/update")
    @Transactional
    public void  update(HttpServletRequest request, PrintWriter printWriter) {
        MbEventClass mbEventClass=new MbEventClass();
        MbEventClass mbEventClass1=new MbEventClass();
        mbEventClass1.setEventClass(request.getParameter("eventClass").split(",")[1]);
        mbEventClassDao.deleteByPrimaryKey(mbEventClass1);  //根据主键删除原始数据
        mbEventClass.setEventClass(request.getParameter("eventClass").split(",")[0]);
        mbEventClass.setEventClassDesc(request.getParameter("eventClassDesc"));
        mbEventClass.setEventClassLevel(request.getParameter("eventClassLevel"));
        mbEventClass.setParentEventClass(request.getParameter("parentEventClass"));
        Map  result =new HashMap();
        mbEventClassDao.insert(mbEventClass);  //完成包含逐渐在内的数据的修改
        result.put("retStatus", "S");
        result.put("retMsg", "修改成功");
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request,PrintWriter printWriter) {
        MbEventClass mbEventClass=new MbEventClass();
        mbEventClass.setEventClass(request.getParameter("eventClass"));
        String str="%" + request.getParameter("eventClass") +"%";
        mbEventClass.setParentEventClass(str);
         Map  result =new HashMap();
        List<MbEventType> mbEventTypeList=mbEventTypeDao.getByEventClass(request.getParameter("eventClass"));
        List<MbEventClass> mbEventClassList=mbEventClassDao.getByParentEventClass(mbEventClass);
        if (!mbEventTypeList.isEmpty() || !mbEventClassList.isEmpty()){
            result.put("retStatus","F");
            result.put("retMsg","该分类下存在事件，不能删除！");
        } else {
            mbEventClassDao.deleteByPrimaryKey(mbEventClass);
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
        List<MbEventClass> mbEventClassList= mbEventClassDao.getAll();
        out.put("data",mbEventClassList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getDesc")
    public void getDesc(PrintWriter printWriter){
        JSONObject out = new JSONObject();
        Map<String ,String > map=new HashMap<>();
        List<MbEventClass> mbEventClassList=mbEventClassDao.getAll();
        for(MbEventClass eventClass : mbEventClassList){
            map.put(eventClass.getEventClass() , eventClass.getEventClassDesc());
        }
        out.put("data", map);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/geteventKey")
    public void geteventKey(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out=new JSONObject();
        MbEventClass mbEventClass=mbEventClassDao.selectByPrimaryKey(new MbEventClass(), request.getParameter("eventClass"));
        if(mbEventClass != null){
            out.put("retStatus","F");
            out.put("retMsg","事件分类代码已存在！");
        } else {
            out.put("retStatus","S");
        }
        out.put("data",mbEventClass);
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


}

