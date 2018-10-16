package com.dcits.ensemble.om.cmc.dao;

import com.alibaba.fastjson.JSON;
import com.dcits.ensemble.om.cmc.dbModel.CmcRuleNo;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @program: ensemble-om
 * @Date: 2018/9/20 9:09
 * @Author: Mr.Zhang
 * @Description:
 */
@Repository
public class CmcRuleNoDao extends BaseDao {
    private static final String NAMESPACE = "com.dcits.ensemble.om.cmc.dao.CmcRuleNoDao";
    @Override
    protected String getNameSpace() {
        return NAMESPACE;
    }

    /**
     * 查询记录
     * @param request
     * @return CmcRuleNo
     */
    public CmcRuleNo getByPrimaryKey(Map request){
        CmcRuleNo cmcRuleNo = JSON.parseObject(
                JSON.toJSONString(request),
                CmcRuleNo.class);
        return super.selectOne("selectByPrimaryKey",cmcRuleNo);
    }

    /**
     * 查询所有记录
     * @param
     * @return List<CmcRuleNo>
     */
    public List<CmcRuleNo> getAll(){
        return super.selectList("selectAll");
    }


}
