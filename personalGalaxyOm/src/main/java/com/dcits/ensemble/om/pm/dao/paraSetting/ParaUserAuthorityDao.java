package com.dcits.ensemble.om.pm.dao.paraSetting;

import com.dcits.ensemble.om.pm.module.paraSetting.ParaUserAuthority;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by maruie on 2016/03/30 18:35:22.
 */
@Repository
public class ParaUserAuthorityDao extends BaseDao {
    public ParaUserAuthority selectRightByuserId(String userId){
        return  super.selectOne(getNameSpace(),"selectRightByUser",userId);
    }
    public List<ParaUserAuthority> findAll(){
        return super.selectList(getNameSpace(),"findAll");
    }
    //The nameSpace that is defined in the module file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pm.dao.paraSetting.ParaUserAuthorityDao";

    /**
     * This method corresponds to the database table PARA_USER_AUTHORITY
     */
    public ParaUserAuthority selectDataByDbPrimaryKey(ParaUserAuthority paraUserAuthority){
        return super.selectOne(DEFAULT_NAME_SPACE,"selectByPrimaryKey",paraUserAuthority);
    }
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}