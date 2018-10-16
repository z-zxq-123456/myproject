package com.dcits.ensemble.om.pf.util;

import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pf.bean.DbProdCheckList;
import com.dcits.ensemble.om.pf.bean.DbProdPublish;
import com.dcits.galaxy.base.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ligan on 2017/3/22.
 */
public class ApplyProdUtil {
    private static byte[] jsonDataDui, jsonDataUpd, jsonKey;
    private static String operateType,currentTime,tableFullName,checkText,req_no,keyColumn="",KeyValue="",prodType;
    private static JSONObject oldRecordObj,newRecordObj,key;
    public enum ProdType {
        PROD_MB_PROD_TYPE, PROD_MB_PROD_DEFINE, PROD_MB_EVENT_TYPE, PROD_MB_EVENT_PART, PROD_MB_EVENT_ATTR,PROD_IRL_PROD_INT;
    }


    public static List<DbProdPublish> getProdTypePublishInfo(DbProdCheckList dbProdCheckList){
        List<DbProdPublish> list=new ArrayList<>();
        boolean isFlag=false;
        for(ProdType p:ProdType.values()){
           if(p.name().equals(dbProdCheckList.getTableFullName()))
            {
                isFlag=true;
            }
        }
        if(!isFlag){
            return null;
        }
        commonValue(dbProdCheckList);
        Set<Map.Entry<String, Object>> oldFieldName,newFieldName;
        Set<Map.Entry<String, Object>> keyMap = key.entrySet();
        Set<Map.Entry<String, Object>> columnName=null;
        if(newRecordObj!=null) {
            newFieldName = newRecordObj.entrySet();
            columnName = newFieldName;
        }
        if(oldRecordObj!=null) {
             oldFieldName = oldRecordObj.entrySet();
            if("D".equals(operateType)){
                columnName=oldFieldName;
            }
        }
        getStrKeyValue(dbProdCheckList,keyMap);
        boolean attrValue=false;
        String value="";
        DbProdPublish dbProdPublish=new DbProdPublish();
            for (Map.Entry<String, Object> arrayFiledObject : columnName) {
                String strKey = arrayFiledObject.getKey();
                String oldValue="",newValue="";
                if(keyColumn.equals(strKey)||"COLUMN_STATUS".equals(strKey)||"SEQ_NO".equals(strKey)){
                    continue;
                }
                if(newRecordObj!=null&&newRecordObj.getString(strKey)!=null)
                    newValue=newRecordObj.getString(strKey);
                if(oldRecordObj!=null&&oldRecordObj.getString(strKey)!=null)
                    oldValue = oldRecordObj.getString(strKey);
                if(!oldValue.equals(newValue)||"ASSEMBLE_ID".equals(strKey)) {
                    dbProdPublish.setPublishTime(dbProdCheckList.getPublishTime());
                    dbProdPublish.setCheckOperator(dbProdCheckList.getOperatorCheck());
                    dbProdPublish.setCheckText("变更意见:"+dbProdCheckList.getCheckText().substring(14));
                    dbProdPublish.setOperator(dbProdCheckList.getOperatorCurrentSystem());
                    dbProdPublish.setOperatorTime(dbProdCheckList.getCurrentSystemTime());
                        dbProdPublish.setCheckTime(dbProdCheckList.getCheckTime());
                    dbProdPublish.setProdType(prodType);
                    if(dbProdCheckList.getTableFullName().equals("PROD_MB_EVENT_ATTR")||dbProdCheckList.getTableFullName().equals("PROD_MB_PROD_DEFINE")||dbProdCheckList.getTableFullName().equals("PROD_MB_EVENT_PART")){

                        if("ATTR_VALUE".equals(strKey))
                        {
                            dbProdPublish.setOldValue(oldValue);
                            dbProdPublish.setNewValue(newValue);
                            attrValue=true;
                        }
                        else if(dbProdCheckList.getTableFullName().equals("PROD_MB_EVENT_PART")&&"ATTR_KEY".equals(strKey)&&attrValue==true) {
                            dbProdPublish.setUprightColumn(dbProdCheckList.getTableFullName()+"."+KeyValue);
                            list.add(dbProdPublish);
                            break;
                        }
                    if(attrValue==true&&"ASSEMBLE_ID".equals(strKey)){
                              attrValue=false;
                              if(oldValue!=null&&oldValue.length()>0)
                              dbProdPublish.setUprightColumn(dbProdCheckList.getTableFullName().substring(5)+"."+oldValue);
                              else
                                  dbProdPublish.setUprightColumn(dbProdCheckList.getTableFullName().substring(5)+"."+newValue);
                          } else{
                              continue;
                      }
                      }
                    else {
                        dbProdPublish.setUprightColumn(dbProdCheckList.getTableFullName().substring(5)+"."+strKey);
                        dbProdPublish.setOldValue(oldValue);
                        dbProdPublish.setNewValue(newValue);
                    }
                    if(attrValue==false) {
                        list.add(dbProdPublish);
                        dbProdPublish=new DbProdPublish();
                    }
                }
            }
        return list;
    }
    public static void currentAttr(Map<String,Object> map,Set<Map.Entry<String, Object>> keyMap){

            for(Map.Entry<String, Object> key:keyMap){
                KeyValue = key.getValue().toString();
                keyColumn = key.getKey().toString();
                if ("ASSEMBLE_ID".equals(keyColumn) || "EVENT_TYPE".equals(keyColumn) || "PROD_TYPE".equals(keyColumn))
                    break;
            }
        }

    public static void getStrKeyValue(DbProdCheckList dbProdCheckList,Set<Map.Entry<String, Object>> keyMap ){

        for(Map.Entry<String, Object> key:keyMap){

            keyColumn = key.getKey().toString();
            if("PROD_MB_EVENT_ATTR".equals(dbProdCheckList.getTableFullName())){
                if ("ASSEMBLE_ID".equals(keyColumn) )
                    KeyValue = key.getValue().toString();
                if("EVENT_TYPE".equals(keyColumn))
                    prodType=key.getValue().toString();
            }else if("PROD_MB_PROD_DEFINE".equals(dbProdCheckList.getTableFullName())){
                if ("ASSEMBLE_ID".equals(keyColumn) )
                    KeyValue = key.getValue().toString();
            }else if("PROD_MB_EVENT_PART".equals(dbProdCheckList.getTableFullName()))
            {
                if ("ATTR_KEY".equals(keyColumn) )
                    KeyValue = key.getValue().toString();
                if("EVENT_TYPE".equals(keyColumn))
                    prodType=key.getValue().toString();
            }
            if("PROD_TYPE".equals(keyColumn))
                prodType=key.getValue().toString();
        }
    }
    public static void commonValue(DbProdCheckList dbProdCheckList){
        String strDataDui,strDataUpd,strKey;
        jsonDataDui=(byte[])dbProdCheckList.getDataDui();
        jsonDataUpd=(byte[])dbProdCheckList.getOlddataUpd();
        jsonKey=(byte[])dbProdCheckList.getKeyValue();
        operateType=dbProdCheckList.getOperateType();
        req_no=dbProdCheckList.getReqNo();;
        try {
            if(jsonDataDui!=null) {
                strDataDui = new String(jsonDataDui, "UTF-8");
                newRecordObj = JSONObject.parseObject(strDataDui);
            }
            if(jsonDataUpd!=null) {
                strDataUpd = new String(jsonDataUpd, "UTF-8");
                oldRecordObj  = JSONObject.parseObject(strDataUpd);
            }
            if(jsonKey!=null) {
                strKey = new String(jsonKey, "UTF-8");
                key = JSONObject.parseObject(strKey);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
