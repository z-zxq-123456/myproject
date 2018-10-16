package com.dcits.ensemble.om.pf.util;

/**
 * Created by maruie on 2015/9/29.
 */
public class ObjectType {
    public static boolean isString(Object obj){
        boolean flag=false;
        if(obj instanceof String){
            flag=true;
        }
        return flag;
    }



}
