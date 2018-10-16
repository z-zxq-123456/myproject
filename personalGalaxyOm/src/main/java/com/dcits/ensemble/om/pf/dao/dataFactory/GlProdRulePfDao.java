package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.ensemble.om.pf.module.dataFactory.GlProdRulePf;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2016/05/27 19:02:42.
 */
@Repository
public class GlProdRulePfDao extends BaseDao {

    //The nameSpace that is defined in the mapper file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.GlProdRulePfDao";
    public List<GlProdRulePf> getAll(String prodType) {
        return super.selectList(getNameSpace(),"getAll",prodType);
    }

    public void insertNewProdType(GlProdRulePf glProdRulePf){
        super.insert(getNameSpace(), "insertNewProdType", glProdRulePf);
    }
    /**
     * This method corresponds to the database table GL_PROD_RULE
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}