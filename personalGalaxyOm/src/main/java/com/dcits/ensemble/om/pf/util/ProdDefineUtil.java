package com.dcits.ensemble.om.pf.util;

import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pf.module.dataFactory.*;
/**
 * Created by ligan on 2016/9/20.
 */
public class ProdDefineUtil {

    public static MbProdDefine ProdDefineByJson(JSONObject json){
        MbProdDefine mbProdDefine=new MbProdDefine();
        mbProdDefine.setAttrKey(json.getString("attrKey"));
        mbProdDefine.setAttrValue(json.getString("attrValue"));
        mbProdDefine.setEventDefault(json.getString("eventDefault"));
        mbProdDefine.setAssembleType(json.getString("assembleType"));
        mbProdDefine.setProdType(json.getString("prodType"));
        mbProdDefine.setStatus("A");

        return mbProdDefine;
    }
    public static MbEventAttr MbEventAttrByJson(JSONObject json){
        MbEventAttr mbEventAttr = new MbEventAttr();
        mbEventAttr.setAssembleType("ATTR");
        mbEventAttr.setAssembleId(json.getString("assembleId"));
        mbEventAttr.setAssembleRule(json.getString("assembleRule"));
        mbEventAttr.setAttrValue(json.getString("attrValue"));
        return mbEventAttr;
    }
    public static MbEventAttr MbEventAttrPartByJson(JSONObject json){
        MbEventAttr mbEventAttr = new MbEventAttr();
        mbEventAttr.setAssembleType("PART");
        mbEventAttr.setAssembleRule(json.getString("assembleRule"));
        mbEventAttr.setAssembleId(json.getString("assembleId"));
        return mbEventAttr;
    }
    public static MbEventPart MbEventPartByJson(JSONObject json){
        MbEventPart mbEventPart = new MbEventPart();
        mbEventPart.setAssembleId(json.getString("assembleId"));
        mbEventPart.setAttrKey(json.getString("attrKey"));
        mbEventPart.setAttrValue(json.getString("attrValue"));
        return mbEventPart;
    }
    public static MbProdGroup MbProdGroupByJson(JSONObject json){
        MbProdGroup mbProdGroup = new MbProdGroup();
        mbProdGroup.setProdSubType(json.getString("childProdType"));
        mbProdGroup.setDefaultProd(json.getString("defaultProd"));
        return mbProdGroup;
    }
    public static MbProdRelationDefine MbProdRelationDefineByJson(JSONObject json){
        MbProdRelationDefine mbProdRelationDefine=new MbProdRelationDefine();
        mbProdRelationDefine.setEventType(json.getString("eventDefaultType"));
        mbProdRelationDefine.setAssembleType(json.getString("assembleType"));
        mbProdRelationDefine.setAssembleId(json.getString("assembleId"));
        mbProdRelationDefine.setStatus(json.getString("status"));
        return mbProdRelationDefine;
    }
    public static MbEventLink MbEventLinkByJson(JSONObject json){
        MbEventLink mbEventLink=new MbEventLink();
        mbEventLink.setOrigProdType(json.getString("prodA"));
        mbEventLink.setOrigEventType(json.getString("eventA"));
        mbEventLink.setLinkProdType(json.getString("prodB"));
        mbEventLink.setLinkEventType(json.getString("eventB"));
        mbEventLink.setLinkCondition(json.getString("rule"));
        mbEventLink.setStatus("A");
        return mbEventLink;
    }
    public static  String CheckOptionFlag(String jsonFlag,String jsonObFlag){
        String openType="";
       if("I".equals(jsonFlag)){
           if(!"D".equals(jsonObFlag)){
               openType="Insert";
           }
       }else if("U".equals(jsonFlag)) {
           if ("U".equals(jsonObFlag)) {
            openType="Modify";
           }else if("D".equals(jsonObFlag)){
               openType="Delete";
           }else if("I".equals(jsonObFlag)){
               openType="Insert";
           }
       }else if("O".equals(jsonFlag)){
           if("D".equals(jsonObFlag)){
               openType="Delete";
           }
       }else if("D".equals(jsonFlag)){
           openType="Delete";
       }

     return openType;
    }
}
