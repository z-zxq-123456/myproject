package com.dcits.orion.stria.test.spel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.galaxy.base.util.ClassLoaderUtils;
import com.dcits.galaxy.base.util.FileUtils;
import com.dcits.galaxy.base.util.JsonUtils;
import com.dcits.galaxy.base.util.ObjectUtils;

import junit.framework.TestCase;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TestSpel extends TestCase {

    public void testService() {
        String filePath = ClassLoaderUtils.getResource("RB31_01.txt").getFile();
        String jsonStr = FileUtils.readFile(filePath);
        jsonStr = JsonUtils.convertMsg(jsonStr, JsonUtils.HUMP_TYPE);
        JSONObject in = JSON.parseObject(jsonStr);
        JSONObject inMsg = new JSONObject();

        inMsg.put("in", in);
        inMsg.put("etc", new JSONObject());
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("_inMsg", inMsg);
        m.put("user", new User("zhangsan", "man222",""));


        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(new User("zhangsan", "man222",""));
        context.setVariable("M", m);
        for (Map.Entry<String, Object> entry : m.entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }

        System.out
                .println(parser.parseExpression("#M['user']['name']").getValue(context, String.class));


        BigDecimal age = parser.parseExpression("age").getValue(context, BigDecimal.class);
        System.out
                .println(age);

        User2 bean = new User2();
        ObjectUtils.executeMethod(bean, "setAge", new Object[]{ parser.parseExpression("age").getValue(context, BigDecimal.class)});
        System.out
                .println(bean.getAge());
    }
}
