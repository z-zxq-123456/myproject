/**
 * Title: Galaxy(Distributed service platform)
 * File: SeqDao.java
 * Copyright: Copyright (c) 2014-2017
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.galaxy.sequences.dao;

import com.alibaba.druid.pool.DruidDataSource;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.sequences.model.Sequences;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Created on 2017/6/10.</p>
 *
 * @author Tim <Tim@dcits.com>
 * @version 1.0.0
 * @since 1.7
 */
public class OracleSeqDao {

    private ShardSqlSessionTemplate shardSqlSessionTemplate;

    /**
     * 是否自动创建序列，默认开启
     */
    private boolean autoCreateSeq = true;

    /**
     * oracle 序列命名空间
     */
    private static String nameSpace = "com.dcits.galaxy.sequences.dao.OracleSeqDao";

    /**
     * 创建序列
     *
     * @param seq
     */
    public void createSeq(Sequences seq) {
        // durid有防注入，不能设置cache
        shardSqlSessionTemplate.insert(nameSpace, "create", seq);
        // 获取默认的DataSource
        /*Statement statement = null;
        try {
            Connection connection = DataSourceUtils.getConnection(shardSqlSessionTemplate.getShardManager().getDefaultShard().getDataSource());
            //SqlRunner sqlRunner = new SqlRunner(connection);
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE SEQUENCE ").append(seq.getSeqName()).append("\n");
            sb.append("START WITH ").append(seq.getSeqMinValue()).append("\n");
            sb.append("INCREMENT BY ").append(seq.getSeqIncrementBy()).append("\n");
            sb.append("MAXVALUE ").append(seq.getSeqMaxValue()).append("\n");
            sb.append("MINVALUE ").append(seq.getSeqMinValue()).append("\n");
            if (seq.getSeqCache() > 0) {
                sb.append("CACHE ").append(seq.getSeqCache()).append("\n");
            } else {
                sb.append("NOCACHE ").append("\n");
            }
            if ("Y".equals(seq.getSeqCycle())) {
                sb.append("CYCLE").append("\n");
            } else {
                sb.append("NOCYCLE").append("\n");
            }
            sb.append("NOORDER");
            statement = connection.createStatement();
            statement.execute(sb.toString());
        } catch (Throwable t) {
            throw new RuntimeException(t);
        } finally {
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }*/
    }

    /**
     * 获取序列
     *
     * @param seq
     * @return
     */
    public Long getSeq(Sequences seq) {
        try {
            return (Long) shardSqlSessionTemplate.selectOne(nameSpace, "getSeq", seq);
        } catch (Throwable t) {
            // 无序列，自动创建序列，通过oracle异常码判断解决并发下的创建问题。
            if (autoCreateSeq && t.getCause() instanceof SQLException) {
                int errorCode = ((SQLException) t.getCause()).getErrorCode();
                // 序列不存在
                if (errorCode == 2289) {
                    try {
                        // 尝试创建序列
                        this.createSeq(seq);
                    } catch (Throwable t1) {
                        errorCode = ((SQLException) t.getCause()).getErrorCode();
                        if (errorCode != 955) {
                            throw t1;
                        }
                    }
                    // 读取序列数据
                    return (Long) shardSqlSessionTemplate.selectOne(nameSpace, "getSeq", seq);
                }
            }
            throw t;
        }
    }

    /**
     * 获取一个序列集合
     *
     * @param seq
     * @return
     */
    public List<Long> getSeqList(Sequences seq) {
        try {
            return shardSqlSessionTemplate.selectList(nameSpace, "getSeqList", seq);
        } catch (Throwable t) {
            if (t.getCause() instanceof SQLException) {
                int errorCode = ((SQLException) t.getCause()).getErrorCode();
                // oracle ora-02289 序列不存在
                if (autoCreateSeq && errorCode == 2289) {
                    try {
                        // 尝试创建序列
                        this.createSeq(seq);
                    } catch (Throwable t1) {
                        errorCode = ((SQLException) t.getCause()).getErrorCode();
                        // oracle ora-00955 序列名称已定义
                        if (errorCode != 955) {
                            throw t1;
                        }
                    }
                    // 读取序列数据
                    return shardSqlSessionTemplate.selectList(nameSpace, "getSeqList", seq);
                }
            }
            throw t;
        }
    }

    /**
     * 是否含有序列
     *
     * @param seqName
     * @return
     */
    public boolean containsSequence(String seqName) {
        Map<String, String> param = new HashMap<>();
        param.put("seqName", seqName);
        DataSource dataSource = shardSqlSessionTemplate.getShardManager().getDefaultShard().getDataSource();
        if (dataSource instanceof DruidDataSource) {
            param.put("user", ((DruidDataSource) dataSource).getUsername());
        }
        int count = (int) shardSqlSessionTemplate.selectOne(nameSpace, "containsSequence", param);
        if (1 == count)
            return true;
        else
            return false;
    }

    /**
     * 获取当前所有序列
     *
     * @return
     */
    public List<Sequences> getSequneces() {
        DataSource dataSource = shardSqlSessionTemplate.getShardManager().getDefaultShard().getDataSource();
        Map<String, String> param = new HashMap<>();
        if (dataSource instanceof DruidDataSource) {
            param.put("user", ((DruidDataSource) dataSource).getUsername());
        }
        return shardSqlSessionTemplate.selectList(nameSpace, "getSequences", param);

    }

    /**
     * 获取当前所有序列
     *
     * @return
     */
    public List<Sequences> getSequneces(String seqName) {
        Map<String, String> param = new HashMap<>();
        param.put("seqName", seqName);
        DataSource dataSource = shardSqlSessionTemplate.getShardManager().getDefaultShard().getDataSource();
        if (dataSource instanceof DruidDataSource) {
            param.put("user", ((DruidDataSource) dataSource).getUsername());
        }
        return shardSqlSessionTemplate.selectList(nameSpace, "getLikeSequences", param);

    }

    public void setShardSqlSessionTemplate(ShardSqlSessionTemplate shardSqlSessionTemplate) {
        this.shardSqlSessionTemplate = shardSqlSessionTemplate;
    }

    public void setAutoCreateSeq(boolean autoCreateSeq) {
        this.autoCreateSeq = autoCreateSeq;
    }
}
