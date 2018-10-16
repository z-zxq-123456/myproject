package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.ensemble.om.pf.module.dataFactory.MbProdRelationDefine;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2016/04/28 13:43:54.
 */
@Repository
public class MbProdRelationDefineDao extends BaseDao {

    //The nameSpace that is defined in the mapper file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.MbProdRelationDefineDao";

    public List<MbProdRelationDefine> selectByPrimaryKeyList(MbProdRelationDefine mbProdRelationDefine){
        return super.selectList(getNameSpace(),"selectByPrimaryKeyList",mbProdRelationDefine);
    }

    /**
     * This method corresponds to the database table MB_PROD_RELATION_DEFINE
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}