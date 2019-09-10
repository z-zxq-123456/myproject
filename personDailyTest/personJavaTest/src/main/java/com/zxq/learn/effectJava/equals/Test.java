package com.zxq.learn.effectJava.equals;


/**
 * Created{ by zhouxqh} on 2017/10/21.
 */
public class Test {
    public static void main(String[]args){

        CaseIntensitiveString s = new CaseIntensitiveString("Zxwdwf");
        CaseIntensitiveString s2 = new CaseIntensitiveString("zxwdwf");
        String s1 = "zxwdwf";
        System.out.println(s.equals(s1));//true
        System.out.println(s1.equals(s));//false
        System.out.println(s.equals(s2));//false
        //违法了equals的对称性
    }
}
