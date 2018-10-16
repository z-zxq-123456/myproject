package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2016/04/23 11:25:55.
 */
@Repository
public class MbEventPartRelationDao extends BaseDao {

    //The nameSpace that is defined in the mapper file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.MbEventPartRelationDao";

    /**
     * This method corresponds to the database table MB_EVENT_PART_RELATION
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}