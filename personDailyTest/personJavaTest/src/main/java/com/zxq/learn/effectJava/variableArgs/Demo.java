package com.zxq.learn.effectJava.variableArgs;

/**
 * 可变参数
 * Created{ by zhouxqh} on 2017/10/26.
 */
public class Demo
{
    public static int min(int firstArg,int ...remainings){
        int min = firstArg;
        for(int arg:remainings){
            if (arg < min){
                min = arg;
            }
        }
        return min;
    }

    public static void main(String[]args){

        System.out.println(min(2,1,3,5));
    }
}
