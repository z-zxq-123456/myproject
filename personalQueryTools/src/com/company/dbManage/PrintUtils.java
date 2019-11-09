package com.company.dbManage;

import java.util.List;
import java.util.Map;

public  class PrintUtils {

    public static void print(String key,String args, List<Map<String,Object>> result){

        try {
            String[] columns = args.split(",");
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(key).append(" result = {").append("\r\n");
            for (Map map:result){
                for (int i = 0; i < columns.length; i ++){
                    stringBuffer.append("<").append(columns[i]).append(" = ").append(map.get(columns[i])).append(">").append("\r\n") ;
                }
                stringBuffer.append("}");
                System.out.println(stringBuffer.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
