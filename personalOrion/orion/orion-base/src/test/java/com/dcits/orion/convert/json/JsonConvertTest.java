package com.dcits.orion.convert.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.galaxy.base.data.BaseRequest;
import com.dcits.galaxy.base.util.ClassLoaderUtils;
import com.dcits.galaxy.base.util.FileUtils;
import com.dcits.galaxy.base.util.JsonUtils;
import com.jayway.jsonpath.JsonPath;

import junit.framework.TestCase;

/**
 * Created by Tim on 2016/6/12.
 */
public class JsonConvertTest extends TestCase {
    String jsonStr;

    @Override
    protected void setUp() {
        String filePath = ClassLoaderUtils.getResource("test.json").getFile();
        jsonStr = FileUtils.readFile(filePath);
        jsonStr = JsonUtils.convertMsg(jsonStr, JsonUtils.HUMP_TYPE);
    }

    public void testConverMsg() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            JsonUtils.convertMsg(jsonStr, JsonUtils.HUMP_TYPE);
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    public void testUnpack() throws Exception {
        long start = System.currentTimeMillis();
        Non12009100In req;
        for (int i = 0; i < 100000; i++) {
            req = (Non12009100In) unpack(jsonStr);
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    public void testUnpack1() throws Exception {
        long start = System.currentTimeMillis();
        Non12009100In req;
        for (int i = 0; i < 100000; i++) {
            req = (Non12009100In) unpack1(jsonStr);
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    public BaseRequest unpack(String msg) {
//        JSONObject jsonObject = JSON.parseObject(msg);
//        String serviceCode = jsonObject.getJSONObject("sysHead").getString("serviceCode");
//        String messageType = jsonObject.getJSONObject("sysHead").getString("messageType");
//        String messageCode = jsonObject.getJSONObject("sysHead").getString("messageCode");
        String serviceCode = JsonPath.read(msg, "$.sysHead.serviceCode");
        String messageType = JsonPath.read(msg, "$.sysHead.messageType");
        String messageCode = JsonPath.read(msg, "$.sysHead.messageCode");
        // System.out.println(serviceCode+"-"+messageType+"-"+messageCode);
        BaseRequest request = JSON.parseObject(msg, Non12009100In.class);
        return request;
    }

    public BaseRequest unpack1(String msg) {
        JSONObject jsonObject = JSON.parseObject(msg);
        String serviceCode = jsonObject.getJSONObject("sysHead").getString("serviceCode");
        String messageType = jsonObject.getJSONObject("sysHead").getString("messageType");
        String messageCode = jsonObject.getJSONObject("sysHead").getString("messageCode");
//        String serviceCode = JsonPath.read(msg, "$.sysHead.serviceCode");
//        String messageType = JsonPath.read(msg, "$.sysHead.messageType");
//        String messageCode = JsonPath.read(msg, "$.sysHead.messageCode");
        // System.out.println(serviceCode+"-"+messageType+"-"+messageCode);
        BaseRequest request = JSON.parseObject(msg, Non12009100In.class);
        return request;
    }
}