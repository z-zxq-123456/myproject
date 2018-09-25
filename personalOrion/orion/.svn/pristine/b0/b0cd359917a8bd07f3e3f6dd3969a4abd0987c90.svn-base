package com.dcits.orion.stria.test.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.galaxy.base.util.ClassLoaderUtils;
import com.dcits.galaxy.base.util.FileUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Tim on 2015/6/5.
 */
public class TestJson {

    public static void main(String arg[]) throws IOException{
            String filePath = ClassLoaderUtils.getResource("flow.json")
                    .getFile();

        JSONArray jsonArray = (JSONArray) JSON.parse(FileUtils.readFile(filePath));


        for (JSONObject j :jsonArray.toArray(new JSONObject[]{})){
            JSONObject c =  j.getJSONObject("content");
            String flowid = c.getString("flowid");
            JSONObject n = c.getJSONObject("nodes");

            for (Map.Entry entry :n.entrySet()){
                String key = (String) entry.getKey();
                JSONObject no = (JSONObject) entry.getValue();

                String sourceType = no.getString("sourceType");
                if (null != sourceType && !"".equals(sourceType)){
                    System.out.println("update stria_flow_nodes set SOURCE_TYPE = '"+sourceType+"' where flowid = '"+ flowid +"' and id='"+key+"';");
                }
            }


            n.getString("");
        }
    }
}
