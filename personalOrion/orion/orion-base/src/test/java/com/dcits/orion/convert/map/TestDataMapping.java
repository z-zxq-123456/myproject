package com.dcits.orion.convert.map;

import com.alibaba.fastjson.JSON;
import com.dcits.galaxy.base.util.ClassLoaderUtils;
import com.dcits.galaxy.base.util.FileUtils;
import com.dcits.galaxy.base.util.JsonUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.junit.TestBase;
import com.dcits.orion.base.map.mapping.ParserMapping;

import java.util.Map;

/**
 * Created by lixiaobin on 2017/3/16.
 */
public class TestDataMapping extends TestBase {

    public void testParserMapping() {
        String inFilePath = ClassLoaderUtils.getResource("in.json").getFile();
        Map in = JSON.parseObject(FileUtils.readFile(inFilePath));
        ParserMapping parserMapping = SpringApplicationContext.getContext().getBean(ParserMapping.class);
        Map inMapping = parserMapping.getMapping(in, "json2mapByMapping", "in");
        System.out.println("in:" + JsonUtils.formatJson(in.toString()));
        System.out.println("inMapping:" + JsonUtils.formatJson(inMapping.toString()));

        String outFilePath = ClassLoaderUtils.getResource("out.json").getFile();
        Map out = JSON.parseObject(FileUtils.readFile(outFilePath));
        Map outMapping = parserMapping.getMapping(out, "json2mapByMapping", "out");
        System.out.println("out:" + JsonUtils.formatJson(out.toString()));
        System.out.println("outMapping:" + JsonUtils.formatJson(outMapping.toString()));
    }

    public void testListMapping()
    {
        String inFilePath = ClassLoaderUtils.getResource("financial-9999-0101.json").getFile();
        Map in = JSON.parseObject(FileUtils.readFile(inFilePath));
        ParserMapping parserMapping = SpringApplicationContext.getContext().getBean(ParserMapping.class);
        Map inMapping = parserMapping.getMapping(in, "json2mapByMapping", "in");
        System.out.println("in:" + JsonUtils.formatJson(in.toString()));
        System.out.println("inMapping:" + JsonUtils.formatJson(inMapping.toString()));

        String outFilePath = ClassLoaderUtils.getResource("out.json").getFile();
        Map out = JSON.parseObject(FileUtils.readFile(outFilePath));
        Map outMapping = parserMapping.getMapping(out, "json2mapByMapping", "out");
        System.out.println("out:" + JsonUtils.formatJson(out.toString()));
        System.out.println("outMapping:" + JsonUtils.formatJson(outMapping.toString()));

    }
}
