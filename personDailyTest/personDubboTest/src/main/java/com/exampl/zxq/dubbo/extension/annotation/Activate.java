package com.exampl.zxq.dubbo.extension.annotation;

import java.lang.annotation.*;

/**
 *  * Activate. This annotation is useful for automatically activate certain extensions with the given criteria,
 *  * for examples: <code>@Activate</code> can be used to load certain <code>Filter</code> extension when there are
 *  * multiple implementations.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface Activate {

    String [] group() default {};

    String [] value() default {};

    int order() default 0;

}
