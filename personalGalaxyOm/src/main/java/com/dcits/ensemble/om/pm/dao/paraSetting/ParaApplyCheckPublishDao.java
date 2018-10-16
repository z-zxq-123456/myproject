package com.dcits.ensemble.om.pm.dao.paraSetting;

import com.dcits.ensemble.om.pf.bean.DbProdCheckList;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaApplyCheckPublish;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ParaApplyCheckPublishDao extends BaseDao {
    public List<ParaApplyCheckPublish> getNeedCheckInfoList(String reqNo){
        return super.selectList(getNameSpace(), "needCheckOrPublishInfoList", reqNo);
    }
    public List<DbProdCheckList> applyCheckProdList(){
        return super.selectList(getNameSpace(),"applyCheckProdList");
    }
    public Map<String,String > getOperator(String reqNo){
        return super.selectOne(getNameSpace(),"getOperator",reqNo);
    }
    public Map<String,String > getOperatorPublish(String reqNo){
        return super.selectOne(getNameSpace(),"getOperatorPublish",reqNo);
    }
    public List<DbProdCheckList> byProdTypeList(Map map){
        return super.selectList(getNameSpace(),"byProdTypeList",map);
    }
    public String checkIfAnotherPublisher(ParaApplyCheckPublish paraApplyCheckPublish){
        return super.selectOne(getNameSpace(), "checkIfAnotherPublisher", paraApplyCheckPublish);
    }

    //The nameSpace that is defined in the module file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pm.dao.paraSetting.ParaApplyCheckPublishDao";

    /**
     * This method corresponds to the database table PARA_APPLY_CHECK_PUBLISH
     */
    public  List<Map<String,Object>> findList(){
        return super.selectList(getNameSpace(),"getList");
    }
    public List<Map<String,Object>> findByDate(String startDate,String endDate){
        Map<String,String> map =new HashMap<>();
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        return    super.selectList(getNameSpace(),"findByDate",map);
    }
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}