package com.dcits.orion.scp.mapping.entity;

import com.alibaba.dubbo.common.utils.StringUtils;

/**
 * Created by Tim on 2015/5/6.
 */
public class ListMapper extends Mapper {
    String alias;
    String index;
    String source;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
