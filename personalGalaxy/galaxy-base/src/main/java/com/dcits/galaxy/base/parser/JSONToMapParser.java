package com.dcits.galaxy.base.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Map;

/**
 * Created by Tim on 2016/10/31.
 */
public class JSONToMapParser implements MapParser<String> {

    /**
     * 将Json字符串转为Map
     *
     * @param s
     * @return
     */
    @Override
    public Map<String, Object> toMap(String s) {
        return JSON.parseObject(s, Map.class);
    }

    /**
     * 将Map转为Json字符串
     *
     * @param map
     * @return
     */
    @Override
    public String toObj(Map<String, Object> map) {
        return JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
    }
}
