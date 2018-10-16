package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.ensemble.om.pf.module.PkList;
import com.dcits.ensemble.om.pf.module.dataFactory.MbProdType;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangyjw on 2015/09/28 10:32:46.
 */
@Repository
public class MbProdTypeDao extends BaseDao {

    //Mapper文件定义的NameSpace
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.MbProdTypeDao";
    public List<MbProdType> selectByProdClass(MbProdType mbProdType){
        return super.selectList(getNameSpace(),"selectByProdClass",mbProdType);
    }
    public List<MbProdType> getByProdClass(String prodClass){
        return super.selectList(getNameSpace(), "getByProdClass", prodClass);
    }
    public List<MbProdType> selectByProdClass1(MbProdType mbProdType){
        return super.selectList(getNameSpace(),"selectByProdClass",mbProdType);
    }
    public List<MbProdType> selectByProdClass2(MbProdType mbProdType){
        return super.selectList(getNameSpace(),"selectByProdClass2",mbProdType);
    }
    public List<MbProdType> selectByProdType(MbProdType mbProdType){
        return super.selectList(getNameSpace(),"selectByPrimaryKey",mbProdType);
    }
    public List<MbProdType> getAll(String prodGroup){
        return super.selectList(getNameSpace(),"getAll",prodGroup);
    }
    public List<MbProdType> getAllContrast(String prodGroup){
        return super.selectList(getNameSpace(),"getAllContrast",prodGroup);
    }

    public List<MbProdType> getComAll(String prodGroup){
        return super.selectList(getNameSpace(),"getAll",prodGroup);
    }

    public List<PkList> getbaseProd(String Bmodule) {
        MbProdType mbProdType = new MbProdType();
        String str = "%" + Bmodule +"%";
        mbProdType.setProdClass(str);
        mbProdType.setProdRange("B");
        return super.selectList(getNameSpace(), "getbaseProd",mbProdType);
    }
    public List<PkList> getChildProd(String Bmodule) {
        MbProdType mbProdType = new MbProdType();
        String str = "%" + Bmodule +"%";
        mbProdType.setProdType(str);
        return super.selectList(getNameSpace(), "getChildProd",mbProdType);
    }
    public String getProdDesc(String prodType){
        return super.selectOne(getNameSpace(), "getProdDesc", prodType);
    }

    /**
     * This method corresponds to the database table MB_PROD_TYPE
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}