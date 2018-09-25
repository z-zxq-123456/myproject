package com.dcits.orion.stria.impl;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

import com.dcits.orion.stria.impl.SpelExpression;

/**
 * Created by Tim on 2016/6/15.
 */
public class SpelExpressionTest extends TestCase {
    SpelExpression spelExpression;

    @Override
    public void setUp() {
        spelExpression = new SpelExpression();
    }

    public void testEval() throws Exception {
        Map<String, Object> map = new HashMap<>();
        User user = new User();
        user.setAge(19);
        user.setName("zhangSan");
        map.put("bb", user);
        String name = spelExpression.eval(String.class, "#bb.name", map);
        int age = spelExpression.eval(Integer.class, "#bb.age", map);
        String name1 = spelExpression.eval(String.class, "name", map, user);
        Assert.assertEquals("zhangSan", name);
        Assert.assertEquals("zhangSan", name1);
        Assert.assertEquals(19, age);
    }

    public void testEval1() throws Exception {

    }

    public static class User {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}