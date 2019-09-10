package com.zxq.learn.effectJava.StringFormat;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * String格式解析
 * Created{ by zhouxqh} on 2017/10/27.
 */
public class NumberFormatTest {

    public static void main(String[]args){

        int i = 1000000000;
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMaximumIntegerDigits(8);
        nf.setMinimumIntegerDigits(5);
        System.out.println(nf.format(i));


        DecimalFormat df = new DecimalFormat("0000");
        System.out.println(df.format(2));
    }
}
