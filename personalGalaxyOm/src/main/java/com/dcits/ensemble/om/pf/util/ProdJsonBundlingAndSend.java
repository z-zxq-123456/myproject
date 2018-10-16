package com.dcits.ensemble.om.pf.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.dynamic.web.util.ResourcesUtils;
import com.dcits.ensemble.om.cmc.util.DataPostUtil;
import com.dcits.ensemble.om.pm.util.ParaMeterUtils;
import com.dcits.galaxy.base.exception.GalaxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ligan on 2016/7/28.
 */
public  class ProdJsonBundlingAndSend {

    protected static final Map<String,String> MAP_REQ=new HashMap<>();
    protected static Lock LOCK = new ReentrantLock();
    private static final Logger logger = LoggerFactory.getLogger(ProdJsonBundlingAndSend.class);

    public static boolean ProdJsonBundling(String req,Map<String,String> dataMap )
    {
        if(MAP_REQ.containsKey(dataMap.get("tableName") + req)){
            if (!mergeTbTransaction(req,dataMap)){
                MAP_REQ.put(dataMap.get("tableName")+req,MAP_REQ.get(dataMap.get("tableName")+req)+","+dataMap.get("recordData"));
            }
        }else{
            MAP_REQ.put(dataMap.get("tableName")+req,dataMap.get("recordData"));
        }
        return true;
    }

    /**
     * clearCache
     * @param tables
     * @param reqNum 流水号
     */
    public static void clearCache(List<String> tables, String reqNum){
        for (String tab:tables){
            String key = tab+reqNum;
            if (MAP_REQ.containsKey(key)){
                MAP_REQ.remove(key);
            }
        }
    }

    /**
     * validate 校验格式
     * @param tables
     * @param reqNum
     */
    public static void validate(List<String> tables, String reqNum){
        for (String tab:tables){
            String key = tab+reqNum;
            if (!MAP_REQ.containsKey(key)){
                throw new RuntimeException("未找到 TABLE: ["+tab+ "] 配置信息,保存失败!");
            }
        }
    }

    public static boolean ProdJsonStartOrEnd(String req,HttpServletRequest request,String flag,String[] tableNames,String transActionId){
        StringBuilder jsonList=new StringBuilder();
        if(MAP_REQ.containsKey(req)){
            if("Start".equals(flag)){
                for(String tableName:tableNames){
                    MAP_REQ.remove(req+tableName);
                }
                MAP_REQ.remove(req);
            }else {
                jsonList.append(MAP_REQ.get(req));
            }
        }
        if("Start".equals(flag)){
            jsonList.append("{\n" +
                    "  \"reqNum\": \""+req+"\",\n" +
                    "  \"systemId\": \"PROD\",\n" +
                    "  \"paraData\": [");
            MAP_REQ.put(req,jsonList.toString());
        }
        if ("End".equals(flag)){
            for(String tableName:tableNames){
                if(MAP_REQ.get(tableName+req)!=null) {
                    jsonList.append("{\"tableName\": \"" + tableName + "\",\"recordData\": [");
                    jsonList.append(MAP_REQ.get(tableName + req));
                    jsonList.append("]},");
//                    MAP_REQ.remove(tableName + req);
                }
            }
            MAP_REQ.remove(req);
            jsonList.replace(0, jsonList.length(), jsonList.substring(0, jsonList.length() - 1));
            jsonList.append("]}");
            JSONObject jsonObject=JSON.parseObject(jsonList.toString());
            String userId = ResourcesUtils.getSystemUser(request);
            String ipAddress = ResourcesUtils.getClientIP(request);
            try {
                ParaMeterUtils.addModifyDeleteMutiRecords(jsonObject, userId, ipAddress);
            }catch (GalaxyException e){
                ParaMeterUtils.nullifyDataFromTbs(transActionId, req, "PROD", userId, ipAddress);
                throw e;
            }
        }
        return true;
    }

    /**
     * mergTransaction
     * @param req
     * @param dataMap
     * @return
     */
    private static boolean mergeTbTransaction(String req,Map<String,String> dataMap){
        try {
            LOCK.tryLock(2,TimeUnit.SECONDS);
            String operType = (String) JSON.parseObject(dataMap.get("recordData")).get("operType");
            String key = dataMap.get("tableName")+req;
            String compareValue = MAP_REQ.get(key);
            String resultStr;
            State optStat = new State(false,true);
            boolean resFlag = false;
            switch (operType){
                case DataPostUtil.Delete:
                    resultStr = doDeleteValid(compareValue,dataMap.get("recordData"),optStat);
                    resFlag = afterProcessFlag(key,resultStr,optStat);
                    break;
                case DataPostUtil.UPDATE:
                    resultStr = doUpdateValid(compareValue,dataMap.get("recordData"),optStat);
                    resFlag = afterProcessFlag(key,resultStr,optStat);
                    break;
                default:
                    break;
            }
            return resFlag ;
        }catch (Exception e){
            logger.error ("error in merge rule,please check the input data! error: "+e.getMessage());
        }finally {
            LOCK.unlock();
        }
        return false;
    }

