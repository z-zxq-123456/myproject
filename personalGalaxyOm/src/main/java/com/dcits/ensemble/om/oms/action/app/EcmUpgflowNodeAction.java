package com.dcits.ensemble.om.oms.action.app;

import com.alibaba.fastjson.JSON;
import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.ensemble.om.oms.manager.app.UpgNodeCache;
import com.dcits.ensemble.om.oms.module.app.EcmUpgflowNode;
import com.dcits.ensemble.om.oms.module.utils.PageData;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * EcmUpgflowNodeAction*
 *
 * @author LuoLang
 * @createdate 2016-02-29
 */

@Controller
public class EcmUpgflowNodeAction {

    private int max_req_no = 0;

    @Resource
    PkServiceUtil serviceUtil;
    @Resource
    IService omsBaseService;
    @Resource
    UpgNodeCache upgNodeCache;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("saveEcmUpgflowNode")
    public void save(HttpServletRequest request, PrintWriter printWriter, EcmUpgflowNode upgflowNode) {
        try {
            int upgflowNodeId = serviceUtil.getMaxReqID(max_req_no, "ecm_upgflow_node", "upgflow_node_id");
            upgflowNode.setUpgflowNodeId(upgflowNodeId);
            omsBaseService.insert(upgflowNode);
            //ActionResultWrite.setsuccessfulResult(printWriter);
            upgNodeCache.removeNodeMap();
            HashMap result = new HashMap();
            result.put("success", "success");
            result.put("id", upgflowNodeId);
            String jsonStr = JSON.toJSONString(result);
            printWriter.print(jsonStr);
            printWriter.flush();
            printWriter.close();
        }catch (Exception e){
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
    }

    @RequestMapping("updateEcmUpgflowNode")
    public void update(HttpServletRequest request, PrintWriter printWriter, EcmUpgflowNode upgflowNode) {
        try {
            omsBaseService.updateByPrimaryKey(upgflowNode);
            ActionResultWrite.setsuccessfulResult(printWriter);
            upgNodeCache.removeNodeMap();
        }catch (Exception e){
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
    }

    @RequestMapping("deleteEcmUpgflowNode")
    public void delete(HttpServletRequest request, PrintWriter printWriter, EcmUpgflowNode upgflowNode) {
        try {
            omsBaseService.deleteByPrimaryKey(upgflowNode);
            ActionResultWrite.setsuccessfulResult(printWriter);
            upgNodeCache.removeNodeMap();
        }catch (Exception e){
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
    }

    @RequestMapping("findEcmUpgflowNode")
    public void find(HttpServletRequest request, PrintWriter printWriter, EcmUpgflowNode upgflowNode) {
        try {
            int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
            int pageSize = ServletRequestUtils.getIntParameter(request, "rows", 10);
            log.info("pageNo =" + pageNo + "pageSize =" + pageSize);
            PageData<EcmUpgflowNode> pageData = omsBaseService.findPageByCond(upgflowNode, pageNo, pageSize);
//        ActionResultWrite.setPageReadResult(printWriter,pageData.getList(),pageData.getTotalNum());
            List<EcmUpgflowNode> ecmUpgflowNode = pageData.getList();
            ActionResultWrite.setReadResult(printWriter, ecmUpgflowNode);
        }catch (Exception e){
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }

    }

    @RequestMapping("findUpgflowNodeComboxA")
    public void findCombox1(HttpServletRequest request, PrintWriter printWriter) {
        List<EcmUpgflowNode> infoList = omsBaseService.findListByCond(new EcmUpgflowNode(), new HashMap<String, Object>());
        List<PkList> lists = new ArrayList<>();
        for (int i = 0; i < infoList.size(); i++) {
            PkList pkList = new PkList();
            pkList.setPkDesc(infoList.get(i).getUpgflowNodeName());
            pkList.setPkValue(infoList.get(i).getUpgflowNodeId() + "");
            lists.add(pkList);
        }
        printWriter.print(JSON.toJSONString(lists));
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("findUpgflowNodeComboxB")
    public void findCombox2(HttpServletRequest request, PrintWriter printWriter) {
        List<EcmUpgflowNode> infoList = omsBaseService.findListByCond(new EcmUpgflowNode(), new HashMap<String, Object>());
        List<PkList> lists = new ArrayList<>();
        for (int i = 0; i < infoList.size(); i++) {
            PkList pkList = new PkList();
            pkList.setPkDesc(infoList.get(i).getUpgflowNodeName());
            pkList.setPkValue(infoList.get(i).getUpgflowNodeId().toString());
            lists.add(pkList);
        }
        printWriter.print(JSON.toJSONString(lists));
        printWriter.flush();
        printWriter.close();
//        ActionResultWrite.setReadResult(printWriter, infoList);
    }
}
