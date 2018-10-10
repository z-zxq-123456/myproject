package com.qxz.learn.parsing;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.*;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/10/9
 */
public class MyXpathParser {

    private Document document;
    private boolean validation;
    private EntityResolver entityResolver;
    private Properties variables;
    private XPath xpath;

    public MyXpathParser(String xml){
       commonConstructor(false,null,null);
       this.document = createDocument(new InputSource(new StringReader(xml)));
    }

    public MyXpathParser(String xml, boolean validation, Properties variables) {
        commonConstructor(validation, variables, null);
        this.document = createDocument(new InputSource(new StringReader(xml)));
    }

    public MyXpathParser(Reader reader){
        commonConstructor(false,null,null);
        this.document = createDocument(new InputSource(reader));
    }

    public MyXpathParser(Reader reader, boolean validation, Properties variables, EntityResolver entityResolver) {
        commonConstructor(validation, variables, entityResolver);
        this.document = createDocument(new InputSource(reader));
    }

    public MyXpathParser(InputStream inputStream){
        commonConstructor(false, null, null);
        this.document = createDocument(new InputSource(inputStream));

    }

    public MyXpathParser(Document document){
        commonConstructor(false, null, null);
        this.document = document;
    }

    public void setVariables(Properties variables) {
        this.variables = variables;
    }

    public String evalString(String expression){
        return evalString(document,expression);
    }

    public String evalString(Object root,String expression){
       String result = (String) evaluate(expression,root,new QName("http://www.w3.org/1999/XSL/Transform", "STRING"));
       result = MyPropertyParser.parse(expression,variables);
       return result;
    }

    public List<MyXNode> evalNodes(String expression) {
        return evalNodes(document, expression);
    }

    public List<MyXNode> evalNodes(Object root, String expression) {
        List<MyXNode> xnodes = new ArrayList<MyXNode>();
        NodeList nodes = (NodeList) evaluate(expression, root,new QName("http://www.w3.org/1999/XSL/Transform", "NODESET"));
        for (int i = 0; i < nodes.getLength(); i++) {
            xnodes.add(new MyXNode( nodes.item(i), variables,this));
        }
        return xnodes;
    }

    public MyXNode evalNode(String expression) {
        return evalNode(document, expression);
    }

    public MyXNode evalNode(Object root, String expression) {
        Node node = (Node) evaluate(expression, root, new QName("http://www.w3.org/1999/XSL/Transform", "NODE"));
        if (node == null) {
            return null;
        }
        return new MyXNode(node,variables,this);
    }

    private Object evaluate(String expression,Object root,QName returnType){
        try {
            return xpath.evaluate(expression,root,returnType);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Document createDocument(InputSource inputSource){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(validation);
            factory.setNamespaceAware(false);
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(false);
            factory.setCoalescing(false);
            factory.setExpandEntityReferences(true);

            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setEntityResolver(entityResolver);
            builder.setErrorHandler(new ErrorHandler() {
                @Override
                public void warning(SAXParseException exception) throws SAXException {
                    throw exception;
                }

                @Override
                public void error(SAXParseException exception) throws SAXException {
                    throw exception;
                }

                @Override
                public void fatalError(SAXParseException exception) throws SAXException {
                    throw exception;
                }
            });
            return builder.parse(inputSource);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (SAXException e){
            e.printStackTrace();
        }
        return null;
    }

    private void commonConstructor(boolean validation,Properties variables,EntityResolver entityResolver){
        this.validation = validation;
        this.entityResolver = entityResolver;
        this.variables = variables;
        XPathFactory factory = XPathFactory.newInstance();
        this.xpath = factory.newXPath();
    }
}
