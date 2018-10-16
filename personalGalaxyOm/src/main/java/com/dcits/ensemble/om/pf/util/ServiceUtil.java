package com.dcits.ensemble.om.pf.util;

/**
 * Created by Tim on 2015/9/14.
 */
public class ServiceUtil {

    /**
     * 通过服务码获取消息类型
     *
     * @param ServiceCode
     * @return
     */
    public static String getMessageType(String ServiceCode) {
        String messageType = null;
        switch (ServiceCode) {
            case "SVR_FINANCIAL":
            case "Financial":
                messageType = "1000";
                break;
            case "SVR_NONFINANCIAL":
            case "Non-Financial":
                messageType = "1200";
                break;
            case "SVR_INQUIRY":
            case "Inquiry":
                messageType = "1400";
                break;
            case "SVR_REVERSAL":
            case "Reversal":
                messageType = "1300";
                break;
            case "SVR_FILE":
            case "File":
                messageType = "1220";
                break;
        }

        return messageType;
    }

}
