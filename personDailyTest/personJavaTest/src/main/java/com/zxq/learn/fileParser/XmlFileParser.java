package com.zxq.learn.fileParser;//package com.fileParser;
//
//import org.dom4j.DocumentHelper;
//import org.w3c.dom.Document;
//import org.xml.sax.InputSource;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import java.io.ByteArrayOutputStream;
//import java.io.StringReader;
//
///**
// * 解析xml
// * Created{ by zhouxqh} on 2018/1/8.
// */
//public class XmlFileParser {
//
//    /**
//     * 使用最原始的javax.xml.parsers，标准的jdk api
//     * @return
//     * @throws Exception
//     */
//    public static  String defaultXmlReader() throws Exception{
//
//        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                "<mapping>\n" +
//                "<!--预授权完成撤销冲正-->\n" +
//                " <flow id=\"MbsdCard-1300-0007\">"+
//                "</flow></mapping>";
//        StringReader sr = new StringReader(xmlStr);
//        InputSource is = new InputSource(sr);
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        Document doc = builder.parse(is);
//
//        TransformerFactory tf = TransformerFactory.newInstance();
//        Transformer t = tf.newTransformer();
//        t.setOutputProperty("encoding","GB23121");
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        t.transform(new DOMSource(doc),new StreamResult(bos));
//
//        return bos.toString();
//    }
//
//    /**
//     *dom4j
//     * @return
//     * @throws Exception
//     */
//    public static String dom4jXmlReader() throws Exception{
//
//        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                "<mapping>\n" +
//                "<!--预授权完成撤销冲正-->\n" +
//                " <flow id=\"MbsdCard-1300-0007\">"+
//                "</flow>"+
//                "</mapping>";
//        org.dom4j.Document document = DocumentHelper.parseText(xmlStr);
//        String text = document.asXML();
//        return text;
//    }
//
//
//}
