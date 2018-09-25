package com.dcits.galaxy.orion.xml;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.galaxy.base.data.BaseRequest;
import com.dcits.orion.base.xml.XmlConvert;
import com.jayway.jsonpath.JsonPath;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by lixbb on 2016/1/13.
 */
public class TestXml {


    public List<Fin99990101In> getNames() {
        return names;
    }

    public void setNames(List<Fin99990101In> names) {
        this.names = names;
    }

    private List<Fin99990101In> names = new ArrayList<>();

    public static void test1() throws Exception {
        Fin99990101In in = new Fin99990101In();
        in.writeValue("sysHead", in.fieldType("sysHead").newInstance());
        in.writeValue("sysHead.serviceCode", "SVC_IN");
        in.writeValue("sysHead.messageType", "1200");
        in.writeValue("sysHead.messageCode", "0101");

        Fin99990101In.Body body = new Fin99990101In.Body();
        List<Fin99990101In.Repay> repayArray = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Fin99990101In.Repay repay = new Fin99990101In.Repay();
            repay.setSchNo("SCHNO" + i);
            repayArray.add(repay);
        }
        in.writeValue("body", body);
        in.writeValue("body.repayArray", repayArray);
        String str = JSON.toJSONString(in);


        System.out.println(str);


        //Fin99990101In in1 = JSON.parseObject(str,Fin99990101In.class);
        Fin99990101In in1 = JSON.toJavaObject(JSON.parseObject(str), Fin99990101In.class);

        JSONObject jsonObject = JSON.parseObject(str);
        Object obj = JsonPath.read(jsonObject, "$.sysHead.messageCode");

        System.out.println(in);

    }

    public static void main(String[] args) throws Exception {


        //XmlConvert xmlConvert = new XmlConvert();
        //Request request = xmlConvert.unpack(text);

        //System.out.println(request);
       /* Document document = DocumentHelper.parseText(text);
        Node node = document.getDocument().selectSingleNode("/service/sys-header/data/struct");
        System.out.println(node);
        System.out.println(node.selectSingleNode("data[@name='MESSAGE_TYPE']/field").getText());*/

       /* BeanInfo beanInfo = Introspector.getBeanInfo(TestXml.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor:propertyDescriptors)
        {
            System.out.println(propertyDescriptor.getName());
            System.out.println(propertyDescriptor.getPropertyType());
        }

        System.out.println(JsonUtils.convertUpperCase("acctNo"));*/
        //test4();

        //System.out.println(new BigDecimal("1E7"));
        //test6();

        //test1();
        //1System.out.println(Double.SIZE);

        //test();
        // String str = "zookeeper://192.168.165.216:2181?backup=192.168.165.217:2181,192.168.165.218:2181";
        // System.out.println(str.replaceAll("zookeeper://","").replaceAll("\\?backup=",","));
        test0();


    }

    public static void test0() throws IllegalAccessException, InstantiationException {

    }

    public static void test() throws Exception {
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
            XmlConvert xmlConvert = new XmlConvert();
            BaseRequest request = xmlConvert.unpack(text);
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

    public static void test2() throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(TestXml.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if (propertyDescriptor.getWriteMethod() != null) {

                System.out.println(propertyDescriptor.getName());
                Class type = (Class) ((ParameterizedType) propertyDescriptor.getReadMethod().getGenericReturnType()).getActualTypeArguments()[0];
                System.out.println(type.getName());
            }
        }

    }


    public static void test4() {
        System.out.println(Boolean.TYPE.getName());
        System.out.println(Boolean.class);
        System.out.println(Character.TYPE);
        System.out.println(Character.class);
        System.out.println(Byte.TYPE);
        System.out.println(Byte.class);
        System.out.println(Short.TYPE);
        System.out.println(Short.class);
        System.out.println(Integer.TYPE);
        System.out.println(Integer.class);
        System.out.println(Long.TYPE);
        System.out.println(Long.class);
        System.out.println(Float.TYPE);
        System.out.println(Float.class);
        System.out.println(Double.TYPE);
        System.out.println(Double.class);
        System.out.println(BigInteger.class);
        System.out.println(BigDecimal.class);
        System.out.println(String.class);
        System.out.println(Date.class);
        System.out.println(java.sql.Date.class);
        System.out.println(Time.class);
        System.out.println(Timestamp.class);
    }

    public static void test5() {
        Document doc = DocumentHelper.createDocument();
        Element service = doc.addElement("service");
        Element sys_header = service.addElement("sys-header");
        Element SYS_HEAD = sys_header.addElement("data").addAttribute("name", "SYS_HEAD");
        Element struct = SYS_HEAD.addElement("struct");


        System.out.println(doc.asXML());
    }

    public static void test6() {
        XmlConvert xmlConvert = new XmlConvert();
        System.out.println(xmlConvert.pack(null, null));

    }
}
