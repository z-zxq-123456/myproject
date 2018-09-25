/**
 * <p>Title: ParamDataAbstract.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Liang
 * @update 2015年3月5日 上午10:23:37
 * @version V1.0
 */
package com.dcits.galaxy.nosql;

import com.dcits.galaxy.nosql.exception.NoSqlException;
import com.dcits.galaxy.nosql.model.HCell;
import com.dcits.galaxy.nosql.model.HRow;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Liang
 * @version V1.0
 * @description
 * @update 2015年3月5日 上午10:23:37
 */

public class Hbase {
    private static Logger logger = LoggerFactory.getLogger(Hbase.class);
    // 声明静态配置
    private static Configuration conf;

    public static void CreateConfiguration() {
        Hbase.conf = new Configuration();
        Hbase.conf.addResource("hbase-site.xml");
        Hbase.conf = HBaseConfiguration.create(Hbase.conf);
    }

    public static void CreateConfiguration(Configuration conf) {
        Hbase.conf = conf;
    }

    public Configuration getConfiguration() {
        if (null == Hbase.conf)
            Hbase.CreateConfiguration();
        return Hbase.conf;
    }

    /*
     * 创建表
     *
     * @tableName 表名
     * @family 列族列表
     */
    @Deprecated
    public void creatTablecreatTable(String tableName, String[] family) throws Exception {
        HBaseAdmin admin = new HBaseAdmin(getConfiguration());
        HTableDescriptor desc = new HTableDescriptor(
                TableName.valueOf(tableName));
        for (int i = 0; i < family.length; i++) {
            desc.addFamily(new HColumnDescriptor(family[i]));
        }
        if (admin.tableExists(tableName)) {
            if (logger.isInfoEnabled())
                logger.info("table Exists!");
        } else {
            admin.createTable(desc);
            if (logger.isInfoEnabled())
                logger.info("create table Success!");
        }
        admin.close();
    }

    /*
     * 创建表
     *
     * @tableName 表名
     * @family 列族列表
     */
    public void creatTable(String tableName, String[] family) {
        HBaseAdmin admin = null;
        try {
            admin = new HBaseAdmin(getConfiguration());
            if (admin.tableExists(tableName)) {
                if (logger.isInfoEnabled())
                    logger.info("table Exists!");
            } else {
                HTableDescriptor desc = new HTableDescriptor(
                        TableName.valueOf(tableName));
                for (int i = 0; i < family.length; i++) {
                    desc.addFamily(new HColumnDescriptor(family[i]));
                }
                admin.createTable(desc);
                if (logger.isInfoEnabled())
                    logger.info("create table Success!");
            }
            admin.close();
        } catch (IOException e) {
            throw new NoSqlException(e);
        }
    }


    /**
     * @param rowKey     rowKey
     * @param familyName 列族
     * @param column     列族的列表
     * @param value      列值列表
     * @throws IOException
     * @description 表添加数据
     * @version 1.0
     * @author Liang
     * @update 2015年3月6日 上午10:39:22
     */
    public void put(String tableName, String rowKey, String familyName,
                    String[] column, String[] value) {
        Put put = new Put(Bytes.toBytes(rowKey));// 设置rowkey
        HTable table = null;
        try {
            table = new HTable(getConfiguration(), Bytes.toBytes(tableName));
            for (int j = 0; j < column.length; j++) {
                put.add(Bytes.toBytes(familyName), Bytes.toBytes(column[j]),
                        Bytes.toBytes(value[j]));
            }
            table.put(put);
        } catch (Throwable t) {
            throw new NoSqlException(t);
        } finally {
            if (table != null) {
                try {
                    table.close();
                } catch (IOException e) {
                    throw new NoSqlException(e);
                }
            }
        }

        if (logger.isDebugEnabled())
            logger.debug("add data Success!");
    }

