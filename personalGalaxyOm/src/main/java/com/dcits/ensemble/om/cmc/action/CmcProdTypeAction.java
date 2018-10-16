package com.dcits.ensemble.om.cmc.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.cmc.dao.BaseDbTableDao;
import com.dcits.ensemble.om.cmc.dao.BaseDbTableDaoImpl;
import com.dcits.ensemble.om.cmc.dao.CmcProductTypeDao;
import com.dcits.ensemble.om.cmc.dbModel.CmcProductType;
import com.dcits.ensemble.om.cmc.util.DataPostUtil;
import com.dcits.ensemble.om.cmc.util.ErrorMsgHandle;
import com.dcits.ensemble.om.cmc.util.LocalMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/8/25
 */
@Controller
@RequestMapping("/cardProdType")
public class CmcProdTypeAction {

    @Resource
    CmcProductTypeDao productTypeDao;

    @Autowired
    BaseDbTableDao baseDbTableDao;

    @RequestMapping("/getAll")
    public void  getAll(HttpServletRequest request,PrintWriter printWriter){
        JSONObject out = new JSONObject();
        String publishChannel = request.getParameter("publishChannel");
        String cardProductCode = request.getParameter("cardProductCode");
        Map requestMap = new HashMap();
        requestMap.put("publishChannel",publishChannel);
        requestMap.put("cardProductCode",cardProductCode);
        requestMap.put("columnStatus","Y");
        List<CmcProductType> mbProdTypes= productTypeDao.getAll(requestMap);
        out.put("data", JSON.toJSONString(mbProdTypes));
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/getAllStatus")
    public void getAllStatus(HttpServletRequest request,PrintWriter printWriter){

        JSONObject out = new JSONObject();
        String tableName = request.getParameter("tableName");
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("tableName",tableName);
        List<Map> mbProdTypes= baseDbTableDao.getAllStatus(requestMap);
        out.put("data", mbProdTypes);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("getProdTypeKey")
    public void isExists(HttpServletRequest request,PrintWriter printWriter){

        JSONObject out=new JSONObject();
        Map requestMap = new HashMap();
        requestMap.put("tableName","CMC_PRODUCT_INFO");
        requestMap.put("pk_name","CARD_PRODUCT_CODE");
        requestMap.put("pk_value",request.getParameter("prodType"));
        requestMap.put("columnStatus","Y");

        List result = baseDbTableDao.selectByPrimaryKey(requestMap);
        out.put("retStatus", "S");
        out.put("data",result);
        printWriter.print(out.toJSONString());
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/insert")
    public void insert(@RequestBody CmcProductType productType, PrintWriter printWriter){
        JSONObject out=new JSONObject();
        try {
            productType.setColumnStatus("C");
            productTypeDao.insert(productType);
            out.put("retStatus", "S");
            out.put("retMsg", "提交成功");
            printWriter.print(out.toJSONString());
            printWriter.flush();
            printWriter.close();
        }catch (Exception e){
            ErrorMsgHandle.errorMsg("Failed during process [insert] cardProductType: " +e.getMessage(),printWriter);
        }
    }

    @RequestMapping("/update")
    public void update(@RequestBody CmcProductType productType,PrintWriter printWriter){
        JSONObject out=new JSONObject();
        try {
            productTypeDao.updateByPrimaryKey(productType);
            out.put("retStatus", "S");
            out.put("retMsg", "操作成功!");
        }catch (Exception e){
            ErrorMsgHandle.errorMsg("fail in insert cardProductType: " +e.getMessage(),printWriter);
            out.put("retStatus", "F");
            out.put("retMsg", e.getMessage());
        }
        printWriter.print(out.toJSONString());
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/updateReqNum")
    public void updateReqNum(@RequestBody CmcProductType productType,PrintWriter printWriter){
        JSONObject out=new JSONObject();
        try {
            productTypeDao.updateReqNum(productType);
            out.put("retStatus", "S");
            out.put("retMsg", "操作成功!");
        }catch (Exception e){
            ErrorMsgHandle.errorMsg("fail in insert cardProductType: " +e.getMessage(),printWriter);
            out.put("retStatus", "F");
            out.put("retMsg", e.getMessage());
        }
        printWriter.print(out.toJSONString());
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/getAllContrast")
    public void getAllContrast(HttpServletRequest request,PrintWriter printWriter){
        JSONObject out = new JSONObject();
        Map<String,Object> requestParams = new HashMap<>();
        requestParams.put("tableName",request.getParameter("tableName"));
        List<Map> prodTypes= baseDbTableDao.getAllContrast(requestParams);
        out.put("data",prodTypes);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/delete")
    public void delete(HttpServletRequest request,PrintWriter printWriter){
        JSONObject out = new JSONObject();
        try {
            CmcProductType productType  = new CmcProductType();
            productType.setReqNum(request.getParameter("reqNum"));
            productTypeDao.deleteByPrimaryKey(productType);
            out.put("retStatus","S");
            out.put("retMsg","删除成功");
            printWriter.print(out);
            printWriter.flush();
            printWriter.close();
        }catch (Exception e){
            ErrorMsgHandle.errorMsg(e.getMessage(),printWriter);
        }
    }

    @RequestMapping("/reset")
    public void resetCache(HttpServletRequest request,PrintWriter printWriter){
        JSONObject out = new JSONObject();
        try{
            if (request.getParameter("reqNum")==null){
                throw new RuntimeException("reqNum is required not null!");
            }
            LocalMapUtils.clear(request.getParameter("reqNum"));
            out.put("retStatus","S");
            out.put("retMsg","删除成功");
            printWriter.print(out);
            printWriter.flush();
            printWriter.close();
        }catch (Exception e){
            ErrorMsgHandle.errorMsg(e.getMessage(),printWriter);
        }
    }

    @RequestMapping("/validate")
    public void validate(HttpServletRequest request,PrintWriter printWriter){
        JSONObject out = new JSONObject();
        try{
            if (request.getParameter("reqNum")==null){
                throw new RuntimeException("reqNum is required not null!");
            }
            LocalMapUtils.validateTbInfo(request.getParameter("reqNum"));
            out.put("retStatus","S");
            printWriter.print(out);
            printWriter.flush();
            printWriter.close();
        }catch (Exception e){
            ErrorMsgHandle.errorMsg(e.getMessage(),printWriter);
        }
    }
}
