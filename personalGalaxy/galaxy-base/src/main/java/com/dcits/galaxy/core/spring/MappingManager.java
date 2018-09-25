/**
 * Title: Galaxy(Distributed service platform)
 * Copyright:Copyright (c) 2014-2016
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.galaxy.core.spring;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 交易ID与接口的映射关系管理，全局唯一
 *
 * @author xuecy
 */
public class MappingManager {
    private volatile static MappingManager instance = null;

    /**
     * 交易ID与接口的映射关系
     */
    private Map<String, Class<?>> mapping = new ConcurrentHashMap<String, Class<?>>();

    /**
     * 构造函数
     */
    MappingManager() {
    }

    /**
     * 获取Instance
     *
     * @return
     */
    public static MappingManager getInstance() {
        if (instance == null) {
            synchronized (MappingManager.class) {
                if (instance == null) {
                    instance = new MappingManager();
                }
            }
        }
        return instance;
    }

    /**
     * 增加映射信息
     *
     * @param id
     * @param clazz
     */
    public void add(String id, Class<?> clazz) {
        this.mapping.put(id, clazz);
    }

    /**
     * 获取id对应的interface
     *
     * @param id
     * @return
     */
    public Class<?> getInterface(String id) {
        return this.mapping.get(id);
    }

    /**
     * 获取全部的映射关系
     *
     * @return
     */
    public Map<String, Class<?>> getMapping() {
        return mapping;
    }
}
