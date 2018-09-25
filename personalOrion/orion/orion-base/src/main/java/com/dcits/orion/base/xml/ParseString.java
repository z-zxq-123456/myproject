package com.dcits.orion.base.xml;

import com.dcits.orion.api.IParseString;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by lixbb on 2016/1/14.
 */
@Repository
public class ParseString implements IParseString {
    @Override
    public Object parse(Class class1, String string) {
        if (string == null || string.trim().length()==0)
            return null;
        Object object = null;
        if (class1 == Boolean.TYPE || class1 == Boolean.class)
        {
            object = Boolean.parseBoolean(string);
        }
        else if (class1 == Character.TYPE || class1 == Character.class)
        {
           object = new Character(string.charAt(0));
        }
        else if (class1 == Byte.TYPE || class1 == Byte.class)
        {
            object = Byte.parseByte(string);
        }
        else if (class1 == Short.TYPE || class1 == Short.class)
        {
            object = Short.parseShort(string);
        }
        else if (class1 == Integer.TYPE || class1 == Integer.class)
        {
            object = Integer.parseInt(string);
        }
        else if (class1 == Long.TYPE || class1 == Long.class)
        {
            object = Long.parseLong(string);
        }
        else if (class1 == Float.TYPE || class1 == Float.class)
        {
            object = Float.parseFloat(string);
        }
        else if (class1 == Double.TYPE || class1 == Double.class)
        {
            object = Double.parseDouble(string);
        }
        else if (class1 == BigInteger.class)
        {
            object = new BigInteger(string);
        }
        else if (class1 == BigDecimal.class)
        {
            object = new BigDecimal(string);
        }
        else if (class1 == String.class)
        {
            object = string;
        }
        return object;
    }

    @Override
    public String getString(Object o) {
        if (o != null)
        {
            return o.toString();
        }
        return "";
    }

    @Override
    public int getLength(Object o) {
        if (o==null)
            return 0;
        int length = 0;
        Class class1 = o.getClass();

        if (class1 == Boolean.TYPE || class1 == Boolean.class)
        {
            length = 8;
        }
        else if (class1 == Character.TYPE || class1 == Character.class)
        {
            length = 8;
        }
        else if (class1 == Byte.TYPE || class1 == Byte.class)
        {
            length = Byte.SIZE;
        }
        else if (class1 == Short.TYPE || class1 == Short.class)
        {
            length =Short.SIZE;
        }
        else if (class1 == Integer.TYPE || class1 == Integer.class)
        {
            length = Integer.SIZE;
        }
        else if (class1 == Long.TYPE || class1 == Long.class)
        {
            length = Long.SIZE;
        }
        else if (class1 == Float.TYPE || class1 == Float.class)
        {
            length = Float.SIZE;
        }
        else if (class1 == Double.TYPE || class1 == Double.class)
        {
            length = Double.SIZE;
        }
        else if (class1 == BigInteger.class)
        {
            length = 128;
        }
        else if (class1 == BigDecimal.class)
        {
            length = 128;
        }
        else if (class1 == String.class)
        {
            length = ((String)o).length();
        }
        return length;

    }

    @Override
    public String getType(Class class1) {

        String type = "string";

        if (class1 == Boolean.TYPE || class1 == Boolean.class)
        {
            type = "boolean";
        }
        else if (class1 == Character.TYPE || class1 == Character.class)
        {
            type = "string";
        }
        else if (class1 == Byte.TYPE || class1 == Byte.class)
        {
            type = "int";
        }
        else if (class1 == Short.TYPE || class1 == Short.class)
        {
            type = "int";
        }
        else if (class1 == Integer.TYPE || class1 == Integer.class)
        {
            type = "int";
        }
        else if (class1 == Long.TYPE || class1 == Long.class)
        {
            type = "int";
        }
        else if (class1 == Float.TYPE || class1 == Float.class)
        {
            type = "double";
        }
        else if (class1 == Double.TYPE || class1 == Double.class)
        {
            type = "double";
        }
        else if (class1 == BigInteger.class)
        {
            type = "int";
        }
        else if (class1 == BigDecimal.class)
        {
            type = "double";
        }
        else if (class1 == String.class)
        {
            type = "string";
        }
        return type;
    }
}
