package com.dcits.orion.stria.test.reflection;

import com.dcits.galaxy.base.util.ClassLoaderUtils;

import junit.framework.TestCase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Tim on 2015/12/7.
 */
public class TestStira extends TestCase {

    public void testClass() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            Class<?> test = ClassLoaderUtils.loadClass("FlowServiceTest");
            Object o = test.newInstance();
            Method m = test.getMethod("setTest", String.class);
            m.invoke(o, new Object[]{String.valueOf(i)});
        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }


}
