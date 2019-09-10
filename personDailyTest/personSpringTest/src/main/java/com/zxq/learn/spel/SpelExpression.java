package com.zxq.learn.spel;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * spring  spel 表达式
 * Created{ by zhouxqh} on 2018/1/23.
 */
public class SpelExpression {

    public static void main(String []args){

        ExpressionParser parser = new SpelExpressionParser();
        /*字符串*/
        String str = parser.parseExpression(" 'Hello World!' ").getValue(String.class);
        System.out.println(str);
        String str2 = parser.parseExpression(" \"Hello World!\" ").getValue(String.class);
        System.out.println(str2);
         /*int*/
        int int3 = parser.parseExpression("1").getValue(Integer.class);
        System.out.println(int3);
         /*double*/
        double double4 = parser.parseExpression("1.1E+2").getValue(double.class);
        System.out.println(double4);
         /*boolean*/
        boolean boolean5 = parser.parseExpression("true").getValue(boolean.class);
        System.out.println(boolean5);
         /*null*/
        Object obj = parser.parseExpression("null").getValue(Object.class);
        System.out.println(obj == null);
        Object obj2 = parser.parseExpression("Null").getValue(Object.class);
        System.out.println(obj2 != null);
        /*运算*/
        int result1 = parser.parseExpression("1+2*3/2").getValue(Integer.class);
        System.out.println(result1);
        /*关系表达式*/
        boolean result2 = parser.parseExpression("1>2").getValue(boolean.class);
        System.out.println(result2);
        /*正则表达式*/
        boolean result3 = parser.parseExpression("'1233'matches '\\d{3}'").getValue(boolean.class);
        System.out.println(result3);
        /*类型表达式*/
        Class<String> cls1 = parser.parseExpression("T(String)").getValue(Class.class);
        System.out.println(cls1 == String.class);
        /*其他包类访问*/
        String dest = "T(Integer).parseInt('1')";
        int intres = parser.parseExpression(dest).getValue(int.class);
        System.out.println(intres);

    }
}
