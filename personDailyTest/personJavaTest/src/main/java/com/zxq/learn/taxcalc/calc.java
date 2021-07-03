package com.zxq.learn.taxcalc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class calc {

    private static List<Float> getTaxRate(int salary){

           List<Float> list = new ArrayList<>();

        if (salary <= 36000){
            list.add(3f);
            list.add(0f);
        }else if (salary <= 144000){
            list.add(10f);
            list.add(2520f);
        }else if (salary <=300000) {
            list.add(20f);
            list.add(16920f);
        }else if (salary <= 420000){
            list.add(25f);
            list.add(31920f);
        }else if (salary <= 660000){
            list.add(30f);
            list.add(52920f);
        }else if (salary <= 960000){
            list.add(35f);
            list.add(85920f);
        }else {
            list.add(40f);
            list.add(181920f);
        }
        return list;
    }


    public static void main(String[] args) {

        int baseSalary = 32000;
        int step =5000;

        int moth=1;
        float before=0;
        while (moth <= 12){

            int initVal=baseSalary * moth-step*moth-(5825)*moth;
            List<Float> clcList = getTaxRate(initVal);
            float tax = (clcList.get(0)/100)*(initVal)-clcList.get(1)-before;
            before += tax;
            System.out.print("------ " + moth+ "月 ");
            System.out.print(" val= " + initVal);
            System.out.print(" tax= " + tax+" before= "+ before);
            System.out.print(" taxTotal= " + before);
            System.out.println();
            moth++;
        }



    }

}
