package com.exampl.zxq.dubbo.compiler;

import com.exampl.zxq.dubbo.extension.annotation.SPI;

/**
 * compiler
 */

@SPI("javassist")
public interface Compiler {

    public Class<?> compile(String code,ClassLoader classLoader);
}
