package com.dcits.galaxy.util;

import com.dcits.galaxy.base.util.ClassLoaderUtils;
import com.dcits.galaxy.base.util.FileUtils;
import com.dcits.galaxy.base.util.JsonUtils;

import junit.framework.TestCase;

/**
 * Created by Tim on 2016/7/5.
 */
public class JsonUtilsTest extends TestCase {

    String humpJson;

    @Override
    public void setUp() {
        String filePath = ClassLoaderUtils.getResource("test.json").getFile();
        humpJson = FileUtils.readFile(filePath);
    }

    public void testConvertMsg() throws Exception {
        String upperMsg = JsonUtils.convertMsg(humpJson, JsonUtils.UPPER_TYPE);
        System.out.println(upperMsg);
        String str2 = JsonUtils.convertMsg(upperMsg, JsonUtils.HUMP_TYPE);
        System.out.println(str2);
    }
}