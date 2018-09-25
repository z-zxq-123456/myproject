package com.dcits.orion.base.xml;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.orion.api.Convert;
import com.dcits.orion.api.IParseString;
import com.dcits.orion.base.util.ConvertUtils;
import com.dcits.orion.base.AbstractConvert;
import com.dcits.galaxy.base.data.*;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.JsonUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * Created by lixbb on 2016/1/13.
 */
@Repository
public class XmlConvert extends AbstractConvert implements Convert<String>, InitializingBean {


    private static final Logger logger = LoggerFactory
            .getLogger(XmlConvert.class);

    public org.springframework.core.io.Resource getConfigLocation() {
        return configLocation;
    }

    public void setConfigLocation(org.springframework.core.io.Resource configLocation) {
        this.configLocation = configLocation;
    }


    /**
     * 组包，将公共数据结构中的数据组织为特定的报文结构
     *
     * @param sysHead
     * @param br
     * @return
     */
    @Override
    public String pack(ISysHead sysHead, BeanResult br) {
        sysHead = null == sysHead ? new SysHead() : sysHead;
        ConvertUtils.outServiceCode(sysHead);
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("service");
        Element sys_header = root.addElement("sys-header").addElement("data").addAttribute("name", "SYS_HEAD").addElement("struct");
        Element app_header = root.addElement("app-header").addElement("data").addAttribute("name", "APP_HEAD").addElement("struct");

        packSysHead(sys_header, sysHead, br);
        packAppHead(app_header, sysHead, br);
        packBody(root, br);
        debugDocument(document);
        return documentToXml(document);
    }

    private String documentToXml(Document document) {
        debugDocument(document);
        return document.asXML();
    }

