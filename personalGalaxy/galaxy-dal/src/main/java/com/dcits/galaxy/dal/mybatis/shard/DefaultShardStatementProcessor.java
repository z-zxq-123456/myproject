package com.dcits.galaxy.dal.mybatis.shard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.sql.DataSource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.ConcurrencyFailureException;

import com.dcits.galaxy.base.util.CollectionUtils;
import com.dcits.galaxy.dal.mybatis.SqlSessionCallBack;
import com.dcits.galaxy.dal.mybatis.utils.SqlSessionUtils;

public class DefaultShardStatementProcessor implements ShardStatementProcessor {

	private static final Logger logger = LoggerFactory.getLogger(DefaultShardStatementProcessor.class);

	private SqlSessionFactory sqlSessionFactory;

	public DefaultShardStatementProcessor() {
	}

	public DefaultShardStatementProcessor(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public List<ShardResultSet> process(List<ShardStatement> shardStatements) {
		List<ShardResultSet> resultList = new ArrayList<ShardResultSet>();

		if (CollectionUtils.isEmpty(shardStatements))
			return resultList;
		
		if (shardStatements.size() == 1) {
			ShardStatement request = shardStatements.get(0);
			final String dataSourceId = request.getShardId();
			final SqlSessionCallBack action = request.getAction();
			final DataSource dataSource = request.getDataSource();
			final ExecutorType type = request.getExecutorType();
			final SqlSession session = SqlSessionUtils.getSession(sqlSessionFactory, type, dataSource);
			List<ShardResultSet> list = new ArrayList<>();
			try{
				list.add(executeWith(session, action, dataSourceId));
			} finally {
				try {
					SqlSessionUtils.closeSqlSession(session, dataSource, sqlSessionFactory);
				} catch (Throwable ex) {
					if(logger.isInfoEnabled())
					logger.info("Could not close SqlSession:", session);
				}
			}
			return list;

		}

		Map<DataSource, SqlSession> sessions = new HashMap<>();

		final CountDownLatch latch = new CountDownLatch(shardStatements.size());
		List<Future<ShardResultSet>> futures = new ArrayList<Future<ShardResultSet>>();
		try {

			for(final ShardStatement request : shardStatements){
				final String dataSourceId = request.getShardId();
				final SqlSessionCallBack action = request.getAction();
				final DataSource dataSource = request.getDataSource();
				final ExecutorType type = request.getExecutorType();
				final SqlSession session = SqlSessionUtils.getSession(sqlSessionFactory, type, dataSource);
				sessions.put(dataSource, session);
				
				futures.add(request.getExecutor().submit(
						new Callable<ShardResultSet>() {
							@Override
							public ShardResultSet call() throws Exception {
								try {
									return executeWith(session, action, dataSourceId);
								} finally {
									latch.countDown();
								}
							}
						}));
			}

			try {
				latch.await();
			} catch (InterruptedException e) {
				throw new ConcurrencyFailureException(
						"interrupted when processing data access request in concurrency",
						e);
			}

		} finally {
			for (Entry<DataSource, SqlSession> entry : sessions.entrySet()) {
				DataSource dataSource = entry.getKey();
				SqlSession session = entry.getValue();
				try {
					SqlSessionUtils.closeSqlSession(session, dataSource, sqlSessionFactory);
				} catch (Throwable ex) {
					if(logger.isInfoEnabled())
					logger.info("Could not close SqlSession:", session);
				}
			}
		}

		fillResultListWithFutureResults(futures, resultList);

		return resultList;
	}

	@SuppressWarnings("unchecked")
	protected ShardResultSet executeWith(SqlSession session,SqlSessionCallBack action, String dataSourceId) {
		ShardResultSet shardResultSet = new ShardResultSet(dataSourceId);
		Object result = action.execute(session);
		if (result != null && !(result instanceof List)) {
			List<Object> resultList = new ArrayList<Object>();
			resultList.add(result);
			shardResultSet.setResult(resultList);
		} else if (result != null && (result instanceof List)) {
			shardResultSet.setResult((List<Object>) result);
		}
		return shardResultSet;
	}

	private void fillResultListWithFutureResults(List<Future<ShardResultSet>> futures, List<ShardResultSet> resultList) {
		for (Future<ShardResultSet> future : futures) {
			try {
				resultList.add(future.get());
			} catch (InterruptedException e) {
				throw new ConcurrencyFailureException(
						"interrupted when processing data access request in concurrency",e);
			} catch (ExecutionException e) {
				throw new ConcurrencyFailureException("something goes wrong in processing", e);
			}
		}
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

}
