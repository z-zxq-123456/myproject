/**
 * <p>Title: NodeDao<p>
 * <p>Description: Table stria_flow_nodes Dao operator<p>
 * <p>Copyright: Copyright (c) 2015<p>
 * <p>Description: <p>
 * <p>Company: dcits<p>
 *
 * @author galaxyTools
 * @version V1.0
 */

package com.dcits.orion.stria.dao;

import com.dcits.orion.stria.dao.mapper.Node;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.util.List;

@Repository
public class NodeDao {

    @Resource
    ShardSqlSessionTemplate shardSqlSessionTemplate;

    //Mapper文件定义的NameSpace
    private final String DEFAULT_NAME_SPACE = "com.dcits.orion.stria.dao.NodeDao";

    public int insert(Node node) {
        return shardSqlSessionTemplate.insert(DEFAULT_NAME_SPACE, "insert", node);
    }

    public int update(Node node) {
        return shardSqlSessionTemplate.update(DEFAULT_NAME_SPACE, "updateByPrimaryKey", node);
    }

    public Node select(Node node) {
        return (Node) shardSqlSessionTemplate.selectOne(DEFAULT_NAME_SPACE, "selectByPrimaryKey", node);
    }

    public List<String> selectAllClazz() {
        return shardSqlSessionTemplate.selectList(DEFAULT_NAME_SPACE, "selectAllClazz");
    }

    public List<String> selectAllMethodName() {
        return shardSqlSessionTemplate.selectList(DEFAULT_NAME_SPACE, "selectAllMethodName");
    }

    public List<Node> selectList(Node node) {
        return shardSqlSessionTemplate.selectList(DEFAULT_NAME_SPACE, "select", node);
    }

    public int delete(Node node) {
        return shardSqlSessionTemplate.delete(DEFAULT_NAME_SPACE, "deleteByPrimaryKey", node);
    }

    /**
     * This method corresponds to the database table stria_flow_lines
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}