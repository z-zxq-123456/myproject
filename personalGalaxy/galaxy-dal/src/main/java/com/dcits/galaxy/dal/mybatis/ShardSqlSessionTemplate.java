package com.dcits.galaxy.dal.mybatis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.dcits.galaxy.base.util.CollectionUtils;
import com.dcits.galaxy.core.threadpool.support.NamedThreadFactory;
import com.dcits.galaxy.dal.mybatis.exception.NoShardFoundException;
import com.dcits.galaxy.dal.mybatis.extension.MapperExtensionLoader;
import com.dcits.galaxy.dal.mybatis.extension.StatementExecutor;
import com.dcits.galaxy.dal.mybatis.extension.StatementHandler;
import com.dcits.galaxy.dal.mybatis.idempotent.IdempotentContext;
import com.dcits.galaxy.dal.mybatis.idempotent.IdempotentUtil;
import com.dcits.galaxy.dal.mybatis.merger.MergeExecutor;
import com.dcits.galaxy.dal.mybatis.merger.config.vo.MergeRule;
import com.dcits.galaxy.dal.mybatis.proactor.ProactorExecutor;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;
import com.dcits.galaxy.dal.mybatis.router.IRouter;
import com.dcits.galaxy.dal.mybatis.shard.DefaultShardStatementProcessor;
import com.dcits.galaxy.dal.mybatis.shard.Shard;
import com.dcits.galaxy.dal.mybatis.shard.ShardManager;
import com.dcits.galaxy.dal.mybatis.shard.ShardParameter;
import com.dcits.galaxy.dal.mybatis.shard.ShardResultSet;
import com.dcits.galaxy.dal.mybatis.shard.ShardStatement;
import com.dcits.galaxy.dal.mybatis.shard.ShardStatementProcessor;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ShardSqlSessionTemplate {

    private static Logger logger = LoggerFactory.getLogger(ShardSqlSessionTemplate.class);

    private SqlSessionFactory sqlSessionFactory;

    private ShardManager shardManager;

    private IRouter router;

    private ExecutorService dataSourceExecutor = null;
    
    private MergeExecutor mergeExecutor = null;

    private ProactorExecutor proactorExecutor = null;

    private IdempotentUtil idempotentUtil = null;
    
	private MapperExtensionLoader extensionLoader = null;
	private String extesionLocations = null;

    private ShardStatementProcessor processor = null;

    private DefaultShardPolicy defaultShardPolicy = DefaultShardPolicy.ROUTE_NOSHARD_DEFAULT;

    private int poolSize = Runtime.getRuntime().availableProcessors()*5;

    private static enum DefaultShardPolicy {
        ROUTE_NOSHARD_ERROR,
        ROUTE_NOSHARD_DEFAULT
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public Configuration getConfiguration() {
        return sqlSessionFactory.getConfiguration();
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public void setShardManager(ShardManager shardManager) {
        this.shardManager = shardManager;
    }

    public IRouter getRouter() {
        return router;
    }

    public void setRouter(IRouter router) {
        this.router = router;
    }

    public void setMergeExecutor(MergeExecutor mergeExecutor) {
        this.mergeExecutor = mergeExecutor;
    }
    public ProactorExecutor getProactorExecutor() {
        return proactorExecutor;
    }

    public void setProactorExecutor(ProactorExecutor proactorExecutor) {
        this.proactorExecutor = proactorExecutor;
    }

    public IdempotentUtil getIdempotentUtil() {
        return idempotentUtil;
    }

    public void setIdempotentUtil(IdempotentUtil idempotentUtil) {
        this.idempotentUtil = idempotentUtil;
    }

    public ExecutorService getDataSourceExecutor() {
		return dataSourceExecutor;
	}

	public void setDataSourceExecutor(ExecutorService dataSourceExecutor) {
		this.dataSourceExecutor = dataSourceExecutor;
	}

	public int getPoolSize() {
		return poolSize;
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}
    public DefaultShardPolicy getDefaultShardPolicy() {
        return defaultShardPolicy;
    }

    public void setDefaultShardPolicy(String defaultShardPolicy) {
        this.defaultShardPolicy = DefaultShardPolicy.valueOf(defaultShardPolicy.toUpperCase());
    }

    public boolean isPartitioning() {
        return router != null;
    }

    public boolean isOpenIdempotentService() {
        return idempotentUtil != null;
    }

	public void setExtesionLocations(String extesionLocations) {
		this.extesionLocations = extesionLocations;
	}
	
	public MergeExecutor getMergeExecutor() {
		return mergeExecutor;
	}

	public int insert(String statement) {
		return this.insert(statement, new Object());
	}

	public int insert(String namespace, String sqlId) {
		String statement = namespace + "." + sqlId;
		return this.insert(statement, new Object());
	}

	public int insert(String namespace, String sqlId, Object parameter) {
		String statement = namespace + "." + sqlId;
		return this.insert(statement, parameter);
	}

	public int insert(final String statement, final Object parameter) {
		return this.insert(statement, parameter, true);
	}
	
	public int insert(final String statement, final Object parameter, boolean applyExtention) {
		
		if(applyExtention){
			StatementExecutor handle = getExecutorForStatement(statement);
			if(handle != null) {
				return (int) handle.execute(this, statement, parameter);
			}
		}
		
		List<Shard> resultShards = lookupDataSourcesByRouter(statement, parameter);
		resultShards = existAndSave(resultShards);
		if (resultShards.isEmpty()) {
			return Integer.MAX_VALUE;
		}
		SqlSessionCallBack action = new SqlSessionCallBack() {
			@Override
			public Object execute(SqlSession sqlSession) {
				return sqlSession.insert(statement, parameter);
			}
		};
		List<ShardResultSet> originalResultList = execute(action, resultShards);
		List<Object> tempResultList = new ArrayList<Object>();
		for (ShardResultSet item : originalResultList) {
			List<Object> result = item.getResult();
			if (result != null) {
				tempResultList.addAll(result);
			}
		}
		return this.mergeNumber(tempResultList);
	}

	/**
	 * 批量插入 Mapper中需配置成foreach形式
	 * @param namespace
	 * @param sqlId
	 * @param paramters
	 * @return
	 */
	public int insertBatch(String namespace, String sqlId, List<?> paramters) {
		String statement = namespace + "." + sqlId;
		return this.insertBatch(statement, paramters);
	}

	/**
	 * 批量插入 Mapper中需配置成foreach形式
	 * @param namespace
	 * @param sqlId
	 * @param paramters
	 * @return
	 */
	public int insertBatch(final String statement, List<?> parameters) {
		return this.insertBatch(statement, parameters, true);
	}
	
	public int insertBatch(final String statement, List<?> parameters, boolean applyExtention) {
		
		if(applyExtention){
			StatementExecutor handle = getExecutorForStatement(statement);
			if(handle != null) {
				return (int) handle.execute(this, statement, parameters);
			}
		}
		
		Map<Shard, List<Object>> parameterMap = new HashMap<>();
		for (Object parameter : parameters) {
			List<Shard> resultShards = lookupDataSourcesByRouter(statement, parameter);
			for (Shard shard : resultShards) {
				List<Object> list = parameterMap.get(shard);
				if (list == null) {
					list = new ArrayList<>();
					parameterMap.put(shard, list);
				}
				list.add(parameter);
			}

		}
		List<Shard> resultShards = new ArrayList<Shard>(parameterMap.keySet());
		resultShards = existAndSave(resultShards);
		if (resultShards.isEmpty()) {
			return Integer.MAX_VALUE;
		}
		Map<Shard, SqlSessionCallBack> actionMap = new HashMap<>();
		for (Shard shard : resultShards) {
			final Object parameter = parameterMap.get(shard);
			SqlSessionCallBack action = new SqlSessionCallBack() {
				@Override
				public Object execute(SqlSession sqlSession) {
					return sqlSession.insert(statement, parameter);
				}
			};
			actionMap.put(shard, action);
		}
		List<ShardResultSet> originalResultList = execute(actionMap, ExecutorType.SIMPLE);
		List<Object> tempResultList = new ArrayList<Object>();
		for (ShardResultSet item : originalResultList) {
			List<Object> result = item.getResult();
			if (result != null) {
				tempResultList.addAll(result);
			}
		}
		return this.mergeNumber(tempResultList);
	}

	/**
	 * 批量插入 通过addBatch方法批量提交
	 * @param namespace
	 * @param sqlId
	 * @param paramters
	 * @return
	 */
	public int insertAddBatch(String namespace, String sqlId, List<?> paramters) {
		String statement = namespace + "." + sqlId;
		return this.insertAddBatch(statement, paramters);
	}

	/**
	 * 批量插入 通过addBatch方式批量提交
	 * @param statement
	 * @param parameters
	 * @return
	 */
	public int insertAddBatch(final String statement, List<?> parameters) {
		return insertAddBatch(statement, parameters, true);
	}
	
	public int insertAddBatch(final String statement, List<?> parameters, boolean applyExtention) {
		
		if(applyExtention){
			StatementExecutor handle = getExecutorForStatement(statement);
			if(handle != null) {
				return (int) handle.execute(this, statement, parameters);
			}
		}
		
		Map<Shard, List<Object>> parameterMap = lookupShardsWithList(statement, parameters);
		List<Shard> resultShards = new ArrayList<Shard>(parameterMap.keySet());
		resultShards = existAndSave(resultShards);
		if (resultShards.isEmpty()) {
			return Integer.MAX_VALUE;
		}
		Map<Shard, SqlSessionCallBack> actionMap = new HashMap<>();
		for (Shard shard : resultShards) {
			final List<Object> params = parameterMap.get(shard);
			SqlSessionCallBack action = new SqlSessionCallBack() {
				@Override
				public Object execute(SqlSession sqlSession) {
					for (Object param : params) {
						sqlSession.insert(statement, param);
					}
					return handelBatchResult(sqlSession.flushStatements());
				}
			};

			actionMap.put(shard, action);
		}
		List<ShardResultSet> originalResultList = execute(actionMap, ExecutorType.BATCH);
		List<Object> tempResultList = new ArrayList<Object>();
		for (ShardResultSet item : originalResultList) {
			List<Object> result = item.getResult();
			if (result != null) {
				tempResultList.addAll(result);
			}
		}
		return this.mergeNumber(tempResultList);
	}
	
	/**
	 * 指定单个Shard的插入方法 可通过Mapper中配置foreach循环实现单库批量插入
	 * @param statement
	 * @param parameter
	 * @param shard
	 * @return
	 */
	public int insert(final String statement, final Object parameter, Shard shard) {
		return insert(statement, parameter, shard, true);
	}
	
	public int insert(final String statement, final Object parameter, Shard shard, boolean applyExtention) {
		
		List<Shard> shardList = new ArrayList<Shard>();
		shardList.add(shard);
		List<ShardResultSet> shardResultSet = insert(statement, parameter, shardList,applyExtention);
		if (shardResultSet.isEmpty()) {
			return 0;
		}
		return (int) shardResultSet.get(0).getResult().get(0);
	}

	/**
	 * 指定多个Shard的插入方法 可通过Mapper中配置foreach循环实现所有批量插入
	 * @param statement
	 * @param parameter
	 * @param shardList
	 * @return
	 */
	public List<ShardResultSet> insert(final String statement, final Object parameter, List<Shard> shardList) {
		return insert(statement, parameter, shardList, true);
	}
	
	public List<ShardResultSet> insert(final String statement, final Object parameter, List<Shard> shardList,boolean applyExtention) {
		shardList = existAndSave(shardList);
		if (shardList.isEmpty()) {
			return Collections.emptyList();
		}
		if(applyExtention){
			StatementExecutor handle = getExecutorForStatement(statement);
			if(handle != null) {
				return handle.execute(this, statement, parameter,shardList);
			}
		}
		
		List<ShardResultSet> originalResultList = new ArrayList<ShardResultSet>();
		if (CollectionUtils.isNotEmpty(shardList)) {
			SqlSessionCallBack action = new SqlSessionCallBack() {
				@Override
				public Object execute(SqlSession sqlSession) {
					return sqlSession.insert(statement, parameter);
				}
			};
			originalResultList = execute(action, shardList);
		}
		return originalResultList;
	}

	/**
	 * 指定单个shard的批量插入 通过addBatch方式提交
	 * @param statement
	 * @param params
	 * @param shard
	 * @return
	 */
	public int insertAddBatch(final String statement, final List<Object> params, Shard shard) {
		return this.insertAddBatch(statement, params, shard, true);
	}
	
	public int insertAddBatch(final String statement, final List<Object> params, Shard shard,boolean applyExtention) {
		List<Shard> shardList = new ArrayList<Shard>();
		shardList.add(shard);
		List<ShardResultSet> shardResultSet = insertAddBatch(statement, params, shardList,applyExtention);
		if (shardResultSet.isEmpty()) {
			return 0;
		}
		return (int) shardResultSet.get(0).getResult().get(0);
	}

	/**
	 * 指定多个shard的批量插入 通过addBatch方式提交
	 * @param statement
	 * @param params
	 * @param shardList
	 * @return
	 */
	public List<ShardResultSet> insertAddBatch(final String statement, final List<Object> params, List<Shard> shardList) {
		return this.insertAddBatch(statement, params, shardList, true);
	}
	
	public List<ShardResultSet> insertAddBatch(final String statement, final List<Object> params, List<Shard> shardList,boolean applyExtention) {
		shardList = existAndSave(shardList);
		if (shardList.isEmpty()) {
			return Collections.emptyList();
		}
		if(applyExtention){
			StatementExecutor handle = getExecutorForStatement(statement);
			if(handle != null) {
				return handle.execute(this, statement, params,shardList);
			}
		}
		List<ShardResultSet> originalResultList = new ArrayList<ShardResultSet>();
		if (CollectionUtils.isNotEmpty(shardList)) {
			SqlSessionCallBack action = new SqlSessionCallBack() {
				@Override
				public Object execute(SqlSession sqlSession) {
					for (Object param : params) {
						sqlSession.insert(statement, param);
					}
					return handelBatchResult(sqlSession.flushStatements());
				}
			};
			originalResultList = execute(action, shardList, ExecutorType.BATCH);
		}
		return originalResultList;
	}

	public int delete(String statement) {
		return this.delete(statement, new Object());
	}

	public int delete(String namespace, String sqlId) {
		String statement = namespace + "." + sqlId;
		return this.delete(statement, new Object());
	}

	public int delete(String namespace, String sqlId, Object parameter) {
		String statement = namespace + "." + sqlId;
		return this.delete(statement, parameter);
	}

	public int delete(final String statement, final Object parameter) {
		return delete(statement,parameter,true);
	}
	
	public int delete(final String statement, final Object parameter,boolean applyExtention) {
		if(applyExtention){
			StatementExecutor handle = getExecutorForStatement(statement);
			if(handle != null) {
				return (int) handle.execute(this, statement, parameter);
			}
		}
		
		List<Shard> resultShards = lookupDataSourcesByRouter(statement, parameter);
		resultShards = existAndSave(resultShards);
		if (resultShards.isEmpty()) {
			return Integer.MAX_VALUE;
		}
		SqlSessionCallBack action = new SqlSessionCallBack() {
			@Override
			public Object execute(SqlSession sqlSession) {
				return sqlSession.delete(statement, parameter);
			}
		};
		List<ShardResultSet> originalResultList = execute(action, resultShards);
		List<Object> tempResultList = new ArrayList<Object>();
		for (ShardResultSet item : originalResultList) {
			List<Object> result = item.getResult();
			if (result != null) {
				tempResultList.addAll(result);
			}
		}
		return this.mergeNumber(tempResultList);
	}

	/**
	 * 批量删除 通过addBatch方式提交
	 * @param namespace
	 * @param sqlId
	 * @param paramters
	 * @return
	 */
	public int deleteAddBatch(String namespace, String sqlId, List<?> paramters) {
		String statement = namespace + "." + sqlId;
		return this.deleteAddBatch(statement, paramters);
	}

	/**
	 * 批量删除 通过addBatch方式提交
	 * @param statement
	 * @param parameters
	 * @return
	 */
	public int deleteAddBatch(final String statement, List<?> parameters) {
		return deleteAddBatch(statement, parameters, true);
	}
	
	public int deleteAddBatch(final String statement, List<?> parameters,boolean applyExtention) {
		if(applyExtention){
			StatementExecutor handle = getExecutorForStatement(statement);
			if(handle != null) {
				return (int) handle.execute(this, statement, parameters);
			}
		}
		
		Map<Shard, List<Object>> parameterMap = lookupShardsWithList(statement, parameters);
		List<Shard> resultShards = new ArrayList<Shard>(parameterMap.keySet());
		resultShards = existAndSave(resultShards);
		if (resultShards.isEmpty()) {
			return Integer.MAX_VALUE;
		}
		Map<Shard, SqlSessionCallBack> actionMap = new HashMap<>();
		for (Shard shard : resultShards) {
			final List<Object> params = parameterMap.get(shard);
			SqlSessionCallBack action = new SqlSessionCallBack() {
				@Override
				public Object execute(SqlSession sqlSession) {
					for (Object param : params) {
						sqlSession.delete(statement, param);
					}
					return handelBatchResult(sqlSession.flushStatements());
				}
			};
			actionMap.put(shard, action);
		}
		List<ShardResultSet> originalResultList = execute(actionMap, ExecutorType.BATCH);
		List<Object> tempResultList = new ArrayList<Object>();
		for (ShardResultSet item : originalResultList) {
			List<Object> result = item.getResult();
			if (result != null) {
				tempResultList.addAll(result);
			}
		}
		return this.mergeNumber(tempResultList);
	}

	public int delete(final String statement, final Object parameter, Shard shard) {
		return delete(statement,parameter,shard,true);
	}
	
	public int delete(final String statement, final Object parameter, Shard shard,boolean applyExtention) {
		List<Shard> shardList = new ArrayList<Shard>();
		shardList.add(shard);
		List<ShardResultSet> shardResultSet = delete(statement, parameter, shardList,applyExtention);
		if (shardResultSet.isEmpty()) {
			return 0;
		}
		return (int) shardResultSet.get(0).getResult().get(0);
	}

	public List<ShardResultSet> delete(final String statement, final Object parameter, List<Shard> shardList) {
		return delete(statement, parameter, shardList, true);
	}
	
	public List<ShardResultSet> delete(final String statement, final Object parameter, List<Shard> shardList,boolean applyExtention) {
		shardList = existAndSave(shardList);
		if (shardList.isEmpty()) {
			return Collections.emptyList();
		}
		if(applyExtention){
			StatementExecutor handle = getExecutorForStatement(statement);
			if(handle != null) {
				return handle.execute(this, statement, parameter,shardList);
			}
		}
		List<ShardResultSet> originalResultList = new ArrayList<ShardResultSet>();
		if (CollectionUtils.isNotEmpty(shardList)) {
			SqlSessionCallBack action = new SqlSessionCallBack() {
				@Override
				public Object execute(SqlSession sqlSession) {
					return sqlSession.delete(statement, parameter);
				}
			};
			originalResultList = execute(action, shardList);
		}
		return originalResultList;
	}

	/**
	 * 指定单个shard的批量删除 通过addBatch方式提交
	 * @param statement
	 * @param params
	 * @param shard
	 * @return
	 */
	public int deleteAddBatch(final String statement, final List<Object> params, Shard shard) {
		return deleteAddBatch(statement, params, shard, true);
	}
	
	public int deleteAddBatch(final String statement, final List<Object> params, Shard shard, boolean applyExtention) {
		List<Shard> shardList = new ArrayList<Shard>();
		shardList.add(shard);
		List<ShardResultSet> shardResultSet = deleteAddBatch(statement, params, shardList, applyExtention);
		if (shardResultSet.isEmpty()) {
			return 0;
		}
		return (int) shardResultSet.get(0).getResult().get(0);
	}

	/**
	 * 指定多个shard的批量删除 通过addBatch方式提交
	 * @param statement
	 * @param params
	 * @param shardList
	 * @return
	 */
	public List<ShardResultSet> deleteAddBatch(final String statement, final List<Object> params, List<Shard> shardList) {
		return deleteAddBatch(statement, params, shardList, true);
	}
	
	public List<ShardResultSet> deleteAddBatch(final String statement, final List<Object> params, List<Shard> shardList,boolean applyExtention) {
		shardList = existAndSave(shardList);
		if (shardList.isEmpty()) {
			return Collections.emptyList();
		}
		if(applyExtention){
			StatementExecutor handle = getExecutorForStatement(statement);
			if(handle != null) {
				return handle.execute(this, statement, params,shardList);
			}
		}
		List<ShardResultSet> originalResultList = new ArrayList<ShardResultSet>();
		if (CollectionUtils.isNotEmpty(shardList)) {
			SqlSessionCallBack action = new SqlSessionCallBack() {
				@Override
				public Object execute(SqlSession sqlSession) {
					for (Object param : params) {
						sqlSession.delete(statement, param);
					}
					return handelBatchResult(sqlSession.flushStatements());
				}
			};
			originalResultList = execute(action, shardList, ExecutorType.BATCH);
		}
		return originalResultList;
	}

	public int update(String statement) {
		return this.update(statement, new Object());
	}

	public int update(String namespace, String sqlId) {
		String statement = namespace + "." + sqlId;
		return this.update(statement, new Object());
	}

	public int update(String namespace, String sqlId, Object parameter) {
		String statement = namespace + "." + sqlId;
		return this.update(statement, parameter);
	}

	public int update(final String statement, final Object parameter) {
		return update(statement, parameter, true);
	}
	
	public int update(final String statement, final Object parameter,boolean applyExtention) {
		if(applyExtention){
			StatementExecutor handle = getExecutorForStatement(statement);
			if(handle != null) {
				return (int) handle.execute(this, statement, parameter);
			}
		}
		
		List<Shard> resultShards = lookupDataSourcesByRouter(statement, parameter);
		resultShards = existAndSave(resultShards);
		if (resultShards.isEmpty()) {
			return Integer.MAX_VALUE;
		}
		SqlSessionCallBack action = new SqlSessionCallBack() {
			@Override
			public Object execute(SqlSession sqlSession) {
				return sqlSession.update(statement, parameter);
			}
		};
		List<ShardResultSet> originalResultList = execute(action, resultShards);
		List<Object> tempResultList = new ArrayList<Object>();
		for (ShardResultSet item : originalResultList) {
			List<Object> result = item.getResult();
			if (result != null) {
				tempResultList.addAll(result);
			}
		}
		return this.mergeNumber(tempResultList);
	}

	/**
	 * 批量更新 通过addBatch方式提交
	 * @param namespace
	 * @param sqlId
	 * @param paramters
	 * @return
	 */
	public int updateAddBatch(String namespace, String sqlId, List<?> paramters) {
		String statement = namespace + "." + sqlId;
		return this.updateAddBatch(statement, paramters);
	}

	/**
	 * 批量更新 通过addBatch方式提交
	 * @param namespace
	 * @param paramters
	 * @return
	 */
	public int updateAddBatch(final String statement, List<?> parameters) {
		return updateAddBatch(statement, parameters, true);
	}
	
	public int updateAddBatch(final String statement, List<?> parameters,boolean applyExtention) {
		if(applyExtention){
			StatementExecutor handle = getExecutorForStatement(statement);
			if(handle != null) {
				return (int) handle.execute(this, statement, parameters);
			}
		}
		
		Map<Shard, List<Object>> parameterMap = lookupShardsWithList(statement, parameters);
		List<Shard> resultShards = new ArrayList<Shard>(parameterMap.keySet());
		resultShards = existAndSave(resultShards);
		if (resultShards.isEmpty()) {
			return Integer.MAX_VALUE;
		}
		Map<Shard, SqlSessionCallBack> actionMap = new HashMap<>();
		for (Shard shard : resultShards) {
			final List<Object> params = parameterMap.get(shard);
			SqlSessionCallBack action = new SqlSessionCallBack() {
				@Override
				public Object execute(SqlSession sqlSession) {
					for (Object param : params) {
						sqlSession.update(statement, param);
					}
					return handelBatchResult(sqlSession.flushStatements());
				}
			};
			actionMap.put(shard, action);
		}
		List<ShardResultSet> originalResultList = execute(actionMap, ExecutorType.BATCH);
		List<Object> tempResultList = new ArrayList<Object>();
		for (ShardResultSet item : originalResultList) {
			List<Object> result = item.getResult();
			if (result != null) {
				tempResultList.addAll(result);
			}
		}
		return this.mergeNumber(tempResultList);
	}

	public int update(final String statement, final Object parameter, Shard shard) {
		return update(statement, parameter,shard, true);
	}
	
	public int update(final String statement, final Object parameter, Shard shard,boolean applyExtention) {
		List<Shard> shardList = new ArrayList<Shard>();
		shardList.add(shard);
		List<ShardResultSet> shardResultSet = update(statement, parameter, shardList,applyExtention);
		if (shardResultSet.isEmpty()) {
			return 0;
		}
		return (int) shardResultSet.get(0).getResult().get(0);
	}

	public List<ShardResultSet> update(final String statement, final Object parameter, List<Shard> shardList) {
		return update(statement, parameter, shardList, true);
	}
	
	public List<ShardResultSet> update(final String statement, final Object parameter, List<Shard> shardList,boolean applyExtention) {
		shardList = existAndSave(shardList);
		if (shardList.isEmpty()) {
			return Collections.emptyList();
		}
		if(applyExtention){
			StatementExecutor handle = getExecutorForStatement(statement);
			if(handle != null) {
				return handle.execute(this, statement, parameter, shardList);
			}
		}
		
		List<ShardResultSet> originalResultList = new ArrayList<ShardResultSet>();
		if (CollectionUtils.isNotEmpty(shardList)) {
			SqlSessionCallBack action = new SqlSessionCallBack() {
				@Override
				public Object execute(SqlSession sqlSession) {
					return sqlSession.update(statement, parameter);
				}
			};
			originalResultList = execute(action, shardList);
		}
		return originalResultList;
	}

	/**
	 * 指定单个shard的批量更新 通过addBatch方式提交
	 * @param namespace 命名空间
	 * @param sqlId SQLID
	 * @param paramters 参数
	 * @return 更新记录数
	 */
	public int updateAddBatch(final String statement, final List<Object> params, Shard shard) {
		return updateAddBatch(statement, params, shard, true);
	}
	
	public int updateAddBatch(final String statement, final List<Object> params, Shard shard,boolean applyExtention) {
		List<Shard> shardList = new ArrayList<Shard>();
		shardList.add(shard);
		List<ShardResultSet> shardResultSet = updateAddBatch(statement, params, shardList,applyExtention);
		if (shardResultSet.isEmpty()) {
			return 0;
		}
		return (int) shardResultSet.get(0).getResult().get(0);
	}

	/**
	 * 指定多个shard的批量更新 通过addBatch方式提交
	 * @param namespace
	 * @param sqlId
	 * @param paramters
	 * @return
	 */
	public List<ShardResultSet> updateAddBatch(final String statement, final List<Object> params, List<Shard> shardList) {
		return updateAddBatch(statement	, params,shardList,true);
	}
	
	public List<ShardResultSet> updateAddBatch(final String statement, final List<Object> params, List<Shard> shardList,boolean applyExtention) {
		shardList = existAndSave(shardList);
		if (shardList.isEmpty()) {
			return Collections.emptyList();
		}
		if(applyExtention){
			StatementExecutor handle = getExecutorForStatement(statement);
			if(handle != null) {
				return handle.execute(this, statement, params,shardList);
			}
		}
		
		List<ShardResultSet> originalResultList = new ArrayList<ShardResultSet>();
		if (CollectionUtils.isNotEmpty(shardList)) {
			SqlSessionCallBack action = new SqlSessionCallBack() {
				@Override
				public Object execute(SqlSession sqlSession) {
					for (Object param : params) {
						sqlSession.update(statement, param);
					}
					return handelBatchResult(sqlSession.flushStatements());
				}
			};
			originalResultList = execute(action, shardList, ExecutorType.BATCH);
		}
		return originalResultList;
	}

	public Object selectOne(String statement) {
		return this.selectOne(statement, new Object());
	}

	public Object selectOne(String namespace, String sqlId) {
		String statement = namespace + "." + sqlId;
		return this.selectOne(statement, new Object());
	}

	public Object selectOne(final String statement, final Object parameter) {
		return selectOne(statement, parameter, true);
	}
	
	public Object selectOne(final String statement, final Object parameter,boolean applyExtention) {
		List<Object> result = selectList(statement, parameter,applyExtention);
		if (result == null || result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}

	public Object selectOne(String namespace, String sqlId, Object parameter) {
		String statement = namespace + "." + sqlId;
		return this.selectOne(statement, parameter);
	}

	public List selectList(String statement) {
		return this.selectList(statement, new Object());
	}

	public List selectList(String namespace, String sqlId) {
		String statement = namespace + "." + sqlId;
		return this.selectList(statement, new Object());
	}

	public List selectList(final String statement, final Object parameter) {
		return selectList(statement,parameter,true);
	}
	
	public List selectList(final String statement, final Object parameter,boolean applyExtention) {
		if(applyExtention){
			StatementExecutor handle = getExecutorForStatement(statement);
			if(handle != null) {
				return (List) handle.execute(this, statement, parameter);
			}
		}
		
		List<Shard> resultList = lookupDataSourcesByRouter(statement, parameter);
		if (resultList.size() == 1) {
			List<ShardResultSet> shardResultSets = selectList(statement, parameter, resultList, false);
			List<Object> result = new ArrayList<Object>();
			if (shardResultSets != null && shardResultSets.size() > 0) {
				ShardResultSet shardResultSet = shardResultSets.get(0);
				List<Object> shardResult = shardResultSet.getResult();
				if (shardResult != null && shardResult.size() > 0) {
					result.addAll(shardResult);
				}
			}
			return result;
		}
		ProactorExecutor proactorExecutor = getProactorExecutor();
		if (proactorExecutor != null) {
			MergeRule megeRule = mergeExecutor.getMergeRule(statement, parameter, getSqlSessionFactory());
			if (megeRule != null && megeRule.hasProactor()) {
				return proactorExecutor.executor(statement, parameter, this, megeRule);
			}
		}
		SqlSessionCallBack action = new SqlSessionCallBack() {
			@Override
			public List<Object> execute(SqlSession sqlSession) {
				return sqlSession.selectList(statement, parameter);
			}
		};
		List<Object> result = executeQuery(action, resultList, statement, parameter);
		return this.mergeResult(result);
	}

	public List selectList(String namespace, String sqlId, Object parameter) {
		String statement = namespace + "." + sqlId;
		return this.selectList(statement, parameter);
	}

	public Object selectOne(String statement, Shard shard) {
		return this.selectOne(statement, new Object(), shard);
	}

	public Object selectOne(String namespace, String sqlId, Shard shard) {
		String statement = namespace + "." + sqlId;
		return this.selectOne(statement, new Object(), shard);
	}

	public Object selectOne(final String statement, final Object parameter, Shard shard) {
		return selectOne(statement,parameter,shard,true);
	}
	
	public Object selectOne(final String statement, final Object parameter, Shard shard, boolean applyExtention) {
		List<Object> result = selectList(statement, parameter, shard,applyExtention);
		if (result == null || result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}
	
	public List selectList(final String statement, final Object parameter, Shard shard) {
		return selectList(statement,parameter,shard,true);
	}
	
	public List selectList(final String statement, final Object parameter, Shard shard, boolean applyExtention) {
		List<Shard> shardList = new ArrayList<Shard>();
		shardList.add(shard);
		List<ShardResultSet> shardResultSet = selectList(statement, parameter, shardList, applyExtention);
		if (shardResultSet.isEmpty()) {
			return new ArrayList();
		}
		return shardResultSet.get(0).getResult();
	}

	public List<ShardResultSet> selectList(final String statement, final Object parameter, List<Shard> sharList) {
		return selectList(statement, parameter, sharList, true);
	}
	
	public List<ShardResultSet> selectList(final String statement, final Object parameter, List<Shard> shardList, boolean applyExtention) {
		if(applyExtention){
			StatementExecutor handle = getExecutorForStatement(statement);
			if(handle != null) {
				return handle.execute(this, statement, parameter,shardList);
			}
		}
		
		List<ShardResultSet> originalResultList = new ArrayList<ShardResultSet>();
		if (CollectionUtils.isNotEmpty(shardList)) {
			SqlSessionCallBack callback = new SqlSessionCallBack() {
				@Override
				public List<Object> execute(SqlSession sqlSession) {
					return sqlSession.selectList(statement, parameter);
				}
			};
			originalResultList = execute(callback, shardList);
		}
		return originalResultList;
	}

	@Deprecated
	public List<ShardResultSet> selectList(final String statement, final Object parameter, List<Shard> shardList, Map<String, ShardParameter> spMap) {
		List<ShardResultSet> originalResultList = new ArrayList<ShardResultSet>();
		if (CollectionUtils.isNotEmpty(shardList)) {
			SqlSessionCallBack callback = new SqlSessionCallBack() {
				@Override
				public List<Object> execute(SqlSession sqlSession) {
					return sqlSession.selectList(statement, parameter);
				}
			};
			originalResultList = execute(callback, shardList);
		}
		return originalResultList;
	}
	
	public List<ShardResultSet> selectListWithShardParameters(final String statement, Map<Shard, Object> shardParameters) {
		if(shardParameters == null || shardParameters.isEmpty()) {
			return Collections.emptyList();
		}
		
		Map<Shard, SqlSessionCallBack> actionMap = new HashMap<>();
		
		for(Entry<Shard, Object> entry : shardParameters.entrySet()) {
			Shard shard = entry.getKey();
			final Object parameter = entry.getValue();
			SqlSessionCallBack callback = new SqlSessionCallBack() {
				@Override
				public List<?> execute(SqlSession sqlSession) {
					return sqlSession.selectList(statement, parameter);
				}
			};
			actionMap.put(shard, callback);
		}
		return execute(actionMap);
	}
	
	protected List<Object> executeQuery(SqlSessionCallBack action, List<Shard> shardList, String statementName, Object parameter) {
		List<ShardResultSet> originalResultList = execute(action, shardList);
		List<Object> resultList = new ArrayList<Object>();
		if (CollectionUtils.isNotEmpty(originalResultList)) {
			if (mergeExecutor != null) {
				MergeRule megeRule = mergeExecutor.getMergeRule(statementName, parameter, getSqlSessionFactory());
				if (megeRule != null && megeRule.hasMerger()) {
					List<Object> tempResultList = new ArrayList<Object>();
					for (ShardResultSet item : originalResultList) {
						List<Object> result = item.getResult();
						for (Object obj : result) {
							if (obj != null) {
								tempResultList.add(result);
								break;
							}
						}
					}
					if (CollectionUtils.isNotEmpty(tempResultList)) {
						return mergeExecutor.executor(tempResultList, statementName, parameter, getSqlSessionFactory(), megeRule);
					}
				}
			}
			for (ShardResultSet item : originalResultList) {
				List<Object> result = item.getResult();
				if (result != null) {
					for (Object obj : result) {
						if (obj != null) {
							resultList.add(obj);
						}
					}
				}
			}
		}
		return resultList;
	}
	
	protected List<ShardResultSet> execute(Map<Shard, SqlSessionCallBack> actionMap) {
		return execute(actionMap, ExecutorType.SIMPLE);
	}

	protected List<ShardResultSet> execute(Map<Shard, SqlSessionCallBack> actionMap, ExecutorType type) {
		List<ShardStatement> shardStatements = new ArrayList<ShardStatement>();

		for (Entry<Shard, SqlSessionCallBack> entry : actionMap.entrySet()) {
			Shard shard = entry.getKey();
			SqlSessionCallBack action = entry.getValue();

			ShardStatement shardStatement = newShardStatement(shard, action, type);

			shardStatements.add(shardStatement);
		}
		List<ShardResultSet> results = processor.process(shardStatements);
		return results;
	}
	
	protected List<ShardResultSet> execute(SqlSessionCallBack action, List<Shard> shardList) {
		return execute(action, shardList, ExecutorType.SIMPLE);
	}
	
	protected List<ShardResultSet> execute(SqlSessionCallBack action, List<Shard> shardList,ExecutorType type) {
		List<ShardStatement> shardStatements = new ArrayList<ShardStatement>();
		for (Shard shard : shardList) {
			ShardStatement shardStatement = newShardStatement(shard, action, type);
			shardStatements.add(shardStatement);
		}

		List<ShardResultSet> results = processor.process(shardStatements);
		return results;
	}

	private ShardStatement newShardStatement(Shard shard, SqlSessionCallBack action, ExecutorType type) {
		ShardStatement shardStatement = new ShardStatement();
		String shardId = shard.getId();
		shardStatement.setAction(action);
		shardStatement.setDataSource(shard.getDataSource());
		shardStatement.setShardId(shardId);
		shardStatement.setExecutor(getDataSourceExecutor());
		shardStatement.setExecutorType(type);
		return shardStatement;
	}

    public List<Shard> lookupDataSourcesByRouter(String statementName, Object parameterObject) {
        List<Shard> resultList = new ArrayList<Shard>();
        if (isPartitioning()) {
            if (getRouter().isRouteExist(statementName)) {
            	Set<String> ids = getRouter().route(statementName, parameterObject);
            	Set<Shard> dsSet = shardManager.getShards(ids);
                if (!dsSet.isEmpty()) {
                    resultList.addAll(dsSet);
                    return resultList;
                } else if (getDefaultShardPolicy() == DefaultShardPolicy.ROUTE_NOSHARD_ERROR) {
                    throw new NoShardFoundException("No route found for \"" + statementName + "\" with \"" + parameterObject + "\"");
                }
            }
        }
        if(logger.isInfoEnabled()){
        logger.info("No matched rule found for {} with parameter {} , use defaultShard", statementName, parameterObject);
        }
        Shard defaultShard = shardManager.getDefaultShard();
        if (defaultShard != null) {
            resultList.add(defaultShard);
            return resultList;
        }
        throw new NoShardFoundException("No defaultShard found for \"" + statementName + "\" with \"" + parameterObject + "\"");
    }

    private Map<Shard, List<Object>> lookupShardsWithList(String statement, List<?> parameters) {
        Map<Shard, List<Object>> parameterMap = new HashMap<>();
        for (Object parameter : parameters) {
            List<Shard> resultShards = lookupDataSourcesByRouter(statement, parameter);
            if (CollectionUtils.isEmpty(resultShards)) {

            } else {
                for (Shard shard : resultShards) {
                    List<Object> list = parameterMap.get(shard);

                    if (list == null) {
                        list = new ArrayList<>();
                        parameterMap.put(shard, list);
                    }

                    list.add(parameter);
                }
            }
        }
        return parameterMap;
    }

	protected void init() {
		Assert.notNull(shardManager, "ShardManager can't be null");
		this.processor = new DefaultShardStatementProcessor(getSqlSessionFactory());
		this.dataSourceExecutor = createExecutorService(getPoolSize(), "ExecutorForDataSource");
		initMergeRuleForPage();
		this.extensionLoader = new MapperExtensionLoader(this,extesionLocations);
		initMappedStatementHandler();
	}
	
	public void destroy(){
		if(this.dataSourceExecutor != null && !this.dataSourceExecutor.isShutdown()){
			try {
				this.dataSourceExecutor.shutdown();
			} catch (Exception e) {
				logger.warn(e.getMessage(), e);
			}
			this.dataSourceExecutor = null;
		}
	}

    private ExecutorService createExecutorService(int poolSize, final String method) {
    	/**
		 * 任务队列与线程数一致
	         int coreSize = Runtime.getRuntime().availableProcessors();
	         if (poolSize < coreSize) {
	             coreSize = poolSize; 
	         }
		 */
		
		int coreSize = poolSize;
		
		/**
	        ThreadFactory tf = new ThreadFactory() {
	            @Override
				public Thread newThread(Runnable r) {
	                Thread t = new Thread(r, method);
	                t.setDaemon(true);
	                return t;
	            }
	        };
		 */
		ThreadFactory tf = new NamedThreadFactory(method, true);
		BlockingQueue<Runnable> queueToUse = new LinkedBlockingQueue<>(coreSize);
		final ThreadPoolExecutor executor =
				new ThreadPoolExecutor(coreSize, poolSize, 60, TimeUnit.SECONDS, queueToUse, tf, new ThreadPoolExecutor.CallerRunsPolicy());
		return executor;
    }

	
	/**
	 * <p>初始化完成后对所有分页语句尝试生成 MergeRule，
	 * 以避免在运行中生成分页 MergeRule 时，
	 * 调用Configuration.addMappedStatement方法，造成并发问题
	 * </p>
	 * 
	 * <p>此方法根据 paramterType 判断是否属于分页语句。
	 * 如因扩展或其他需要，某些分页语句其配置的paramterType并非ParameterWrapper，
	 * 则需要手动处理相关语句</p>
	 */
	private void initMergeRuleForPage() {
		
		if(mergeExecutor == null && proactorExecutor == null)
			return;
		
		Set<MappedStatement> pageSet = new HashSet<>();
		
		Configuration configuration = getConfiguration();
		Collection<MappedStatement> mappedStatements = configuration.getMappedStatements();
		
		for(Object obj : mappedStatements) {
			
			if(!(obj instanceof MappedStatement)){
				continue;
			}
			
			MappedStatement ms = (MappedStatement) obj;
			
			Class<?> paramterType = ms.getParameterMap().getType();
			if(paramterType != null && ParameterWrapper.class.isAssignableFrom(paramterType)) {
				pageSet.add(ms);
			}
		}
		
		ParameterWrapper nullParameterWrapper = new ParameterWrapper();
		for(MappedStatement ms : pageSet) {
			mergeExecutor.getMergeRule(ms.getId(), nullParameterWrapper, sqlSessionFactory);
		}
	}
	
	private void initMappedStatementHandler(){
		
		Set<MappedStatement> handleStatementSet = new HashSet<>();
		
		Configuration configuration = getConfiguration();
		Collection<MappedStatement> mappedStatements = configuration.getMappedStatements();
		
		for(Object obj : mappedStatements) {
			if(!(obj instanceof MappedStatement)){
				continue;
			}
			
			MappedStatement ms = (MappedStatement) obj;
			
			Class<?> paramterType = ms.getParameterMap().getType();
			if(paramterType != null && extensionLoader.getHandler(paramterType) != null) {
				handleStatementSet.add(ms);
			}
		}
		
		for(MappedStatement ms : handleStatementSet) {
			StatementHandler handler = extensionLoader.getHandler(ms.getParameterMap().getType());
			handler.handle(this, ms);
		}
	}
	
	private StatementExecutor getExecutorForStatement(String sqlId){
		Configuration configuration = getConfiguration();
		MappedStatement ms = configuration.getMappedStatement(sqlId);
		Class<?> parameterType = ms.getParameterMap().getType();
		
		if(parameterType == null)
			return null;
		
		return extensionLoader.getExecutor(parameterType);
	}

    /**
     * 合并数量
     *
     * @param result
     * @return int 数量
     */
    private int mergeNumber(List<Object> result) {
        Integer sum = 0;
        if (result != null && !result.isEmpty()) {
            for (Object obj : result) {
                sum += (Integer) obj;
            }
        }
        return sum.intValue();
    }

    /**
     * 合并记录集
     *
     * @param result
     * @return List
     */
    private List mergeResult(List<Object> result) {
        List list = new ArrayList();
        for (Object obj : result) {
            if (obj instanceof Collection) {
                list.addAll((Collection) obj);
            } else {
                list.add(obj);
            }
        }
        return list;
    }

    /**
     * 用于addBatch方法的记录集合并
     *
     * @param batchResults
     * @return
     */
    private int handelBatchResult(List<BatchResult> batchResults) {
        if (batchResults == null) {
            return 0;
        }
        int count = 0;
        for (BatchResult batchResult : batchResults) {
            for (int num : batchResult.getUpdateCounts()) {
                count += num;
            }
        }
        return count;
    }

    /**
     * 用于保证操作幂等性
     *
     * @param shardList
     * @return List<Shard>
     */
    private List<Shard> existAndSave(List<Shard> shardList) {
        if (shardList.isEmpty()) {
            return shardList;
        }
        if (!isOpenIdempotentService()) {
            return shardList;
        }
        Object obj = IdempotentContext.getIdempotentObj();
        if (obj == null) {
            return shardList;
        }
        return idempotentUtil.existAndSave(obj, shardList);
    }

}
