package com.zxq.learn.collections;

import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-04-22 21:07
 **/
public class MapResize {

    public static void main(String[] args) {
        Map map = new HashMap();

        //resize for defalut factor 12  no linkedlist

       /* for (int i = 0; i <13; i++){
            map.put(i,i);
            System.out.println("finish put key = " + i);
        }*/


       //resize for default factor 12  linkedlist

    /*    for (int i = 0; i <14; i+=1){
            int hash  = String.valueOf(i).hashCode();
            int index = hash^ (hash >>> 16);
            int count = (16-1)&index;
            map.put(String.valueOf(i),i);
            System.out.println("finish put key = " + count+" i = "+i);
        }
*/
        //resize for default factor 12 treemap  mintreefiy 64,小于64自动扩容

 /*       map.put(String.valueOf(0),0);
        map.put(String.valueOf(11),0);
        map.put(String.valueOf(22),0);
        map.put(String.valueOf(33),0);
        map.put(String.valueOf(44),0);
        map.put(String.valueOf(55),0);
        map.put(String.valueOf(66),0);
        map.put(String.valueOf(77),0);
        map.put(String.valueOf(88),0);

        for (int i = 0; i <5; i+=1){
            int hash  = String.valueOf(i).hashCode();
            int index = hash^ (hash >>> 16);
            int count = (16-1)&index;
            map.put(String.valueOf(i+100),i+100);
            System.out.println("finish put key = " + count+" i = "+i);
        }*/



        //linkedlist-->treefiybin
        for (int i = 600; i <664; i+=1){

            map.put(String.valueOf(i),i);
        }

        for (int i = 0;i<600;i++){
            int hash  = String.valueOf(i).hashCode();
            int index = hash^ (hash >>> 16);
            int count = (128-1)&index;
            System.out.println("i == " + i + "count =" + count);
        }


        map.put(String.valueOf(0),0);
        map.put(String.valueOf(121),0);
        map.put(String.valueOf(165),0);
        map.put(String.valueOf(242),0);
        map.put(String.valueOf(286),0);
        map.put(String.valueOf(363),0);
        map.put(String.valueOf(440),0);
        map.put(String.valueOf(484),0);
        map.put(String.valueOf(561),0);



    }
}