    private void debugDocument(Document document)
    {
        if (logger.isDebugEnabled()) {
            StringWriter stringWriter = new StringWriter();
            OutputFormat xmlFormat = new OutputFormat();
            xmlFormat.setNewlines(true);
            xmlFormat.setIndent(true);
            xmlFormat.setIndent("    ");
            XMLWriter xmlWriter = new XMLWriter(stringWriter, xmlFormat);
            try {
                xmlWriter.write(document);
                xmlWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            logger.debug(stringWriter.toString());
        }

    }

    private void packSysHead(Element sys_header, ISysHead sysHead, BeanResult br) {
        packField(sys_header, sysHead);
        /*sys_header.addElement("RET_STATUS").
                addElement("field").
                addAttribute("type", "string").
                addAttribute("length", "1").addText(br.getRetStatus());*/

        sys_header.addElement("data").addAttribute("name", "RET_STATUS").addElement("field").
                addAttribute("type", "string").
                addAttribute("length", "1").addText(br.getRetStatus());
        packField(sys_header, br.getRs());


    }

    private void packAppHead(Element app_header, ISysHead sysHead, BeanResult br) {

        if (br.getAppHead() != null) {
            packField(app_header, br.getAppHead());
        }
    }

    private void packBody(Element root, BeanResult br) {
        if (br.getResponse() != null) {
            Element body = root.addElement("body");
            packField(body, br.getResponse());
        }
    }

    private void packField(Element element, Object object) {
        if (object == null)
            return;
        if (object instanceof Map) {
            Map<String, Object> map = (Map) object;
            for (String key : map.keySet()) {
                Object fieldValue = map.get(key);
                if (fieldValue != null) {
                    if (fieldValue instanceof List) {
                        Element arrayElement = element.addElement("data").
                                addAttribute("name", key).addElement("array");
                        List<Map> array = (List) fieldValue;
                        for (Map struct : array) {
                            Element subElement = arrayElement.addElement("struct");
                            packField(subElement, struct);
                        }
                    } else if (fieldValue instanceof Map) {
                        Element subElement = element.addElement("data").addAttribute("name", key).addElement("struct");
                        packField(subElement, fieldValue);
                    } else if (parseConfig.containsKey(fieldValue.getClass().getName())) {
                        IParseString parseString = (IParseString) SpringApplicationContext.getContext().getBean(parseConfig.get(fieldValue.getClass().getName()));
                        if (fieldValue != null) {
                            element.addElement("data").
                                    addAttribute("name", key).
                                    addElement("field").addAttribute("type", parseString.getType(fieldValue.getClass())).
                                    addAttribute("length", parseString.getLength(fieldValue) + "").
                                    addText(parseString.getString(fieldValue));
                        }
                    }
                }
            }
        } else {

            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    if (propertyDescriptor.getReadMethod() != null && propertyDescriptor.getWriteMethod() != null) {
                        String PropertyTypeName = propertyDescriptor.getPropertyType().getName();
                        if (propertyDescriptor.getPropertyType() == List.class) {
                            Element arrayElement = element.addElement("data").
                                    addAttribute("name", JsonUtils.convertUpperCase(propertyDescriptor.getName())).addElement("array");
                            List list = (List) propertyDescriptor.getReadMethod().invoke(object);
                            if (list != null) {
                                for (Object subOjb : list) {
                                    Element subElement = arrayElement.addElement("struct");
                                    packField(subElement, subOjb);
                                }
                            }

                        } else if (parseConfig.containsKey(PropertyTypeName)) {

                            IParseString parseString = (IParseString) SpringApplicationContext.getContext().getBean(parseConfig.get(PropertyTypeName));
                            Object fieldValue = propertyDescriptor.getReadMethod().invoke(object);
                            if (fieldValue != null) {
                                element.addElement("data").
                                        addAttribute("name", JsonUtils.convertUpperCase(propertyDescriptor.getName())).
                                        addElement("field").addAttribute("type", parseString.getType(propertyDescriptor.getPropertyType())).
                                        addAttribute("length", parseString.getLength(fieldValue) + "").
                                        addText(parseString.getString(fieldValue));
                            }
                        } else {
                            Object subOjb = propertyDescriptor.getReadMethod().invoke(object);
                            Element subElement = element.addElement("data").addAttribute("name", JsonUtils.convertUpperCase(propertyDescriptor.getName())).addElement("struct");
                            packField(subElement, subOjb);
                        }

                    }
                }
            } catch (Exception e) {
                throw new GalaxyException(e);
            }
        }


    }


    private Document getDocument(String msg) {
        if (msg.trim().startsWith("<?xml version=")) {
            msg = msg.split(">", 2)[1];
        }
        Document document = null;
        try {
            document = DocumentHelper.parseText(msg);
        } catch (Exception e) {
            throw new GalaxyException(e);
        }
        return document;
    }

    public BaseRequest unpack(String msg) {
        BaseRequest request = null;
        try {

            Map requestMap = xmlToMap(msg);
            Map sysHead = (Map) requestMap.get("SYS_HEAD");
            String serviceCode = (String) sysHead.get("SERVICE_CODE");
            String messageType = (String) sysHead.get("MESSAGE_TYPE");
            String messageCode = (String) sysHead.get("MESSAGE_CODE");
            Class<? extends BaseRequest> modelClazz = getModelClazz(serviceCode, messageType, messageCode);
            String strMsg = JSON.toJSONString(requestMap);
            strMsg = JsonUtils.convertMsg(strMsg, JsonUtils.HUMP_TYPE);
            request = JSON.parseObject(strMsg, modelClazz);
            afterUnpack(request);
        } catch (Exception e) {
            throw new GalaxyException(e);
        }
        return request;
    }

    public BaseRequest unpack(Document document, Class<? extends BaseRequest> modelClazz) throws Exception {
        BaseRequest request = modelClazz.newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(modelClazz);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if (propertyDescriptor.getName().equals("sysHead")) {
                unpack(request, propertyDescriptor, document.selectSingleNode("/service/sys-header"));
            } else if (propertyDescriptor.getName().equals("appHead")) {
                unpack(request, propertyDescriptor, document.selectSingleNode("/service/app-header"));

            } else if (propertyDescriptor.getName().equals("localHead")) {
                unpack(request, propertyDescriptor, document.selectSingleNode("/service/local-header"));

            } else if (propertyDescriptor.getName().equals("body")) {
                Object body = propertyDescriptor.getPropertyType().newInstance();
                propertyDescriptor.getWriteMethod().invoke(request, body);
                unpack(body, document.selectSingleNode("/service/body"));
            }

        }
        return request;
    }

    private void unpack(Object obj, PropertyDescriptor propertyDescriptor, Node document) throws Exception {


        if (propertyDescriptor.getWriteMethod() == null || propertyDescriptor.getReadMethod() == null)
            return;
        if (document == null)
            return;


        if (propertyDescriptor.getPropertyType() == List.class) {

            Class type = (Class) ((ParameterizedType) propertyDescriptor.getReadMethod().getGenericReturnType()).getActualTypeArguments()[0];
            List<Node> nodes = document.selectNodes("array/struct");
            if (nodes != null) {
                List list = new ArrayList();
                for (Node node : nodes) {
                    Object subOjb = type.newInstance();
                    list.add(subOjb);
                    unpack(subOjb, node);
                }
                propertyDescriptor.getWriteMethod().invoke(obj, list);

            }

        } else if (parseConfig.containsKey(propertyDescriptor.getPropertyType().getName())) {
            Node node = document.selectSingleNode("field");
            if (node != null) {
                String beanId = parseConfig.get(propertyDescriptor.getPropertyType().getName());
                IParseString parseString = (IParseString) SpringApplicationContext.getContext().getBean(beanId);
                Object object = parseString.parse(propertyDescriptor.getPropertyType(), node.getText());
                if (object != null)
                    propertyDescriptor.getWriteMethod().invoke(obj, object);
            }


        } else {
            Object subOjb = propertyDescriptor.getPropertyType().newInstance();
            propertyDescriptor.getWriteMethod().invoke(obj, subOjb);
            Node subDocument = document.selectSingleNode("data[@name='" + JsonUtils.convertUpperCase(propertyDescriptor.getName()) + "']/struct");
            unpack(subOjb, subDocument);
        }
    }

    private void unpack(Object obj, Node document) throws Exception {

        if (document == null)
            return;

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {

            if (propertyDescriptor.getWriteMethod() != null && propertyDescriptor.getReadMethod() != null) {
                Node subDocument = document.selectSingleNode("data[@name='" + JsonUtils.convertUpperCase(propertyDescriptor.getName()) + "']");
                unpack(obj, propertyDescriptor, subDocument);
            }

        }
    }

    public String requestToXml(BaseRequest request) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("service");
        Element sys_header = root.addElement("sys-header").addElement("data").addAttribute("name", "SYS_HEAD").addElement("struct");
        Element app_header = root.addElement("app-header").addElement("data").addAttribute("name", "APP_HEAD").addElement("struct");
        Element local_header = root.addElement("local-header").addElement("data").addAttribute("name", "LOCAL_HEAD").addElement("struct");
        Element elementBody = root.addElement("body");
        packField(sys_header, request.getSysHead());
        packField(app_header, request.getAppHead());
        packField(local_header, request.getLocalHead());
        try {
            Object body = request.readValue("body");
            packField(elementBody, body);
        } catch (Exception e) {
            if (logger.isInfoEnabled())
                logger.info(request.getClass().getName() + " has no body!!");

        }
        return documentToXml(document);
    }

    public BeanResult xmlToBeanResult(String inMsg, BaseRequest request, Class<? extends BaseResponse> responseClazz) throws Exception {
        IAppHead appHead = (IAppHead) request.fieldType("appHead").newInstance();
        BaseResponse response = null;
        Document document = getDocument(inMsg);
        Node body = document.selectSingleNode("/service/body");
        if (body != null) {
            response = responseClazz.newInstance();
            unpack(response, body);
        }
        unpack(appHead, document.selectSingleNode("/service/app-header/data[@name='APP_HEAD']/struct"));
        BeanResult beanResult = new BeanResult(response, appHead);
        Results rs = new Results();
        unpack(rs, document.selectSingleNode("/service/sys-header/data[@name='SYS_HEAD']/struct"));
        beanResult.setRs(rs);
        return beanResult;
    }

    public String jsonObjectToXml(JSONObject jsonObject) {
        return mapToXml(jsonObject);
    }

    public JSONObject xmlToJsonObject(String inMsg) {
        return (JSONObject) xmlToMap(inMsg, JSONObject.class);
    }

    public void nodeToMap(Node node, Map map) {
        if (node == null)
            return;
        List<Element> nodes = node.selectNodes("data");
        for (Element data : nodes) {
            Element fieldArrayElement = (Element) data.elements().get(0);
            String fieldArray = fieldArrayElement.getName();
            if ("array".equals(fieldArray)) {

                List<Element> array = data.selectNodes("array/struct");
                if (array != null) {
                    List<Map> structs = new ArrayList();
                    for (Element struct : array) {
                        Map jStruct = null;
                        if (map instanceof HashMap) {
                            jStruct = new HashMap();
                        } else if (map instanceof JSONObject) {
                            jStruct = new JSONObject();
                        }
                        nodeToMap(struct, jStruct);
                        structs.add(jStruct);
                    }
                    map.put(data.attributeValue("name"), structs);
                }

            } else if ("field".equals(fieldArray)) {
                    map.put(data.attributeValue("name"), fieldArrayElement.getText());
            }
        }
    }

    public String mapToXml(Map map) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("service");
        Element sys_header = root.addElement("sys-header").addElement("data").addAttribute("name", "SYS_HEAD").addElement("struct");
        Element app_header = root.addElement("app-header").addElement("data").addAttribute("name", "APP_HEAD").addElement("struct");
        Element local_header = root.addElement("local-header").addElement("data").addAttribute("name", "LOCAL_HEAD").addElement("struct");
        Element elementBody = root.addElement("body");
        packField(sys_header, map.get("SYS_HEAD"));
        packField(app_header, map.get("APP_HEAD"));
        packField(local_header, map.get("LOCAL_HEAD"));
        packField(elementBody, map.get("BODY"));
        return documentToXml(document);
    }

    public Map xmlToMap(String inMsg) {
        return xmlToMap(inMsg, HashMap.class);
    }

    private Map xmlToMap(String inMsg, Class<? extends Map> clazz) {
        Document document = getDocument(inMsg);
        Node body = document.selectSingleNode("/service/body");
        Node appHead = document.selectSingleNode("/service/app-header/data[@name='APP_HEAD']/struct");
        Node sysHead = document.selectSingleNode("/service/sys-header/data[@name='SYS_HEAD']/struct");
        Node localHead = document.selectSingleNode("/service/sys-header/data[@name='LOCAL_HEAD']/struct");
        Map<String, Map> map = getMap(clazz);
        map.put("BODY", getMap(clazz));
        nodeToMap(body, map.get("BODY"));
        if (appHead != null)
        {
            map.put("APP_HEAD", getMap(clazz));
            nodeToMap(appHead, map.get("APP_HEAD"));
        }

        if (localHead != null)
        {
            map.put("LOCAL_HEAD", getMap(clazz));
            nodeToMap(localHead, map.get("LOCAL_HEAD"));

        }
        map.put("SYS_HEAD", getMap(clazz));
        nodeToMap(sysHead, map.get("SYS_HEAD"));

        return map;
    }

    private Map getMap(Class<? extends Map> clazz) {
        Map map = null;
        if (clazz == HashMap.class)
            map = new HashMap();
        else if (clazz == JSONObject.class)
            map = new JSONObject();
        return map;
    }
}
