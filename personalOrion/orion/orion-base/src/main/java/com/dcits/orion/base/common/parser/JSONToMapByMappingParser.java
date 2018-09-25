/**
 * Title: Galaxy(Distributed service platform)
 * File: JsonToMapForMapping.java
 * Copyright: Copyright (c) 2014-2017
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.orion.base.common.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dcits.galaxy.base.extension.SPI;
import com.dcits.galaxy.base.parser.MapParser;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.orion.base.map.mapping.ParserMapping;

import java.util.Map;

/**
 * <p>Created on 2017/6/19.</p>
 *
 * @author Tim <guotao@dcits.com>
 * @version 1.0.0
 * @since 1.7
 */
public class JSONToMapByMappingParser implements MapParser<String>, SPI {

    private String SPIName;

    @Override
    public Map<String, Object> toMap(String s) {
        Map<String, Object> map = JSON.parseObject(s, Map.class);
        ParserMapping parserMapping = SpringApplicationContext.getContext().getBean(ParserMapping.class);
        return parserMapping.getMapping(map, this.getSPIName(), ParserMapping.WORK_MAP_IN);
    }

    @Override
    public String toObj(Map<String, Object> map) {
        ParserMapping parserMapping = SpringApplicationContext.getContext().getBean(ParserMapping.class);
        map = parserMapping.getMapping(map, this.getSPIName(), ParserMapping.WORK_MAP_OUT);
        return JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
    }

    @Override
    public void setSPIName(String s) {
        this.SPIName = s;
    }

    @Override
    public String getSPIName() {
        return this.SPIName;
    }
}
