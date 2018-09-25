package com.dcits.orion.core.reversal;

import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.plugins.RowArgs;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by lixbb on 2016/2/16.
 */
@Repository
public class AutoReversalDao {
    @Resource
    private ShardSqlSessionTemplate shardSqlSessionTemplate;

    private static final String NAMESPACE = "com.dcits.orion.core.dao.AutoReversalDao";

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertReversal(Map map) {
        shardSqlSessionTemplate.insert(NAMESPACE, "insertReversal", map);
    }

    public void updateReversal(Map map) {
        shardSqlSessionTemplate.update(NAMESPACE, "updateReversal", map);
    }
    public List getReversals(int limit)
    {
        RowArgs rowArgs = new RowArgs(0, limit);//每次只处理dealCount条
        ParameterWrapper paraWrapper = new ParameterWrapper();
        paraWrapper.setRowArgs(rowArgs);
       return shardSqlSessionTemplate.selectList(NAMESPACE,"getReversals",paraWrapper);
    }

}
