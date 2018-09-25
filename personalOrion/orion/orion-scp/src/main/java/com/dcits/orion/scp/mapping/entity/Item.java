package com.dcits.orion.scp.mapping.entity;

import com.dcits.galaxy.base.bean.AbstractBean;

/**
 * Created by Tim on 2015/5/19.
 */
public class Item extends AbstractBean {
    private String key;
    private String value;
    private Mapper mapper;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Mapper getMapper() {
        return mapper;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }


}