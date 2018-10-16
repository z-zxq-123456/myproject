package com.dcits.ensemble.om.pm.dao.paraSetting;

import com.dcits.ensemble.om.pm.module.paraSetting.ParaCircleFlow;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ParaCircleFlowDao extends BaseDao {

    //The nameSpace that is defined in the module file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pm.dao.paraSetting.ParaCircleFlowDao";

    public ParaCircleFlow getTableFinishStatue(String tableName) {
        return super.selectOne(getNameSpace(), "getTableFinishStatue", tableName);
    }

    public List<ParaCircleFlow> getNeedInfoList(String currentStatus){
        return  super.selectList(getNameSpace(), "getNeedInfoList", currentStatus);
    }

    public ParaCircleFlow getDataByPrimaryKey(String reqNo) {
        return super.selectOne(getNameSpace(), "getDataByPrimaryKey", reqNo);
    }
    public String selectTransctionByReqNo(String reqNo) {
        return super.selectOne(getNameSpace(), "selectTransctionByReqNo", reqNo);
    }

    /**
     * This method corresponds to the database table PARA_CIRCLE_FLOW
     */
    public String getApplicationId(String transactionId){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("transactionId",transactionId);
        return super.selectOne(getNameSpace(),"getApplicationId",map);
    }
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}