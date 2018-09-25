package com.dcits.orion.stria.test.mapping;

import com.alibaba.fastjson.JSON;
import com.dcits.orion.stria.config.StriaConstants;
import com.dcits.orion.stria.core.Runner;
import com.dcits.orion.stria.dao.mapper.Flow;
import com.dcits.orion.stria.mapping.MappingBuilder;
import com.dcits.orion.stria.mapping.RequestMapping;
import com.dcits.ensemble.cif.api.Non12009100In;
import com.dcits.galaxy.base.util.ClassLoaderUtils;
import com.dcits.galaxy.base.util.FileUtils;
import com.dcits.galaxy.base.util.JsonUtils;

import junit.framework.TestCase;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tim on 2015/7/13.
 */
public class TestMapping extends TestCase {

    private Resource r = new FileSystemResource(ClassLoaderUtils.getResource("").getFile() + "mapping\\test-mapping.xml");
    private Non12009100In acct;

    @Override
    protected void setUp() {
        String filePath = ClassLoaderUtils.getResource("test.json").getFile();
        String jsonStr = FileUtils.readFile(filePath);
        jsonStr = JsonUtils.convertMsg(jsonStr, JsonUtils.HUMP_TYPE);
        acct = JSON.parseObject(jsonStr, Non12009100In.class);
    }

    public void testParse() {
        MappingBuilder mb = new MappingBuilder(r);
        System.out.println(mb.parser());
    }

    public void testMapping() {
        RequestMapping requestMapping = new RequestMapping();
        requestMapping.setMappingLocations(new Resource[]{r});
        try {
            requestMapping.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> args = new HashMap<String, Object>();
        args.put(StriaConstants.MSG_KEY, acct);
        Runner runner = new Runner(new Flow(), args);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1; i++)
            requestMapping.mapper("Non-Financial-1200-9100-2", runner);
        //System.out.println(System.currentTimeMillis() - start);
        System.out.println(args);
    }
}
