package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.ensemble.om.pf.module.dataFactory.GlProdMappingPf;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2016/05/27 19:02:42.
 */
@Repository
public class GlProdMappingPfDao extends BaseDao {

    //The nameSpace that is defined in the mapper file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.GlProdMappingPfDao";
    public List<GlProdMappingPf> getAll(String prodType) {
        return super.selectList(getNameSpace(),"getAll",prodType);
    }

    public void insertNewProdType(GlProdMappingPf glProdMappingPf){
        super.insert(getNameSpace(), "insertNewProdType", glProdMappingPf);
    }
    /**
     * This method corresponds to the database table GL_PROD_MAPPING
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}