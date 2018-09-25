package com.dcits.orion.batch.common.dao;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.jdbc.Null;
import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.util.JsonUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.idempotent.IdempotentContext;
import com.dcits.galaxy.dal.mybatis.idempotent.IdempotentUtil;
import com.dcits.galaxy.dal.mybatis.plugins.RowArgs;
import com.dcits.galaxy.dal.mybatis.shard.Shard;
import com.dcits.galaxy.dal.mybatis.shard.ShardManager;
import com.dcits.galaxy.dal.mybatis.transaction.DataSourceUtils;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.batch.common.ShardManageUtils;
import com.dcits.orion.batch.common.bean.AbstractTableObj;

/**
 * Created by lixbb on 2016/2/18.
 */
@Repository
public class AutoMapperDao {


    private int batchSize = 5000;

    static int borrow = 0;

    private static Logger logger = LoggerFactory.getLogger(AutoMapperDao.class);
    @Resource
    IdempotentUtil idempotentUtil;
    @Resource
    private ShardSqlSessionTemplate shardSqlSessionTemplate;
    private static final String NAMESPACE = "com.dcits.galaxy.dal.dto.dao.AutoMapperDao";




    public void insert(AbstractTableObj tableObj) {
        insert(tableObj, shardSqlSessionTemplate, shardSqlSessionTemplate.getShardManager().getDefaultShard());
    }

    public void insert(AbstractTableObj tableObj, Shard shard) {
        insert(tableObj, shardSqlSessionTemplate, shard);

    }

    public void insert(AbstractTableObj tableObj, ShardManager shardManager, Shard shard) {
        insert(tableObj, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shard);

    }

    public void insert(AbstractTableObj tableObj, ShardManager shardManager) {
        insert(tableObj, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shardManager.getDefaultShard());
    }

    public void insert(AbstractTableObj tableObj, ShardSqlSessionTemplate shardSqlSessionTemplate, Shard shard) {
        if (idempotentCheck(shard))
        {
            ExecuteUtils executeUtils = new ExecuteUtils(tableObj, "insert", shardSqlSessionTemplate, shard, null);
            executeUtils.insert();
        }

    }


    public void batchInsert(AbstractTableObj tableObj, ShardSqlSessionTemplate shardSqlSessionTemplate, Shard shard, int size) {

       /* if(size == 0)
            size = batchSize;
        List records = tableObj.getRecords();
        List<List> splitList = split(records, size);
        for (List list : splitList)
        {
            tableObj.setRecords(list);
            ExecuteUtils executeUtils = new ExecuteUtils(tableObj,"batchInsert",shardSqlSessionTemplate,shard,null);
            executeUtils.insert();
        }
        tableObj.setRecords(records);*/
        insertBatch(tableObj, shardSqlSessionTemplate, shard, size);
    }


    public void batchInsert(AbstractTableObj tableObjs, ShardManager shardManager, int size) {
        batchInsert(tableObjs, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shardManager.getDefaultShard(), size);

    }

    public void batchInsert(AbstractTableObj tableObjs, ShardManager shardManager) {
        batchInsert(tableObjs, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shardManager.getDefaultShard(), batchSize);

    }

    public void batchInsert(AbstractTableObj tableObjs, ShardManager shardManager, Shard shard, int size) {
        batchInsert(tableObjs, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shard, size);


    }

    public void batchInsert(AbstractTableObj tableObjs, ShardManager shardManager, Shard shard) {
        batchInsert(tableObjs, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shard, batchSize);
    }


    public void batchInsert(AbstractTableObj tableObjs, int size) {
        batchInsert(tableObjs, shardSqlSessionTemplate, shardSqlSessionTemplate.getShardManager().getDefaultShard(), size);

    }

    public void batchInsert(AbstractTableObj tableObjs) {
        batchInsert(tableObjs, shardSqlSessionTemplate, shardSqlSessionTemplate.getShardManager().getDefaultShard(), batchSize);

    }

    public void batchInsert(AbstractTableObj tableObjs, Shard shard, int size) {
        batchInsert(tableObjs, shardSqlSessionTemplate, shard, size);
    }

