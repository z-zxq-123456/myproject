package com.dcits.orion.base.common.parser;

import com.alibaba.fastjson.JSONObject;
import com.dcits.galaxy.base.extension.ExtensionLoader;
import com.dcits.galaxy.base.parser.MapParser;
import com.dcits.galaxy.base.util.ClassLoaderUtils;
import com.dcits.galaxy.base.util.FileUtils;
import com.dcits.galaxy.junit.TestBase;

import java.util.Map;

/**
 * <p>Created on 2017/6/19.</p>
 *
 * @author Tim <guotao@dcits.com>
 * @version 1.0.0
 * @since 1.7
 */
public class JSONToMapByMappingParserTest extends TestBase {
    private String inMsg;
    private Map<String, Object> out;
    private String parserName = "json2mapByMapping";

    public void setUp() {
        inMsg = FileUtils.readFile(ClassLoaderUtils.getResource("in.json").getFile());
        out = new JSONObject();
        Map<String, Object> sysHead = new JSONObject();
        Map<String, Object> body = new JSONObject();
        sysHead.put("MESSAGE_TYPE", "1200");
        body.put("ACCT_NO", "11111222233111");
        out.put("SYS_HEAD", sysHead);
        out.put("BODY", body);
    }

    public void testToMap() throws Exception {
        MapParser<String> parser = ExtensionLoader.getExtensionLoader(MapParser.class).getExtension(parserName);
        Map<String, Object> map = parser.toMap(inMsg);
        assertEquals(JSONToMapByMappingParser.class, parser.getClass());
        assertEquals("1200", ((Map<String, Object>) map.get("SYS_HEAD")).get("MESSAGE_TYPE").toString());
        assertEquals(false, ((Map<String, Object>) map.get("BODY")).containsKey("BASE_ACCT_NO"));
        assertEquals(true, ((Map<String, Object>) map.get("BODY")).containsKey("ACCT_NO"));
        assertEquals("RB304999900000000011", ((Map<String, Object>) map.get("BODY")).get("ACCT_NO").toString());
    }

    public void testToObj() throws Exception {
        MapParser<String> parser = ExtensionLoader.getExtensionLoader(MapParser.class).getExtension(parserName);
        parser.toMap(inMsg);
        String outMsg = parser.toObj(out);
        System.out.print("outMsg:" + outMsg);
    }

}