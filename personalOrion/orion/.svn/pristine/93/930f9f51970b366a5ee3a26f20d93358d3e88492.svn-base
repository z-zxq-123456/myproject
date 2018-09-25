package com.dcits.orion.base.map.mapping;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.orion.base.map.mapping.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixiaobin on 2017/3/16.
 */
public class MappingBuilder {
    private static final Logger log = LoggerFactory
            .getLogger(MappingBuilder.class);
    private Resource resource;
    private Map<String, Service> serviceMapping = new HashMap<>();
    private Map<String, BaseDefine> baseDefineMapping = new HashMap<>();


    public MappingBuilder(Resource resource) {
        this.resource = resource;
        try {
            parser(resource.getInputStream());
        } catch (Exception e) {
            throw new GalaxyException("资源解析失败，请检查配置文件[" + resource.getFilename() + "]", e);
        }
    }

    public MappingBuilder(InputStream inputStream) throws Exception {
        parser(inputStream);
    }

    public void parser(InputStream inputStream) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        if (documentBuilder != null) {
            Document doc = documentBuilder.parse(inputStream);
            Element configElement = doc.getDocumentElement();
            String rootUseMapping = configElement.hasAttribute("useMapping") ? configElement.getAttribute("useMapping") : null;
            NodeList mappingList = configElement.getElementsByTagName("mapping");
            if (mappingList != null) {
                for (int i = 0; i < mappingList.getLength(); i++) {
                    Element mapping = (Element) mappingList.item(i);
                    String serviceUseMapping = mapping.hasAttribute("useMapping") ? mapping.getAttribute("useMapping") : null;
                    if (serviceUseMapping == null && rootUseMapping != null)
                        serviceUseMapping = rootUseMapping;
                    String mappingId = mapping.getAttribute("id");
                    Service service = getService(mapping, serviceUseMapping);
                    serviceMapping.put(mappingId, service);
                }
            }
            NodeList baseDefineList = configElement.getElementsByTagName("baseDefine");
            if (baseDefineList != null) {
                for (int i = 0; i < baseDefineList.getLength(); i++) {
                    Element baseDefineElement = (Element) baseDefineList.item(i);
                    String id = baseDefineElement.getAttribute("id");
                    String globalMappingId = getElementValue(baseDefineElement,"globalMappingId");
                    String mappingIdSpel = getElementValue(baseDefineElement,"mappingIdSpel");
                    BaseDefine baseDefine = new BaseDefine();
                    baseDefine.setId(id);
                    baseDefine.setGlobalMappingId(globalMappingId);
                    baseDefine.setMappingIdSpel(mappingIdSpel);
                    baseDefineMapping.put(id,baseDefine);
                }
            }
        }
    }

    private String getElementValue(Element element,String name)
    {
        NodeList nodeList = element.getElementsByTagName(name);
        if (nodeList != null && nodeList.getLength() > 0)
        {
            Element valueElement = (Element)nodeList.item(0);
            return valueElement.getFirstChild().getNodeValue();
        }
        else
            return null;
    }

    private Service getService(Element mapping, String useMapping) {
        Service service = new Service();
        service.setUseMapping(useMapping);
        Mapper in = getMapper(mapping, "in");
        if (in != null) {
            if (in.getUseMapping() == null)
                in.setUseMapping(useMapping);
        }
        Mapper out = getMapper(mapping, "out");
        if (out != null) {
            if (out.getUseMapping() == null)
                out.setUseMapping(useMapping);
        }
        service.setIn(in);
        service.setOut(out);
        return service;
    }

    private Mapper getMapper(Element mapperNode, String name) {
        NodeList nodeList = mapperNode.getElementsByTagName(name);
        if (nodeList != null && nodeList.getLength() > 0) {
            Element element = (Element) nodeList.item(0);
            Mapper mapper = getMapper(element);
            return mapper;
        } else return null;

    }

    private Mapper getMapper(Element mapperNode) {
        Mapper mapper = new Mapper();
        mapper.setRef(mapperNode.getAttribute("ref"));
        String remove = mapperNode.getAttribute("remove");
        if (!StringUtils.isBlank(remove))
            mapper.setRemoves(remove.split(","));
        if (mapperNode.hasAttribute("useMapping"))
            mapper.setUseMapping(mapperNode.getAttribute("useMapping"));
        List<Item> items = new ArrayList<>();
        mapper.setItems(items);
        NodeList childNodes = mapperNode.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element childNode = (Element) node;
                Item item;
                if ("if".equals(childNode.getTagName())) {
                    item = new IfItem();
                    item.setValue(childNode.getAttribute("test"));
                    item.setMapper(getMapper(childNode));
                } else {
                    item = new Item();
                    item.setKey(childNode.getAttribute("name"));
                    if ("item".equals(childNode.getTagName())) {
                        item.setValue(childNode.getFirstChild().getNodeValue());
                    } else {
                        Mapper mapper1 = getMapper(childNode);
                        if ("map".equals(childNode.getTagName())) {
                            item.setMapper(mapper1);
                        } else if ("list".equals(childNode.getTagName())) {
                            ListMapper listMapper = new ListMapper();
                            listMapper.setSource(childNode.getAttribute("source"));
                            listMapper.setIndex(childNode.getAttribute("index"));
                            listMapper.setAlias(childNode.getAttribute("alias"));
                            listMapper.setItems(mapper1.getItems());
                            listMapper.setRef(mapper1.getRef());
                            listMapper.setRemoves(mapper1.getRemoves());
                            item.setMapper(listMapper);
                        }
                    }
                }
                items.add(item);
            }
        }
        return mapper;
    }


    public Map<String, Service> getServiceMapping() {
        return serviceMapping;
    }

    public Map<String,BaseDefine> getBaseDefineMapping(){
        return baseDefineMapping;
    }

}
