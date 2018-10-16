package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.ensemble.om.pf.module.dataFactory.IrlProdIntPf;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2016/05/10 09:49:37.
 */
@Repository
public class IrlProdIntPfDao extends BaseDao {

    //The nameSpace that is defined in the mapper file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.IrlProdIntPfDao";

    public List<IrlProdIntPf> getAll(String prodType) {

        return super.selectList(getNameSpace(),"getAll",prodType);
    }
    public List<IrlProdIntPf> selectBase(IrlProdIntPf irlProdIntPf) {

        return super.selectList(getNameSpace(),"selectBase", irlProdIntPf);
    }
    public void insertNewProdType(IrlProdIntPf irlProdIntPf){
        super.insert(getNameSpace(), "insertNewProdType", irlProdIntPf);
    }
    /**
     * This method corresponds to the database table IRL_PROD_INT
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}