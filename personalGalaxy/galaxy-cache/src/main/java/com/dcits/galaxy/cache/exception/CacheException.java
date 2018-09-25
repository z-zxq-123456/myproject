package com.dcits.galaxy.cache.exception;

import com.dcits.galaxy.base.exception.GalaxyException;


/**
 * Cache异常类
 * 
 * @author xuecy
 * 
 */
public class CacheException extends GalaxyException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1943133835893082721L;

	/**
	 * Creates a new <code>CacheException</code>.
	 */
	public CacheException() {
		super();
	}

	/**
	 * Creates a new <code>CacheException</code>.
	 * 
	 * @param message
	 *            the reason for the exception.
	 */
	public CacheException(String message) {
		super(message);
	}

	/**
	 * Creates a new <code>CacheException</code>.
	 * 
	 * @param cause
	 *            the underlying cause of the exception.
	 */
	public CacheException(Throwable cause) {
		super(cause);
	}

	/**
	 * Creates a new <code>CacheException</code>.
	 * 
	 * @param message
	 *            the reason for the exception.
	 * @param cause
	 *            the underlying cause of the exception.
	 */
	public CacheException(String message, Throwable cause) {
		super(message, cause);
	}
}
