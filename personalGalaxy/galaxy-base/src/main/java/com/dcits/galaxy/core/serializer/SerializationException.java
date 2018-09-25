package com.dcits.galaxy.core.serializer;

import com.dcits.galaxy.base.exception.GalaxyException;

/**
 * 序列化异常
 * 
 * @author xuecy
 *
 */
public class SerializationException extends GalaxyException {

    private static final long serialVersionUID = -8561411072499373859L;

    /**
     * Constructs a new <code>SerializationException</code> instance.
     * 
     * @param msg
     * @param cause
     */
    public SerializationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     * Constructs a new <code>SerializationException</code> instance.
     * 
     * @param msg
     */
    public SerializationException(String msg) {
        super(msg);
    }
   
    /**
     * Constructs a new <code>SerializationException</code> instance.
     * 
     * @param cause
     */
    public SerializationException(Throwable cause) {
        super(cause);
    }
    
}
