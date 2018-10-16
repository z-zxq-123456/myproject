package com.dcits.ensemble.om.cmc.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.ensemble.om.cmc.dao.CmcRuleNoDao;
import com.dcits.ensemble.om.cmc.dbModel.CmcCardProductChannel;
import com.dcits.ensemble.om.cmc.dbModel.CmcRuleNo;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: ensemble-om
 * @Date: 2018/9/20 9:07
 * @Author: Mr.Zhang
 * @Description:
 */
@Controller
@RequestMapping("/cmcRuleNo")
public class CmcRuleNoAction {
    private static final Logger logger = LoggerFactory.getLogger(CmcRuleNoAction.class);

    @Resource
    private CmcRuleNoDao cmcRuleNoDao;

    @RequestMapping("/add")
    public void insert(@RequestBody CmcRuleNo cmcRuleNo, PrintWriter printWriter){
        Map<String,String> result = new HashMap<>();

        check(cmcRuleNo);

        logger.debug("add cmcRuleNo : " + JSON.toJSONString(cmcRuleNo));

        try {
            if (isExist(cmcRuleNo)){
                throw new RuntimeException("this ruleNo and ruleEx is duplicated!");
            }
            cmcRuleNoDao.insert("insert",cmcRuleNo);
            result.put("success", "success");
        }catch (Exception e){
            logger.error("卡规则定义添加失败！" +e.getMessage());
            result.put("errorMsg", "卡规则定义添加失败: " +e.getMessage());
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/update")
    public void update(@RequestBody CmcRuleNo cmcRuleNo, PrintWriter printWriter){
        Map<String,String> result = new HashMap<>();
        check(cmcRuleNo);
        logger.debug("update cmcRuleNo : " + JSON.toJSONString(cmcRuleNo));

        try {
            cmcRuleNoDao.update("updateByPrimaryKey",cmcRuleNo);
            result.put("success", "success");

        }catch (Exception e){
            logger.error("卡规则定义更新失败！" +e.getMessage());
            result.put("errorMsg", "卡规则定义更新失败: "+e.getMessage());
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, PrintWriter printWriter){

        CmcRuleNo cmcRuleNo = new CmcRuleNo();
        cmcRuleNo.setRuleCode(request.getParameter("ruleCode"));
        JSONObject out = new JSONObject();
        check(cmcRuleNo);
        try {
            cmcRuleNoDao.delete("deleteByPrimaryKey",cmcRuleNo);
            out.put("retStatus", "S");
            out.put("retMsg", "删除成功");

        }catch (Exception e){
            logger.error("卡规则定义删除失败！" +e.getMessage());
            out.put("retStatus", "F");
            out.put("retMsg", "卡规则定义删除失败: "+e.getMessage());
        }
        printWriter.print(JSON.toJSONString(out));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/getAll")
    public void getAll(HttpServletRequest request,PrintWriter printWriter){
        JSONObject out = new JSONObject();
        List<CmcRuleNo> cmcRuleNos = cmcRuleNoDao.getAll();
        out.put("data", cmcRuleNos);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/selectByPrimaryKey")
    public void selectByPrimaryKey(HttpServletRequest request,PrintWriter printWriter){
        Map<String,String> requestArgs = new HashMap<>();
        requestArgs.put("ruleCode",request.getParameter("ruleCode"));
        requestArgs.put("ruleNo",request.getParameter("ruleNo"));
        JSONObject out = new JSONObject();
        CmcRuleNo cmcRuleNo = cmcRuleNoDao.getByPrimaryKey(requestArgs);
        out.put("ruleEx", cmcRuleNo.getRuleEx());
        out.put("ruleNo", cmcRuleNo.getRuleNo());
        out.put("ruleDesc", cmcRuleNo.getRuleDesc());
        out.put("ruleCode", cmcRuleNo.getRuleCode());
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("/getAllForSelect")
    public void getAllForSelect(HttpServletRequest request,PrintWriter printWriter){
        String tableCol = request.getParameter("tableCol");
        List<PkList> pklist = new ArrayList<>();
        List<CmcRuleNo> cmcRuleNos = cmcRuleNoDao.getAll();
        if(cmcRuleNos!=null && cmcRuleNos.size()>0){
            for (CmcRuleNo cmcRuleNo:cmcRuleNos ) {
                PkList pK=new PkList();
                if("RULE_CODE".equals(tableCol)){
                    pK.setPkValue(cmcRuleNo.getRuleCode());
                    pK.setPkDesc(cmcRuleNo.getRuleCode()+" "+cmcRuleNo.getRuleDesc());

                }else if("RULE_EX".equals(tableCol)){
                    pK.setPkValue(cmcRuleNo.getRuleEx());
                    pK.setPkDesc(cmcRuleNo.getRuleEx()+" "+cmcRuleNo.getRuleDesc());

                }else if("RULE_NO".equals(tableCol)){
                    pK.setPkValue(cmcRuleNo.getRuleNo());
                    pK.setPkDesc(cmcRuleNo.getRuleNo()+" "+cmcRuleNo.getRuleDesc());

                }
                pklist.add(pK);

            }
        }
        System.out.println("pKLiST:"+JSON.toJSONString(pklist));
        printWriter.print(JSON.toJSONString(pklist));
        printWriter.flush();
        printWriter.close();
    }


    private void check(CmcRuleNo cmcRuleNo){
        if (cmcRuleNo == null || cmcRuleNo.getRuleCode()==null || "".equals(cmcRuleNo.getRuleCode())){
            throw new RuntimeException("cmcRuleNo is not allowed null!");
        }
    }

    /**
     * 判断是否存在记录
     * @param cmcRuleNo
     * @return
     */
    private boolean isExist(CmcRuleNo cmcRuleNo){
        List<CmcRuleNo> cmcRuleNos =cmcRuleNoDao.selectList("selectByPrimaryKey",cmcRuleNo);

        return cmcRuleNos != null && cmcRuleNos.size()>0;
    }

}
