package com.dcits.galaxy.connector;

import com.dcits.galaxy.base.exception.GalaxyException;

/**
 * Connector异常类
 * 
 * @author xuecy
 * 
 */
public class ConnectorException extends GalaxyException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5525234495113884419L;

	/**
	 * Creates a new <code>CacheException</code>.
	 */
	public ConnectorException() {
		super();
	}

	/**
	 * Creates a new <code>CacheException</code>.
	 * 
	 * @param message
	 *            the reason for the exception.
	 */
	public ConnectorException(String message) {
		super(message);
	}

	/**
	 * Creates a new <code>CacheException</code>.
	 * 
	 * @param cause
	 *            the underlying cause of the exception.
	 */
	public ConnectorException(Throwable cause) {
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
	public ConnectorException(String message, Throwable cause) {
		super(message, cause);
	}
}
