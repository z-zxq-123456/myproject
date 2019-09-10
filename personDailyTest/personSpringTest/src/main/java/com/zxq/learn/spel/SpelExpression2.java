package com.zxq.learn.spel;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * spring  spel 表达式
 * Created{ by zhouxqh} on 2018/1/23.
 */
public class SpelExpression2 {

    public static void main(String []args)throws Exception{

        ExpressionParser parser = new SpelExpressionParser();
        /*自定义函数*/
        StandardEvaluationContext context = new StandardEvaluationContext();
        Method parseInt = Integer.class.getDeclaredMethod("parseInt",String.class);
        context.registerFunction("parseInt",parseInt);
        context.setVariable("parseInt2",parseInt);
        String expression = "#parseInt('3') == #parseInt2('4')";
        boolean expressRes = parser.parseExpression(expression).getValue(context,boolean.class);
        System.out.println(expressRes);
        /*赋值表达式*/
        StandardEvaluationContext context2 = new StandardEvaluationContext("aaaaa");
        String res = parser.parseExpression("#root = 'aaaaa'").getValue(context2,String.class);//给根对象赋值
        System.out.println(res);
        String result2 = parser.parseExpression("#this='aaaa'").getValue(context2, String.class);//给当前上下文赋值
        System.out.println(result2);
        context2.setVariable("#newVar","newVar");
        String res2 = parser.parseExpression("#newVar=#root").getValue(context2,String.class);//给自定义变量赋值
        System.out.println(res2);
        /*对象方法调用*/
        Date date = new Date();
        StandardEvaluationContext context3 = new StandardEvaluationContext(date);
        long res3 = parser.parseExpression("getTime()").getValue(context3,long.class);
        System.out.println(res3);
    }
}
