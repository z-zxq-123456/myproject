package com.company.tools;

public class CheckTools {


    public static boolean checkBaseAcctNo(String baseAcctNo){
        if (baseAcctNo == null ||  baseAcctNo.length() != 22){
            return false;
        }
        return true;
    }

    public static boolean checkChannelSeqNo(String channelSeqNo){

        if (channelSeqNo == null || channelSeqNo.equalsIgnoreCase("")){
            return false;
        }
        return true;
    }

    public static boolean checkPart(String[] args){
        if (args == null || args.length != 3){
            return false;
        }
        return true;
    }


    public static boolean checkAcctInfo(String[] args){
        if (args == null || args.length != 2){
            return false;
        }
        return true;
    }

}
