package com.dcits.ensemble.om.pm.tools;

import com.dcits.ensemble.om.pm.dao.paraSetting.ParaFieldsColumnDao;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaFieldsTableDao;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaNamespaceOrgDao;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaSelectFieldsDao;

import freemarker.template.TemplateException;

import java.io.IOException;

public interface CreateParameterCode {
    /**
     * 创建db dao相关code
     *
     * @return code代码生成的Root Path
     * @throws IOException
     * @throws TemplateException
     */
      String createParameterCode(String tableName, String creator,String mainPath,ParaFieldsTableDao paraTableFiledsDao, ParaFieldsColumnDao paraFieldsColumnDao,ParaNamespaceOrgDao paraNamespaceOrgDao,ParaSelectFieldsDao paraSelectFieldsDao)throws IOException, TemplateException;

}
