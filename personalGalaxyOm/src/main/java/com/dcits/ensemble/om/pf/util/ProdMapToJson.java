package com.dcits.ensemble.om.pf.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ligan on 2016/9/23.
 */
public class ProdMapToJson {
    public static Map<String,String> MapToJson(Map<String,Object> map){
        Map<String,String> dataMap=new HashMap<>();
        String[] keyList = (String[]) map.get("keyList");
        String[] genList = (String[]) map.get("genList");
        StringBuilder recordData=new StringBuilder();
        recordData.append("{\"key\": {");
        for (String key : keyList) {
            if(map.get(key)!=null) {
                recordData.append("\"" + key + "\":\"" + map.get(key) + "\",");
            }
        }
        if(recordData.toString().endsWith(",")) {
            recordData.replace(0, recordData.length(), recordData.substring(0, recordData.length() - 1));
        }
        recordData.append("},");
        if (genList != null &&!"Delete".equals(map.get("operType"))) {
            recordData.append("\"general\": {");
            for (String gen : genList) {
                if(map.get(gen)!=null)
                    recordData.append("\"" + gen + "\":\"" + map.get(gen) + "\",");
            }
        if(recordData.toString().endsWith(",")) {
            recordData.replace(0, recordData.length(), recordData.substring(0, recordData.length() - 1));
        }
        recordData.append("},");
        }
        recordData.append("\"operType\": \"" + map.get("operType") + "\"}");
        dataMap.put("recordData",recordData.toString());
        dataMap.put("tableName",map.get("tableName").toString());
        return dataMap;
    }

}
