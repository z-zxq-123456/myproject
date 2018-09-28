package com.qxz.learn.mapping;

import java.util.Map;

public class MyDiscriminator {

    private MyResultMapping resultMapping;

    private Map<String, String> discriminatorMap;


    public MyResultMapping getResultMapping() {
        return resultMapping;
    }

    public Map<String, String> getDiscriminatorMap() {
        return discriminatorMap;
    }

    public String getMapIdFor(String s) {
        return discriminatorMap.get(s);
    }

}
