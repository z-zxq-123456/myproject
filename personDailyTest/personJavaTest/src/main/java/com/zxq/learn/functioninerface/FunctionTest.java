package com.zxq.learn.functioninerface;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-18 21:32
 **/
public class FunctionTest {

    public static void main(String[] args) {

        FunctionUnit<String,String> functionUnit = new FunctionUnit<>();
        functionUnit.print("1fdJJJjdfs", FunctionTest::getLower);
    }

    private static String getLower(String key){
        return key.toLowerCase();
    }
}
