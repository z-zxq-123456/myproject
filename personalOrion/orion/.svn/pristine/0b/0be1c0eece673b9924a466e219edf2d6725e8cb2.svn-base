package com.dcits.orion.base.file;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import com.dcits.orion.base.model.Field;
import com.dcits.orion.base.model.FileBody;
import com.dcits.orion.base.model.FileConvert;
import com.dcits.orion.base.model.FileHead;
import com.dcits.orion.base.model.FileTail;

/**
 * Created by Tim on 2016/3/8.
 */
public class FileConvertUtil implements InitializingBean {

    private static final Logger logger = LoggerFactory
            .getLogger(FileConvertUtil.class);

    private static Map<String, FileConvert> fileConvertMap = new HashMap<>();

    private Resource[] configLocations;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 加载所有的xml文件格式定义配置文件
        if (logger.isDebugEnabled())
            logger.debug("load file convert configuration XML!");
        SAXReader reader = new SAXReader();
        for (Resource res : configLocations) {
            if (logger.isDebugEnabled())
                logger.debug("fileName " + "[" + res.getFilename() + "]");
            try {
                Document document = reader.read(res.getInputStream());
                Element root = document.getRootElement();
                FileConvert fc = new FileConvert();
                fc.setName(getElementAttribute("name", root));
                fc.setEncoding(getElementAttribute("encoding", root, "UTF-8"));
                fc.setType(getElementAttribute("type", root));
                fc.setBr(getElementAttribute("br", root, "\r\n"));
                fc.setSheetName(getElementAttribute("sheetName", root));
                // 头格式定义
                Element headElement = (Element) document.selectSingleNode("/fileConvert/head");
                fc.setFileHead(parseHead(headElement));
                // 体格式定义
                Element bodyElement = (Element) document.selectSingleNode("/fileConvert/body");
                fc.setFileBody(parseBody(bodyElement));
                // 尾格式定义
                Element tailElement = (Element) document.selectSingleNode("/fileConvert/tail");
                fc.setFileTail(parseTail(tailElement));
                fileConvertMap.put(fc.getName(), fc);
            } catch (Exception e) {
                if (logger.isWarnEnabled())
                    logger.warn("load antiRepeatCfg.xml failed! " + e.getMessage());
            }
        }
    }


    private FileHead parseHead(Element element) {
        if (null == element) {
            return null;
        }
        FileHead fh = new FileHead();
        fh.setSepType(getElementAttribute("sepType", element));
        fh.setSeparator(getElementAttribute("separator", element));
        fh.setIdent(getElementAttribute("ident", element));
        fh.setClazz(getElementAttribute("clazz", element, Map.class.getName()));
        fh.setRef(getElementAttribute("ref", element));
        List<Field> fields = new ArrayList<>();
        List<Element> fieldElements = element.selectNodes("field");
        if (fieldElements != null) {
            for (Element fieldElement : fieldElements) {
                fields.add(parseField(fieldElement));
            }
        }
        fh.setFields(fields);
        return fh;
    }

    private FileBody parseBody(Element element) {
        if (null == element) {
            return null;
        }
        FileBody fb = new FileBody();
        fb.setSepType(getElementAttribute("sepType", element));
        fb.setSeparator(getElementAttribute("separator", element));
        fb.setIdent(getElementAttribute("ident", element));
        fb.setClazz(getElementAttribute("clazz", element, Map.class.getName()));
        fb.setRef(getElementAttribute("ref", element));
        List<Field> fields = new ArrayList<>();
        List<Element> fieldElements = element.selectNodes("field");
        if (fieldElements != null) {
            for (Element fieldElement : fieldElements) {
                fields.add(parseField(fieldElement));
            }
        }
        fb.setFields(fields);
        return fb;

    }

    private FileTail parseTail(Element element) {
        if (null == element) {
            return null;
        }
        FileTail ft = new FileTail();
        ft.setSepType(getElementAttribute("sepType", element));
        ft.setSeparator(getElementAttribute("separator", element));
        ft.setIdent(getElementAttribute("ident", element));
        ft.setClazz(getElementAttribute("clazz", element, Map.class.getName()));
        ft.setRef(getElementAttribute("ref", element));
        List<Field> fields = new ArrayList<>();
        List<Element> fieldElements = element.selectNodes("field");
        if (fieldElements != null) {
            for (Element fieldElement : fieldElements) {
                fields.add(parseField(fieldElement));
            }
        }
        ft.setFields(fields);
        return ft;
    }

    private Field parseField(Element element) {
        if (null == element) {
            return null;
        }
        Field field = new Field();
        field.setName(getElementAttribute("name", element));
        field.setLength(getElementAttribute("length", element));
        field.setTrim(getElementAttribute("trim", element));
        field.setType(getElementAttribute("type", element, "String"));
        field.setParseBeanId(getElementAttribute("parseBeanId", element, "parseString"));
        field.setLr(getElementAttribute("lr", element, "R"));
        field.setPd(getElementAttribute("pd", element, " "));
        field.setDesc(getElementAttribute("desc", element, ""));
        field.setIsNull(getElementAttribute("isNull", element, "Y"));
        return field;
    }

    private String getElementAttribute(String name, Element element) {
        return getElementAttribute(name, element, null);
    }

    private String getElementAttribute(String name, Element element, String defaultValue) {
        return null == element.attribute(name) ? defaultValue : (String) element.attribute(name).getData();
    }

    public void setConfigLocations(Resource[] configLocations) {
        this.configLocations = configLocations;
    }

    public static FileConvert getFileConvert(String convertName) {
        return fileConvertMap.get(convertName);
    }
}
