package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.ensemble.om.pf.module.dataFactory.MbEventPart;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2016/02/25 19:10:35.
 */
@Repository
public class MbEventPartDao extends BaseDao {

    public List<MbEventPart> getByPartType1(MbEventPart mbEventPart){
        return super.selectList(getNameSpace(),"getByPartType1",mbEventPart);
    }
    public List<MbEventPart> getByPartType1Contrast(MbEventPart mbEventPart){
        return super.selectList(getNameSpace(),"getByPartType1Contrast",mbEventPart);
    }

    //The nameSpace that is defined in the mapper file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.MbEventPartDao";

    /**
     * This method corresponds to the database table MB_EVENT_PART
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }

    public void deleteByPrimaryKey(MbEventPart mbEventPart){
        super.delete(getNameSpace(), "deleteByPrimaryKey", mbEventPart);
    }
    public List<MbEventPart> getByEventType(MbEventPart mbEventPart){
        return super.selectList(getNameSpace(),"getByEventType1",mbEventPart);
    }


}