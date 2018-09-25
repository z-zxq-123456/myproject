package com.dcits.galaxy.core.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.caucho.hessian.io.SerializerFactory;

/**
 * 基于Hessian的序列化
 * 
 * @author xuecy
 *
 */
public class HessianSerializer implements Serializer<Object> {

    static private SerializerFactory _serializerFactory;
    static {
        _serializerFactory = new SerializerFactory();
    }

    static public HessianOutput createHessianOutput(OutputStream out) {
        HessianOutput hout = new HessianOutput(out);
        hout.setSerializerFactory(_serializerFactory);
        return hout;
    }

    static public HessianInput createHessianInput(InputStream in) {
        HessianInput hin = new HessianInput(in);
        hin.setSerializerFactory(_serializerFactory);
        return hin;
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null) {
            return null;
        }
        try {
            ByteArrayInputStream input = new ByteArrayInputStream(bytes);
            HessianInput hin = createHessianInput(input);
            return hin.readObject();
        } catch (IOException e) {
            throw new SerializationException("反序列化对象失败", e);
        }
    }

    @Override
    public byte[] serialize(Object t) throws SerializationException {
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            createHessianOutput(bout).writeObject(t);
            return bout.toByteArray();
        } catch (IOException e) {
            throw new SerializationException("序列化对象失败：" + t.getClass(), e);
        }
    }

}
