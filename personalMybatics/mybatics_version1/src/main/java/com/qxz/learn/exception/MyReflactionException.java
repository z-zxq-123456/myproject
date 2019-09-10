package com.qxz.learn.exception;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/28
 */
public class MyReflactionException extends RuntimeException {

    public MyReflactionException(String message) {
        super(message);
    }

    public MyReflactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
