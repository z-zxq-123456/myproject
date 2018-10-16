package com.dcits.ensemble.om.cmc.dao;

import com.dcits.ensemble.om.cmc.dbModel.CmcProductType;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/8/25
 */
@Repository
public class CmcProductTypeDao extends BaseDao {
    private static final String NAMESPACE = "com.dcits.ensemble.om.cmc.dao.CmcProductTypeDao";

    @Override
    protected String getNameSpace() {
        return NAMESPACE;
    }

    public List<CmcProductType> getAll(Map request){
        return super.selectList("getAllAttrs",request);
    }

    public void updateReqNum(CmcProductType cmcProductType){
        super.update("updateByReqNum",cmcProductType);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<CmcProductType> selectByReqNum(CmcProductType cmcProductType){
        return super.selectList("selectByReqNum",cmcProductType);
    }
}
