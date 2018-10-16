package com.dcits.ensemble.om.pf.dao.tools;

import com.dcits.ensemble.om.pf.module.PkList;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ligan-1 on 2016/1/12.
 */
@Repository
public class ParamTableSelectDao extends BaseDao {
    //Mapper文件定义的NameSpace
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.tools.ParamTableSelectDao";

    /**
     * This method corresponds to the database table ecm_mbsd_details
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
   public List<Map<String,String>> selectByTableName(String tableName){
        Map<String,String> map = new HashMap<>();
        map.put("tableName", tableName);
      return   super.selectList(DEFAULT_NAME_SPACE,"selectByTableName",map);
    }
    public List<PkList> selectParameterPklist(Map map) {
        return super.selectList(getNameSpace(), "selectParameterPklist", map);
    }
	    public List<Map<String,String>> tableData(String tableName ,String tableCol,String tableColDes)
    {
        Map<String,String> map=new HashMap();
       map.put("tableColDes",tableColDes);
        map.put("tableCol",tableCol);
        map.put("tableName",tableName);
        return super.selectList(getNameSpace(), "tableData", map);
    }
    public List<Map<String,String>> selectTableAndCol(String tableName,String col1 ,String colV1,String col2,String colV2,String col3,String colV3){
        Map<String,String> map = new HashMap<>();
        map.put("tableName", tableName);
        if(col1==null||"".equals(col1))
        {
            map.put("col1",null);
        }else {
            map.put("col1", col1);
        }
       if(col2==null||"".equals(col2)){
        map.put("col2",null);}
        else {map.put("col2",col2);}
        if(col3==null||"".equals(col3))
        {map.put("col3",null);}
        else {map.put("col3",col3);}

        if(colV1==null||"".equals(colV1.trim())){
            map.put("colV1",null);
        }else{
            map.put("colV1",colV1.trim());
        }
        if(colV2==null||"".equals(colV2.trim())){
            map.put("colV2",null);
        }else{
            map.put("colV2",colV2.trim());
        }
        if(colV3==null||"".equals(colV3.trim())){
            map.put("colV3",null);
        }else{
            map.put("colV3",colV3.trim());
        }

        return   super.selectList(DEFAULT_NAME_SPACE,"selectTableAndCol",map);
    }

}
