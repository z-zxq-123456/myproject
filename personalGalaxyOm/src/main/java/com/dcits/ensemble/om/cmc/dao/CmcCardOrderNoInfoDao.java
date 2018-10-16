package com.dcits.ensemble.om.cmc.dao;

import com.dcits.ensemble.om.cmc.dbModel.CmcCardOrderNoInfo;
import com.dcits.ensemble.om.cmc.dbModel.CmcProductInfo;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/8/26
 */
@Repository
public class CmcCardOrderNoInfoDao extends BaseDao {

    private static final String NAMESPACE = "com.dcits.ensemble.om.cmc.dao.CmcCardOrderNoInfoDao";
    @Override
    protected String getNameSpace() {
        return NAMESPACE;
    }

    /**
     * 查询记录
     * @param code
     * @return
     */
    public List<CmcCardOrderNoInfo> getAll(String code){
        CmcCardOrderNoInfo cardOrderNoInfo = new CmcCardOrderNoInfo();
        cardOrderNoInfo.setProductRuleNo(code);
        return super.selectList("selectByPrimaryKey",cardOrderNoInfo);
    }

}
