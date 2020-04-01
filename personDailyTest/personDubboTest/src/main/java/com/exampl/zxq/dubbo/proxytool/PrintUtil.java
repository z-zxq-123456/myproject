package com.exampl.zxq.dubbo.proxytool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintUtil implements IMethod {

    public PrintUtil() {
    }

    public void pringLog() {
        System.out.println("PrintUtil pringLog()! ");
    }

    public static void main(String[] args) {

        System.out.println(new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date()));

        try {
            System.out.println(new SimpleDateFormat("yyyyMMdd HH:mm:ss").parse("20190709 19:46:47"));
            System.out.println(new Date().getTime());
            System.out.println(System.currentTimeMillis());
            System.out.println(new Date());
            System.out.println(System.getProperties());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
