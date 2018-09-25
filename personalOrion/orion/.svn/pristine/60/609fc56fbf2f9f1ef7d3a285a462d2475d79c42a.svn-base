package com.dcits.galaxy.orion.xml;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.galaxy.base.data.*;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.BeanUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.junit.TestBase;
import com.dcits.galaxy.orion.encrypt.Fin99990101Out;
import com.dcits.orion.base.xml.XmlConvert;
import com.dcits.orion.core.antirepeate.AntiRepeatUtil;
import com.dcits.orion.core.util.BusinessUtils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import java.io.*;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lixbb on 2016/1/14.
 */
public class TestXml2 extends TestBase {

    public void testUnpack() throws Exception {
        File file = new File(new TestXml().getClass().getClassLoader().getResource("").getPath() + "compositeData.xml");
        BufferedReader bufferedReader = null;
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            InputStreamReader read = new InputStreamReader(in);
            bufferedReader = new BufferedReader(read);
            String text = "";
            String line;
            StringBuilder sb = new StringBuilder();
            sb.append(text);
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(" ").append(line);
            }
            text = sb.toString();
            if (text.trim().startsWith("<?xml version=")) {
                text = text.split(">", 2)[1];
            }
            XmlConvert xmlConvert = SpringApplicationContext.getContext().getBean(XmlConvert.class);
            Document document = DocumentHelper.parseText(text);
            BaseRequest request = xmlConvert.unpack(document, Fin99990101In.class);
            System.out.println(request);
            // System.out.println(xmlConvert.requestToXml(request));
            List list = BeanUtils.pickBeansByProperty(request, "ddAllocation", "ddRepayXrate");
            for (Object obj : list) {
                Object o = BeanUtils.getFieldValue(obj, "ddAllocation");
                BeanUtils.setFieldValue(obj, "ddRepayXrate", "" + o);
            }

