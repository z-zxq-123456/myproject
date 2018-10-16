package com.dcits.ensemble.om.pm.dao.paraSetting;

import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaSystemOrg;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParaSystemOrgDao extends BaseDao {

    //The nameSpace that is defined in the module file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pm.dao.paraSetting.ParaSystemOrgDao";

    public List<ParaSystemOrg> getSystemList() {
        return super.selectList(getNameSpace(), "findList");
    }
    /**
     * This method corresponds to the database table PARA_SYSTEM_ORG
     */
    public List<PkList> getSystem(){
        return super.selectList(getNameSpace(),"getSystem");
    }

    public List<PkList> getSystemIngnoreProd(){
        return super.selectList(getNameSpace(),"getSystemIgnoreProd");
    }
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}