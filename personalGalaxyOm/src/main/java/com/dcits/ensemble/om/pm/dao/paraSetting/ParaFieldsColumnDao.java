package com.dcits.ensemble.om.pm.dao.paraSetting;

import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaFieldsColumn;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParaFieldsColumnDao extends BaseDao {

    //The nameSpace that is defined in the module file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pm.dao.paraSetting.ParaFieldsColumnDao";

    /**
     * This method corresponds to the database table PARA_FIELDS_COLUMN
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
    public List<ParaFieldsColumn> getColumnList() {
        return super.selectList(getNameSpace(), "findList");
    }
    public List<PkList> getColumnNamePklist() {
        return super.selectList(getNameSpace(), "selectBaseForPklist");
    }
    public ParaFieldsColumn getTbColumnsByColumnName(String paraFieldsColumn){
        return super.selectOne(getNameSpace(), "selectByPkName",paraFieldsColumn);
    }
}