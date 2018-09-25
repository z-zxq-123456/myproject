/**
 * Title: Galaxy(Distributed service platform)
 * File: BigDecimalSerializer.java
 * Copyright: Copyright (c) 2014-2017
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.orion.base.json;

import com.alibaba.fastjson.serializer.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;

/**
 * <p>Created on 2017/6/8.</p>
 *
 * @author Tim <Tim@dcits.com>
 * @version 1.0.0
 * @since 1.7
 */
public class BigDecimalSerializer implements ObjectSerializer {

    public final static BigDecimalSerializer instance = new BigDecimalSerializer();

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        SerializeWriter out = serializer.getWriter();

        if (object == null) {
            if (out.isEnabled(SerializerFeature.WriteNullNumberAsZero)) {
                out.write('0');
            } else {
                out.writeNull();
            }
            return;
        }

        BigDecimal val = (BigDecimal) object;
        // 避免精度问题
        out.write(val.toPlainString());

        if (out.isEnabled(SerializerFeature.WriteClassName) && fieldType != BigDecimal.class && val.scale() == 0) {
            out.write('.');
        }
    }
}
