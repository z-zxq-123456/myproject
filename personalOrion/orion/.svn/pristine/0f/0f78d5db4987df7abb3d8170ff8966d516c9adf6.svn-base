package com.dcits.orion.base.map.mapping.entity;

import com.dcits.galaxy.base.util.StringUtils;

/**
 * Created by lixiaobin on 2017/6/13.
 */
public class Service {
    private Mapper in;
    private Mapper out;
    private String useMapping;
    private String[] useMappings;

    public Mapper getIn() {
        return in;
    }
    public void setIn(Mapper in) {
        this.in = in;
    }
    public Mapper getOut() {
        return out;
    }
    public void setOut(Mapper out) {
        this.out = out;
    }

    public String getUseMapping() {
        return useMapping;
    }

    public void setUseMapping(String useMapping) {
        this.useMapping = useMapping;
        if (!StringUtils.isBlank(useMapping))
            useMappings = useMapping.split(",");
    }
    public String[] getUseMappings() {
        return useMappings;
    }
}
