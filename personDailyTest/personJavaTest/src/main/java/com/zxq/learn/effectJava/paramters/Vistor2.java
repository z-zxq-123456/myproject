package com.zxq.learn.effectJava.paramters;


/**
 * 改进措施:将数组改成私有
 * Created{ by zhouxqh} on 2017/10/21.
 */
public class Vistor2 {

    private static Thing[] VALUES = { };
    public static final Thing[] value(){
        return VALUES.clone();
    }
}
