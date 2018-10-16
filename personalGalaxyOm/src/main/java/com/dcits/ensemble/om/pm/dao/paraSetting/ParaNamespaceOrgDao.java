package com.dcits.ensemble.om.pm.dao.paraSetting;

import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaNamespaceOrg;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParaNamespaceOrgDao extends BaseDao {

    //The nameSpace that is defined in the module file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pm.dao.paraSetting.ParaNamespaceOrgDao";

    /**
     * This method corresponds to the database table PARA_NAMESPACE_ORG
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }

    public List<ParaNamespaceOrg> getNamespaceList() {
        return super.selectList(getNameSpace(), "findList");
    }

    public List<PkList> getTableBySystemModule(ParaNamespaceOrg namespaceOrg){
        return super.selectList(getNameSpace(),"getTableBySystemModule",namespaceOrg);
    }

    public ParaNamespaceOrg getTableForVerifyingToApplication(ParaNamespaceOrg namespaceOrg){
        return super.selectOne(getNameSpace(),"getTableForVerifyingToApplication",namespaceOrg);
    }
    public List<PkList> getTableForModifingDataBySystemModule(ParaNamespaceOrg namespaceOrg){
        return super.selectList(getNameSpace(),"getTableForModifingDataBySystemModule",namespaceOrg);
    }

    public List<PkList> getTableByModule(String moduleId){
        return super.selectList(getNameSpace(),"getTableByModule",moduleId);
    }
    public List<PkList> getTableNamePklist() {
        return super.selectList(getNameSpace(), "selectBaseForPklist");
    }

    public ParaNamespaceOrg selectDataByDbPrimaryKey(ParaNamespaceOrg paraNamespaceOrg){
        return super.selectOne(getNameSpace(), "selectByPrimaryKey", paraNamespaceOrg);
    }
}