package com.dcits.ensemble.om.cmc.dao;

import com.dcits.ensemble.om.cmc.dbModel.CmcCardProductChannel;
import com.dcits.ensemble.om.cmc.dbModel.CmcProductInfo;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/8/26
 */
@Repository
public class CmcCardProductChannelDao extends BaseDao {

    private static final String NAMESPACE = "com.dcits.ensemble.om.cmc.dao.CmcCardProductChannelDao";
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
        CmcCardProductChannel productChannel = new CmcCardProductChannel();
        productChannel.setCardProductCode(code);
        return super.selectList("selectByPrimaryKey",productChannel);
    }

}
