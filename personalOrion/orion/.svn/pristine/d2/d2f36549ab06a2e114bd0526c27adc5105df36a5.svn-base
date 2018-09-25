package com.dcits.orion.scp.mapping;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.orion.scp.mapping.entity.*;
import com.dcits.orion.scp.utils.ScpConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
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
    Resource resource;
    public MappingBuilder(Resource resource) {
        this.resource = resource;
    }
    public Map<String, Map<String, Mapper>> parser(InputStream inputStream) throws Exception {
        Map<String, Map<String, Mapper>> map = new HashMap<>();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        if (documentBuilder != null) {
            Document doc = documentBuilder.parse(inputStream);
            Element configElement = doc.getDocumentElement();
            NodeList flowList = configElement.getElementsByTagName("flow");
            String flowId;
            for (int i = 0; i < flowList.getLength(); i++) {
                Element flow = (Element) flowList.item(i);
                flowId = flow.getAttribute("id");
                NodeList serviceList = flow.getElementsByTagName("service");
                Map<String, Mapper> mappers = getServiceMappers(serviceList);
                Mapper out = getMapperByName(flow,ScpConstants.out);
                mappers.put(ScpConstants.out,out);
                Mapper error = getMapperByName(flow,ScpConstants.error);
                mappers.put(ScpConstants.error,error);
                Mapper logFields = getMapperByName(flow,ScpConstants.logFields);
                mappers.put(ScpConstants.logFields,logFields);
                map.put(flowId, mappers);
            }
        }
        return map;
    }

    public Map<String, Map<String, Mapper>> parser() {
        try {
            return parser(resource.getInputStream());
        } catch (Exception e) {
            throw new GalaxyException("资源解析失败，请检查配置文件[" + resource.getFilename() + "]", e.getCause());
        }
    }

    private Map<String, Mapper> getServiceMappers(NodeList serviceList) {
        Map<String, Mapper> mapperMap = new HashMap<>();
        for (int i = 0; i < serviceList.getLength(); i++) {
            Node nodeService =  serviceList.item(i);
            if ( nodeService.getNodeType() ==  Node.ELEMENT_NODE) {
                Element service = (Element) nodeService;
                String serviceId = service.getAttribute("id");
                Mapper in = getMapperByName(service,ScpConstants.in);
                if (in != null)
                    mapperMap.put(serviceId+ ScpConstants.split+ScpConstants.in,in);

                Mapper reversal = getMapperByName(service,ScpConstants.reversal);
                if (reversal != null)
                    mapperMap.put(serviceId+ ScpConstants.split+ScpConstants.reversal,reversal);

                Mapper confirm = getMapperByName(service,ScpConstants.confirm);
                if (confirm != null)
                    mapperMap.put(serviceId+ ScpConstants.split+ScpConstants.confirm,confirm);
                Mapper logFields = getMapperByName(service,ScpConstants.logFields);
                if (logFields != null)
                    mapperMap.put(serviceId+ ScpConstants.split+ScpConstants.logFields,logFields);
            }
        }
        return mapperMap;
    }

    private Mapper getMapperByName(Element service,String name)
    {
        NodeList in = service.getElementsByTagName(name);
        if (in != null && in.getLength() > 0 )
        {
            Element element = (Element)in.item(0);
            Mapper mapper = getMapper(element);
            return mapper;
        }
        else return null;
    }


    private Mapper getMapper(Element mapperNode) {
        Mapper mapper = new Mapper();
        List<Item> items = new ArrayList<>();
        mapper.setItems(items);
        NodeList childNodes = mapperNode.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if ( node.getNodeType() ==  Node.ELEMENT_NODE) {
                Element childNode = (Element) node;
                Item item;
                if ("if".equals(childNode.getTagName()))
                {
                    item = new IfItem();
                    item.setValue(childNode.getAttribute("test"));
                    item.setMapper(getMapper(childNode));
                }
                else if ("for".equals(childNode.getTagName()))
                {
                    item = new ForItem();
                    item.setValue(childNode.getAttribute("test"));
                    ((ForItem)item).setIndex(childNode.getAttribute("index"));
                    item.setMapper(getMapper(childNode));
                }
                else {
                    item = new Item();
                    item.setKey(childNode.getAttribute("name"));
                    if ("item".equals(childNode.getTagName())) {
                        item.setValue(childNode.getFirstChild().getNodeValue());
                    } else {
                        Mapper mapper1 = getMapper(childNode);
                        mapper1.setRef(childNode.getAttribute("ref"));
                        String remove = childNode.getAttribute("remove");
                        if (!StringUtils.isBlank(remove))
                            mapper1.setRemoves(remove.split(","));
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


}
