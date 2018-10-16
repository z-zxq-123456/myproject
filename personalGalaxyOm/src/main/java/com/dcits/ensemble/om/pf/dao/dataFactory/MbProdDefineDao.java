package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.ensemble.om.pf.module.PkList;
import com.dcits.ensemble.om.pf.module.dataFactory.MbEventPartRelation;
import com.dcits.ensemble.om.pf.module.dataFactory.MbProdDefine;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wujuan on 2015/10/21 10:16:06.
 */
@Repository
public class MbProdDefineDao extends BaseDao {

    //Mapper文件定义的NameSpace
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.MbProdDefineDao";

    public List<MbProdDefine> selectAll(){
        return super.selectList(getNameSpace(),"selectAll");
    }
    public List<MbProdDefine> selectByProdType(MbProdDefine mbProdDefine){
        return super.selectList(getNameSpace(),"selectByProdType",mbProdDefine);
    }
    public List<MbProdDefine> selectByProdTypeContrast(MbProdDefine mbProdDefine){
        return super.selectList(getNameSpace(),"selectByProdTypeContrast",mbProdDefine);
    }
    public List<MbProdDefine> selectByCondition(MbProdDefine mbProdDefine){
        return super.selectList(getNameSpace(),"selectByCondition",mbProdDefine);
    }
    public List<MbProdDefine> selectByConditionFourth(MbProdDefine mbProdDefine){
        return super.selectList(getNameSpace(),"selectByConditionFourth",mbProdDefine);
    }
    public void deleteByAssembleId(MbProdDefine mbProdDefine){
        super.delete(getNameSpace(), "deleteByAssembleId", mbProdDefine);
    }

    public void updateByAssembleId(MbProdDefine mbProdDefine){
        super.update(getNameSpace(), "updateByAssembleId", mbProdDefine);
    }

    public String getMaxSeqNo(String prodType){
        return super.selectOne(getNameSpace(), "getMaxSeqNo", prodType);
    }

    public List<MbEventPartRelation> getCprod() {
        return super.selectList(getNameSpace(),"getCprod");
    }
    public List<PkList> getEventByprod(String prodType) {
        MbProdDefine mbProdDefine = new MbProdDefine();
        mbProdDefine.setProdType(prodType);
        return super.selectList(getNameSpace(), "getEventByprod",mbProdDefine);
    }

    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}