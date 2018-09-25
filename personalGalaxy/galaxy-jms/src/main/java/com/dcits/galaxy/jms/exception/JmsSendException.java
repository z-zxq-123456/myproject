package com.dcits.galaxy.jms.exception;

public class JmsSendException extends JmsException {

	private static final long serialVersionUID = 1L;

	public JmsSendException() {
		super();
	}

	public JmsSendException(String message, Throwable cause) {
		super(message, cause);
	}

	public JmsSendException(String message) {
		super(message);
	}

	public JmsSendException(Throwable cause) {
		super(cause);
	}
}
