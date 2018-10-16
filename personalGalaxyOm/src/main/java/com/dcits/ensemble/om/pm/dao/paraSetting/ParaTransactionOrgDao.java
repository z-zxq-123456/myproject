package com.dcits.ensemble.om.pm.dao.paraSetting;

import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by maruie on 2016/05/12 18:44:58.
 */
@Repository
public class ParaTransactionOrgDao extends BaseDao {

    //The nameSpace that is defined in the module file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pm.dao.paraSetting.ParaTransactionOrgDao";
    /**
     * This method corresponds to the database table PARA_TRANSACTION_ORG
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}