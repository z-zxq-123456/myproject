package com.dcits.ensemble.om.cmc.dao;

import com.dcits.ensemble.om.cmc.dbModel.CmcCardNoRoleEx;
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
public class CmcCardNoRoleExDao extends BaseDao {

    private static final String NAMESPACE = "com.dcits.ensemble.om.cmc.dao.CmcCardNoRoleExDao";
    @Override
    protected String getNameSpace() {
        return NAMESPACE;
    }

    /**
     * 查询记录
     * @param name
     * @return
     */
    public List<Map> getAll(String name){
        CmcCardNoRoleEx cmcCardNoRoleEx = new CmcCardNoRoleEx();
        cmcCardNoRoleEx.setParamName(name);
        return super.selectList("selectByPrimaryKey",cmcCardNoRoleEx);
    }

}
