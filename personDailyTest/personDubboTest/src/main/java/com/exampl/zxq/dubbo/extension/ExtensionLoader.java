package com.exampl.zxq.dubbo.extension;

import com.exampl.zxq.dubbo.compiler.Compiler;
import com.exampl.zxq.dubbo.extension.annotation.Activate;
import com.exampl.zxq.dubbo.extension.annotation.Adaptive;
import com.exampl.zxq.dubbo.extension.annotation.SPI;
import com.exampl.zxq.dubbo.extension.factory.ExtensionFactory;
import com.exampl.zxq.dubbo.utils.Holder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

/**
 * load dubbo extensions
 * auto inject dependency extension
 * auto warp extension wrapper
 * default extension is an adaptive instance
 * @param <T>
 */
public class ExtensionLoader<T> {

    private static final ConcurrentHashMap<Class<?>,ExtensionLoader<?>> EXTENSION_LOADERS = new ConcurrentHashMap<Class<?>, ExtensionLoader<?>>();
    private final Holder cachedAdaptiveInstance = new Holder();
    private final Holder<Map<String,Class<?>>> cacheClasses = new Holder<Map<String, Class<?>>>();
    private final Map<String, Object> cachedActivates = new ConcurrentHashMap<String, Object>();
    private final ConcurrentMap<Class<?>, String> cachedNames = new ConcurrentHashMap<Class<?>, String>();

    private Throwable errorMsg;
    private final Class<?> type;
    private Class<?> cacheAdaptiveClass = null;
    private Set<Class<?>> cacheWrappeClasses;
    private ExtensionFactory objectFactory;
    private String cacheDefaultName;

    private Map<String, IllegalStateException> exceptions = new ConcurrentHashMap<String, IllegalStateException>();
    private static final Pattern NAME_SEPREATOR = Pattern.compile("\\s*[,]+\\s*");

    private final ConcurrentMap<String, Holder<Object>> cachedInstances = new ConcurrentHashMap<String, Holder<Object>>();
    private static final ConcurrentMap<Class<?>, Object> EXTENSION_INSTANCES = new ConcurrentHashMap<Class<?>, Object>();


    public ExtensionLoader(Class<?> type) {
        this.type = type;
        this.objectFactory =
                (type == ExtensionFactory.class ? null : ExtensionLoader.getExtensionLoader(ExtensionFactory.class).getAdaptiveExtension());
    }

    /**
     * 判断SPI是否在type上
     */
    public static <T> boolean hasExtensionAnnotation(Class<T> type){
        return type.isAnnotationPresent(SPI.class);
    }

    /**
     *创建loader
     */
    @SuppressWarnings("unchecked")
    public static <T> ExtensionLoader<T> getExtensionLoader(Class<T> type){
        if (!type.isInterface()){
            throw new IllegalArgumentException("Extension type " + type+" is not interface");
        }
        if (!hasExtensionAnnotation(type)){
            throw new IllegalArgumentException("Extension type " +type + "" +
                    " is not extension cause WITHOUT @" + SPI.class.getSimpleName() + " annotation");
        }
        ExtensionLoader<T> loader = (ExtensionLoader<T>)EXTENSION_LOADERS.get(type);
        if (loader == null){
            EXTENSION_LOADERS.putIfAbsent(type,new ExtensionLoader<T>(type));
            loader = ( ExtensionLoader<T>)EXTENSION_LOADERS.get(type);
        }
        return loader;
    }

    /**
     * 带锁创建实例
     */
    @SuppressWarnings("unchecked")
    public T getAdaptiveExtension(){
        Object instance = cachedAdaptiveInstance.getValue();
        if (instance == null){
            if (errorMsg == null){
                synchronized (cachedAdaptiveInstance){
                    instance = cachedAdaptiveInstance.getValue();
                    if (instance == null){
                          try {
                                instance = createInstance();
                              cachedAdaptiveInstance.setValue(instance);
                          }catch (Throwable e){
                              errorMsg = e;
                              throw new IllegalStateException("fail to create adaptive instance: " + e.toString(), e);
                          }
                    }
                }
            }else {
                throw new IllegalArgumentException("fail to create instance " + errorMsg.toString(),errorMsg);
            }
        }
        return (T)instance;
    }