    public void batchInsert(AbstractTableObj tableObjs, Shard shard) {
        batchInsert(tableObjs, shardSqlSessionTemplate, shard, batchSize);
    }


    public void insertBatch(AbstractTableObj tableObj, ShardSqlSessionTemplate shardSqlSessionTemplate, Shard shard, int size) {
        if (idempotentCheck(shard)) {
            if (size == 0)
                size = batchSize;
            List records = tableObj.getRecords();
            List<List> splitList = split(records, size);
            for (List list : splitList) {
                tableObj.setRecords(list);
                ExecuteUtils executeUtils = new ExecuteUtils(tableObj, "insertBatch", shardSqlSessionTemplate, shard, null);
                executeUtils.executeBatch();
            }
            tableObj.setRecords(records);
        }
    }

    public void insertBatch(AbstractTableObj tableObjs, ShardManager shardManager, int size) {
        insertBatch(tableObjs, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shardManager.getDefaultShard(), size);

    }

    public void insertBatch(AbstractTableObj tableObjs, ShardManager shardManager) {
        insertBatch(tableObjs, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shardManager.getDefaultShard(), batchSize);

    }

    public void insertBatch(AbstractTableObj tableObjs, ShardManager shardManager, Shard shard, int size) {
        insertBatch(tableObjs, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shard, size);


    }

    public void insertBatch(AbstractTableObj tableObjs, ShardManager shardManager, Shard shard) {
        insertBatch(tableObjs, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shard, batchSize);
    }


    public void insertBatch(AbstractTableObj tableObjs, int size) {
        insertBatch(tableObjs, shardSqlSessionTemplate, shardSqlSessionTemplate.getShardManager().getDefaultShard(), size);

    }

    public void insertBatch(AbstractTableObj tableObjs) {
        insertBatch(tableObjs, shardSqlSessionTemplate, shardSqlSessionTemplate.getShardManager().getDefaultShard(), batchSize);

    }

    public void insertBatch(AbstractTableObj tableObjs, Shard shard, int size) {
        insertBatch(tableObjs, shardSqlSessionTemplate, shard, size);
    }

    public void insertBatch(AbstractTableObj tableObjs, Shard shard) {
        insertBatch(tableObjs, shardSqlSessionTemplate, shard, batchSize);
    }


    public void batchUpdate(AbstractTableObj tableObj, ShardSqlSessionTemplate shardSqlSessionTemplate, Shard shard, int size) {

        if (idempotentCheck(shard)) {
            if (size == 0)
                size = batchSize;
            List records = tableObj.getRecords();
            List<List> splitList = split(records, size);
            for (List list : splitList) {
                tableObj.setRecords(list);
                ExecuteUtils executeUtils = new ExecuteUtils(tableObj, "batchUpdate", shardSqlSessionTemplate, shard, null);
                executeUtils.executeBatch();
            }
            tableObj.setRecords(records);
        }
    }

    public void batchUpdate(AbstractTableObj tableObjs, ShardManager shardManager, int size) {
        batchUpdate(tableObjs, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shardManager.getDefaultShard(), size);

    }

    public void batchUpdate(AbstractTableObj tableObjs, ShardManager shardManager) {
        batchUpdate(tableObjs, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shardManager.getDefaultShard(), batchSize);

    }

    public void batchUpdate(AbstractTableObj tableObjs, ShardManager shardManager, Shard shard, int size) {
        batchUpdate(tableObjs, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shard, size);


    }

    public void batchUpdate(AbstractTableObj tableObjs, ShardManager shardManager, Shard shard) {
        batchUpdate(tableObjs, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shard, batchSize);
    }


    public void batchUpdate(AbstractTableObj tableObjs, int size) {
        batchUpdate(tableObjs, shardSqlSessionTemplate, shardSqlSessionTemplate.getShardManager().getDefaultShard(), size);

    }

    public void batchUpdate(AbstractTableObj tableObjs) {
        batchUpdate(tableObjs, shardSqlSessionTemplate, shardSqlSessionTemplate.getShardManager().getDefaultShard(), batchSize);

    }

    public void batchUpdate(AbstractTableObj tableObjs, Shard shard, int size) {
        batchUpdate(tableObjs, shardSqlSessionTemplate, shard, size);
    }

