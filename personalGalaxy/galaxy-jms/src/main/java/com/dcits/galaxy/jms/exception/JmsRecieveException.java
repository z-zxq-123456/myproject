package com.dcits.galaxy.jms.exception;

public class JmsRecieveException extends JmsException {

	private static final long serialVersionUID = 1L;

	public JmsRecieveException() {
		super();
	}

	public JmsRecieveException(String message, Throwable cause) {
		super(message, cause);
	}

	public JmsRecieveException(String message) {
		super(message);
	}

	public JmsRecieveException(Throwable cause) {
		super(cause);
	}
}
