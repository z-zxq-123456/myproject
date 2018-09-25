package com.dcits.galaxy.jms.exception;

import com.dcits.galaxy.base.exception.GalaxyException;

public class JmsException extends GalaxyException {

	private static final long serialVersionUID = 1L;

	public JmsException() {
		super();
	}

	public JmsException(String message, Throwable cause) {
		super(message, cause);
	}

	public JmsException(String message) {
		super(message);
	}

	public JmsException(Throwable cause) {
		super(cause);
	}
}
