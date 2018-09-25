package com.dcits.orion.stria.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tim on 2016/2/25.
 */
public class StriaThreadLocal {

    public static ThreadLocal<Map<String, Object>> LOCAL = new ThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<String, Object>();
        }
    };

    /**
     * 设置线程级的K/V信息
     *
     * @param key
     * @param vlaue
     */
    public static void put(String key, Object vlaue) {
        LOCAL.get().put(key, vlaue);
    }

    /**
     * 检查是否存在K对象实例
     *
     * @param key
     * @return
     */
    public static boolean containsKey(String key) {
        return LOCAL.get().containsKey(key);
    }

    /**
     * 获取线程级的K/V信息
     *
     * @return
     */
    public static Object get(String key) {
        return LOCAL.get().get(key);
    }

    /**
     * 清空线程
     */
    public static void remove() {
        LOCAL.remove();
    }

}
