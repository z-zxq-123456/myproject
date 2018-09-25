/**
 * <p>Title: Strings.java</p>
 * <p>Description: 字符串工具类
 * 目前实现了左右填充字符和Exception堆栈转为字符串</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0
 */
package com.dcits.galaxy.base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.data.Result;
import com.dcits.galaxy.base.exception.*;
import com.dcits.galaxy.base.data.Results;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * @author Tim
 * @version V1.0
 * @description 异常工具类
 * @update 2014年9月15日 下午2:08:12
 */

public class ExceptionUtils {

    public static String getStackTrace(Throwable t) {
        if (t == null) {
            return "NULL";
        }
        StringBuffer b = new StringBuffer();
        b.append(t.getMessage());
        b.append("\n");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        t.printStackTrace(ps);
        b.append(baos.toString());
        return b.toString();
    }

    @SuppressWarnings("unchecked")
    public static <T extends BusinessException> BusinessException parseException(
            Throwable t) {
        BusinessException bs = null;
        try {
            if (t instanceof BusinessException) {
                throw t;
            } else if (t instanceof GalaxyException) {
                throw new BusinessException(GalaxyConstants.CODE_FAILED,
                        t.getMessage());
            } else if (t instanceof RuntimeException) {
                String msg = t.getMessage();
                String className = msg.substring(0, msg.indexOf(":"));
                Class<?> c = Class.forName(className);
                if (c == BusinessException.class
                        || c == WithoutAuthorizationException.class
                        || c == WithoutConfirmationException.class) {
                    String[] message = (t.getMessage().split("\r\n"))[0]
                            .split(":");
                    if (message.length == 3) {
                        if (c == BusinessException.class)
                            bs = new BusinessException(message[1].trim(),
                                    message[2].trim(), t);
                        else if (c == WithoutAuthorizationException.class)
                            bs = new WithoutAuthorizationException(
                                    message[1].trim(), message[2].trim(), t);
                        else if (c == WithoutConfirmationException.class)
                            bs = new WithoutConfirmationException(
                                    message[1].trim(), message[2].trim(), t);
                        else if (c == WithoutAuthAndConfirmException.class)
                            bs = new WithoutAuthAndConfirmException(
                                    message[1].trim(), message[2].trim(), t);
                    } else {
                        StringBuffer sb = new StringBuffer();
                        for (int i = 1; i < message.length; i++) {
                            if (i != 1)
                                sb.append(":");
                            sb.append(message[i]);
                        }
                        // 解析多条错误结果
                        List<JSONObject> retsL = JSON.parseObject(
                                sb.toString(), List.class);
                        Results rets = new Results();
                        for (JSONObject jb : retsL) {
                            rets.addResult(new Result(jb.getString("retCode"),
                                    jb.getString("retMsg")));
                        }

                        if (c == BusinessException.class)
                            bs = new BusinessException(rets, t);
                        else if (c == WithoutAuthorizationException.class)
                            bs = new WithoutAuthorizationException(rets, t);
                        else if (c == WithoutConfirmationException.class)
                            bs = new WithoutConfirmationException(rets, t);
                        else if (c == WithoutAuthAndConfirmException.class)
                            bs = new WithoutAuthAndConfirmException(rets, t);
                    }
                } else
                    bs = new BusinessException(GalaxyConstants.CODE_FAILED, t
                            .getClass().getSimpleName()
                            + ":"
                            + t.getMessage().split("\\r\\n")[0], t);
            } else {
                throw t;
            }
        } catch (Throwable t1) {
            bs = new BusinessException(GalaxyConstants.CODE_FAILED, t
                    .getClass().getSimpleName() + ":" + t.getMessage() + "[" + t.getStackTrace()[0] + "]", t);
        }
        return bs;
    }
}
