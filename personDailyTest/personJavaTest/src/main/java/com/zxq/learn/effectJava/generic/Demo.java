package com.zxq.learn.effectJava.generic;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created{ by zhouxqh} on 2017/10/23.
 */
public class Demo {

    static <E> E reduce (List<E> list, Funtion f, E initVal){

       /*//可取方法
       List<E> snapshot;
        synchronized (list){
            snapshot = new ArrayList<E>(list);
        }*/

       //不可取方法
        E[] snapshot = (E[]) list.toArray();//编译时运行类型E  运行时类型为Object  此方法不安全
        E result = initVal;
        for (E e:snapshot){
            result = (E)f.apply(result,e);
        }
        return result;
    }

    public static <T extends Comparable<T>> T max(List<T> list){

        Iterator<T> i = list.iterator();
        T result = i.next();
        while(i.hasNext()){
            T t = i.next();
            if(t.compareTo(result)>0){
                result = t;
            }
        }
        return result;
    }

    public static void main(String[] args){

       System.out.println(Demo.reduce(Arrays.asList(1,"3","5"),new Sub(),100));
    }
}
