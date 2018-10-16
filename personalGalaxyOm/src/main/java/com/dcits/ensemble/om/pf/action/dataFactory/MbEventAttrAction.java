package com.dcits.ensemble.om.pf.action.dataFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pf.dao.dataFactory.*;
import com.dcits.ensemble.om.pf.module.dataFactory.*;
import com.dcits.ensemble.om.pf.util.ProdDefineBeanToJson;
import com.dcits.ensemble.om.pf.util.action.ActionResultWrite;
import com.dcits.ensemble.om.pf.zTreeModel.AttrModel;
import com.dcits.ensemble.om.pf.zTreeModel.PartModel;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.*;
/**
 * Created by wujuan on 2015/10/15.
 */
@Controller
@RequestMapping("/eventAttr")
public class MbEventAttrAction {
    @Resource
    private MbEventTypeDao mbEventTypeDao;

    @Resource
    private MbEventAttrDao mbEventAttrDao;

    @Resource
    private MbEventPartDao mbEventPartDao;

    @Resource
    private MbAttrTypeDao mbAttrTypeDao;

    @Resource
    private MbPartTypeDao mbPartTypeDao;

    @Resource
    private MbPartAttrDao mbPartAttrDao;

    @Resource
    private MbAttrValueDao mbAttrValueDao;

    @Resource
    private MbEventClassDao mbEventClassDao;


