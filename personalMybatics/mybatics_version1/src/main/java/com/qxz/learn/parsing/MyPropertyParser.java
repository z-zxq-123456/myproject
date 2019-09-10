package com.qxz.learn.parsing;

import java.util.Properties;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/10/9
 */
public class MyPropertyParser {

    public static String parse(String String, Properties variables){
        VariableTokenHandler handler = new VariableTokenHandler(variables);
        MyGenericTokenParser parser = new MyGenericTokenParser("${","}",handler);
        return parser.parse(String);
    }

    public static class VariableTokenHandler implements MyTokenHandler{

        private Properties variables;

        public VariableTokenHandler(Properties variables) {
            this.variables = variables;
        }

        @Override
        public String handleToken(String content) {
            if (variables != null && variables.containsKey(content)) {
                return variables.getProperty(content);
            }
            return "${" + content + "}";
        }
    }
}
