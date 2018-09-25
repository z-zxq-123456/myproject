package com.dcits.galaxy.base.validate;

import com.dcits.galaxy.base.exception.GalaxyException;

/**
 * @description
 * @version V1.0
 * @author Tim
 * @update 2015年1月14日 上午11:15:43
 */

public class ValidateException extends GalaxyException {

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 2090506579009073806L;

	public ValidateException(Throwable t) {
		super(t.getMessage(), t);
	}

	public ValidateException(String message) {
		super(message);
	}

	public ValidateException(String message, Throwable t) {
		super(message, t);
	}
}
