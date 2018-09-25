/**
 * Title: Galaxy(Distributed service platform)
 * File: BigDecimalValueFilter.java
 * Copyright: Copyright (c) 2014-2017
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.orion.base.json;

import com.alibaba.fastjson.serializer.ValueFilter;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 处理小数精度过多的浮点数据转变为科学计数法问题
 * <p>Created on 2017/6/8</p>
 *
 * @author Tim <guotao@dcits.com>
 * @version 1.0.0
 * @since 1.7
 */
public class BigDecimalValueFilter implements ValueFilter {

    @Override
    public Object process(Object object, String name, Object value) {
        // 处理小数精度过多的浮点数据转变为科学计数法问题，此方式会将类型转化为String modify by Tim 20170608
        if (null != value && value instanceof BigDecimal) {
            DecimalFormat decimalFormat = new DecimalFormat();
            return ((BigDecimal) value).toPlainString();
        }

        return value;
    }
}
