package com.dcits.ensemble.om.pm.dao.paraSetting;
import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaTableFileds;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParaFieldsTableDao extends BaseDao {

    //The nameSpace that is defined in the module file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pm.dao.paraSetting.ParaFieldsTableDao";

    /**
     * This method corresponds to the database table PARA_FIELDS_TABLE
     */
    public List<String> getAllCulumnsByTableName(String tableName) {
        return super.selectList(getNameSpace(), "getAllCulumnsByTableName",tableName);
    }
    public List<String> getPKCulumnsByTableName(String tableName) {
        return super.selectList(getNameSpace(), "getPKCulumnsByTableName",tableName);
    }

    public List<String> getNonPkCulumnsByTableName(String tableName) {
        return super.selectList(getNameSpace(), "getNonPkCulumnsByTableName",tableName);
    }
    public List<String> getCulumnDescByTableName(String tableName) {
        return super.selectList(getNameSpace(), "getCulumnDescByTableName",tableName);
    }
    public List<String> selectTableFiled(String tableName) {
        return super.selectList(getNameSpace(), "selectTableFiled",tableName);
    }
    public List<ParaTableFileds> getTableList() {
        return super.selectList(getNameSpace(), "findList");
    }
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }

    public List<ParaTableFileds> getFieldDataByPKValue(ParaTableFileds paraTableFileds){
        return super.selectList(getNameSpace(), "getFieldsByPK", paraTableFileds);
    }
    public Integer selectPrimaryNum(ParaTableFileds table){
        return super.selectOne(getNameSpace(),"selectPrimaryNum",table);
    }
    public List<ParaTableFileds> getColumnList(ParaTableFileds table) {
        return super.selectList(getNameSpace(), "selectColumnForPklist", table);
    }

    public List<PkList> getTableByColumn(String columnId){
        return super.selectList(getNameSpace(),"getTableByColumn",columnId);
    }
    public List<PkList> getTableByTable(String tableName){
        return super.selectList(getNameSpace(),"getTableByTable",tableName);
    }

}