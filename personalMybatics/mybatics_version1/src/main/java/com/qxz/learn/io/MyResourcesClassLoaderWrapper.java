package com.qxz.learn.io;

import java.io.InputStream;
import java.net.URL;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/30
 */
public class MyResourcesClassLoaderWrapper {

    ClassLoader defaultClassLoader;
    ClassLoader systemClassLoader;

    public MyResourcesClassLoaderWrapper() {

        systemClassLoader = ClassLoader.getSystemClassLoader();
    }

    ClassLoader[] getClassLoaders(ClassLoader classLoader) {
        return new ClassLoader[]{
                classLoader,
                defaultClassLoader,
                Thread.currentThread().getContextClassLoader(),
                getClass().getClassLoader(),
                systemClassLoader};
    }

    public InputStream getResourceAsStream(String resource, ClassLoader classLoader) {
        return getResourceAsStream(resource, getClassLoaders(classLoader));
    }

    public InputStream getResourceAsStream(String resource) {
        return getResourceAsStream(resource, getClassLoaders(null));
    }

    public URL getResourceAsURL(String resource, ClassLoader classLoader) {
        return getResourceAsURL(resource, getClassLoaders(classLoader));
    }

    public URL getResourceAsURL(String resource) {
        return getResourceAsURL(resource, getClassLoaders(null));
    }

    URL getResourceAsURL(String resource, ClassLoader[] classLoader) {
        URL url;
        for (ClassLoader cl : classLoader) {
            if (null != cl) {
                url = cl.getResource(resource);
                if (null == url) {
                    url = cl.getResource("/" + resource);
                }
                if (null != url) {
                    return url;
                }
            }
        }
        return null;
    }

    InputStream getResourceAsStream(String resource, ClassLoader[] classLoader) {
        for (ClassLoader cl : classLoader) {
            if (null != cl) {
                InputStream returnValue = cl.getResourceAsStream(resource);
                if (null == returnValue) {
                    returnValue = cl.getResourceAsStream("/" + resource);
                }
                if (null != returnValue) {
                    return returnValue;
                }
            }
        }
        return null;
    }

    public Class<?> classForName(String name) throws ClassNotFoundException {
        return classForName(name, getClassLoaders(null));
    }

    /**
     * classForName
     *
     * @param name
     * @param classLoader
     * @return
     * @throws ClassNotFoundException
     */
    Class<?> classForName(String name, ClassLoader[] classLoader) throws ClassNotFoundException {

        for (ClassLoader cl : classLoader) {
            if (null != cl) {
                try {
                    Class<?> c = Class.forName(name, true, cl);
                    if (null != c) {
                        return c;
                    }
                } catch (ClassNotFoundException e) {
                }
            }
        }
        return null;
    }


}
