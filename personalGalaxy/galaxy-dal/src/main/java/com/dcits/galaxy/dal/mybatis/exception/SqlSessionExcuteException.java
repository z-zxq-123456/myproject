package com.dcits.galaxy.dal.mybatis.exception;


/**
 * NoExecutorFoundException异常
 *
 * @author fankj
 *
 */
public class SqlSessionExcuteException extends DALException {

	/**
	 *
	 */
	private static final long serialVersionUID = 8036869033485577543L;

	/**
	 * Creates a new NoExecutorFoundException.
	 */
	public SqlSessionExcuteException() {
		super();
	}

	/**
	 * Constructs a new NoExecutorFoundException.
	 *
	 * @param message the reason for the exception
	 */
	public SqlSessionExcuteException(String message) {
		super(message);
	}

	/**
	 * Constructs a new NoExecutorFoundException.
	 *
	 * @param cause the underlying Throwable that caused this exception to be thrown.
	 */
	public SqlSessionExcuteException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new NoExecutorFoundException.
	 *
	 * @param message the reason for the exception
	 * @param cause   the underlying Throwable that caused this exception to be thrown.
	 */
	public SqlSessionExcuteException(String message, Throwable cause) {
		super(message, cause);
	}

}
