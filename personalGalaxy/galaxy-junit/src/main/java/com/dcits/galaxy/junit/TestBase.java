package com.dcits.galaxy.junit;

import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Tim on 2015/6/11.
 */
public class TestBase extends TestCase {

    protected ApplicationContext context = new ClassPathXmlApplicationContext(
            "classpath*:META-INF/spring/*.xml");

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }
}

