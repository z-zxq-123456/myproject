package com.zxq.learn.dynamtic;


/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/6/15
 */
public class MyOperation implements Opertaion {

    @Override
    public void add(Object o1, Object o2) {
        System.out.println("o1 = " +o1);
        System.out.println("o2 = " +o2);
        Integer a = Integer.valueOf(o1.toString());
        Integer b = Integer.valueOf(o2.toString());
        Integer c = a+b;
        System.out.println("result = "+c);
    }

    @Override
    public void sub(Object o1, Object o2) {

    }
}
