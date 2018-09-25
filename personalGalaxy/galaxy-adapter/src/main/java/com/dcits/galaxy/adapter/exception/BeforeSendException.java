/**
 * Title: Galaxy(Distributed service platform)
 * File: BeforeSendException.java
 * Copyright: Copyright (c) 2014-2016
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.galaxy.adapter.exception;

import com.dcits.galaxy.base.exception.GalaxyException;

/**
 * 发送前异常，继承GalaxyException异常类
 * <p>Created on 2015/10/21.</p>
 *
 * @author Tim <guotao@dcits.com>
 * @version 1.0.0
 * @see GalaxyException
 * @since 1.7
 */
public class BeforeSendException extends GalaxyException {

    private static final long serialVersionUID = 7473815505404138912L;

    /**
     * Creates a new BeforeSendException.
     */
    public BeforeSendException() {
        super();
    }

    /**
     * Constructs a new BeforeSendException.
     *
     * @param message
     *         the reason for the exception
     */
    public BeforeSendException(String message) {
        super(message);
    }

    /**
     * Constructs a new BeforeSendException.
     *
     * @param cause
     *         the underlying Throwable that caused this exception to be thrown.
     */
    public BeforeSendException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new BeforeSendException.
     *
     * @param message
     *         the reason for the exception
     * @param cause
     *         the underlying Throwable that caused this exception to be thrown.
     */
    public BeforeSendException(String message, Throwable cause) {
        super(message, cause);
    }

}
