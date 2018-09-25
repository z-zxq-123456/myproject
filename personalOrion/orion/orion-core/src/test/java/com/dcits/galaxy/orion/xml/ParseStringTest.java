package com.dcits.galaxy.orion.xml;

import com.dcits.orion.api.IParseString;

import org.springframework.stereotype.Repository;

/**
 * Created by lixbb on 2016/1/15.
 */
@Repository
public class ParseStringTest implements IParseString<Double> {
    @Override
    public Double parse(Class<?> class1, String string) {
        if (string != null && string.trim().length() > 0)
        return Double.parseDouble(string);
        else return null;
    }

    @Override
    public String getString(Double aDouble) {
        return aDouble.toString();
    }

    @Override
    public int getLength(Double aDouble) {
        return Double.SIZE;
    }

    @Override
    public String getType(Class<?> class1) {
        return "DOUBLE";
    }
}
