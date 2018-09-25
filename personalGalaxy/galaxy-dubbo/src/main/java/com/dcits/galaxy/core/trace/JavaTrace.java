package com.dcits.galaxy.core.trace;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Java方法调用时，调用链信息采集注解，生效的必须是spring容器中的Bean。
 * 
 * @author xuecy
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JavaTrace {
    /**
     * 指定所属app 如果是纯java调用为对应的jar包名称
     * @return
     */
    String app() default "test";
}
