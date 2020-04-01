package com.exampl.zxq.dubbo.compiler;

import javassist.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavasistCompiler extends AbstractCompiler {

    private static final Pattern IMPORT_PATTERN = Pattern.compile("import\\s+([\\w\\.\\*]+);\n");
    private static final Pattern EXTENDS_PATTERN = Pattern.compile("\\s+extends\\s+([\\w\\.]+)[^\\{]*\\{\n");
    private static final Pattern IMPLEMENTS_PATTERN = Pattern.compile("\\s+implements\\s+([\\w\\.]+)\\s*\\{\n");
    private static final Pattern METHODS_PATTERN = Pattern.compile("\n(private|public|protected)\\s+");
    private static final Pattern FIELD_PATTERN = Pattern.compile("[^\n]+=[^\n]+;");

    protected Class<?> doCompile(String name, String source) throws Throwable {

        int i = name.lastIndexOf(".");
        String className = i < 0 ? name : name.substring(i + 1);
        ClassPool classPool = new ClassPool(true);
        classPool.appendClassPath(new LoaderClassPath(this.getClass().getClassLoader()));
        Matcher matcher = IMPORT_PATTERN.matcher(source);
        List<String> importPackages = new ArrayList<String>();
        Map<String, String> fullNames = new HashMap<String, String>();

        while (matcher.find()){
            String pk = matcher.group(1);
            if (pk.endsWith(".*")){
                String pkName = pk.substring(0,pk.length() - 2);
                classPool.importPackage(pkName);
                importPackages.add(pkName);
            }else {
                int pos = pk.lastIndexOf(".");
                if (pos > 0){
                    String pkName = pk.substring(0,pos);
                    classPool.importPackage(pkName);
                    importPackages.add(pkName);
                    fullNames.put(pkName.substring(pos+1),pkName);
                }
            }
        }
        String[] packages = importPackages.toArray(new String[0]);
        matcher = EXTENDS_PATTERN.matcher(source);
        CtClass ct;
        if (matcher.find()){
            String extend = matcher.group(1).trim();
            String extendClass;
            if (extend.contains(".")){
                extendClass = extend;
            }else if (fullNames.containsKey(extend)){
                extendClass = fullNames.get(extend);
            }else {
                extendClass = ClassUtils.forName(packages,extend).getName();
            }
            ct = classPool.makeClass(name,classPool.get(extendClass));
        }else {
            ct = classPool.makeClass(name);
        }

        matcher = IMPLEMENTS_PATTERN.matcher(source);
        if (matcher.find()) {
            String[] ifaces = matcher.group(1).trim().split("\\,");
            for (String iface : ifaces) {
                iface = iface.trim();
                String ifaceClass;
                if (iface.contains(".")) {
                    ifaceClass = iface;
                } else if (fullNames.containsKey(iface)) {
                    ifaceClass = fullNames.get(iface);
                } else {
                    ifaceClass = ClassUtils.forName(packages, iface).getName();
                }
                ct.addInterface(classPool.get(ifaceClass));
            }
        }
        String body = source.substring(source.indexOf("{") + 1, source.length() - 1);
        String[] methods = METHODS_PATTERN.split(body);
        for (String method : methods) {
            method = method.trim();
            if (method.length() > 0) {
                if (method.startsWith(className)) {
                    ct.addConstructor(CtNewConstructor.make("public " + method, ct));
                } else if (FIELD_PATTERN.matcher(method).matches()) {
                    ct.addField(CtField.make("private " + method, ct));
                } else {
                    ct.addMethod(CtNewMethod.make("public " + method, ct));
                }
            }
        }
        return ct.toClass(Thread.currentThread().getContextClassLoader(), JavasistCompiler.class.getProtectionDomain());
    }


}
