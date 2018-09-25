package com.dcits.orion.schedule.jdbc;

import com.dcits.orion.schedule.common.DBError;
import com.dcits.orion.schedule.common.JobConfiguration;
import com.dcits.orion.schedule.exception.DBException;
import com.dcits.orion.schedule.model.JBDCConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import java.sql.*;
import java.util.Properties;

public class JDBCExecutor {

    private static Logger logger = LoggerFactory.getLogger(JDBCExecutor.class);

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    private PreparedStatement preparedStatement;

    public JDBCExecutor(String connectionName) {
        JBDCConnection jcc = JobConfiguration.getInstance()
                .getConnectionContainer().getConnection(connectionName);
        try {
            Class.forName(jcc.getJdbc_driver());
            connection = DriverManager.getConnection(jcc.getConnection_url(),
                    jcc.getUser_name(), jcc.getPass_word());

        } catch (ClassNotFoundException e) {
            throw new DBException(DBError.DB_0000, jcc.getJdbc_driver(), e);

        } catch (SQLException e) {
            throw new DBException(DBError.DB_0001, e);
        }
    }

    public JDBCExecutor(String driver, String url, String username,
                        String password) {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {
            throw new DBException(DBError.DB_0000, driver, e);

        } catch (SQLException e) {
            throw new DBException(DBError.DB_0001, e);
        }
    }

    public JDBCExecutor(String driver, String url, Properties p) {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, p);

        } catch (ClassNotFoundException e) {
            throw new DBException(DBError.DB_0000, driver, e);

        } catch (SQLException e) {
            throw new DBException(DBError.DB_0001, e);
        }
    }

    public ResultSet executeQuery(String sql) {
        Statement statement = null;
        try {
            statement = connection.createStatement(
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            return statement.executeQuery(sql);

        } catch (SQLException e) {
            throw new DBException(DBError.DB_0002, e);
        } finally {
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new DBException(DBError.DB_0002, e);
                }
            }
        }
    }

    public void setAutoCommit(boolean autoCommit) {
        try {
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            throw new DBException(DBError.DB_0002, e);
        }
    }

    public void deleteTableData(String tableName) {
        logger.info("Deleting all the rows from: " + tableName);
        executeUpdate("DELETE FROM " + tableName);
    }

    public void migrateData(String fromTable, String toTable) {
        String insertQuery = "INSERT INTO " + toTable + " ( SELECT * FROM "
                + fromTable + " )";
        Statement stmt = null;
        Boolean oldAutoCommit = null;
        try {
            final long expectedInsertCount = getTableRowCount(fromTable);
            oldAutoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            final int actualInsertCount = stmt.executeUpdate(insertQuery);
            if (expectedInsertCount == actualInsertCount) {
                logger.info("Transferred " + actualInsertCount
                        + " rows of staged data " + "from: " + fromTable
                        + " to: " + toTable);
                connection.commit();
                deleteTableData(fromTable);
                connection.commit();
            } else {
                logger.error("Rolling back as number of rows inserted into table: "
                        + toTable
                        + " was: "
                        + actualInsertCount
                        + " expected: " + expectedInsertCount);
                connection.rollback();
                throw new DBException(DBError.DB_0018);
            }
        } catch (SQLException e) {
            logger.error("Got SQLException while migrating data from: "
                    + fromTable + " to: " + toTable, e);
            throw new DBException(DBError.DB_0018, e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    logger.warn(
                            "Got SQLException at the time of closing statement.",
                            e);
                }
            }
            if (oldAutoCommit != null) {
                try {
                    connection.setAutoCommit(oldAutoCommit);
                } catch (SQLException e) {
                    logger.warn("Got SQLException while setting autoCommit mode.",
                            e);
                }
            }
        }
    }

    public long getTableRowCount(String tableName) {
        ResultSet resultSet = null;
        try {
            resultSet = executeQuery("SELECT COUNT(1) FROM " + tableName);
            if (null != resultSet && resultSet.next()) {
                return resultSet.getLong(1);
            } else
                return 0;
        } catch (SQLException e) {
            throw new DBException(DBError.DB_0004, e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                if (logger.isWarnEnabled())
                    logger.warn("Got SQLException while closing resultset.", e);
            }
        }
    }

    public void executeUpdate(String sql) {
        Statement statement = null;
        try {
            statement = connection.createStatement(
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DBException(DBError.DB_0002, e);
        } finally {
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new DBException(DBError.DB_0002, e);
                }
            }
        }
    }

    public void beginBatch(String sql) {
        try {
            preparedStatement = connection.prepareStatement(sql,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            throw new DBException(DBError.DB_0002, e);
        }
    }

    public void addBatch(Object[] array) {
        try {
            for (int i = 0; i < array.length; i++) {
                preparedStatement.setObject(i + 1, array[i]);
            }
            preparedStatement.addBatch();
        } catch (SQLException e) {
            throw new DBException(DBError.DB_0002, e);
        }
    }

    public void executeBatch(boolean commit) {
        try {
            preparedStatement.executeBatch();
            if (commit) {
                connection.commit();
            }
        } catch (SQLException e) {
            throw new DBException(DBError.DB_0002, e);
        }
    }

    public void endBatch() {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            throw new DBException(DBError.DB_0002, e);
        }
    }

    public String getPrimaryKey(String table) {
        ResultSet rs = null;
        try {
            String[] splitNames = dequalify(table);
            DatabaseMetaData dbmd = connection.getMetaData();
            rs = dbmd.getPrimaryKeys(null, splitNames[0],
                    splitNames[1]);

            if (rs != null && rs.next()) {
                return rs.getString("COLUMN_NAME");
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new DBException(DBError.DB_0003, e);
        } finally {
            if (null != rs) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DBException(DBError.DB_0003, e);
                }
            }
        }
    }

    public String[] getQueryColumns(String query) {
        try (Statement statement = connection.createStatement(
                ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = statement.executeQuery(query)) {
            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();
            String[] columns = new String[count];
            for (int i = 0; i < count; i++) {
                columns[i] = rsmd.getColumnName(i + 1);
            }
            return columns;
        } catch (SQLException e) {
            throw new DBException(DBError.DB_0003, e);
        }
    }

    public boolean existTable(String table) {
        try {
            String[] splitNames = dequalify(table);

            DatabaseMetaData dbmd = connection.getMetaData();
            ResultSet rs = dbmd.getTables(null, splitNames[0], splitNames[1],
                    null);

            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new DBException(DBError.DB_0003, e);
        }
    }

    /*
     * If not qualified already, the name will be added with the qualifier. If
     * qualified already, old qualifier will be replaced.
     */
    public String qualify(String name, String qualifier) {
        String[] splits = dequalify(name);
        return qualifier + "." + splits[1];
    }

    /*
     * Split the name into a qualifier (element 0) and a base (element 1).
     */
    public String[] dequalify(String name) {
        String qualifier;
        String base;
        int dot = name.indexOf(".");
        if (dot != -1) {
            qualifier = name.substring(0, dot);
            base = name.substring(dot + 1);
        } else {
            qualifier = null;
            base = name;
        }
        return new String[]{qualifier, base};
    }

    public String delimitIdentifier(String name) {
        return name;
    }

    public void close() {
        try {
            connection.close();

        } catch (SQLException e) {
            // TODO: logger the exception
        }
    }

}
