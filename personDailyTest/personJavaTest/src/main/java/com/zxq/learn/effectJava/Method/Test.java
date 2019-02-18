package com.zxq.learn.effectJava.Method;

/**
 * Created{ by zhouxqh} on 2017/10/26.
 */
public class Test {

    private static void sort(long a[],int offSet,int length){

        assert a != null;
        assert offSet >= 0 && offSet <= a.length;
        assert length > 0 && length <= a.length - offSet;

        System.out.println("sort method is running!");
    }

    public static void main(String[]args){

        sort(null,-3,0);
    }
}