    public T getDefaultExtension() {
        getExtensionClass();
        if (null == cacheDefaultName || cacheDefaultName.length() == 0
                || "true".equals(cacheDefaultName)) {
            return null;
        }
        return getExtension(cacheDefaultName);
    }

    /**
     * 实例构造
     * adaptive-->
     * extensionClass-->
     * adaptiveExtensionClass-->inject
     */
    @SuppressWarnings("unchecked")
    private T createInstance(){
        try {
            return injectExtension((T)getAdaptiveExtensionClass().newInstance());
        }catch (Exception e ){
            throw new IllegalStateException("can not create adaptive extension  " +type + " cause " + e.getMessage());
        }

    }

    /**
     * getAdaptiveExtensionClass
     * @return
     */
    public Class<?> getAdaptiveExtensionClass(){
        getExtensionClass();
        if (cacheAdaptiveClass != null){
            return cacheAdaptiveClass;
        }
        return cacheAdaptiveClass = createAdaptiveExtensionClass();
    }

    /**
     * fetch extensionClass
     * cache classes
     */
    private Map<String,Class<?>> getExtensionClass(){
            Map<String,Class<?>> classMap = cacheClasses.getValue();
            if (classMap == null){
                synchronized (cacheClasses){
                    classMap = cacheClasses.getValue();{
                        if (classMap == null){
                            classMap = loadExtensionClass();
                            cacheClasses.setValue(classMap);
                        }
                    }
                }
            }
            return classMap;
    }

    /**
     * loadExtensionClass
     * @return
     */
    private Map<String,Class<?>> loadExtensionClass(){

        final SPI defaultAnnotation = type.getAnnotation(SPI.class);
        if (defaultAnnotation != null){
            String value = defaultAnnotation.value();
            if ((value = value.trim()).length()> 0){
                String[] names = NAME_SEPREATOR.split(value);
                if (names.length > 1){
                    throw new IllegalStateException("more than one default extension " +type.getName() +
                            " :" + Arrays.toString(names));
                }
                if (names.length == 1) cacheDefaultName = names[0];
            }
        }
        Map<String,Class<?>> extensionClasses = new HashMap<String, Class<?>>();
        loadDirectory(extensionClasses,"META-INF/dubbo/internal/",type.getName());
        return extensionClasses;
    }

