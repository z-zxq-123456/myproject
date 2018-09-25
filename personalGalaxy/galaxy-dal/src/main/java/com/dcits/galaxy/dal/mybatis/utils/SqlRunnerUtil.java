package com.dcits.galaxy.dal.mybatis.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.jdbc.SqlRunner;

import com.dcits.galaxy.dal.mybatis.exception.DALException;
import com.dcits.galaxy.dal.mybatis.shard.Shard;
import com.dcits.galaxy.dal.mybatis.transaction.DataSourceUtils;

public class SqlRunnerUtil {
	
	public static Map<String, Object> selectOne(String sql, Object[] args, Shard shard) {
		DataSource dataSource = shard.getDataSource();
		Connection connection = DataSourceUtils.getConnection(dataSource);
		SqlRunner sqlRunner = new SqlRunner(connection);
		Map<String, Object> rs = null;
		try {
			rs = sqlRunner.selectOne(sql, args);
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			DataSourceUtils.releaseConnection(connection, dataSource);
		}
		return rs;
	}

	public static List<Map<String, Object>> selectAll(String sql, Object[] args, Shard shard) {
		DataSource dataSource = shard.getDataSource();
		Connection connection = DataSourceUtils.getConnection(dataSource);
		SqlRunner sqlRunner = new SqlRunner(connection);
		List<Map<String, Object>> rs = null;
		try {
			rs = sqlRunner.selectAll(sql, args);
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			DataSourceUtils.releaseConnection(connection, dataSource);
		}
		return rs;
	}
	
	public static int insert(String sql, Object[] args, Shard shard) {
		DataSource dataSource = shard.getDataSource();
		Connection connection = DataSourceUtils.getConnection(dataSource);
		SqlRunner sqlRunner = new SqlRunner(connection);
		int rs = 0;
		try {
			rs = sqlRunner.insert(sql, args);
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			DataSourceUtils.releaseConnection(connection, dataSource);
		}
		return rs;
	}
	
	public static int update(String sql, Object[] args, Shard shard) {
		DataSource dataSource = shard.getDataSource();
		Connection connection = DataSourceUtils.getConnection(dataSource);
		SqlRunner sqlRunner = new SqlRunner(connection);
		int rs = 0;
		try {
			rs = sqlRunner.update(sql, args);
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			DataSourceUtils.releaseConnection(connection, dataSource);
		}
		return rs;
	}

	public static int delete(String sql, Object[] args, Shard shard) {
		DataSource dataSource = shard.getDataSource();
		Connection connection = DataSourceUtils.getConnection(dataSource);
		SqlRunner sqlRunner = new SqlRunner(connection);
		int rs = 0;
		try {
			rs = sqlRunner.delete(sql, args);
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			DataSourceUtils.releaseConnection(connection, dataSource);
		}
		return rs;
	}

	public static void run(String sql, Shard shard) {
		DataSource dataSource = shard.getDataSource();
		Connection connection = DataSourceUtils.getConnection(dataSource);
		SqlRunner sqlRunner = new SqlRunner(connection);
		try {
			sqlRunner.run(sql);
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			DataSourceUtils.releaseConnection(connection, dataSource);
		}
	}
	
}
