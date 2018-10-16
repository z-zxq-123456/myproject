package com.dcits.ensemble.om.cmc.dao;

import com.dcits.ensemble.om.cmc.dbModel.CmcCardNoParam;
import com.dcits.ensemble.om.cmc.dbModel.CmcProductInfo;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/8/26
 */
@Repository
public class CmcCardNoParamDao extends BaseDao {

    private static final String NAMESPACE = "com.dcits.ensemble.om.cmc.dao.CmcCardNoParamDao";
    @Override
    protected String getNameSpace() {
        return NAMESPACE;
    }

    /**
     * 查询记录
     * @param ruleNo
     * @return
     */
    public List<CmcCardNoParam> getAll(String ruleNo){
        if (ruleNo!=null && !ruleNo.equals("")){
            CmcCardNoParam cardNoParam = new CmcCardNoParam();
            cardNoParam.setProductRuleNo(ruleNo);
            return super.selectList("selectByPrimaryKey",cardNoParam);
        }
      return null;
    }

}