    /**
     * doDeleteValid
     * @param compareValue 缓存中的记录
     * @param records 新增记录
     * @return boolean
     */
    private static String doDeleteValid(String compareValue,String records,State optStat){

        String strCompare =  DataPostUtil.fillCloseStr(compareValue);
        JSONArray existsArray = JSON.parseArray(strCompare);
        JSONObject newRecords = JSON.parseObject(records);
        JSONObject keyVal = newRecords.getJSONObject("key");
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < existsArray.size(); i ++){
            JSONObject temp = existsArray.getJSONObject(i);
            JSONObject pkValue = temp.getJSONObject("key");
            if (compareJsonObject(keyVal,pkValue)){
                optStat.setIsChanged(true);
            }else {
                jsonArray.add(temp);
            }
        }
        optStat.setSame(false);
        return JSONArray.toJSONString(jsonArray);
    }

    /**
     * doUpdateValid
     * @param compareValue 缓存中的记录
     * @param records 新增记录
     * @return boolean
     */
    private static String doUpdateValid(String compareValue,String records,State optStat){

        String strCompare =  DataPostUtil.fillCloseStr(compareValue);
        JSONArray existsArray = JSON.parseArray(strCompare);
        JSONObject newRecords = JSON.parseObject(records);
        JSONObject keyVal = newRecords.getJSONObject("key");
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < existsArray.size(); i ++){
            JSONObject temp = existsArray.getJSONObject(i);
            JSONObject pkValue = temp.getJSONObject("key");
            int index = compareJsonObjectWithInt(keyVal,pkValue);
            if (index == -1){
                jsonArray.add(temp);
                optStat.setSame(false);
            }else {
                if (!compareJsonObject(temp.getJSONObject("general"),newRecords.getJSONObject("general"))){
                    temp.put("general",newRecords.get("general"));
                    jsonArray.add(temp);
                    optStat.setSame(false);
                    optStat.setIsChanged(true);
                }else {
                    optStat.setSame(true);
                }
            }
        }
        return JSONArray.toJSONString(jsonArray);
    }

    /**
     * 比较对象
     * @param obj1
     * @param obj2
     * @return false  对象不一致
     */
    private static Boolean compareJsonObject(JSONObject obj1,JSONObject obj2){

        boolean flag = true;
        Set keySet = obj1.keySet();
        for (Object key:keySet){
            if (!obj1.get(key).equals(obj2.get(key))){
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * compareJsonObjectWithInt
     * @param obj1
     * @param obj2
     * @return int 索引
     */
    private static int compareJsonObjectWithInt(JSONObject obj1,JSONObject obj2){
        if (obj1== null && obj2 == null){
            return 0;
        }
        Set keySet = obj1.keySet();
        Iterator iterator = keySet.iterator();
        while (iterator.hasNext()){
            String key = (String) iterator.next();
            if (!obj1.get(key).equals(obj2.get(key))){
                return -1;
            }
        }
        return 0;
    }

    /**
     * afterProcessFlag
     * @param key
     * @param resultStr
     * @param optStat
     * @return
     */
    private static Boolean afterProcessFlag(String key,String resultStr,State optStat){
        boolean skipNext = false;
        if (optStat.isChanged && !optStat.isSame){
            if (resultStr.equals("[]")){
                MAP_REQ.remove(key);
                skipNext = true;
            }else {
                MAP_REQ.put(key,DataPostUtil.removeCloseStr(resultStr));
                skipNext = true;
            }
        }
        if (optStat.isSame){
            skipNext = true;
        }
        return skipNext;
    }

    public static class State {
        private boolean isChanged;
        private boolean isSame;

        public State(boolean isChanged) {
            this.isChanged = isChanged;
        }

        public State(boolean isChanged, boolean isSame) {
            this.isChanged = isChanged;
            this.isSame = isSame;
        }

        public boolean isSame() {
            return isSame;
        }

        public void setSame(boolean same) {
            isSame = same;
        }

        public boolean isIsChanged() {
            return isChanged;
        }
        public void setIsChanged(boolean isChanged) {
            this.isChanged = isChanged;
        }
    }

}
