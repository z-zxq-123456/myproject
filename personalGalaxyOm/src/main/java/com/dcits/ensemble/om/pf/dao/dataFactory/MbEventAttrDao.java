package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.orion.core.dao.BaseDao;
import com.dcits.ensemble.om.pf.module.dataFactory.MbEventAttr;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wujuan on 2015/10/13 11:01:00.
 */
@Repository
public class MbEventAttrDao extends BaseDao {

    //Mapper文件定义的NameSpace
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.MbEventAttrDao";

    /**
     * This method corresponds to the database table MB_EVENT_ATTR
     */
    public List<MbEventAttr>selectByCondition(MbEventAttr mbEventAttr){
        return super.selectList(getNameSpace(),"selectByCondition",mbEventAttr);
    }
    public List<MbEventAttr> selectByEventType(MbEventAttr mbEventAttr){
        return super.selectList(getNameSpace(),"selectByEventType",mbEventAttr);

    }
    public List<MbEventAttr> getByEventType1(String eventType){
        return super.selectList(getNameSpace(),"getByEventType1",eventType);
    }
    public List<MbEventAttr> getByEventType1Contrast(String eventType){
        return super.selectList(getNameSpace(),"getByEventType1Contrast",eventType);
    }

    public List<MbEventAttr> getByEventType(MbEventAttr mbEventAttr){
        return super.selectList(getNameSpace(),"getByEventType",mbEventAttr);

    }
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }

    public void deleteByAssembleId(MbEventAttr mbEventAttr){
         super.delete(getNameSpace(), "deleteByAssembleId", mbEventAttr);
    }

    public void updateByAssembleId(MbEventAttr mbEventAttr){
         super.update(getNameSpace(), "updateByAssembleId", mbEventAttr);
    }

    public void updateByAssemble(MbEventAttr mbEventAttr){
        super.update(getNameSpace(), "updateByAssemble", mbEventAttr);
    }

    public String getMaxSeqNo(String eventType){
         return super.selectOne(getNameSpace(), "getMaxSeqNo", eventType);
    }
}