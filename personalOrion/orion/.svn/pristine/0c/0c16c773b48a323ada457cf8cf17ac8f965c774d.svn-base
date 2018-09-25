package com.dcits.orion.core.encrypt;

import com.dcits.orion.api.IField2EncryptDecrypt;
import com.dcits.orion.base.common.ServiceHandler;
import com.dcits.galaxy.base.data.BaseRequest;
import com.dcits.galaxy.base.data.BaseResponse;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.BeanUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by lixbb on 2016/1/6.
 */
@Repository
public class EncryptUtil implements InitializingBean {


    private static final Logger logger = LoggerFactory
            .getLogger(EncryptUtil.class);

    private Map<String, EncryptService> encryptServices;
    private Resource configLocation;
    private String switch_on_off;
    private boolean switchOn = false;

    public boolean switchOn() {
        return switchOn;
    }

    //Request 加解密
    public void requestEncryptDecrypt(BaseRequest request) {
        String serviceId = ServiceHandler.getServiceName(request);
        EncryptService encryptService = encryptServices.get(serviceId);
        if (encryptService != null) {
            try {
                for (EncryptField encryptField : encryptService.getRequestFields().values()) {
                    Object[] args = new String[encryptField.getArgs().length];
                    for (int i = 0; i < args.length; i++) {
                        if (encryptField.getArgs()[i].indexOf(".") >= 0) {
                            args[i] = request.readValue(encryptField.getArgs()[i]);
                        } else {
                            args[i] = encryptField.getArgs()[i];
                        }
                    }
                    encryptDecrypt(encryptField.getBeanId(), request, encryptField.getFieldName(), args);
                }
            } catch (Throwable t) {
                throw new GalaxyException(t);
            }
        }
    }

    //response加解密
    public void responseEncryptDecrypt(BaseRequest request, BaseResponse response) throws Exception {
        String serviceId = ServiceHandler.getServiceName(request);
        EncryptService encryptService = encryptServices.get(serviceId);
        if (encryptService != null) {
            try {
                for (EncryptField encryptField : encryptService.getResponseFields().values()) {

                    Object[] args = new String[encryptField.getArgs().length];
                    for (int i = 0; i < args.length; i++) {
                        if (encryptField.getArgs()[i].indexOf(".") >= 0) {
                            args[i] = getFieldValue(request, response, encryptField.getArgs()[i]);

                        } else {
                            args[i] = encryptField.getArgs()[i];
                        }
                    }
                    encryptDecrypt(encryptField.getBeanId(), response, encryptField.getFieldName(), args);
                }
            } catch (Throwable t) {
                throw new GalaxyException(t);
            }
        }
    }

    private void encryptDecrypt(String beanId, Object object, String fieldName, Object[] args) throws Exception {
        if (fieldName.indexOf(".") < 0) {
            IField2EncryptDecrypt iField2EncryptDecrypt = (IField2EncryptDecrypt) SpringApplicationContext.getContext().getBean(beanId);
            BeanUtils.setFieldValue(object, fieldName,
                    iField2EncryptDecrypt.encryptDecrypt(
                            BeanUtils.getFieldValue(object, fieldName), args
                    )
            );

        } else {
            String[] fieldNames = BeanUtils.splitByFirst(fieldName);
            Object obj = BeanUtils.getFieldValue(object, fieldNames[0]);

            if (obj instanceof Iterable) {
                for (Object obj1 : (Iterable) obj) {
                    encryptDecrypt(beanId, obj1, fieldNames[1], args);
                }

            } else encryptDecrypt(beanId, obj, fieldNames[1], args);
        }

    }


    private Object getFieldValue(BaseRequest request, BaseResponse response, String fieldName) throws Exception {
        if (fieldName.startsWith("#request.")) {
            return BeanUtils.getFieldValue(request, fieldName.replaceFirst("#request.", ""));
        } else if (fieldName.startsWith("#response.")) {
            return BeanUtils.getFieldValue(response, fieldName.replaceFirst("#response.", ""));
        } else return BeanUtils.getFieldValue(request, fieldName);
    }


    @Override
    public void afterPropertiesSet() {
        if (logger.isDebugEnabled())
            logger.debug("load fieldEncryptDecrypt.xml!");

        encryptServices = new HashMap<String, EncryptService>();
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(configLocation.getInputStream());
            Element root = document.getRootElement();
            switch_on_off = (String) root.attribute("switch").getData();
            if (switch_on_off.equals("on")) {
                switchOn = true;
                for (Iterator i = root.elementIterator(); i.hasNext(); ) {
                    Element requestResponseElement = (Element) i.next();
                    String requestResponse = requestResponseElement.getName();
                    for (Iterator ii = requestResponseElement.elementIterator(); ii.hasNext(); ) {
                        Element field = (Element) ii.next();
                        String name = (String) field.attribute("name").getData();
                        String beanId = (String) field.attribute("beanId").getData();
                        String args = "";
                        if (field.attribute("args") != null) {
                            args = (String) field.attribute("args").getData();
                        }
                        for (Iterator j = field.elementIterator(); j.hasNext(); ) {
                            Element service = (Element) j.next();
                            String id = (String) service.attribute("id").getData();
                            Attribute attributeArgs = service.attribute("args");
                            String serviceArgs;
                            if (attributeArgs == null) {
                                serviceArgs = args;
                            } else {
                                serviceArgs = (String) attributeArgs.getData();
                            }
                            EncryptField encryptField = new EncryptField();
                            encryptField.setFieldName(name);
                            encryptField.setBeanId(beanId);
                            encryptField.setArgs(serviceArgs.split(","));
                            EncryptService encryptService = encryptServices.get(id);
                            if (encryptService == null) {
                                encryptService = new EncryptService();
                                encryptService.setServiceId(id);
                                encryptServices.put(id, encryptService);
                            }
                            if (requestResponse.equals("Request"))
                                encryptService.getRequestFields().put(encryptField.getFieldName(), encryptField);
                            else if (requestResponse.equals("Response"))
                                encryptService.getResponseFields().put(encryptField.getFieldName(), encryptField);

                        }
                    }
                }
            }
        } catch (Exception e) {
            if (logger.isWarnEnabled())
                logger.warn("load fieldEncryptDecrypt.xml failed! " + e.getMessage());
        }
    }

    public Resource getConfigLocation() {
        return configLocation;
    }

    public void setConfigLocation(Resource configLocation) {
        this.configLocation = configLocation;
    }
}