    /**
     * @param tableName 表名
     * @param rowKey    rowKey
     * @return
     * @throws IOException
     * @description 根据rwokey查询
     * @version 1.0
     * @author Liang
     * @update 2015年3月6日 上午10:53:04
     */
    public HRow getResult(String tableName, String rowKey) {
        Get get = new Get(Bytes.toBytes(rowKey));
        HTable table = null;
        Result result = null;
        HRow row = new HRow();
        try {
            if (logger.isDebugEnabled())
                logger.debug("tableName:" + tableName + " rowKey:" + rowKey);
            Configuration conf = getConfiguration();
            if (logger.isDebugEnabled())
                logger.debug("hbase.zookeeper.quorum:" + conf.get("hbase.zookeeper.quorum"));
            table = new HTable(getConfiguration(), Bytes.toBytes(tableName));
            result = table.get(get);
            row = Result2HRow(result);

            if (logger.isDebugEnabled())
                logger.debug("HRow:" + row);
            /*
             * for (KeyValue kv : result.list()) { System.out.println("family:"
			 * + Bytes.toString(kv.getFamily())); System.out
			 * .println("qualifier:" + Bytes.toString(kv.getQualifier()));
			 * System.out.println("value:" + Bytes.toString(kv.getValue()));
			 * System.out.println("Timestamp:" + kv.getTimestamp());
			 * System.out.println
			 * ("-------------------------------------------"); }
			 */
        } catch (IOException t) {
            throw new NoSqlException(t);
        } finally {
            if (table != null) {
                try {
                    table.close();
                } catch (IOException e) {
                    throw new NoSqlException(e);
                }
            }
        }

        return row;
    }

