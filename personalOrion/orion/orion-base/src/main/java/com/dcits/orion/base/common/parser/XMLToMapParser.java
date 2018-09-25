package com.dcits.orion.base.common.parser;

import com.dcits.orion.base.util.CdXmlUtils;
import com.dcits.galaxy.base.parser.MapParser;

import java.util.Map;

/**
 * Created by Tim on 2016/11/1.
 */
public class XMLToMapParser implements MapParser<String> {
    /**
     * 将对象转为Map
     *
     * @param s
     * @return
     */
    @Override
    public Map<String, Object> toMap(String s) {
        return CdXmlUtils.xmlToMap(s);
    }

    /**
     * 将Map转为对象
     *
     * @param map
     * @return
     */
    @Override
    public String toObj(Map<String, Object> map) {
        return CdXmlUtils.mapToXml(map);
    }
}
