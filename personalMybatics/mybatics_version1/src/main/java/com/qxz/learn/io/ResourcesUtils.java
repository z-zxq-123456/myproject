package com.qxz.learn.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/30
 */
public class ResourcesUtils {

    private static MyResourcesClassLoaderWrapper classLoaderWrapper = new MyResourcesClassLoaderWrapper();
    private static Charset charset;

    public static MyResourcesClassLoaderWrapper getClassLoaderWrapper() {
        return classLoaderWrapper;
    }

    public static void setClassLoaderWrapper(MyResourcesClassLoaderWrapper classLoaderWrapper) {
        ResourcesUtils.classLoaderWrapper = classLoaderWrapper;
    }

    public static Charset getCharset() {
        return charset;
    }

    public static void setCharset(Charset charset) {
        ResourcesUtils.charset = charset;
    }

    public static Class<?> classForName(String className)throws ClassNotFoundException{
        return classLoaderWrapper.classForName(className);
    }

    public static InputStream getUrlAsStream(String urlString) throws IOException {
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        return conn.getInputStream();
    }

    public static URL getResourceURL(ClassLoader loader, String resource) throws IOException {
        URL url = classLoaderWrapper.getResourceAsURL(resource, loader);
        if (url == null) {
            throw new IOException("Could not find resource " + resource);
        }
        return url;
    }

    public static Properties getResourceAsProperties(ClassLoader loader, String resource) throws IOException {
        Properties props = new Properties();
        InputStream in = getResourceAsStream(loader, resource);
        props.load(in);
        in.close();
        return props;
    }

    public static InputStream getResourceAsStream(ClassLoader loader, String resource) throws IOException {
        InputStream in = classLoaderWrapper.getResourceAsStream(resource, loader);
        if (in == null) {
            throw new IOException("Could not find resource " + resource);
        }
        return in;
    }
}
