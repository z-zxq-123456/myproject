package com.dcits.ensemble.om.cmc.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.cmc.dao.CmcCardNoRoleExDao;
import com.dcits.ensemble.om.cmc.dbModel.CmcCardNoRoleEx;
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
@RequestMapping("/cardNoRoleEx")
public class CmcCardNoRoleExAction {

    private static final Logger logger = LoggerFactory.getLogger(CmcCardNoRoleExAction.class);

    @Resource
    private CmcCardNoRoleExDao cmcCardNoRoleExDao;

    @RequestMapping("/add")
    public void insert(@RequestBody CmcCardNoRoleEx cmcCardNoRoleEx, PrintWriter printWriter){
        Map<String,String> result = new HashMap<>();

        check(cmcCardNoRoleEx);

       logger.debug("add cmcCardNoRoleEx : " + JSON.toJSONString(cmcCardNoRoleEx));

        try {
            if (isExist(cmcCardNoRoleEx)){
                throw new RuntimeException("this paramName is duplicated!");
            }
            LocalMapUtils.mapCardNoRoleEx(cmcCardNoRoleEx,"Insert",cmcCardNoRoleEx.getReqNum());
            result.put("success", "success");
        }catch (Exception e){
            logger.error("卡渠道信息添加失败！" +e.getMessage());
            result.put("errorMsg", "卡渠道信息添加失败: " +e.getMessage());
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/update")
    public void update(@RequestBody CmcCardNoRoleEx cmcCardNoRoleEx, PrintWriter printWriter){
        Map<String,String> result = new HashMap<>();
        check(cmcCardNoRoleEx);
        logger.debug("update cmcCardNoRoleEx : " + JSON.toJSONString(cmcCardNoRoleEx));

        try {
            LocalMapUtils.mapCardNoRoleEx(cmcCardNoRoleEx, DataPostUtil.UPDATE,cmcCardNoRoleEx.getReqNum());
            result.put("success", "success");

        }catch (Exception e){
            logger.error("卡渠道信息更新失败！" +e.getMessage());
            result.put("errorMsg", "卡渠道信息更新失败: "+e.getMessage());
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    /**
     * cmcProductInfo
     * @param cmcCardNoRoleEx
     */
    private void check(CmcCardNoRoleEx cmcCardNoRoleEx){
        if (cmcCardNoRoleEx == null || cmcCardNoRoleEx.getParamName()==null || cmcCardNoRoleEx.getParamName().equals("")){
            throw new RuntimeException("cmcCardNoRoleEx is not allowed null!");
        }
    }

    /**
     * cmcProductInfo
     * @param paramName
     */
    private void check(String  paramName){
        if (paramName == null || paramName.equals("")){
            throw new RuntimeException("cmcCardNoRoleEx is not allowed null!");
        }
    }

    /**
     * 判断是否存在记录
     * @param cmcCardNoRoleEx
     * @return
     */
    private boolean isExist(CmcCardNoRoleEx cmcCardNoRoleEx){
        List<CmcCardNoRoleEx> cmcCardNoRoleExes =
                cmcCardNoRoleExDao.selectList("selectByPrimaryKey",cmcCardNoRoleEx);

        return cmcCardNoRoleExes != null && cmcCardNoRoleExes.size()>0;
    }

    @RequestMapping("/getAll")
    public void getAll(HttpServletRequest request,PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<Map> cmcCardNoRoleExes = cmcCardNoRoleExDao.getAll(request.getParameter("paramName"));
        out.put("data", cmcCardNoRoleExes);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, PrintWriter printWriter){
        CmcCardNoRoleEx cmcCardNoRoleEx = new CmcCardNoRoleEx();
        cmcCardNoRoleEx.setParamName(request.getParameter("paramName"));
        cmcCardNoRoleEx.setReqNum(request.getParameter("reqNum"));

        JSONObject out = new JSONObject();
        check(cmcCardNoRoleEx);
        try {
            LocalMapUtils.mapCardNoRoleEx(cmcCardNoRoleEx,DataPostUtil.Delete,cmcCardNoRoleEx.getReqNum());
            out.put("retStatus", "S");
            out.put("retMsg", "删除成功");

        }catch (Exception e){
            logger.error("卡渠道信息删除失败！" +e.getMessage());
            out.put("retStatus", "F");
            out.put("retMsg", "卡渠道信息删除失败: "+e.getMessage());
        }
        printWriter.print(JSON.toJSONString(out));
        printWriter.flush();
        printWriter.close();
    }
}
