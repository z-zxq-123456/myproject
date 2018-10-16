package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.ensemble.om.pf.module.dataFactory.IrlProdTypePf;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2016/05/10 09:49:37.
 */
@Repository
public class IrlProdTypePfDao extends BaseDao {

    //The nameSpace that is defined in the mapper file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.IrlProdTypePfDao";

    public List<IrlProdTypePf> getAll(String prodType) {

        return super.selectList(getNameSpace(),"getAll",prodType);
    }

    public void insertNewProdType(IrlProdTypePf irlProdTypePf){
        super.insert(getNameSpace(), "insertNewProdType", irlProdTypePf);
    }

    public List<IrlProdTypePf> selectBase(IrlProdTypePf irlProdTypePf) {

        return super.selectList(getNameSpace(),"selectBase", irlProdTypePf);
    }
    /**
     * This method corresponds to the database table IRL_PROD_TYPE
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}