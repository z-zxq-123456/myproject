package com.zxq.learn.fileParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * java  DOM
 */
public class XMLTest {


    public static void main(String[] args) {


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {

            DocumentBuilder builder=factory.newDocumentBuilder();

            Document document = builder.parse(new File(ClassLoader.getSystemResource("Tables.xml").getFile()));

           Element root = document.getDocumentElement();

           NodeList nodeList = root.getElementsByTagName("table");

           for (int i = 0; i  < nodeList.getLength(); i++){

               Element element = (Element) nodeList.item(i);

               System.out.println(element.getAttribute("id"));
               System.out.println(element.getAttribute("type"));

               NodeList child =   element.getElementsByTagName("colum");

               for (int j = 0; j < child.getLength(); j++){
                   Element element2 = (Element) child.item(i);
                   System.out.println(element2.getAttribute("id"));

                   NodeList child3 = element2.getElementsByTagName("property");
                   Element element3 = (Element) child3.item(i);

                   System.out.println(element3.getAttribute("name"));

               }
           }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