    public void batchUpdate(AbstractTableObj tableObjs, Shard shard) {
        batchUpdate(tableObjs, shardSqlSessionTemplate, shard, batchSize);
    }


    public void batchDelete(AbstractTableObj tableObj, ShardSqlSessionTemplate shardSqlSessionTemplate, Shard shard, int size) {

        if (idempotentCheck(shard)){
            if (size == 0)
                size = batchSize;
            List records = tableObj.getRecords();
            List<List> splitList = split(records, size);
            for (List list : splitList) {
                tableObj.setRecords(list);
                ExecuteUtils executeUtils = new ExecuteUtils(tableObj, "batchDelete", shardSqlSessionTemplate, shard, null);
                executeUtils.executeBatch();
            }
            tableObj.setRecords(records);
        }
    }

    public void batchDelete(AbstractTableObj tableObjs, ShardManager shardManager, int size) {
        batchDelete(tableObjs, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shardManager.getDefaultShard(), size);

    }

    public void batchDelete(AbstractTableObj tableObjs, ShardManager shardManager) {
        batchDelete(tableObjs, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shardManager.getDefaultShard(), batchSize);

    }

    public void batchDelete(AbstractTableObj tableObjs, ShardManager shardManager, Shard shard, int size) {
        batchDelete(tableObjs, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shard, size);


    }

    public void batchDelete(AbstractTableObj tableObjs, ShardManager shardManager, Shard shard) {
        batchDelete(tableObjs, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shard, batchSize);
    }


    public void batchDelete(AbstractTableObj tableObjs, int size) {
        batchDelete(tableObjs, shardSqlSessionTemplate, shardSqlSessionTemplate.getShardManager().getDefaultShard(), size);

    }

    public void batchDelete(AbstractTableObj tableObjs) {
        batchDelete(tableObjs, shardSqlSessionTemplate, shardSqlSessionTemplate.getShardManager().getDefaultShard(), batchSize);

    }

    public void batchDelete(AbstractTableObj tableObjs, Shard shard, int size) {
        batchDelete(tableObjs, shardSqlSessionTemplate, shard, size);
    }

    public void batchDelete(AbstractTableObj tableObjs, Shard shard) {
        batchDelete(tableObjs, shardSqlSessionTemplate, shard, batchSize);
    }


    public void delete(AbstractTableObj tableObj, ShardSqlSessionTemplate shardSqlSessionTemplate, Shard shard) {

        if (idempotentCheck(shard)) {
            ExecuteUtils executeUtils = new ExecuteUtils(tableObj, "delete", shardSqlSessionTemplate, shard, null);
            executeUtils.delete();
        }
    }

    public void delete(AbstractTableObj tableObj, ShardManager shardManager) {
        delete(tableObj, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shardManager.getDefaultShard());
    }

    public void delete(AbstractTableObj tableObj, ShardManager shardManager, Shard shard) {
        delete(tableObj, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shard);
    }

    public void delete(AbstractTableObj tableObj) {
        delete(tableObj, shardSqlSessionTemplate, shardSqlSessionTemplate.getShardManager().getDefaultShard());
    }

    public void delete(AbstractTableObj tableObj, Shard shard) {
        delete(tableObj, shardSqlSessionTemplate, shard);
    }


    public void update(AbstractTableObj tableObj, ShardSqlSessionTemplate shardSqlSessionTemplate, Shard shard) {
        if (idempotentCheck(shard)){
            ExecuteUtils executeUtils = new ExecuteUtils(tableObj, "update", shardSqlSessionTemplate, shard, null);
            executeUtils.update();
        }
    }


    public void update(AbstractTableObj tableObj, ShardManager shardManager, Shard shard) {
        update(tableObj, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shard);

    }

    public void update(AbstractTableObj tableObj, ShardManager shardManager) {
        update(tableObj, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shardManager.getDefaultShard());

    }

    public void update(AbstractTableObj tableObj, Shard shard) {
        update(tableObj, shardSqlSessionTemplate, shard);

    }

    public void update(AbstractTableObj tableObj) {
        update(tableObj, shardSqlSessionTemplate, shardSqlSessionTemplate.getShardManager().getDefaultShard());
    }


