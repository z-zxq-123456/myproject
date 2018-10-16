package com.dcits.ensemble.om.cmc.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.cmc.dataTable.CmcCardTableUsedInfoModel;
import com.dcits.ensemble.om.cmc.service.CmcCardTableUsedInfoService;
import com.dcits.ensemble.om.cmc.util.ErrorMsgHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

/**
 * @Description :预制卡表使用量统计
 * @Author :wangpjc
 * @Date : Create on 2018/4/27
 */
@Controller
@RequestMapping("/cardTableUsedInfo")
public class CmcCardTableUsedInfoAction {

    private static Logger logger = LoggerFactory.getLogger(CmcCardTableUsedInfoAction.class);

    @Resource
    private CmcCardTableUsedInfoService cmcCardTableUsedInfoService;

    @RequestMapping("/getCardTableUsedInfo")
    public void getOpenCardRecords(HttpServletRequest request, PrintWriter writer){


        String productRuleNo = request.getParameter("productRuleNo");
        String tableName = request.getParameter("tableName");
        JSONObject requestParams = new JSONObject();
        requestParams.put("PRODUCT_RULE_NO",productRuleNo);
        requestParams.put("TABLE_NAME",tableName);

        try {
           /*预制卡表已使用信息*/
            CmcCardTableUsedInfoModel acountDTModel = cmcCardTableUsedInfoService.qryTableUsedInfo(requestParams);
            JSONObject out = new JSONObject();
            out.put("data",acountDTModel);
            writer.print(out);
            writer.flush();
            writer.close();
            if (logger.isDebugEnabled()){
                logger.debug("预制卡查询成功！"
                        + JSON.toJSONString(out));
            }
        }catch (Exception e){
            ErrorMsgHandle.errorMsg(e.getMessage(),writer);
        }
    }
}
