package com.dcits.ensemble.om.pm.dao.paraSetting;

import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaEnvOrg;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FmRefCodeDao extends BaseDao {

    //The nameSpace that is defined in the module file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pm.dao.paraSetting.FmRefCodeDao";

    /**
     * This method corresponds to the database table PARA_ENV_ORG
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }

    public List<PkList> getCtrlValue(){
        return super.selectList(getNameSpace(),"getCtrlValue");
    }

}