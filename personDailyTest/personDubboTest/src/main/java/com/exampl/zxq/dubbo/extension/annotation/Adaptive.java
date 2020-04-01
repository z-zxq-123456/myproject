package com.exampl.zxq.dubbo.extension.annotation;

import com.exampl.zxq.dubbo.utils.URL;

import java.lang.annotation.*;

/**
 * inject dependency extension instance
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface Adaptive {

    /**
     *  If the specified parameters are not found from {@link URL}, then the default extension will be used for
        *      * dependency injection (specified in its interface's {@link SPI}).
        *      * <p>
        *      * For examples, given <code>String[] {"key1", "key2"}</code>:
        *      * <ol>
        *      * <li>find parameter 'key1' in URL, use its value as the extension's name</li>
        *      * <li>try 'key2' for extension's name if 'key1' is not found (or its value is empty) in URL</li>
        *      * <li>use default extension if 'key2' doesn't appear either</li>
        *      * <li>otherwise, throw {@link IllegalStateException}</li>
        *      * </ol>
     */
    String[] value() default {};

}