package com.zxq.learn.handwrite.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-25 22:51
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAspect {

    String pkg() default "";

    String cls() default  "";
}