    /**
     * @param tableName  表名
     * @param rowKey     rowKey
     * @param familyName 列簇
     * @param columnName 列名
     * @return
     * @throws IOException
     * @description
     * @version 1.0
     * @author Liang
     * @update 2015年3月6日 上午10:55:21
     */
    public Result getResultByColumn(String tableName, String rowKey,
                                    String familyName, String columnName) {
        HTable table = null;
        Result result = null;
        try {
            table = new HTable(getConfiguration(), Bytes.toBytes(tableName));
            Get get = new Get(Bytes.toBytes(rowKey));
            get.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName)); // 获取指定列族和列修饰符对应的列
            result = table.get(get);
            /*
             * for (KeyValue kv : result.list()) { System.out.println("family:"
			 * + Bytes.toString(kv.getFamily())); System.out
			 * .println("qualifier:" + Bytes.toString(kv.getQualifier()));
			 * System.out.println("value:" + Bytes.toString(kv.getValue()));
			 * System.out.println("Timestamp:" + kv.getTimestamp());
			 * System.out.println
			 * ("-------------------------------------------"); }
			 */
        } catch (Throwable t) {
            throw new NoSqlException(t);
        } finally {
            if (null != table) {
                try {
                    table.close();
                } catch (IOException e) {
                    throw new NoSqlException(e);
                }
            }
        }

        return result;
    }

    /**
     * @param tableName 表名
     * @return
     * @throws IOException
     * @description 查询hbase表
     * @version 1.0
     * @author Liang
     * @update 2015年3月6日 上午11:08:31
     */
    public ResultScanner getResultScann(String tableName) {
        Scan scan = new Scan();
        ResultScanner rs = null;
        HTable table = null;
        try {
            table = new HTable(getConfiguration(), Bytes.toBytes(tableName));
            rs = table.getScanner(scan);
            /*
             * for (Result r : rs) { for (KeyValue kv : r.list()) {
			 * System.out.println("row:" + Bytes.toString(kv.getRow()));
			 * System.out.println("family:" + Bytes.toString(kv.getFamily()));
			 * System.out.println("qualifier:" +
			 * Bytes.toString(kv.getQualifier())); System.out .println("value:"
			 * + Bytes.toString(kv.getValue())); System.out.println("timestamp:"
			 * + kv.getTimestamp()); System.out
			 * .println("-------------------------------------------"); } }
			 */
        } catch (Throwable t) {
            throw new NoSqlException(t);
        } finally {
            if (null != rs)
                rs.close();
            if (null != table) {
                try {
                    table.close();
                } catch (IOException e) {
                    throw new NoSqlException(e);
                }
            }
        }
        return rs;
    }

    /**
     * @param tableName 表名
     * @return
     * @throws IOException
     * @description 查询hbase表
     * @version 1.0
     * @author Liang
     * @update 2015年3月6日 上午11:08:31
     */
    public ArrayList<HRow> getResultScann(String tableName, Filter filter) {
        ResultScanner rs = null;
        HTable table = null;
        ArrayList<HRow> rows = new ArrayList<HRow>();
        try {
            table = new HTable(getConfiguration(), Bytes.toBytes(tableName));
            Scan scan = new Scan();
            scan.setFilter(filter);
            rs = table.getScanner(scan);
            for (Result r : rs) {
                rows.add(Result2HRow(r));
            }
        } catch (Throwable t) {
            throw new NoSqlException(t);
        } finally {
            if (null != rs)
                rs.close();
            if (null != table) {
                try {
                    table.close();
                } catch (IOException e) {
                    throw new NoSqlException(e);
                }
            }
        }
        return rows;
    }

    /**
     * @param tableName    表名
     * @param start_rowkey 起始rowkey
     * @param stop_rowkey  终止rowkey
     * @throws IOException
     * @description 查询hbase表, 指定rowkey范围
     * @version 1.0
     * @author Liang
     * @update 2015年3月6日 上午11:09:38
     */
    public ResultScanner getResultScann(String tableName, String start_rowkey,
                                        String stop_rowkey) {
        Scan scan = new Scan();
        scan.setStartRow(Bytes.toBytes(start_rowkey));
        scan.setStopRow(Bytes.toBytes(stop_rowkey));
        ResultScanner rs = null;
        HTable table = null;
        try {
            table = new HTable(getConfiguration(), Bytes.toBytes(tableName));
            rs = table.getScanner(scan);
            /*
             * for (Result r : rs) { for (KeyValue kv : r.list()) {
			 * System.out.println("row:" + Bytes.toString(kv.getRow()));
			 * System.out.println("family:" + Bytes.toString(kv.getFamily()));
			 * System.out.println("qualifier:" +
			 * Bytes.toString(kv.getQualifier())); System.out .println("value:"
			 * + Bytes.toString(kv.getValue())); System.out.println("timestamp:"
			 * + kv.getTimestamp()); System.out
			 * .println("-------------------------------------------"); } }
			 */
        } catch (Throwable t) {
            throw new NoSqlException(t);
        } finally {
            if (null != rs)
                rs.close();
            if (null != table) {
                try {
                    table.close();
                } catch (IOException e) {
                    throw new NoSqlException(e);
                }
            }
        }
        return rs;
    }

    /*
     * 更新表中的某一列
     *
     * @tableName 表名
     *
     * @rowKey rowKey
     *
     * @familyName 列族名
     *
     * @columnName 列名
     *
     * @value 更新后的值
     */
    public void update(String tableName, String rowKey, String familyName,
                       String columnName, String value) {
        HTable table = null;
        try {
            table = new HTable(getConfiguration(), Bytes.toBytes(tableName));
            Put put = new Put(Bytes.toBytes(rowKey));
            put.add(Bytes.toBytes(familyName), Bytes.toBytes(columnName),
                    Bytes.toBytes(value));
            table.put(put);
            table.close();
        } catch (Throwable t) {
            throw new NoSqlException(t);
        } finally {
            if (null != table) {
                try {
                    table.close();
                } catch (IOException e) {
                    throw new NoSqlException(e);
                }
            }
        }
    }

    /**
     * @param tableName  表名
     * @param rowKey     rowKey
     * @param familyName 列族名
     * @param columnName 列名
     * @return
     * @throws IOException
     * @description 查询某列数据的多个版本
     * @version 1.0
     * @author Liang
     * @update 2015年3月6日 上午11:06:14
     */
    public Result getResultByVersion(String tableName, String rowKey,
                                     String familyName, String columnName) {
        HTable table = null;
        Result result = null;
        try {
            table = new HTable(getConfiguration(), Bytes.toBytes(tableName));
            Get get = new Get(Bytes.toBytes(rowKey));
            get.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName));
            get.setMaxVersions(5);
            result = table.get(get);
        /*
         * for (KeyValue kv : result.list()) { System.out.println("family:" +
		 * Bytes.toString(kv.getFamily())); System.out .println("qualifier:" +
		 * Bytes.toString(kv.getQualifier())); System.out.println("value:" +
		 * Bytes.toString(kv.getValue())); System.out.println("Timestamp:" +
		 * kv.getTimestamp());
		 * System.out.println("-------------------------------------------"); }
		 */
        } catch (Throwable t) {
            throw new NoSqlException(t);
        } finally {
            if (null != table) {
                try {
                    table.close();
                } catch (IOException e) {
                    throw new NoSqlException(e);
                }
            }
        }
        return result;
    }

    /**
     * @param tableName  表名
     * @param rowKey     rowKey
     * @param falilyName 列族名
     * @param columnName 列名
     * @throws IOException
     * @description 删除指定的列
     * @version 1.0
     * @author Liang
     * @update 2015年3月6日 上午10:58:02
     */
    public void deleteColumn(String tableName, String rowKey,
                             String falilyName, String columnName) {
        HTable table = null;
        try {
            table = new HTable(getConfiguration(), Bytes.toBytes(tableName));
            Delete deleteColumn = new Delete(Bytes.toBytes(rowKey));
            deleteColumn.deleteColumns(Bytes.toBytes(falilyName),
                    Bytes.toBytes(columnName));
            table.delete(deleteColumn);
        } catch (Throwable t) {
            throw new NoSqlException(t);
        } finally {
            if (null != table) {
                try {
                    table.close();
                } catch (IOException e) {
                    throw new NoSqlException(e);
                }
            }
        }
    }

    /**
     * @param tableName 表名
     * @param rowKey    rowKey
     * @throws IOException
     * @description 删除指定的列
     * @version 1.0
     * @author Liang
     * @update 2015年3月6日 上午10:58:54
     */
    public void deleteAllColumn(String tableName, String rowKey) {
        HTable table = null;
        try {
            table = new HTable(getConfiguration(), Bytes.toBytes(tableName));
            Delete deleteAll = new Delete(Bytes.toBytes(rowKey));
            table.delete(deleteAll);
        } catch (Throwable t) {
            throw new NoSqlException(t);
        } finally {
            if (null != table) {
                try {
                    table.close();
                } catch (IOException e) {
                    throw new NoSqlException(e);
                }
            }
        }
    }

    /**
     * @param tableName 表名
     * @throws IOException
     * @description 删除表
     * @version 1.0
     * @author Liang
     * @update 2015年3月6日 上午11:00:19
     */
    public void deleteTable(String tableName) {
        HBaseAdmin admin = null;
        try {
            admin = new HBaseAdmin(getConfiguration());
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
        } catch (Throwable t) {
            throw new NoSqlException(t);
        } finally {
            if (null != admin) {
                try {
                    admin.close();
                } catch (IOException e) {
                    throw new NoSqlException(e);
                }
            }
        }
        if (logger.isInfoEnabled())
            logger.info(tableName + "is deleted!");
    }

    public HRow Result2HRow(Result result) {
        ArrayList<HCell> lists = new ArrayList<HCell>();
        HRow row = new HRow();
        row.setRowkey(Bytes.toString(result.getRow()));
        for (Cell cell : result.rawCells()) {
            HCell hc = new HCell();
            hc.setRowName(new String(CellUtil.cloneRow(cell)));
            hc.setFamilyName(new String(CellUtil.cloneFamily(cell)));
            hc.setColumn(new String(CellUtil.cloneQualifier(cell)));
            hc.setValue(new String(CellUtil.cloneValue(cell)));
            lists.add(hc);
        }
        row.setCell(lists);
        return row;
    }

    /**
     * @param result 查询结果
     * @description 输出Result结果
     * @version 1.0
     * @author Liang
     * @update 2015年3月6日 上午11:21:09
     */
    public static void toStringResult(Result result) {
        for (Cell cell : result.rawCells()) {
            logger.debug("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
            logger.debug("Timetamp:" + cell.getTimestamp() + " ");
            logger.debug("column Family:"
                    + new String(CellUtil.cloneFamily(cell)) + " ");
            logger.debug("row Name:"
                    + new String(CellUtil.cloneQualifier(cell)) + " ");
            logger.debug("value:" + new String(CellUtil.cloneValue(cell)) + " ");
            logger.debug("-------------------------------------------");
        }
    }
}
