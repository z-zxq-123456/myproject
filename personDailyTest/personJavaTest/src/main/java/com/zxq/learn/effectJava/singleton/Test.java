package com.zxq.learn.effectJava.singleton;


import java.lang.reflect.Field;

/**
 * Created{ by zhouxqh} on 2017/10/21.
 */
public class Test {
    public static void main(String[] args){

        Evis2 evis2 = Evis2.getInstance();
//        Evis2 evis21 = Evis2.getInstance();
        try{
            Class ee = Class.forName("com.test2.learn.effectJava.singleton.Evis2");
            Field[] field = ee.getDeclaredFields();
            for(Field fie:field){
                fie.setAccessible(true);
                Evis2 evis22 = (Evis2)fie.get(evis2);
                System.out.println(evis2 == evis22);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
