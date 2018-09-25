package com.dcits.orion.stria;

import java.util.Map;

/**
 * Created by Tim on 2015/5/19.
 */
public interface Expression {
    /**
     * 根据表达式串、参数解析表达式并返回指定类型
     *
     * @param T    返回类型
     * @param expr 表达式串
     * @param args 参数列表
     * @return T 返回对象
     */
    public <T> T eval(Class<T> T, String expr, Map<String, Object> args);

    public <T> T eval(Class<T> T, String expr, Map<String, Object> args, Object rootObj);
}

