package com.dcits.ensemble.om.pf.action.dataFactory;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pf.dao.dataFactory.*;
import com.dcits.ensemble.om.pf.module.dataFactory.*;
import com.dcits.ensemble.om.pf.util.ProdDefineBeanToJson;
import com.dcits.ensemble.om.pf.util.ProdToMap;
import com.dcits.ensemble.om.pf.util.action.ActionResultWrite;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wujuan on 2015/10/21.
 */
@Controller
@RequestMapping("/prodDefine")
public class MbProdDefineAction {
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
    private MbProdRunRuleDao mbProdRunRuleDao;

    @Resource
    private MbProdDefineDao mbProdDefineDao;

    @Resource
    private MbProdTypeDao mbProdTypeDao;

    @Resource
    private MbEventLinkDao mbEventLinkDao;

    @Resource
    private MbProdGroupDao mbProdGroupDao;

    @Resource
    private MbProdRelationDefineDao mbProdRelationDefineDao;

    @RequestMapping("/save")
    @Transactional
    public void save(PrintWriter printWriter, @RequestBody String request)   {
        List<JSONObject> jsonList = JSON.parseArray(request, JSONObject.class);
        Map result = new HashMap();
        System.out.println("--prodDefine--save-----" + jsonList.toString());
        try {
            prodDefineSaveOrCopy(jsonList,"save");
            result.put("retStatus", "S");
            result.put("retMsg", "保存成功");
        } catch (Exception e) {
            result.put("retStatus", "F");
            result.put("retMsg", e.getMessage());
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
        System.out.println("--prodDefine--copy-----" + jsonList.toString());
        try {
            prodDefineSaveOrCopy(jsonList,"copy");
            result.put("retStatus", "S");
            result.put("retMsg", "复制成功");
        } catch (Exception e) {
            result.put("retStatus", "F");
            result.put("retMsg", "复制失败");
            throw e;
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getDefine")
    public void getAttr(HttpServletRequest request, PrintWriter printWriter) {
        JSONArray outArray = new JSONArray();
        MbProdDefine mbProdDefine = new MbProdDefine();
        mbProdDefine.setProdType(request.getParameter("prodType"));
        List<MbProdDefine> mbProdDefines = mbProdDefineDao.selectByProdType(mbProdDefine);
        for (int i = 0; i < mbProdDefines.size(); i++) {
            JSONObject eventAttr = new JSONObject();
            eventAttr.put("prodType", mbProdDefines.get(i).getProdType());
            eventAttr.put("assembleType", mbProdDefines.get(i).getAssembleType());
            eventAttr.put("assembleId", mbProdDefines.get(i).getAssembleId());
            eventAttr.put("attrValue", mbProdDefines.get(i).getAttrValue());
            eventAttr.put("seqNo", mbProdDefines.get(i).getSeqNo());
            try {
                if ("ATTR".equals(mbProdDefines.get(i).getAssembleType())) {
                    MbAttrType mbAttrType = mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(), mbProdDefines.get(i).getAssembleId());
                    if (mbAttrType!=null&&"A".equals(mbAttrType.getStatus())) {
                        eventAttr.put("valueMethod", mbAttrType.getValueMethod());
                        eventAttr.put("attrDesc", mbAttrType.getAttrDesc());
                        eventAttr.put("setValueFlag", mbAttrType.getSetValueFlag());
                        eventAttr.put("attrClass",mbAttrType.getAttrClass());
                    }
                } else if ("EVENT".equals(mbProdDefines.get(i).getAssembleType())) {
                    MbEventType mbEventType = mbEventTypeDao.selectByPrimaryKey(new MbEventType(), mbProdDefines.get(i).getAssembleId());
                    JSONArray collect = new JSONArray();
                    MbEventAttr mbEventAttr = new MbEventAttr();
                    mbEventAttr.setEventType(mbProdDefines.get(i).getAssembleId());
                    List<MbEventAttr> mbEventAttrs = mbEventAttrDao.selectByEventType(mbEventAttr);
                    for (int j = 0; j < mbEventAttrs.size(); j++) {
                        JSONObject eventPart = new JSONObject();
                        eventPart.put("eventType", mbEventAttrs.get(j).getEventType());
                        eventPart.put("assembleType", mbEventAttrs.get(j).getAssembleType());
                        eventPart.put("assembleId", mbEventAttrs.get(j).getAssembleId());
                        eventPart.put("attrValue", mbEventAttrs.get(j).getAttrValue());
                        eventPart.put("assembleRule", mbEventAttrs.get(j).getAssembleRule());
                        eventPart.put("seqNo", mbEventAttrs.get(j).getSeqNo());
                        if ("ATTR".equals(mbEventAttrs.get(j).getAssembleType())) {
                            MbAttrType mbAttrType = mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(), mbEventAttrs.get(j).getAssembleId());
                            if (null != mbAttrType && "A".equals(mbAttrType.getStatus())) {
                                eventPart.put("attrDesc", mbAttrType.getAttrDesc());
                                eventPart.put("attrClass", mbAttrType.getAttrClass());
                                eventPart.put("valueMethod", mbAttrType.getValueMethod());
                                eventPart.put("setValueFlag", mbAttrType.getSetValueFlag());
                            }
                        } else if ("PART".equals(mbEventAttrs.get(j).getAssembleType())) {
                            JSONArray result = new JSONArray();
                            MbPartType mbPartType = mbPartTypeDao.selectByPrimaryKey(new MbPartType(), mbEventAttrs.get(j).getAssembleId());
                            MbEventPart mbEventPart = new MbEventPart();
                            mbEventPart.setEventType(mbProdDefines.get(i).getAssembleId());
                            mbEventPart.setAssembleId(mbEventAttrs.get(j).getAssembleId());
                            List<MbEventPart> mbEventPartList = mbEventPartDao.getByPartType1(mbEventPart);
                            for (int k = 0; k < mbEventPartList.size(); k++) {
                                MbAttrType mbAttrType = mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(),mbEventPartList.get(k).getAttrKey());
                                JSONObject data = new JSONObject();
                                data.put("attrKey", mbEventPartList.get(k).getAttrKey());
                                data.put("attrValue", mbEventPartList.get(k).getAttrValue());
                                data.put("valueMethod", mbAttrType.getValueMethod());
                                data.put("attrDesc", mbAttrType.getAttrDesc());
                                data.put("setValueFlag", mbAttrType.getSetValueFlag());
                                result.add(k, data);
                            }
                            if (mbPartType!=null&&"A".equals(mbPartType.getStatus())) {
                                eventPart.put("partClass", mbPartType.getPartClass());
                                eventPart.put("partDesc", mbPartType.getPartDesc());
                                eventPart.put("data", result);
                            }
                        }
                        collect.add(j, eventPart);
                    }
                    if (mbEventType!=null&&"A".equals(mbEventType.getStatus())) {
                        eventAttr.put("eventClass", mbEventType.getEventClass());
                        eventAttr.put("eventDesc", mbEventType.getEventDesc());
                        eventAttr.put("prodData", collect);
                    }
                } else if ("PART".equals(mbProdDefines.get(i).getAssembleType())) {
                    MbPartType mbPartType = mbPartTypeDao.selectByPrimaryKey(new MbPartType(), mbProdDefines.get(i).getAssembleId());
                    if (mbPartType!=null&&"A".equals(mbPartType.getStatus())) {
                        eventAttr.put("partClass", mbPartType.getPartClass());
                        eventAttr.put("partDesc", mbPartType.getPartDesc());
                    }
                }
            } catch (Exception e) {
                throw e;
            }
            outArray.add(eventAttr);
        }
        JSONObject ProdGroup = new JSONObject();
        JSONArray array1 = new JSONArray();
        MbProdGroup mbProdGroup = new MbProdGroup();
        mbProdGroup.setProdType(request.getParameter("prodType"));
        List<MbProdGroup> mbProdGroupList = mbProdGroupDao.selectByPrimaryKeyList(mbProdGroup);
        for (int k = 0; k < mbProdGroupList.size(); k++) {
            JSONObject data = new JSONObject();
            data.put("prodType", mbProdGroupList.get(k).getProdType());
            data.put("prodSubType",  mbProdGroupList.get(k).getProdSubType());
            data.put("prodSubDesc",  mbProdTypeDao.getProdDesc( mbProdGroupList.get(k).getProdSubType()));
            data.put("defaultProd", mbProdGroupList.get(k).getDefaultProd());
            array1.add(k, data);
        }
        ProdGroup.put("tableType", "ProdGroup");
        ProdGroup.put("ProdGroup", array1);
        outArray.add(ProdGroup);
        JSONObject ProdRelatDefine = new JSONObject();
        JSONArray array2 = new JSONArray();
        MbProdRelationDefine mbProdRelationDefine = new MbProdRelationDefine();
        mbProdRelationDefine.setProdType(request.getParameter("prodType"));
        List<MbProdRelationDefine> mbProdRelationDefineList = mbProdRelationDefineDao.selectByPrimaryKeyList(mbProdRelationDefine);
        for (int k = 0; k < mbProdRelationDefineList.size(); k++) {
            JSONObject data = new JSONObject();
            data.put("eventType", mbProdRelationDefineList.get(k).getEventType());
            data.put("assembleType", mbProdRelationDefineList.get(k).getAssembleType());
            data.put("assembleId",  mbProdRelationDefineList.get(k).getAssembleId());
            data.put("status", mbProdRelationDefineList.get(k).getStatus());
            array2.add(k, data);
        }
        ProdRelatDefine.put("tableType", "ProdRelatDefine");
        ProdRelatDefine.put("ProdRelatDefine", array2);
        outArray.add(ProdRelatDefine);
        JSONObject EventLink = new JSONObject();
        JSONArray array3 = new JSONArray();
        MbEventLink mbEventLink = new MbEventLink();
        mbEventLink.setProdType(request.getParameter("prodType"));
        List<MbEventLink> mbEventLinkList = mbEventLinkDao.selectByPrimaryKeyList(mbEventLink);
        for (int k = 0; k < mbEventLinkList.size(); k++) {
            JSONObject data = new JSONObject();
            data.put("prodType", mbEventLinkList.get(k).getProdType());
            data.put("origProdType",  mbEventLinkList.get(k).getOrigProdType());
            data.put("origEventType", mbEventLinkList.get(k).getOrigEventType());
            data.put("linkProdType", mbEventLinkList.get(k).getLinkProdType());
            data.put("linkEventType",  mbEventLinkList.get(k).getLinkEventType());
            data.put("linkCondition", mbEventLinkList.get(k).getLinkCondition());
            data.put("status", mbEventLinkList.get(k).getStatus());
            array3.add(k, data);
        }
        EventLink.put("tableType", "EventLink");
        EventLink.put("EventLink", array3);
        outArray.add(EventLink);
        System.out.println("--prodDefine--getDefine-----" + outArray.toString());
        printWriter.print(outArray.toJSONString());
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/getAttrAll")
    public void getAttrAll(HttpServletRequest request, PrintWriter printWriter) {
        JSONArray outArray = new JSONArray();
        MbProdDefine mbProdDefine = new MbProdDefine();
        mbProdDefine.setProdType(request.getParameter("prodType"));
        List<MbProdDefine> mbProdDefines = mbProdDefineDao.selectByProdType(mbProdDefine);
        for (int i = 0; i < mbProdDefines.size(); i++) {
            if ("ATTR".equals(mbProdDefines.get(i).getAssembleType())) {
                JSONObject prodAttr = new JSONObject();
                prodAttr.put("prodType", mbProdDefines.get(i).getProdType());
                prodAttr.put("assembleType", "属性");
                prodAttr.put("assembleId", mbProdDefines.get(i).getAssembleId());
                prodAttr.put("attrValue", mbProdDefines.get(i).getAttrValue());
                prodAttr.put("seqNo", mbProdDefines.get(i).getSeqNo());
                MbAttrType mbAttrType = mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(), mbProdDefines.get(i).getAssembleId());
                if ("A".equals(mbAttrType.getStatus())) {
                    prodAttr.put("attrDesc", mbAttrType.getAttrDesc());
                    prodAttr.put("attrClass", mbAttrType.getAttrClass());
                    prodAttr.put("valueMethod", mbAttrType.getValueMethod());
                }
                MbAttrValue MbAttrValue = mbAttrValueDao.selectByPrimaryKey(new MbAttrValue(), mbProdDefines.get(i).getAssembleId(), mbProdDefines.get(i).getAttrValue());
                if (MbAttrValue != null) {
                    prodAttr.put("attrValue", mbProdDefines.get(i).getAttrValue() + "-" + MbAttrValue.getValueDesc());
                } else {
                    if ("Y".equals(mbProdDefines.get(i).getAttrValue())) {
                        prodAttr.put("attrValue", mbProdDefines.get(i).getAttrValue() + "-是");
                    } else if ("N".equals(mbProdDefines.get(i).getAttrValue())) {
                        prodAttr.put("attrValue", mbProdDefines.get(i).getAttrValue() + "-否");
                    } else {
                        prodAttr.put("attrValue", mbProdDefines.get(i).getAttrValue());
                    }
                }
                outArray.add(prodAttr);
            } else if ("PART".equals(mbProdDefines.get(i).getAssembleType())) {
                MbPartAttr mbProdPart = new MbPartAttr();
                mbProdPart.setPartType(mbProdDefines.get(i).getAssembleId());
                MbPartType mbPartType = mbPartTypeDao.selectByPrimaryKey(new MbPartType(), mbProdDefines.get(i).getAssembleId());
                List<MbPartAttr> mbPartAttrs = mbPartAttrDao.getByPartType(mbProdPart);
                for (int j = 0; j < mbPartAttrs.size(); j++) {
                    JSONObject partAttr = new JSONObject();
                    partAttr.put("prodType", mbProdDefines.get(i).getProdType());
                    partAttr.put("assembleType", "部件-" + mbPartType.getPartDesc());
                    partAttr.put("assembleId", mbPartAttrs.get(j).getAttrKey());
                    partAttr.put("attrValue", mbPartAttrs.get(j).getAttrValue());
                    partAttr.put("seqNo", mbProdDefines.get(i).getSeqNo());
                    MbAttrType mbAttrTypeP = mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(), mbPartAttrs.get(j).getAttrKey());
                    if ("A".equals(mbAttrTypeP.getStatus())) {
                        partAttr.put("attrDesc", mbAttrTypeP.getAttrDesc());
                        partAttr.put("attrClass", mbAttrTypeP.getAttrClass());
                        partAttr.put("valueMethod", mbAttrTypeP.getValueMethod());
                    }
                    MbAttrValue MbAttrValue = mbAttrValueDao.selectByPrimaryKey(new MbAttrValue(), mbPartAttrs.get(j).getAttrKey(), mbPartAttrs.get(j).getAttrValue());
                    if (MbAttrValue != null) {
                        partAttr.put("attrValue", mbPartAttrs.get(j).getAttrValue() + "-" + MbAttrValue.getValueDesc());
                    } else {
                        if ("Y".equals(mbPartAttrs.get(j).getAttrValue())) {
                            partAttr.put("attrValue", mbPartAttrs.get(j).getAttrValue() + "-是");
                        } else if ("N".equals(mbPartAttrs.get(j).getAttrValue())) {
                            partAttr.put("attrValue", mbPartAttrs.get(j).getAttrValue() + "-否");
                        } else {
                            partAttr.put("attrValue", mbPartAttrs.get(j).getAttrValue());
                        }
                    }
                    outArray.add(partAttr);
                }
            } else if ("EVENT".equals(mbProdDefines.get(i).getAssembleType())) {
                MbEventAttr mbEventAttr = new MbEventAttr();
                mbEventAttr.setEventType(mbProdDefines.get(i).getAssembleId());
                MbEventType mbEventType = mbEventTypeDao.selectByPrimaryKey(new MbEventType(), mbProdDefines.get(i).getAssembleId());
                List<MbEventAttr> mbEventAttrs = mbEventAttrDao.selectByEventType(mbEventAttr);
                for (int j = 0; j < mbEventAttrs.size(); j++) {
                    if ("ATTR".equals(mbEventAttrs.get(j).getAssembleType())) {
                        JSONObject eventAttr = new JSONObject();
                        eventAttr.put("prodType", mbProdDefines.get(i).getProdType());
                        eventAttr.put("assembleType", "事件-" + mbEventType.getEventDesc());
                        eventAttr.put("assembleId", mbEventAttrs.get(j).getAssembleId());
                        eventAttr.put("attrValue", mbEventAttrs.get(j).getAttrValue());
                        eventAttr.put("seqNo", mbProdDefines.get(i).getSeqNo());
                        MbAttrType mbAttrType = mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(), mbEventAttrs.get(j).getAssembleId());
                        if ("A".equals(mbAttrType.getStatus())) {
                            eventAttr.put("attrDesc", mbAttrType.getAttrDesc());
                            eventAttr.put("attrClass", mbAttrType.getAttrClass());
                            eventAttr.put("valueMethod", mbAttrType.getValueMethod());
                        }
                        MbAttrValue MbAttrValue = mbAttrValueDao.selectByPrimaryKey(new MbAttrValue(), mbEventAttrs.get(j).getAssembleId(), mbEventAttrs.get(j).getAttrValue());
                        if (MbAttrValue != null) {
                            eventAttr.put("attrValue", mbEventAttrs.get(j).getAttrValue() + "-" + MbAttrValue.getValueDesc());
                        } else {
                            if ("Y".equals(mbEventAttrs.get(j).getAttrValue())) {
                                eventAttr.put("attrValue", mbEventAttrs.get(j).getAttrValue() + "-是");
                            } else if ("N".equals(mbEventAttrs.get(j).getAttrValue())) {
                                eventAttr.put("attrValue", mbEventAttrs.get(j).getAttrValue() + "-否");
                            } else {
                                eventAttr.put("attrValue", mbEventAttrs.get(j).getAttrValue());
                            }
                        }
                        outArray.add(eventAttr);
                    } else if ("PART".equals(mbEventAttrs.get(j).getAssembleType())) {
                        MbPartAttr mbPartAttr = new MbPartAttr();
                        mbPartAttr.setPartType(mbEventAttrs.get(j).getAssembleId());
                        MbPartType mbPartType = mbPartTypeDao.selectByPrimaryKey(new MbPartType(), mbEventAttrs.get(j).getAssembleId());
                        List<MbPartAttr> mbPartAttrs = mbPartAttrDao.getByPartType(mbPartAttr);
                        for (int k = 0; k < mbPartAttrs.size(); k++) {
                            JSONObject eventAttrP = new JSONObject();
                            eventAttrP.put("prodType", mbProdDefines.get(i).getProdType());
                            eventAttrP.put("assembleType", "事件-" + mbEventType.getEventDesc() + "（部件-" + mbPartType.getPartDesc() + ")");
                            eventAttrP.put("assembleId", mbPartAttrs.get(k).getAttrKey());
                            eventAttrP.put("attrValue", mbPartAttrs.get(k).getAttrValue());
                            eventAttrP.put("seqNo", mbProdDefines.get(i).getSeqNo());
                            MbAttrType mbAttrTypeP = mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(), mbPartAttrs.get(k).getAttrKey());
                            if ("A".equals(mbAttrTypeP.getStatus())) {
                                eventAttrP.put("attrDesc", mbAttrTypeP.getAttrDesc());
                                eventAttrP.put("attrClass", mbAttrTypeP.getAttrClass());
                                eventAttrP.put("valueMethod", mbAttrTypeP.getValueMethod());
                            }
                            MbAttrValue MbAttrValue = mbAttrValueDao.selectByPrimaryKey(new MbAttrValue(), mbPartAttrs.get(k).getAttrKey(), mbPartAttrs.get(k).getAttrValue());
                            if (MbAttrValue != null) {
                                eventAttrP.put("attrValue", mbPartAttrs.get(k).getAttrValue() + "-" + MbAttrValue.getValueDesc());
                            } else {
                                if ("Y".equals(mbPartAttrs.get(k).getAttrValue())) {
                                    eventAttrP.put("attrValue", mbPartAttrs.get(k).getAttrValue() + "-是");
                                } else if ("N".equals(mbPartAttrs.get(k).getAttrValue())) {
                                    eventAttrP.put("attrValue", mbPartAttrs.get(k).getAttrValue() + "-否");
                                } else {
                                    eventAttrP.put("attrValue", mbPartAttrs.get(k).getAttrValue());
                                }
                            }
                            outArray.add(eventAttrP);
                        }
                    }
                }
            }
        }
        System.out.println("--prodDefine--getAttrAll-----" + outArray.toString());
        printWriter.print(outArray.toJSONString());
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/selectAll")
    public void selectAll( PrintWriter printWriter) {
        List<MbProdDefine> info ;
        try {
            info = mbProdDefineDao.selectAll();
            ActionResultWrite.setReadResult(printWriter, info);
        } catch (Exception e) {
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
            throw e;
        }
    }

    @RequestMapping("/selectParent")
    public void selectParent(HttpServletRequest request, PrintWriter printWriter) {
        List<MbProdDefine> info = new ArrayList<>();   //元数据
        String prodType = request.getParameter("prodType");
        String assembleType = request.getParameter("assembleType");
        String assembleId = request.getParameter("assembleId");
        try {
            if (StringUtils.isEmpty(prodType)) {
                ActionResultWrite.setReadResult(printWriter, new ArrayList<>());
                return;
            } else if (assembleId.isEmpty() && assembleType.isEmpty()) { //1 100
                MbProdDefine prodDefine = new MbProdDefine();
                prodDefine.setProdType(prodType);
                prodDefine.setAssembleType(null);
                prodDefine.setAssembleId(null);
                info = mbProdDefineDao.selectByCondition(prodDefine);
            } else if (assembleId.isEmpty() && !(assembleType.isEmpty())) { //2  110
                MbProdDefine prodDefine = new MbProdDefine();
                prodDefine.setProdType(prodType);
                prodDefine.setAssembleType(assembleType);
                prodDefine.setAssembleId(null);
                info = mbProdDefineDao.selectByCondition(prodDefine);
            } else if (!(assembleType.isEmpty()) && !(assembleId.isEmpty())) { //3  111
                if ("ATTR".equals(assembleType)) {
                    MbProdDefine prodDefine = new MbProdDefine();
                    prodDefine.setProdType(prodType);
                    prodDefine.setAttrKey(assembleId);
                    info = mbProdDefineDao.selectByConditionFourth(prodDefine);
                } else {
                    MbProdDefine prodDefine = new MbProdDefine();
                    prodDefine.setProdType(prodType);
                    prodDefine.setAssembleType(assembleType);
                    prodDefine.setAssembleId(assembleId);
                    info = mbProdDefineDao.selectByCondition(prodDefine);
                }
            }
            for (MbProdDefine mbProdDefine : info) {
                if (mbProdDefine.getAssembleType().isEmpty()) {   //对参数进行汉化
                    String key = mbProdDefine.getAttrKey();
                    String value = mbProdDefine.getAttrValue();
                    String attrDesc = mbAttrTypeDao.getAttrDesc(key);
                    mbProdDefine.setAttrKey(attrDesc);
                    String valueMethod = mbAttrTypeDao.getValueMethod(key);
                    if (null != valueMethod) {
                        if ("FD".equals(valueMethod)) {
                            MbAttrValue attrValue = new MbAttrValue();
                            attrValue.setAttrKey(key);
                            attrValue.setAttrValue(value);
                            mbProdDefine.setAttrValue(mbAttrValueDao.getAttrValueDesc(attrValue));
                        } else {
                            MbAttrValue attrValue = new MbAttrValue();
                            attrValue.setAttrKey(key);
                            attrValue.setAttrValue(value);
                            String valueDesc = mbAttrValueDao.getAttrValueDesc(attrValue);
                            if (valueDesc == null) {   // null(a) ->DESC(a)
                                mbProdDefine.setAttrValue(attrDesc + " (" + value + ")");
                            } else {    //desc(null)
                                mbProdDefine.setAttrValue(valueDesc + " (" + value + ")");
                            }
                        }
                    }

                }
            }
            ActionResultWrite.setReadResult(printWriter, info);
        } catch (Exception e) {
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
            throw e;
        }
    }

    @RequestMapping("/selectChild")
    public void selectChild(HttpServletRequest request, PrintWriter printWriter) {
        try {
            List<MbProdDefine> infoList = new ArrayList<>();
            if ("EVENT".equals(request.getParameter("assembleType"))) {
                MbEventAttr mbEventAttr = new MbEventAttr();
                mbEventAttr.setEventType(request.getParameter("assembleId"));
                List<MbEventAttr> list = mbEventAttrDao.selectByEventType(mbEventAttr);
                //事件类型查找得到的集合，其中有可能含有指标
                for (MbEventAttr eventAttr : list) {
                    if (eventAttr.getAssembleType().equals("PART")) {
                        MbPartAttr partAttr = new MbPartAttr();
                        partAttr.setPartType(eventAttr.getAssembleId());      //事件下的指标
                        List<MbPartAttr> attrslist = mbPartAttrDao.getByPartType(partAttr);
                        for (MbPartAttr mbPartAttr : attrslist) {
                            MbProdDefine mbProdDefine = new MbProdDefine();
                            mbProdDefine.setAssembleId(mbEventTypeDao.getEventDesc(request.getParameter("assembleId")) + " 下的 " + mbPartTypeDao.getPartTypeDesc(mbPartAttr.getPartType()) + " 指标");
                            String attrKey = mbAttrTypeDao.getAttrDesc(mbPartAttr.getAttrKey());
                            mbProdDefine.setAttrKey(attrKey);
                            MbAttrValue attrValue = new MbAttrValue();
                            attrValue.setAttrKey(mbPartAttr.getAttrKey());
                            attrValue.setAttrValue(mbPartAttr.getAttrValue());
                            String valueMethod = mbAttrTypeDao.getValueMethod(attrValue.getAttrKey());
                            String attrValue1 = mbAttrValueDao.getAttrValue(attrValue);
                            String valueDesc = mbAttrValueDao.getAttrValueDesc(attrValue);
                            if (null != valueMethod) {
                                if ("FD".equals(valueMethod)) {
                                    mbProdDefine.setAttrValue(valueDesc);
                                } else {
                                    if (attrValue1 == null) {  //空数据
                                        mbProdDefine.setAttrValue("");       //null(null) ->  ""
                                    } else if (valueDesc == null) {
                                        mbProdDefine.setAttrValue(attrKey + " (" + eventAttr.getAttrValue() + ")");  //null(x) -> DESC(x)
                                    } else {
                                        mbProdDefine.setAttrValue(valueDesc + " (" + attrValue1 + ")");  //desc(x)
                                    }
                                }
                            }
                            infoList.add(mbProdDefine);
                        }
                    } else if ("ATTR".equals(eventAttr.getAssembleType())) {
                        MbProdDefine mbProdDefine = new MbProdDefine();
                        mbProdDefine.setAssembleId(mbEventTypeDao.getEventDesc(request.getParameter("assembleId")));
                        mbProdDefine.setAssembleType("事件");
                        //字段内容汉化
                        String attrDesc = mbAttrTypeDao.getAttrDesc(eventAttr.getAssembleId());
                        mbProdDefine.setAttrKey(attrDesc);
                        MbAttrValue attrValue = new MbAttrValue();
                        attrValue.setAttrKey(eventAttr.getAssembleId());
                        attrValue.setAttrValue(eventAttr.getAttrValue());
                        String valueDesc = mbAttrValueDao.getAttrValueDesc(attrValue);
                        String valueMethod = mbAttrTypeDao.getValueMethod(attrValue.getAttrKey());
                        if (null != valueMethod) {
                            if ("FD".equals(valueMethod)) {
                                mbProdDefine.setAttrValue(valueDesc);
                            } else {
                                if (eventAttr.getAttrValue() == null) {  //空数据
                                    mbProdDefine.setAttrValue("");       //null(null) ->  ""
                                } else if (valueDesc == null) {
                                    mbProdDefine.setAttrValue(attrDesc + " (" + eventAttr.getAttrValue() + ")");  //null(x) -> DESC(x)
                                } else {
                                    mbProdDefine.setAttrValue(valueDesc + " (" + eventAttr.getAttrValue() + ")");  //desc(x)
                                }
                            }
                        }
                        infoList.add(mbProdDefine);
                    }
                }
            } else if ("PART".equals(request.getParameter("assembleType"))) {
                MbPartAttr partAttr = new MbPartAttr();
                partAttr.setPartType(request.getParameter("assembleId"));
                List<MbPartAttr> attrslist = mbPartAttrDao.getByPartType(partAttr);
                for (MbPartAttr mbPartAttr : attrslist) {
                    MbProdDefine mbProdDefine = new MbProdDefine();
                    mbProdDefine.setAssembleId(mbPartTypeDao.getPartTypeDesc(mbPartAttr.getPartType()));
                    String attrDesc = mbAttrTypeDao.getAttrDesc(mbPartAttr.getAttrKey());
                    mbProdDefine.setAttrKey(attrDesc);
                    MbAttrValue attrValue = new MbAttrValue();
                    attrValue.setAttrKey(mbPartAttr.getAttrKey());
                    attrValue.setAttrValue(mbPartAttr.getAttrValue());
                    String valueMethod = mbAttrTypeDao.getValueMethod(attrValue.getAttrKey());
                    String attrValueD = mbAttrValueDao.getAttrValueDesc(attrValue);
                    String attrvalue1 = mbAttrValueDao.getAttrValue(attrValue);
                    if (null != valueMethod) {
                        if ("FD".equals(valueMethod)) {
                            mbProdDefine.setAttrValue(attrValueD);
                        } else if (attrvalue1 == null) {
                            mbProdDefine.setAttrValue("");     //null(null) ->  ""
                        } else if (attrValueD == null) {
                            mbProdDefine.setAttrValue(attrDesc + "(" + attrvalue1 + ")");  //null(x) -> DESC(x)
                        } else {
                            mbProdDefine.setAttrValue(attrValueD + "(" + attrvalue1 + ")");
                        }
                    }
                    infoList.add(mbProdDefine);
                }
            }
            ActionResultWrite.setReadResult(printWriter, infoList);
        } catch (Exception e) {
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
            throw e;
        }
    }

    @RequestMapping("/getCprod")
    public void getCprod( PrintWriter printWriter) {
        JSONObject out = new JSONObject();
        List<MbEventPartRelation> mbEventPartRelations = mbProdDefineDao.getCprod();
        out.put("data", mbEventPartRelations);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/selectByPrimaryKeyList")
    public void selectByPrimaryKeyList(HttpServletRequest request, PrintWriter printWriter) {
        JSONObject out = new JSONObject();
        MbProdRelationDefine mbProdRelationDefine = new MbProdRelationDefine();
        mbProdRelationDefine.setProdType(request.getParameter("prodType"));
        List<MbProdRelationDefine> mbProdRelationDefineList = mbProdRelationDefineDao.selectByPrimaryKeyList(mbProdRelationDefine);
        out.put("data", mbProdRelationDefineList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getByProdType")
    public void  getByProdType(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        MbProdDefine mbProdDefine = new MbProdDefine();
        mbProdDefine.setProdType(request.getParameter("prodType"));
        List<MbProdDefine> mbProdDefineList= mbProdDefineDao.selectByProdType(mbProdDefine);
        out.put("data",mbProdDefineList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getByProdTypeContrast")
    public void  selectByProdTypeContrast(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out = new JSONObject();
        MbProdDefine mbProdDefine = new MbProdDefine();
        mbProdDefine.setProdType(request.getParameter("prodType"));
        List<MbProdDefine> mbProdDefineList= mbProdDefineDao.selectByProdTypeContrast(mbProdDefine);
        out.put("data",mbProdDefineList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/selectChildrenEvent")
    public void selectChildrenEvent(HttpServletRequest request,PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<MbEventAttr> mbEventAttrList = mbEventAttrDao.getByEventType1(request.getParameter("assembleId"));
        out.put("data",mbEventAttrList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/selectChildrenEventContrsat")
    public void selectChildrenEventContrast(HttpServletRequest request,PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<MbEventAttr> mbEventAttrList = mbEventAttrDao.getByEventType1Contrast(request.getParameter("assembleId"));
        out.put("data",mbEventAttrList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    public void prodDefineSaveOrCopy(List<JSONObject> jsonList,String actionFlag){
        int seqProdDefine=0;
        for (JSONObject json : jsonList) {
            String AssembleID="";
            String reqNum = json.getString("reqNum");
            String optionFlag=json.getString("optionFlag");
            String prodType=json.getString("oldProdType")==null?json.getString("prodType"):json.getString("oldProdType");
            if(seqProdDefine==0) {
                String seqNoDefine = mbProdDefineDao.getMaxSeqNo(prodType);
                seqProdDefine = seqNoDefine == null ? 1 : Integer.parseInt(seqNoDefine) + 1;
            }
            if ("EVENT".equals(json.getString("assembleType"))) {
                int a = json.getString("assembleId").indexOf("_");
                AssembleID = json.getString("assembleId").substring(0, a + 1) + json.getString("prodType");
            }else{
                AssembleID = json.getString("assembleId");
            }
            if("copy".equals(actionFlag))
            {
                optionFlag="I";
            }
            if(json.getString("assembleType")!=null) {
                seqProdDefine = ProdDefineBeanToJson.ProdDefineToJson(json, optionFlag, AssembleID, seqProdDefine, reqNum);
            }
            if ("EVENT".equals(json.getString("assembleType"))) {
                JSONArray attr = json.getJSONArray("attr");
                JSONArray part = json.getJSONArray("part");
                JSONArray part1 = json.getJSONArray("part1");
                String seqNoAttr = mbEventAttrDao.getMaxSeqNo(AssembleID);
                int seqEventAttr = seqNoAttr == null ? 1 : Integer.parseInt(seqNoAttr) + 1;
                for (int j = 0; j < attr.size(); j++) {
                    JSONObject jsonObject = attr.getJSONObject(j);
                    seqEventAttr= ProdDefineBeanToJson.EventAttrToJson(jsonObject,optionFlag,AssembleID,seqEventAttr,reqNum,"attr");
                }
                for (int k = 0; k < part.size(); k++) {
                    JSONObject jsonObject = part.getJSONObject(k);
                    if (jsonObject.size() == 2) {
                        continue;
                    } else {
                        ProdDefineBeanToJson.EventPartToJson(jsonObject,optionFlag,AssembleID,reqNum);
                    }
                }
                for (int m = 0; m < part1.size(); m++) {
                    JSONObject jsonObject = part1.getJSONObject(m);
                    seqEventAttr= ProdDefineBeanToJson.EventAttrToJson(jsonObject,optionFlag,AssembleID,seqEventAttr,reqNum,"part");
                }
                if ("I".equals(optionFlag)) {
                    MbEventType mbEventType = new MbEventType();
                    mbEventType.setEventType(json.getString("assembleId"));
                    List<MbEventType> list1 = mbEventTypeDao.getByEventType(mbEventType);
                    for (int i = 0; i < list1.size(); i++) {
                        String str = list1.get(i).getEventDesc();
                        String eventDesc = json.getString("prodDesc") + str.substring(str.length() - 2, str.length());
                        list1.get(i).setEventType(AssembleID);
                        list1.get(i).setEventDesc(eventDesc);
                        ProdToMap.eventType(list1.get(i), "Insert", reqNum);
                    }
                }
            }
            if (json.getString("operateType") != null) {
                JSONArray childProdList = json.getJSONArray("childProdList");
                JSONArray combProdList = json.getJSONArray("combProdList");
                JSONArray prodTableM = json.getJSONArray("prodTableM");
                String seqNoGroup = mbProdGroupDao.getMaxSeqNo(AssembleID);
                int seqGroup = seqNoGroup == null ? 1 : Integer.parseInt(seqNoGroup) + 1;
                if ("update".equals(json.getString("operateType"))) {
                    List<MbEventLink> linkList = mbEventLinkDao.selectListByPrimaryKey(new MbEventLink(), json.getString("prodType"), null, null, null, null);
                    for (MbEventLink mbEventLink1 : linkList) {
                        ProdToMap.eventLink(mbEventLink1, "Delete", reqNum);
                    }
                    List<MbProdGroup> prodGroupList = mbProdGroupDao.selectListByPrimaryKey(new MbProdGroup(), json.getString("prodType"), null);
                    for (MbProdGroup mbProdGroup1 : prodGroupList) {
                        ProdToMap.prodGroup(mbProdGroup1, "Delete", reqNum);
                    }
                    List<MbProdRelationDefine> prodRelationDefineList = mbProdRelationDefineDao.selectListByPrimaryKey(new MbProdRelationDefine(), json.getString("prodType"), null, null, null);
                    for (MbProdRelationDefine prodRelationDefine : prodRelationDefineList) {
                        ProdToMap.prodRelationDefine(prodRelationDefine, "Delete", reqNum);
                    }
                }
                for (int i = 0; i < childProdList.size(); i++) {
                    JSONObject jsonObject = childProdList.getJSONObject(i);
                    seqGroup=ProdDefineBeanToJson.ProdGroupToJson(jsonObject,json.getString("prodType"),seqGroup,reqNum);
                    for (int j = 0; j < combProdList.size(); j++) {
                        JSONObject jsonObject1 = combProdList.getJSONObject(j);
                        ProdDefineBeanToJson.ProdRelationDefineToJson(jsonObject1, jsonObject.getString("childProdType"), json.getString("prodType"), reqNum);
                    }
                }
                for (int k = 0; k < prodTableM.size(); k++) {
                    JSONObject jsonObject = prodTableM.getJSONObject(k);
                    ProdDefineBeanToJson.EventLinkToJson(jsonObject,reqNum);
                }
            }
        }

    }
}