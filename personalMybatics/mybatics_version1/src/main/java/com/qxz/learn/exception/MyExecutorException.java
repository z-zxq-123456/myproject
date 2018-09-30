package com.qxz.learn.exception;

public class MyExecutorException extends RuntimeException {

    public MyExecutorException(String message) {
        super(message);
    }

    public MyExecutorException(String message, Throwable cause) {
        super(message, cause);
    }
}
