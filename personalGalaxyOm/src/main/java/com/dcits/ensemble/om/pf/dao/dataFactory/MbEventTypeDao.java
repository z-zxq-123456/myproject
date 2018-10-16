package com.dcits.ensemble.om.pf.dao.dataFactory;


import com.dcits.orion.core.dao.BaseDao;
import com.dcits.ensemble.om.pf.module.dataFactory.MbEventType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangyjw on 2015/09/25 15:32:27.
 */
@Repository
public class MbEventTypeDao extends BaseDao {

    //Mapper文件定义的NameSpace
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.MbEventTypeDao";
    public String  getForCheck(String eventType){
        return super.selectOne(getNameSpace(), "getForCheck", eventType);
    }
    public List<MbEventType> getByEventClass(String eventClass){
        return super.selectList(getNameSpace(), "getByEventClass", eventClass);
    }
    public List<MbEventType> getByEventType(MbEventType mbEventType){
        return super.selectList(getNameSpace(), "getByEventType", mbEventType);
    }
    public List<MbEventType> getIsStandardAllY() {
        return super.selectList(getNameSpace(),"getIsStandardAllY");
    }
    public String getEventDesc(String eventType){
        return  super.selectOne(getNameSpace(), "getEventDesc", eventType);
    }
    public List<MbEventType> getAll(){
        return super.selectList(getNameSpace(), "getAll");
    }
    public List<MbEventType> getAllContrast(){
        return super.selectList(getNameSpace(), "getAllContrast");
    }
    public List<MbEventType> getAllN(){
        String isStandard="N";
        return super.selectList(getNameSpace(),"getAllN",isStandard);
    }
    public List<MbEventType> getEventTypeByClass(MbEventType mbEventType){
        return super.selectList(getNameSpace(),"getEventTypeByClass",mbEventType);
    }
    public List<MbEventType> getByBmodule(String prodType) {
        MbEventType mbEventType =new MbEventType();
        String str = "%" + prodType +"%";
        mbEventType.setEventType(str);
        return super.selectList(getNameSpace(), "getByBmodule",mbEventType);
    }
    /**
     * This method corresponds to the database table MB_EVENT_TYPE
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}