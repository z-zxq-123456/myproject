package com.dcits.ensemble.om.pm.action.tools;

import com.alibaba.fastjson.JSON;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaSelectFieldsDao;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaFieldsColumnDao;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaFieldsTableDao;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaNamespaceOrgDao;
import com.dcits.ensemble.om.pm.tools.CodeHandler;
import com.dcits.orion.core.util.BusinessUtils;
import com.dcits.galaxy.base.exception.GalaxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/createCode")
public class CreateCodeAction {

    private static final Logger log = LoggerFactory
            .getLogger(CreateCodeAction.class);
    @Resource
    private ParaFieldsTableDao paraTableFiledsDao;
    @Resource
    private ParaFieldsColumnDao paraFieldsColumnDao;
    @Resource
    private ParaNamespaceOrgDao paraNamespaceOrgDao;
    @Resource
    private ParaSelectFieldsDao paraSelectFieldsDao;

    @RequestMapping(value = "createParameter", method = RequestMethod.POST)
    public void createParameter(HttpServletRequest request, PrintWriter printWriter) {
        String table = request.getParameter("table");
        String jspPackage = request.getParameter("jspPackage");
        String mainPath = request.getSession().getServletContext().getRealPath("/");

        if (BusinessUtils.isNull(jspPackage))
            throw new GalaxyException("Jsp包路径定义必须输入");
        String creator = (String) request.getSession().getAttribute("UserName");
        for (String tab : table.split(",")) {
            if (log.isDebugEnabled()) {
                log.debug(" jspPackage[" + jspPackage + "], , tab[" + tab + "]");
            }
        }
        Map<String, String> result = new HashMap<String, String>();
        try {
            for (String tab : table.split(",")) {
                CodeHandler.createParameterCode(jspPackage, tab, creator, mainPath, paraTableFiledsDao, paraFieldsColumnDao, paraNamespaceOrgDao, paraSelectFieldsDao);
                result.put("retStatus", "S");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("retStatus", "F");
            result.put("retMsg", "生成文件失败");
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
}
