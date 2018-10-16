package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.ensemble.om.pf.module.dataFactory.MbEventLink;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2016/04/28 13:43:54.
 */
@Repository
public class MbEventLinkDao extends BaseDao {

    //The nameSpace that is defined in the mapper file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.MbEventLinkDao";

    public List<MbEventLink> selectByPrimaryKeyList(MbEventLink mbEventLink){
        return super.selectList(getNameSpace(),"selectByPrimaryKeyList",mbEventLink);
    }
    public List<MbEventLink> getByLinkCondition(String conditionId) {
         MbEventLink mbEventLink = new MbEventLink();
        mbEventLink.setLinkCondition(conditionId);
        return super.selectList(getNameSpace(),"getByLinkCondition",mbEventLink);
    }

    /**
     * This method corresponds to the database table MB_EVENT_LINK
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}