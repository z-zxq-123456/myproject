package com.dcits.galaxy.dal.mybatis.route;

/**
 * 路由异常
 * @author chenhongk
 *
 */
public class RouteException extends RuntimeException {

	/**
	 * 路由异常
	 * @author chen.hong
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Creates a new RouteException.
     */
    public RouteException() {
        super();
    }

    /**
     * Constructs a new RouteException.
     *
     * @param message the reason for the exception
     */
    public RouteException(String message) {
        super(message);
    }

    /**
     * Constructs a new RouteException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public RouteException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new RouteException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public RouteException(String message, Throwable cause) {
        super(message, cause);
    }
}
