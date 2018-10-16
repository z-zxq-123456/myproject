package com.dcits.ensemble.om.pf.util;

import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pf.module.dataFactory.*;

/**
 * Created by ligan on 2016/9/22.
 */
public class ProdDefineBeanToJson {
     public static int ProdDefineToJson(JSONObject json,String jsonFlag,String assembleID,int seq,String reqNum){
          int seqNo=seq;
          String seqN;
          String openType=ProdDefineUtil.CheckOptionFlag(jsonFlag,json.getString("optionFlag"));;
          MbProdDefine mbProdDefine = ProdDefineUtil.ProdDefineByJson(json);
          mbProdDefine.setAssembleId(assembleID);
          if(openType.isEmpty()){
               return seqNo;
          }
         if(json.getString("seqNo").isEmpty()){
             seqN=Integer.toString(seqNo);
             seqNo++;
         }else{
             seqN=json.getString("seqNo");
         }
          mbProdDefine.setSeqNo(seqN);
         ProdToMap.prodDefine(mbProdDefine, openType, reqNum);
          return seqNo;
     }
     public static int EventAttrToJson(JSONObject json, String jsonFlag,String assembleID,int seq,String reqNum,String event){
         int seqNo=seq;
         String seqN;
          String openType = ProdDefineUtil.CheckOptionFlag(jsonFlag, json.getString("optionFlag"));
          if(openType.isEmpty()){
            return seqNo;
          }
          if (json.getString("seqNo").isEmpty()) {
              seqN=Integer.toString(seqNo);
               seqNo++;
          }else{
              seqN=json.getString("seqNo");
          }
         MbEventAttr mbEventAttr;
         if("attr".equals(event)){
             mbEventAttr= ProdDefineUtil.MbEventAttrByJson(json);
         }else{
             mbEventAttr= ProdDefineUtil.MbEventAttrPartByJson(json);
         }
          mbEventAttr.setEventType(assembleID);
          mbEventAttr.setSeqNo(seqN);
         ProdToMap.eventAttr(mbEventAttr, openType, reqNum);
          return seqNo;
     }
     public static void EventPartToJson(JSONObject json, String jsonFlag,String assembleID,String reqNum){
          MbEventPart mbEventPart = ProdDefineUtil.MbEventPartByJson(json);
          mbEventPart.setEventType(assembleID);
          String openType = ProdDefineUtil.CheckOptionFlag(jsonFlag, json.getString("optionFlag"));
          if(openType.isEmpty()){
               return;
          }
         ProdToMap.eventPart(mbEventPart, openType, reqNum);
     }
     public static int ProdGroupToJson(JSONObject json ,String prodType,int seq,String reqNum){
          int seqNo=seq;
          MbProdGroup mbProdGroup = ProdDefineUtil.MbProdGroupByJson(json);
          mbProdGroup.setProdType(prodType);
          mbProdGroup.setSeqNo(Integer.toString(seqNo));
         ProdToMap.prodGroup(mbProdGroup, "Insert", reqNum);
          seqNo++;
          return seqNo;
     }
     public static void ProdRelationDefineToJson(JSONObject json, String subProdType,String prodType,String reqNum){
          MbProdRelationDefine mbProdRelationDefine = ProdDefineUtil.MbProdRelationDefineByJson(json);
          mbProdRelationDefine.setSubProdType(subProdType);
          mbProdRelationDefine.setProdType(prodType);
         ProdToMap.prodRelationDefine(mbProdRelationDefine, "Insert", reqNum);
     }
     public static void EventLinkToJson(JSONObject json,String reqNum){
          MbEventLink mbEventLink = ProdDefineUtil.MbEventLinkByJson(json);
         ProdToMap.eventLink(mbEventLink, "Insert", reqNum);
     }
}
