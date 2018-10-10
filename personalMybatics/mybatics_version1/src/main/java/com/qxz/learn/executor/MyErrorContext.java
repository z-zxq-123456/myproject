package com.qxz.learn.executor;


/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/27
 */
public class MyErrorContext {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");

    private static final ThreadLocal<MyErrorContext> LOCAL = ThreadLocal.withInitial(MyErrorContext::instance);

    private MyErrorContext stored;
    private String resource;
    private String activity;
    private String object;
    private String message;
    private String sql;
    private Throwable cause;


    public MyErrorContext() {
    }

    public static MyErrorContext instance() {
        MyErrorContext context = LOCAL.get();
        if (context == null) {
            context = new MyErrorContext();
            LOCAL.set(context);
        }
        return context;
    }

    public MyErrorContext sql(String sql) {
        this.sql = sql;
        return this;
    }

    public MyErrorContext store() {
        stored = this;
        LOCAL.set(new MyErrorContext());
        return LOCAL.get();
    }

    public MyErrorContext reCall() {
        if (stored != null) {
            LOCAL.set(stored);
            stored = null;
        }
        return LOCAL.get();
    }

    public MyErrorContext reset() {
        resource = null;
        activity = null;
        object = null;
        message = null;
        sql = null;
        cause = null;
        LOCAL.remove();
        return this;
    }

    public MyErrorContext activity(String activity) {
        this.activity = activity;
        return this;
    }

    public MyErrorContext object(String obj) {
        this.object = obj;
        return this;
    }

    public MyErrorContext source(String source) {
        this.resource = source;
        return this;
    }
}
