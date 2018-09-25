package com.dcits.galaxy.core.serializer;

/**
 * 序列化接口
 * 
 * @author xuecy
 *
 * @param <T>
 */
public interface Serializer<T> {

    /**
     * Serialize the given object to binary data.
     * 
     * @param t
     *            object to serialize
     * @return the equivalent binary data
     */
    byte[] serialize(T t) throws SerializationException;

    /**
     * Deserialize an object from the given binary data.
     * 
     * @param bytes
     *            object binary representation
     * @return the equivalent object instance
     */
    T deserialize(byte[] bytes) throws SerializationException;

}
