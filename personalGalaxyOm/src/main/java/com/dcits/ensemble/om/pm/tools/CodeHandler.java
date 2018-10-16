package com.dcits.ensemble.om.pm.tools;

import com.dcits.ensemble.om.pm.dao.paraSetting.ParaFieldsColumnDao;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaFieldsTableDao;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaNamespaceOrgDao;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaSelectFieldsDao;
import com.dcits.ensemble.om.pm.tools.parameter.*;
import com.dcits.galaxy.base.exception.GalaxyException;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CodeHandler {
    private static final Logger log = LoggerFactory.getLogger(CodeHandler.class);
    /**
     *
     *
     * @param module
     * @param envId
     * @param tab
     * @param creator
     * @return
     */
    /**
     * 生成参数平台代码
     */
    public static void createParameterCode( String jspPackage, String tab, String creator ,String mainPath,ParaFieldsTableDao paraTableFiledsDao, ParaFieldsColumnDao paraFieldsColumnDao,ParaNamespaceOrgDao paraNamespaceOrgDao,ParaSelectFieldsDao paraSelectFieldsDao) {
        try {

            CreateParameterCode cm = new CreateJsp(jspPackage);
            cm.createParameterCode( tab, creator,mainPath,paraTableFiledsDao,paraFieldsColumnDao,paraNamespaceOrgDao,paraSelectFieldsDao);
            CreateParameterCode cn = new CreateJspAdd(jspPackage);
            cn.createParameterCode(tab, creator,mainPath,paraTableFiledsDao,paraFieldsColumnDao,paraNamespaceOrgDao,paraSelectFieldsDao);
            CreateParameterCode cd = new CreateJspMod(jspPackage);
            cd.createParameterCode( tab, creator,mainPath,paraTableFiledsDao,paraFieldsColumnDao,paraNamespaceOrgDao,paraSelectFieldsDao);

            CreateParameterCode cmJs = new CreateJavaScript(jspPackage);
            cmJs.createParameterCode( tab, creator,mainPath,paraTableFiledsDao,paraFieldsColumnDao,paraNamespaceOrgDao,paraSelectFieldsDao);
            CreateParameterCode cnJs = new CreateJavaScriptAdd(jspPackage);
            cnJs.createParameterCode(tab, creator,mainPath,paraTableFiledsDao,paraFieldsColumnDao,paraNamespaceOrgDao,paraSelectFieldsDao);
            CreateParameterCode cdJs = new CreateJavaScriptMod(jspPackage);
            cdJs.createParameterCode( tab, creator,mainPath,paraTableFiledsDao,paraFieldsColumnDao,paraNamespaceOrgDao,paraSelectFieldsDao);

        } catch (IOException | TemplateException e) {
            e.printStackTrace();
            throw new GalaxyException("生成Code失败，" + e.getMessage());
        }
    }

}
