package com.dcits.galaxy.dal.mybatis.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;

import com.dcits.galaxy.dal.mybatis.merger.MergeExecutor;
import com.dcits.galaxy.dal.mybatis.merger.config.vo.MergeRule;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;
import com.dcits.galaxy.dal.mybatis.shard.ShardResultSet;
import com.dcits.galaxy.dal.mybatis.table.proactor.ProactorExecutor;
import com.dcits.galaxy.dal.mybatis.table.router.ITableRouter;
import com.dcits.galaxy.dal.mybatis.table.router.TableShardContext;
import com.dcits.galaxy.dal.mybatis.table.tableshard.TableShard;
/**
 * @author huang.zhe
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TableShardSqlSessionTemplate {

	private SqlSessionTemplate sqlSessionTemplate;

	private ITableRouter router;

	private MergeExecutor mergeExecutor = null;

	private ProactorExecutor proactorExecutor = null;

	
	public MergeExecutor getMergeExecutor() {
		return mergeExecutor;
	}
	
	public Configuration getConfiguration() {
		return sqlSessionTemplate.getConfiguration();
	}
	

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionTemplate.getSqlSessionFactory();
	}

	public ITableRouter getRouter() {
		return router;
	}

	public void setRouter(ITableRouter router) {
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
	
	public boolean isPartitioning() {
		return router != null;
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
		if (isPartitioning()) {
			List<TableShard> tableShards = lookupTableShardsByRouter(statement, parameter);
			if (tableShards.isEmpty()) {
				return sqlSessionTemplate.insert(statement, parameter);
			}
			List resultList = new ArrayList<>();
			for (TableShard tableShard : tableShards) {
				TableShardContext.put(tableShard);
				int result = sqlSessionTemplate.insert(statement, parameter);
				resultList.add(result);
			}
			return mergeNumber(resultList);
		} else {
			return sqlSessionTemplate.insert(statement, parameter);
		}
	}

	public int insertBatch(String namespace, String sqlId, List<?> paramters) {
		String statement = namespace + "." + sqlId;
		return this.insertBatch(statement, paramters);
	}

	public int insertBatch(final String statement, List<?> parameters) {
		if (isPartitioning()) {
			List resultList = new ArrayList<>();
			Map<TableShard, List<Object>> parameterMap = new HashMap<>();
			for (Object parameter : parameters) {
				List<TableShard> tableShards = lookupTableShardsByRouter(statement, parameter);
				if (tableShards.isEmpty()) {
					return sqlSessionTemplate.insert(statement, parameters);
				} else {
					for (TableShard ts : tableShards) {
						List<Object> list = parameterMap.get(ts);
						if (list == null) {
							list = new ArrayList<>();
							parameterMap.put(ts, list);
						}
						list.add(parameter);
					}
				}
			}
			for (Map.Entry<TableShard, List<Object>> entry : parameterMap.entrySet()) {
				TableShardContext.put(entry.getKey());
				int rs = sqlSessionTemplate.insert(statement, entry.getValue());
				resultList.add(rs);
			}
			return this.mergeNumber(resultList);
		} else {
			return sqlSessionTemplate.insert(statement, parameters);
		}
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
		if (isPartitioning()) {
			List<TableShard> tableShards = lookupTableShardsByRouter(statement, parameter);
			if (tableShards.isEmpty()) {
				return sqlSessionTemplate.delete(statement, parameter);
			}
			List resultList = new ArrayList<>();
			for (TableShard tableShard : tableShards) {
				TableShardContext.put(tableShard);
				int result = sqlSessionTemplate.delete(statement, parameter);
				resultList.add(result);
			}
			return mergeNumber(resultList);
		} else {
			return sqlSessionTemplate.delete(statement, parameter);
		}
	}
    
	public int deleteBatch(final String statement, List<?> parameters) {
		if (isPartitioning()) {
			List resultList = new ArrayList<>();
			Map<TableShard, List<Object>> parameterMap = new HashMap<>();
			for (Object parameter : parameters) {
				List<TableShard> tableShards = lookupTableShardsByRouter(statement, parameter);
				if (tableShards.isEmpty()) {
					return sqlSessionTemplate.delete(statement, parameters);
				} else {
					for (TableShard ts : tableShards) {
						List<Object> list = parameterMap.get(ts);
						if (list == null) {
							list = new ArrayList<>();
							parameterMap.put(ts, list);
						}
						list.add(parameter);
					}
				}
			}
			for (Map.Entry<TableShard, List<Object>> entry : parameterMap.entrySet()) {
				TableShardContext.put(entry.getKey());
				int rs = sqlSessionTemplate.delete(statement, entry.getValue());
				resultList.add(rs);
			}
			return this.mergeNumber(resultList);
		} else {
			return sqlSessionTemplate.delete(statement, parameters);
		}
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
		if (isPartitioning()) {
			List<TableShard> tableShards = lookupTableShardsByRouter(statement, parameter);
			if (tableShards.isEmpty()) {
				return sqlSessionTemplate.update(statement, parameter);
			}
			List resultList = new ArrayList<>();
			for (TableShard tableShard : tableShards) {
				TableShardContext.put(tableShard);
				int result = sqlSessionTemplate.update(statement, parameter);
				resultList.add(result);
			}
			return mergeNumber(resultList);
		} else {
			return sqlSessionTemplate.update(statement, parameter);
		}
	}

	public <T> T selectOne(String statement) {
		return this.selectOne(statement, new Object());
	}

	public <T> T selectOne(String namespace, String sqlId) {
		String statement = namespace + "." + sqlId;
		return this.selectOne(statement, new Object());
	}

	public <T> T selectOne(final String statement, final Object parameter) {
		if (isPartitioning()) {
			List<TableShard> tableShards = lookupTableShardsByRouter(statement, parameter);
			if (tableShards.isEmpty()) {
				return sqlSessionTemplate.selectOne(statement, parameter);
			}else{
					TableShard ts = tableShards.get(0);
					TableShardContext.put(ts);
					return sqlSessionTemplate.selectOne(statement, parameter);
				}
			}
		 else {
			return sqlSessionTemplate.selectOne(statement, parameter);
		}
	}

	public <T> T selectOne(String namespace, String sqlId, Object parameter) {
		String statement = namespace + "." + sqlId;
		return this.selectOne(statement, parameter);
	}

	public <E> List<E> selectList(String statement) {
		return this.selectList(statement, new Object());
	}

	public <E> List<E> selectList(String namespace, String sqlId) {
		String statement = namespace + "." + sqlId;
		return this.selectList(statement, new Object());
	}

	public <E> List<E> selectList(final String statement, final Object parameter) {
		if (isPartitioning()) {
			List<TableShard> tableShards = lookupTableShardsByRouter(statement, parameter);
			if (tableShards.isEmpty()) {
				return sqlSessionTemplate.selectList(statement, parameter);
			}
			if (tableShards.size() == 1) {
				TableShard ts = tableShards.get(0);
				TableShardContext.put(ts);
				return sqlSessionTemplate.selectList(statement, parameter);
			}
			ProactorExecutor proactorExecutor = getProactorExecutor();
			if (proactorExecutor != null) {
				MergeRule megeRule = mergeExecutor.getMergeRule(statement, parameter, this.sqlSessionTemplate.getSqlSessionFactory());
				if (megeRule != null && megeRule.hasProactor()) {
					return proactorExecutor.executor(statement, parameter, this, megeRule);
				}
			}
			List<Object> resultList = new ArrayList<>();
			for (TableShard ts : tableShards) {
				TableShardContext.put(ts);
				List<Object> originalResultList = sqlSessionTemplate.selectList(statement, parameter);
				resultList.add(originalResultList);
			}
			resultList = executeQuery(statement, parameter, resultList);
			return this.mergeResult(resultList);
		} else {
			return sqlSessionTemplate.selectList(statement, parameter);
		}
	}

	public <E> List<E> selectList(String namespace, String sqlId, Object parameter) {
		String statement = namespace + "." + sqlId;
		return this.selectList(statement, parameter);
	}

	public List<ShardResultSet> selectList(final String statement, final Object parameter, List<TableShard> tableShards) {
		List<ShardResultSet> originalResultList = new ArrayList<ShardResultSet>();
		if (!tableShards.isEmpty()) {
			for (TableShard ts : tableShards) {
				String shardId = ts.getId();
				TableShardContext.put(ts);
				List<Object> rs = sqlSessionTemplate.selectList(statement, parameter);
				ShardResultSet shardResultSet = new ShardResultSet(shardId, rs);
				originalResultList.add(shardResultSet);
			}

		}
		return originalResultList;
	}

	public List<ShardResultSet> selectList(final String statement, Map<TableShard,Object> shardParameters) {
		List<ShardResultSet> originalResultList = new ArrayList<ShardResultSet>();
		if (!shardParameters.isEmpty()) {
			for(Entry<TableShard, Object> entry:shardParameters.entrySet()) {
				TableShard shard = entry.getKey();
				Object parameter = entry.getValue();
				TableShardContext.put(shard);
				List<Object> rs = sqlSessionTemplate.selectList(statement, parameter);
				ShardResultSet shardResultSet = new ShardResultSet(shard.getId(), rs);
				originalResultList.add(shardResultSet);
			}
		}
		return originalResultList;
	}

	public List<TableShard> lookupTableShardsByRouter(final String statementName, final Object parameterObject) {
		List<TableShard> resultList = new ArrayList<TableShard>();
		if (getRouter() != null) {
			Set<TableShard> tsSet = null;
			if (parameterObject instanceof List) {
				List<Object> params = (List<Object>) parameterObject;
				Set<TableShard> rs = new HashSet<TableShard>();
				for (Object param : params) {
					tsSet = getRouter().routeTableShard(statementName, param);
					if (!tsSet.isEmpty()) {
						rs.addAll(tsSet);
					}
				}
				resultList.addAll(rs);
			} else {
				tsSet = getRouter().routeTableShard(statementName,
						parameterObject);
				if (!tsSet.isEmpty()) {
					resultList.addAll(tsSet);
				}
			}
		}
		return resultList;
	}

	private List<Object> executeQuery(String statementName, Object parameter, List<Object> tempResultList) {
		if (mergeExecutor != null) {
			MergeRule megeRule = mergeExecutor.getMergeRule(statementName, parameter, this.sqlSessionTemplate.getSqlSessionFactory());
			if (megeRule != null && megeRule.hasMerger()) {
				return mergeExecutor.executor(tempResultList, statementName, parameter, this.sqlSessionTemplate.getSqlSessionFactory(), megeRule);
			}
		}
		return tempResultList;
	}
	
	public void init(){
		initMergeRuleForPage();
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
			mergeExecutor.getMergeRule(ms.getId(), nullParameterWrapper, getSqlSessionFactory());
		}
	}

	/**
	 * 合并数量
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

}
