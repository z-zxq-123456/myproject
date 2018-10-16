package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.ensemble.om.pf.module.dataFactory.MbProdRunRule;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2016/03/07 19:04:51.
 */
@Repository
public class MbProdRunRuleDao extends BaseDao {

    //The nameSpace that is defined in the mapper file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.MbProdRunRuleDao";

    public List<MbProdRunRule> getByEventType(MbProdRunRule mbProdRunRule){
        return super.selectList(getNameSpace(), "getByEventType", mbProdRunRule);
    }

    /**
     * This method corresponds to the database table MB_PROD_RUN_RULE
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}