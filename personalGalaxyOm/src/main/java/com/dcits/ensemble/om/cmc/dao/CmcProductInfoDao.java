package com.dcits.ensemble.om.cmc.dao;

import com.dcits.ensemble.om.cmc.dbModel.CmcProductInfo;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/8/26
 */
@Repository
public class CmcProductInfoDao extends BaseDao {

    private static final String NAMESPACE = "com.dcits.ensemble.om.cmc.dao.CmcProductInfoDao";
    @Override
    protected String getNameSpace() {
        return NAMESPACE;
    }

    /**
     * 查询记录
     * @param code
     * @return
     */
    public List<Map> getAll(String code){
        CmcProductInfo productInfo = new CmcProductInfo();
        productInfo.setCardProductCode(code);
        return super.selectList("selectByPrimaryKey",productInfo);
    }

}