            System.out.println("=======================================================================================");
            System.out.println(request);
        } finally {
            if (null != in) {
                in.close();
            }
            if (null != bufferedReader) {
                bufferedReader.close();
            }
        }
    }

    public void testPack() throws Exception {
        Fin99990101In in = new Fin99990101In();
        Fin99990101Out out = new Fin99990101Out();
        out.setPassword("toEncrypt");
        out.setAcctName("acctName:lixb");
        List<Fin99990101Out.OutItem> items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Fin99990101Out.OutItem item = new Fin99990101Out.OutItem();
            item.setPassword("response password in items" + i);
            items.add(item);
            List<Fin99990101Out.OutAcct> accts = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Fin99990101Out.OutAcct acct = new Fin99990101Out.OutAcct();
                acct.setAcctNo("acct no in Array and array");
                acct.setAmount(j * 100.95);
                accts.add(acct);
            }
            item.setAccts(accts);
        }
        out.setItems(items);
        BeanResult br = new BeanResult(out);
        Results rs = new Results();
        rs.addResult(new Result("999999", "错误测试"));
        br.setRs(rs);
        ISysHead sysHead = in.fieldTypeNewInstance("sysHead");
        sysHead.writeValue("serviceCode", "SVR_FINANCIAL");
        sysHead.writeValue("messageType", "1200");
        sysHead.writeValue("messageCode", "6002");
        XmlConvert xmlConvert = SpringApplicationContext.getContext().getBean(XmlConvert.class);
        Document document = DocumentHelper.parseText(xmlConvert.pack(sysHead, br));
        //System.out.println(document.asXML());

        BeanResult beanResult = xmlConvert.xmlToBeanResult(document.asXML(), in, Fin99990101Out.class);
        System.out.println(beanResult);
    }

    public void testJetty() throws Exception {
        String url = "http://192.168.166.97:9001/galaxyxml";
        File file = new File(new TestXml().getClass().getClassLoader().getResource("").getPath() + "compositeData.xml");
        BufferedReader bufferedReader = null;
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            InputStreamReader read = new InputStreamReader(in);
            bufferedReader = new BufferedReader(read);
            String text = "";
            String line;
            StringBuilder sb = new StringBuilder();
            sb.append(text);
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(" ").append(line);
            }
            text = sb.toString();
            System.out.println(doSubmit(url, text));
        } finally {
            if (null != in) {
                in.close();
            }
            if (null != bufferedReader) {
                bufferedReader.close();
            }
        }
    }

    public String doSubmit(String url, String str) {
        PostMethod pm = null;
        String out = null;
        ByteArrayInputStream bais = null;
        try {
            HttpClient hc = new HttpClient();
            pm = new PostMethod(url);
            // hc.getHttpConnectionManager().getParams().setConnectionTimeout(5 * 1000);
            pm.getParams().setSoTimeout(60 * 1000);
            bais = new ByteArrayInputStream(str.getBytes("UTF-8"));
            pm.setRequestEntity(new InputStreamRequestEntity(bais));
            int status = hc.executeMethod(pm);
            if (status != 200) {
                throw new GalaxyException("后台服务端执行出错：" + status);
            } else {
                byte[] b_out = pm.getResponseBody();
                out = new String(b_out, "UTF-8");
            }
        } catch (ConnectException e) {
            throw new GalaxyException("[" + url + "]后台服务未启动");
        } catch (SocketTimeoutException e) {
            throw new GalaxyException("获取响应超时");
        } catch (GalaxyException e) {
            throw e;
        } catch (Throwable t) {
            throw new RuntimeException(t);
        } finally {
            try {
                if (null != bais)
                    bais.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (null != pm) {
                pm.releaseConnection();
            }
        }
        return out;
    }

    public void testStream() throws Exception {
        BeanResult[] beanResults = new BeanResult[2];
        beanResults[0] = new BeanResult("000000", "SUCCESS");
        beanResults[1] = new BeanResult("111111", "SUCCESS");
        byte[] bytes = BusinessUtils.serialize(beanResults[0]);
        BeanResult[] beanResults1 = BusinessUtils.deserialize(bytes);
        System.out.println(beanResults1[0]);
        System.out.println(beanResults1[1]);
        //System.out.println();
    }

    public void testXmlToJSONObject() throws Exception {
        File file = new File(new TestXml().getClass().getClassLoader().getResource("").getPath() + "compositeData.xml");
        BufferedReader bufferedReader = null;
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            InputStreamReader read = new InputStreamReader(in);
            bufferedReader = new BufferedReader(read);
            String text = "";
            String line;
            StringBuilder sb = new StringBuilder();
            sb.append(text);
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(" ").append(line);
            }
            text = sb.toString();
            XmlConvert xmlConvert = SpringApplicationContext.getContext().getBean(XmlConvert.class);
            JSONObject jsonObject = xmlConvert.xmlToJsonObject(text);
            String jsonXMl = xmlConvert.jsonObjectToXml(jsonObject);
            Map map = xmlConvert.xmlToMap(jsonXMl);
            String mapXml = xmlConvert.mapToXml(map);
            System.out.println(mapXml);
        } finally {
            if (null != in) {
                in.close();
            }
            if (null != bufferedReader) {
                bufferedReader.close();
            }
        }
    }

    public void testJsonObject() {
        Fin99990101Out out = new Fin99990101Out();
        out.setPassword("toEncrypt");
        out.setAcctName("acctName:lixb");
        List<Fin99990101Out.OutItem> items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Fin99990101Out.OutItem item = new Fin99990101Out.OutItem();
            item.setPassword("response password in items" + i);
            items.add(item);
            List<Fin99990101Out.OutAcct> accts = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Fin99990101Out.OutAcct acct = new Fin99990101Out.OutAcct();
                acct.setAcctNo("acct no in Array and array");
                acct.setAmount(j * 100.95);
                accts.add(acct);
            }
            item.setAccts(accts);
        }
        out.setItems(items);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(out);
        System.out.println(jsonObject);

    }

    public void testAntiRepeate() {
        AntiRepeatUtil antiRepeatUtil = SpringApplicationContext.getContext().getBean(AntiRepeatUtil.class);
        antiRepeatUtil.process(null);
    }

    public void testThread() throws InterruptedException {
        Thread.sleep(Integer.MAX_VALUE);
    }
}
