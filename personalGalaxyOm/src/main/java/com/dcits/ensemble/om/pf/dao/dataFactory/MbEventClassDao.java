package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.ensemble.om.pf.module.PkList;
import com.dcits.ensemble.om.pf.module.dataFactory.MbEventClass;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangyjw on 2015/09/24 10:04:26.
 */
@Repository
public class MbEventClassDao extends BaseDao {

    //Mapper文件定义的NameSpace
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.MbEventClassDao";
    public List<MbEventClass> getAll(){
        return super.selectList(getNameSpace(),"getAll");
    }
    public List<MbEventClass> getTree(){
        return super.selectList(getNameSpace(),"getTree");
    }
    public List<MbEventClass> getByParentEventClass(MbEventClass mbEventClass){
        return super.selectList(getNameSpace(), "getByParentEventClass", mbEventClass);
    }
    public List<PkList> getForPkList(){
        return super.selectList(getNameSpace(),"getForPkList");
    }
    public List<PkList> getBmodule() {
        return super.selectList(getNameSpace(), "getBmodule");
    }
    public List<PkList> geteventClass(String Bmodule) {
        String str = "%" + Bmodule +"%";
        MbEventClass mbEventClass = new MbEventClass();
        mbEventClass.setParentEventClass(str);
        return super.selectList(getNameSpace(), "geteventClass",mbEventClass);
    }
    public List<PkList> getparentEventClass(String eventClassLevel) {
        int classLevel = Integer.parseInt(eventClassLevel) - 1;
        MbEventClass mbEventClass = new MbEventClass();
        mbEventClass.setEventClassLevel(classLevel + "");
        return super.selectList(getNameSpace(), "getparentEventClass",mbEventClass);
    }

    public List<MbEventClass> getTreeByBmodule(String Bmodule) {
        MbEventClass mbEventClass = new MbEventClass();
        String str = "%" + Bmodule +"%";
        mbEventClass.setParentEventClass(str);
        return super.selectList(getNameSpace(),"getTreeByBmodule",mbEventClass);
    }
    /**
     * This method corresponds to the database table MB_EVENT_CLASS
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}