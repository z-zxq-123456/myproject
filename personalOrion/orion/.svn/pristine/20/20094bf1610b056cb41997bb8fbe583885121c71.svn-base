package com.dcits.orion.base.model;

import com.dcits.galaxy.base.bean.AbstractBean;

/**
 * 数据字典类
 * <p/>
 * Created by Tim on 2016/3/8.
 */
public class Field extends AbstractBean {

    private static final long serialVersionUID = -2366660090051864827L;
    /**
     * 字段名
     */
    private String name;

    /**
     * 定长格式的字段占位长
     */
    private String length;


    /**
     * 是否需要去掉两端空格
     */
    private String trim;

    /**
     * 字段类型（默认是String）
     * <p/>
     * String
     * Int
     * Double
     * Long
     * Date
     */
    private String type = "String";

    private String lr;

    private String pd;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIsNull() {
        return isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull;
    }

    private String isNull;

    private String desc;

    public String getLr() {
        return lr;
    }

    public void setLr(String lr) {
        this.lr = lr;
    }

    public String getPd() {
        return pd;
    }

    public void setPd(String pd) {
        this.pd = pd;
    }

    public String getParseBeanId() {
        return parseBeanId;
    }

    public void setParseBeanId(String parseBeanId) {
        this.parseBeanId = parseBeanId;
    }

    /**
     * 字段格式（生成文件时用）
     */
    private String parseBeanId="parseString";


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return Integer.parseInt(length);
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
