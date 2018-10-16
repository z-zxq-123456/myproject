package com.dcits.ensemble.om.pm.util.dao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by jiaxina on 2016/9/1.
 */
@Component
public class PkserviceInUtil {
        @Resource
        PkCreateInDao  pkCreateInDao;
        /**
         * 生成的文件记录主键
         * @return int  生成的文件记录主键
         */
        public synchronized int getMaxReqID(int max_req_no,String tabName,String pkColName) {
            if(max_req_no==0){
                Map<String,String>  sqlMap =new HashMap<String,String>();
                sqlMap.put("TABNAME",tabName);
                sqlMap.put("PKCOLNAME",pkColName);
                Object obj =  pkCreateInDao.createPk(sqlMap);
                if(obj!=null){

                    max_req_no = (int)(Double.parseDouble(""+obj) + 1);

                    return max_req_no;
                }
            }
            return ++max_req_no;
        }


        /**
         * 生成带条件的字段最大值
         * @return int  生成的主键
         */
        public synchronized int getMaxByCon(String tabName,String colName,String tabCon) {
            Map<String,String>  sqlMap =new HashMap<String,String>();
            sqlMap.put("TABNAME",tabName);
            sqlMap.put("MAXCOLNAME",colName);
            sqlMap.put("TABCON",tabCon);
            String obj = pkCreateInDao.createMaxByCon(sqlMap);
            return obj == null?1:Integer.parseInt("" + obj) + 1;
        }
    }

