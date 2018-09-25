/**
 * Title: Galaxy(Distributed service platform)
 * File: error.java
 * Copyright: Copyright (c) 2014-2016
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.orion.base.error;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.orion.api.IError;
import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.data.Result;
import com.dcits.galaxy.base.exception.BusinessException;
import com.dcits.galaxy.base.util.DateUtils;
import com.dcits.galaxy.base.util.ExceptionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 应用与平台层面的错误，根据异常组织Json错误包
 * <p>Created on 2016/11/9.</p>
 *
 * @author Tim <guotao@dcits.com>
 * @version 1.0.0
 * @since 1.7
 */
public class JsonError implements IError {

    private static final Logger logger = LoggerFactory
            .getLogger(JsonError.class);

    @Override
    public String pack(Throwable t) {
        if (logger.isErrorEnabled()) {
            logger.error(ExceptionUtils.getStackTrace(t));
        }
        JSONArray rets = new JSONArray();
        if (t instanceof BusinessException) {
            for (Result result : ((BusinessException) t).getRets().getRets()) {
                JSONObject ret = new JSONObject();
                ret.put("RET_CODE", result.getRetCode());
                ret.put("RET_MSG", result.getRetMsg());
                rets.add(ret);
            }
        } else {
            JSONObject ret = new JSONObject();
            ret.put("RET_CODE", GalaxyConstants.CODE_FAILED);
            ret.put("RET_MSG", t.getClass().getSimpleName() + ":" + t.getMessage());
            rets.add(ret);
        }
        return "{\"SYS_HEAD\":{\"RET\":" + rets.toString() + ",\"RET_STATUS\":\"F\",\"TRAN_TIMESTAMP\":\"" + DateUtils.getDateTime("HHmmssSSS") + "\"}}";
    }
}
