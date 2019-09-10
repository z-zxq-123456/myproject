package com.qxz.learn.parsing;

import org.w3c.dom.CharacterData;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/10/9
 */
public class MyXNode {

    private Node node;
    private String name;
    private String body;
    private Properties attributes;
    private Properties variables;
    private MyXpathParser xpathParser;

    public String getName() {
        return name;
    }

    public Properties getAttributes() {
        return attributes;
    }

    public MyXNode(Node node, Properties variables, MyXpathParser xpathParser) {
        this.node = node;
        this.variables = variables;
        this.xpathParser = xpathParser;
        this.name = node.getNodeName();
        this.attributes = parseAttrs(node);
        this.body = parseBody(node);
    }

    private Properties parseAttrs(Node node){
        Properties attributes = new Properties();
        NamedNodeMap attributeNodes = node.getAttributes();
        if (attributeNodes != null) {
            for (int i = 0; i < attributeNodes.getLength(); i++) {
                Node attribute = attributeNodes.item(i);
                String value = MyPropertyParser.parse(attribute.getNodeValue(), variables);
                attributes.put(attribute.getNodeName(), value);
            }
        }
        return attributes;
    }

    private String parseBody(Node node) {
        String data = getBodyData(node);
        if (data == null) {
            NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                data = getBodyData(child);
                if (data != null) {
                    break;
                }
            }
        }
        return data;
    }

    private String getBodyData(Node child) {
        if (child.getNodeType() == Node.CDATA_SECTION_NODE
                || child.getNodeType() == Node.TEXT_NODE) {
            String data = ((CharacterData) child).getData();
            data = MyPropertyParser.parse(data, variables);
            return data;
        }
        return null;
    }

    public Properties getChildrenAsProperties(){
        Properties properties = new Properties();
        for (MyXNode child:getChildren()){
            String name = child.getStringAttr("name");
            String value = child.getStringAttr("value");
            if (name != null && value != null){
                properties.setProperty(name,value);
            }
        }
        return properties;
    }

    public List<MyXNode> getChildren(){
        List<MyXNode> children = new ArrayList<>();
        NodeList nodeList = node.getChildNodes();
        if (nodeList != null){
            for (int i = 0, n = nodeList.getLength(); i < n; i++){
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    children.add(new MyXNode(node,variables,xpathParser));
                }
            }
        }
        return children;
    }

    public String getStringAttr(String name){
        return getStringAttr(name,null);
    }

    private String getStringAttr(String name,String def){
        String value = attributes.getProperty(name);
        if (value == null){
            return def;
        }else {
            return value;
        }
    }

    public MyXNode evalNode(String expression) {
        return xpathParser.evalNode(node, expression);
    }
}
