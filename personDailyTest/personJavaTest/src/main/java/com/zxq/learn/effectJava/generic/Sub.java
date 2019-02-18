package com.zxq.learn.effectJava.generic;

/**
 * Created{ by zhouxqh} on 2017/10/23.
 */
public class Sub implements Funtion {

    @Override
    public Object apply(Object arg1, Object arg2) {

        return Integer.parseInt(arg1.toString())-Integer.parseInt(arg2.toString());
    }
}
