package com.dcits.orion.base.model;

import com.dcits.galaxy.base.bean.AbstractBean;

import java.util.List;

/**
 * 文件行格式定义
 * <p/>
 * Created by Tim on 2016/3/8.
 */
public class FileRow extends AbstractBean{

    private static final long serialVersionUID = -2818660775705414292L;
    /**
     * 字段分隔类型
     * Txt文件类型必输
     * F：定长格式
     * S：分隔符分隔
     */
    private String sepType;

    /**
     * 分隔符
     */
    private String separator;

    /**
     * 头标识
     */
    private String ident;

    /**
     * 文件记录解析成java类的全名
     */
    private String clazz;

    /**
     * 引用格式
     * 其他结构的定义名称
     */
    private String ref;

    /**
     * 数据格式
     */
    private List<Field> fields;

    public String getSepType() {
        return sepType;
    }

    public void setSepType(String sepType) {
        this.sepType = sepType;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
