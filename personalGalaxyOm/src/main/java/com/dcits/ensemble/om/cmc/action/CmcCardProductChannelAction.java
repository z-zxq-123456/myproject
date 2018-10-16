package com.dcits.ensemble.om.cmc.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.cmc.dao.CmcCardProductChannelDao;
import com.dcits.ensemble.om.cmc.dbModel.CmcCardProductChannel;
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
@RequestMapping("/cardProductChannel")
public class CmcCardProductChannelAction {

    private static final Logger logger = LoggerFactory.getLogger(CmcCardProductChannelAction.class);

    @Resource
    private CmcCardProductChannelDao cmcCardProductChannelDao;

    @RequestMapping("/add")
    public void insert(@RequestBody CmcCardProductChannel cmcCardProductChannel, PrintWriter printWriter){
        Map<String,String> result = new HashMap<>();

        check(cmcCardProductChannel);

       logger.debug("add cmcCardProductChannel : " + JSON.toJSONString(cmcCardProductChannel));

        try {
            if (isExist(cmcCardProductChannel)){
                throw new RuntimeException("this cardProductCode is duplicated!");
            }
            LocalMapUtils.mapCardProdChannel(cmcCardProductChannel,"Insert",cmcCardProductChannel.getReqNum());
            result.put("success", "success");
        }catch (Exception e){
            logger.error("卡产品渠道限制添加失败！" +e.getMessage());
            result.put("errorMsg", "卡产品渠道限制添加失败: " +e.getMessage());
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/update")
    public void update(@RequestBody CmcCardProductChannel cmcCardProductChannel, PrintWriter printWriter){
        Map<String,String> result = new HashMap<>();
        check(cmcCardProductChannel);
        logger.debug("update cmcCardProductChannel : " + JSON.toJSONString(cmcCardProductChannel));

        try {
            LocalMapUtils.mapCardProdChannel(cmcCardProductChannel, DataPostUtil.UPDATE,cmcCardProductChannel.getReqNum());
            result.put("success", "success");

        }catch (Exception e){
            logger.error("卡产品渠道限制更新失败！" +e.getMessage());
            result.put("errorMsg", "卡产品渠道限制更新失败: "+e.getMessage());
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    /**
     * cmcProductInfo
     * @param cmcCardProductChannel
     */
    private void check(CmcCardProductChannel cmcCardProductChannel){
        if (cmcCardProductChannel == null || cmcCardProductChannel.getCardProductCode()==null || cmcCardProductChannel.getCardProductCode().equals("")){
            throw new RuntimeException("cmcCardProductChannel is not allowed null!");
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
     * @param cmcCardProductChannel
     * @return
     */
    private boolean isExist(CmcCardProductChannel cmcCardProductChannel){
        List<CmcCardProductChannel> cmcCardProductChannels =
                cmcCardProductChannelDao.selectList("selectByPrimaryKey",cmcCardProductChannel);

        return cmcCardProductChannels != null && cmcCardProductChannels.size()>0;
    }

    @RequestMapping("/getAll")
    public void getAll(HttpServletRequest request,PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<Map> cmcCardProductChannels = cmcCardProductChannelDao.getAll(request.getParameter("cardProductCode"));
        out.put("data", cmcCardProductChannels);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, PrintWriter printWriter){

        CmcCardProductChannel cmcCardProductChannel = new CmcCardProductChannel();
        cmcCardProductChannel.setCardProductCode(request.getParameter("cardProductCode"));
        cmcCardProductChannel.setLimitChannel(request.getParameter("limitChannel"));
        cmcCardProductChannel.setAuthTranType(request.getParameter("authTranType"));
        cmcCardProductChannel.setReqNum(request.getParameter("reqNum"));

        JSONObject out = new JSONObject();
        check(cmcCardProductChannel);
        try {
            LocalMapUtils.mapCardProdChannel(cmcCardProductChannel,DataPostUtil.Delete,cmcCardProductChannel.getReqNum());
            out.put("retStatus", "S");
            out.put("retMsg", "删除成功");

        }catch (Exception e){
            logger.error("卡产品渠道限制删除失败！" +e.getMessage());
            out.put("retStatus", "F");
            out.put("retMsg", "卡产品渠道限制删除失败: "+e.getMessage());
        }
        printWriter.print(JSON.toJSONString(out));
        printWriter.flush();
        printWriter.close();
    }
}
