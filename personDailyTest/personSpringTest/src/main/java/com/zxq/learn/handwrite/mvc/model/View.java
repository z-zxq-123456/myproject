package com.zxq.learn.handwrite.mvc.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-31 17:38
 **/
public class View {


    private String viewPath;

    private Map<String,Object> model;


    public View(String viewPath) {
        this.viewPath = viewPath;
        this.model = new HashMap<>();
    }

    public View addView(String key,Object value){

        model.put(key, value);
        return this;
    }

    public String getViewPath() {
        return viewPath;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
