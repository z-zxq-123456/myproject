package com.dcits.galaxy.dal.mybatis.exception;


/**
 * NoShardFoundException异常
 * 
 * @author huang.zhe
 *
 */
public class NoShardFoundException extends DALException {

	private static final long serialVersionUID = 1885809745666498666L;

	/**
     * Creates a new NoShardFoundException.
     */
    public NoShardFoundException() {
        super();
    }

    /**
     * Constructs a new NoShardFoundException.
     *
     * @param message the reason for the exception
     */
    public NoShardFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new NoShardFoundException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public NoShardFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new NoShardFoundException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public NoShardFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
