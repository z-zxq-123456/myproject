package com.dcits.orion.stria.entity;

import com.alibaba.dubbo.common.utils.StringUtils;

/**
 * Created by Tim on 2015/5/6.
 */
public class ListMapper extends Mapper {

    private static final long serialVersionUID = 1804569338581373082L;

    private String source;

    private String superSource;

    public String getSource() {
        return source;
    }

    public String getSource(String superSource) {
        if (StringUtils.isNotEmpty(superSource))
            return source.replace("${superSource}", superSource);
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSuperSource() {
        return superSource;
    }

    public void setSuperSource(String superSource) {
        this.superSource = superSource;
    }

}
