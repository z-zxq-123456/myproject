package com.zxq.learn.tree.find;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @Description :红黑树测试
 * @Author :zhouxqh
 * @Date : Create on 2018/5/23
 */
public class Demo4
{
    public static void main(String[]args){
        Map map = new HashMap();
        map.putIfAbsent("fruits","apple");
        map.putIfAbsent("fruits","peak");
        System.out.println(map);

        // 构建多值Map样例代码
        Map<String, HashSet<String>> map1 = new HashMap<>();
        map1.computeIfAbsent("fruits", k -> genValue(k)).add("apple");
        map1.computeIfAbsent("fruits", k -> genValue(k)).add("orange");
        map1.computeIfAbsent("fruits", k -> genValue(k)).add("pear");
        map1.computeIfAbsent("fruits", k -> genValue(k)).add("banana");
        map1.computeIfAbsent("fruits", k -> genValue(k)).add("water");
        System.out.println(map1);

        System.out.println(tableSizeFor(100000));
    }
    static HashSet<String> genValue(String str) {
        return new HashSet<String>();
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : n + 1;
    }
}
