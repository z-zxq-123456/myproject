package com.dcits.galaxy.base.validate;

import java.lang.reflect.Field;

/**
 * @author Tim
 * @version V1.0
 * @description
 * @update 2015年1月13日 下午5:54:07
 */

public interface Handler {

    void validate(V annotation, Object filter, Field field, Object fieldValue, String prefix);

}
