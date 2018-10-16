package com.dcits.ensemble.om.cmc.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.cmc.dao.CmcCardOrderNoInfoDao;
import com.dcits.ensemble.om.cmc.dbModel.CmcCardOrderNoInfo;
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
@RequestMapping("/cardOrderNoInfo")
public class CmcCardOrderNoInfoAction {

    private static final Logger logger = LoggerFactory.getLogger(CmcCardOrderNoInfoAction.class);

    @Resource
    private CmcCardOrderNoInfoDao cmcCardOrderNoInfoDao;

    @RequestMapping("/add")
    public void insert(@RequestBody CmcCardOrderNoInfo cmcCardOrderNoInfo, PrintWriter printWriter){
        Map<String,String> result = new HashMap<>();

        check(cmcCardOrderNoInfo);

       logger.debug("add cmcCardOrderNoInfo : " + JSON.toJSONString(cmcCardOrderNoInfo));

        try {
            if (isExist(cmcCardOrderNoInfo)){
                throw new RuntimeException("this cmcCardOrderNoInfo is duplicated!");
            }
            LocalMapUtils.mapCardOrderNoInfo(cmcCardOrderNoInfo,"Insert",cmcCardOrderNoInfo.getReqNum());
            result.put("success", "success");
        }catch (Exception e){
            logger.error("卡顺序信息添加失败！" +e.getMessage());
            result.put("errorMsg", "卡顺序信息添加失败: " +e.getMessage());
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/update")
    public void update(@RequestBody CmcCardOrderNoInfo cmcCardOrderNoInfo, PrintWriter printWriter){
        Map<String,String> result = new HashMap<>();
        check(cmcCardOrderNoInfo);
        logger.debug("update cmcCardOrderNoInfo : " + JSON.toJSONString(cmcCardOrderNoInfo));

        try {
            LocalMapUtils.mapCardOrderNoInfo(cmcCardOrderNoInfo, DataPostUtil.UPDATE,cmcCardOrderNoInfo.getReqNum());
            result.put("success", "success");

        }catch (Exception e){
            logger.error("卡顺序信息更新失败！" +e.getMessage());
            result.put("errorMsg", "卡顺序信息更新失败: "+e.getMessage());
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    /**
     * cmcProductInfo
     * @param cmcCardOrderNoInfo
     */
    private void check(CmcCardOrderNoInfo cmcCardOrderNoInfo){
        if (cmcCardOrderNoInfo == null || cmcCardOrderNoInfo.getProductRuleNo()==null || cmcCardOrderNoInfo.getProductRuleNo().equals("")){
            throw new RuntimeException("cmcCardOrderNoInfo is not allowed null!");
        }
    }

    /**
     * cmcProductInfo
     * @param cardProductCode
     */
    private void check(String  cardProductCode){
        if (cardProductCode == null || cardProductCode.equals("")){
            throw new RuntimeException("cmcCardOrderNoInfo is not allowed null!");
        }
    }

    /**
     * 判断是否存在记录
     * @param cmcCardOrderNoInfo
     * @return
     */
    private boolean isExist(CmcCardOrderNoInfo cmcCardOrderNoInfo){
        List<CmcCardOrderNoInfo> cmcCardOrderNoInfos =
                cmcCardOrderNoInfoDao.selectList("selectByPrimaryKey",cmcCardOrderNoInfo);

        return cmcCardOrderNoInfos != null && cmcCardOrderNoInfos.size()>0;
    }

    @RequestMapping("/getAll")
    public void getAll(HttpServletRequest request,PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<CmcCardOrderNoInfo> cmcCardOrderNoInfos = cmcCardOrderNoInfoDao.getAll(request.getParameter("productRuleNo"));
        out.put("data", cmcCardOrderNoInfos);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, PrintWriter printWriter){
        CmcCardOrderNoInfo cmcCardOrderNoInfo = new CmcCardOrderNoInfo();
        cmcCardOrderNoInfo.setProductRuleNo(request.getParameter("productRuleNo"));
        cmcCardOrderNoInfo.setReqNum(request.getParameter("reqNum"));

        JSONObject out = new JSONObject();
        check(cmcCardOrderNoInfo);
        try {
            LocalMapUtils.mapCardOrderNoInfo(cmcCardOrderNoInfo,DataPostUtil.Delete,cmcCardOrderNoInfo.getReqNum());
            out.put("retStatus", "S");
            out.put("retMsg", "删除成功");

        }catch (Exception e){
            logger.error("卡顺序信息删除失败！" +e.getMessage());
            out.put("retStatus", "F");
            out.put("retMsg", "卡顺序信息删除失败: "+e.getMessage());
        }
        printWriter.print(JSON.toJSONString(out));
        printWriter.flush();
        printWriter.close();
    }
}
