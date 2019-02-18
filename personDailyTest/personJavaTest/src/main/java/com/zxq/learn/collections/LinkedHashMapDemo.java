package com.zxq.learn.collections;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/6/1
 */
public class LinkedHashMapDemo {
    public static void main(String[]args){

        Map linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("a","a1");
        linkedHashMap.put("c","c1");
        linkedHashMap.put("d","d1");
        linkedHashMap.put("b","b1");
        linkedHashMap.put("e","e1");
        linkedHashMap.putIfAbsent("a","a11");
        linkedHashMap.putIfAbsent("a","a12");
        System.out.println(linkedHashMap);

        String str = null;
        /*Objects.requireNonNull(str,"this String is null");*/

        Supplier supplier = ()->new BigDecimal("1");
        BigDecimal decimal = new BigDecimal(supplier.get().toString());
        System.out.println(decimal.compareTo(BigDecimal.ZERO) > 0);

        Supplier supplier2 = ()->"msg is null";
        Objects.requireNonNull(str,supplier2);
    }
}
