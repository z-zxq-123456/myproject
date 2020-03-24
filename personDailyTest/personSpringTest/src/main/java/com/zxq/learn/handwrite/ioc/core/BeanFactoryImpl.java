package com.zxq.learn.handwrite.ioc.core;

import com.zxq.learn.handwrite.ioc.bean.BeanDefinition;
import com.zxq.learn.handwrite.ioc.bean.ContructArg;
import com.zxq.learn.handwrite.ioc.util.BeanUtils;
import com.zxq.learn.handwrite.ioc.util.ClassUtils;
import com.zxq.learn.handwrite.ioc.util.ReflectUtils;
import org.springframework.util.StringUtils;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-23 21:24
 **/
public class BeanFactoryImpl implements BeanFactory {

    private static final ConcurrentHashMap<String,Object> beanMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    /*解决循环依赖*/
    private static final ConcurrentHashMap<String, Object> earlySingleObjects = new ConcurrentHashMap<>();
    private static final Set<String> beanNameSet = Collections.synchronizedSet(new HashSet<>());

    @Override
    public Object getBean(String beanName) {
        /*查看实例化bean*/
        Object o = beanMap.get(beanName);
        if (o != null){
            return o;
        }
        Object earlyBean = earlySingleObjects.get(beanName);
        if (earlyBean != null){
            System.out.println("循环依赖，提前返回尚未加载完成的bean:" + beanName);
            return earlyBean;
        }
        /*创建bean*/
        try {
            o = createBean(beanDefinitionMap.get(beanName));
            if (o != null){
                earlySingleObjects.put(beanName,o);
                /*注入参数*/
                populateBean(o);
                beanMap.put(beanName,o);
                earlySingleObjects.remove(beanName);
            }
        } catch (NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }

    private Object createBean(BeanDefinition beanDefinition) throws NoSuchMethodException {
        String beanName = beanDefinition.getClassName();
        Class clsz = ClassUtils.loadClass(beanName);
        List<ContructArg> constructorList = beanDefinition.getContructArgs();
        if (constructorList != null &&!constructorList.isEmpty()){
            List<Object> objects = new ArrayList<>();
            for (ContructArg contructArg:constructorList){
                objects.add(getBean(contructArg.getRef()));
            }
            return BeanUtils.intanceByCglib(clsz,clsz.getConstructor(),objects.toArray());
        }else {
            return BeanUtils.intanceByCglib(clsz,null,null);
        }
    }

    private void populateBean(Object bean) throws IllegalAccessException {
        Field[] fields = bean.getClass().getSuperclass().getDeclaredFields();
        if (fields!=null){
            for (Field field:fields){
                String beanName = field.getName();
                beanName = StringUtils.uncapitalize(beanName);
                if (beanNameSet.contains(beanName)){
                    Object fieldBean = getBean(beanName);
                    if (fieldBean != null){
                        ReflectUtils.injectField(field,bean,fieldBean);
                    }
                }
            }
        }
    }

    protected void registerBean(String name,BeanDefinition bd){
        beanDefinitionMap.put(name, bd);
        beanNameSet.add(name);
    }
}
