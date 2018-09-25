package com.dcits.galaxy.base.exception;

/**
 * Galaxy的根异常
 * 
 * @author xuecy
 *
 */
public class GalaxyException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5480079865489577671L;

	/**
     * Creates a new GalaxyException.
     */
    public GalaxyException() {
        super();
    }

    /**
     * Constructs a new GalaxyException.
     *
     * @param message the reason for the exception
     */
    public GalaxyException(String message) {
        super(message);
    }

    /**
     * Constructs a new GalaxyException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public GalaxyException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new GalaxyException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public GalaxyException(String message, Throwable cause) {
        super(message, cause);
    }

}
