package com.dcits.galaxy.cache.paratab;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dcits.galaxy.cache.exception.CacheException;
import com.dcits.galaxy.cache.paratab.metadata.Database;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;


/**
 * 聚合全局参数配置
 *
 * @author tangxlf
 * @date 2015-2-3
 */
@Repository
public class MusterParaTabCacheConfig {

    private static final Logger log = LoggerFactory.getLogger(MusterParaTabCacheConfig.class);


    private Map<String, ParaTabCacheInfo> configMap = new HashMap<String, ParaTabCacheInfo>();

    @Resource
    private ShardSqlSessionTemplate shardSqlSessionTemplate;

    private String dbProdName;//数据库产品名

    private Database database;//参数表所在数据库元数据

    public synchronized Database getDatabase() {
        if (database == null) {
            loadDatabase();
        }
        return database;
    }


    private boolean isLoad = false;    //是否装载数据库产品名

    public String getDbProdName() {
        if (!isLoad) {
            loadDBProdName();
        }
        return dbProdName;
    }

    public synchronized void addParaTabCacheInfo(ParaTabCacheInfo info) {
        //System.out.println("table "+info.getTableName() +" load  table metedata end !");
        log.debug("table " + info.getTableName() + " load  table metedata end !");
        configMap.put(info.getTableName(), info);
    }

    public void setDbProdName(String dbProdName) {
        this.dbProdName = dbProdName;
    }

    private void loadDBProdName() {
        Connection conn = null;
        try {
            conn = shardSqlSessionTemplate.getShardManager().getDefaultShard().getDataSource().getConnection();
            setDbProdName(conn.getMetaData().getDatabaseProductName().toUpperCase());
            isLoad = true;
        } catch (SQLException e) {
            throw new CacheException("参数表缓存初始化出错" + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new CacheException("参数表缓存初始化出错" + e.getMessage());
                }
            }
        }
    }


    private void loadDatabase() {
        Connection conn = null;
        Database database = null;
        try {
            conn = shardSqlSessionTemplate.getShardManager().getDefaultShard().getDataSource().getConnection();
            database = DatabaseFactory.newDatabase(conn, null, conn.getMetaData().getUserName());
        } catch (Exception e) {
            throw new CacheException("参数表缓存时，装载database元数据初始化出错！\n"
                    + e.getClass().getSimpleName() + ":" + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new CacheException("参数表缓存时，装载database元数据初始化出错！"
                            + e.getMessage());
                }
            }
        }
        if (database != null) {
            this.database = database;
        } else {
            throw new CacheException("参数表缓存时，装载database元数据初始化出错！");
        }
    }


    public synchronized ParaTabCacheInfo getCacheInfo(String tableName) {
        if (configMap.get(tableName) == null) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            	// no-do
            }
            if (configMap.get(tableName) == null) {
                throw new CacheException("表" + tableName + "尚未配置缓存文件!");
            }
            return configMap.get(tableName);

        }
        return configMap.get(tableName);
    }

    public String getPkStrs(String tableName) {
        return getCacheInfo(tableName).getPkStr();
    }

    public int getExpirePeriod(String tableName) {
        return ExpireConstant.getExpire(getCacheInfo(tableName).getExpirePeriod());
    }

    public String getDbTableName(String tableName) {
        return getCacheInfo(tableName).getDbTableName();
    }

    public int getColType(String tableName, String colName) {
        return getCacheInfo(tableName).getColumnMap().get(colName).getType();
    }

    public String getDbColName(String tableName, String colName) {
        return getCacheInfo(tableName).getColumnMap().get(colName).getName();
    }

    public String getHanlderClass(String tableName) {
        return getCacheInfo(tableName).getHanlderClass();
    }

    public ShardSqlSessionTemplate getShardSqlSessionTemplate() {
        return shardSqlSessionTemplate;
    }

    public void setShardSqlSessionTemplate(ShardSqlSessionTemplate shardSqlSessionTemplate) {
        this.shardSqlSessionTemplate = shardSqlSessionTemplate;
    }
}
