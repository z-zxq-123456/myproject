package com.dcits.ensemble.om.pm.dao.paraSetting;

import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaModuleOrg;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParaModuleOrgDao extends BaseDao {

    //The nameSpace that is defined in the module file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pm.dao.paraSetting.ParaModuleOrgDao";

    /**
     * This method corresponds to the database table PARA_MODULE_ORG
     */
    public List<ParaModuleOrg> getModuleList() {
        return super.selectList(getNameSpace(), "findList");
    }
    public List<PkList> getModuleBySystem(String systemId){
        return super.selectList(getNameSpace(),"getModuleBySystem",systemId);
    }
    public ParaModuleOrg selectModule(ParaModuleOrg moduleOrg){
        return super.selectOne(getNameSpace(), "selectByPrimaryKey", moduleOrg);
    }
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}