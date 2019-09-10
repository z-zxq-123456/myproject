package com.zxq.learn.collections;

import java.util.*;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/5/31
 */
public class HashMapDemo {

    public static void main(String[]args){

        Map<String,String> hashMap = new HashMap<>();
        hashMap.put("h1","h1v");
        hashMap.put("a1","a1v");
        hashMap.put("c1","c1v");
        hashMap.put("e1","e1v");
        hashMap.put("d1","d1v");
        hashMap.put("d2","d1v");
        hashMap.put("d3","d1v");
        hashMap.put("d4","d1v");
        hashMap.put("d5","d1v");
        hashMap.put("d6","d1v");
        hashMap.put("d7","d1v");
        hashMap.put("d8","d1v");
        hashMap.put("d9","d1v");
        hashMap.computeIfAbsent("con1",(k)->convert(k));
        hashMap.computeIfAbsent("con2",(k)->convert(k));
        hashMap.put("d91","d1v");
        hashMap.put("d92","d1v");
        hashMap.put("d93","d1v");
        hashMap.put("d94","d1v");
        hashMap.put("d95","d1v");
        hashMap.put("d96","d1v");
        hashMap.put("d97","d1v");
        hashMap.put("d98","d1v");


        Date beigin = new Date();
        for (Map.Entry entry:hashMap.entrySet()){
            System.out.println("key: "+entry.getKey()+"value: " +entry.getValue());
        }
        Date end = new Date();
        System.out.println();
        System.out.println((end.getTime()-beigin.getTime()));

        Date beigin1 = new Date();
        /*forEach*/
        hashMap.forEach((k,v)->System.out.println("key : " + k + "; value : " + v));
        Date end1 = new Date();
        System.out.println();
        System.out.println((end1.getTime()-beigin1.getTime()));

        Date beigin2 = new Date();
        for (String key:hashMap.keySet()){
            System.out.println("key: "+key+" value: "+hashMap.get(key));
        }
        Date end2 = new Date();
        System.out.println();
        System.out.println((end2.getTime()-beigin2.getTime()));

        Date beigin3 = new Date();
        Set set = hashMap.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            Object key = iterator.next();
            System.out.println("key: "+key+" value: "+hashMap.get(key));
        }
        Date end3 = new Date();
        System.out.println();
        System.out.println((end3.getTime()-beigin3.getTime()));

        Map subMap = new HashMap<>(hashMap);
        System.out.println("subMap : " + subMap);


        int n = 4;
        n = n-1;
        n |= n >>> 1;
        System.out.println("n= : " + n);
        n |= n >>> 2;
        System.out.println("n= : " + n);
        n |= n >>> 4;
        System.out.println("n= : " + n);
        n |= n >>> 8;
        System.out.println("n= : " + n);
        n |= n >>> 16;
        System.out.println("n= : " + n);

        System.out.println();
    }

    static String convert(String key){
        return key.concat("convert");
    }
}
