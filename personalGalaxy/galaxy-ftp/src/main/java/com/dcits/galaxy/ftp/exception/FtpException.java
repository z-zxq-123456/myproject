package com.dcits.galaxy.ftp.exception;

import com.dcits.galaxy.base.exception.GalaxyException;

/**
 * Created by Tim on 2015/10/15.
 */
public class FtpException extends GalaxyException {

    private static final long serialVersionUID = -239087217205517377L;

    /**
     * Creates a new FtpException.
     */
    public FtpException() {
        super();
    }

    /**
     * Constructs a new FtpException.
     *
     * @param message the reason for the exception
     */
    public FtpException(String message) {
        super(message);
    }

    /**
     * Constructs a new FtpException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public FtpException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new FtpException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public FtpException(String message, Throwable cause) {
        super(message, cause);
    }

}
