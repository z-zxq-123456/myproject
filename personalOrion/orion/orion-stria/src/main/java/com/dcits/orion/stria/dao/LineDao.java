/**
 * <p>Title: LineDao<p>
 * <p>Description: Table stria_flow_lines Dao operator<p>
 * <p>Copyright: Copyright (c) 2015<p>
 * <p>Description: <p>
 * <p>Company: dcits<p>
 *
 * @author galaxyTools
 * @version V1.0
 */

package com.dcits.orion.stria.dao;

import com.dcits.orion.stria.dao.mapper.Line;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.util.List;

@Repository
public class LineDao {

    @Resource
    ShardSqlSessionTemplate shardSqlSessionTemplate;

    //Mapper文件定义的NameSpace
    private final String DEFAULT_NAME_SPACE = "com.dcits.orion.stria.dao.LineDao";

    public int insert(Line line) {
        return shardSqlSessionTemplate.insert(DEFAULT_NAME_SPACE, "insert", line);
    }

    public int update(Line line) {
        return shardSqlSessionTemplate.update(DEFAULT_NAME_SPACE, "updateByPrimaryKey", line);
    }

    public Line select(Line line) {
        return (Line) shardSqlSessionTemplate.selectOne(DEFAULT_NAME_SPACE, "selectByPrimaryKey", line);
    }

    public List<Line> selectList(Line line) {
        return shardSqlSessionTemplate.selectList(DEFAULT_NAME_SPACE, "select", line);
    }

    public int delete(Line line) {
        return shardSqlSessionTemplate.delete(DEFAULT_NAME_SPACE, "deleteByPrimaryKey", line);
    }
    /**
     * This method corresponds to the database table stria_flow_lines
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}