package com.zxq.learn.handwrite.mvc.model;

import java.util.Map;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-31 17:37
 **/
public class Param {

    private Map<String,Object> params;

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }


    public boolean isEmpty(){
        return params.isEmpty();
    }

}
