package com.zxq.learn.functioninerface;

import java.util.function.Function;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-18 21:32
 **/
public class FunctionUnit<K,R> {

    public void print(K key,Function<K,R> function){
        System.out.println(function.apply(key));
    }
}
