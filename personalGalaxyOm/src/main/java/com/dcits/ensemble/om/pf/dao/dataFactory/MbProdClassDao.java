package com.dcits.ensemble.om.pf.dao.dataFactory;


import com.dcits.ensemble.om.pf.module.PkList;
import com.dcits.ensemble.om.pf.module.dataFactory.MbProdClass;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangyjw on 2015/09/25 10:30:51.
 */
@Repository
public class MbProdClassDao extends BaseDao {

    //Mapper文件定义的NameSpace
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.MbProdClassDao";
    public List<MbProdClass> getByLevel(MbProdClass mbProdClass){
        return super.selectList(getNameSpace(),"getByLevel",mbProdClass);
    }
    public List<MbProdClass> getByLevell( String prodClassLevel){
        return super.selectList(getNameSpace(),"getByLevell",prodClassLevel);
    }
    public List<MbProdClass> getByParentProdClass(MbProdClass mbProdClass){
        return super.selectList(getNameSpace(), "getByParentProdClass", mbProdClass);
    }
    public List<MbProdClass> getByProdClassLevel( MbProdClass info){
        return super.selectList(getNameSpace(),"getByProdClassLevel",info);
    }
    public List<MbProdClass> getAll(){
        return super.selectList(getNameSpace(),"getAll");
    }
    public List<PkList> getBmodule() {
        return super.selectList(getNameSpace(), "getBmodule");
    }
    public List<PkList> getprodClass(String Bmodule) {
        MbProdClass mbProdClass = new MbProdClass();
        mbProdClass.setParentProdClass(Bmodule);
        return super.selectList(getNameSpace(), "getprodClass",mbProdClass);
    }
    public List<PkList> getparentProdClass(String prodClassLevel) {
        int classLevel = Integer.parseInt(prodClassLevel) - 1;
        MbProdClass mbProdClass = new MbProdClass();
        mbProdClass.setProdClassLevel(classLevel + "");
        return super.selectList(getNameSpace(), "getparentProdClass",mbProdClass);
    }
    public List<MbProdClass> getByBmodule(String Bmodule) {
        MbProdClass mbProdClass = new MbProdClass();
        mbProdClass.setProdClass(Bmodule);
        return super.selectList(getNameSpace(), "getByBmodule",mbProdClass);
    }
    /**
     * This method corresponds to the database table MB_PROD_CLASS
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}