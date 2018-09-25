package com.dcits.galaxy.base.bean;

/**
 * Created by Tim on 2016/6/3.
 */
public interface IBean {

    /**
     * 写属性
     *
     * @param fieldName
     * @return
     */
    Object readValue(String fieldName) throws Exception;

    /**
     * 读属性
     *
     * @param fieldName
     * @param fieldValue
     */
    void writeValue(String fieldName, Object fieldValue) throws Exception;

}
