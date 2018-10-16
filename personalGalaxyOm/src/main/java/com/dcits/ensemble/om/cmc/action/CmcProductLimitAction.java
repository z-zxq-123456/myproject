package com.dcits.ensemble.om.cmc.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.cmc.dao.CmcProductLimitDao;
import com.dcits.ensemble.om.cmc.dbModel.CmcProductLimit;
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
@RequestMapping("/cardProductLimit")
public class CmcProductLimitAction {

    private static final Logger logger = LoggerFactory.getLogger(CmcProductLimitAction.class);

    @Resource
    private CmcProductLimitDao cmcProductLimitDao;

    @RequestMapping("/add")
    public void insert(@RequestBody CmcProductLimit cmcProductLimit, PrintWriter printWriter){
        Map<String,String> result = new HashMap<>();

        check(cmcProductLimit);

       logger.debug("add cmcProductInfo : " + JSON.toJSONString(cmcProductLimit));

        try {
            if (isExist(cmcProductLimit)){
                throw new RuntimeException("this cardProductCode is duplicated!");
            }
            LocalMapUtils.mapCardProdLimit(cmcProductLimit,"Insert",cmcProductLimit.getReqNum());
            result.put("success", "success");
        }catch (Exception e){
            logger.error("卡限额信息添加失败！" +e.getMessage());
            result.put("errorMsg", "卡限额信息添加失败: " +e.getMessage());
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/update")
    public void update(@RequestBody CmcProductLimit cmcProductLimit, PrintWriter printWriter){
        Map<String,String> result = new HashMap<>();
        check(cmcProductLimit);
        logger.debug("update cmcProductInfo : " + JSON.toJSONString(cmcProductLimit));

        try {
            LocalMapUtils.mapCardProdLimit(cmcProductLimit, DataPostUtil.UPDATE,cmcProductLimit.getReqNum());
            result.put("success", "success");

        }catch (Exception e){
            logger.error("卡限额信息更新失败！" +e.getMessage());
            result.put("errorMsg", "卡限额信息更新失败: "+e.getMessage());
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    /**
     * cmcProductInfo
     * @param cmcProductLimit
     */
    private void check(CmcProductLimit cmcProductLimit){
        if (cmcProductLimit == null || cmcProductLimit.getCardProductCode()==null || cmcProductLimit.getCardProductCode().equals("")){
            throw new RuntimeException("cmcProductLimit is not allowed null!");
        }
    }

    /**
     * cmcProductInfo
     * @param cardProductCode
     */
    private void check(String  cardProductCode){
        if (cardProductCode == null || cardProductCode.equals("")){
            throw new RuntimeException("cmcProductLimit is not allowed null!");
        }
    }

    /**
     * 判断是否存在记录
     * @param cmcProductLimit
     * @return
     */
    private boolean isExist(CmcProductLimit cmcProductLimit){
        List<CmcProductLimit> cmcProductLimits =
                cmcProductLimitDao.selectList("selectByPrimaryKey",cmcProductLimit);

        return cmcProductLimits != null && cmcProductLimits.size()>0;
    }

    @RequestMapping("/getAll")
    public void getAll(HttpServletRequest request,PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<Map> cmcProductLimits = cmcProductLimitDao.getAll(request.getParameter("cardProductCode"));
        out.put("data", cmcProductLimits);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, PrintWriter printWriter){
        CmcProductLimit cmcProductLimit = new CmcProductLimit();
        cmcProductLimit.setCardProductCode(request.getParameter("cardProductCode"));
        cmcProductLimit.setChannelType(request.getParameter("channelType"));
        cmcProductLimit.setPeriod(request.getParameter("period"));
        cmcProductLimit.setCcy(request.getParameter("ccy"));
        cmcProductLimit.setReqNum(request.getParameter("reqNum"));

        JSONObject out = new JSONObject();
        check(cmcProductLimit);
        try {
            LocalMapUtils.mapCardProdLimit(cmcProductLimit,DataPostUtil.Delete,cmcProductLimit.getReqNum());
            out.put("retStatus", "S");
            out.put("retMsg", "删除成功");

        }catch (Exception e){
            logger.error("卡限额信息删除失败！" +e.getMessage());
            out.put("retStatus", "F");
            out.put("retMsg", "卡限额信息删除失败: "+e.getMessage());
        }
        printWriter.print(JSON.toJSONString(out));
        printWriter.flush();
        printWriter.close();
    }
}
