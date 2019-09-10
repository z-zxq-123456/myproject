package com.zxq.learn.effectJava.generic;

/**
 *
 * 泛型--类型转换
 * Created{ by zhouxqh} on 2017/10/23.
 */
public class Case {
    public static void main(String[]args){

        //运行时发现异常
        Object[] objectArray = new Long[1];
        objectArray[0] = "test type convert";

        //编译时发现异常
        //  List<Object> list = new ArrayList<Long>();
    }
}