    public Map selectOne(AbstractTableObj tableObj, ShardSqlSessionTemplate shardSqlSessionTemplate, Shard shard) {
        ExecuteUtils executeUtils = new ExecuteUtils(tableObj, "select", shardSqlSessionTemplate, shard, null);
        return executeUtils.selectOne();
    }


    public Map selectOne(AbstractTableObj tableObj, ShardManager shardManager, Shard shard) {
        return selectOne(tableObj, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shard);
    }

    public Map selectOne(AbstractTableObj tableObj, ShardManager shardManager) {
        return selectOne(tableObj, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shardManager.getDefaultShard());
    }

    public Map selectOne(AbstractTableObj tableObj, Shard shard) {
        return selectOne(tableObj, shardSqlSessionTemplate, shard);
    }

    public Map selectOne(AbstractTableObj tableObj) {
        return selectOne(tableObj, shardSqlSessionTemplate, shardSqlSessionTemplate.getShardManager().getDefaultShard());
    }


    public Map selectCount(AbstractTableObj tableObj, ShardSqlSessionTemplate shardSqlSessionTemplate, Shard shard) {
        ExecuteUtils executeUtils = new ExecuteUtils(tableObj, "selectCount", shardSqlSessionTemplate, shard, null);
        return executeUtils.selectOne();
    }


    public Map selectCount(AbstractTableObj tableObj, ShardManager shardManager, Shard shard) {
        return selectCount(tableObj, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shard);
    }

    public Map selectCount(AbstractTableObj tableObj, ShardManager shardManager) {
        return selectCount(tableObj, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shardManager.getDefaultShard());
    }

    public Map selectCount(AbstractTableObj tableObj, Shard shard) {
        return selectCount(tableObj, shardSqlSessionTemplate, shard);
    }

    public Map selectCount(AbstractTableObj tableObj) {
        return selectCount(tableObj, shardSqlSessionTemplate, shardSqlSessionTemplate.getShardManager().getDefaultShard());
    }


    public List selectList(AbstractTableObj tableObj, ShardSqlSessionTemplate shardSqlSessionTemplate, Shard shard) {
        ExecuteUtils executeUtils = new ExecuteUtils(tableObj, "select", shardSqlSessionTemplate, shard, null);
        return executeUtils.selectAll();
    }


    public List selectList(AbstractTableObj tableObj, ShardManager shardManager) {
        return selectList(tableObj, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shardManager.getDefaultShard());
    }

    public List selectList(AbstractTableObj tableObj, ShardManager shardManager, Shard shard) {
        return selectList(tableObj, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shard);
    }


    public List selectList(AbstractTableObj tableObj) {
        return selectList(tableObj, shardSqlSessionTemplate, shardSqlSessionTemplate.getShardManager().getDefaultShard());
    }

    public List selectList(AbstractTableObj tableObj, Shard shard) {
        return selectList(tableObj, shardSqlSessionTemplate, shard);
    }

    public List selectSplitByKeyList(AbstractTableObj tableObj, ShardSqlSessionTemplate shardSqlSessionTemplate, Shard shard) {
        ExecuteUtils executeUtils = new ExecuteUtils(tableObj, "selectSplitByKey", shardSqlSessionTemplate, shard, null);
        return executeUtils.selectAll();
    }


    public List selectSplitByKeyList(AbstractTableObj tableObj, ShardManager shardManager) {
        return selectSplitByKeyList(tableObj, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shardManager.getDefaultShard());
    }

    public List selectSplitByKeyList(AbstractTableObj tableObj, ShardManager shardManager, Shard shard) {
        return selectSplitByKeyList(tableObj, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shard);
    }


    public List selectSplitByKeyList(AbstractTableObj tableObj) {
        return selectSplitByKeyList(tableObj, shardSqlSessionTemplate, shardSqlSessionTemplate.getShardManager().getDefaultShard());
    }

    public List selectSplitByKeyList(AbstractTableObj tableObj, Shard shard) {
        return selectSplitByKeyList(tableObj, shardSqlSessionTemplate, shard);
    }


