package com.dcits.galaxy.dal.mybatis.exception;


/**
 * GalaxyTransaction异常
 * 
 * @author fankj
 *
 */
public class UnexpectedTransactionException extends DALException {

	private static final long serialVersionUID = 1885809745666498666L;

	/**
     * Creates a new GalaxyException.
     */
    public UnexpectedTransactionException() {
        super();
    }

    /**
     * Constructs a new GalaxyException.
     *
     * @param message the reason for the exception
     */
    public UnexpectedTransactionException(String message) {
        super(message);
    }

    /**
     * Constructs a new GalaxyException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public UnexpectedTransactionException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new GalaxyException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public UnexpectedTransactionException(String message, Throwable cause) {
        super(message, cause);
    }

}
