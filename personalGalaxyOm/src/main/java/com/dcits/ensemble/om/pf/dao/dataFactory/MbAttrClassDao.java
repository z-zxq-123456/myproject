package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.ensemble.om.pf.module.PkList;
import com.dcits.ensemble.om.pf.module.dataFactory.MbAttrClass;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangyjw on 2015/09/22 11:33:48.
 */
@Repository
public class MbAttrClassDao extends BaseDao {

    //Mapper文件定义的NameSpace
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.MbAttrClassDao";
    public List<MbAttrClass> getAll() {
        return super.selectList(getNameSpace(),"getAll");
    }

    public List<MbAttrClass> getByBmodule(String attrClass) {
        MbAttrClass mbAttrClass = new MbAttrClass();
        mbAttrClass.setAttrClass(attrClass);
        return super.selectList(getNameSpace(),"getByBmodule",mbAttrClass);
    }

    public List<MbAttrClass> getByAttrClass(MbAttrClass mbAttrClass) {
        return super.selectList(getNameSpace(),"getByAttrClass",mbAttrClass);
    }
    public List<MbAttrClass> getList(MbAttrClass mbAttrClass) {
        return super.selectList(getNameSpace(),"getList",mbAttrClass);
    }
    public List<PkList> getForPkList(){
        return super.selectList(getNameSpace(), "getForPkList");
    }

    public List<PkList> getBmodule() {
        String attrClassLevel="1";
        return super.selectList(getNameSpace(), "getBmodule",attrClassLevel);
    }

    public List<PkList> getparentAttrClass(String attrClassLevel) {
        int classLevel = Integer.parseInt(attrClassLevel) - 1;
        MbAttrClass mbAttrClass = new MbAttrClass();
        mbAttrClass.setAttrClassLevel(classLevel + "");
        return super.selectList(getNameSpace(), "getparentAttrClass",mbAttrClass);
    }
    /**
     * This method corresponds to the database table MB_ATTR_CLASS
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}