package com.dcits.galaxy.base.parser;

import com.dcits.galaxy.base.extension.GalaxySPI;

import java.util.Map;

/**
 * 解析器，用于Map类型转换
 * Created by Tim on 2016/10/31.
 */
@GalaxySPI(value = "json2map")
public interface MapParser<T> {

    /**
     * 将对象转为Map
     *
     * @param t
     * @return
     */
    Map<String, Object> toMap(T t);

    /**
     * 将Map转为对象
     *
     * @param map
     * @return
     */
    T toObj(Map<String, Object> map);
}