    public List selectRangeList(AbstractTableObj tableObj, ShardSqlSessionTemplate shardSqlSessionTemplate, Shard shard) {
        ExecuteUtils executeUtils = new ExecuteUtils(tableObj, "selectRange", shardSqlSessionTemplate, shard, null);
        return executeUtils.selectAll();
    }


    public List selectRangeList(AbstractTableObj tableObj, ShardManager shardManager) {
        return selectRangeList(tableObj, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shardManager.getDefaultShard());
    }

    public List selectRangeList(AbstractTableObj tableObj, ShardManager shardManager, Shard shard) {
        return selectRangeList(tableObj, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shard);
    }


    public List selectRangeList(AbstractTableObj tableObj) {
        return selectRangeList(tableObj, shardSqlSessionTemplate, shardSqlSessionTemplate.getShardManager().getDefaultShard());
    }

    public List selectRangeList(AbstractTableObj tableObj, Shard shard) {
        return selectRangeList(tableObj, shardSqlSessionTemplate, shard);
    }


    public List selectListByPage(AbstractTableObj tableObj, ShardSqlSessionTemplate shardSqlSessionTemplate, Shard shard, RowArgs rowArgs) {
        ExecuteUtils executeUtils = new ExecuteUtils(tableObj, "selectByPage", shardSqlSessionTemplate, shard, rowArgs);
        return executeUtils.selectAll();
    }

    public List selectListByPage(AbstractTableObj tableObj, ShardManager shardManager, RowArgs rowArgs) {
        return selectListByPage(tableObj, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shardManager.getDefaultShard(), rowArgs);
    }

    public List selectListByPage(AbstractTableObj tableObj, ShardManager shardManager, Shard shard, RowArgs rowArgs) {
        return selectListByPage(tableObj, ShardManageUtils.getShardSqlSessionTemplate(shardManager), shard, rowArgs);
    }

    public List selectListByPage(AbstractTableObj tableObj, RowArgs rowArgs) {

        return selectListByPage(tableObj, shardSqlSessionTemplate, shardSqlSessionTemplate.getShardManager().getDefaultShard(), rowArgs);
    }

    public List selectListByPage(AbstractTableObj tableObj, Shard shard, RowArgs rowArgs) {
        return selectListByPage(tableObj, shardSqlSessionTemplate, shard, rowArgs);
    }

    public List<List> split(List list) {
        return split(list, batchSize);
    }

    public List<List> split(List list, int size) {
        int listSize = list.size();
        List<List> retList = new ArrayList<>();
        for (int i = 0; i * size < listSize; i++) {
            List ret = list.subList(i * size, (i + 1) * size > listSize ? listSize : (i + 1) * size);
            retList.add(ret);
        }
        return retList;

    }


    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public boolean idempotentCheck(Shard shard)
    {
        if (IdempotentContext.getIdempotentObj() == null)
            return true;
        List shards = new ArrayList();
        shards.add(shard);
        List<Shard> retShards = idempotentUtil.exist(IdempotentContext.getIdempotentObj(),shards);
        if (retShards != null && !retShards.isEmpty()) {
            return true;
        }
        else return false;
    }

    public void executeSql(String sql,List args)
    {
        Shard shard = shardSqlSessionTemplate.getShardManager().getDefaultShard();
        executeSql(sql,args,shard);
    }

    public void executeSql(String sql,List args,ShardManager shardManager)
    {
        Shard shard = shardManager.getDefaultShard();
        executeSql(sql,args,shard);
    }
    public void executeSql(String sql,List args, Shard shard)
    {
        if (idempotentCheck(shard))
        {
            ExecuteUtils executeUtils = new ExecuteUtils(sql,args,shard);
            executeUtils.execute();
        }
    }


    public void executeBatchSql(String sql,List<Object[]> args)
    {
        Shard shard = shardSqlSessionTemplate.getShardManager().getDefaultShard();
        executeBatchSql(sql,args,shard);
    }

    public void executeBatchSql(String sql,List<Object[]> args,ShardManager shardManager)
    {
        Shard shard = shardManager.getDefaultShard();
        executeBatchSql(sql,args,shard);
    }
    public void executeBatchSql(String sql,List<Object[]> args, Shard shard)
    {
        if (idempotentCheck(shard))
        {
            ExecuteUtils executeUtils = new ExecuteUtils(sql,args,shard);
            executeUtils.executeBatch();
        }
    }
    private static class ExecuteUtils {
        private SqlRunner sqlRunner;

