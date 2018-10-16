package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.ensemble.om.pf.module.PkList;
import com.dcits.ensemble.om.pf.module.dataFactory.MbPartClass;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangyjw on 2015/09/24 14:36:28.
 */
@Repository
public class MbPartClassDao extends BaseDao {

    //Mapper文件定义的NameSpace
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.MbPartClassDao";
    public List<MbPartClass> getAll(){
        return super.selectList(getNameSpace(),"getAll");
    }
    public List<PkList> getForPkList(){
        return super.selectList(getNameSpace(),"getForPkList");
    }
    public List<MbPartClass> getByPartClass(MbPartClass mbPartClass){
        return super.selectList(getNameSpace(),"getByPartClass",mbPartClass);
    }


    public List<PkList> getBmodule() {
        String partClassLevel="1";
        return super.selectList(getNameSpace(), "getBmodule",partClassLevel);
    }

    public List<PkList> getpartClass(String Bmodule) {
        MbPartClass mbPartClass = new MbPartClass();
        mbPartClass.setParentPartClass(Bmodule);
        return super.selectList(getNameSpace(), "getpartClass",mbPartClass);
    }

    public List<MbPartClass> getByBmodule(String Bmodule) {
        MbPartClass mbPartClass = new MbPartClass();
        mbPartClass.setPartClass(Bmodule);
        return super.selectList(getNameSpace(), "getByBmodule",mbPartClass);
    }

    public List<PkList> getparentPartClass(String partClassLevel) {
        int classLevel = Integer.parseInt(partClassLevel) - 1;
        MbPartClass mbPartClass = new MbPartClass();
        mbPartClass.setPartClassLevel(classLevel + "");
        return super.selectList(getNameSpace(), "getparentPartClass",mbPartClass);
    }
    /**
     * This method corresponds to the database table MB_PART_CLASS
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}