    @RequestMapping("/save")
    @Transactional
    public void save(PrintWriter printWriter, @RequestBody String request)   {
        List<JSONObject> jsonList = JSON.parseArray(request, JSONObject.class);
        Map result = new HashMap();
        try{
            eventAttrSaveOrCopy(jsonList, "save");
            result.put("retStatus", "S");
            result.put("retMsg", "保存成功");
        }catch(Exception e) {
            result.put("retStatus", "F");
            result.put("retMsg", "保存失败");
            throw e;
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/copy")
    @Transactional
    public void copy(PrintWriter printWriter, @RequestBody String request)   {
        List<JSONObject> jsonList = JSON.parseArray(request, JSONObject.class);
        Map result = new HashMap();
        try{
            eventAttrSaveOrCopy(jsonList, "copy");
            result.put("retStatus", "S");
            result.put("retMsg", "复制成功");
        }catch(Exception e) {
            result.put("retStatus", "F");
            result.put("retMsg", "复制失败");
            throw e;
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getByPartType1")
    public void getByPartType1(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out=new JSONObject();
        MbEventPart mbEventPart=new MbEventPart();
        mbEventPart.setEventType(request.getParameter("eventType"));
        mbEventPart.setAssembleId(request.getParameter("partType"));
        List<MbEventPart> mbEventPartList =mbEventPartDao.getByPartType1(mbEventPart);
        List<AttrModel> attrModelList=new ArrayList<>();
        for(int i=0;i<mbEventPartList.size();i++){
            AttrModel attrModel=new AttrModel();
            MbEventPart mbEventPart1=mbEventPartList.get(i);
            attrModel.setAttrKey(mbEventPart1.getAttrKey());
            attrModel.setAttrValue(mbEventPart1.getAttrValue());
            attrModel.setName((mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(), mbEventPart1.getAttrKey())).getAttrDesc());
            attrModel.setValueMethod((mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(), mbEventPart1.getAttrKey())).getValueMethod());
            attrModelList.add(attrModel);
        }
        out.put("data", attrModelList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getAttr")
    public void getAttr(HttpServletRequest request, PrintWriter printWriter) {
        JSONArray outArray = new JSONArray();
        MbEventAttr mbEventAttr = new MbEventAttr();
        mbEventAttr.setEventType(request.getParameter("eventType"));
        List<MbEventAttr> mbEventAttrs = mbEventAttrDao.selectByEventType(mbEventAttr);
        for (int i = 0; i < mbEventAttrs.size(); i++) {
            JSONObject eventAttr = new JSONObject();
            eventAttr.put("eventType", mbEventAttrs.get(i).getEventType());
            eventAttr.put("assembleType", mbEventAttrs.get(i).getAssembleType());
            eventAttr.put("assembleId", mbEventAttrs.get(i).getAssembleId());
            eventAttr.put("attrValue", mbEventAttrs.get(i).getAttrValue());
            eventAttr.put("seqNo", mbEventAttrs.get(i).getSeqNo());
            if ("ATTR".equals(mbEventAttrs.get(i).getAssembleType())) {
                MbAttrType mbAttrType = mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(), mbEventAttrs.get(i).getAssembleId());
                if (null!=mbAttrType && "A".equals(mbAttrType.getStatus())) {
                    eventAttr.put("attrDesc", mbAttrType.getAttrDesc());
                    eventAttr.put("attrClass", mbAttrType.getAttrClass());
                    eventAttr.put("valueMethod", mbAttrType.getValueMethod());
                    eventAttr.put("setValueFlag", mbAttrType.getSetValueFlag());
                }
            } else if ("PART".equals(mbEventAttrs.get(i).getAssembleType())) {
                JSONArray result=new JSONArray();
                MbPartType mbPartType = mbPartTypeDao.selectByPrimaryKey(new MbPartType(), mbEventAttrs.get(i).getAssembleId());
                MbEventPart mbEventPart=new MbEventPart();
                mbEventPart.setEventType(request.getParameter("eventType"));
                mbEventPart.setAssembleId(mbEventAttrs.get(i).getAssembleId());
                List<MbEventPart> mbEventPartList =mbEventPartDao.getByPartType1(mbEventPart);
                for(int j=0;j<mbEventPartList.size();j++){
                    MbAttrType mbAttrType = mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(),mbEventPartList.get(j).getAttrKey());
                    JSONObject data=new JSONObject();
                    data.put("attrKey",mbEventPartList.get(j).getAttrKey());
                    data.put("attrValue", mbEventPartList.get(j).getAttrValue());
                    data.put("valueMethod", mbAttrType.getValueMethod());
                    data.put("attrDesc",mbAttrType.getAttrDesc());
                    data.put("setValueFlag",mbAttrType.getSetValueFlag());
                    result.add(j, data);
                }
                if ("A".equals(mbPartType.getStatus())) {
                    eventAttr.put("partClass", mbPartType.getPartClass());
                    eventAttr.put("partDesc", mbPartType.getPartDesc());
                    eventAttr.put("data",result);
                }
            }
            outArray.add(eventAttr);
        }
        printWriter.print(outArray.toJSONString());
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getByEvent")
    public void getByEvent(HttpServletRequest request, PrintWriter printWriter) {
        JSONArray outArray = new JSONArray();
        JSONArray collect = new JSONArray();
        JSONObject eventAttr = new JSONObject();
        MbEventType mbEventType = mbEventTypeDao.selectByPrimaryKey(new MbEventType(), request.getParameter("eventType"));
        MbEventAttr mbEventAttr = new MbEventAttr();
        mbEventAttr.setEventType(request.getParameter("eventType"));
        List<MbEventAttr> mbEventAttrs = mbEventAttrDao.selectByEventType(mbEventAttr);

        for (int j = 0; j < mbEventAttrs.size(); j++) {
            JSONObject eventPart = new JSONObject();
            eventPart.put("eventType", mbEventAttrs.get(j).getEventType());
            eventPart.put("assembleType", mbEventAttrs.get(j).getAssembleType());
            eventPart.put("assembleId", mbEventAttrs.get(j).getAssembleId());
            eventPart.put("attrValue", mbEventAttrs.get(j).getAttrValue());
            eventPart.put("assembleRule",mbEventAttrs.get(j).getAssembleRule());
            eventPart.put("seqNo", mbEventAttrs.get(j).getSeqNo());
            if ("ATTR".equals(mbEventAttrs.get(j).getAssembleType())) {
                MbAttrType mbAttrType = mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(), mbEventAttrs.get(j).getAssembleId());
                if (null!=mbAttrType && "A".equals(mbAttrType.getStatus())) {
                    eventPart.put("attrDesc", mbAttrType.getAttrDesc());
                    eventPart.put("attrClass", mbAttrType.getAttrClass());
                    eventPart.put("valueMethod", mbAttrType.getValueMethod());
                }
            } else if ("PART".equals(mbEventAttrs.get(j).getAssembleType())) {
                JSONArray result=new JSONArray();
                MbPartType mbPartType = mbPartTypeDao.selectByPrimaryKey(new MbPartType(), mbEventAttrs.get(j).getAssembleId());
                MbEventPart mbEventPart=new MbEventPart();
                mbEventPart.setEventType(request.getParameter("eventType"));
                mbEventPart.setAssembleId(mbEventAttrs.get(j).getAssembleId());
                List<MbEventPart> mbEventPartList =mbEventPartDao.getByPartType1(mbEventPart);
                for(int k=0;k<mbEventPartList.size();k++){
                    JSONObject data=new JSONObject();
                    data.put("attrKey",mbEventPartList.get(k).getAttrKey());
                    data.put("attrValue", mbEventPartList.get(k).getAttrValue());
                    data.put("valueMethod", mbAttrTypeDao.getValueMethod(mbEventPartList.get(k).getAttrKey()));
                    data.put("attrDesc",mbAttrTypeDao.getAttrDesc(mbEventPartList.get(k).getAttrKey()));
                    result.add(k, data);
                }
                if ("A".equals(mbPartType.getStatus())) {
                    eventPart.put("partClass", mbPartType.getPartClass());
                    eventPart.put("partDesc", mbPartType.getPartDesc());
                    eventPart.put("data",result);
                }
            }
            collect.add(j,eventPart);
        }
        if ("A".equals(mbEventType.getStatus())) {
            eventAttr.put("eventClass", mbEventType.getEventClass());
            eventAttr.put("eventDesc", mbEventType.getEventDesc());
            eventAttr.put("prodData",collect);
        }
            outArray.add(eventAttr);
            System.out.println("--prodDefineevent--getDefine-----" + outArray.toString());
            printWriter.print(outArray.toJSONString());
            printWriter.flush();
            printWriter.close();
}

    @RequestMapping("/getAttrAll")
    public void getAttrAll(HttpServletRequest request, PrintWriter printWriter) {
        JSONArray outArray = new JSONArray();
        MbEventAttr mbEventAttr = new MbEventAttr();
        mbEventAttr.setEventType(request.getParameter("eventType"));
        List<MbEventAttr> mbEventAttrs = mbEventAttrDao.selectByEventType(mbEventAttr);
        for (int i = 0; i < mbEventAttrs.size(); i++) {
            if ("ATTR".equals(mbEventAttrs.get(i).getAssembleType())) {
                JSONObject eventAttr = new JSONObject();
                eventAttr.put("eventType", mbEventAttrs.get(i).getEventType());
                eventAttr.put("assembleType", "属性");
                eventAttr.put("assembleId", mbEventAttrs.get(i).getAssembleId());
                eventAttr.put("attrValue", mbEventAttrs.get(i).getAttrValue());
                eventAttr.put("seqNo", mbEventAttrs.get(i).getSeqNo());
                MbAttrType mbAttrType = mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(), mbEventAttrs.get(i).getAssembleId());
                if ("A".equals(mbAttrType.getStatus())) {
                    eventAttr.put("attrDesc", mbAttrType.getAttrDesc());
                    eventAttr.put("attrClass", mbAttrType.getAttrClass());
                    eventAttr.put("valueMethod", mbAttrType.getValueMethod());
                }
                MbAttrValue MbAttrValue = mbAttrValueDao.selectByPrimaryKey(new MbAttrValue(), mbEventAttrs.get(i).getAssembleId(), mbEventAttrs.get(i).getAttrValue());
                if(MbAttrValue!=null){
                    eventAttr.put("attrValue", mbEventAttrs.get(i).getAttrValue() + "-" + MbAttrValue.getValueDesc());
                } else {
                    if("Y".equals(mbEventAttrs.get(i).getAttrValue())){
                        eventAttr.put("attrValue", mbEventAttrs.get(i).getAttrValue() + "-是");
                    } else if("N".equals(mbEventAttrs.get(i).getAttrValue())){
                        eventAttr.put("attrValue", mbEventAttrs.get(i).getAttrValue() + "-否");
                    } else {
                        eventAttr.put("attrValue", mbEventAttrs.get(i).getAttrValue());
                    }
                }
                outArray.add(eventAttr);
            } else if ("PART".equals(mbEventAttrs.get(i).getAssembleType())) {
                MbPartAttr mbPartAttr = new MbPartAttr();
                mbPartAttr.setPartType(mbEventAttrs.get(i).getAssembleId());
                MbPartType mbPartType = mbPartTypeDao.selectByPrimaryKey(new MbPartType(), mbEventAttrs.get(i).getAssembleId());
                List<MbPartAttr> mbPartAttrs = mbPartAttrDao.getByPartType(mbPartAttr);
                for (int j = 0; j < mbPartAttrs.size(); j++) {
                    JSONObject eventAttrP = new JSONObject();
                    eventAttrP.put("eventType", mbEventAttrs.get(i).getEventType());
                    eventAttrP.put("assembleType", "部件-"+mbPartType.getPartDesc());
                    eventAttrP.put("assembleId", mbPartAttrs.get(j).getAttrKey());
                    eventAttrP.put("attrValue", mbPartAttrs.get(j).getAttrValue());
                    eventAttrP.put("seqNo", mbEventAttrs.get(i).getSeqNo());
                    MbAttrType mbAttrTypeP = mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(), mbPartAttrs.get(j).getAttrKey());
                    if ("A".equals(mbAttrTypeP.getStatus())) {
                        eventAttrP.put("attrDesc", mbAttrTypeP.getAttrDesc());
                        eventAttrP.put("attrClass", mbAttrTypeP.getAttrClass());
                        eventAttrP.put("valueMethod", mbAttrTypeP.getValueMethod());
                    }
                    MbAttrValue MbAttrValue = mbAttrValueDao.selectByPrimaryKey(new MbAttrValue(), mbPartAttrs.get(j).getAttrKey(), mbPartAttrs.get(j).getAttrValue());
                    if(MbAttrValue!=null){
                        eventAttrP.put("attrValue", mbPartAttrs.get(j).getAttrValue() + "-" + MbAttrValue.getValueDesc());
                    }else {
                        if("Y".equals(mbPartAttrs.get(j).getAttrValue())){
                            eventAttrP.put("attrValue", mbPartAttrs.get(j).getAttrValue() + "-是");
                        } else if("N".equals(mbPartAttrs.get(j).getAttrValue())){
                            eventAttrP.put("attrValue", mbPartAttrs.get(j).getAttrValue() + "-否");
                        } else {
                            eventAttrP.put("attrValue", mbPartAttrs.get(j).getAttrValue());
                        }
                    }
                    outArray.add(eventAttrP);
                }
            }
        }
        System.out.println("---eventAttr----getAttrAll---"+outArray.toString());
        printWriter.print(outArray.toJSONString());
        printWriter.flush();
        printWriter.close();
    }