        private String sql;

        private List args;

        private Connection connection;

        private DataSource dataSource;

        private Shard shard;

        public ExecuteUtils(String sql,List args, Shard shard)
        {
            this.shard = shard;
            dataSource = shard.getDataSource();
            connection = DataSourceUtils.getConnection(dataSource);
            sqlRunner = new SqlRunner(connection);
            this.sql = sql;
            this.args = args;
        }


        public ExecuteUtils(AbstractTableObj tableObj, String flag, ShardSqlSessionTemplate shardSqlSessionTemplate, Shard shard, RowArgs rowArgs) {
            this.shard = shard;
            dataSource = shard.getDataSource();
            connection = DataSourceUtils.getConnection(dataSource);

            if (flag.equals("insert"))
                sql = tableObj.insertSql(getColumns(tableObj.tableName()));
            else if (flag.equals("update"))
                sql = tableObj.updateSql();
            else if (flag.equals("delete"))
                sql = tableObj.deleteSql();
            else if (flag.equals("select"))
                sql = tableObj.selectSql();
            else if (flag.equals("selectCount"))
                sql = tableObj.selectCountSql();
            else if (flag.equals("selectByPage"))
                sql = tableObj.selectSqlByPage(getDatabaseProductName(), rowArgs);
            else if (flag.equals("batchInsert"))
                sql = tableObj.batchInsertSql(getDatabaseProductName(), getColumns(tableObj.tableName()));
            else if (flag.equals("insertBatch"))
                sql = tableObj.insertBatchSql(getColumns(tableObj.tableName()));
            else if (flag.equals("batchUpdate"))
                sql = tableObj.batchUpdateSql();
            else if (flag.equals("batchDelete"))
                sql = tableObj.batchDeleteSql();
            else if (flag.equals("selectSplitByKey"))
                sql = tableObj.getSplitByKeySql(getDatabaseProductName());
            else if (flag.equals("selectRange"))
                sql = tableObj.getRangeSql();
            if (logger.isDebugEnabled())
                logger.debug("自动生成的SQL：" + sql);
            this.args = tableObj.getArgs();
            /*if (flag.equals("batchInsert") || flag.equals("insertBatch") || flag.equals("batchUpdate") || flag.equals("batchDelete") ||flag.equals("update") ||flag.equals("delete")) {
                this.args = tableObj.getArgs();
            } else {
                TextSqlNode node = new TextSqlNode(sql);
                DynamicSqlSource s = new DynamicSqlSource(shardSqlSessionTemplate.getConfiguration(), node);
                BoundSql boundSql = s.getBoundSql(tableObj);
                this.sql = boundSql.getSql();
                List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
                List argsArray = new ArrayList();
                for (ParameterMapping parameterMapping : parameterMappings) {
                    argsArray.add(tableObj.get(parameterMapping.getProperty()));
                }
                this.args = argsArray;
            }*/
            sqlRunner = new SqlRunner(connection);
        }

        public List selectAll() {
            try {
                return sqlRunner.selectAll(sql, args.toArray());
            } catch (SQLException e) {
                throw new GalaxyException(e);
            } finally {
                releaseConnection();
            }
        }

        public Map selectOne() {
            try {
                List<Map<String, Object>> list = sqlRunner.selectAll(sql, args.toArray());
                if (list == null || list.isEmpty())
                    return null;
                else
                    return list.get(0);
            } catch (SQLException e) {
                throw new GalaxyException(e);
            } finally {
                releaseConnection();
            }
        }

        public void execute()
        {
            try {
                sqlRunner.update(sql, args.toArray());
            } catch (SQLException e) {
                throw new GalaxyException(e);
            } finally {
                releaseConnection();
            }
        }

        public void insert() {
            execute();
        }

