package com.dcits.ensemble.om.cmc.action;

import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.cmc.dataTable.CmcTranInfoModel;
import com.dcits.ensemble.om.cmc.model.CmcTranInfo;
import com.dcits.ensemble.om.cmc.service.CmcCardTranHistService;
import com.dcits.ensemble.om.cmc.util.BeanConvertUtils;
import com.dcits.ensemble.om.cmc.util.ErrorMsgHandle;
import com.dcits.ensemble.om.pf.util.SmartAccounting.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dangyk on 2018/4/20.
 */
@Controller
@RequestMapping("/cardTranHist")
public class CmcCardTranHistAction {

    private static Logger logger = LoggerFactory.getLogger(CmcCardTranHistAction.class);

    @Resource
    private CmcCardTranHistService cmcCardTranHistService;

    @RequestMapping("/findHist")
    public void getCardTranHist(HttpServletRequest request, PrintWriter printWriter){

        String cardNo = request.getParameter("cardNo");
        String startDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");
        JSONObject requestParams = new JSONObject();
        requestParams.put("CARD_NO",cardNo);
        requestParams.put("BEGIN_DATE",startDate);
        requestParams.put("END_DATE",endDate);

        //查询卡交易历史
        List<CmcTranInfoModel> cmcTranInfoModelList = new ArrayList<>();
        try{
            CmcTranInfo cmcTranInfoList = cmcCardTranHistService.getCmcTranHist(requestParams);
            if(cmcTranInfoList != null){
                for(CmcTranInfo.CardTranHistArray model : cmcTranInfoList.getCardTranHistArray()){
                    CmcTranInfoModel cmcTranInfoModel = new CmcTranInfoModel();
                    cmcTranInfoModel = (CmcTranInfoModel)BeanConvertUtils.convertToDataTableModel(model,cmcTranInfoModel,true,null);
                    cmcTranInfoModelList.add(cmcTranInfoModel);
                }
            }
            //结果返回
            JSONObject result = new JSONObject();
            result.put("data",cmcTranInfoModelList);
            printWriter.print(result.toJSONString());
            printWriter.flush();
            printWriter.close();
            if (logger.isDebugEnabled()){
                logger.debug("卡交易历史查询成功！"
                        + result.toJSONString());
            }
        }catch(Exception e){
            ErrorMsgHandle.errorMsg(e.getMessage(),printWriter);
            logger.error("卡交易流水查询异常： " + e.getMessage());
        }

    }


}
