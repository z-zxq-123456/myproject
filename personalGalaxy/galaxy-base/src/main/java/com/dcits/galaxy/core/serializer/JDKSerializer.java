package com.dcits.galaxy.core.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 基于JDK的实现
 * 
 * @author xuecy
 *
 */
public class JDKSerializer implements Serializer<Object> {

    @Override
    public Object deserialize(byte[] source) throws SerializationException {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(source);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteStream);
            return objectInputStream.readObject();
        } catch (Exception ex) {
            throw new SerializationException("反序列化对象失败" + ex);
        }
    }

    @Override
    public byte[] serialize(Object object) throws SerializationException {
        if (!(object instanceof Serializable)) {
            throw new IllegalArgumentException(getClass().getSimpleName() + " requires a Serializable payload " + "but received an object of type [" + object.getClass().getName() + "]");
        }
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream(128);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            return byteStream.toByteArray();
        } catch (Exception e) {
            throw new SerializationException("序列化对象失败:" + object.getClass(), e);
        }
    }

}
