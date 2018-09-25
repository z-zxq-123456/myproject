/**
 * <p>Title: ClassLoaderUtil.java</p>
 * <p>Description: Class和Resource工具类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0
 */
package com.dcits.galaxy.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Tim
 * @version V1.0
 * @description Class和Resource工具类
 * @update 2014年9月15日 下午2:06:43
 */

public class ClassLoaderUtils {

    private static Logger logger = LoggerFactory
            .getLogger(ClassLoaderUtils.class);

    private static final Map<String, Class<?>> classCache = new ConcurrentHashMap<>();

    /**
     * 加载Java类。 使用全限定类名
     *
     * @param className
     * @return
     */
    public static Class<?> loadClass(String className) throws ClassNotFoundException {
        Class<?> clazz = null;
        if (classCache.containsKey(className)) {
            return classCache.get(className);
        }
        clazz = ClassUtils.forName(className, getClassLoader());
        if (null != clazz) {
            classCache.put(className, clazz);
        }
        return clazz;
    }

    public static Object newInstance(String classImpl)
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        return loadClass(classImpl).newInstance();
    }

    public static Object newInstance(String classImpl, Class<?>[] pType,
                                     Object[] obj) throws InstantiationException,
            IllegalAccessException, ClassNotFoundException,
            IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException {
        return loadClass(classImpl).getConstructor(pType).newInstance(obj);
    }

    /**
     * 得到类加载器
     *
     * @return
     */
    public static ClassLoader getClassLoader() {
        return ClassLoaderUtils.class.getClassLoader();
    }

    /**
     * 提供相对于classpath的资源路径，返回文件的输入流
     *
     * @param relativePath 必须传递资源的相对路径
     *                     是相对于classpath的路径。如果需要查找classpath外部的资源，需要使用../来查找
     * @return 文件输入流
     * @throw sIOException
     * @throw sMalformedURLException
     */
    public static InputStream getStream(String relativePath)
            throws MalformedURLException, IOException {
        if (!relativePath.contains("../")) {
            return getClassLoader().getResourceAsStream(relativePath);

        } else {
            return ClassLoaderUtils.getStreamByExtendResource(relativePath);
        }
    }

    /**
     * @param url
     * @return
     * @throw sIOException
     */
    public static InputStream getStream(URL url) throws IOException {
        if (url != null) {

            return url.openStream();

        } else {
            return null;
        }
    }

    /**
     * @param relativePath 必须传递资源的相对路径
     *                     是相对于classpath的路径。如果需要查找classpath外部的资源，需要使用../来查找
     * @return
     * @throw sMalformedURLException
     * @throw sIOException
     */
    public static InputStream getStreamByExtendResource(String relativePath)
            throws IOException {
        return ClassLoaderUtils.getStream(ClassLoaderUtils
                .getExtendResource(relativePath));
    }

    /**
     * 提供相对于classpath的资源路径，返回属性对象，它是一个散列表
     *
     * @param resource
     * @return
     */
    public static Properties getProperties(String resource) {
        InputStream in = null;
        try {
            in = getStream(resource);
            if (null == in) {
                in = new BufferedInputStream(new FileInputStream(resource));
            }
        } catch (IOException e) {
            throw new RuntimeException("couldn't load properties file '"
                    + resource + "'", e);
        }
        return getProperties(in);
    }

    /**
     * 提供相对于classpath的资源路径，返回属性对象，它是一个散列表
     *
     * @param in
     * @return
     */
    public static Properties getProperties(InputStream in) {
        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException("couldn't load properties inputStream",
                    e);
        }

        return properties;
    }

    /**
     * 得到本Class所在的ClassLoader的Classpat的绝对路径。 URL形式的
     *
     * @return
     */
    public static String getAbsolutePathOfClassLoaderClassPath() {
        String classPath = ClassLoaderUtils.getClassLoader().getResource("")
                .toString();
        logger.info(classPath);
        return classPath;
    }

    /**
     * @param relativePath 必须传递资源的相对路径。是相对于classpath的路径。如果需要查找classpath外部的资源，需要使用. ./来查找
     * @return 资源的绝对URL
     * @throws MalformedURLException
     */
    public static URL getExtendResource(String relativePath)
            throws MalformedURLException {
        URL resourceAbsoluteURL = null;
        logger.info("A relative path to the incoming:" + relativePath);
        if (!relativePath.contains("../")) {
            resourceAbsoluteURL = ClassLoaderUtils.getResource(relativePath);
            logger.info("URL:" + resourceAbsoluteURL);
            return resourceAbsoluteURL;
        }
        String classPathAbsolutePath = ClassLoaderUtils
                .getAbsolutePathOfClassLoaderClassPath();
        if (relativePath.substring(0, 1).equals("/")) {
            relativePath = relativePath.substring(1);
        }
        logger.info(String.valueOf(relativePath.lastIndexOf("../")));

        String wildcardString = relativePath.substring(0,
                relativePath.lastIndexOf("../") + 3);
        relativePath = relativePath
                .substring(relativePath.lastIndexOf("../") + 3);
        int containSum = ClassLoaderUtils.containSum(wildcardString, "../");
        classPathAbsolutePath = ClassLoaderUtils.cutLastString(
                classPathAbsolutePath, "/", containSum);
        String resourceAbsolutePath = classPathAbsolutePath + relativePath;
        logger.info("Absolute path:" + resourceAbsolutePath);
        logger.info("URL:" + resourceAbsoluteURL);
        resourceAbsoluteURL = new URL(resourceAbsolutePath);
        logger.info("URL:" + resourceAbsoluteURL);
        return resourceAbsoluteURL;
    }

    /**
     * @param source
     * @param dest
     * @return
     */
    private static int containSum(String source, String dest) {
        int containSum = 0;
        int destLength = dest.length();
        while (source.contains(dest)) {
            containSum = containSum + 1;
            source = source.substring(destLength);

        }

        return containSum;
    }

    /**
     * @param source
     * @param dest
     * @param num
     * @return
     */
    private static String cutLastString(String source, String dest, int num) {
        for (int i = 0; i < num; i++) {
            source = source.substring(0,
                    source.lastIndexOf(dest, source.length() - 2) + 1);

        }

        return source;
    }

    public static URL getResource(String resource) {
        return ClassLoaderUtils.getClassLoader().getResource(resource);
    }

    public static String getHome() {
        File f = new File(ClassLoaderUtils.getResource("").getFile());
        return f.getParentFile().getParent();
    }
}