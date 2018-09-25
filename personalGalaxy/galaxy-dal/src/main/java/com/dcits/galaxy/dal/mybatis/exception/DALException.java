package com.dcits.galaxy.dal.mybatis.exception;

import com.dcits.galaxy.base.exception.GalaxyException;

/**
 * GalaxyTransaction异常
 * 
 * @author fankj
 *
 */
public class DALException extends GalaxyException {

	private static final long serialVersionUID = 1885809745666498666L;

	/**
     * Creates a new GalaxyException.
     */
    public DALException() {
        super();
    }

    /**
     * Constructs a new GalaxyException.
     *
     * @param message the reason for the exception
     */
    public DALException(String message) {
        super(message);
    }

    /**
     * Constructs a new GalaxyException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public DALException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new GalaxyException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public DALException(String message, Throwable cause) {
        super(message, cause);
    }

}
