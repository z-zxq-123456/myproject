package com.dcits.ensemble.om.cmc.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.cmc.dao.CmcCardNoRoleInfoDao;
import com.dcits.ensemble.om.cmc.dbModel.CmcCardNoRoleInfo;
import com.dcits.ensemble.om.cmc.util.DataPostUtil;
import com.dcits.ensemble.om.cmc.util.ErrorMsgHandle;
import com.dcits.ensemble.om.cmc.util.LocalMapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/8/26
 */
@Controller
@RequestMapping("/cardNoRoleInfo")
public class CmcCardNoRoleInfoAction {

    private static final Logger logger = LoggerFactory.getLogger(CmcCardNoRoleInfoAction.class);

    @Resource
    private CmcCardNoRoleInfoDao cmcCardNoRoleInfoDao;

    @RequestMapping("/add")
    public void insert(@RequestBody CmcCardNoRoleInfo cmcCardNoRoleInfo, PrintWriter printWriter){
        Map<String,String> result = new HashMap<>();

        check(cmcCardNoRoleInfo);

       logger.debug("add cmcCardNoRoleInfo : " + JSON.toJSONString(cmcCardNoRoleInfo));

        try {
            if (isExist(cmcCardNoRoleInfo)){
                throw new RuntimeException("this cmcCardNoRoleInfo is duplicated!");
            }
            LocalMapUtils.mapCardNoRoleInfo(cmcCardNoRoleInfo,"Insert",cmcCardNoRoleInfo.getReqNum());
            result.put("success", "success");
        }catch (Exception e){
            logger.error("卡规则定义信息添加失败！" +e.getMessage());
            result.put("errorMsg", "卡规则定义信息添加失败: " +e.getMessage());
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/update")
    public void update(@RequestBody CmcCardNoRoleInfo cmcCardNoRoleInfo, PrintWriter printWriter){
        Map<String,String> result = new HashMap<>();
        check(cmcCardNoRoleInfo);
        logger.debug("update cmcCardNoRoleInfo : " + JSON.toJSONString(cmcCardNoRoleInfo));

        try {
            LocalMapUtils.mapCardNoRoleInfo(cmcCardNoRoleInfo, DataPostUtil.UPDATE,cmcCardNoRoleInfo.getReqNum());
            result.put("success", "success");

        }catch (Exception e){
            logger.error("卡规则定义信息更新失败！" +e.getMessage());
            result.put("errorMsg", "卡规则定义信息更新失败: "+e.getMessage());
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    /**
     * cmcProductInfo
     * @param cmcCardNoRoleInfo
     */
    private void check(CmcCardNoRoleInfo cmcCardNoRoleInfo){
        if (cmcCardNoRoleInfo == null || cmcCardNoRoleInfo.getCardNoRoleCode()==null || cmcCardNoRoleInfo.getCardNoRoleCode().equals("")){
            throw new RuntimeException("cmcCardNoRoleInfo is not allowed null!");
        }
    }

    /**
     * cmcProductInfo
     * @param cardNoRoleCode
     */
    private void check(String  cardNoRoleCode){
        if (cardNoRoleCode == null || cardNoRoleCode.equals("")){
            throw new RuntimeException("cardNoRoleCode is not allowed null!");
        }
    }

    /**
     * 判断是否存在记录
     * @param cardNoRoleCode
     * @return
     */
    private boolean isExist(CmcCardNoRoleInfo cardNoRoleCode){
        List<CmcCardNoRoleInfo> cmcCardNoRoleInfos =
                cmcCardNoRoleInfoDao.selectList("selectByPrimaryKey",cardNoRoleCode);

        return cmcCardNoRoleInfos != null && cmcCardNoRoleInfos.size()>0;
    }

    @RequestMapping("/getAll")
    public void getAll(HttpServletRequest request,PrintWriter printWriter){
        JSONObject out = new JSONObject();
        try {
            List<CmcCardNoRoleInfo> cmcCardNoRoleInfos = cmcCardNoRoleInfoDao.getAll(request.getParameter("cardNoRoleCode"));
            if (cmcCardNoRoleInfos!=null && cmcCardNoRoleInfos.size()>0){
                out.put("data", cmcCardNoRoleInfos);
            }else {
                out.put("data", new ArrayList<CmcCardNoRoleInfo>());
            }
            printWriter.print(JSON.toJSONString(out));
            printWriter.flush();
            printWriter.close();
        }catch (Exception e){
            logger.error("query cardNoRoleCode records exception : " + e.getMessage());
            ErrorMsgHandle.errorMsg(e.getMessage(),printWriter);
        }
    }

    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, PrintWriter printWriter){
        CmcCardNoRoleInfo cmcCardNoRoleInfo = new CmcCardNoRoleInfo();
        cmcCardNoRoleInfo.setCardNoRoleCode(request.getParameter("cardNoRoleCode"));
        cmcCardNoRoleInfo.setReqNum(request.getParameter("reqNum"));

        JSONObject out = new JSONObject();
        check(cmcCardNoRoleInfo);
        try {
            LocalMapUtils.mapCardNoRoleInfo(cmcCardNoRoleInfo,DataPostUtil.Delete,cmcCardNoRoleInfo.getReqNum());
            out.put("retStatus", "S");
            out.put("retMsg", "删除成功");

        }catch (Exception e){
            logger.error("卡规则定义信息删除失败！" +e.getMessage());
            out.put("retStatus", "F");
            out.put("retMsg", "卡规则定义信息删除失败: "+e.getMessage());
        }
        printWriter.print(JSON.toJSONString(out));
        printWriter.flush();
        printWriter.close();
    }
}
