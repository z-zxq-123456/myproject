package com.zxq.learn.handwrite.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zxq.learn.handwrite.bean.BeanDefinition;
import com.zxq.learn.handwrite.util.JsonUtils;

import java.io.InputStream;
import java.util.List;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-23 22:04
 **/
public class JsonApplicationContext extends BeanFactoryImpl {

    private String fileName;

    public JsonApplicationContext(String fileName) {
        this.fileName = fileName;
    }

    public void init(){
        loadFile();
    }

    private void loadFile(){
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        List<BeanDefinition> beanDefinitionList = JsonUtils.readValue(is, new TypeReference<List<BeanDefinition>>() {});
        if (beanDefinitionList != null && !beanDefinitionList.isEmpty()){
            for (BeanDefinition definition:beanDefinitionList){
                registerBean(definition.getName(),definition);
            }
        }
    }
}
