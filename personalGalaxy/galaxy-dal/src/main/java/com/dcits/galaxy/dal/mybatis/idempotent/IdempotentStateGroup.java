package com.dcits.galaxy.dal.mybatis.idempotent;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于存储各分库幂等信息
 * @author huang.zhe
 */
public class IdempotentStateGroup {
	private Object idempotentObj;

	private Map<String, IdempotentState> stateMap = new HashMap<String, IdempotentState>();

	public Object getIdempotentObj() {
		return idempotentObj;
	}

	public void setIdempotentObj(Object idempotentObj) {
		this.idempotentObj = idempotentObj;
	}

	public Map<String, IdempotentState> getStateMap() {
		return stateMap;
	}

}
