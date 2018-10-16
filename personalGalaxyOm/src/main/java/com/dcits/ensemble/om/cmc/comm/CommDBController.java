package com.dcits.ensemble.om.cmc.comm;

import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.cmc.dao.BaseDbTableDao;
import com.dcits.ensemble.om.cmc.util.ErrorMsgHandle;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/8
 */
@Controller
@RequestMapping("/baseComm")
public class CommDBController {

    @Autowired
    private BaseDbTableDao baseDbTableDao;

    private static final String BaseLoaderPath = "com.dcits.ensemble.om.cmc.dbModel.";

    @RequestMapping("/getAll")
    public void  getByPkValue(HttpServletRequest request, PrintWriter printWriter){

        String tableName = request.getParameter("tableName");
        String pk_name = request.getParameter("pk_name");
        String pk_value = request.getParameter("pk_value");
        String columnStatus = request.getParameter("columnStatus");

        checkNull(tableName,"tableName",printWriter);
        checkNull(pk_name,"pk_name",printWriter);
        checkNull(pk_value,"pk_value",printWriter);

        Map requestMap = new HashMap();
        requestMap.put("tableName",tableName);
        requestMap.put("pk_name",pk_name);
        requestMap.put("pk_value",pk_value);
        requestMap.put("columnStatus",columnStatus);

       try {

           List<Map> tableParams;
           tableParams = baseDbTableDao.selectByPrimaryKey(requestMap);
           JSONObject out = new JSONObject();
           out.put("data",tableParams);
           printWriter.write(out.toJSONString());
           printWriter.flush();
           printWriter.close();
       }catch (Exception e){
           ErrorMsgHandle.errorMsg(e.getMessage(),printWriter);
       }
    }

    private void checkNull(String name ,String field,PrintWriter writer){
        String errorMsg;
        if (name == null || name.trim().equals("")){
            errorMsg = field +"is required not null!";
            ErrorMsgHandle.errorMsg(errorMsg,writer);
        }
    }

    private Class convertToDbClass(String tableName){
        String [] split = tableName.toLowerCase().split("_");
        StringBuilder sb = new StringBuilder();
        for (String sp:split){
            String tmp = sp.substring(0,1).toUpperCase()+sp.substring(1);
            sb.append(tmp);
        }
       try {
           return Class.forName(BaseLoaderPath+sb.toString(),false,Thread.currentThread().getContextClassLoader());
       }catch (Exception e){
            throw new RuntimeException(e.getMessage());
       }
    }
}
