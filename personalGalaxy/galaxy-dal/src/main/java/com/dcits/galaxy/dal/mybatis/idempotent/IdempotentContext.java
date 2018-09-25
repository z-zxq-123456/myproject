package com.dcits.galaxy.dal.mybatis.idempotent;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.dcits.galaxy.dal.mybatis.exception.IdempotentException;
import com.dcits.galaxy.dal.mybatis.exception.UnexpectedTransactionException;

/**
 * 基于线程变量存放各idempotentId和状态的上下文容器
 * 
 * @author huang.zhe
 */
public class IdempotentContext {
	private static ThreadLocal<IdempotentStateGroup> idempotentStateGroup = new ThreadLocal<IdempotentStateGroup>();

	private static Logger logger = LoggerFactory.getLogger(IdempotentContext.class);
	
	private final static String FIRST_SHARD_STATE = "";
    
	
	public static Object getIdempotentObj() {
		IdempotentStateGroup stateGroup = getStateGroup();
		if(stateGroup == null)
			return null;
		return stateGroup.getIdempotentObj();
	}

	public static void setIdempotentObj(Object obj) {
		boolean isActualTransaction = TransactionSynchronizationManager.isActualTransactionActive();
		if(!isActualTransaction) {
			throw new UnexpectedTransactionException("it not in a actual transaction.");
		}
		
		IdempotentStateGroup stateGroup = getStateGroup();
		if(stateGroup == null) {
			stateGroup = setStateGroup(new IdempotentStateGroup());
			
			if (logger.isDebugEnabled()) {
				logger.debug("Regist transaction synchronization for idempotent object [{}]", obj);
			}
			TransactionSynchronizationManager.registerSynchronization(new IdempotentContextSynchronization());
		}
		if (!obj.equals(stateGroup.getIdempotentObj())) {
			stateGroup.getStateMap().clear();
			stateGroup.setIdempotentObj(obj);;
		}
	}

	public static IdempotentState getState() {
		IdempotentStateGroup stateGroup = getStateGroup();
		
		if(stateGroup == null) {
			throw new IdempotentException("idempotentObj is null, must set idempotentObj before this method! ");
		}
		
		Map<String, IdempotentState> parameterMap = stateGroup.getStateMap();
		
		if (parameterMap.isEmpty()) {
			put(FIRST_SHARD_STATE, IdempotentState.DEFAULT);
		}
		return parameterMap.get(FIRST_SHARD_STATE);
	}

	public static void setState(IdempotentState state) {
		put(FIRST_SHARD_STATE, state);
	}

	public static boolean containsShardId(String shardId) {
		IdempotentStateGroup stateGroup = getStateGroup();
		
		if(stateGroup == null) {
			throw new IdempotentException("idempotentObj is null, must set idempotentObj before this method! ");
		}
		
		Map<String, IdempotentState> parameterMap = stateGroup.getStateMap();
		return parameterMap.containsKey(shardId);
	}

	public static void put(String shardId, IdempotentState state) {
		IdempotentStateGroup stateGroup = getStateGroup();
		
		if(stateGroup == null) {
			throw new IdempotentException("idempotentObj is null, must set idempotentObj before this method! ");
		}
		
		Map<String, IdempotentState> parameterMap = stateGroup.getStateMap();
		parameterMap.put(shardId, state);
	}

	public static IdempotentState getState(String shardId) {
		IdempotentStateGroup stateGroup = getStateGroup();
		
		if(stateGroup == null) {
			throw new IdempotentException("idempotentObj is null, must set idempotentObj before this method! ");
		}
		
		Map<String, IdempotentState> parameterMap = stateGroup.getStateMap();
		return parameterMap.get(shardId);
	}

	public static boolean isEmpty() {
		IdempotentStateGroup stateGroup = getStateGroup();
		
		if(stateGroup == null) {
			return true;
		}
		
		Map<String, IdempotentState> parameterMap = stateGroup.getStateMap();
		return parameterMap.isEmpty();
	}
	
	public static void clear(){
		IdempotentStateGroup stateGroup = getStateGroup();

		if (stateGroup != null) {
			stateGroup.setIdempotentObj(null);
			stateGroup.getStateMap().clear();
		}
	}
	
	private static IdempotentStateGroup getStateGroup() {
		return idempotentStateGroup.get();
	}
	
	private static IdempotentStateGroup setStateGroup(IdempotentStateGroup stateGroup) {
		idempotentStateGroup.set(stateGroup);
		return idempotentStateGroup.get();
	}
	
	private static void flush(){
		idempotentStateGroup.remove();
	}
	
	private static class IdempotentContextSynchronization extends TransactionSynchronizationAdapter{
		
		private IdempotentStateGroup idempotent = null;
		
		@Override
		public void suspend() {
			idempotent = IdempotentContext.getStateGroup();
			flush();
		}

		@Override
		public void resume() {
			IdempotentContext.setStateGroup(idempotent);
		}

		@Override
		public void flush() {
			IdempotentContext.flush();
		}
		
		@Override
		public void afterCompletion(int status) {
			flush();
		}
	}
}
