package com.dcits.ensemble.om.pm.dao.paraSetting;

import com.dcits.ensemble.om.pm.module.paraSetting.ParaTransactionTableOrg;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2016/08/11 16:24:01.
 */
@Repository
public class ParaTransactionTableOrgDao extends BaseDao {

    //The nameSpace that is defined in the mapper file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pm.dao.paraSetting.ParaTransactionTableOrgDao";

    /**
     * This method corresponds to the database table PARA_TRANSACTION_TABLE_ORG
     */
    public List<ParaTransactionTableOrg> selectListByBusiness(String businessKey){
        return super.selectList(getNameSpace(), "selectByBusiness", businessKey);
    }
    public int deleteByBusiness(ParaTransactionTableOrg paraTransactionTableOrg){
        return super.delete(getNameSpace(), "deleteByBusiness", paraTransactionTableOrg);
    }
    public ParaTransactionTableOrg selectDataByDbPrimaryKey(ParaTransactionTableOrg paraTransactionTableOrg){
        return super.selectOne(DEFAULT_NAME_SPACE, "selectByPrimaryKey", paraTransactionTableOrg);
    }
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}