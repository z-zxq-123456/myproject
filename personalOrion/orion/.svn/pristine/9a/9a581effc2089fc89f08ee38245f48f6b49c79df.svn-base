/**
 * <p>Title: FlowDao<p>
 * <p>Description: Table stria_flow Dao operator<p>
 * <p>Copyright: Copyright (c) 2015<p>
 * <p>Description: <p>
 * <p>Company: dcits<p>
 *
 * @author galaxyTools
 * @version V1.0
 */

package com.dcits.orion.stria.dao;

import com.dcits.orion.stria.dao.mapper.Flow;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FlowDao {

    @Resource
    ShardSqlSessionTemplate shardSqlSessionTemplate;

    //Mapper文件定义的NameSpace
    private final String DEFAULT_NAME_SPACE = "com.dcits.orion.stria.dao.FlowDao";

    /**
     * This method corresponds to the database table stria_flow
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }

    public int insert(Flow flow) {
        return shardSqlSessionTemplate.insert(DEFAULT_NAME_SPACE, "insert", flow);
    }

    public int update(Flow flow) {
        return shardSqlSessionTemplate.update(DEFAULT_NAME_SPACE, "updateByPrimaryKey", flow);
    }

    public Flow select(Flow flow) {
        return (Flow) shardSqlSessionTemplate.selectOne(DEFAULT_NAME_SPACE, "selectByPrimaryKey", flow);
    }

    public List<Flow> selectList(String flowType) {
        Map<String, String> param = new HashMap<>();
        param.put("flowType", flowType);
        return shardSqlSessionTemplate.selectList(DEFAULT_NAME_SPACE, "select", param);
    }

    public int delete(Flow flow) {
        return shardSqlSessionTemplate.delete(DEFAULT_NAME_SPACE, "deleteByPrimaryKey", flow);
    }
}