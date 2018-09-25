/**
 * Title: Galaxy(Distributed service platform)
 * File: JsonSerializerFeature.java
 * Copyright: Copyright (c) 2014-2017
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.orion.base.json;

import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.stereotype.Repository;

/**
 * <p>Created on 2017/6/8.</p>
 *
 * @author Tim <guotao@dcits.com>
 * @version 1.0.0
 * @since 1.7
 */
@Repository
public class JsonSerializerFeature implements IJsonSerializerFeature {

    @Override
    public SerializerFeature[] getSerializerFeature() {
        return new SerializerFeature[]{
                SerializerFeature.DisableCircularReferenceDetect
        };
    }

}
