package com.dcits.ensemble.om.cmc.dao;

import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/26
 */

@Repository(value="baseDbTableDao")
public class BaseDbTableDaoImpl implements BaseDbTableDao {

    @Resource
    private ShardSqlSessionTemplate shardSqlSessionTemplate;

    @Override
    public List<Map> selectByPrimaryKey(Map requestParams) {
        return shardSqlSessionTemplate.selectList(NAMESPACE,"selectByPrimaryKey",requestParams);
    }

    @Override
    public List<Map> getAllContrast(Map requestParams) {
        return shardSqlSessionTemplate.selectList(NAMESPACE,"getAllContrast",requestParams);
    }

    @Override
    public List<Map> getAllStatus(Map requestParams) {
        return shardSqlSessionTemplate.selectList(NAMESPACE,"getAllStatus",requestParams);
    }

    @Override
    public void delete(Map requestParams) {
        shardSqlSessionTemplate.delete(NAMESPACE,"delete",requestParams);
    }
}