        public void executeBatch() {
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(sql);
                for (Object object : this.args) {
                    Object[] array = (Object[]) object;
                    this.setParameters(ps, array);
                    ps.addBatch();
                }
                ps.executeBatch();
            } catch (SQLException e) {
                throw new GalaxyException(e);
            } finally {
                if (ps != null)
                    try {
                        ps.clearBatch();
                        ps.close();
                    } catch (SQLException e) {
                        logger.error(ExceptionUtils.getStackTrace(e));
                    }
                releaseConnection();
            }

        }


        public void update() {
            execute();

        }

        public void delete() {
            execute();
        }



        private void releaseConnection() {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        private String getDatabaseProductName() {

            try {
                return connection.getMetaData().getDatabaseProductName();
            } catch (SQLException e) {
                throw new GalaxyException(e);
            }
        }

        private List<String> getColumns(String tableName) {
            List columns = new ArrayList();
            try {

                String schema = null;

                String DatabaseProductName = getDatabaseProductName();
                if (AbstractTableObj.DatabaseProductName_Oracle.equals(DatabaseProductName)) {
                    String[] schemaTableName = tableName.split("\\.");
                    if (schemaTableName.length == 2) {
                        schema = schemaTableName[0];
                        tableName = schemaTableName[1];
                    } else {
                        schema = connection.getMetaData().getUserName().toUpperCase();
                    }
                }
                ResultSet rs = connection.getMetaData().getColumns(null, schema, tableName.toUpperCase(), null);
                while (rs.next()) {
                    String columnName = rs.getString("COLUMN_NAME").toUpperCase();
                    columns.add(columnName);
                }
                rs.close();
            } catch (Exception e) {
                if (logger.isErrorEnabled())
                    logger.error(ExceptionUtils.getStackTrace(e));
            }
            return columns;
        }

        private void setParameters(PreparedStatement ps, Object... args) throws SQLException {
            int i = 0;
            if (args == null)
                return;
            TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();

            for (int n = args.length; i < n; ++i) {
                if (args[i] == null) {
                    ps.setObject(i + 1, null);
                    //throw new SQLException("SqlRunner requires an instance of Null to represent typed null values for JDBC compatibility");
                } else if (args[i] instanceof Null) {
                    ((Null) args[i]).getTypeHandler().setParameter(ps, i + 1, null, ((Null) args[i]).getJdbcType());
                } else {
                    TypeHandler typeHandler = typeHandlerRegistry.getTypeHandler(args[i].getClass());
                    if (typeHandler == null) {
                        throw new SQLException("SqlRunner could not find a TypeHandler instance for " + args[i].getClass());
                    }
                    typeHandler.setParameter(ps, i + 1, args[i], (JdbcType) null);
                }
            }

        }
    }
    public <T> List<T> mapListToBeanList(List<Map> mapList,Class<T> tClass)
    {
        if (mapList == null)
            return null;


        List list = new ArrayList();
        for (Map map : mapList)
        {
            T object = mapToBean(map,tClass);
            list.add(object);
        }
        return list;
    }

    public <T> T mapToBean(Map map,Class<T> tClass)
    {
       if (map == null)
           return null;
        T object = null;
        try {
            object =  tClass.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(tClass);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                if (propertyDescriptor.getReadMethod() != null && propertyDescriptor.getWriteMethod() != null) {
                    String propertyTypeName = propertyDescriptor.getName();
                    Object value = map.get(JsonUtils.convertUpperCase(propertyTypeName));
                    if (value != null) {
                        if (propertyDescriptor.getPropertyType() == Long.class || propertyDescriptor.getPropertyType() == long.class)
                            propertyDescriptor.getWriteMethod().invoke(object, BatchUtils.parseLong(value));
                        else if (propertyDescriptor.getPropertyType() == double.class || propertyDescriptor.getPropertyType() == Double.class)
                            propertyDescriptor.getWriteMethod().invoke(object, BatchUtils.parseDouble(value));
                        else if (propertyDescriptor.getPropertyType() == int.class || propertyDescriptor.getPropertyType() == Integer.class)
                            propertyDescriptor.getWriteMethod().invoke(object, BatchUtils.parseInt(value));
                        else
                            propertyDescriptor.getWriteMethod().invoke(object, value);
                    }
                }
            }
        }
        catch (Exception e)
        {

        }
        return object;
    }



}
