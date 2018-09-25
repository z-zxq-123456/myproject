package com.dcits.galaxy.dal.mybatis.exception;


/**
 * NoExecutorFoundException异常
 * 
 * @author fankj
 *
 */
public class NoExecutorFoundException extends DALException {

	private static final long serialVersionUID = 1885809745666498666L;

	/**
     * Creates a new NoExecutorFoundException.
     */
    public NoExecutorFoundException() {
        super();
    }

    /**
     * Constructs a new NoExecutorFoundException.
     *
     * @param message the reason for the exception
     */
    public NoExecutorFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new NoExecutorFoundException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public NoExecutorFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new NoExecutorFoundException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public NoExecutorFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
