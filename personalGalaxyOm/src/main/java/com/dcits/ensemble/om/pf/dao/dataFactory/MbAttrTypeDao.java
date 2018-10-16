package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.ensemble.om.pf.module.PkList;
import com.dcits.ensemble.om.pf.module.dataFactory.MbAttrClass;
import com.dcits.ensemble.om.pf.module.dataFactory.MbAttrType;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangyjw on 2015/09/23 14:19:08.
 */
@Repository
public class MbAttrTypeDao extends BaseDao {

    //Mapper文件定义的NameSpace
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.MbAttrTypeDao";
    public List<MbAttrType> getAll() {
        return super.selectList(getNameSpace(),"getAll");
    }

    public String getValueMethod(String attrKey){
        return super.selectOne(getNameSpace(), "getValueMethod", attrKey);
    }

    public String getUseMethod(String attrKey){
        return super.selectOne(getNameSpace(), "getUseMethod", attrKey);
    }


    public String getAttrDesc(String attrKey){
        return super.selectOne(getNameSpace(),"getAttrDesc",attrKey);
    }
    public List<MbAttrType> getAttrKey(MbAttrClass mbAttrClass){
        return super.selectList(getNameSpace(), "getAttrKey", mbAttrClass);
    }
    public List<MbAttrType> getAttrKey11(MbAttrType mbAttrType){
        return super.selectList(getNameSpace(), "getAttrKey11", mbAttrType);
    }

    public List<PkList> getattrClass(String Bmodule) {
        return super.selectList(getNameSpace(), "getattrClass",Bmodule);
    }
    public List<PkList> getAttrType() {
        return super.selectList(getNameSpace(), "getAttrType");
    }
    public List<PkList> getattrKey1() {
        return super.selectList(getNameSpace(), "getattrKey1");
    }
    /**
     * This method corresponds to the database table MB_ATTR_TYPE
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}