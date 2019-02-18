package com.zxq.learn.tree.find;

import java.util.List;

/**
 * @Description :二分法查找
 * @Author :zhouxqh
 * @Date : Create on 2018/5/22
 */
public class BinarySearch {

    public static void findByHalf(List sort,int begin,int end,int value) {

        if (begin <= end){
            int mid= (begin+end)/2;
            if (value < (Integer) sort.get(begin)
                || value > (Integer)sort.get(end-1)
                || (Integer) sort.get(begin)  > (Integer)sort.get(end-1)){
                System.out.println("target value is not find in list!");
            }else {
                int midValue = (Integer) sort.get(mid);
                if (midValue == value){
                    System.out.println("target value is find in list! index: "+mid);
                }else if (midValue > value){
                    findByHalf(sort,begin,mid,value);
                }else {
                    findByHalf(sort,mid,sort.size(),value);
                }
            }
        }
    }
}
