package com.dcits.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pm.common.adapter.CmcOMAdapter;
import com.dcits.galaxy.base.util.ClassLoaderUtils;
import com.dcits.galaxy.base.util.FileUtils;
import com.dcits.galaxy.base.util.JsonUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class CmcTest {

    public static void main(String[] args) {
        String filePath = ClassLoaderUtils.getResource("sendMessage.json").getFile();
        System.out.println("filePath: "+filePath);
        JSONObject jsonObject = JSON.parseObject(FileUtils.readFile(filePath));
        String str = jsonObject.toJSONString();
        System.out.println("JettyConnectorTest=====>"+ JsonUtils.formatJson(str));
        long start = System.currentTimeMillis();
        int i = -1;
        int count = 1;
        int success = 0;
        int fails = 0;
        int unknow = 0;
        try {
            for (i = 0; i < count; i++) {
                HttpClient hc = new HttpClient();
                PostMethod pm = new PostMethod("http://localhost:18889/ensemble-om/omtest");
                try {
                    pm.getParams().setSoTimeout(10 * 10000);

                    ByteArrayInputStream bais = new ByteArrayInputStream(
                            str.getBytes("UTF-8"));
                    pm.setRequestEntity(new InputStreamRequestEntity(bais));
                    int status = hc.executeMethod(pm);
                    if (status != 200) {
                        System.out.println("err...");
                    } else {
                        byte[] b_out = pm.getResponseBody();
                        String out = new String(b_out, "UTF-8");
                        if (count == 1)
                            System.out.println(JsonUtils.formatJson(out));
                        JSONObject outJson = JSON.parseObject(out);
                        if (outJson.containsKey("SYS_HEAD")
                                && outJson.getJSONObject("SYS_HEAD")
                                .containsKey("RET_STATUS")
                                && "S".equals(outJson.getJSONObject("SYS_HEAD")
                                .getString("RET_STATUS"))) {
                            success++;
                            System.out.println("第" + (i + 1) + "笔[SUCCESS]");
                        } else {
                            System.out.println("第"
                                    + (i + 1)
                                    + "笔"
                                    + JsonUtils
                                    .formatJson(outJson
                                            .getJSONObject("SYS_HEAD")
                                            .getJSONArray("RET")
                                            .toJSONString()));
                            fails++;
                        }
                    }
                } catch (Throwable t) {
                    t.printStackTrace();
                    unknow++;
                    try {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        PrintStream ps = new PrintStream(baos);

                        t.printStackTrace(ps);

                        baos.close();
                        ps.close();

                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                } finally {
                    pm.releaseConnection();
                }
            }
        } finally {
            long end = System.currentTimeMillis();
            System.out.println("交易笔数：" + i + " \n成功：" + success + " \n失败："
                    + fails + " \n未知：" + unknow + " \n平均时间：" + (end - start) / i);
        }
    }

}
