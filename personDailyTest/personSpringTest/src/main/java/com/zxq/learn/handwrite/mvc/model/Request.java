package com.zxq.learn.handwrite.mvc.model;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-31 17:23
 **/
public class Request {

    private String requestMethod;
    private String requestPath;

    public Request(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }
}
