package com.dcits.orion.stria.mapping;

import com.dcits.orion.stria.entity.Item;
import com.dcits.orion.stria.entity.ListMapper;
import com.dcits.orion.stria.entity.Mapper;
import com.dcits.orion.stria.exception.StriaException;
import com.dcits.orion.stria.utils.XmlUtil;
import com.dcits.galaxy.base.util.ExceptionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Request数据映射
 * <p/>
 * Created by Tim on 2015/5/6.
 */
public class MappingBuilder {

    private static final Logger log = LoggerFactory
            .getLogger(MappingBuilder.class);

    private Resource resource;

    public MappingBuilder(Resource resource) {
        this.resource = resource;
    }

    public Map<String, List<Mapper>> parser() {
        Map<String, List<Mapper>> map = new HashMap<>();
        //解析所有配置节点，并实例化class指定的类
        DocumentBuilder documentBuilder = XmlUtil.createDocumentBuilder();
        try {
            if (documentBuilder != null) {
                InputStream input = resource.getInputStream();
                if (input == null) return null;
                Document doc = documentBuilder.parse(input);
                Element configElement = doc.getDocumentElement();
                NodeList serviceNodeList = configElement.getElementsByTagName("service");
                String serviceId;
                int nodeSize = serviceNodeList.getLength();
                for (int i = 0; i < serviceNodeList.getLength(); i++) {
                    Element ele = (Element) serviceNodeList.item(i);
                    serviceId = ((Element) serviceNodeList.item(i)).getAttribute("id");
                    List<Mapper> mappingModel = new ArrayList<>();
                    // mapper映射
                    NodeList mapperNodeList = ele.getElementsByTagName("mapper");
                    mappingModel.addAll(getMapperNode(mapperNodeList));
                    // mapperList映射
                    NodeList mapperListNodeList = ele.getElementsByTagName("mapperlist");
                    mappingModel.addAll(getMapperListNode(mapperListNodeList));
                    if (mappingModel.size() > 0) {
                        map.put(serviceId, mappingModel);
                    }
                }
            }
        } catch (Exception e) {
            if (log.isErrorEnabled())
                log.error(ExceptionUtils.getStackTrace(e));
            throw new StriaException("资源解析失败，请检查配置文件[" + resource.getFilename() + "]", e.getCause());
        }
        return map;
    }

    private List<Mapper> getMapperListNode(NodeList mapperListNodeList) {
        List<Mapper> mappingList = new ArrayList<>();
        int nodeSize = mapperListNodeList.getLength();
        for (int i = 0; i < nodeSize; i++) {
            Node node = mapperListNodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                ListMapper mapper = new ListMapper();
                mapper.setName(element.getAttribute("name"));
                mapper.setClazz(element.getAttribute("class"));
                mapper.setCondition(element.getAttribute("condition"));
                mapper.setSource(element.getAttribute("source"));
                mapper.setItem(getMapperItemElement(element));
                mappingList.add(mapper);
            }
        }
        return mappingList;
    }

    private List<Mapper> getMapperNode(NodeList mapperList) {
        List<Mapper> mappingList = new ArrayList<>();
        int nodeSize = mapperList.getLength();
        for (int i = 0; i < nodeSize; i++) {
            Node node = mapperList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Mapper mapper = new Mapper();
                mapper.setName(element.getAttribute("name"));
                mapper.setClazz(element.getAttribute("class"));
                mapper.setCondition(element.getAttribute("condition"));
                mapper.setItem(getMapperItemElement(element));
                mappingList.add(mapper);
            }
        }
        return mappingList;
    }

    private List<Item> getMapperItemElement(Node mapper) {
        List<Item> itemList = new ArrayList<>();
        NodeList mappers = mapper.getChildNodes();
        int len = mappers.getLength();
        for (int i = 0; i < len; i++) {
            Node node = mappers.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) mappers.item(i);
                if ("item".equals(element.getTagName())) {
                    itemList.add(getItemNode(element));
                } else if ("clazz".equals(element.getTagName())) {
                    itemList.add(getClazzNode(element));
                } else if ("list".equals(element.getTagName())) {
                    itemList.add(getListNode(element));
                }
            }
        }

        return itemList;
    }

    private Item getListNode(Node Object) {
        Item item = new Item();
        Element element = (Element) Object;
        item.setKey(element.getAttribute("name"));
        ListMapper mapper = new ListMapper();
        mapper.setName(element.getAttribute("name"));
        mapper.setClazz(element.getAttribute("class"));
        mapper.setCondition(element.getAttribute("condition"));
        mapper.setSource(element.getAttribute("source"));
        mapper.setItem(getMapperItemElement(element));
        item.setMapper(mapper);
        return item;
    }

    private Item getClazzNode(Node Object) {
        Item item = new Item();
        Element element = (Element) Object;
        item.setKey(element.getAttribute("name"));
        Mapper mapper = new Mapper();
        mapper.setName(element.getAttribute("name"));
        mapper.setClazz(element.getAttribute("class"));
        mapper.setCondition(element.getAttribute("condition"));
        mapper.setItem(getMapperItemElement(element));
        item.setMapper(mapper);
        return item;
    }

    private Item getItemNode(Node node) {
        Item item = new Item();
        Element element = (Element) node;
        item.setKey(element.getAttribute("name"));
        item.setValue(element.getFirstChild().getNodeValue());
        return item;
    }
}
