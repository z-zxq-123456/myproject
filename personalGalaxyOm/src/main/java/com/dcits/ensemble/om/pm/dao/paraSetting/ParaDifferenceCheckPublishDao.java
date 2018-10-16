package com.dcits.ensemble.om.pm.dao.paraSetting;

import com.dcits.ensemble.om.pm.module.paraSetting.ParaDifferenceCheckPublish;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParaDifferenceCheckPublishDao extends BaseDao {

    public List<ParaDifferenceCheckPublish> selectAllDataByTbReq(ParaDifferenceCheckPublish paraDifferenceCheckPublish){
        return super.selectList(getNameSpace(), "getAllDataByTbReqKv", paraDifferenceCheckPublish);
    }

    public ParaDifferenceCheckPublish selectDifferenceByPk(ParaDifferenceCheckPublish paraDifferenceCheckPublish){
        return super.selectOne(getNameSpace(), "selectByPrimaryKey", paraDifferenceCheckPublish);
    }

    public ParaDifferenceCheckPublish selectDifferenceByTbReq(ParaDifferenceCheckPublish paraDifferenceCheckPublish){
        return super.selectOne(getNameSpace(), "getDifferenceByTbReqKv", paraDifferenceCheckPublish);
    }

    public void deleteByTableReqNo(String reqNo,String tbName){
        ParaDifferenceCheckPublish paraDifferenceCheckPublish = new ParaDifferenceCheckPublish();
        paraDifferenceCheckPublish.setTableFullName(tbName);
        paraDifferenceCheckPublish.setReqNo(reqNo);
        super.delete(getNameSpace(), "deleteByTableReqNo", paraDifferenceCheckPublish);
    }

    public void removeDataByTbReq(ParaDifferenceCheckPublish paraDifferenceCheckPublish){
        super.delete(getNameSpace(), "deleteByTbReqKv", paraDifferenceCheckPublish);
    }

    //The nameSpace that is defined in the module file
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pm.dao.paraSetting.ParaDifferenceCheckPublishDao";

    /**
     * This method corresponds to the database table PARA_DIFFERENCE_CHECK_PUBLISH
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}