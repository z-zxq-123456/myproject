package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.ensemble.om.pf.module.dataFactory.MbProdGroup;
import com.dcits.orion.core.dao.BaseDao;
import com.dcits.ensemble.om.pf.module.PkList;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2016/04/28 13:43:54.
 */
@Repository
public class MbProdGroupDao extends BaseDao {

    //The nameSpace that is defined in the mapper file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.MbProdGroupDao";

    public String getMaxSeqNo(String prodType){
        return super.selectOne(getNameSpace(), "getMaxSeqNo", prodType);
    }
    public List<PkList> getSubProdByProdType(String prodType) {
        MbProdGroup mbProdGroup= new MbProdGroup();
        mbProdGroup.setProdType(prodType);
        return super.selectList(getNameSpace(), "getSubProdByProdType",mbProdGroup);
    }
    public List<MbProdGroup> selectByPrimaryKeyList(MbProdGroup mbProdGroup){
        return super.selectList(getNameSpace(),"selectByPrimaryKeyList",mbProdGroup);
    }
    /**
     * This method corresponds to the database table MB_PROD_GROUP
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}