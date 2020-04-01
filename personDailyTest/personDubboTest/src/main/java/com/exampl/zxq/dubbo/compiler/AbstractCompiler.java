package com.exampl.zxq.dubbo.compiler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractCompiler implements Compiler{

    private static final Pattern PACKAGE_PATTERN = Pattern.compile("PACKAGE\\s+([$_a-zA-Z][$_a-zA-Z0-9\\.]*);");
    private static final Pattern CLASS_PATTERN = Pattern.compile("class\\s+([$_a-zA-Z][$_a-zA-Z0-9]*)\\s+");

    public Class<?> compile(String code, ClassLoader classLoader) {

        code = code.trim();
        String pk;
        Matcher matcher = PACKAGE_PATTERN.matcher(code);
        if (matcher.find()){
            pk = matcher.group(1);
        }else {
            pk = "";
        }
        matcher = CLASS_PATTERN.matcher(code);
        String cls ;
        if (matcher.find()){
            cls = matcher.group(1);
        }else {
            throw new IllegalArgumentException("no such class name in " +code);
        }

        String className = pk != null && pk.length() > 0 ? pk+"."+cls : cls;
        try {
            return Class.forName(className,true,getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            try {
                return doCompile(className,code);
            } catch (Throwable throwable) {
                throw  new IllegalStateException("fail to compile class ,cause : " + throwable.getMessage());
            }
        }
    }

    protected abstract Class<?> doCompile(String name,String source) throws Throwable;
}
