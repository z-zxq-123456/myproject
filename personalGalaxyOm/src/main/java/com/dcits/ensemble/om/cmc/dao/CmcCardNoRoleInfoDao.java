package com.dcits.ensemble.om.cmc.dao;

import com.dcits.ensemble.om.cmc.dbModel.CmcCardNoRoleInfo;
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
public class CmcCardNoRoleInfoDao extends BaseDao {

    private static final String NAMESPACE = "com.dcits.ensemble.om.cmc.dao.CmcCardNoRoleInfoDao";
    @Override
    protected String getNameSpace() {
        return NAMESPACE;
    }

    /**
     * 查询记录
     * @param code
     * @return
     */
    public List<CmcCardNoRoleInfo> getAll(String code){
        if (code != null && !code .equals("")){
            CmcCardNoRoleInfo cardNoRoleInfo = new CmcCardNoRoleInfo();
            cardNoRoleInfo.setCardNoRoleCode(code);
            return super.selectList("selectByPrimaryKey",cardNoRoleInfo);
        }
        return null;
    }

}
