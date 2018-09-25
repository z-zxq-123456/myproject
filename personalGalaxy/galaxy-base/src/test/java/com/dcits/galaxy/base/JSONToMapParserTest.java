package com.dcits.galaxy.base;

import com.dcits.galaxy.base.parser.JSONToMapParser;
import com.dcits.galaxy.base.util.JsonUtils;
import com.dcits.galaxy.base.util.ClassLoaderUtils;
import com.dcits.galaxy.base.util.FileUtils;

import junit.framework.TestCase;

import java.util.Map;

/**
 * Created by Tim on 2016/10/31.
 */
public class JSONToMapParserTest extends TestCase {

    private String jsonStr;

    @Override
    public void setUp() {
        String filePath = ClassLoaderUtils.getResource("test.json").getFile();
        jsonStr = FileUtils.readFile(filePath);
        jsonStr = JsonUtils.convertMsg(jsonStr, JsonUtils.HUMP_TYPE);
    }

    public void testToMap() throws Exception {
        JSONToMapParser jstp = new JSONToMapParser();
        Map<String ,Object> map = jstp.toMap(jsonStr);
        System.out.println(map);
    }

    public void testToObj() throws Exception {
        JSONToMapParser jstp = new JSONToMapParser();
        Map<String ,Object> map = jstp.toMap(jsonStr);
        String jsonStr = jstp.toObj(map);
        System.out.println(jsonStr);
    }

}