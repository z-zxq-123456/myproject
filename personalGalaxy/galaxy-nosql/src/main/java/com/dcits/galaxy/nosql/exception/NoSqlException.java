/**   
 * <p>Title: StriaException.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年12月18日 下午4:11:14
 * @version V1.0
 */
package com.dcits.galaxy.nosql.exception;

import com.dcits.galaxy.base.exception.GalaxyException;

/**
 * @description
 * @version V1.0
 * @author Tim
 * @update 2014年12月18日 下午4:11:14
 */

public class NoSqlException extends GalaxyException {

	/**
	 * @fields serialVersionUID
	 */
	private static final long serialVersionUID = -9103507843938935916L;

	/**
	 * Creates a new GalaxyException.
	 */
	public NoSqlException() {
		super();
	}

	/**
	 * Constructs a new GalaxyException.
	 *
	 * @param message
	 *            the reason for the exception
	 */
	public NoSqlException(String message) {
		super(message);
	}

	/**
	 * Constructs a new GalaxyException.
	 *
	 * @param cause
	 *            the underlying Throwable that caused this exception to be
	 *            thrown.
	 */
	public NoSqlException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new GalaxyException.
	 *
	 * @param message
	 *            the reason for the exception
	 * @param cause
	 *            the underlying Throwable that caused this exception to be
	 *            thrown.
	 */
	public NoSqlException(String message, Throwable cause) {
		super(message, cause);
	}

}
