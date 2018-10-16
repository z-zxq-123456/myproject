package com.dcits.ensemble.om.cmc.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.cmc.dao.CmcProductInfoDao;
import com.dcits.ensemble.om.cmc.dbModel.CmcProductInfo;
import com.dcits.ensemble.om.cmc.util.DataPostUtil;
import com.dcits.ensemble.om.cmc.util.LocalMapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @Date : Create on 2018/8/26
 */
@Controller
@RequestMapping("/cardProductInfo")
public class CmcProductInfoAction {

    private static final Logger logger = LoggerFactory.getLogger(CmcProductInfoAction.class);

    @Resource
    private CmcProductInfoDao cmcProductInfoDao;

    @RequestMapping("/add")
    public void insert(@RequestBody CmcProductInfo cmcProductInfo, PrintWriter printWriter){
        Map<String,String> result = new HashMap<>();

        check(cmcProductInfo);

       logger.debug("add cmcProductInfo : " + JSON.toJSONString(cmcProductInfo));

        try {
            if (isExist(cmcProductInfo)){
                throw new RuntimeException("this cardProductCode is duplicated!");
            }
            LocalMapUtils.mapProdInfo(cmcProductInfo,"Insert",cmcProductInfo.getReqNum());
            result.put("success", "success");
        }catch (Exception e){
            logger.error("卡产品信息添加失败！" +e.getMessage());
            result.put("errorMsg", "卡产品信息添加失败: " +e.getMessage());
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/update")
    public void update(@RequestBody CmcProductInfo cmcProductInfo, PrintWriter printWriter){
        Map<String,String> result = new HashMap<>();
        check(cmcProductInfo);
        logger.debug("update cmcProductInfo : " + JSON.toJSONString(cmcProductInfo));

        try {
            LocalMapUtils.mapProdInfo(cmcProductInfo, DataPostUtil.UPDATE,cmcProductInfo.getReqNum());
            result.put("success", "success");

        }catch (Exception e){
            logger.error("卡产品信息更新失败！" +e.getMessage());
            result.put("errorMsg", "卡产品信息更新失败: "+e.getMessage());
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    /**
     * cmcProductInfo
     * @param cmcProductInfo
     */
    private void check(CmcProductInfo cmcProductInfo){
        if (cmcProductInfo == null || cmcProductInfo.getCardProductCode()==null || cmcProductInfo.getCardProductCode().equals("")){
            throw new RuntimeException("cmcProductInfo is not allowed null!");
        }
    }

    /**
     * cmcProductInfo
     * @param cardProductCode
     */
    private void check(String  cardProductCode){
        if (cardProductCode == null || cardProductCode.equals("")){
            throw new RuntimeException("cmcProductInfo is not allowed null!");
        }
    }

    /**
     * 判断是否存在记录
     * @param cmcProductInfo
     * @return
     */
    private boolean isExist(CmcProductInfo cmcProductInfo){
        List<CmcProductInfo> cmcProductInfos =
                cmcProductInfoDao.selectList("selectByPrimaryKey",cmcProductInfo);

        return cmcProductInfos != null && cmcProductInfos.size()>0;
    }

    @RequestMapping("/getAll")
    public void getAll(HttpServletRequest request,PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<Map> cmcProductInfos = cmcProductInfoDao.getAll(request.getParameter("prodCode"));
        out.put("data", cmcProductInfos);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, PrintWriter printWriter){
        CmcProductInfo productInfo = new CmcProductInfo();
        productInfo.setCardProductCode(request.getParameter("cardProductCode"));
        productInfo.setReqNum(request.getParameter("reqNum"));
        JSONObject out = new JSONObject();
        check(productInfo);
        try {
            LocalMapUtils.mapProdInfo(productInfo,DataPostUtil.Delete,productInfo.getReqNum());
            out.put("retStatus", "S");
            out.put("retMsg", "删除成功");

        }catch (Exception e){
            logger.error("卡产品信息删除失败！" +e.getMessage());
            out.put("retStatus", "F");
            out.put("retMsg", "卡产品信息删除失败: "+e.getMessage());
        }
        printWriter.print(JSON.toJSONString(out));
        printWriter.flush();
        printWriter.close();
    }
}
