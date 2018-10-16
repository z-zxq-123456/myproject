package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.ensemble.om.pf.module.dataFactory.IrlFeeMappingPf;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2016/05/26 14:04:10.
 */
@Repository
public class IrlFeeMappingPfDao extends BaseDao {

    //The nameSpace that is defined in the mapper file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.IrlFeeMappingPfDao";

    public List<IrlFeeMappingPf> getAll(String prodType) {

        return super.selectList(getNameSpace(),"getAll",prodType);
    }
    public String getMax(){
        return super.selectOne(getNameSpace(),"getMax");
    }
    public void insertNewProdType(IrlFeeMappingPf irlFeeMappingPf){
        super.insert(getNameSpace(), "insertNewProdType", irlFeeMappingPf);
    }

    /**
     * This method corresponds to the database table IRL_FEE_MAPPING
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}