    @RequestMapping("/getzTreeList")
    public void getzTreeList(HttpServletRequest request, PrintWriter printWriter){

        MbEventClass mbEventClass;
        List<MbEventClass> mbEventClassList;

        if(request.getParameter("Bmodule")== null)
        {
            mbEventClassList=mbEventClassDao.getTree();
        }
        else {
            mbEventClassList=mbEventClassDao.getTreeByBmodule(request.getParameter("Bmodule"));
        }
        List<PartModel> partModelList=new ArrayList<>();
        for(int i=0;i<mbEventClassList.size();i++){
            mbEventClass=mbEventClassList.get(i);
            PartModel partModel = new PartModel();
            partModel.setId(mbEventClass.getEventClass());
            partModel.setPId(mbEventClass.getParentEventClass());
            partModel.setName(mbEventClass.getEventClassDesc());
            partModelList.add(partModel);
            MbEventType mbEventType=new MbEventType();
            mbEventType.setEventClass(mbEventClass.getEventClass());
            List<MbEventType> mbEventTypeList=mbEventTypeDao.getEventTypeByClass(mbEventType);
            for(int j=0;j<mbEventTypeList.size();j++){
                mbEventType=mbEventTypeList.get(j);
                PartModel partModel1=new PartModel();
                partModel1.setId(mbEventType.getEventType());
                partModel1.setPId(mbEventType.getEventClass());
                partModel1.setName(mbEventType.getEventDesc());
                partModelList.add(partModel1);
            }

        }
        System.out.println("---eventAttr----getzTreeList---"+partModelList.toString());
        printWriter.print(JSON.toJSONString(partModelList));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/selectParent")
    public void selectParent(HttpServletRequest request,PrintWriter printWriter){
        try{
            String eventType=request.getParameter("eventType");
            String assembleType=request.getParameter("assembleType");
            String assembleId=request.getParameter("assembleId");
            MbEventAttr mbEventAttr=new MbEventAttr();
            List<MbEventAttr> info=new ArrayList<>();
            if(eventType.isEmpty()){
                ActionResultWrite.setReadResult(printWriter, new ArrayList<>());
                return;
            }else if(assembleType.isEmpty() && assembleId.isEmpty()){
                mbEventAttr.setEventType(eventType);
                mbEventAttr.setAssembleType(null);
                mbEventAttr.setAssembleId(null);
                info=mbEventAttrDao.selectByCondition(mbEventAttr);
            }else if( assembleId.isEmpty() && !(assembleType.isEmpty()) ){
                mbEventAttr.setEventType(eventType);
                mbEventAttr.setAssembleType(assembleType);
                mbEventAttr.setAssembleId(null);
                info=mbEventAttrDao.selectByCondition(mbEventAttr);
            }else if(!(assembleId.isEmpty()) && !(assembleType.isEmpty())) {
                mbEventAttr.setEventType(eventType);
                mbEventAttr.setAssembleType(assembleType);
                mbEventAttr.setAssembleId(assembleId);
                info=mbEventAttrDao.selectByCondition(mbEventAttr);
            }
            for(MbEventAttr eventAttr : info ){    //非指标汉化
                if("ATTR".equals(eventAttr.getAssembleType())){
                    String key=eventAttr.getAssembleId();
                    String value=eventAttr.getAttrValue();
                    String attrDesc=mbAttrTypeDao.getAttrDesc(key);
                    eventAttr.setAssembleId(attrDesc);
                    String valueMethod=mbAttrTypeDao.getValueMethod(key);
                    if(null != valueMethod ){
                        if("FD".equals(valueMethod)){
                            eventAttr.setAttrValue(value);  //null(null) ->""
                        }else {
                            MbAttrValue attrValue=new MbAttrValue();
                            attrValue.setAttrKey(key);
                            attrValue.setAttrValue(value);
                            String valueDesc=mbAttrValueDao.getAttrValueDesc(attrValue);
                            if(valueDesc == null ){       //null(a) ->DESC(null);
                                eventAttr.setAttrValue(attrDesc+" ("+value+")");
                            }else{                        //desc(null)
                            eventAttr.setAttrValue(valueDesc+" ("+value+")");
                            }
                        }
                    }
                }
            }
            ActionResultWrite.setReadResult(printWriter, info);
        }catch(Exception e){
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
            throw e;
        }
    }
    @RequestMapping("/selectChildren")
    public void selectChildren(HttpServletRequest request,PrintWriter printWriter){
        MbEventAttr eventAttr=new MbEventAttr();
        List<MbEventAttr> infoList = new ArrayList<>();
        try{
            MbPartAttr partAttr = new MbPartAttr();
            partAttr.setPartType(request.getParameter("assembleId"));
            List<MbPartAttr> list=mbPartAttrDao.getByPartType(partAttr);
            for(MbPartAttr mbPartAttr : list){
                MbEventAttr mbEventAttr1=new MbEventAttr();
                String attrDesc=mbAttrTypeDao.getAttrDesc(mbPartAttr.getAttrKey());
                mbEventAttr1.setAssembleId(attrDesc);
                mbEventAttr1.setAssembleType(mbPartTypeDao.getPartTypeDesc(mbPartAttr.getPartType()));

                MbAttrValue attrValue=new MbAttrValue();
                attrValue.setAttrKey(mbPartAttr.getAttrKey());
                attrValue.setAttrValue(mbPartAttr.getAttrValue());
                String valueMethod=mbAttrTypeDao.getValueMethod(attrValue.getAttrKey());
                String valueDesc=mbAttrValueDao.getAttrValueDesc(attrValue);
                if(null != valueMethod ){
                    if("FD".equals(valueMethod)){
                        mbEventAttr1.setAttrValue(attrValue.getAttrValue());
                    }else if( mbPartAttr.getAttrValue() == null){
                        mbEventAttr1.setAttrValue("");    //null(null) -> ""
                    }else if(valueDesc == null){
                        mbEventAttr1.setAttrValue(attrDesc+" ("+mbPartAttr.getAttrValue()+")");  // null(a) ->DESC(a)
                    }else{
                        mbEventAttr1.setAttrValue(valueDesc+" ("+mbPartAttr.getAttrValue()+")");   //desc(a)
                    }
                }
                infoList.add(mbEventAttr1);
            }
            ActionResultWrite.setReadResult(printWriter,infoList);
        }catch (Exception e){
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
            throw e;
        }
    }

    @RequestMapping("/getByEventType")
    public void  getByEventType(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<MbEventAttr> mbEventAttrList= mbEventAttrDao.getByEventType1(request.getParameter("eventType"));
        out.put("data",mbEventAttrList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getByEventTypeContrast")
    public void  getByEventTypeContrast(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<MbEventAttr> mbEventAttrList= mbEventAttrDao.getByEventType1Contrast(request.getParameter("eventType"));
        out.put("data",mbEventAttrList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/selectChildrenPart")
    public void selectChildrenPart(HttpServletRequest request,PrintWriter printWriter){
        JSONObject out = new JSONObject();
        MbEventPart mbEventPart = new MbEventPart();
        mbEventPart.setEventType(request.getParameter("eventType"));
        mbEventPart.setAssembleId(request.getParameter("assembleId"));
        List<MbEventPart> mbEventPartList = mbEventPartDao.getByPartType1(mbEventPart);
        out.put("data",mbEventPartList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/selectChildrenPartContrast")
    public void selectChildrenPartContrast(HttpServletRequest request,PrintWriter printWriter){
        JSONObject out = new JSONObject();
        MbEventPart mbEventPart = new MbEventPart();
        mbEventPart.setEventType(request.getParameter("eventType"));
        mbEventPart.setAssembleId(request.getParameter("assembleId"));
        List<MbEventPart> mbEventPartList = mbEventPartDao.getByPartType1Contrast(mbEventPart);
        out.put("data",mbEventPartList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    public void eventAttrSaveOrCopy(List<JSONObject> jsonList,String actionFlag) {
        int seqEventAttr = 0;
        for (JSONObject json : jsonList) {
            String reqNum = json.getString("reqNum");
            String eventType=json.getString("eventType");
            String assembleRule="";
            String optionFlag;
            String assembleType=json.getString("assembleType");
            if ("copy".equals(actionFlag))
            {
                optionFlag="I";
            }else{
                optionFlag=json.getString("optionFlag");
            }
            if(seqEventAttr==0) {
                String OldEventType=json.getString("oldEventType")==null?json.getString("eventType"):json.getString("oldEventType");
                String seqNoAttr = mbEventAttrDao.getMaxSeqNo(OldEventType);
                seqEventAttr = seqNoAttr == null ? 1 : Integer.parseInt(seqNoAttr) + 1;
            }
            if("I".equals(optionFlag)) {
                if ("ATTR".equals(assembleType)) {
                    String useMethod = mbAttrTypeDao.getUseMethod(json.getString("assembleId"));
                    if ("CTR".equals(useMethod)) {
                        assembleRule = "A";
                    } else if ("IND".equals(useMethod)) {
                        assembleRule = "C";
                    } else if ("EVAL".equals(useMethod)) {
                        assembleRule = "F";
                    }
                } else if ("PART".equals(assembleType)) {
                    assembleRule = mbPartTypeDao.getProcessMethod(json.getString("assembleId"));
                }
            }else{
                assembleRule=mbEventAttrDao.selectByPrimaryKey(new MbEventAttr(),eventType,json.getString("seqNo")).getAssembleRule();
            }
            json.put("assembleRule",assembleRule);
            seqEventAttr= ProdDefineBeanToJson.EventAttrToJson(json,optionFlag,eventType, seqEventAttr, reqNum, assembleType.toLowerCase());
            if("PART".equals(assembleType)){
                JSONArray array = json.getJSONArray("formData");
                String assembleId=json.getString("assembleId");
                for(int i=0;i<array.size();i++){
                    JSONObject jsonObject = array.getJSONObject(i);
                    if(jsonObject.size()==0){
                        continue;
                    }else {
                        jsonObject.put("assembleId",assembleId);
                        jsonObject.put("optionFlag",optionFlag);
                        ProdDefineBeanToJson.EventPartToJson(jsonObject,optionFlag,eventType,reqNum);
                    }
                }
            }
        }
    }
}

