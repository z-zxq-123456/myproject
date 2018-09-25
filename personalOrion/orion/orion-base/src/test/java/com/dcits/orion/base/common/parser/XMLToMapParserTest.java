package com.dcits.orion.base.common.parser;

import com.dcits.galaxy.base.extension.ExtensionLoader;
import com.dcits.galaxy.base.parser.MapParser;
import com.dcits.galaxy.base.util.ClassLoaderUtils;
import com.dcits.galaxy.base.util.FileUtils;
import com.dcits.galaxy.junit.TestBase;

import java.util.Map;
import java.util.Set;

/**
 * Created by Tim on 2016/11/1.
 */
public class XMLToMapParserTest extends TestBase {

    private String xmlStr;

    @Override
    public void setUp() {
        String filePath = ClassLoaderUtils.getResource("compositeData.xml").getFile();
        xmlStr = FileUtils.readFile(filePath);
    }

    public void testToMap() throws Exception {
        MapParser<String> parser = ExtensionLoader.getExtensionLoader(MapParser.class).getExtension("xml2map");
        Map<String, Object> map = parser.toMap(xmlStr);
        assertEquals(XMLToMapParser.class, parser.getClass());
    }

    public void testToObj() throws Exception {
        MapParser<String> parser = ExtensionLoader.getExtensionLoader(MapParser.class).getExtension("xml2map");
        Map<String, Object> map = parser.toMap(xmlStr);
        assertEquals(XMLToMapParser.class, parser.getClass());
        String xml = parser.toObj(map);
        System.out.println(xml);
    }

    public void testGetExtensions() throws Exception {
        Set<MapParser> t = ExtensionLoader.getExtensionLoader(MapParser.class).getExtensions();
        assertEquals(2, t.size());
    }

}