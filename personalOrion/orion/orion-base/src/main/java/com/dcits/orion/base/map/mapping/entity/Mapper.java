package com.dcits.orion.base.map.mapping.entity;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.base.util.StringUtils;

import java.util.List;

/**
 * Created by Tim on 2015/5/6.
 */
public class Mapper extends AbstractBean {

    private String useMapping;
    private List<Item> items;
    private String ref;

    private String[] removes;

    private String[] useMappings;

    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String[] getRemoves() {
        return removes;
    }

    public void setRemoves(String[] removes) {
        this.removes = removes;
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

    public void setUseMappings(String[] useMappings) {
        this.useMappings = useMappings;
    }
}