    /**
     * loadDirectory
     * @param extensionClasses
     * @param dir
     * @param name
     */
    private void loadDirectory(Map<String,Class<?>> extensionClasses,String dir,String name){

        String fileName = dir+name;
        try {
            Enumeration<URL> urls;
            java.lang.ClassLoader loader= findClassLoader();
            if (loader != null){
                urls = loader.getResources(fileName);
            }else {
                urls = java.lang.ClassLoader.getSystemResources(fileName);
            }
            if (urls != null){
                while (urls.hasMoreElements()){
                    URL url = urls.nextElement();
                    loadResources(extensionClasses,loader,url);
                }
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    /**
     * loadResources
     * @param extensionClass
     * @param classLoader
     * @param url
     */
    private void loadResources(Map<String,Class<?>> extensionClass,ClassLoader classLoader,URL url){

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
            String line;
            try {
                while((line = bufferedReader.readLine()) != null){
                    final int ci = line.indexOf("#");
                    if (ci > 0) line = line.substring(0,ci);
                    line = line.trim();

                    if (line.length() > 0){
                        try {
                            String name = null;
                            int i = line.indexOf("=");
                            if (i > 0){
                                name = line.substring(0, i).trim();
                                line = line.substring(i + 1).trim();
                            }
                            if (line.length() > 0){
                                loadClass(extensionClass,url,Class.forName(line,true,classLoader),name);
                            }
                        }catch (Throwable t){
                            IllegalStateException e = new IllegalStateException("fail to load extension class ");
                            exceptions.put(line,e);
                        }
                    }
                }
            }finally {
                bufferedReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * loadClass
     * @param extensionClass
     * @param resourceURL
     * @param clazz
     * @param name
     */
    private void loadClass(Map<String,Class<?>> extensionClass,java.net.URL resourceURL, Class<?> clazz, String name) throws NoSuchMethodException {

        if (!type.isAssignableFrom(clazz)){
            throw new IllegalStateException("error in load extension class interface "
                    +type + " ,class " + clazz.getName() + " is not subType of interface ");
        }
        if (clazz.isAnnotationPresent(Adaptive.class)){
            if (cacheAdaptiveClass == null){
                cacheAdaptiveClass = clazz;
            }else if (!cacheAdaptiveClass.equals(clazz)){
                throw new IllegalStateException("more than 1 adaptive class found");
            }
        }else if (isWrapperClass(clazz)){
            Set<Class<?>> wrappers = cacheWrappeClasses;
            if (wrappers == null){
                cacheWrappeClasses = new HashSet<Class<?>>();
            }
            wrappers.add(clazz);
        }else {
            clazz.getConstructor();
            String[] names = NAME_SEPREATOR.split(name);
            if (names != null && names.length > 0){
                Activate activate = clazz.getAnnotation(Activate.class);
                if (activate != null){
                    cachedActivates.put(names[0],activate);
                }
                for (String n : names){
                    if (!cachedNames.containsKey(clazz)){
                        cachedNames.put(clazz,n);
                    }
                    Class<?> c = extensionClass.get(n);
                    if (c == null){
                        extensionClass.put(n,clazz);
                    }else if (c != clazz){
                        throw new IllegalStateException("duplicate extension " +type.getName());
                    }
                }
            }
        }
    }

    /**
     * createAdaptiveExtensionClass
     * @return
     */
    private Class<?> createAdaptiveExtensionClass(){
        String code = createAdaptiveClassCode();
        ClassLoader loader = findClassLoader();
        Compiler compiler = ExtensionLoader.getExtensionLoader(Compiler.class).getAdaptiveExtension();
        return compiler.compile(code,loader);
    }

    private String createAdaptiveClassCode(){
        StringBuilder codeBuiler = new StringBuilder();
        Method[] methods = type.getMethods();
        boolean hasAdaptiveAnnotation = false;
        for (Method m : methods){
            if (m.isAnnotationPresent(Adaptive.class)){
                hasAdaptiveAnnotation = true;
                break;
            }
        }
        if (!hasAdaptiveAnnotation){
            throw new IllegalStateException(" no adaptive method on extension " + type.getName());
        }

        codeBuiler.append("package ").append(type.getPackage().getName()).append(";");
        codeBuiler.append("\nimport ").append(ExtensionLoader.class.getName()).append(";");
        codeBuiler.append("\npublic class ").append(type.getSimpleName()).append("$Adaptive").append(" implements ").append(type.getCanonicalName()).append(" {");
        codeBuiler.append("\nnative java.util.concurrent.atomic.AtomicInteger count = new java.util.concurrent.atomic.AtomicInteger (0);\n");

        for (Method m: methods){
            Class<?> returnType = m.getReturnType();
            Class<?> [] paramTypes = m.getParameterTypes();
            Class<?>[] exceptionTypes = m.getExceptionTypes();

            Adaptive adaptive = type.getAnnotation(Adaptive.class);
            StringBuilder code = new StringBuilder(512);
            if (adaptive == null){
                code.append("throw new UnsupportedOperateException(\"method ").append(m.toString()).append(" of interface ")
                        .append(type.getName()).append(" is not adaptive method!");
            }else{
                int urlTypeIndex = -1;
                for(int i = 0; i < paramTypes.length; i++){
                    if (paramTypes[i] .equals(URL.class)){
                        urlTypeIndex = i;
                        break;
                    }
                }
                //found url in parameter type
                if (urlTypeIndex != -1){
                    //checkout null pointer exception
                    String s = String.format("\nif (arg%d == null) throw new IllegalArgumentException(\"url == null\");",urlTypeIndex);
                    code.append(s);
                    s = String.format("\n%s url = arg%d;",URL.class.getName(),urlTypeIndex);
                    code.append(s);
                }else {

                    String attribMethod = null;
                    loopBack:
                    for (int i = 0; i < paramTypes.length; i++){
                        Method[] ms = paramTypes[i].getMethods();
                        for (Method method:ms){
                            String name = method.getName();
                            if ((name.startsWith("get") || name.length() > 3)
                                    && Modifier.isPublic(method.getModifiers())
                                    && !Modifier.isStatic(method.getModifiers())
                                    && method.getParameterTypes().length == 0
                                    && method.getReturnType() == URL.class){
                                urlTypeIndex = i;
                                attribMethod = name;
                                break loopBack;
                            }
                        }
                    }
                    if (attribMethod == null){
                        throw new IllegalStateException("fail to create adaptive class for interface "+type.getName()
                                                          + "  :not found url parameter or url attribute in parameters of method " + m.getName());
                    }
                    String s = String.format("\nif (arg%d == null) throw new IllegalArgumentException(\"%s argument == null\");"
                            ,urlTypeIndex,paramTypes[urlTypeIndex].getName());
                    code.append(s);

                    s = String.format("\nif (arg%d.%s() == null) throw new IllegalArgumentException(\"%s argument %s == null \");"
                            ,urlTypeIndex,attribMethod,paramTypes[urlTypeIndex].getName(),attribMethod);
                    code.append(s);

                    s = String.format("%s url = arg%d.%s();", URL.class.getName(), urlTypeIndex, attribMethod);
                    code.append(s);
                }

                String[] value = adaptive.value();
                if (value.length == 0){
                    char[] charArray = type.getSimpleName().toCharArray();
                    StringBuilder sb = new StringBuilder(128);
                    for (int i = 0; i < charArray.length; i++){
                        if (Character.isUpperCase(charArray[i])){
                            if (i != 0){
                                sb.append(".");
                            }
                            sb.append(Character.toLowerCase(charArray[i]));
                        }else {
                            sb.append(charArray[i]);
                        }
                    }
                    value = new String[]{sb.toString()};
                }
                boolean hasInvocation = false;
                for (int i = 0; i < paramTypes.length; i++){
                    if (paramTypes[i].getName().equals("org.apache.dubbo.rpc.Invocation")){
                        String  s = String.format("\nString methodName = arg%d.getMethodName();", i);
                        code.append(s);
                        hasInvocation = true;
                        break;
                    }
                }
                String defaultExtName = cacheDefaultName;
                String getNameCode = null;
                for (int i = value.length - 1; i >= 0; --i) {
                    if (i == value.length - 1) {
                        if (null != defaultExtName) {
                            if (!"protocol".equals(value[i]))
                                if (hasInvocation)
                                    getNameCode = String.format("url.getMethodParameter(methodName, \"%s\", \"%s\")", value[i], defaultExtName);
                                else
                                    getNameCode = String.format("url.getParameter(\"%s\", \"%s\")", value[i], defaultExtName);
                            else
                                getNameCode = String.format("( url.getProtocol() == null ? \"%s\" : url.getProtocol() )", defaultExtName);
                        } else {
                            if (!"protocol".equals(value[i]))
                                if (hasInvocation)
                                    getNameCode = String.format("url.getMethodParameter(methodName, \"%s\", \"%s\")", value[i], defaultExtName);
                                else
                                    getNameCode = String.format("url.getParameter(\"%s\")", value[i]);
                            else
                                getNameCode = "url.getProtocol()";
                        }
                    } else {
                        if (!"protocol".equals(value[i]))
                            if (hasInvocation)
                                getNameCode = String.format("url.getMethodParameter(methodName, \"%s\", \"%s\")", value[i], defaultExtName);
                            else
                                getNameCode = String.format("url.getParameter(\"%s\", %s)", value[i], getNameCode);
                        else
                            getNameCode = String.format("url.getProtocol() == null ? (%s) : url.getProtocol()", getNameCode);
                    }
                }
                code.append("\nString extName = ").append(getNameCode).append(";");

                String s = String.format("\nif(extName == null) " +
                                "throw new IllegalStateException(\"Fail to get extension(%s) name from url(\" + url.toString() + \") use keys(%s)\");",
                        type.getName(), Arrays.toString(value));
                code.append(s);

                code.append(String.format("\n%s extension = null;\n try {\nextension = (%<s)%s.getExtensionLoader(%s.class).getExtension(extName);\n}catch(Exception e){\n",
                        type.getName(), ExtensionLoader.class.getSimpleName(), type.getName()));
                code.append(String.format("if (count.incrementAndGet() == 1) {\nlogger.warn(\"Failed to find extension named \" + extName + \" for type %s, will use default extension %s instead.\", e);\n}\n",
                        type.getName(), defaultExtName));
                code.append(String.format("extension = (%s)%s.getExtensionLoader(%s.class).getExtension(\"%s\");\n}",
                        type.getName(), ExtensionLoader.class.getSimpleName(), type.getName(), defaultExtName));

                // return statement
                if (!returnType.equals(void.class)) {
                    code.append("\nreturn ");
                }

                s = String.format("extension.%s(", m.getName());
                code.append(s);
                for (int i = 0; i < paramTypes.length; i++) {
                    if (i != 0)
                        code.append(", ");
                    code.append("arg").append(i);
                }
                code.append(");");
            }

            codeBuiler.append("\npublic ").append(returnType.getCanonicalName()).append(" ").append(m.getName()).append("(");

            for (int i = 0; i < paramTypes.length; i++){
                if (i > 0){
                    codeBuiler.append(", ");
                }
                codeBuiler.append(paramTypes[i].getCanonicalName());
                codeBuiler.append(" ");
                codeBuiler.append("arg").append(i);
            }
            codeBuiler.append(")");

            if (exceptionTypes.length > 0){
                codeBuiler.append(" throws ");
                for (int i = 0; i < exceptionTypes.length; i++) {
                    if (i > 0) {
                        codeBuiler.append(", ");
                    }
                    codeBuiler.append(exceptionTypes[i].getCanonicalName());
                }
            }
            codeBuiler.append(" {");
            codeBuiler.append(code.toString());
            codeBuiler.append("\n}");
        }
        codeBuiler.append("\n}");
        return codeBuiler.toString();
    }

    /**
     * inject
     * @param instance
     * @return
     */
    private T injectExtension(T instance){
        if (objectFactory != null){
            for (Method method:instance.getClass().getMethods()){
                if (method.getName().startsWith("set")
                        && method.getParameterTypes().length == 1
                        && Modifier.isPublic(method.getModifiers())) {
                    Class<?> pt = method.getParameterTypes()[0];
                    try {
                        String property = method.getName().length() > 3 ? method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4) : "";
                        Object object = objectFactory.getExtension(pt, property);
                        if (object != null) {
                            method.invoke(instance, object);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }

    /**
     * 获取是否包装类
     * @param claz
     * @return
     */
    private boolean isWrapperClass(Class claz){
        try {
            claz.getConstructor(type);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }


    /**
     * classLoader
     * @return
     */
    public static ClassLoader findClassLoader(){
        ClassLoader clz = null;
        try {
            clz = Thread.currentThread().getContextClassLoader();
        }catch (Exception e){
        }
        if (clz == null){
            clz= ExtensionLoader.class.getClassLoader();
            if (clz == null){
                clz = ClassLoader.getSystemClassLoader();
            }
        }
        return clz;
    }

    public Set<String> getSupportedExtensions(){
        Map<String,Class<?>> classzs = getExtensionClass();
        return Collections.unmodifiableNavigableSet(new TreeSet<>(classzs.keySet()));
    }



    public T getExtension(String name) {
        if (name == null || name.length() == 0)
            throw new IllegalArgumentException("Extension name == null");
        if ("true".equals(name)) {
            return getDefaultExtension();
        }
        Holder<Object> holder = cachedInstances.get(name);
        if (holder == null) {
            cachedInstances.putIfAbsent(name, new Holder<Object>());
            holder = cachedInstances.get(name);
        }
        Object instance = holder.getValue();
        if (instance == null) {
            synchronized (holder) {
                instance = holder.getValue();
                if (instance == null) {
                    instance = createExtension(name);
                    holder.setValue(instance);
                }
            }
        }
        return (T) instance;
    }

    private T createExtension(String name) {
        Class<?> clazz = getExtensionClass().get(name);
        if (clazz == null) {
//            throw findException(name);
        }
        try {
            T instance = (T) EXTENSION_INSTANCES.get(clazz);
            if (instance == null) {
                EXTENSION_INSTANCES.putIfAbsent(clazz, clazz.newInstance());
                instance = (T) EXTENSION_INSTANCES.get(clazz);
            }
            injectExtension(instance);
            Set<Class<?>> wrapperClasses = cacheWrappeClasses;
            if (wrapperClasses != null && !wrapperClasses.isEmpty()) {
                for (Class<?> wrapperClass : wrapperClasses) {
                    instance = injectExtension((T) wrapperClass.getConstructor(type).newInstance(instance));
                }
            }
            return instance;
        } catch (Throwable t) {
            throw new IllegalStateException("Extension instance(name: " + name + ", class: " +
                    type + ")  could not be instantiated: " + t.getMessage(), t);
        }
    }


}
