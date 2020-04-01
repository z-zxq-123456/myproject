package com.exampl.zxq.dubbo.extension.annotation;

import java.lang.annotation.*;

/**
 * Marker for extension interface
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SPI {

     String value() default "";
}