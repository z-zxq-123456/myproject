package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.ensemble.om.pf.module.dataFactory.MbLinkCondition;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2016/05/05 15:18:13.
 */
@Repository
public class MbLinkConditionDao extends BaseDao {

    //The nameSpace that is defined in the mapper file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.MbLinkConditionDao";

    public List<MbLinkCondition> getAll(){
        return super.selectList(getNameSpace(),"getAll");
    }

    /**
     * This method corresponds to the database table MB_LINK_CONDITION
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}