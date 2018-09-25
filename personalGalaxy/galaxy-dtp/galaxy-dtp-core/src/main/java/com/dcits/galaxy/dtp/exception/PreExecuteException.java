package com.dcits.galaxy.dtp.exception;

public class PreExecuteException extends DTPException {

	private static final long serialVersionUID = 2639909493363973438L;

	public PreExecuteException() {
        super();
    }
	
	public PreExecuteException(String message) {
		super(message);
	}
	
	public PreExecuteException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public PreExecuteException(Throwable cause) {
        super(cause);
    }
}
