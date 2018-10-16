package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.ensemble.om.pf.module.dataFactory.GlProdAccountingPf;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2016/05/27 19:02:42.
 */
@Repository
public class GlProdAccountingPfDao extends BaseDao {

    //The nameSpace that is defined in the mapper file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.GlProdAccountingPfDao";
    public List<GlProdAccountingPf> getAll(String prodType) {
        return super.selectList(getNameSpace(),"getAll",prodType);
    }

    public void insertNewProdType(GlProdAccountingPf glProdAccountingPf){
        super.insert(getNameSpace(), "insertNewProdType", glProdAccountingPf);
    }
    /**
     * This method corresponds to the database table GL_PROD_ACCOUNTING
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}