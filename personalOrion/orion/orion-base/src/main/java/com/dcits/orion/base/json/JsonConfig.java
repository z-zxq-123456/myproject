/**
 * Title: Galaxy(Distributed service platform)
 * File: JsonConfig.java
 * Copyright: Copyright (c) 2014-2017
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.orion.base.json;

import com.alibaba.fastjson.serializer.SerializeConfig;

import java.math.BigDecimal;

/**
 * <p>Created on 2017/6/8.</p>
 *
 * @author Tim <Tim@dcits.com>
 * @version 1.0.0
 * @since 1.7
 */
public class JsonConfig implements IJsonConfig {
    @Override
    public void initJsonConfig() {
        // 处理小数精度过多的浮点数据转变为科学计数法问题，重新定义BigDecimalSerializer序列化函数
        SerializeConfig serializeConfig = SerializeConfig.getGlobalInstance();
        serializeConfig.put(BigDecimal.class, BigDecimalSerializer.instance);
    }
}
