package com.dcits.galaxy.orion.batch.common;

import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.junit.TestBase;
import com.dcits.orion.core.encrypt.EncryptUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Iterator;

/**
 * Created by lixbb on 2015/12/23.
 */
public class ParseXml extends TestBase {

    public void read() throws MalformedURLException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(this.getClass().getClassLoader().getResource("").getPath() + "ext/fieldEncryptDecrypt.xml"));
        Element root = document.getRootElement();
        //System.out.println(root);
        System.out.println(root.attribute("switch").getData());
        for (Iterator i = root.elementIterator(); i.hasNext(); ) {
            Element element = (Element) i.next();
            System.out.println(element.attribute("name").getData());
            System.out.println(element.attribute("beanId").getData());
            System.out.println(element.attribute("args").getData());
            for (Iterator j = element.elementIterator(); j.hasNext(); ) {
                Element service = (Element) j.next();
                System.out.println(service.attribute("id").getData());
                System.out.println(service.attribute("args").getData());
            }
        }

    }

    public void testParseXml() throws Exception {
        EncryptUtil encryptUtil = SpringApplicationContext.getContext().getBean(EncryptUtil.class);
        encryptUtil.afterPropertiesSet();
        Thread.sleep(Integer.MAX_VALUE);
    }

}
