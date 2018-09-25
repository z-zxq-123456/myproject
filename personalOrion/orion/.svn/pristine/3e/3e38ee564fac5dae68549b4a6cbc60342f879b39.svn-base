package com.dcits.orion.convert;

import com.dcits.orion.api.IParseString;

import org.springframework.stereotype.Repository;

/**
 * Created by lixbb on 2016/3/10.
 */
@Repository
public class AmountParse implements IParseString {
    @Override
    public Object parse(Class class1, String string) {
        return Double.parseDouble(string);
    }

    @Override
    public String getString(Object o) {
        return o.toString();
    }

    @Override
    public int getLength(Object o) {
        return 0;
    }

    @Override
    public String getType(Class class1) {
        return class1.getName();
    }
}
