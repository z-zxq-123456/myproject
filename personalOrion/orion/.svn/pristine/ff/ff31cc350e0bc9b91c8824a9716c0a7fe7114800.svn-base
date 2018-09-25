/**
 * Title: Galaxy(Distributed service platform)
 * File: XmlError.java
 * Copyright: Copyright (c) 2014-2016
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.orion.base.error;

import com.dcits.orion.api.IError;
import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.data.Result;
import com.dcits.galaxy.base.exception.BusinessException;
import com.dcits.galaxy.base.util.ExceptionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 应用与平台层面的错误，根据异常组织Xml错误包
 * <p>Created on 2016/11/9.</p>
 *
 * @author Tim <guotao@dcits.com>
 * @version 1.0.0
 * @since 1.7
 */
public class XmlError implements IError {
    private static final Logger logger = LoggerFactory
            .getLogger(JsonError.class);

    @Override
    public String pack(Throwable t) {
        if (logger.isErrorEnabled()) {
            logger.error(ExceptionUtils.getStackTrace(t));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<service>");
        sb.append("<sys-header>");
        sb.append("<data name=\"SYS_HEAD\">");
        sb.append("<struct>");
        sb.append("<data name=\"RET\">");
        sb.append("<array>");
        if (t instanceof BusinessException) {
            for (Result result : ((BusinessException) t).getRets().getRets()) {
                sb.append("<struct>");
                sb.append("<data name=\"RET_MSG\">");
                sb.append("<field type=\"string\" length=\"").append(result.getRetMsg().length()).append("\">").append(result.getRetMsg()).append("</field>");
                sb.append("</data>");
                sb.append("<data name=\"RET_CODE\">");
                sb.append("<field type=\"string\" length=\"6\">").append(result.getRetCode()).append("</field>");
                sb.append("</data>");
                sb.append("</struct>");
            }
        } else {
            String errMsg = t.getClass().getSimpleName() + ":" + t.getMessage();
            sb.append("<struct>");
            sb.append("<data name=\"RET_MSG\">");
            sb.append("<field type=\"string\" length=\"").append(errMsg.length()).append("\">").append(errMsg).append("</field>");
            sb.append("</data>");
            sb.append("<data name=\"RET_CODE\">");
            sb.append("<field type=\"string\" length=\"6\">").append(GalaxyConstants.CODE_FAILED).append("</field>");
            sb.append("</data>");
            sb.append("</struct>");
        }
        sb.append("</array>");
        sb.append("</data>");
        sb.append("</struct>");
        sb.append("</data>");
        sb.append("</sys-header>");
        sb.append("</service>");
        return sb.toString();
    }
}
