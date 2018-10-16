package com.dcits.ensemble.om.cmc.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.PrintWriter;


/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/4/27
 */
public class ErrorMsgHandle {

    private static Logger logger = LoggerFactory.getLogger(ErrorMsgHandle.class);

    public static void errorMsg(String errorMsg, PrintWriter writer){

        logger.error(errorMsg);

        JSONObject result = new JSONObject();
        String res;

        if (errorMsg.trim().contains("[{\"retCode\":") && errorMsg.trim().contains("\"retMsg\"")){
            String errCode = errorMsg.substring(2,11);
            String temp = errorMsg.substring(11, errorMsg.length() - 2);
            res = temp.replace("\"retMsg\"", " 错误信息: ");
            res  = "错误码: " + errCode + res;

        } else if (errorMsg.trim().contains("  ")){
           String errCode = errorMsg.substring(0,errorMsg.indexOf("  "));
           String temp = errorMsg.substring(errorMsg.indexOf("  "));
           res  = "错误码: " + errCode + " 错误信息: "+temp;

        }else {

          res = "执行异常: "+errorMsg;
        }
        result.put("retMsg", res);
        result.put("retStatus","F");
        writer.print(result);
        writer.flush();
        writer.close();
    }

}
