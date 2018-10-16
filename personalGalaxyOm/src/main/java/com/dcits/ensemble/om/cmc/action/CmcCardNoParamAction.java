package com.dcits.ensemble.om.cmc.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.cmc.dao.CmcCardNoParamDao;
import com.dcits.ensemble.om.cmc.dbModel.CmcCardNoParam;
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
@RequestMapping("/cardNoParam")
public class CmcCardNoParamAction {

    private static final Logger logger = LoggerFactory.getLogger(CmcCardNoParamAction.class);
    @Resource
    private CmcCardNoParamDao cmcCardNoParamDao;

    @RequestMapping("/add")
    public void insert(@RequestBody CmcCardNoParam cmcCardNoParam,PrintWriter printWriter){

        check(cmcCardNoParam);
        Map<String,String> result = new HashMap<>();
       logger.debug("add cmcCardNoParam : " + JSON.toJSONString(cmcCardNoParam));
        try {
            if (isExist(cmcCardNoParam)){
                throw new RuntimeException("this cardProductCode is duplicated!");
            }
            LocalMapUtils.mapCardNoParam(cmcCardNoParam, DataPostUtil.INSERT,cmcCardNoParam.getReqNum());
            result.put("success", "success");
        }catch (Exception e){
            logger.error("预制卡参数信息添加失败！" +e.getMessage());
            result.put("errorMsg", "预制卡参数信息添加失败: " +e.getMessage());
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/update")
    public void update(@RequestBody CmcCardNoParam cmcCardNoParam, PrintWriter printWriter){

        check(cmcCardNoParam);
        Map<String,String> result = new HashMap<>();
        try {
            LocalMapUtils.mapCardNoParam(cmcCardNoParam, DataPostUtil.UPDATE, cmcCardNoParam.getReqNum());
            result.put("success", "success");
        }catch (Exception e){
            logger.error("预制卡参数信息更新失败！" +e.getMessage());
            result.put("errorMsg", "预制卡参数信息更新失败: "+e.getMessage());
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    /**
     * cmcProductInfo
     * @param cmcCardNoParam
     */
    private void check(CmcCardNoParam cmcCardNoParam){
        if (cmcCardNoParam == null || cmcCardNoParam.getProductRuleNo()==null || cmcCardNoParam.getProductRuleNo().equals("")){
            throw new RuntimeException("cmcCardNoParam is not allowed null!");
        }
    }

    /**
     * cmcProductInfo
     * @param cmcCardNoParam
     */
    private void check(String  cmcCardNoParam){
        if (cmcCardNoParam == null || cmcCardNoParam.equals("")){
            throw new RuntimeException("cmcCardNoParam is not allowed null!");
        }
    }

    /**
     * 判断是否存在记录
     * @param cmcCardNoParam
     * @return
     */
    private boolean isExist(CmcCardNoParam cmcCardNoParam){
        List<CmcCardNoParam> cmcCardNoParams =
                cmcCardNoParamDao.selectList("selectByPrimaryKey",cmcCardNoParam);
        return cmcCardNoParams != null && cmcCardNoParams.size()>0;
    }

    @RequestMapping("/getAll")
    public void getAll(HttpServletRequest request,PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<CmcCardNoParam> cmcCardNoParams = cmcCardNoParamDao.getAll(request.getParameter("prodCode"));
        out.put("data", cmcCardNoParams);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, PrintWriter printWriter){

        CmcCardNoParam cmcCardNoParam = new CmcCardNoParam();
        cmcCardNoParam.setProductRuleNo(request.getParameter("productRuleNo"));
        cmcCardNoParam.setReqNum(request.getParameter("reqNum"));
        check(cmcCardNoParam);
        JSONObject out = new JSONObject();
        try {
            LocalMapUtils.mapCardNoParam(cmcCardNoParam, DataPostUtil.Delete, cmcCardNoParam.getReqNum());
            out.put("retStatus", "S");
            out.put("retMsg", "删除成功");
        }catch (Exception e){
            logger.error("预制卡参数信息删除失败！" +e.getMessage());
            out.put("retStatus", "F");
            out.put("retMsg", "预制卡参数信息删除失败: "+e.getMessage());
        }
        printWriter.print(JSON.toJSONString(out));
        printWriter.flush();
        printWriter.close();
    }
}
