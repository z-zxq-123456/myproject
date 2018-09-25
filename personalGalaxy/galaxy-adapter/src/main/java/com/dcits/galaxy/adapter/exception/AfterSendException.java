/**
 * Title: Galaxy(Distributed service platform)
 * File: AfterSendException.java
 * Copyright: Copyright (c) 2014-2016
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.galaxy.adapter.exception;

import com.dcits.galaxy.base.exception.GalaxyException;

/**
 * 发送后异常，继承GalaxyException异常类
 * <p>Created on 2015/10/21.</p>
 *
 * @author Tim <guotao@dcits.com>
 * @version 1.0.0
 * @see GalaxyException
 * @since 1.7
 */
public class AfterSendException extends GalaxyException {

    private static final long serialVersionUID = 7114062890797763348L;

    /**
     * Creates a new AfterSendException.
     */
    public AfterSendException() {
        super();
    }

    /**
     * Constructs a new AfterSendException.
     *
     * @param message
     *         the reason for the exception
     */
    public AfterSendException(String message) {
        super(message);
    }

    /**
     * Constructs a new AfterSendException.
     *
     * @param cause
     *         the underlying Throwable that caused this exception to be thrown.
     */
    public AfterSendException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new AfterSendException.
     *
     * @param message
     *         the reason for the exception
     * @param cause
     *         the underlying Throwable that caused this exception to be thrown.
     */
    public AfterSendException(String message, Throwable cause) {
        super(message, cause);
    }

}
