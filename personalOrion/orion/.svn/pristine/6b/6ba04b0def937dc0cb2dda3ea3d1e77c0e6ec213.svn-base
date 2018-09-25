package com.dcits.orion.schedule.jdbc;

import com.dcits.orion.schedule.model.Partition;
import com.dcits.orion.schedule.process.DXProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCPartitioner {

    private static Logger logger = LoggerFactory.getLogger(DXProcess.class);

    public Partition getPartitions(String connectionName, String boundaryQuery,
                                   Long rows) {
        if (rows <= 0)
            return null;
        ResultSet rs = null;
        try {
            JDBCExecutor db = new JDBCExecutor(connectionName);
            String temp = boundaryQuery.toLowerCase();
            String queryCount = "select count(1) "
                    + boundaryQuery.substring(temp.indexOf("from"));
            if (logger.isDebugEnabled())
                logger.debug("queryCount sql:" + queryCount);
            rs = db.executeQuery(queryCount);
            if (null != rs && rs.next()) {
                Long count = rs.getLong(1);
                long parts = (count / rows) == 0 ? 1 : (count % rows) == 0 ? count
                        / rows : count / rows + 1;
                // mapper与reduce 1比4
                long loads = rows / 4;

                long loaders = (count / loads) == 0 ? 1
                        : (count % loads) == 0 ? count / loads : count / loads + 1;
                if (logger.isDebugEnabled())
                    logger.debug("根据抽取的数据量[" + count + "]和HDFS文件存放记录数[" + rows
                            + "]，设置EXTRACTORS=" + parts + "和LOADERS=" + loaders);
                return new Partition(String.valueOf(parts), String.valueOf(loaders));
            }
            return null;
        } catch (Throwable t) {
            return null;
        } finally {
            if (null != rs) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public Partition getPartitions(Connection connection, String boundaryQuery,
                                   Long rows) {
        if (rows <= 0)
            return null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            String temp = boundaryQuery.toLowerCase();
            String queryCount = "select count(1) "
                    + boundaryQuery.substring(temp.indexOf("from"));
            if (logger.isDebugEnabled())
                logger.debug("queryCount sql:" + queryCount);
            statement = connection.createStatement(
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            rs = statement.executeQuery(queryCount);
            if (null != rs && rs.next()) {
                Long count = rs.getLong(1);
                long parts = (count / rows) == 0 ? 1 : (count % rows) == 0 ? count
                        / rows : count / rows + 1;
                // mapper与reduce 1比4
                long loads = rows / 4;

                long loaders = (count / loads) == 0 ? 1
                        : (count % loads) == 0 ? count / loads : count / loads + 1;
                if (logger.isDebugEnabled())
                    logger.debug("根据抽取的数据量[" + count + "]和HDFS文件存放记录数[" + rows
                            + "]，设置EXTRACTORS=" + parts + "和LOADERS=" + loaders);
                return new Partition(String.valueOf(parts), String.valueOf(loaders));
            }
            return null;
        } catch (Throwable t) {
            return null;
        } finally {
            if (null != rs) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}
