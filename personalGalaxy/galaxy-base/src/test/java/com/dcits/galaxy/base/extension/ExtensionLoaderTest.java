package com.dcits.galaxy.base.extension;

import com.dcits.galaxy.base.parser.JSONToMapParser;
import com.dcits.galaxy.base.parser.MapParser;
import com.dcits.galaxy.base.util.FileUtils;
import com.dcits.galaxy.base.util.JsonUtils;
import com.dcits.galaxy.base.util.ClassLoaderUtils;

import junit.framework.TestCase;

import java.util.Map;
import java.util.Set;

/**
 * Created by Tim on 2016/11/1.
 */
public class ExtensionLoaderTest extends TestCase {

    private String jsonStr;

    @Override
    public void setUp() {
        String filePath = ClassLoaderUtils.getResource("test.json").getFile();
        jsonStr = FileUtils.readFile(filePath);
        jsonStr = JsonUtils.convertMsg(jsonStr, JsonUtils.HUMP_TYPE);
    }

    public void testGetExtensionLoader() throws Exception {
        ExtensionLoader<MapParser> extensionLoader = ExtensionLoader.getExtensionLoader(MapParser.class);
    }

    public void testGetExtensionName() throws Exception {
        ExtensionLoader<MapParser> extensionLoader = ExtensionLoader.getExtensionLoader(MapParser.class);
        MapParser<String> parser = extensionLoader.getExtension("json2map");
        Map<String,Object> map = parser.toMap(jsonStr);
        assertEquals(JSONToMapParser.class, parser.getClass());
    }

    public void testGetLoadedExtension() throws Exception {
        ExtensionLoader<MapParser> extensionLoader = ExtensionLoader.getExtensionLoader(MapParser.class);
        Set<MapParser> set = extensionLoader.getExtensions();
        MapParser parser = extensionLoader.getLoadedExtension("json2map");
        Map<String,Object> map = parser.toMap(jsonStr);
        assertEquals(JSONToMapParser.class, parser.getClass());
    }

}