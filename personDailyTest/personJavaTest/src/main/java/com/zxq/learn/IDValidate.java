package com.zxq.learn;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-04-06 09:09
 **/
public class IDValidate {


    public static void main(String[] args) {

        Integer[] mode = new Integer[]{7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};
        char [] lastArray = new char[]{'1','0','X','9','8','7','6','5','4','3','2'};

        List<Integer> idList = new ArrayList<>();
        idList.add(6);
        idList.add(1);
        idList.add(2);
        idList.add(7);
        idList.add(2);
        idList.add(5);
        idList.add(1);
        idList.add(9);
        idList.add(9);
        idList.add(5);
        idList.add(0);
        idList.add(2);
        idList.add(2);
        idList.add(5);
        idList.add(2);
        idList.add(0);
        idList.add(1);

      int sum = 0;
      int i = 0;
      for (Integer id:idList){

          sum = sum + id*mode[i++];
      }
      System.out.println(lastArray[sum%11]);

    }
}
