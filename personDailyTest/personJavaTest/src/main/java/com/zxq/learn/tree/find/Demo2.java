package com.zxq.learn.tree.find;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description :二分法查找测试
 * @Author :zhouxqh
 * @Date : Create on 2018/5/22
 */
public class Demo2 {

    private static List sorts;

    static {
        sorts = new ArrayList();
        sorts.add(1);
        sorts.add(3);
        sorts.add(10);
        sorts.add(20);
        sorts.add(15);
        sorts.add(11);
        sorts.add(23);
        sorts.add(8);
        sorts.add(30);
    }

    public static void main(String[]args){

        Collections.sort(sorts);
        System.out.print("sort : ");
        for (Object i:sorts){
            System.out.print(" "+i);
        }
        System.out.println();
        BinarySearch.findByHalf(sorts,0,sorts.size(),20);
    }
}
