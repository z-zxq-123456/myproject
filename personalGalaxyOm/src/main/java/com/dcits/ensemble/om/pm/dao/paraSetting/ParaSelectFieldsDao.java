package com.dcits.ensemble.om.pm.dao.paraSetting;

import com.dcits.ensemble.om.pm.module.paraSetting.ParaSelectFields;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ParaSelectFieldsDao extends BaseDao {

    //The nameSpace that is defined in the module file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pm.dao.paraSetting.ParaSelectFieldsDao";

    /**
     * This method corresponds to the database table PARA_SELECT_FIELDS
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
    public List<ParaSelectFields> getSelectList() {
        return super.selectList(getNameSpace(), "findList");
    }
    public ParaSelectFields getSelectByTable(String tableName,String columnName){
        Map<String,Object> map = new HashMap<>();
        map.put("tableName", tableName);
        map.put("columnName",columnName);
        return super.selectOne(getNameSpace(),"getSelectByTable",map);
    }
}